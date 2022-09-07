package com.govtech.viswa.repo;

import com.govtech.viswa.entity.IdStudentTeacher;
import com.govtech.viswa.entity.StudentTeacher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.repo
 * @class StudentTeacherRepo
 */

//@Repository
public interface StudentTeacherRepo extends ReactiveCrudRepository<StudentTeacher, IdStudentTeacher> {

//    @Query(nativeQuery = true, value = "select * from studentTeacher where studentId = :studentId and teacherId = :teacherId")
//    Mono<StudentTeacher> findById(Long studentId, Long teacherId );

}
