package fr.medhead.urgence;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.medhead.urgence.consommationrest.ConsumingHopitalRest;
import fr.medhead.urgence.consommationrest.Hopital;
import fr.medhead.urgence.consommationrest.Specialite;
import fr.medhead.urgence.controller.UrgenceRestController;
import fr.medhead.urgence.model.Urgence;
import fr.medhead.urgence.service.UrgenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(UrgenceRestController.class)
@ExtendWith(MockitoExtension.class)
class UrgenceApplicationTests {

    @Autowired
    WebTestClient webTestClient;

    @Mock
    private ConsumingHopitalRest consumingHopitalRest;

    @MockBean
    private UrgenceService urgenceService;

    private Urgence urgenceDemande;

    private Urgence urgenceAttendue;

    private Hopital mockHopitalAttendu;

    private JacksonTester<Urgence> jsonUrgence;

    //    @SneakyThrows
    @BeforeEach
    void setup() {

        List<Specialite> specialiteList = new ArrayList<>();
        specialiteList.add(new Specialite("medecine generale","cardiologie"));
        specialiteList.add(new Specialite("pathologie", "immunologie"));
        mockHopitalAttendu = new Hopital("Fred",2,2,2, specialiteList);
                mockHopitalAttendu.setId(1);

        urgenceDemande = new Urgence(1234,"cardiologie",5,5);
        urgenceAttendue = new Urgence(1234,"cardiologie",5,5,1,"Fred",0);

        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void givenCreeUrgence_whenEnvoieUneUrgence_thenEnregistreUneUrgence() throws Exception {

        Mockito.when(urgenceService.nouvelleUrgence(urgenceDemande)).thenReturn(urgenceAttendue);

        webTestClient.post().uri("/urgences")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(jsonUrgence.write(urgenceDemande).getJson())
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.patientId").isEqualTo(0)
                .jsonPath("$.specialiteSouhaite").isEqualTo("cardiologie")
                .jsonPath("$.gpsOrigineX").isEqualTo(5)
                .jsonPath("$.gpsOrigineY").isEqualTo(5)
                .jsonPath("$.hopitalDestinationId").isEqualTo(1)
                .jsonPath("$.nomHopitalDestination").isEqualTo("Fred")
                .jsonPath("$.reservationId").isEqualTo(0);

        Mockito.verify(urgenceService, Mockito.times(1)).nouvelleUrgence(urgenceDemande);

    }
}
