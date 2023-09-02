package org.trainee.hw_hibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.trainee.hw_hibernate.entities.AbstractCorporation;

@Repository
public interface CorporationRepository extends JpaRepository<AbstractCorporation, Long> {

    AbstractCorporation getAbstractCorporationByName(String name);
}
