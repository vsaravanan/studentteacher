package com.govtech.viswa.pojo;

import com.govtech.viswa.entity.Student;
import com.govtech.viswa.util.Common;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.HashSet;

/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.pojo
 * @class Students
 */
@Data
@Log4j2
public class StudentBos extends HashSet<StudentBo> {



    public void setStudents(StudentBos studentBos) {
        for (StudentBo studentBo :  studentBos ) {
            this.add(studentBo);

        }
    }

    public Students getStudents() {
        return getStudents(this);
    }

    public Students getStudents(String myjson) {

        StudentBos studentBos = Common.gson.fromJson(myjson, StudentBos.class );
        this.setStudents(studentBos);
        Students students = getStudents(studentBos);
        return students;
    }

    public Students getStudents(StudentBos studentBos) {
        Students students = new Students();
        for (StudentBo studentBo :  studentBos ) {
            Student student = new Student(studentBo.getName(), studentBo.getEmail());
            students.add(student);
        }
        return students;
    }


}
