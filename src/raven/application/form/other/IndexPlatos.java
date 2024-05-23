package raven.application.form.other;

import application.clasess.Plato;
import application.clasess.Producto;
import application.controllers.PlatosController;
import application.tablesModel.PlatosTableModel;
import application.utils.ButtonEditor;
import application.utils.CentrarColumnas;
import application.utils.ButtonRenderer;
import application.utils.ResultadoOperacion;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import raven.application.Application;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public class IndexPlatos extends javax.swing.JPanel {

    //Atributos de la clase
    PlatosController controller = new PlatosController();
    int idPlato;
    Plato platoSeleccionado;
    private JTable tablaDetalle;
    ImageIcon iconDetalle = new ImageIcon(Application.class.getResource("/raven/icon/png/view-icon.png"));

    public IndexPlatos() {
        initComponents();
        inicializarCampos();
        cargarTabla(controller.obtenerPlatosTModel());
    }

    //Método para inicializarCampos
    void inicializarCampos() {
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        tblPlatos.setDefaultRenderer(Object.class, new CentrarColumnas());
        lblErrorMsg.setVisible(false);
        txtBusqueda.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Papas fritas ...");
    }

    void cargarTabla(PlatosTableModel model) {
        tblPlatos.setModel(model);
        tblPlatos.getColumnModel().getColumn(5).setMaxWidth(100);
        tblPlatos.getColumnModel().getColumn(5).setMinWidth(100);
        tblPlatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tblPlatos.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer(Color.BLACK, iconDetalle, "Visualizar Detalle del plato"));
        tblPlatos.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox(), tblPlatos));
    }

    void reiniciarForm() {
        txtBusqueda.setText("");
        idPlato = 0;
        platoSeleccionado = null;
    }

    void mostrarDetalle() {
        DefaultTableModel modeloDetalle = new DefaultTableModel();
        modeloDetalle.addColumn("Ingrediente");
        modeloDetalle.addColumn("Cantidad");

        ArrayList<HashMap<Producto, Integer>> ingredientes = platoSeleccionado.getIngredientes();

        for (HashMap<Producto, Integer> ingrediente : ingredientes) {
            for (Map.Entry<Producto, Integer> entry : ingrediente.entrySet()) {
                Producto producto = entry.getKey();
                Integer cantidad = entry.getValue();
                modeloDetalle.addRow(new Object[]{producto.getNombreProducto(), cantidad});
            }
        }

        // Crear y mostrar la tabla de detalle en un diálogo
        tablaDetalle = new JTable(modeloDetalle);
        JScrollPane scrollPane = new JScrollPane(tablaDetalle);
        JOptionPane.showMessageDialog(null, scrollPane, "Detalle del Plato: " + (platoSeleccionado.getNombrePlato()), JOptionPane.PLAIN_MESSAGE);
    }

    //Deshabilitar o Habilitar form
    void toggleEnableForm() {
        txtBusqueda.setEnabled(!txtBusqueda.isEnabled());
        btnAdd.setEnabled(!btnAdd.isEnabled());
        btnBuscar.setEnabled(!btnBuscar.isEnabled());
        btnDel.setEnabled(!btnDel.isEnabled());
        btnMod.setEnabled(!btnMod.isEnabled());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lb = new javax.swing.JLabel();
        pnlFooter = new javax.swing.JPanel();
        btnDel = new javax.swing.JButton();
        btnMod = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        pnlCentral = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPlatos = new javax.swing.JTable();
        btnReiniciarTabla = new javax.swing.JButton();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lbUser = new javax.swing.JLabel();
        lblErrorMsg = new javax.swing.JLabel();

        jPanel1.setLayout(new java.awt.BorderLayout());

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Listado de Platos");
        jPanel1.add(lb, java.awt.BorderLayout.PAGE_START);

        btnDel.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        btnDel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDel.setForeground(new java.awt.Color(0, 0, 0));
        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/del-icon.png"))); // NOI18N
        btnDel.setText("Eliminar");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        btnMod.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Yellow"));
        btnMod.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMod.setForeground(new java.awt.Color(0, 0, 0));
        btnMod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/mod-icon.png"))); // NOI18N
        btnMod.setText("Modificar");
        btnMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(0, 204, 102));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(0, 0, 0));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/add-icon.png"))); // NOI18N
        btnAdd.setText(" Agregar ");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFooterLayout = new javax.swing.GroupLayout(pnlFooter);
        pnlFooter.setLayout(pnlFooterLayout);
        pnlFooterLayout.setHorizontalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFooterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                .addComponent(btnMod, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlFooterLayout.setVerticalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFooterLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMod, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(pnlFooter, java.awt.BorderLayout.PAGE_END);

        tblPlatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblPlatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPlatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPlatos);

        btnReiniciarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/reload-icon.png"))); // NOI18N
        btnReiniciarTabla.setToolTipText("Recargar la Tabla");
        btnReiniciarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarTablaActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(0, 0, 0));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/search-icon.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        lbUser.setText("Ingrese el plato a buscar:");

        lblErrorMsg.setForeground(javax.swing.UIManager.getDefaults().getColor("Component.custom.borderColor"));
        lblErrorMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblErrorMsg.setText("No hay Platos añadidos");

        javax.swing.GroupLayout pnlCentralLayout = new javax.swing.GroupLayout(pnlCentral);
        pnlCentral.setLayout(pnlCentralLayout);
        pnlCentralLayout.setHorizontalGroup(
            pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
            .addGroup(pnlCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnReiniciarTabla)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblErrorMsg)
                .addGap(31, 31, 31)
                .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCentralLayout.createSequentialGroup()
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar))
                    .addComponent(lbUser))
                .addGap(26, 26, 26))
        );
        pnlCentralLayout.setVerticalGroup(
            pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCentralLayout.createSequentialGroup()
                .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlCentralLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnReiniciarTabla))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCentralLayout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addComponent(lbUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar)
                            .addComponent(lblErrorMsg))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(pnlCentral, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        toggleEnableForm();
        int row = tblPlatos.getSelectedRow();
        System.out.println(row + " " + idPlato);
        if (row != -1 && idPlato > 0) {
            ResultadoOperacion res = controller.delPlato(idPlato);

            if (res.isExito()) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, res.getMensaje());
                cargarTabla(controller.obtenerPlatosTModel());
                reiniciarForm();
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, res.getMensaje());
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe seleccionar un plato");
        }

        toggleEnableForm();
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModActionPerformed

    }//GEN-LAST:event_btnModActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Application.showForm(new FormPlatos());
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblPlatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPlatosMouseClicked
        int row = tblPlatos.getSelectedRow();
        int column = tblPlatos.getSelectedColumn();
        PlatosTableModel model = (PlatosTableModel) tblPlatos.getModel();
        //Seteamos el id seleccionado y el Plato seleccionado
        idPlato = model.getIdAt(row);
        platoSeleccionado = model.getPlatoAt(row);
        if (column == 5) {
            mostrarDetalle();
        }
    }//GEN-LAST:event_tblPlatosMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (txtBusqueda.getText().trim().isBlank()) {
            cargarTabla(controller.obtenerPlatosTModel());
        } else {
            cargarTabla(controller.buscarPlatos(txtBusqueda.getText()));
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnReiniciarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarTablaActionPerformed
        reiniciarForm();
        cargarTabla(controller.obtenerPlatosTModel());
    }//GEN-LAST:event_btnReiniciarTablaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnMod;
    private javax.swing.JButton btnReiniciarTabla;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbUser;
    public static javax.swing.JLabel lblErrorMsg;
    private javax.swing.JPanel pnlCentral;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JTable tblPlatos;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
