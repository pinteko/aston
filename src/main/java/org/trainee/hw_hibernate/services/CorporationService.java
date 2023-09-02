package org.trainee.hw_hibernate.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.trainee.hw_hibernate.dtos.CorporationDto;
import org.trainee.hw_hibernate.entities.AbstractCorporation;
import org.trainee.hw_hibernate.entities.AppleCorporation;
import org.trainee.hw_hibernate.entities.MicrosoftCorporation;
import org.trainee.hw_hibernate.entities.TeslaCorporation;
import org.trainee.hw_hibernate.repositories.CorporationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CorporationService {

    private final CorporationRepository corporationRepository;

    public AbstractCorporation getCorporationById(Long id) {
        return corporationRepository.getById(id);
    }

    public AbstractCorporation getCorporationByName(String name) {
        return corporationRepository.getAbstractCorporationByName(name);
    }

    public List<AbstractCorporation> getAllCorporations() {
        return corporationRepository.findAll();
    }

    public void saveCorporation(CorporationDto corporationDto) {
        AbstractCorporation corporation = null;
        String type = corporationDto.getProduct();
        if (type != null) {
            switch (type) {
                case "Microsoft" -> {   corporation = new MicrosoftCorporation();
                    ((MicrosoftCorporation) corporation).setMicrosoftProduct(corporationDto.getProduct());}
                case "Tesla" -> {       corporation = new TeslaCorporation();
                    ((TeslaCorporation) corporation).setTeslaProduct(corporationDto.getProduct());}
                case "Apple" -> {       corporation = new AppleCorporation();
                    ((AppleCorporation) corporation).setAppleProduct(corporationDto.getProduct());}
            }
        } else {
            throw new RuntimeException("wrong types");
        }
        assert corporation != null;
        corporationRepository.save(corporation);
    }

    public void deleteCorporation(Long id) {
        corporationRepository.deleteById(id);
    }
}
