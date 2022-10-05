package com.schmitlein.controlador;

import com.schmitlein.DTO.AdminEventosDTO;
import com.schmitlein.service.AdministradoresEventoService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/adminsitradoresE/")
public class AdministradoresEventosController {

    @Autowired
    AdministradoresEventoService adminService;

    @GetMapping("/listar/{idPub}")
    public List<AdminEventosDTO> listarAdministradoresPorPublicacionId(@PathVariable(name = "idPub") long idPub) {
        return adminService.obtenerAdminsPorPublicacionId(idPub);
    }

    @GetMapping("/publicacion/{idPub}/admin/{idAdm}")
    public ResponseEntity<AdminEventosDTO> obtenerAdministradorPorId(@PathVariable(name = "idPub") long idPub, @PathVariable(name = "idAdm") long idAdm) {

        return new ResponseEntity<>(adminService.obtenerAdminPorId(idPub,idAdm), HttpStatus.OK);
    }

    
    //el @valid siempre siempre tiene que estar al lado del @requestbody, sino, no funciona
    @PreAuthorize("hasRole(ADMIN)")
    @PostMapping("/agregar/{idPub}")
    public ResponseEntity<AdminEventosDTO> agregarAdmin(@PathVariable(name = "idPub") long idPub, @Valid @RequestBody AdminEventosDTO admin) {
        return new ResponseEntity<>(adminService.agregarAdmin(idPub, admin), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole(ADMIN)")
    @DeleteMapping("/eliminar/{idAdmin}")
    public ResponseEntity<AdminEventosDTO> eliminarAdmin(@PathVariable (name = "idAdmin") long idAdmin){
        return new ResponseEntity<>(adminService.eliminarAdmin(idAdmin), HttpStatus.OK);
    }

    @PreAuthorize("hasRole(ADMIN)")
    @PutMapping("/actualizarAdmin/publicacion/{idPub}/admin/{idAdmin}")
    public ResponseEntity<AdminEventosDTO> actualizarAdmin(@PathVariable (name = "idPub") Long idPub, @PathVariable (name = "idAdmin") Long idAdmin, @Valid @RequestBody AdminEventosDTO admin){
        return new ResponseEntity<>(adminService.actualizarAdmin(idPub, idAdmin, admin), HttpStatus.OK);
    }
    
}
