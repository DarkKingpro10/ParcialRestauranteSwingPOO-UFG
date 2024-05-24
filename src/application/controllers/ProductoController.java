package application.controllers;

import application.clasess.Producto;
import application.clasess.Restaurante;
import application.tablesModel.InventarioTableModel;
import application.utils.ResultadoOperacion;
import java.util.List;
import raven.application.form.other.IndexInventario;
import raven.toast.Notifications;

/**
 *
 * @author Jesus Esquivel
 */
public class ProductoController {

    //Método para obtener los productos en forma de modelo de tabla
    public InventarioTableModel obtenerProductos() {
        List<Producto> productos = Restaurante.obtenerProductosExistentes();

        if (productos.size() <= 0) {
            IndexInventario.lblErrorMsg.setVisible(true);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "No hay productos añadidos");
        } else {
            IndexInventario.lblErrorMsg.setVisible(false);
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
    
    /**
     * Agrega un producto al inventario
     * @param nombre del producto
     * @param cantidad a añadir al producto
     */
    public ResultadoOperacion addProducto(String nombre, int cantidad){
        return Restaurante.agregarProducto(nombre, cantidad);
    }
    
    /**
     * Eliminar un producto del inventario
     * @param id representa el id del producto
     * @return objeto de tipo ResultadoOperacion
     */
    public ResultadoOperacion delProducto(int id){
        return Restaurante.eliminarProducto(id);
    }
    
    /**
     * Modificar un producto
     * @param id representa el id del producto
     * @param nombre a modificar
     * @param cantidad a añadir al producto
     * @param cantidadD a eliminar al producto
     * @return 
     */
    public ResultadoOperacion modProducto(int id, String nombre, int cantidad, int cantidadD){
        return Restaurante.modificarProducto(id, nombre, cantidad, cantidadD);
    }
}
