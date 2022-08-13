package com.govtech.viswa.repo;

import com.govtech.viswa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.repo
 * @class StudentRepo
 */

@Repository
public interface StudentRepo extends JpaRepository<Student, String> {

    Student findByEmail(String email);
}
