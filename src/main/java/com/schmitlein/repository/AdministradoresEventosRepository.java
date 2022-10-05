package com.schmitlein.repository;

import com.schmitlein.entidades.AdministradoresEvento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradoresEventosRepository extends JpaRepository<AdministradoresEvento, Long>{
    
    public List<AdministradoresEvento> findByPublicacionId(long id);
    
}
