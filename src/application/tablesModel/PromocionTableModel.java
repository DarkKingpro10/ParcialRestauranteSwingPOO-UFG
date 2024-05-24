package application.tablesModel;

import application.clasess.Plato;
import application.clasess.Promocion;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jesus Esquivel
 */
public class PromocionTableModel extends AbstractTableModel{
    private final List<Promocion> promociones;
    private final String[] columnNames = {"Nombre Promoción", "Descripción", "Plato", "Porcentaje Descuento", "Platos Minimos"};

    public PromocionTableModel(List<Promocion> promociones){
        this.promociones = promociones;
    }

    @Override
    public int getRowCount() {
        return this.promociones.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Promocion promocion = this.promociones.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return promocion.getNombrePromocion();
            case 1:
                return promocion.getDescripcion();
            case 2:
                return promocion.getPlato();
            case 3:
                return String.valueOf(promocion.getPorcentajeDescuento())+"%";
            case 4:
                return promocion.getPlatosMinimos();
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

    public Promocion getPromocionAt(int rowIndex) {
        return promociones.get(rowIndex);
    }

    public int getIdAt(int rowIndex) {
        return promociones.get(rowIndex).getIdPromocion();
    }
    
    public Plato getPlatoAt(int rowIndex){
        return promociones.get(rowIndex).getPlato();
    }
}
