package com.govtech.viswa.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author Sarav on 13 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.pojo
 * @class ListStudent
 */
@Data
public class ListStudent {
    List<String> students;

    public ListStudent(List<String> students) {
        this.students = students;
    }
}
