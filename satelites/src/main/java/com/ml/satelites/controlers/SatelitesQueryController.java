package com.ml.satelites.controlers;

import com.ml.satelites.dtos.SateliteDto;
import com.ml.satelites.services.SatelitesService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ae_qu
 */
@RestController
@RequestMapping()
public class SatelitesQueryController {
    
    @Autowired
    private SatelitesService satelitesService;
    
    @GetMapping(value = "/count")
    public ResponseEntity cantSatelites(){
        return new ResponseEntity(satelitesService.getCantidadDeSatelites(), HttpStatus.OK);
    }
    
    @GetMapping(value = "/{satellite_name}")
    public ResponseEntity retrieve(@PathVariable String satellite_name){
        Optional<SateliteDto> dto = satelitesService.getByName(satellite_name);
        if (dto.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(dto.get(), HttpStatus.OK);
    }
    
    @GetMapping(value = "/")
    public ResponseEntity getAll(){
        return new ResponseEntity(satelitesService.getAllWorking(), HttpStatus.OK);
    }
    
}
