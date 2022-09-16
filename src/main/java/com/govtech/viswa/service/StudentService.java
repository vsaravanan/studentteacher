package com.govtech.viswa.service;

import com.govtech.viswa.entity.Student;
import com.govtech.viswa.pojo.StudentBos;
import com.govtech.viswa.pojo.Students;
import com.govtech.viswa.repo.StudentRepo;
import com.govtech.viswa.util.Common;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.service
 * @class StudentService
 */

@Service
@Log4j2
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    public Mono<Student> getStudent() {
        Mono<Student> student = studentRepo.findByEmail("viswa@gmail.com");
        return student;

//        StudentBo studentDTO = new StudentBo("saravan", "saravan@gmail.com");
//        return Mono.just(StudentBo);
    }

    @Transactional
    public Student saveStudent(Student student) {
        Student student2 = studentRepo.save(student).block();
        return student2;
    }

    public Students saveStudent(Students students) {

        Students students2 = new Students();
        for (Student student : students) {
            Student studentUpd = studentRepo.findByEmail(student.getEmail()).block();
            if (studentUpd != null) {
                studentUpd.setName(student.getName());
            } else {
                studentUpd = student;
            }

            Student finalStudent = saveStudent(studentUpd);
            students2.add(finalStudent);

        }


        return students2;
    }

    public void createStudentsData()  {
        // This is not for production, please delete this, it is a kind of unit testing

        String jsonString = Common.readJson("testdata/TestDataStudentService.json");
        StudentBos studentBos = new StudentBos();
        Students students = studentBos.getStudents(jsonString);
        students = saveStudent(students);
        log.info("Created Students test data" + students);

    }
}
