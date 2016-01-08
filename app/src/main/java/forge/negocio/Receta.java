package forge.negocio;

import java.io.Serializable;

/**
 * Clase que se simula el comportamiento de una receta
 */
public class Receta implements Serializable{
    private String nombre;
    private String hora;
    private String minuto;
    private String lapsoDia;
    private String lapsoHora;
    private String lapsoMinuto;
    private String dosis;
    private String dosisCantidad;
    private String notas;

    public Receta() {
    }

    public Receta(String nombre, String hora, String minuto,
                  String lapsoDia, String lapsoHora, String
                          lapsoMinuto, String dosis, String dosisCantidad, String notas) {
        this.nombre = nombre;
        this.hora = hora;
        this.minuto = minuto;
        this.lapsoDia = lapsoDia;
        this.lapsoHora = lapsoHora;
        this.lapsoMinuto = lapsoMinuto;
        this.dosis = dosis;
        this.dosisCantidad = dosisCantidad;
        this.notas = notas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getDosisCantidad() {
        return dosisCantidad;
    }

    public void setDosisCantidad(String dosisCantidad) {
        this.dosisCantidad = dosisCantidad;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getLapsoMinuto() {
        return lapsoMinuto;
    }

    public void setLapsoMinuto(String lapsoMinuto) {
        this.lapsoMinuto = lapsoMinuto;
    }

    public String getLapsoHora() {
        return lapsoHora;
    }

    public void setLapsoHora(String lapsoHora) {
        this.lapsoHora = lapsoHora;
    }

    public String getLapsoDia() {
        return lapsoDia;
    }

    public void setLapsoDia(String lapsoDia) {
        this.lapsoDia = lapsoDia;
    }

    public String getMinuto() {
        return minuto;
    }

    public void setMinuto(String minuto) {
        this.minuto = minuto;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Receta{" +
                "nombre='" + nombre + '\'' +
                ", hora='" + hora + '\'' +
                ", minuto='" + minuto + '\'' +
                ", lapsoDia='" + lapsoDia + '\'' +
                ", lapsoHora='" + lapsoHora + '\'' +
                ", lapsoMinuto='" + lapsoMinuto + '\'' +
                ", dosis='" + dosis + '\'' +
                ", dosisCantidad='" + dosisCantidad + '\'' +
                ", notas='" + notas + '\'' +
                '}';
    }
}
