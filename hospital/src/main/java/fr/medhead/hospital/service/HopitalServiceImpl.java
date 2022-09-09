package fr.medhead.hospital.service;

import fr.medhead.hospital.exceptions.SpecialiteNotFoundException;
import fr.medhead.hospital.model.Hopital;
import fr.medhead.hospital.model.Specialite;
import fr.medhead.hospital.repertoire.HopitalRepository;
import fr.medhead.hospital.repertoire.SpecialiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class HopitalServiceImpl implements HopitalService{
    @Autowired
    private final HopitalRepository hopitalRepository;

    @Autowired
    private final SpecialiteRepository specialiteRepository;

    public HopitalServiceImpl(HopitalRepository hopitalRepository, SpecialiteRepository specialiteRepository) {
        this.hopitalRepository = hopitalRepository;
        this.specialiteRepository = specialiteRepository;
    }

    @Override
    public Collection<Hopital> tous() {
        return hopitalRepository.findAll();
    }

    @Override
    public Hopital trouverUnHopitalProcheParSpecialite(String specialiteSouhaite) throws SpecialiteNotFoundException {
        // Collecter les hopitaux destinations qui possède la spécialité souhaité
        Hopital hRetour;

        try {
            hRetour = hopitalRepository.findAll()
                    .stream()
                    .filter(h -> h.getSpecialites()
                            .contains(specialiteRepository.findByNom(specialiteSouhaite))) // spécialité souhaité
                    .filter(h -> h.getLitsDisponibles()>0) // lits disponibles
                    .findFirst()
                    .get();
        } catch (java.util.NoSuchElementException e) {
            throw new SpecialiteNotFoundException(specialiteSouhaite);
        }

        return hRetour;
    }
}
