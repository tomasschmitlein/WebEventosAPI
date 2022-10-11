package com.schmitlein.security;

//En esta clase vamos a generar el token, validarlo, obtener los clains, etc

import com.schmitlein.excepciones.WebAppException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
 
    //obtenemos el valor de un propiedad JWT, de nuestro .propiertie
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    
    @Value("${app.jwt-expiration-milliseconds}")
    private String jwtExpirationMs;
    
    public String generarToken(Authentication auth){
        
        String username = auth.getName();
        Date fechaActual = new Date();
        Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationMs);
        
        String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(fechaExpiracion)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
        
        return token;
    }
    
    
    public String obtenerUsernameDelJWT(String token){
        
        //los claims son los datos del token
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        
        return claims.getSubject();
    }
    
    public boolean validarToken(String token){
        
        try {
            
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            
            return true;
            
        } catch (SignatureException e) {
            throw new WebAppException(HttpStatus.BAD_REQUEST, "Firma JWT no valida");
        }catch (MalformedJwtException e) {
            throw new WebAppException(HttpStatus.BAD_REQUEST, "Token JWT no valido");
        }catch (ExpiredJwtException e) {
            throw new WebAppException(HttpStatus.BAD_REQUEST, "Token JWT vencido");
        }catch (UnsupportedJwtException e) {
            throw new WebAppException(HttpStatus.BAD_REQUEST, "Token JWT no compatible");
        }catch (IllegalArgumentException e) {
            throw new WebAppException(HttpStatus.BAD_REQUEST, "La cadena claims JWT esta vacia");
        }
        
    }
}
