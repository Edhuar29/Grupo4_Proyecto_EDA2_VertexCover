package model;

import java.util.ArrayList;
import java.util.List;

public class ResultadoSolucion {
    private boolean tieneSolucion;
    private List<Integer> certificado;
    private long tiempoNano;

    public ResultadoSolucion() {
        this.tieneSolucion = false;
        this.certificado = new ArrayList<>();
        this.tiempoNano = 0;
    }

    public ResultadoSolucion(boolean tieneSolucion, List<Integer> certificado, long tiempoNano) {
        this.tieneSolucion = tieneSolucion;
        this.certificado = certificado;
        this.tiempoNano = tiempoNano;
    }

    public boolean isTieneSolucion() {
        return tieneSolucion;
    }

    public void setTieneSolucion(boolean tieneSolucion) {
        this.tieneSolucion = tieneSolucion;
    }

    public List<Integer> getCertificado() {
        return certificado;
    }

    public void setCertificado(List<Integer> certificado) {
        this.certificado = certificado;
    }

    public long getTiempoNano() {
        return tiempoNano;
    }

    public void setTiempoNano(long tiempoNano) {
        this.tiempoNano = tiempoNano;
    }
}
