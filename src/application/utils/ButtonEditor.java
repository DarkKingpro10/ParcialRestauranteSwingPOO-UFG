package application.utils;

import application.clasess.Restaurante;
import application.tablesModel.PlatosTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends DefaultCellEditor {

    private JButton button;
    private String label;
    private boolean isPushed;
    private JTable table;

    public ButtonEditor(JCheckBox checkBox, JTable table, Color color, ImageIcon icon, String tooltip) {
        super(checkBox);
        this.table = table;
        button = new JButton("Editar");
        button.setOpaque(true);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setToolTipText(tooltip);
        button.setIcon(icon); // Asegúrate de que la ruta sea correcta
        button.setPreferredSize(new Dimension(25, 25));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                //String row = (String) table.getValueAt(table.getSelectedRow(), 0);
                //int id = ((PlatosTableModel) table.getModel()).getIdAt(row);
                // Aquí puedes llamar al método que necesites con el ID del plato
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        //label = (value == null) ? "" : value.toString();
        //button.setText(label);
        //isPushed = true;
        //return button;

        if (table.getValueAt(row, 0) != null && !table.getValueAt(row, 0).toString().isEmpty()) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        } else {
            return null; // No editar nada para filas de descuento
        }
    }

    @Override
    public Object getCellEditorValue() {
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
