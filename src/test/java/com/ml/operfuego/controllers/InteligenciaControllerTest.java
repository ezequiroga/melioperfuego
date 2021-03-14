package com.ml.operfuego.controllers;

import com.ml.operfuego.dtos.SateliteDto;
import com.ml.operfuego.dtos.SatellitesDto;
import com.ml.operfuego.dtos.TopSecreteDto;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author ae_qu
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class InteligenciaControllerTest {
    
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    private String baseApiUri;
    
    public InteligenciaControllerTest() {
    }
    
    @BeforeEach
    private void setUp(){
        StringBuilder  uriAux = new StringBuilder("http://localhost:");
        uriAux.append(String.valueOf(port)).append("/api");
        baseApiUri = uriAux.toString();
    }

    @Test
    public void testTopSecrete() {
        System.out.println("topSecrete");
        String service = baseApiUri.concat("/topsecrete/");
        
        String[] m1 = new String[]{"este","","","mensaje",""};
        SateliteDto s1 = new SateliteDto("kenobi", Double.parseDouble("100.0"),m1);
        
        String[] m2 = new String[]{"","es","","","secreto"};
        SateliteDto s2 = new SateliteDto("skywalker", Double.parseDouble("115.5"),m2);
        
        String[] m3 = new String[]{"este","","un","",""};
        SateliteDto s3 = new SateliteDto("sato", Double.parseDouble("142.7"),m3);
        
        List<SateliteDto> lSatelites = new LinkedList<>();
        lSatelites.add(s1);
        lSatelites.add(s2);
        lSatelites.add(s3);        
        
        SatellitesDto satellitesDto = new SatellitesDto();
        satellitesDto.setSatellites(lSatelites);
        
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        HttpEntity entity = new HttpEntity(satellitesDto,headers);
        
        ResponseEntity<TopSecreteDto> responseEntity = restTemplate.postForEntity(service, entity, TopSecreteDto.class);
        
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

    }
    
}
