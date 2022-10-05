package com.schmitlein.service;

import com.schmitlein.DTO.PublicacionDTO;
import com.schmitlein.DTO.PublicacionRta;
import java.util.List;
import java.util.Optional;

public interface PublicacionService {
    
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
    
    public PublicacionRta obtenerTodasLasPublicaciones(int numPagina, int cantRegistros, String ordenarPor, String direccOrden);
    
    public PublicacionDTO findById(long id);
    
    public PublicacionDTO eliminarPublicacion(long id);
    
    public PublicacionDTO actualizarPublicacion(PublicacionDTO pubDTO, long id);
}
