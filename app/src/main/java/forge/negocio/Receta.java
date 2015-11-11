package forge.negocio;

/**
 * Created by Nacho on 11/11/2015.
 */
public class Receta {
    private String nombre;
    private String hora;
    private String lapso;
    private String dosis;

    public Receta() {
    }

    public Receta(String nombre, String hora, String lapso, String dosis) {
        this.nombre = nombre;
        this.hora = hora;
        this.lapso = lapso;
        this.dosis = dosis;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLapso() {
        return lapso;
    }

    public void setLapso(String lapso) {
        this.lapso = lapso;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    @Override
    public String toString() {
        return "Receta{" +
                "nombre='" + nombre + '\'' +
                ", hora='" + hora + '\'' +
                ", lapso='" + lapso + '\'' +
                ", dosis='" + dosis + '\'' +
                '}';
    }
}
