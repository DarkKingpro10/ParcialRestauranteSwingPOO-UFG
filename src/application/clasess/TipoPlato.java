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

    //Constructor Parametrizado
    public TipoPlato(int idTipoPlato, String nombreTipoPlato) {
        this.idTipoPlato = idTipoPlato;
        this.nombreTipoPlato = nombreTipoPlato;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.nombreTipoPlato);
        return hash;
    }
    
    /**
     * Método para saber si dos objetos de tipoPlato son igual
     * @param obj que representa el objeto a ser evaluado
     * @return {boolean} que representa si el objeto es igual o no es igual
     */
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
        return Objects.equals(this.nombreTipoPlato, other.nombreTipoPlato);
    }
}
