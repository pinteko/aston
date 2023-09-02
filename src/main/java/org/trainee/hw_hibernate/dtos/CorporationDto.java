package org.trainee.hw_hibernate.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CorporationDto {

    private String name;
    private String type;
    private String product;
}
