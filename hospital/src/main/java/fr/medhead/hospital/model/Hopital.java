package fr.medhead.hospital.model;

import fr.medhead.hospital.repertoire.HopitalRepository;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Hopital {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String nomHopital;
    private int litsDisponibles;

    @ManyToMany()
    @JoinTable(name = "hopital_specialite"
            , joinColumns = @JoinColumn(name = "hopital_id")
            , inverseJoinColumns = @JoinColumn(name = "specialite_id"))
    private List<Specialite> specialites;

    public Hopital() {}

    public Hopital(String nomHopital, int litsDisponibles, List<Specialite> specialites) {
        this.nomHopital = nomHopital;
        this.litsDisponibles = litsDisponibles;
        this.specialites = specialites;
    }
}
