package application.clasess;

import java.util.Objects;

/**
 *
 * @author Jesús Gerardo Esquivel Ramírez
 */
public class TipoPlato {
    //Atributos de clas
    private int idTipoPlato;
    private String nombreTipoPlato;
    private boolean disponibilidad;

    //Constructor Parametrizado
    public TipoPlato(int idTipoPlato, String nombreTipoPlato, boolean disponible) {
        this.idTipoPlato = idTipoPlato;
        this.nombreTipoPlato = nombreTipoPlato;
        this.disponibilidad = disponible;
    }

    /*GETTERS y SETTERS*/
    public int getIdTipoPlato() {
        return idTipoPlato;
    }

    public void setIdTipoPlato(int idTipoPlato) {
        this.idTipoPlato = idTipoPlato;
    }

    public String getNombreTipoPlato() {
        return nombreTipoPlato;
    }

    public void setNombreTipoPlato(String nombreTipoPlato) {
        this.nombreTipoPlato = nombreTipoPlato;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.nombreTipoPlato);
        hash = 67 * hash + (this.disponibilidad ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoPlato other = (TipoPlato) obj;
        if (this.disponibilidad != other.disponibilidad) {
            return false;
        }
        return Objects.equals(this.nombreTipoPlato, other.nombreTipoPlato);
    }
}
