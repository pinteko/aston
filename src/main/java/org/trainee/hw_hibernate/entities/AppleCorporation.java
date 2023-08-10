package org.trainee.hw_hibernate.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Apple")
@Data
@EqualsAndHashCode(callSuper = true)
public class AppleCorporation extends AbstractCorporation {
    @Column(name = "apple_product")
    private String appleProduct;
}
