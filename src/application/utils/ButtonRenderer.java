
package application.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import raven.application.Application;

/**
 *
 * @author Jesus Esquivel
 */
public class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer(Color color, ImageIcon icon, String tooltip) {
        setOpaque(true);
        setBackground(color);
        setForeground(Color.WHITE);
        setToolTipText(tooltip);
        setIcon(icon);
        setPreferredSize(new Dimension(25, 25));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}