package com.govtech.viswa.controller;

import com.govtech.viswa.entity.ApiResponse;
import com.govtech.viswa.pojo.ListStudent;
import com.govtech.viswa.pojo.StudentTeacherBo;
import com.govtech.viswa.service.StudentTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sarav on 13 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.controller
 * @class StudentTeacherController
 */

@Api(value="Student")
@RestController
@Log4j2
public class StudentTeacherController {


    @Autowired
    StudentTeacherService studentTeacherService;
    @ApiOperation(value = "Link Students with Teacher", response = ApiResponse.class )
    @RequestMapping(
            value = "students/register",
            method = RequestMethod.POST
    )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public ApiResponse<StudentTeacherBo> registerStudents(@RequestBody StudentTeacherBo studentTeacherBo) {
        log.info("Link Students with Teacher : " +studentTeacherBo);

        studentTeacherService.linkStudentTeacher(studentTeacherBo);

        return new ApiResponse<>(HttpStatus.NO_CONTENT, "Link Students with Teacher", studentTeacherBo.getListStudents().size(), studentTeacherBo);

    }

    private ApiResponse<ListStudent> myCommonStudents(List<String> listTeacher) {
        log.info("Common Students for the Teachers : " +listTeacher);

        ListStudent listStudent = studentTeacherService.findCommonStudents(listTeacher);

        String msg = String.format("Found common student(s) %s for teacher(s) %s ", listStudent, listTeacher);

        return new ApiResponse<>(HttpStatus.OK, msg, listStudent.getStudents().size(), listStudent);
    }

    @ApiOperation(value = "Common Students for the Teachers", response = ApiResponse.class )
    @RequestMapping(
            value = "commonstudents",
            method = RequestMethod.GET
    )
    public ApiResponse<ListStudent> commonStudents(@RequestBody List<String> listTeacher) {

        return myCommonStudents(listTeacher);
    }

    @ApiOperation(value = "Common Students for the Teachers (without json)", response = ApiResponse.class )
    @RequestMapping(
            value = "commonstudents2",
            method = RequestMethod.GET
    )
    public ApiResponse<ListStudent> commonStudents2(@RequestParam(name="teacher") List<String> listTeacher) {

        return myCommonStudents(listTeacher);

    }
}
