package com.govtech.viswa.pojo;

import com.govtech.viswa.entity.Teacher;
import com.govtech.viswa.util.Common;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.HashSet;

/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.pojo
 * @class Teachers
 */
@Data
@Log4j2
public class TeacherBos extends HashSet<TeacherBo> {



    public void setTeachers(TeacherBos teacherBos) {
        for (TeacherBo teacherBo :  teacherBos ) {
            this.add(teacherBo);

        }
    }

    public Teachers getTeachers() {
        return getTeachers(this);
    }

    public Teachers getTeachers(String myjson) {

        TeacherBos teacherBos = Common.gson.fromJson(myjson, TeacherBos.class );
        this.setTeachers(teacherBos);
        Teachers teachers = getTeachers(teacherBos);
        return teachers;
    }

    public Teachers getTeachers(TeacherBos teacherBos) {
        Teachers teachers = new Teachers();
        for (TeacherBo teacherBo :  teacherBos ) {
            Teacher teacher = new Teacher(teacherBo.getName(), teacherBo.getEmail());
            teachers.add(teacher);
        }
        return teachers;
    }


}
