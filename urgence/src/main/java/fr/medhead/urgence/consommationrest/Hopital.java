package fr.medhead.urgence.consommationrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hopital {
    private long id;
    private String nomHopital;
    private int litsDisponibles;
    private List<Specialite> specialites;

    public Hopital() {}
}
