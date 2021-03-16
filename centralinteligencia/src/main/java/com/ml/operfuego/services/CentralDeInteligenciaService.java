package com.ml.operfuego.services;

import com.ml.operfuego.dtos.CoordenadaDto;
import com.ml.operfuego.dtos.SateliteDto;
import com.ml.operfuego.dtos.SatellitesDto;
import com.ml.operfuego.dtos.TopSecreteDto;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    
    @Value("${ms.satelites}")
    private String msSatelite;

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

    public Optional<TopSecreteDto> informacionUltraSecreta(SatellitesDto satellitesDto) {
        Optional<CoordenadaDto> coordenadas = inteligenciaDePosicionesService.posisionNaveEnemiga(satellitesDto);
        if (coordenadas.isEmpty()) {
            return Optional.empty();
        }
        String mensaje = inteligenciaDeMensajeService.reconstruirMensaje(satellitesDto);
        return Optional.of(new TopSecreteDto(coordenadas.get(), mensaje));
    }

    public Optional<TopSecreteDto> informacionUltraSecreta() {
        SatellitesDto satellitesDto = new SatellitesDto();
        List<SateliteDto> satelitesList = new LinkedList<>();
        satelites.entrySet().forEach(entry -> {
            satelitesList.add(entry.getValue());
        });
        satellitesDto.setSatellites(satelitesList);
        return informacionUltraSecreta(satellitesDto);
    }

}
