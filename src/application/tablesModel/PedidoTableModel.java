package application.tablesModel;

import application.clasess.Pedido;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jesus Esquivel
 */
public class PedidoTableModel extends AbstractTableModel{
    private final List<Pedido> pedidos;
    private final String[] columnNames = {"#Pedido", "Nombre Cliente", "Fecha Pedido", "Total", "Tiempo Estimado", "Estado",  "Ver Detalle"};

    public PedidoTableModel(List<Pedido> pedidos){
        this.pedidos = pedidos;
    }

    @Override
    public int getRowCount() {
        return this.pedidos.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pedido pedido = this.pedidos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pedido.getIdPedido();
            case 1:
                return pedido.getCliente();
            case 2:
                return pedido.getFechaFormateada();
            case 3:
                return pedido.getTotal();
            case 4:
                return pedido.getTiempoEntregaEstimado();
            case 5:
                return pedido.getEstadoPedido();
            case 6: 
                return "";
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public Pedido getPedidoAt(int rowIndex) {
        return pedidos.get(rowIndex);
    }

    public int getIdAt(int rowIndex) {
        return pedidos.get(rowIndex).getIdPedido();
    }
}
