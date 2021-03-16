package com.ml.operfuego.dtos;

import java.util.List;
import lombok.Data;

/**
 * Lista de satelites en operaciones.
 *
 * @author ae_qu
 */
@Data
public class SatellitesDto {
    private List<SateliteDto> satellites;
}
