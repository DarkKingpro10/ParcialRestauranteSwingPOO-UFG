package application.utils;

/**
 *
 * @author Jesus Esquivel
 */
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class CentrarColumnas extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setHorizontalAlignment(SwingConstants.CENTER);

        if (table.getValueAt(row, 0) != null && table.getValueAt(row, 0).toString().isEmpty()) { // Identifica las filas de descuento
            setForeground(Color.red);
        }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
