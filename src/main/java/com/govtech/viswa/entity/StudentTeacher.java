package com.govtech.viswa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;

/**
 * @author Sarav on 12 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.entity
 * @class StudentTeacher
 */

@Entity
@Table ("studentteacher")
@Data
//@Audited
//@AuditOverride( forClass = Auditable.class )
@IdClass(IdStudentTeacher.class)
@NoArgsConstructor
@EqualsAndHashCode(of = {"studentId","teacherId"}, callSuper = false)
//@EqualsAndHashCode(of = {"idStudentTeacher"}, callSuper = false)
public class StudentTeacher {  // extends Auditable<String>  {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id;
    @Id
    @Column("studentid")
    Long studentId;

    @Id
    @Column("teacherid")
    Long teacherId;

//    @Id
//    IdStudentTeacher idStudentTeacher;

    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
//    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    Student student;

    @ManyToOne
    @JoinColumn(name = "teacherId", referencedColumnName = "id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
//    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    Teacher teacher;

    public StudentTeacher(IdStudentTeacher idStudentTeacher) {
//        this.idStudentTeacher = idStudentTeacher;
        this.studentId = idStudentTeacher.getStudentId();
        this.teacherId = idStudentTeacher.getTeacherId();
    }

    public StudentTeacher(Student student, Teacher teacher) {
//        this.idStudentTeacher = idStudentTeacher;
        this.studentId = student.getId();
        this.teacherId = teacher.getId();
    }

    public StudentTeacher(Long studentId, Long teacherId) {
        this.studentId = studentId;
        this.teacherId = teacherId;
    }
}
