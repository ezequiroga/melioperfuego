package com.ml.satelites.services;

import com.ml.satelites.dtos.CoordenadaDto;
import com.ml.satelites.dtos.SateliteDto;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.internal.Bytes.instance;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author ae_qu
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class SatelitesServiceTest {
    
    @Autowired
    private SatelitesService satelitesService;
    
    public SatelitesServiceTest() {
    }

    @Test
    public void testGetCantidadDeSatelites() {
        System.out.println("getCantidadDeSatelites");
        int expResult = 3;
        int result = satelitesService.getCantidadDeSatelites();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetByName() {
        System.out.println("getByName");
        String name = "kenobi";
                
        Optional<SateliteDto> result = satelitesService.getByName(name);
        assertEquals(-500.0, result.get().getPosition().x);
        assertEquals(-200.0, result.get().getPosition().y);
    }

    @Test
    public void testRegistrarSatelite() {
        System.out.println("registrarSatelite");   
        String name = "sX";
        SateliteDto satelite = new SateliteDto(name, Math.sqrt(26), new String[]{"este", "", "", "mensaje", ""}, new CoordenadaDto(2, 6));
        satelitesService.registrarSatelite(satelite);
        
        Optional<SateliteDto> result = satelitesService.getByName(name);
        assertTrue(result.isPresent());
    }

    @Test
    public void testRegistrarSateliteUpdate() {
        System.out.println("registrarSatelite");   
        String name = "skywalker";
        SateliteDto satelite = new SateliteDto(name, Math.sqrt(26), new String[]{"este", "", "", "mensaje", ""}, new CoordenadaDto(20, 60));
        satelitesService.registrarSatelite(satelite);
        
        Optional<SateliteDto> result = satelitesService.getByName(name);
        assertTrue(result.isPresent());
        assertEquals(20.0, result.get().getPosition().x);
        assertEquals(60.0, result.get().getPosition().y);
    }

    @Test
    public void testGetAll() {
        System.out.println("getAll");
        List<SateliteDto> result = satelitesService.getAllWorking();
        assertTrue(result.size() >= 3);
    }
    
}
