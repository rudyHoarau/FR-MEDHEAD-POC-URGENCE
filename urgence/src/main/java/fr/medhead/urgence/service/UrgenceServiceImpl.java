package fr.medhead.urgence.service;

import fr.medhead.urgence.consommationrest.ConsumingHopitalRest;
import fr.medhead.urgence.consommationrest.Hopital;
import fr.medhead.urgence.model.Urgence;
import fr.medhead.urgence.repertoire.UrgenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UrgenceServiceImpl implements UrgenceService {

    @Autowired
    private final UrgenceRepository urgenceRepository;
    @Autowired
    private final ConsumingHopitalRest hopitalRest;

    public UrgenceServiceImpl(UrgenceRepository urgenceRepository, ConsumingHopitalRest hopitalRest) {
        this.urgenceRepository = urgenceRepository;
        this.hopitalRest = hopitalRest;
    }

    @Override
    public Collection<Urgence> tous() {
        return urgenceRepository.findAll();
    }

    @Override
    public Urgence nouvelleUrgence(Urgence nouvelleUrgence) {


        Hopital hopitalDestination = this.hopitalRest.trouverUnHopitalProcheParSpecialite(nouvelleUrgence.getSpecialiteSouhaite());
        nouvelleUrgence.setHopitalDestinationId(hopitalDestination.getId());
        nouvelleUrgence.setNomHopitalDestination(hopitalDestination.getNomHopital());

        // 2- reserver un lit pour le patient à transférer
        nouvelleUrgence.setReservationId(0);

        // 3 - sauvegarder l'urgence
        return this.urgenceRepository.save(nouvelleUrgence);
    }
}
