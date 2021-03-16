package com.ml.satelites.controlers;

import com.ml.satelites.dtos.SateliteDto;
import com.ml.satelites.services.SatelitesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ae_qu
 */
@RestController
@RequestMapping()
public class SatelitesCommandController {
    
    @Autowired
    private SatelitesService satelitesService;
    
    @PostMapping(value = "/")
    private ResponseEntity retrieve(@RequestBody SateliteDto sateliteDto){
        satelitesService.registrarSatelite(sateliteDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
}
