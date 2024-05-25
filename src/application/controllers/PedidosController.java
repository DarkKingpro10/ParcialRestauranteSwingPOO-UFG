package application.controllers;

import application.clasess.Pedido;
import application.clasess.Restaurante;
import application.enums.EstadoPedido;
import application.tablesModel.PedidoTableModel;
import application.utils.ResultadoOperacion;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
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

    //Método para inicializarse para añadirse
    public PedidosController() {
        pedido = new Pedido();
    }

    //Método para inicializarse cuando se va a actualizar
    public PedidosController(int id) {
        pedido = Restaurante.buscarPedio(id);

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

        return new PedidoTableModel(pedidos);
    }

    /**
     * Método para dar por finalizado un pedido
     *
     * @param id representa el id del pedido
     * @return ResultadoOperacion
     */
    public ResultadoOperacion facturarPedido(int id) {
        Pedido pedido = Restaurante.buscarPedio(id);

        if (pedido == null) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "El pedido no se ha encontrado");
        } else {
            return pedido.facturar();
        }

        return new ResultadoOperacion(false, "No se encontro el pedido");
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

    public DefaultComboBoxModel obtenerEstados() {
        DefaultComboBoxModel<Object> model = new DefaultComboBoxModel<>();
        model.addElement("Todos");

        for (EstadoPedido estado : EstadoPedido.values()) {
            
            if(estado == EstadoPedido.CREANDOSE) continue;
            
            model.addElement(estado);
        }
        
        return model;
    }
}
