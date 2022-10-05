package com.schmitlein.repository;

import com.schmitlein.entidades.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long>{
    
}
