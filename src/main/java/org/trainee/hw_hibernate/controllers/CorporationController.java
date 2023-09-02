package org.trainee.hw_hibernate.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.trainee.hw_hibernate.dtos.CorporationDto;
import org.trainee.hw_hibernate.entities.*;
import org.trainee.hw_hibernate.services.CorporationService;

import java.util.List;

@RestController
@RequestMapping("/corporations")
@RequiredArgsConstructor
public class CorporationController {

    private final CorporationService corporationService;

    @GetMapping
    public List<AbstractCorporation> getAll() {return corporationService.getAllCorporations();}

    @GetMapping("{/id}")
    public AbstractCorporation getById(@PathVariable("id") Long id) {
            return corporationService.getCorporationById(id);
    }

    @GetMapping()
    public AbstractCorporation getByName(@RequestParam String name) {
        return corporationService.getCorporationByName(name);
    }
    @PostMapping
    public void save(@RequestBody CorporationDto corporationDto) {
        corporationService.saveCorporation(corporationDto);
    }

    @DeleteMapping("{/id}")
    public void delete(@PathVariable("id") Long id) {
        corporationService.deleteCorporation(id);
    }
}
