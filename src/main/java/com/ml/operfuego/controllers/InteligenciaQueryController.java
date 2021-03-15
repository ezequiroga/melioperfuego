package com.ml.operfuego.controllers;

import com.ml.operfuego.dtos.TopSecreteDto;
import com.ml.operfuego.services.CentralDeInteligenciaService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class InteligenciaQueryController {

    @Autowired
    private CentralDeInteligenciaService centralDeInteligenciaService;

    /**
     * Servicio que retorna la ubicación de la nave enemiga y el mensaje que
     * emite, a partir de la informacion enviada por un satelite, siempre que
     * sea posible.
     *
     * @param satellite_name Nombre del satelite que envia el mensaje
     * @param sateliteDto Informacion del satelite
     * @return La posicion y el mensaje de la nave que emitio el mensaje, o
     * mensaje de error.
     */
    @GetMapping(value = "/topsecret_split/{satellite_name}")
    public ResponseEntity topSecreteSplitPost(@PathVariable String satellite_name) {
        if (!centralDeInteligenciaService.sateliteRegistrado(satellite_name)) {
            return new ResponseEntity("Satelite desconocido!", HttpStatus.NOT_FOUND);
        }
        if (centralDeInteligenciaService.getCantidadDeSatelites() < 3) {
            return new ResponseEntity("No hay suficiente informacion para retornar la ubicacion!", HttpStatus.NO_CONTENT);
        }
        
        Optional<TopSecreteDto> tsDto = centralDeInteligenciaService.informacionUltraSecreta();
        if (tsDto.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity(tsDto.get(), HttpStatus.OK);
    }

}
