package com.govtech.viswa.repo;

import com.govtech.viswa.entity.IdStudentTeacher;
import com.govtech.viswa.entity.StudentTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.repo
 * @class StudentTeacherRepo
 */

@Repository
public interface StudentTeacherRepo extends JpaRepository<StudentTeacher, IdStudentTeacher> {

    @Query( nativeQuery = true, name = "Select.CommonStudentsForTeachers")
    List< String> findAllByTeacher (List<String> listTeacher, Integer counts );

}
