package com.ml.operfuego.services;

import com.ml.operfuego.dtos.CoordenadaDto;
import com.ml.operfuego.dtos.SateliteDto;
import com.ml.operfuego.dtos.SatellitesDto;
import com.ml.operfuego.dtos.TopSecreteDto;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio que representa la central de inteligencia
 *
 * @author ae_qu
 */
@Service
public class CentralDeInteligenciaService {

    @Autowired
    private InteligenciaDePosicionesService inteligenciaDePosicionesService;
    @Autowired
    private InteligenciaDeMensajesService inteligenciaDeMensajeService;

    HashMap<String, SateliteDto> satelites = new HashMap<>();

    public int getCantidadDeSatelites() {
        return satelites.size();
    }
    
    public boolean sateliteRegistrado(String name){
        return satelites.containsKey(name);
    }

    public void registrarSatelite(SateliteDto satelite) {
        if (!satelites.containsKey(satelite.getName())) {
            satelites.put(satelite.getName(), satelite);
        } else {
            satelites.replace(satelite.getName(), satelite);
        }
    }

    public TopSecreteDto informacionUltraSecreta(SatellitesDto satellitesDto) {
        CoordenadaDto coordenadas = inteligenciaDePosicionesService.posisionNaveEnemiga(satellitesDto);
        String mensaje = inteligenciaDeMensajeService.reconstruirMensaje(satellitesDto);
        return new TopSecreteDto(coordenadas, mensaje);
    }

    public TopSecreteDto informacionUltraSecreta() {
        SatellitesDto satellitesDto = new SatellitesDto();
        List<SateliteDto> satelitesList = new LinkedList<>();
        for (Map.Entry<String, SateliteDto> entry : satelites.entrySet()) {
            satelitesList.add(entry.getValue());
        }
        satellitesDto.setSatellites(satelitesList);
        return informacionUltraSecreta(satellitesDto);
    }

}
