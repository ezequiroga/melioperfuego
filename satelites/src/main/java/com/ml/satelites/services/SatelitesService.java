package com.ml.satelites.services;

import com.ml.satelites.dtos.SateliteDto;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author ae_qu
 */
@Service
public class SatelitesService {

    HashMap<String, SateliteDto> satelites = new HashMap<>();

    public int getCantidadDeSatelites() {
        return satelites.size();
    }
    
    public SateliteDto getByName(String name){
        return satelites.get(name);
    }

    public void registrarSatelite(SateliteDto satelite) {
        if (!satelites.containsKey(satelite.getName())) {
            satelites.put(satelite.getName(), satelite);
        } else {
            satelites.replace(satelite.getName(), satelite);
        }
    }
    
    public List<SateliteDto> getAll(){
        List<SateliteDto> satelitesList = new LinkedList<>();
        satelites.entrySet().forEach(entry -> {
            satelitesList.add(entry.getValue());
        });
        return satelitesList;
    }
    
}
