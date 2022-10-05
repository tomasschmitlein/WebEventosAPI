package com.schmitlein.repository;

import com.schmitlein.entidades.Rol;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long>{
    
    public Optional<Rol> findByNombre(String nombre);
    
}
