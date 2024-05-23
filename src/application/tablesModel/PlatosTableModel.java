package application.tablesModel;

import application.clasess.Plato;
import java.util.List;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

public class PlatosTableModel extends AbstractTableModel {
    private final List<Plato> platos;
    private final String[] columnNames = {"Nombre Plato", "Descripción", "Precio", "Tiempo Preparación Estimado (Minutos)", "Tipo Plato", "Ver Ingredientes"};

    public PlatosTableModel(List<Plato> platos){
        this.platos = platos;
    }

    @Override
    public int getRowCount() {
        return this.platos.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Plato plato = this.platos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return plato.getNombrePlato();
            case 1:
                return plato.getDescripcion();
            case 2:
                return plato.getPrecio();
            case 3:
                return plato.getTiempoEstimadoPreparacionMn();
            case 4:
                return plato.getTipoPlato().getNombreTipoPlato();
            case 5:
                return "";  // Retorna un texto o un identificador que el botón puede mostrar
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
        if (columnIndex == 5) {
            return JButton.class;
        }
        return String.class;
    }

    public Plato getPlatoAt(int rowIndex) {
        return platos.get(rowIndex);
    }

    public int getIdAt(int rowIndex) {
        return platos.get(rowIndex).getIdPlato();
    }
}
