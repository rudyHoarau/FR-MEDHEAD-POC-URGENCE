package fr.medhead.urgence.consommationrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
@RestController
public class ConsumingHopitalRest {

    @Autowired
    private final RestTemplate restTemplate;

    public ConsumingHopitalRest(RestTemplate restTemplate) {
        this.restTemplate = new RestTemplate();
    }

    public Hopital trouverUnHopitalProcheParSpecialite(String specialiteSouhaite){

        String url = "http://localhost:8081/hopitaux/%1";

//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity <String> entity = new HttpEntity<String>(headers);
        url = url.replace("%1",specialiteSouhaite);
        return restTemplate
                .getForObject(url, Hopital.class);
    }
}
