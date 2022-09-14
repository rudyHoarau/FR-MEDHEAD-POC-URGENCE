package fr.medhead.urgence.controller;

import fr.medhead.urgence.model.Urgence;
import fr.medhead.urgence.service.UrgenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class UrgenceRestController {
    @Autowired
    private UrgenceService urgenceService;

    public UrgenceRestController(UrgenceService urgenceService) {
        this.urgenceService =urgenceService;
    }

    @GetMapping("/urgences")
    Collection<Urgence> tous() {
        return urgenceService.tous();
    }

    @PostMapping("/urgences")
    Urgence nouvelleUrgence(@RequestBody Urgence nouvelleUrgence) {
        return urgenceService.nouvelleUrgence(nouvelleUrgence);
    }
}
