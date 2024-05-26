package application.controllers;

import application.clasess.Pedido;
import application.clasess.Plato;
import application.clasess.Producto;
import application.clasess.Restaurante;
import application.enums.EstadoPedido;
import application.tablesModel.PedidoTableModel;
import application.utils.ResultadoOperacion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import raven.application.Application;
import raven.application.form.other.IndexPedidos;
import raven.application.form.other.IndexPlatos;
import raven.toast.Notifications;

/**
 *
 * @author Jesus Esquivel
 */
public class PedidosController {

    private final Pedido pedido;
    private ArrayList<HashMap<Plato, Integer>> platosEliminados;

    //Método para inicializarse para añadirse
    public PedidosController() {
        pedido = new Pedido();
        platosEliminados = new ArrayList<>();
    }

    //Método para inicializarse cuando se va a actualizar
    public PedidosController(int id) {
        pedido = Restaurante.buscarPedio(id);
        platosEliminados = new ArrayList<>();

        if (pedido == null) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "El pedido no se ha encontrado");
            Application.showForm(new IndexPedidos());
        }
    }

    //Método para obtener los pedidos
    public PedidoTableModel obtenerPedidos() {
        List<Pedido> pedidos = Restaurante.getPedidos();

        if (pedidos.size() <= 0) {
            IndexPedidos.lblErrorMsg.setVisible(true);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "No hay pedidos añadidos");
        } else {
            IndexPedidos.lblErrorMsg.setVisible(false);
        }

        if (IndexPedidos.estado instanceof EstadoPedido) {
            EstadoPedido estadoPedido = (EstadoPedido) IndexPedidos.estado;
            pedidos = pedidos.stream().filter(pedido -> pedido.getEstadoPedido() == estadoPedido).collect(Collectors.toList());
        }
        
        System.out.println(pedidos.size());

        return new PedidoTableModel(pedidos);
    }

    /**
     * Método para dar por finalizado un pedido
     *
     * @param id representa el id del pedido
     * @return ResultadoOperacion
     */
    public ResultadoOperacion facturarPedido(int id) {
        return Restaurante.facturar(id);
    }

    //Método para obtener los pedidos
    public PedidoTableModel buscarPedidos(String query) {
        List<Pedido> pedidos = Restaurante.buscarPedidos(query);

        if (pedidos.size() <= 0) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "No hay coincidencias");
        } else {
            IndexPedidos.lblErrorMsg.setVisible(false);
        }

        if (IndexPedidos.estado instanceof EstadoPedido) {
            EstadoPedido estadoPedido = (EstadoPedido) IndexPedidos.estado;
            pedidos = pedidos.stream().filter(pedido -> pedido.getEstadoPedido() == estadoPedido).collect(Collectors.toList());
        }

        return new PedidoTableModel(pedidos);
    }

    //Método apra obtener los estados
    public DefaultComboBoxModel obtenerEstados() {
        DefaultComboBoxModel<Object> model = new DefaultComboBoxModel<>();
        model.addElement("Todos");

        for (EstadoPedido estado : EstadoPedido.values()) {

            if (estado == EstadoPedido.CREANDOSE) {
                continue;
            }

            model.addElement(estado);
        }

        return model;
    }

    //Método para obtener los estados pero cuando es modificar
    public DefaultComboBoxModel obtenerEstadosForForm() {
        DefaultComboBoxModel<Object> model = new DefaultComboBoxModel<>();
        model.addElement("Seleccionar Estado");

        for (EstadoPedido estado : EstadoPedido.values()) {

            if (estado == EstadoPedido.CREANDOSE) {
                continue;
            }

            model.addElement(estado);
        }

        return model;
    }

    //Método para obtener los platos que se podrán añadir
    public DefaultTableModel obtenerPlatos() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID Plato");
        model.addColumn("Nombre Plato");
        model.addColumn("Precio");

        for (Plato plato : Restaurante.obtenerPlatosDisponibles()) {
            model.addRow(new Object[]{plato.getIdPlato(), plato.getNombrePlato(), "$" + Math.round((plato.getPrecio()) * 100.0) / 100.0});
        }

        if (model.getRowCount() <= 0) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "No hay platos disponibles actualmente");
        }

        return model;
    }

    //Método para obtener los platos agregados
    public ArrayList<HashMap<Plato, Integer>> obtenerPlatosAgr() {
        return pedido.getPlatos();
    }

    //Método para obtener el Pedido
    public Pedido obtenerPedido() {
        return pedido;
    }

    /**
     * Método para añadir un pedido al restaurante
     *
     * @param nombreCliente
     * @param total representa el total del pedido
     * @param tiempo representa el tiempo del pedido
     * @return ResultadoOperacion
     */
    public ResultadoOperacion add(String nombreCliente, double total, int tiempo) {
        pedido.setCliente(nombreCliente);

        ResultadoOperacion res = Restaurante.agregarPedido(pedido);

        if (res.isExito()) {
            actualizarInventario();
        }

        return res;
    }

    //Método para obtener los platos que se podrán añadir
    public DefaultTableModel buscarPlatos(String query) {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID Plato");
        model.addColumn("Nombre Plato");
        model.addColumn("Precio");

        for (Plato plato : Restaurante.buscarPlatosDisponibles(query)) {
            model.addRow(new Object[]{plato.getIdPlato(), plato.getNombrePlato(), "$" + Math.round((plato.getPrecio()) * 100.0) / 100.0});
        }

        if (model.getRowCount() <= 0) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "No hay platos que coincidan");
        }

        return model;
    }

    /**
     * Método para agregar un plato
     *
     * @param IdPlato
     * @param cantidad a agregar
     * @return
     */
    public ResultadoOperacion addPlato(int IdPlato, int cantidad) {
        //Obtenemos el plato
        Plato plato = Restaurante.buscarPlato(IdPlato);

        if (plato == null) {
            return new ResultadoOperacion(false, "No se encontro el plato");
        }
        
        ResultadoOperacion res = pedido.agregarPlato(plato, cantidad);
        
        return res;
    }

    /**
     * Método para eliminar un plato
     *
     * @param IdPlato
     * @return ResultadoOperacion
     */
    public ResultadoOperacion delPlato(int IdPlato, int cantidad) {
        //Obtenemos el plato
        Plato plato = Restaurante.buscarPlato(IdPlato);

        if (plato == null) {
            return new ResultadoOperacion(false, "No se encontro el plato");
        }

        HashMap<Plato, Integer> platoEliminado = new HashMap<>();
        platoEliminado.put(plato, cantidad);

        ResultadoOperacion res = pedido.borrarPlato(plato, cantidad);
        
        if(res.isExito()){
            platosEliminados.add(platoEliminado);
        }
  
        return res;
    }

    /**
     * Método para buscar los platos agregados
     *
     * @param query el nombre del plato para que coincida
     * @return ArrayList<HashMap<Plato, Integer>>
     */
    public ArrayList<HashMap<Plato, Integer>> buscarPlatosAgr(String query) {
        //Creamos objeto para añadir los platos filtrados
        ArrayList<HashMap<Plato, Integer>> platosFiltrados = new ArrayList<>();

        //Recorremos los platos agregados
        for (HashMap<Plato, Integer> map : pedido.getPlatos()) {
            //Recorremos cada key del map
            for (Map.Entry<Plato, Integer> entry : map.entrySet()) {
                Plato plato = entry.getKey();

                if (plato.getNombrePlato().contains(query)) {
                    HashMap<Plato, Integer> platoFiltrado = new HashMap<>();
                    platoFiltrado.put(plato, entry.getValue());
                    platosFiltrados.add(platoFiltrado);
                }
            }
        }

        return platosFiltrados;
    }

    /**
     * Método para modificar un pedido
     *
     * @param idPedido del pedido a modificar
     * @param nombre del cliente a modificar
     * @param total
     * @param tiempo estimado de preparación
     * @param estado del pedido
     * @return ResultadoOperacion
     */
    public ResultadoOperacion mod(int idPedido, String nombre, double total, int tiempo, EstadoPedido estado) {
        pedido.setCliente(nombre);
        pedido.setEstadoPedido(estado);

        ResultadoOperacion res = Restaurante.modificarPedido(idPedido, pedido);

        //Evaluamos si fue exitoso
        if (res.isExito()) {
            restoreInventario();
            actualizarInventario();
        }

        return res;
    }

    //Método para actualizar la cantidad del producto en el inventario y restar las que se usarán 
    public void actualizarInventario() {
        try {
            //Recorremos los platos agregados
            for (HashMap<Plato, Integer> mapPlatos : pedido.getPlatos()) {
                //Recorremos cada key del map
                for (Map.Entry<Plato, Integer> entryPlatos : mapPlatos.entrySet()) {
                    Plato plato = entryPlatos.getKey(); //Obtenemos el plato

                    //Obtenemos los ingredientes
                    ArrayList<HashMap<Producto, Integer>> ingredientes = plato.getIngredientes();

                    //Recorremos el mapa de productos
                    for (HashMap<Producto, Integer> mapIngredientes : ingredientes) {
                        //recorremos las llaves del mapa de productos
                        for (Map.Entry<Producto, Integer> entryIngredientes : mapIngredientes.entrySet()) {
                            Producto producto = entryIngredientes.getKey();

                            //Buscamos el producto en el Restaurante
                            Producto productoInventario = Restaurante.buscarProducto(producto.getIdProducto());

                            //Una vez adquirido, actualizamos su cantidad restandole la cantidad que se hara
                            //Obtenemos la cantidad a restar de la cantidad de platos * la cantidad de producto que usa ese plato
                            int cantidadARestar = entryPlatos.getValue() * entryIngredientes.getValue();

                            productoInventario.setCantidadProducto(producto.getCantidadProducto() - cantidadARestar);
                        }
                    }
                }
            }
        } catch (Exception e) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Ocurrio un error al actualizar el inventario, hagalo manual por favor");
        }
    }

    //Método para reiniciar la cantidad del inventario en caso se hallan eliminado cantidades del inventario
    public void restoreInventario() {
        try {
            ArrayList<HashMap<Plato, Integer>> platosFiltrados = new ArrayList<>();

            //Recorremos los platos agregados
            for (HashMap<Plato, Integer> mapPlatos : pedido.getPlatos()) {
                //Recorremos cada key del map
                for (Map.Entry<Plato, Integer> entryPlatos : mapPlatos.entrySet()) {
                    Plato plato = entryPlatos.getKey(); //Obtenemos el plato

                    //Obtenemos los ingredientes
                    ArrayList<HashMap<Producto, Integer>> ingredientes = plato.getIngredientes();

                    //Recorremos el mapa de productos
                    for (HashMap<Producto, Integer> mapIngredientes : ingredientes) {
                        //recorremos las llaves del mapa de productos
                        for (Map.Entry<Producto, Integer> entryIngredientes : mapIngredientes.entrySet()) {
                            Producto producto = entryIngredientes.getKey();

                            //Buscamos el producto en el Restaurante
                            Producto productoInventario = Restaurante.buscarProducto(producto.getIdProducto());

                            //Una vez adquirido, actualizamos su cantidad sumandole la cantidad que fue eliminada
                            //Obtenemos la cantidad a restar de la cantidad de platos * la cantidad de producto que usa ese plato
                            int cantidadARestaurar = entryPlatos.getValue() * entryIngredientes.getValue();

                            productoInventario.setCantidadProducto(producto.getCantidadProducto() + cantidadARestaurar);
                        }
                    }
                }
            }
        } catch (Exception e) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Ocurrio un error al actualizar el inventario, hagalo manual por favor");
        }
    }
}
