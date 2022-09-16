package com.govtech.viswa.controller;

import com.govtech.viswa.entity.ApiResponse;
import com.govtech.viswa.entity.Student;
import com.govtech.viswa.exception.GlobalException;
import com.govtech.viswa.pojo.Students;
import com.govtech.viswa.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.controller
 * @class StudentController
 */

@Api(value="Student")
@RestController
@Log4j2
public class StudentController {

    @Autowired
    StudentService studentService;


    @GetMapping("/myname")
    public Mono<String> getMyName(){
        return Mono.just("saravanan");
    }

    @GetMapping("/onestudent")
    public Mono<Student> getOneStudent(){
        return studentService.getStudent();
    }

    @ApiOperation(value = "Save Students", response = ApiResponse.class )
    @RequestMapping(
            value = "students",
            method = RequestMethod.POST
    )
    @ResponseStatus( HttpStatus.CREATED )
    public ApiResponse<Students> save(@RequestBody Students students) {
        log.info("Saving students : " +students);

        for (Student student : students) {
            if (student.getEmail().isBlank()) {
                throw new GlobalException("Email cannot be blank", null, null, student.toString(), "" );
            }
        }

        students = studentService.saveStudent(students);

        return new ApiResponse<>(HttpStatus.CREATED, "Created Students records", students.size(), students);

    }



}
