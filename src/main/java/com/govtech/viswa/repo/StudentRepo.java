package com.govtech.viswa.repo;

import com.govtech.viswa.entity.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.repo
 * @class StudentRepo
 */

//@Repository
public interface StudentRepo extends ReactiveCrudRepository<Student, Long> {

    Mono<Student> findByEmail(String email);


}
