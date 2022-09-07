package com.govtech.viswa.repo;

import com.govtech.viswa.entity.Teacher;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.repo
 * @class TeacherRepo
 */

//@Repository
public interface TeacherRepo extends R2dbcRepository<Teacher, Long> {

//    @Query(nativeQuery = true, value = "SELECT * FROM Teacher WHERE email = :email")
    Mono<Teacher> findByEmail(String email);
}
