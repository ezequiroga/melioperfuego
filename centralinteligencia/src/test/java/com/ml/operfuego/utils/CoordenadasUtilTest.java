package com.ml.operfuego.utils;

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
public class CoordenadasUtilTest {
    
    public CoordenadasUtilTest() {
    }

    @Test
    public void testCalcularCoordenadaY() {
        System.out.println("calcularCoordenadaY");
        
        SateliteDto sX = new SateliteDto("sX", Math.sqrt(26), null, new CoordenadaDto(2, 6));
        SateliteDto sY = new SateliteDto("sY", Math.sqrt(25), null, new CoordenadaDto(5, 4));
        SateliteDto sZ = new SateliteDto("sZ", Math.sqrt(40), null, new CoordenadaDto(7, 3));
        
        List<SateliteDto> satelites = new LinkedList<>();
        satelites.add(sX);
        satelites.add(sY);
        satelites.add(sZ);
        
        SatellitesDto satellitesDto = new SatellitesDto();
        satellitesDto.setSatellites(satelites);
        
        double expResult = 1.0;
        Optional<Double> result = CoordenadasUtil.calcularCoordenadaY(satellitesDto);
        assertEquals(expResult, Math.ceil(result.get()));
    }

    @Test
    public void testCalcularCoordenadaX() {
        System.out.println("calcularCoordenadaX");
        
        SateliteDto sX = new SateliteDto("sX", Math.sqrt(26), null, new CoordenadaDto(2, 6));
        SateliteDto sY = new SateliteDto("sY", Math.sqrt(25), null, new CoordenadaDto(5, 4));
        SateliteDto sZ = new SateliteDto("sZ", Math.sqrt(40), null, new CoordenadaDto(7, 3));
        
        List<SateliteDto> satelites = new LinkedList<>();
        satelites.add(sX);
        satelites.add(sY);
        satelites.add(sZ);
        
        SatellitesDto satellitesDto = new SatellitesDto();
        satellitesDto.setSatellites(satelites);
        Optional<Double> coordY = Optional.of(1.0);
        double expResult = 1.0;
        Optional<Double> result = CoordenadasUtil.calcularCoordenadaX(satellitesDto, coordY);
        assertEquals(expResult, Math.ceil(result.get()));
    }
    
}
