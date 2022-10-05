package com.schmitlein.repository;

import com.schmitlein.entidades.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    //optional si encuentra devuelve true y el usuario, y sino false
    
    public Optional<Usuario> findByEmail(String email);
    
    public Optional<Usuario> findByUsernameOrEmail(String username, String email);
    
    public Optional<Usuario> findByUsername(String username);
    
    public Boolean existsByUsername(String username);
    
    public Boolean existsByEmail(String email);
}
