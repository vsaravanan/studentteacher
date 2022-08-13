package com.govtech.viswa.service;

import com.govtech.viswa.pojo.StudentBos;
import com.govtech.viswa.pojo.Students;
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
 * @class StudentServiceTest
 */

@Log4j2
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
@ActiveProfiles("test")
class StudentServiceTest {

    @Autowired
    StudentService studentService;


    @Test
    void saveStudent()  {
        String jsonString = Common.readJson("testdata/TestDataStudentService.json");
        StudentBos studentBos = new StudentBos();
        Students students = studentBos.getStudents(jsonString);
        students = studentService.saveStudent(students);
        assertTrue(students.size() == 5);

    }
}