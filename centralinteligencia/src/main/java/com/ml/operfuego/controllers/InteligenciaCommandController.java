package com.ml.operfuego.controllers;

import com.ml.operfuego.dtos.SateliteDto;
import com.ml.operfuego.dtos.SatellitesDto;
import com.ml.operfuego.dtos.TopSecreteDto;
import com.ml.operfuego.services.CentralDeInteligenciaService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping()
public class InteligenciaCommandController {

    @Autowired
    private CentralDeInteligenciaService centralDeInteligenciaService;

    /**
     * Servicio que retorna la ubicación de la nave y el mensaje que emite a
     * partir de la ubicacion de los satelites y los mensajes que cada uno de
     * ellos recibio.
     *
     * @param satellitesDto Lista con la informacion de los satelites en
     * funcionamiento.
     *
     * @return La posicion y el mensaje de la nave que emitio el mensaje.
     * @see TopSecreteDto
     */
    @PostMapping(value = "/topsecrete/")
    public ResponseEntity topSecrete(@RequestBody SatellitesDto satellitesDto) {
        
        Optional<TopSecreteDto> tsDto = centralDeInteligenciaService.informacionUltraSecreta(satellitesDto);
        if(tsDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tsDto, HttpStatus.OK);
    }

    /**
     * Servicio que registra un satelite y la informacion enviada por el mismo.
     *
     * @param satellite_name Nombre del satelite que envia el mensaje
     * @param sateliteDto Informacion del satelite
     * @return La posicion y el mensaje de la nave que emitio el mensaje, o
     * mensaje de error.
     */
    @PostMapping(value = "/topsecret_split/{satellite_name}")
    public ResponseEntity topSecreteSplitGet(@PathVariable String satellite_name, @RequestBody SateliteDto sateliteDto) {
        sateliteDto.setName(satellite_name);
        centralDeInteligenciaService.registrarSatelite(sateliteDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
