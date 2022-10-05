package com.schmitlein.DTO;

import java.util.Date;

public class ErrorDetalles {

    private Date marcaDeTiempoM;

    private String mensaje;

    private String detalles;

    public ErrorDetalles(Date marcaDeTiempoM, String mensaje, String detalles) {
        this.marcaDeTiempoM = marcaDeTiempoM;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }

    public Date getMarcaDeTiempoM() {
        return marcaDeTiempoM;
    }

    public void setMarcaDeTiempoM(Date marcaDeTiempoM) {
        this.marcaDeTiempoM = marcaDeTiempoM;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

}
