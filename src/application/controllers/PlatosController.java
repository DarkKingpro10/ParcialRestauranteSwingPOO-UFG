package application.controllers;

import application.clasess.Plato;
import application.clasess.Producto;
import application.clasess.Restaurante;
import application.clasess.TipoPlato;
import application.tablesModel.InventarioTableModel;
import application.tablesModel.PlatosTableModel;
import application.utils.ResultadoOperacion;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import raven.application.Application;
import raven.application.form.other.FormPlatos;
import raven.application.form.other.IndexPlatos;
import raven.toast.Notifications;

/**
 *
 * @author Jesus Esquivel
 */
public class PlatosController {

    private final Plato plato;

    //Método para inicializar el plato a añadirse
    public PlatosController() {
        plato = new Plato();
    }

    ;
    
    //Método para inicializar el plato a modificarse
    public PlatosController(int id) {
        plato = Restaurante.buscarPlato(id);

        if (plato == null) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "El plato no se ha encontrado");
            Application.showForm(new IndexPlatos());
        }
    }

    //Método para obtener los platos
    public PlatosTableModel obtenerPlatosTModel() {
        /*TipoPlato tipo = new TipoPlato(1, "Bebida", true);
        Plato plato = new Plato();
        plato.setIdPlato(1);
        plato.setNombrePlato("Prueba");
        plato.setDescripcion("Descripción prueba");
        plato.setPrecio(25.5);
        plato.setTiempoEstimadoPreparacionMn(5);
        plato.setTipoPlato(tipo);
        
        ArrayList<HashMap<Producto, Integer>> lista = new ArrayList<>();
        HashMap<Producto, Integer> map1 = new HashMap<>();
        map1.put(new Producto(1, "Producto A", 10), 5);
        HashMap<Producto, Integer> map2 = new HashMap<>();
        map2.put(new Producto(2, "Producto B", 20), 3);
        lista.add(map1);
        lista.add(map2);
        plato.setIngredientes(lista);
        
        
        Restaurante.getPlatos().add(plato);*/

        List<Plato> platos = Restaurante.obtenerPlatosExistentes();

        if (platos.size() <= 0) {
            IndexPlatos.lblErrorMsg.setVisible(true);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "No hay platos añadidos");
        } else {
            IndexPlatos.lblErrorMsg.setVisible(false);
        }
        return new PlatosTableModel(platos);
    }

    //Método para buscar los platos
    public PlatosTableModel buscarPlatos(String query) {
        List<Plato> platos = Restaurante.buscarPlatos(query);
        if (platos.size() <= 0) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "No hay coincidencias");
        }

        return new PlatosTableModel(platos);
    }

    /**
     * Eliminar un plato del restaurante
     *
     * @param id representa el id del plato
     * @return objeto de tipo ResultadoOperacion
     */
    public ResultadoOperacion delPlato(int id) {
        return Restaurante.eliminarPlato(id);
    }

    //Método para obtener el combobox de los tipos de plato
    public DefaultComboBoxModel obtenerTiposPlatos() {
        List<TipoPlato> tipos = Restaurante.obtenerTiposPlatoDisponibles();

        DefaultComboBoxModel<TipoPlato> model = new DefaultComboBoxModel();
        model.addElement(new TipoPlato(-1, "Seleccionar Tipo de Plato", false));
        for (TipoPlato tipo : tipos) {
            model.addElement(tipo);
        }

        return model;
    }

    //Método para obtener los ingredientes para llenar la tabla
    public InventarioTableModel obtenerIngredientes() {
        List<Producto> productos = Restaurante.obtenerProductosExistentes();

        if (productos.size() <= 0) {
            FormPlatos.lblErrorMsg.setVisible(true);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "No hay productos añadidos");
        } else {
            FormPlatos.lblErrorMsg.setVisible(false);
        }

        return new InventarioTableModel(productos);
    }

    /**
     * Método para buscar los productos que coincidan
     *
     * @param query Representa el parametro a buscar
     * @return modelo de la tabla
     */
    public InventarioTableModel buscarProductos(String query) {
        List<Producto> productos = Restaurante.buscarProductos(query);

        if (productos.size() <= 0) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "No hay coincidencias");
        }

        return new InventarioTableModel(productos);
    }

    //Método para obtener los ingredientes añadidos al plato
    public DefaultTableModel obtenerIngredientesAgr() {
        DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("ID Producto");
            modelo.addColumn("Ingrediente");
            modelo.addColumn("Cantidad");
            modelo.addColumn("Eliminar Ingrediente");
        try {
            

            for (HashMap<Producto, Integer> ingrediente : plato.getIngredientes()) {
                for (Map.Entry<Producto, Integer> entry : ingrediente.entrySet()) {
                    Producto producto = entry.getKey();
                    Integer cantidad = entry.getValue();
                    modelo.addRow(new Object[]{producto.getIdProducto(), producto.getNombreProducto(), cantidad, ""});
                }
            }

            return modelo;
        } catch(Exception e){
            System.out.println(e);
        }
        
        return modelo;
    }

    //Método para añadir inrgediente al plato
    public ResultadoOperacion addIngrediente(int IdProducto, int cantidad) {
        //Obtenemos el producto
        Producto ingrediente = Restaurante.buscarProducto(IdProducto);

        ResultadoOperacion res = plato.agregarIngrediente(ingrediente, cantidad);

        return res;
    }

    //Método para eliminar un ingrediente del plato
    public ResultadoOperacion delIngrediente(int IdProducto) {
        //Obtenemos el producto
        Producto ingrediente = Restaurante.buscarProducto(IdProducto);

        ResultadoOperacion res = plato.borrarIngrediente(ingrediente);

        return res;
    }

    //Método para obtener los ingredientes según la busqueda
    public DefaultTableModel buscarIngredientes(String query) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Producto");
        modelo.addColumn("Ingrediente");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Eliminar Ingrediente");

        for (HashMap<Producto, Integer> ingrediente : plato.buscarIngredientes(query)) {
            for (Map.Entry<Producto, Integer> entry : ingrediente.entrySet()) {
                Producto producto = entry.getKey();
                Integer cantidad = entry.getValue();
                modelo.addRow(new Object[]{producto.getIdProducto(), producto.getNombreProducto(), cantidad, ""});
            }
        }

        return modelo;
    }

    /**
     * Método para añadir un plato al restaurante
     *
     * @param nombre representa el nombre del plato
     * @param descripcion representa la descripción del plato
     * @param precio representa el precio
     * @param tiempo representa el tiempo estimado que toma preparar el plato
     * @param tipo representa el tipo de plato
     */
    public ResultadoOperacion add(String nombre, String descripcion, double precio, int tiempo, TipoPlato tipo) {
        plato.setNombrePlato(nombre);
        plato.setTipoPlato(tipo);
        plato.setDescripcion(descripcion);
        plato.setPrecio(Math.round(precio * 100.0) / 100.0);
        plato.setTiempoEstimadoPreparacionMn(tiempo);

        return Restaurante.agregarPlato(plato);
    }

    //Método para obtener el plato que esta siendo modificado
    public Plato obtenerPlato() {
        return plato;
    }

    /**
     * Método para añadir un plato al restaurante
     *
     * @param nombre representa el nombre del plato
     * @param descripcion representa la descripción del plato
     * @param precio representa el precio
     * @param tiempo representa el tiempo estimado que toma preparar el plato
     * @param tipo representa el tipo de plato
     */
    public ResultadoOperacion mod(String nombre, String descripcion, double precio, int tiempo, TipoPlato tipo) {
        plato.setNombrePlato(nombre);
        plato.setTipoPlato(tipo);
        plato.setDescripcion(descripcion);
        plato.setPrecio(Math.round(precio * 100.0) / 100.0);
        plato.setTiempoEstimadoPreparacionMn(tiempo);

        return Restaurante.modificarPlato(plato.getIdPlato(), plato);
    }
}
