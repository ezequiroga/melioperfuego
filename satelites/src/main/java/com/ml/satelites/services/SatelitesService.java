package com.ml.satelites.services;

import com.ml.satelites.dtos.CoordenadaDto;
import com.ml.satelites.dtos.SateliteDto;
import com.ml.satelites.entities.SateliteEntity;
import com.ml.satelites.repositories.SatelitesRepository;
import com.ml.satelites.utils.Utils;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ae_qu
 */
@Service
public class SatelitesService {

    @Autowired
    private SatelitesRepository satelitesRepository;
    private final String SEPARADOR = "--";

    public int getCantidadDeSatelites() {
        Optional<List<SateliteEntity>> satelitesEntity = satelitesRepository.findByWorking('Y');
        return satelitesEntity.isEmpty() ? 0 : satelitesEntity.get().size();
    }

    public Optional<SateliteDto> getByName(String name) {
        Optional<SateliteEntity> entity = satelitesRepository.findByName(name);
        if (entity.isEmpty()) {
            return Optional.empty();
        }
        SateliteDto dto = new SateliteDto();
        dto.setName(entity.get().getName());
        if (entity.get().getDistance() != null) {
            dto.setDistance(entity.get().getDistance().doubleValue());
        }
        if (entity.get().getPositionX() != null && entity.get().getPositionY() != null) {
            CoordenadaDto coordenada = new CoordenadaDto(entity.get().getPositionX().doubleValue(), entity.get().getPositionY().doubleValue());
            dto.setPosition(coordenada);
        }
        if(entity.get().getMessage() != null){
            dto.setMessage(Utils.StringToArray(entity.get().getMessage(), SEPARADOR));
        }

        return Optional.of(dto);
    }

    public void registrarSatelite(SateliteDto satelite) {
        Optional<SateliteEntity> entity = satelitesRepository.findByName(satelite.getName());
        SateliteEntity e;
        if (entity.isPresent()) {
            e = entity.get();
        } else {
            e = new SateliteEntity();
            e.setName(satelite.getName());
        }
        if(satelite.getMessage() != null){
            e.setMessage(Utils.ArrayToString(satelite.getMessage(), SEPARADOR));
        }
        if (satelite.getPosition() != null) {
            e.setPositionX(BigDecimal.valueOf(satelite.getPosition().x));
            e.setPositionY(BigDecimal.valueOf(satelite.getPosition().y));
        }
        e.setDistance(BigDecimal.valueOf(satelite.getDistance()));
        satelitesRepository.saveAndFlush(e);
    }

    public List<SateliteDto> getAllWorking() {
        Optional<List<SateliteEntity>> satelitesEntity = satelitesRepository.findByWorking('Y');
        List<SateliteDto> satelitesList = new LinkedList<>();
        satelitesEntity.get().stream().forEach(entity -> {
            SateliteDto s = new SateliteDto();
            s.setName(entity.getName());
            if (entity.getDistance() != null) {
                s.setDistance(entity.getDistance().doubleValue());
            }
            if (entity.getPositionX() != null && entity.getPositionY() != null) {
                CoordenadaDto c = new CoordenadaDto(
                        entity.getPositionX().doubleValue(),
                        entity.getPositionY().doubleValue());
                s.setPosition(c);
            }
            if (entity.getMessage() != null) {
                s.setMessage(Utils.StringToArray(entity.getMessage(), SEPARADOR));
            }
            satelitesList.add(s);
        });
        return satelitesList;
    }

}
