package com.govtech.viswa.config;


import com.govtech.viswa.service.StudentService;
import com.govtech.viswa.service.StudentTeacherService;
import com.govtech.viswa.service.TeacherService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Sarav on 13 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.config
 * @class MyApplicationListener
 */

@Component
@Log4j2
@Order(0)
public class MyApplicationListener
        implements ApplicationListener< ApplicationReadyEvent > {
    // This is not for production, please delete this, it is a kind of unit testing


    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentTeacherService studentTeacherService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        log.info("Initialization");

        // By default, I am preparing the database with test data for your easy testing (since it is sample app)
        // Comment the following lines when you to start with blank database

        studentService.createStudentsData();
        teacherService.createTeachersData();
        studentTeacherService.createStudentTeacherData("testdata/TestDataLinkScienceTeacher.json");
        studentTeacherService.createStudentTeacherData("testdata/TestDataLinkMathsTeacher.json");
        studentTeacherService.runFindCommonStudents("testdata/TestDataFindStudentsForScienceTeacher.json");
        studentTeacherService.runFindCommonStudents("testdata/TestDataFindStudentsForScienceTeacherMathsTeacher.json");
    }

}



