package org.trainee.hw_hibernate.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Microsoft")
@Data
@EqualsAndHashCode(callSuper = true)
public class MicrosoftCorporation extends AbstractCorporation {
    @Column(name = "microsoft_product")
    private String microsoftProduct;
}
