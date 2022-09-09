package fr.medhead.urgence.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class Urgence {

    @Id @GeneratedValue
    private long id;
    private long patientId;
    private String specialiteSouhaite;
    private String adresseOrigine;
    private long hopitalDestinationId;
    private String nomHopitalDestination;
    private long reservationId;

    public Urgence() {
    }

    public Urgence(long patientId, String adresseOrigine) {
        this.patientId = patientId;
        this.adresseOrigine = adresseOrigine;
    }

    public Urgence(long patientId, String adresseOrigine, long hopitalDestinationId, String nomHopitalDestination, long reservationId) {
        this.patientId = patientId;
        this.adresseOrigine = adresseOrigine;
        this.hopitalDestinationId = hopitalDestinationId;
        this.nomHopitalDestination = nomHopitalDestination;
        this.reservationId = reservationId;
    }
}
