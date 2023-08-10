package org.trainee.hw_jdbc.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private List<Group> groups = new ArrayList<>();
}
