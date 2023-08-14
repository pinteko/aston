package org.trainee.hw_hibernate.repositories;

import org.trainee.hw_hibernate.entities.AbstractCorporation;

import java.util.List;

public interface CorporationRepository {

    AbstractCorporation getCorporationById(Long id);

    AbstractCorporation getCorporationByName(String name);

    List<AbstractCorporation> getAllCorporations();

    void saveCorporation(AbstractCorporation corporation);

    void deleteCorporation(Long id);
}
