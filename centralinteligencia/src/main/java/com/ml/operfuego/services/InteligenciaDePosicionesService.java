package com.ml.operfuego.services;

import com.ml.operfuego.dtos.CoordenadaDto;
import com.ml.operfuego.dtos.SatellitesDto;
import com.ml.operfuego.utils.CoordenadasUtil;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Service encargado de terminar las posiciones de las naves enemigas.
 *
 * @author ae_qu
 */
@Service
public class InteligenciaDePosicionesService {
    public Optional<CoordenadaDto> posisionNaveEnemiga(SatellitesDto satellitesDto){
        
        Optional<Double> coordY = CoordenadasUtil.calcularCoordenadaY(satellitesDto);
        Optional<Double> coordX = CoordenadasUtil.calcularCoordenadaX(satellitesDto, coordY);
        
        if(coordX.isEmpty() || coordY.isEmpty()){
            return Optional.empty();
        }
        
        return Optional.of(new CoordenadaDto(coordX.get(), coordY.get()));
    }
}
