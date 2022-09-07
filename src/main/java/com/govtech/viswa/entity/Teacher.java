package com.govtech.viswa.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.relational.core.mapping.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.entity
 * @class Teacher
 */
@Entity
//@Table("Teacher")
@Data
@NoArgsConstructor
@Log4j2
@EqualsAndHashCode(of = {"email"}, callSuper = false)
//@Audited
//@AuditOverride( forClass = Auditable.class )
public class Teacher { //{  //extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    @Column("name")
    private String name;
    @NotNull
    @Column("email")
    private String email;

    public Teacher(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("""
        Teacher { id : %d , name : %s , email : %s }
        """, id, name, email);
    }


}
