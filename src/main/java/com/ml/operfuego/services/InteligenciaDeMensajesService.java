package com.ml.operfuego.services;

import com.ml.operfuego.dtos.SatellitesDto;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * Service encargado de la decodificacion del mensaje enemigo
 *
 * @author ae_qu
 */
@Service
public class InteligenciaDeMensajesService {

    public String reconstruirMensaje(SatellitesDto satellitesDto) {

        HashMap<String, Integer> mensaje = new LinkedHashMap<>();

        //Se recorren los mensajes de todos los satelites
        satellitesDto.getSatellites().stream().forEach(unSatelite -> {
            int size = unSatelite.getMessage().length;
            String[] message = unSatelite.getMessage();
            for (int i = 0; i < size; i++) {
                if (!message[i].isBlank()) {                    
                    if (mensaje.containsKey(message[i])) {
                        //En el map queda el indice menor de aparicion en caso
                        //que la misma palabra aparezca en dos mensajes
                        int valorActual = mensaje.get(message[i]);
                        mensaje.replace(message[i], valorActual, Math.min(valorActual, i) );
                    } else {
                        mensaje.put(message[i], i);
                    }
                }
            }
        });
        
        //Comparador por posision en los respectivos mensajes
        Comparator<Map.Entry<String,Integer>> comparator = (Map.Entry<String,Integer> first, Map.Entry<String,Integer> second) -> first.getValue().compareTo(second.getValue());        
        
        //Se ordena la lista y se retorna el mensaje
        return mensaje.entrySet().stream()
                .sorted(comparator)
                .collect(Collectors.toList())
                .stream().map(s -> s.getKey())
                .collect(Collectors.joining (" "));
    }
}
