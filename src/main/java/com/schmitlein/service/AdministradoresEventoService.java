package com.schmitlein.service;

import com.schmitlein.DTO.AdminEventosDTO;
import java.util.List;

public interface AdministradoresEventoService {
 
    public AdminEventosDTO agregarAdmin(long publicacionId, AdminEventosDTO admin);
    
    public AdminEventosDTO eliminarAdmin(long idAdmin);
    
    public List<AdminEventosDTO> obtenerAdminsPorPublicacionId(long publicacionId);
    
    public AdminEventosDTO obtenerAdminPorId(long publicacionId, long id);
    
    public AdminEventosDTO actualizarAdmin(long publicacionId, Long idAdmin, AdminEventosDTO admin);
}
