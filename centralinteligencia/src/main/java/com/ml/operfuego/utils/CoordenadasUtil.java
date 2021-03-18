package com.ml.operfuego.utils;

import com.ml.operfuego.dtos.SateliteDto;
import com.ml.operfuego.dtos.SatellitesDto;
import java.util.Optional;

/**
 * Utiliza para calcular las coordenas de la nave enemiga, a partir de las
 * formulas de distancias enre puntos y sitemas de ecuaciones lineales.
 *
 * @author ae_qu
 */
public class CoordenadasUtil {
    /**
     * Calcula la coordenada Y de la nave enemiga a partir de la posicion de los
     * tres satelites.
     * 
     * @param satellitesDto
     * @return la coordenada Y de la posicion de la nave enemiga
     */
    public static Optional<Double> calcularCoordenadaY(SatellitesDto satellitesDto){
        SateliteDto sX = satellitesDto.getSatellites().get(0);
        SateliteDto sY = satellitesDto.getSatellites().get(1);
        SateliteDto sZ = satellitesDto.getSatellites().get(2);
        
        double A = sY.getPosition().x - sZ.getPosition().x;
        double B = cuadrado(sZ.getPosition().x) 
                + cuadrado(sZ.getPosition().y) 
                - cuadrado(sY.getPosition().x) 
                - cuadrado(sY.getPosition().y)
                - cuadrado(sZ.getDistance())
                + cuadrado(sY.getDistance());
        double C = (-2)*(sY.getPosition().y - sZ.getPosition().y);
        double D = (-2)*(sY.getPosition().x - sX.getPosition().x);
        double F = 2*A*(
                cuadrado(sX.getPosition().x) 
                + cuadrado(sX.getPosition().y) 
                - cuadrado(sY.getPosition().x) 
                - cuadrado(sY.getPosition().y)
                - cuadrado(sX.getDistance())
                + cuadrado(sY.getDistance())
                );
        double G = sY.getPosition().y - sX.getPosition().y;
        
        if (D == 0){//Hace cero el denominador de las diviciones
            return Optional.empty();
        }
        
        if ((4*A*G)/D == C){//Hace cero el denominador de la divicion
            return Optional.empty();
        }
        
        double coordX = (-(F/D) - B) / ((4*A*G)/D - C);
        
        return Optional.of(coordX);
    }
    
    /**
     * Calcula la coordena X de la nave enemiga a partir de la coordena Y.
     * 
     * @param satellitesDto
     * @param coordY calculada previamente.
     * 
     * @return la coordenada X de la posicion de la nave enemiga.
     */
    public static Optional<Double> calcularCoordenadaX(SatellitesDto satellitesDto, Optional<Double> coordY){
        if(coordY.isEmpty()) return Optional.empty();
        
        SateliteDto sX = satellitesDto.getSatellites().get(0);
        SateliteDto sY = satellitesDto.getSatellites().get(1);
        SateliteDto sZ = satellitesDto.getSatellites().get(2);
        double A = sY.getPosition().y - sX.getPosition().y;
        double B = cuadrado(sX.getPosition().x) 
                + cuadrado(sX.getPosition().y) 
                - cuadrado(sY.getPosition().x) 
                - cuadrado(sY.getPosition().y)
                - cuadrado(sX.getDistance())
                + cuadrado(sY.getDistance());
        double C = (-2)*(sY.getPosition().x - sX.getPosition().x);
        
        if (C == 0){//Hace cero el denominador de la divicion
            return Optional.empty();
        }
        
        return Optional.of((2*coordY.get()*A + B) / C);
    }
    
    private static double cuadrado(int number){
        return Math.pow(number, 2);
    }
    
    private static double cuadrado(double number){
        return Math.pow(number, 2);
    }
}
