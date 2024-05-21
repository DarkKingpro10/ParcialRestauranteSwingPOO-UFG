package application.clasess;

import java.util.Objects;

/**
 *
 * @author Jesús Gerardo Esquivel Ramírez
 */
public class Producto {
    //Atributos de clase
    private int idProducto;
    private String nombreProducto;
    private int cantidadProducto;
    
    //Constructor parametrizado
    public Producto(int idProducto, String nombreProducto, int cantidadProducto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidadProducto = cantidadProducto;
    }
   
    //GETTERS y SETTERS
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    /**
     * Método para saber si dos objetos de producto son igual
     * @param obj que representa el objeto a ser evaluado
     * @return {boolean} que representa si el objeto es igual o no es igual
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if(this.cantidadProducto == -1){//Validando que no se halla eliminado
            return false;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        return Objects.equals(this.nombreProducto, other.nombreProducto);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nombreProducto);
        return hash;
    }
    
    /**
     * Método que valida que el producto posee existencias y esta disponible para ser usado
     * @return {boolean} que representa si esta disponible o no
     */
    public boolean obtenerDisponibilidad(){
        return this.cantidadProducto > 0;
    }
    
    /**
     * Método que valida si el producto ha sido eliminado
     * @return 
     */
    public boolean fueBorrado(){
        return this.cantidadProducto <= -1;
    }
}
