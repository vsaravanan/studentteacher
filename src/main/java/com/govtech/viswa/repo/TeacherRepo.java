package com.govtech.viswa.repo;

import com.govtech.viswa.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.repo
 * @class TeacherRepo
 */

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, String> {

    Teacher findByEmail(String email);
}
