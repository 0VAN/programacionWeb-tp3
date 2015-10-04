package EJB.Jackson;

/**
 * Created by alex on 04/10/15.
 */
public class Cliente {
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    private String nombre;
    private String cedula;
}
