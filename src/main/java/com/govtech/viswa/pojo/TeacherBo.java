package com.govtech.viswa.pojo;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.bo
 * @class TeacherBo
 */
@Data
@Log4j2
public class TeacherBo {
    Long id;
    String name;
    String email;

    public TeacherBo(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
