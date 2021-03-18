package com.ml.operfuego.services;

import com.ml.operfuego.dtos.CoordenadaDto;
import com.ml.operfuego.dtos.SateliteDto;
import com.ml.operfuego.dtos.SatellitesDto;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ae_qu
 */
public class InteligenciaDePosicionesServiceTest {
    
    public InteligenciaDePosicionesServiceTest() {
    }

    @Test
    public void testPosisionNaveEnemiga() {
        System.out.println("posisionNaveEnemiga");
        
        SateliteDto sX = new SateliteDto("sX", Math.sqrt(26), null, new CoordenadaDto(2, 6));
        SateliteDto sY = new SateliteDto("sY", Math.sqrt(25), null, new CoordenadaDto(5, 4));
        SateliteDto sZ = new SateliteDto("sZ", Math.sqrt(40), null, new CoordenadaDto(7, 3));
        
        List<SateliteDto> satelites = new LinkedList<>();
        satelites.add(sX);
        satelites.add(sY);
        satelites.add(sZ);
        
        SatellitesDto satellitesDto = new SatellitesDto();
        satellitesDto.setSatellites(satelites);
        
        InteligenciaDePosicionesService instance = new InteligenciaDePosicionesService();
        
        CoordenadaDto expResult = new CoordenadaDto(1.0, 1.0);
        Optional<CoordenadaDto> resultAux = instance.posisionNaveEnemiga(satellitesDto);
        CoordenadaDto result = new CoordenadaDto(Math.ceil(resultAux.get().x), Math.ceil(resultAux.get().y));
        
        assertEquals(expResult, result);
    }
    
}
