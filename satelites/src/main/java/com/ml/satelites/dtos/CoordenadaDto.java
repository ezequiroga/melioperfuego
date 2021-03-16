package com.ml.satelites.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model un punto en el plano del universo.
 *
 * @author ae_qu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordenadaDto {
    public double x;
    public double y;
}
