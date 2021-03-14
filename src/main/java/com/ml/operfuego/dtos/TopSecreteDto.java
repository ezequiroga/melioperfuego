package com.ml.operfuego.dtos;

import java.awt.Point;
import lombok.Data;

/**
 * DTO que modela la respuesta ultra secreta con la posicion y el mensaje
 * enemigo.
 *
 * @author ae_qu
 */
@Data
public class TopSecreteDto {
    private Point position;
    private String message;
}
