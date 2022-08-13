package com.govtech.viswa.service;

import com.google.gson.reflect.TypeToken;
import com.govtech.viswa.pojo.ListStudent;
import com.govtech.viswa.pojo.StudentTeacherBo;
import com.govtech.viswa.util.Common;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.service
 * @class StudentTeacherServiceTest
 */

@Log4j2
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
@ActiveProfiles("test")
class StudentTeacherServiceTest {

    @Autowired
    StudentTeacherService studentTeacherService;

    void linkStudentTeacher(String filename) {
        String jsonString = Common.readJson(filename);
        StudentTeacherBo studentTeacherBo = Common.gson.fromJson(jsonString, StudentTeacherBo.class );
        studentTeacherService.linkStudentTeacher(studentTeacherBo);
        log.info("Created link on Student Teacher for test data " + jsonString);
    }

    @Test
    void linkScienceTeacher()   {

        linkStudentTeacher("testdata/TestDataLinkScienceTeacher.json");

    }



    @Test
    void linkMathsTeacher()   {

        linkStudentTeacher("testdata/TestDataLinkMathsTeacher.json");

    }

    ListStudent findCommonStudents(String filename) {
        String jsonString = Common.readJson(filename);
        Type ListTeacher = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> listTeacher = Common.gson.fromJson(jsonString, ListTeacher );
        ListStudent listStudent = studentTeacherService.findCommonStudents(listTeacher);
        String msg = String.format("Found common student(s) %s for teacher(s) %s ", listStudent, listTeacher);
        log.info(msg);
        return listStudent;
    }

    @Test
    void runFindCommonStudentsCase1()   {

        ListStudent listStudent = findCommonStudents("testdata/TestDataFindStudentsForScienceTeacher.json");

    }

    @Test
    void runFindCommonStudentsCase2()   {

        ListStudent listStudent = findCommonStudents("testdata/TestDataFindStudentsForScienceTeacherMathsTeacher.json");

    }
}