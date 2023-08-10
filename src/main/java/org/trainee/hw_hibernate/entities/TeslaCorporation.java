package org.trainee.hw_hibernate.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Tesla")
@Data
@EqualsAndHashCode(callSuper = true)
public class TeslaCorporation extends AbstractCorporation {
    @Column(name = "tesla_product")
    private String teslaProduct;
}
