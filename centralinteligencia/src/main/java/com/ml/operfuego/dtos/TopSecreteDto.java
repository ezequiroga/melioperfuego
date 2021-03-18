package com.ml.operfuego.dtos;

import java.awt.Point;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que modela la respuesta ultra secreta con la posicion y el mensaje
 * enemigo.
 *
 * @author ae_qu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopSecreteDto {
    private CoordenadaDto position;
    private String message;
}
