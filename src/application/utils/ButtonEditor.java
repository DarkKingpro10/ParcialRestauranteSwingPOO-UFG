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

    public ButtonEditor(JCheckBox checkBox, JTable table) {
        super(checkBox);
        this.table = table;
        button = new JButton("Editar");
        button.setOpaque(true);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setToolTipText("Visualizar los ingredientes");
        button.setIcon(new ImageIcon(Restaurante.class.getResource("/raven/icon/png/view-icon.png"))); // Asegúrate de que la ruta sea correcta
        button.setPreferredSize(new Dimension(25, 25));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                System.out.println("Entro aqui");
                String row = (String) table.getValueAt(table.getSelectedRow(), 0);
                //int id = ((PlatosTableModel) table.getModel()).getIdAt(row);
                // Aquí puedes llamar al método que necesites con el ID del plato
                System.out.println("ID del plato seleccionado: " + Integer.valueOf(row));
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
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
