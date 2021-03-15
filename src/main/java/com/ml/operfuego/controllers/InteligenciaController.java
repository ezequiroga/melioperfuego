package com.ml.operfuego.controllers;

import com.ml.operfuego.dtos.CoordenadaDto;
import com.ml.operfuego.dtos.SatellitesDto;
import com.ml.operfuego.dtos.TopSecreteDto;
import com.ml.operfuego.services.InteligenciaDeMensajesService;
import com.ml.operfuego.services.InteligenciaDePosicionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller encargado de recibir la informacion de los satelites y determinar
 * la informacion del enemigo.
 *
 * @author ae_qu
 */
@RestController
@RequestMapping("/topsecrete")
public class InteligenciaController {
    
    @Autowired
    private InteligenciaDePosicionesService inteligenciaDePosicionesService;
    @Autowired
    private InteligenciaDeMensajesService inteligenciaDeMensajeService;
    
    @PostMapping(value = "/")
    public ResponseEntity<TopSecreteDto> topSecrete(@RequestBody SatellitesDto satellitesDto){
        
        CoordenadaDto coordenadas = inteligenciaDePosicionesService.posisionNaveEnemiga(satellitesDto);
        String mensaje = inteligenciaDeMensajeService.reconstruirMensaje(satellitesDto);
        
        return new ResponseEntity<>(new TopSecreteDto(coordenadas, mensaje), HttpStatus.OK);
    }
    
}
