package org.trainee.hw_hibernate.dtos;

import lombok.*;
import org.trainee.hw_hibernate.entities.Group;

import java.util.List;

@Data
@NoArgsConstructor
public class StudentDto {

    private Long id;

    private String name;

    private Integer age;

    private List<Group> groups;
}
