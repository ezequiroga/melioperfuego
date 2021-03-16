package com.ml.satelites.services;

import com.ml.satelites.dtos.SateliteDto;
import java.util.HashMap;
import org.springframework.stereotype.Service;

/**
 *
 * @author ae_qu
 */
@Service
public class SatelitesService {

    HashMap<String, SateliteDto> satelites = new HashMap<>();
    
    public SateliteDto getByName(String name){
        return satelites.get(name);
    }
    
}
