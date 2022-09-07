package com.govtech.viswa.service;

import com.govtech.viswa.entity.Teacher;
import com.govtech.viswa.pojo.TeacherBos;
import com.govtech.viswa.pojo.Teachers;
import com.govtech.viswa.repo.TeacherRepo;
import com.govtech.viswa.util.Common;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.service
 * @class TeacherService
 */

@Service
@Log4j2
public class TeacherService {

    @Autowired
    TeacherRepo teacherRepo;

//    @Transactional
    public Teacher saveTeacher(Teacher teacher) {
        Teacher teacher2 = teacherRepo.save(teacher).block();
        return teacher2;
    }

   public Teachers saveTeacher(Teachers teachers) {


        Teachers teachers2 = new Teachers();
        for (Teacher teacher : teachers) {
            Teacher teacherUpd = teacherRepo.findByEmail(teacher.getEmail()).block();
            if (teacherUpd != null) {
                teacherUpd.setName(teacher.getName());
            } else {
                teacherUpd = teacher;
            }

            Teacher finalTeacher = saveTeacher(teacherUpd);
            teachers2.add(finalTeacher);

        }

        return teachers2;
    }


    public void createTeachersData()  {
        // This is not for production, please delete this, it is a kind of unit testing

        String jsonString =  Common.readJson("testdata/TestDataTeacherService.json");
        TeacherBos teacherBos = new TeacherBos();
        Teachers teachers = teacherBos.getTeachers(jsonString);
        teachers = saveTeacher(teachers);
        log.info("Created Teachers test data" + teachers);
        log.info("test");

    }
}
