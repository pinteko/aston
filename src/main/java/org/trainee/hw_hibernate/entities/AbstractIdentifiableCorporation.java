package org.trainee.hw_hibernate.entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractIdentifiableCorporation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}
