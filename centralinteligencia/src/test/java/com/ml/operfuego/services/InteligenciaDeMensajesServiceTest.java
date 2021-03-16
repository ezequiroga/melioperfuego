package com.ml.operfuego.services;

import com.ml.operfuego.dtos.CoordenadaDto;
import com.ml.operfuego.dtos.SateliteDto;
import com.ml.operfuego.dtos.SatellitesDto;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ae_qu
 */
public class InteligenciaDeMensajesServiceTest {
    
    public InteligenciaDeMensajesServiceTest() {
    }

    @Test
    public void testReconstruirMensaje() {
        System.out.println("reconstruirMensaje");
        
        SateliteDto sX = new SateliteDto("sX", Math.sqrt(26), new String[]{"este", "", "", "mensaje", ""}, new CoordenadaDto(2, 6));
        SateliteDto sY = new SateliteDto("sY", Math.sqrt(25), new String[]{"", "es", "", "", "secreto"}, new CoordenadaDto(5, 4));
        SateliteDto sZ = new SateliteDto("sZ", Math.sqrt(40), new String[]{"este", "", "un", "", ""}, new CoordenadaDto(7, 3));
        
        List<SateliteDto> satelites = new LinkedList<>();
        satelites.add(sX);
        satelites.add(sY);
        satelites.add(sZ);
        
        SatellitesDto satellitesDto = new SatellitesDto();
        satellitesDto.setSatellites(satelites);
        
        InteligenciaDeMensajesService instance = new InteligenciaDeMensajesService();
        
        String expResult = "este es un mensaje secreto";
        
        String result = instance.reconstruirMensaje(satellitesDto);
        
        assertEquals(expResult, result);
    }
    
}
