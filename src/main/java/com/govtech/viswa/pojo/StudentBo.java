package com.govtech.viswa.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;

/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.bo
 * @class StudentBo
 */
@Data
@Log4j2
@EqualsAndHashCode(of = {"email"})
public class StudentBo {
    Long id;
    String name;
    String email;

    public StudentBo(String name, String email) {
        this.name = name;
        this.email = email;
    }


}
