package com.schmitlein.controlador;

import com.schmitlein.DTO.PublicacionDTO;
import com.schmitlein.DTO.PublicacionRta;
import com.schmitlein.service.PublicacionService;
import com.schmitlein.util.AppConstantes;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    PublicacionService pubService;

    @GetMapping
    @RequestMapping("/listar")
    public PublicacionRta listarPublicaciones(
            @RequestParam(value = "numPag", defaultValue = AppConstantes.NUMERO_DE_PAG_X_DEFECTO , required = false) int nroPag,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAG_X_DEFECTO, required = false) int cantRegistros,
            @RequestParam(value = "ordenarPor", defaultValue = AppConstantes.ORDENAR_X_DEFECTO, required = false) String ordenarPor, 
            @RequestParam(value = "direccOrden", defaultValue = AppConstantes.ORDENAR_DIRECC_X_DEFECTO, required = false) String direccOrden) {

        return pubService.obtenerTodasLasPublicaciones(nroPag, cantRegistros, ordenarPor, direccOrden);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> encontrarPorId(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(pubService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacion(@Valid @RequestBody PublicacionDTO pubDTO, @PathVariable(name = "id") long id) {
        PublicacionDTO pubDTORta = pubService.actualizarPublicacion(pubDTO, id);

        return new ResponseEntity<>(pubDTORta, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<PublicacionDTO> eliminarPublicacion(@PathVariable long id) {
        PublicacionDTO pubDTORta = pubService.eliminarPublicacion(id);

        return new ResponseEntity<>(pubDTORta, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/agregar")
    public ResponseEntity<PublicacionDTO> guardarPublicacion(@Valid @RequestBody PublicacionDTO pubDTO) {

        return new ResponseEntity<>(pubService.crearPublicacion(pubDTO), HttpStatus.CREATED);
    }
}
