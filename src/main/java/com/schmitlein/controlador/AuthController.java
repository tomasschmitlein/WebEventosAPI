package com.schmitlein.controlador;

import com.schmitlein.DTO.JWTAuthResponseDTO;
import com.schmitlein.DTO.LoginDTO;
import com.schmitlein.DTO.RegistroDTO;
import com.schmitlein.entidades.Rol;
import com.schmitlein.entidades.Usuario;
import com.schmitlein.repository.RolRepository;
import com.schmitlein.repository.UsuarioRepository;
import com.schmitlein.security.JwtTokenProvider;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UsuarioRepository userRepository;
    
    @Autowired
    private RolRepository rolRepo;
    
    @Autowired
    private PasswordEncoder passEncoder;
    
    @Autowired
    private JwtTokenProvider jwtToken;
    
    
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO){
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(auth);
        
        //obtenemos el token del jwtTokenProvider
        String token = jwtToken.generarToken(auth);
        
        return ResponseEntity.ok(new JWTAuthResponseDTO(token));
    }
    
    @PostMapping("/registar")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO){
        
        if(userRepository.existsByUsername(registroDTO.getUsername())){
            return new ResponseEntity<>("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
        }else if(userRepository.existsByEmail(registroDTO.getEmail())){
            return new ResponseEntity<>("Ese email ya existe registrado en nuesta base de datos", HttpStatus.BAD_REQUEST);
        }else{
            
            Usuario usuario = new Usuario();
            
            usuario.setNombre(registroDTO.getNombre());
            usuario.setUsername(registroDTO.getUsername());
            usuario.setEmail(registroDTO.getEmail());
            usuario.setPassword(passEncoder.encode(registroDTO.getPassword()));
            
            Rol roles = rolRepo.findByNombre("ROLE_ADMIN").get();
            
            usuario.setRoles(Collections.singleton(roles));
            
            userRepository.save(usuario);
        }
        
        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK);
    }
}