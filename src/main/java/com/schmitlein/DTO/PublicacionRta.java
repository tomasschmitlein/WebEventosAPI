package com.schmitlein.DTO;

import java.util.List;

public class PublicacionRta {
    
    private List<PublicacionDTO> contenido;
    
    private int numeroDePag;
    
    private int medidadDePagin;
    
    private long totalElementos;
    
    private int totalDePaginas;
    
    private boolean ultima;

    public PublicacionRta() {
    }

    public PublicacionRta(List<PublicacionDTO> contenido, int numeroDePag, int medidadDePagin, long totalElementos, int totalDePaginas, boolean ultima) {
        this.contenido = contenido;
        this.numeroDePag = numeroDePag;
        this.medidadDePagin = medidadDePagin;
        this.totalElementos = totalElementos;
        this.totalDePaginas = totalDePaginas;
        this.ultima = ultima;
    }

    public List<PublicacionDTO> getContenido() {
        return contenido;
    }

    public void setContenido(List<PublicacionDTO> contenido) {
        this.contenido = contenido;
    }

    public int getNumeroDePag() {
        return numeroDePag;
    }

    public void setNumeroDePag(int numeroDePag) {
        this.numeroDePag = numeroDePag;
    }

    public int getMedidadDePagin() {
        return medidadDePagin;
    }

    public void setMedidadDePagin(int medidadDePagin) {
        this.medidadDePagin = medidadDePagin;
    }

    public long getTotalElementos() {
        return totalElementos;
    }

    public void setTotalElementos(long totalElementos) {
        this.totalElementos = totalElementos;
    }

    public int getTotalDePaginas() {
        return totalDePaginas;
    }

    public void setTotalDePaginas(int totalDePaginas) {
        this.totalDePaginas = totalDePaginas;
    }

    public boolean isUltima() {
        return ultima;
    }

    public void setUltima(boolean ultima) {
        this.ultima = ultima;
    }

    @Override
    public String toString() {
        return "PublicacionRta{" + "contenido=" + contenido + ", numeroDePag=" + numeroDePag + ", medidadDePagin=" + medidadDePagin + ", totalElementos=" + totalElementos + ", totalDePaginas=" + totalDePaginas + ", ultima=" + ultima + '}';
    }
    
    
    
}
