package com.ml.operfuego.services;

import com.ml.operfuego.dtos.SateliteDto;
import java.util.HashMap;
import org.springframework.stereotype.Service;

/**
 * Servicio que representa la central de inteligencia
 *
 * @author ae_qu
 */
@Service
public class CentralDeInteligenciaService {
    
    HashMap<String, SateliteDto> satelites = new HashMap<>();
    
    public int getCantidadDeSatelites(){
        return satelites.size();
    }
    
    public void registrarSatelite(SateliteDto satelite){
        satelites.put(satelite.getName(), satelite);
    }
    
}
