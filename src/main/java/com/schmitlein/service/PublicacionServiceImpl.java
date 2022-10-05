package com.schmitlein.service;

import com.schmitlein.DTO.PublicacionDTO;
import com.schmitlein.DTO.PublicacionRta;
import com.schmitlein.entidades.Publicacion;
import com.schmitlein.excepciones.ResourceNotFoundException;
import com.schmitlein.repository.PublicacionRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PublicacionServiceImpl implements PublicacionService {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private PublicacionRepository pubRepository;

    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        //Convertimos de DTO a una entidad
        Publicacion pub = convertirEntidad(publicacionDTO);

        //guardamos la nueva publicacion;
        Publicacion nuevaPub = pubRepository.save(pub);

        //Convertimos de entidad a DTO
        PublicacionDTO pubResponse = new PublicacionDTO();

        //devolvemos la respuesta
        return pubResponse = convertirDTO(nuevaPub);
    }

    @Override
    public PublicacionRta obtenerTodasLasPublicaciones(int numPagina, int cantRegistros, String ordenadoPor, String direccOrden) {
        //estos metodos son propios de springboot, para poder utilizar la paginacion
        
        //Es una condicion que se fija 1ero si el orden que le pasamos es ASC, en ese caso lo ordena ASC y ordenado x el campo que recibimos, si la direccOrden que le pasamos no era ASC lo va a ordenar DESC con el campo que recibio.
        // condicion ? true_hace_esto : falso_hace_esto
        Sort sort = direccOrden.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenadoPor).ascending():Sort.by(ordenadoPor).descending();
        
        Pageable pageable = PageRequest.of(numPagina, cantRegistros, sort);
        
        //esta listando con la cantPagina que le pasamos y la cantRegistros
        Page<Publicacion> publicaciones = pubRepository.findAll(pageable);
        
        //obtenemos el contenido de la lista de publicaciones que viene con la cantidad de paginas que le pasamos, y la cantidad de registros
        List<Publicacion> Listapublicaciones = publicaciones.getContent();
        
        //mapeamos la lista de publicaciones y las convertimos en una lista de dto
        List<PublicacionDTO> contenido = Listapublicaciones.stream().map(publicacion -> convertirDTO(publicacion)).collect(Collectors.toList());
        
        //este publicacion respuesta va a tener como contenido la lista de publicacionesDTO, mas las caracteristicas sobre el tamaño de lo que trae
        PublicacionRta pubResponse = new PublicacionRta();
        
        pubResponse.setContenido(contenido);
        pubResponse.setNumeroDePag(publicaciones.getNumber());
        pubResponse.setMedidadDePagin(publicaciones.getSize());
        pubResponse.setTotalElementos(publicaciones.getTotalElements());
        pubResponse.setTotalDePaginas(publicaciones.getTotalPages());
        pubResponse.setUltima(publicaciones.isLast());
        
        return pubResponse;
        
    }

    @Override
    public PublicacionDTO eliminarPublicacion(long id) {
        Publicacion publicacion = pubRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "ID", id));
        pubRepository.delete(publicacion);

        PublicacionDTO pubDTO = convertirDTO(publicacion);
        return pubDTO;
    }

    @Override
    public PublicacionDTO findById(long id) {
        Publicacion publicacion = pubRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "ID", id));
        return convertirDTO(publicacion);
    }

    @Override
    public PublicacionDTO actualizarPublicacion(PublicacionDTO pubDTO, long id) {
        Publicacion publicacion = pubRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "ID", id));

        publicacion.setTitulo(pubDTO.getTitulo());
        publicacion.setDescripcion(pubDTO.getDescripcion());
        publicacion.setContenido(pubDTO.getContenido());
        
        Publicacion pubActualizada = pubRepository.save(publicacion);

        return convertirDTO(pubActualizada);
    }

    protected Publicacion convertirEntidad(PublicacionDTO pubDTO) {
        
        Publicacion publicacion = modelMapper.map(pubDTO, Publicacion.class);
        
        /*
        vamos a utilizar para optimizar esto la libreria/dependency ModelMapper
        Publicacion publicacion = new Publicacion();

        publicacion.setTitulo(pubDTO.getTitulo());
        publicacion.setDescripcion(pubDTO.getDescripcion());
        publicacion.setContenido(pubDTO.getContenido());
        */
        return publicacion;
    }

    protected PublicacionDTO convertirDTO(Publicacion pub) {

        //con esta simple linea de codigo ya mapeamos de Entidad a DTO
        PublicacionDTO pubDTO = modelMapper.map(pub, PublicacionDTO.class);
        
        /*
        vamos a utilizar para optimizar esto, la libreria ModelMapper
        PublicacionDTO pubDTO = new PublicacionDTO();

        pubDTO.setId(pub.getId());
        pubDTO.setTitulo(pub.getTitulo());
        pubDTO.setDescripcion(pub.getDescripcion());
        pubDTO.setContenido(pub.getContenido());*/

        return pubDTO;
    }

}
