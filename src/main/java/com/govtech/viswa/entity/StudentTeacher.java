package com.govtech.viswa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * @author Sarav on 12 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.entity
 * @class StudentTeacher
 */

@Entity
@Data
@Table(name="studentteacher")
@Audited
@AuditOverride( forClass = Auditable.class )
@IdClass(IdStudentTeacher.class)
@NoArgsConstructor

public class StudentTeacher extends Auditable<String>  {

    @Id
    Long studentId;

    @Id
    Long teacherId;

    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    Student student;

    @ManyToOne
    @JoinColumn(name = "teacherId", referencedColumnName = "id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    Teacher teacher;

    public StudentTeacher(IdStudentTeacher idStudentTeacher) {
        this.studentId = idStudentTeacher.getStudentId();
        this.teacherId = idStudentTeacher.getTeacherId();
    }
}
