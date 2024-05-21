package application.tablesModel;

import application.clasess.Producto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jesus Esquivel
 */
public class InventarioTableModel extends AbstractTableModel {

    private final List<Producto> inventario;
    private final String[] columnNames = {"Nombre Producto", "Cantidad en Inventario"};

    public InventarioTableModel(List<Producto> productos) {
        this.inventario = productos;
    }

    @Override
    public int getRowCount() {
        return this.inventario.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto producto = this.inventario.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return producto.getNombreProducto();
            case 1:
                return producto.getCantidadProducto();
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

    public Producto getTipoPlatoAt(int rowIndex) {
        return inventario.get(rowIndex);
    }

    public int getIdAt(int rowIndex) {
        return inventario.get(rowIndex).getIdProducto();
    }
}
