package com.schmitlein.service;

import com.schmitlein.DTO.AdminEventosDTO;
import com.schmitlein.entidades.AdministradoresEvento;
import com.schmitlein.entidades.Publicacion;
import com.schmitlein.excepciones.ResourceNotFoundException;
import com.schmitlein.excepciones.WebAppException;
import com.schmitlein.repository.AdministradoresEventosRepository;
import com.schmitlein.repository.PublicacionRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AdministradoresEventoServiceImpl implements AdministradoresEventoService {

    @Autowired
    private AdministradoresEventosRepository adminRepository;

    @Autowired
    private PublicacionRepository pubRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AdminEventosDTO agregarAdmin(long publicacionId, AdminEventosDTO admin) {
        AdministradoresEvento administrador = convertirEntity(admin);

        //Buscamos la publicacion a la que se le va a agregar este admin
        Publicacion publicacion = pubRepository.findById(publicacionId).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "ID", publicacionId));

        //Le asigno al administrador la publicacion
        administrador.setPublicacion(publicacion);

        //Guardamos el nuevo admin con todos sus datos y publicacion asociada;
        AdministradoresEvento nuevoAdmin = adminRepository.save(administrador);

        //Devolvemos el nuevo admin agregado como un DTO
        return convertirADTO(nuevoAdmin);
    }

    @Override
    public AdminEventosDTO eliminarAdmin(long idAdmin) {

        AdministradoresEvento administrador = adminRepository.findById(idAdmin).orElseThrow(() -> new ResourceNotFoundException("Administrador", "ID", idAdmin));

        adminRepository.delete(administrador);

        AdminEventosDTO adminEliminado = convertirADTO(administrador);

        return adminEliminado;
    }

    //nos devuelve la lista de administradores de una determinada publicacion, que buscamos x su id
    @Override
    public List<AdminEventosDTO> obtenerAdminsPorPublicacionId(long publicacionId) {
        List<AdministradoresEvento> administradores = adminRepository.findByPublicacionId(publicacionId);

        return administradores.stream().map(administrador -> convertirADTO(administrador)).collect(Collectors.toList());
    }

    @Override
    public AdminEventosDTO obtenerAdminPorId(long idPub, long id) {

        Publicacion publicacion = pubRepository.findById(idPub).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "ID", idPub));

        AdministradoresEvento administrador = adminRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Administrador", "ID", id));

        //verificamos que el administrador que se busca pertenezca a la publicacion que se paso
        if (!administrador.getPublicacion().getId().equals(publicacion.getId())) {
            throw new WebAppException(HttpStatus.BAD_REQUEST, "El administrador no pertenece a la publicacion que se envio");
        } else {
            return convertirADTO(administrador);
        }

    }

    @Override
    public AdminEventosDTO actualizarAdmin(long publicacionId, Long id, AdminEventosDTO admin) {

        Publicacion publicacion = pubRepository.findById(publicacionId).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "ID", publicacionId));

        AdministradoresEvento administrador = adminRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Administrador", "ID", id));

        if (!administrador.getPublicacion().getId().equals(publicacion.getId())) {
            throw new WebAppException(HttpStatus.BAD_REQUEST, "El administrador no pertenece a la publicacion que se envio");
        }
        administrador.setNombre(admin.getNombre());
        administrador.setEmail(admin.getEmail());
        administrador.setCelular(admin.getCelular());

        AdministradoresEvento adminActualizado = adminRepository.save(administrador);

        return convertirADTO(adminActualizado);
    }

    private AdminEventosDTO convertirADTO(AdministradoresEvento admin) {

        AdminEventosDTO adminDTO = modelMapper.map(admin, AdminEventosDTO.class);
        /*
        AdminEventosDTO adminDTO = new AdminEventosDTO();
        
        adminDTO.setId(admin.getId());
        adminDTO.setNombre(admin.getNombre());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setCelular(admin.getCelular());
         */
        return adminDTO;
    }

    private AdministradoresEvento convertirEntity(AdminEventosDTO admin) {

        AdministradoresEvento adminEntity = modelMapper.map(admin, AdministradoresEvento.class);
        /*AdministradoresEvento adminEntity = new AdministradoresEvento();
        
        adminEntity.setId(admin.getId());
        adminEntity.setNombre(admin.getNombre());
        adminEntity.setEmail(admin.getEmail());
        adminEntity.setCelular(admin.getCelular());
         */

        return adminEntity;
    }

}
