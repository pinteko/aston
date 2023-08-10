package org.trainee.hw_hibernate.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "abstract_identifiable_corporation")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "corporation_type", discriminatorType = DiscriminatorType.STRING)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractCorporation extends AbstractIdentifiableCorporation {
    @Column(name = "name")
    private String name;
}
