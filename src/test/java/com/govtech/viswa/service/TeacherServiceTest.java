package com.govtech.viswa.service;

import com.govtech.viswa.pojo.TeacherBos;
import com.govtech.viswa.pojo.Teachers;
import com.govtech.viswa.util.Common;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.service
 * @class TeacherServiceTest
 */

@Log4j2
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
@ActiveProfiles("test")
class TeacherServiceTest {

    @Autowired
    TeacherService teacherService;

//    @Autowired
//    TeacherRepo teacherRepo;

    @Test
    void saveTeacher()   {
        String jsonString = Common.readJson("testdata/TestDataTeacherService.json");
        TeacherBos teacherBos = new TeacherBos();
        Teachers teachers = teacherBos.getTeachers(jsonString);
        teachers = teacherService.saveTeacher(teachers);
        assertTrue(teachers.size() == 6);

    }
}