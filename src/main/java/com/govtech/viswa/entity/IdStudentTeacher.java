package com.govtech.viswa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Sarav on 12 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.entity
 * @class IdStudentTeacher
 */

@Data
@NoArgsConstructor
public class IdStudentTeacher implements Serializable {

    @Id
    Long studentId;

    @Id
    Long teacherId;


    public IdStudentTeacher(Long studentId, Long teacherId) {
        this.studentId = studentId;
        this.teacherId = teacherId;
    }

    public IdStudentTeacher(Student student, Teacher teacher) {
        this.studentId = student.getId();
        this.teacherId = teacher.getId();
    }

}
