package application.tablesModel;

import application.clasess.TipoPlato;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TipoPlatoTableModel extends AbstractTableModel {
    private final List<TipoPlato> tiposPlato;
    private final String[] columnNames = {"Nombre Tipo Plato"};

    public TipoPlatoTableModel(List<TipoPlato> tiposPlato) {
        this.tiposPlato = tiposPlato;
    }

    @Override
    public int getRowCount() {
        return tiposPlato.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TipoPlato tipoPlato = tiposPlato.get(rowIndex);
        return tipoPlato.getNombreTipoPlato();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public TipoPlato getTipoPlatoAt(int rowIndex) {
        return tiposPlato.get(rowIndex);
    }
    
    public int getIdAt(int rowIndex){
        return tiposPlato.get(rowIndex).getIdTipoPlato();
    }
}
