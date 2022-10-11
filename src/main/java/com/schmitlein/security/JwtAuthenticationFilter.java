package com.schmitlein.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private JwtTokenProvider jwtToken;
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        //traigo el token de la solicitud http
        String token = obtenerJWTDeLaSolicitud(request);
        
        //valido el token
        if(StringUtils.hasText(token) && jwtToken.validarToken(token)){
            
            //obtenemos el username del token
            String username = jwtToken.obtenerUsernameDelJWT(token);
            
            //cargo el usuario asociado al token
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); 
        
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            
            //establezco la seguridad
           SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        
        filterChain.doFilter(request, response);
    }
    
    //Bearer token de acceso 
    private String obtenerJWTDeLaSolicitud(HttpServletRequest request){
        
        String bearerToken = request.getHeader("Authorization");
        
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){
            return bearerToken.substring(7, bearerToken.length());
        }else{
            return null;
        }
    }
    
}