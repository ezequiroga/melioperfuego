package com.ml.operfuego.services;

import com.ml.operfuego.dtos.CoordenadaDto;
import com.ml.operfuego.dtos.SatellitesDto;
import com.ml.operfuego.utils.CoordenadasUtil;
import java.awt.Point;
import org.springframework.stereotype.Service;

/**
 * Service encargado de terminar las posiciones de las naves enemigas.
 *
 * @author ae_qu
 */
@Service
public class InteligenciaDePosicionesService {
    public CoordenadaDto posisionNaveEnemiga(SatellitesDto satellitesDto){
        
        double coordY = CoordenadasUtil.calcularCoordenadaY(satellitesDto);
        double coordX = CoordenadasUtil.calcularCoordenadaX(satellitesDto, coordY);
        
        return new CoordenadaDto(coordX, coordY);
    }
}
