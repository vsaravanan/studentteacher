package com.govtech.viswa.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author Sarav on 12 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.pojo
 * @class StudentTeacherBo
 */
@Data
public class StudentTeacherBo {
    String teacherEmail;
    List<String> listStudents;
}

