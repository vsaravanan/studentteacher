package com.govtech.viswa.service;

import com.google.gson.reflect.TypeToken;
import com.govtech.viswa.entity.Student;
import com.govtech.viswa.entity.StudentTeacher;
import com.govtech.viswa.entity.Teacher;
import com.govtech.viswa.exception.GlobalException;
import com.govtech.viswa.pojo.ListStudent;
import com.govtech.viswa.pojo.StudentTeacherBo;
import com.govtech.viswa.pojo.Students;
import com.govtech.viswa.repo.StudentRepo;
import com.govtech.viswa.repo.StudentTeacherRepo;
import com.govtech.viswa.jparepo.TeacherRepo;
import com.govtech.viswa.util.Common;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Sarav on 12 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.service
 * @class StudentTeacherService
 */

@Service
@Log4j2
//@Transactional
public class StudentTeacherService
{

    @Autowired
    StudentTeacherRepo studentTeacherRepo;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    TeacherRepo teacherRepo;

//    @Autowired
//    DatabaseClient databaseClient;

    @Autowired
    R2dbcEntityTemplate r2dbc;

    Flux< String> findAllByTeacherIn(List<String> listTeacher, Integer counts  ) {
        String query = """
             select s.email
             from
             (
                 select distinct s.id, s.email
                 from Student s
                 inner join StudentTeacher t
                    on s.id = t.studentId
                 inner join Teacher h
                    on t.teacherId = h.id
                   and h.email in (:listTeacher)
             ) s
             inner join StudentTeacher t
                on s.id = t.studentId
             inner join Teacher h
                on t.teacherId = h.id
               and h.email in (:listTeacher)
             group by s.id, s.email
             having count(*) = :counts
             """;

        Flux<String> emails =
                r2dbc.getDatabaseClient()
                        .sql(query)
                        .bind("listTeacher", listTeacher)
                        .bind("counts", counts)
                        .fetch()
                        .all()
                        .map(r -> r.get("email").toString())
                ;
        return emails;

    }

    Mono<StudentTeacher> findStudentTeacher(Long studentId, Long teacherId ) {
        String query = """ 
            select * from studentTeacher
            where studentId = :studentId 
            and teacherId = :teacherId 
            """;

        Mono<Map<String, Object>> monomap =
                r2dbc.getDatabaseClient()
                        .sql(query)
                .bind("studentId", studentId)
                .bind("teacherId", teacherId)
                .fetch()
                .first()
                ;


//        Mono<StudentTeacher> studentTeacher =
//        ObjectMapper mapper = new ObjectMapper();
//        User user = mapper.convertValue(info, User.class);
        Mono<StudentTeacher> studentTeacher = monomap.map(
                r -> new StudentTeacher(Long.valueOf(r.get("studentId").toString()),
                       Long.valueOf(r.get("teacherId").toString()))
        );

        return studentTeacher;
    }

    public void linkStudentTeacher(StudentTeacherBo studentTeacherBo)  {

        if (studentTeacherBo.getListStudents().size() == 0) {
            throw new GlobalException("No student record found to link with Teacher");
        }

        String teacherEmail = studentTeacherBo.getTeacherEmail();
        Teacher teacher = teacherRepo.findByEmail(teacherEmail);
        if (teacher == null) {
            throw new GlobalException("Teacher " + teacherEmail  + " does not exists", null, null, studentTeacherBo, "" );
        }
        log.info("Teacher found " + teacher);
        Students students = new Students();

        for (String studentEmail : studentTeacherBo.getListStudents()) {
            Student student = studentRepo.findByEmail(studentEmail).block();
            if (student == null) {
                throw new GlobalException("Student " + studentEmail  + " does not exists", null, null, studentTeacherBo, "" );
            }
            log.info("Student found " + student);
            students.add(student);
        }

        for (Student student : students) {
            StudentTeacher idStudentTeacher = new StudentTeacher(student, teacher);
            StudentTeacher studentTeacherExists = findStudentTeacher( idStudentTeacher.getStudentId(), idStudentTeacher.getTeacherId() ).block();
            if (studentTeacherExists == null) {
                StudentTeacher studentTeacher = idStudentTeacher;
                studentTeacherRepo.save(studentTeacher).block();
                log.info(" Linked Student and Teacher " + student + " " + teacher);
            }
            else {
                log.info(" Already linked Student and Teacher " + student + " " + teacher);
            }
        }

    }



    public void createStudentTeacherData(String filename)  {
        // This is not for production, please delete this, it is a kind of unit testing

        String jsonString = Common.readJson(filename);
        StudentTeacherBo studentTeacherBo = Common.gson.fromJson(jsonString, StudentTeacherBo.class );
        linkStudentTeacher(studentTeacherBo);
        log.info("Created link on Student Teacher for test data " + jsonString);

    }

    public ListStudent findCommonStudents(List<String> listTeacher)  {
        List<String> listStudent = findAllByTeacherIn(listTeacher, listTeacher.size()).collectList().block();
        if (listStudent.size() == 0) {
            // however, first I should check valid teacher
            throw new GlobalException("No students found for the teacher(s) ", null, null, listTeacher, "" );
        }
        ListStudent students = new ListStudent(listStudent);
        return students;
    }


    public ListStudent runFindCommonStudents(String filename)  {
        // This is not for production, please delete this, it is a kind of unit testing

        String jsonString = Common.readJson(filename);
        Type ListTeacher = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> listTeacher = Common.gson.fromJson(jsonString, ListTeacher );
        ListStudent listStudent = findCommonStudents(listTeacher);
        String msg = String.format("Found common student(s) %s for teacher(s) %s ", listStudent, listTeacher);
        log.info(msg);
        return listStudent;

    }




}
