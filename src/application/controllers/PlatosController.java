package application.controllers;

import application.clasess.Plato;
import application.clasess.Producto;
import application.clasess.Restaurante;
import application.clasess.TipoPlato;
import application.tablesModel.InventarioTableModel;
import application.tablesModel.PlatosTableModel;
import application.utils.ResultadoOperacion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
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
    public  PlatosController(){
        plato = new Plato();
    };
    
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
        platos = Restaurante.getPlatos();

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
    
    public InventarioTableModel obtenerIngredientes(){
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
    public DefaultTableModel obtenerIngredientesAgr(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Producto");
        modelo.addColumn("Ingrediente");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Eliminar Ingrediente");

        for (HashMap<Producto, Integer> ingrediente : plato.getIngredientes()) {
            for (Map.Entry<Producto, Integer> entry : ingrediente.entrySet()) {
                Producto producto = entry.getKey();
                Integer cantidad = entry.getValue();
                modelo.addRow(new Object[]{producto.getIdProducto(),producto.getNombreProducto(), cantidad,""});
            }
        }
        
        return modelo;
    }

    //Método para añadir inrgediente al plato
    public ResultadoOperacion addIngrediente(int IdProducto, int cantidad){
        //Obtenemos el producto
        Producto ingrediente = Restaurante.buscarProducto(IdProducto);
        
        ResultadoOperacion res = plato.agregarIngrediente(ingrediente, cantidad);
        
        return res;
    }
            
    public void add(TipoPlato tipo) {
        Plato plato = new Plato();
        plato.setIdPlato(1);
        plato.setNombrePlato("Prueba cmbios");
        plato.setTipoPlato(tipo);
        plato.setDescripcion("Descripción prueba");
        plato.setPrecio(25.5);
        plato.setTiempoEstimadoPreparacionMn(5);
        
        Restaurante.getPlatos().add(plato);
        System.out.println(Restaurante.getPlatos().size());
    }
}
