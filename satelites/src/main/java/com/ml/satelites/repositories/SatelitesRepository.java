package com.ml.satelites.repositories;

import com.ml.satelites.entities.SateliteEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ae_qu
 */
public interface SatelitesRepository extends JpaRepository<SateliteEntity, Integer>{
    
    public Optional<SateliteEntity> findByName(String name);
    public Optional<List<SateliteEntity>> findByWorking(char working);
    
}
