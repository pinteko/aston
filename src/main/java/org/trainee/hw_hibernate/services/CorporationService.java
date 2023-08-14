package org.trainee.hw_hibernate.services;

import org.hibernate.SessionFactory;
import org.trainee.hw_hibernate.entities.AbstractCorporation;
import org.trainee.hw_hibernate.repositories.CorporationRepository;
import org.trainee.hw_hibernate.repositories.CorporationRepositoryImpl;

import java.util.List;

public class CorporationService {

    private final CorporationRepository corporationRepository;

    public CorporationService(SessionFactory sessionFactory) {
        this.corporationRepository = new CorporationRepositoryImpl(sessionFactory);
    }

    public AbstractCorporation getCorporationById(Long id) {
        return corporationRepository.getCorporationById(id);
    }

    public AbstractCorporation getCorporationByName(String name) {
        return corporationRepository.getCorporationByName(name);
    }

    public List<AbstractCorporation> getAllCorporations() {
        return corporationRepository.getAllCorporations();
    }

    public void saveCorporation(AbstractCorporation corporation) {
        corporationRepository.saveCorporation(corporation);
    }

    public void deleteCorporation(Long id) {
        corporationRepository.deleteCorporation(id);
    }
}
