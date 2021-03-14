package com.ml.operfuego.controllers;

import com.ml.operfuego.dtos.SatellitesDto;
import com.ml.operfuego.dtos.TopSecreteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller encargado de recibir la informacion de los satelites y determinar
 * la informacion del enemigo.
 *
 * @author ae_qu
 */
@Controller
@RequestMapping("/topsecrete")
public class InteligenciaController {
    
    @PostMapping
    private ResponseEntity<TopSecreteDto> topSecrete(@RequestBody SatellitesDto satellitesDto){
        
        
        
        
        return null;
    }
    
}
