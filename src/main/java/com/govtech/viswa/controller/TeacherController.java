package com.govtech.viswa.controller;

import com.govtech.viswa.entity.ApiResponse;
import com.govtech.viswa.entity.Teacher;
import com.govtech.viswa.exception.GlobalException;
import com.govtech.viswa.pojo.Teachers;
import com.govtech.viswa.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.controller
 * @class TeacherController
 */

@Api(value="Teacher")
@RestController
@Log4j2
public class TeacherController {

    @Autowired
    TeacherService teacherService;

//    @Autowired
//    TeacherRepo teacherRepo;

    @ApiOperation(value = "Save Teachers", response = ApiResponse.class )
    @RequestMapping(
            value = "teachers",
            method = RequestMethod.POST
    )
    @ResponseStatus( HttpStatus.CREATED )
    public ApiResponse<Teachers> save(@RequestBody Teachers teachers) {
        log.info("Saving teachers : " +teachers);

        for (Teacher teacher : teachers) {
            if (teacher.getEmail().isBlank()) {
                throw new GlobalException("Email cannot be blank", null, null, teacher.toString(), "" );
            }
        }

        teachers = teacherService.saveTeacher(teachers);

        return new ApiResponse<>(HttpStatus.CREATED, "Created Teachers records", teachers.size(), teachers);

    }

}
