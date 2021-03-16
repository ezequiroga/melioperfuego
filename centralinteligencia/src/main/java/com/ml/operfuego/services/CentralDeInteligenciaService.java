package com.ml.operfuego.services;

import com.ml.operfuego.dtos.CoordenadaDto;
import com.ml.operfuego.dtos.SateliteDto;
import com.ml.operfuego.dtos.SatellitesDto;
import com.ml.operfuego.dtos.TopSecreteDto;
import com.ml.operfuego.exceptions.MSComunicationException;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Servicio que representa la central de inteligencia
 *
 * @author ae_qu
 */
@Service
@Log4j2
public class CentralDeInteligenciaService {

    @Autowired
    private InteligenciaDePosicionesService inteligenciaDePosicionesService;
    @Autowired
    private InteligenciaDeMensajesService inteligenciaDeMensajeService;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${ms.satelites}")
    private String msSatelite;

    public int getCantidadDeSatelites() {
        ResponseEntity<Integer> response = restTemplate.getForEntity(msSatelite.concat("/count"), Integer.class);
        return response.getBody();
    }

    public boolean sateliteRegistrado(String name) throws MSComunicationException {
        boolean registrado;
        try {
            ResponseEntity<SateliteDto> response = restTemplate.getForEntity(msSatelite.concat("/").concat(name), SateliteDto.class);
            registrado = response.getStatusCode().equals(HttpStatus.OK);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                registrado = false;
            } else {
                log.equals("Error en la comunicacion con el MS de satelites. Servicio: ".concat(msSatelite.concat("/").concat(name)));
                throw new MSComunicationException();
            }
        }
        return registrado;
    }

    public void registrarSatelite(SateliteDto satelite) {
        HttpEntity<SateliteDto> entity = new HttpEntity<>(satelite);
        restTemplate.postForEntity(msSatelite.concat("/"), entity, Void.class);
    }

    public Optional<TopSecreteDto> informacionUltraSecreta(SatellitesDto satellitesDto) {
        Optional<CoordenadaDto> coordenadas = inteligenciaDePosicionesService.posisionNaveEnemiga(satellitesDto);
        if (coordenadas.isEmpty()) {
            return Optional.empty();
        }
        String mensaje = inteligenciaDeMensajeService.reconstruirMensaje(satellitesDto);
        return Optional.of(new TopSecreteDto(coordenadas.get(), mensaje));
    }

    public Optional<TopSecreteDto> informacionUltraSecreta() {
        ResponseEntity<List<SateliteDto>> response = restTemplate.exchange(msSatelite.concat("/"), HttpMethod.GET, null, new ParameterizedTypeReference<List<SateliteDto>>() {});
        SatellitesDto satellitesDto = new SatellitesDto();
        satellitesDto.setSatellites(response.getBody());
        return informacionUltraSecreta(satellitesDto);
    }

}
