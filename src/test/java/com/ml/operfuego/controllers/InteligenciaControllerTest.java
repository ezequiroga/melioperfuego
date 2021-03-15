package com.ml.operfuego.controllers;

import com.ml.operfuego.dtos.CoordenadaDto;
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
        String mensajeEnviado = "este es un mensaje secreto";
        CoordenadaDto naveEnemiga = new CoordenadaDto(1.0, 1.0);
        
        
        SateliteDto sX = new SateliteDto("kenobi", Math.sqrt(26), new String[]{"este", "", "", "mensaje", ""}, new CoordenadaDto(2, 6));
        SateliteDto sY = new SateliteDto("skywalker", Math.sqrt(25), new String[]{"", "es", "", "", "secreto"}, new CoordenadaDto(5, 4));
        SateliteDto sZ = new SateliteDto("sato", Math.sqrt(40), new String[]{"este", "", "un", "", ""}, new CoordenadaDto(7, 3));
        
        List<SateliteDto> satelites = new LinkedList<>();
        satelites.add(sX);
        satelites.add(sY);
        satelites.add(sZ);
        
        SatellitesDto satellitesDto = new SatellitesDto();
        satellitesDto.setSatellites(satelites);
        
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        HttpEntity entity = new HttpEntity(satellitesDto,headers);
        
        ResponseEntity<TopSecreteDto> responseEntity = restTemplate.postForEntity(service, entity, TopSecreteDto.class);
        
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(naveEnemiga.x, Math.ceil(responseEntity.getBody().getPosition().x));
        assertEquals(naveEnemiga.y, Math.ceil(responseEntity.getBody().getPosition().y));
        assertEquals(mensajeEnviado, responseEntity.getBody().getMessage());

    }
    
}
