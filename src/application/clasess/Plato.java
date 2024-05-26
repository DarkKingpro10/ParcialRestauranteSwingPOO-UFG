package application.clasess;

import application.utils.ResultadoOperacion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Jesus Esquivel
 */
public class Plato {

    //Atributos de la clase
    private int idPlato;
    private String nombrePlato;
    private String descripcion;
    private double precio;
    private int tiempoEstimadoPreparacionMn;
    private ArrayList<HashMap<Producto, Integer>> ingredientes;
    private TipoPlato tipoPlato;
    private boolean disponibilidad;

    //Constructor parametrizado
    public Plato(int id, String nombrePlato, String descripcion, double precio, int tiempoEstimadoPreparacionMn, ArrayList<HashMap<Producto, Integer>> ingredientes, TipoPlato tipoPlato, boolean disponibilidad) {
        this.idPlato = id;
        this.nombrePlato = nombrePlato;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tiempoEstimadoPreparacionMn = tiempoEstimadoPreparacionMn;

        //Inicializando el plato
        this.ingredientes = ingredientes;
        this.tipoPlato = tipoPlato;
        this.disponibilidad = disponibilidad;
    }

    //Constructor vacio
    public Plato() {
        ingredientes = new ArrayList();
    }
    
    //Contructor especial para casos vacios
    public Plato(int id, String nombrePlato){
        this.idPlato = id;
        this.nombrePlato = nombrePlato;
    }

    //GETTERS y SETTERS
    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getTiempoEstimadoPreparacionMn() {
        return tiempoEstimadoPreparacionMn;
    }

    public void setTiempoEstimadoPreparacionMn(int tiempoEstimadoPreparacionMn) {
        this.tiempoEstimadoPreparacionMn = tiempoEstimadoPreparacionMn;
    }

    public TipoPlato getTipoPlato() {
        return tipoPlato;
    }

    public void setTipoPlato(TipoPlato tipoPlato) {
        this.tipoPlato = tipoPlato;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public ArrayList<HashMap<Producto, Integer>> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<HashMap<Producto, Integer>> ingredientes) {
        this.ingredientes = ingredientes;
    }

    //Metodos para validar si el objeto de la clase es igual a otro
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.nombrePlato);
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
        final Plato other = (Plato) obj;
        return Objects.equals(this.nombrePlato, other.nombrePlato);
    }

    /**
     * *
     * Método para agregar un ingrediente a la lista del plato o actualizar la
     * cantidad de este ingrediente
     *
     * @param producto Representa el ingrediente a añadir
     * @param cantidad representa la cantidad del ingrediente a añadir
     * @return objeto de ResultadoOperacion
     */
    public ResultadoOperacion agregarIngrediente(Producto producto, int cantidad) {
        try {
            //Validamos que el producto no este siendo agregado otra vez
            for (HashMap<Producto, Integer> ingrediente : ingredientes) {
                if (ingrediente.containsKey(producto)) { //Si esta siendo agregado otra vez
                    //Actualizamos la cantidad del ingrediente
                    int cantidadActual = ingrediente.get(producto);
                    ingrediente.put(producto, cantidadActual + cantidad);
                    return new ResultadoOperacion(true, "Cantidad de ingrediente incrementada exitosamente.");
                }
            }

            //Como no lo esta lo añadimos
            HashMap<Producto, Integer> nuevoIngrediente = new HashMap<>();
            nuevoIngrediente.put(producto, cantidad);
            ingredientes.add(nuevoIngrediente);

            return new ResultadoOperacion(true, "Ingrediente agregado exitosamente");
        } catch (Exception e) {
            return new ResultadoOperacion(false, "Ocurrio un erro al agregar el ingrediente " + e);
        }
    }

    /**
     * Método para eliminar un ingrediente del plato
     *
     * @param producto representa el ingrediente a eliminar
     * @return objeto de tipo ResultadoOperacion
     */
    public ResultadoOperacion borrarIngrediente(Producto producto) {
        try {
            for (HashMap<Producto, Integer> ingrediente : ingredientes) {
                if (ingrediente.containsKey(producto)) {
                    ingredientes.remove(ingrediente);
                    return new ResultadoOperacion(true, "Ingrediente eliminado exitosamente.");
                }
            }

            return new ResultadoOperacion(false, "No se encontro el ingrediente en la lista para poder eliminar");
        } catch (Exception e) {
            return new ResultadoOperacion(false, "Ingrediente no se borro.");
        }
    }

    /**
     * Método para conocer si el plato esta disponible para cocinar, dependiendo
     * de los ingredientes
     *
     * @return {boolean} Representa si esta disponible el plato o no para
     * cocinar
     */
    public boolean disponibilidadIngredientes() {
        try {
            for (HashMap<Producto, Integer> ingrediente : ingredientes) {
                for (Producto producto : ingrediente.keySet()) {
                    if (producto.getCantidadProducto() <= 0 || (producto.getCantidadProducto() < ingrediente.get(producto))) {
                        return false; // Al menos un producto tiene cantidad <= 0
                    }
                }
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Método para buscar los ingredientes del plato
     * @param query el parametro a buscar
     * @return 
     */
    public ArrayList<HashMap<Producto, Integer>> buscarIngredientes(String query) {
        //Recorremos la lista para buscar
        query = query.trim().toLowerCase();
        
        ArrayList<HashMap<Producto, Integer>> coincidencias = new ArrayList<>();
        //Recorremos la lista
        for (HashMap<Producto, Integer> ingrediente : this.ingredientes) {
            for (Map.Entry<Producto, Integer> entry : ingrediente.entrySet()) {
                Producto producto = entry.getKey();
                Integer cantidad = entry.getValue();
                
                //Validamos que sea el parametro a buscar
                if(producto.getNombreProducto().contains(query) || String.valueOf(producto.getCantidadProducto()).contains(query)){
                    coincidencias.add(ingrediente);
                }
            }
        }
        
        return coincidencias;
    };
    
    @Override
    public String toString() {
        return this.nombrePlato;
    }
}
