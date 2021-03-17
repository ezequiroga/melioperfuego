package com.ml.satelites.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ml.satelites.dtos.CoordenadaDto;
import com.ml.satelites.dtos.SateliteDto;
import com.ml.satelites.entities.SateliteEntity;
import com.ml.satelites.repositories.SatelitesRepository;
import java.math.BigDecimal;
import java.util.HashMap;
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
        if(entity.get().getDistance() != null) dto.setDistance(entity.get().getDistance().doubleValue());
        if (entity.get().getPositionX() != null && entity.get().getPositionY() != null){
            CoordenadaDto coordenada = new CoordenadaDto(entity.get().getPositionX().doubleValue(), entity.get().getPositionY().doubleValue());
            dto.setPosition(coordenada);
        }
        return Optional.of(dto);
    }

    public void registrarSatelite(SateliteDto satelite) {
        Optional<SateliteEntity> entity = satelitesRepository.findByName(satelite.getName());
        SateliteEntity e;
        if (entity.isPresent()) {
            e = entity.get();
            e.setPositionX(BigDecimal.valueOf(satelite.getPosition().x));
            e.setPositionY(BigDecimal.valueOf(satelite.getPosition().y));
            e.setDistance(BigDecimal.valueOf(satelite.getDistance()));
        } else {
            e = new SateliteEntity();
            e.setName(satelite.getName());
            e.setPositionX(BigDecimal.valueOf(satelite.getPosition().x));
            e.setPositionY(BigDecimal.valueOf(satelite.getPosition().y));
            e.setDistance(BigDecimal.valueOf(satelite.getDistance()));
        }
        satelitesRepository.saveAndFlush(e);
    }

    public List<SateliteDto> getAllWorking() {
        Optional<List<SateliteEntity>> satelitesEntity = satelitesRepository.findByWorking('Y');
        List<SateliteDto> satelitesList = new LinkedList<>();
        satelitesEntity.get().stream().forEach(entity -> {
            satelitesList.add(SateliteDto.Builder.fromEntity(entity));
        });
        return satelitesList;
    }

}
