package application.clasess;

import java.util.Objects;

/**
 *
 * @author Jesus Esquivel
 */
public class Promocion {
    //Atributos
    private int idPromocion;
    private String nombrePromocion;
    private String descripcion;
    private Plato plato;
    private int porcentajeDescuento; //Es entero porque no hay descuentos del 15.5%
    private int platosMinimos;
    
    //Constructor parametrizado

    public Promocion(int idPromocion, String nombrePromocion, String descripcion, Plato plato, int porcentajeDescuento, int platosMinimos) {
        this.idPromocion = idPromocion;
        this.nombrePromocion = nombrePromocion;
        this.descripcion = descripcion;
        this.plato = plato;
        this.porcentajeDescuento = porcentajeDescuento;
        this.platosMinimos = platosMinimos;
    }

    //GETTERS y SETTERS
    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getNombrePromocion() {
        return nombrePromocion;
    }

    public void setNombrePromocion(String nombrePromocion) {
        this.nombrePromocion = nombrePromocion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    public int getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(int porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public int getPlatosMinimos() {
        return platosMinimos;
    }

    public void setPlatosMinimos(int platosMinimos) {
        this.platosMinimos = platosMinimos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.plato);
        hash = 29 * hash + this.porcentajeDescuento;
        hash = 29 * hash + this.platosMinimos;
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
        final Promocion other = (Promocion) obj;
        if (this.porcentajeDescuento != other.porcentajeDescuento) {
            return false;
        }
        if (this.platosMinimos != other.platosMinimos) {
            return false;
        }
        return Objects.equals(this.plato, other.plato);
    }
}
