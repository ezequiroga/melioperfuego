package com.ml.satelites.dtos;

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
    private double  distance;
    private String[] message;
    private CoordenadaDto position;
}
