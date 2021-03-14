package com.ml.operfuego.dtos;

import lombok.Data;

/**
 * DTO que modela cada satelite de la flota.
 *
 * @author Eze Q.
 */
@Data
public class SateliteDto {
    private String name;
    private double  distance;
    private String[] message;
}
