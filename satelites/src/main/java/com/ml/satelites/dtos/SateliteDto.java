package com.ml.satelites.dtos;

import com.ml.satelites.entities.SateliteEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que modela cada satelite de la flota.
 *
 * @author Eze Q.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SateliteDto {

    private String name;
    private double distance;
    private String[] message;
    private CoordenadaDto position;

    public static class Builder {

        public static SateliteDto fromEntity(SateliteEntity entity) {
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
            return s;
        }
    }
}
