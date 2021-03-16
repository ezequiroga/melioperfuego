package com.ml.operfuego.services;

import com.ml.operfuego.dtos.CoordenadaDto;
import com.ml.operfuego.dtos.SateliteDto;
import com.ml.operfuego.dtos.SatellitesDto;
import com.ml.operfuego.dtos.TopSecreteDto;
import com.ml.operfuego.exceptions.MSComunicationException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ae_qu
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CentralDeInteligenciaServiceTest {
    
    @Autowired
    private CentralDeInteligenciaService centralDeInteligenciaService;
    
    @MockBean
    private RestTemplate restTemplate;
    
    
    public CentralDeInteligenciaServiceTest() {
    }

    @Test
    public void testGetCantidadDeSatelites() {
        System.out.println("getCantidadDeSatelites");
        int expResult = 2;
        
        ResponseEntity<Integer> response = new ResponseEntity(2, HttpStatus.OK);
        
        when(restTemplate.getForEntity(any(String.class), any(Class.class))).thenReturn(response);
        
        int result = centralDeInteligenciaService.getCantidadDeSatelites();
        assertEquals(expResult, result);
    }

    @Test
    public void testSateliteRegistrado() throws Exception {
        System.out.println("sateliteRegistrado - true");
        String name = "satelite1";
        boolean expResult = true;
        
        SateliteDto s1 = new SateliteDto(name, 100, new String[]{"m1","m2"}, new CoordenadaDto(7, 5));
        
        ResponseEntity<SateliteDto> response = new ResponseEntity(s1, HttpStatus.OK);
        
        when(restTemplate.getForEntity(any(String.class), any(Class.class))).thenReturn(response);
        
        boolean result = centralDeInteligenciaService.sateliteRegistrado(name);
        assertEquals(expResult, result);
    }

    @Test
    public void testSateliteRegistradoFalse() throws Exception {
        System.out.println("sateliteRegistrado - false");
        String name = "satelite1";
        boolean expResult = false;
        
        SateliteDto s1 = new SateliteDto(name, 100, new String[]{"m1","m2"}, new CoordenadaDto(7, 5));
        
        ResponseEntity<SateliteDto> response = new ResponseEntity(null, HttpStatus.NOT_FOUND);
        
        when(restTemplate.getForEntity(any(String.class), any(Class.class))).thenReturn(response);
        
        boolean result = centralDeInteligenciaService.sateliteRegistrado(name);
        assertEquals(expResult, result);
    }

    @Test
    public void testSateliteRegistradoException() {
        System.out.println("sateliteRegistrado - exception");
        String name = "satelite1";
        
        HttpClientErrorException ex = new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        
        when(restTemplate.getForEntity(any(String.class), any(Class.class))).thenThrow(ex);
        
        assertThrows(MSComunicationException.class, () -> {centralDeInteligenciaService.sateliteRegistrado(name);});
    }

    //DUMMY
    //TODO - probar posibles errores de comunicacion
    @Test
    public void testRegistrarSatelite() {
        System.out.println("registrarSatelite");
        when(restTemplate.postForEntity(any(String.class), any(SateliteDto.class), any(Class.class))).thenReturn(null);
        SateliteDto satelite = new SateliteDto("kenobi", Math.sqrt(26), new String[]{"este", "", "", "mensaje", ""}, new CoordenadaDto(2, 6));
        centralDeInteligenciaService.registrarSatelite(satelite);
    }

    @Test
    public void testInformacionUltraSecreta_SatellitesDto() {
        System.out.println("informacionUltraSecreta");
        
        SateliteDto sX = new SateliteDto("sX", Math.sqrt(26), new String[]{"este", "", "", "mensaje", ""}, new CoordenadaDto(2, 6));
        SateliteDto sY = new SateliteDto("sY", Math.sqrt(25), new String[]{"", "es", "", "", "secreto"}, new CoordenadaDto(5, 4));
        SateliteDto sZ = new SateliteDto("sZ", Math.sqrt(40), new String[]{"este", "", "un", "", ""}, new CoordenadaDto(7, 3));
        
        List<SateliteDto> satelites = new LinkedList<>();
        satelites.add(sX);
        satelites.add(sY);
        satelites.add(sZ);
        
        SatellitesDto satellitesDto = new SatellitesDto();
        satellitesDto.setSatellites(satelites);
        
        TopSecreteDto tsDto = new TopSecreteDto(new CoordenadaDto(1.0, 1.0), "este es un mensaje secreto");
        Optional<TopSecreteDto> expResult = Optional.of(tsDto);
        Optional<TopSecreteDto> result = centralDeInteligenciaService.informacionUltraSecreta(satellitesDto);
        CoordenadaDto c = new CoordenadaDto(Math.ceil(result.get().getPosition().x), Math.ceil(result.get().getPosition().y));
        result.get().setPosition(c);
        assertEquals(expResult, result);
    }

    //TODO - probar posibles errores de comunicacion
    @Test
    public void testInformacionUltraSecreta_0args() {
        System.out.println("informacionUltraSecreta");
        
        SateliteDto sX = new SateliteDto("sX", Math.sqrt(26), new String[]{"este", "", "", "mensaje", ""}, new CoordenadaDto(2, 6));
        SateliteDto sY = new SateliteDto("sY", Math.sqrt(25), new String[]{"", "es", "", "", "secreto"}, new CoordenadaDto(5, 4));
        SateliteDto sZ = new SateliteDto("sZ", Math.sqrt(40), new String[]{"este", "", "un", "", ""}, new CoordenadaDto(7, 3));
        
        List<SateliteDto> satelites = new LinkedList<>();
        satelites.add(sX);
        satelites.add(sY);
        satelites.add(sZ);
        
        ResponseEntity<List<SateliteDto>> response = new ResponseEntity(satelites, HttpStatus.OK);
        
        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), isNull(), any(ParameterizedTypeReference.class))).thenReturn(response);
                
        TopSecreteDto tsDto = new TopSecreteDto(new CoordenadaDto(1.0, 1.0), "este es un mensaje secreto");
        Optional<TopSecreteDto> expResult = Optional.of(tsDto);
        
        Optional<TopSecreteDto> result = centralDeInteligenciaService.informacionUltraSecreta();
        CoordenadaDto c = new CoordenadaDto(Math.ceil(result.get().getPosition().x), Math.ceil(result.get().getPosition().y));
        result.get().setPosition(c);
        
        assertEquals(expResult, result);
    }
    
}
