package org.trainee.hw_jdbc.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class Group {
    private Integer id;
    private String title;
    private List<Student> students = new ArrayList<>();
}
