package com.ml.satelites.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author ae_qu
 */
@Entity
@Table(schema = "MELI", name = "SATELITES")
@Data
public class SateliteEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DISTANCE")
    private BigDecimal distance;
    @Column(name = "POSITION_X")
    private BigDecimal positionX;
    @Column(name = "POSITION_Y")
    private BigDecimal positionY;
    @Column(name = "WORKING")
    private char working;
}
