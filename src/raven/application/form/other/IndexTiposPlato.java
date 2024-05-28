/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package raven.application.form.other;

import application.controllers.TipoPlatoController;
import application.tablesModel.TipoPlatoTableModel;
import application.utils.CentrarColumnas;
import application.utils.ResultadoOperacion;
import application.utils.Validaciones;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JComponent;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;
import raven.toast.Notifications;

/**
 *
 * @author Jesus Esquivel
 */
public class IndexTiposPlato extends javax.swing.JPanel {

    //Atributos de la clase
    int idTipoPlato;
    //Importando las clases
    Validaciones validaciones = new Validaciones();
    TipoPlatoController controller = new TipoPlatoController();

    /**
     * Creates new form IndexTiposPlato
     */
    public IndexTiposPlato() {
        initComponents();
        inicializarCampos();
        cargarTabla(controller.obtenerTiposPlato());
    }

    //Método para inicializar la vista
    void inicializarCampos() {
        //Aplicando estilos
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        lblErrorMsg.setVisible(false);
        txtTipo.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Bebidas...");

        //Aplicando validaciones
        JComponent[] componentesAValidar = {txtTipo};
        validaciones.noPegar(componentesAValidar);
        tblTipos.setDefaultRenderer(Object.class, new CentrarColumnas());
        tblTipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    //Método para cargar la tabla
    void cargarTabla(TipoPlatoTableModel model) {
        tblTipos.setModel(model);
    }

    //Método para reiniciar el formulario
    void reiniciarForm() {
        txtTipo.setText("");
        idTipoPlato = 0;
    }

    //Deshabilitar el formulario
    void toggleEnableForm() {
        txtTipo.setEnabled(!txtTipo.isEnabled());
        btnAdd.setEnabled(!btnAdd.isEnabled());
        btnDel.setEnabled(!btnDel.isEnabled());
        btnMod.setEnabled(!btnMod.isEnabled());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        lblErrorMsg = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtTipo = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lbUser = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTipos = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(806, 460));

        jPanel1.setLayout(new java.awt.BorderLayout());

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Gestion de los tipos de platos");
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

        lblErrorMsg.setForeground(javax.swing.UIManager.getDefaults().getColor("Component.custom.borderColor"));
        lblErrorMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblErrorMsg.setText("No hay tipos de platos añadidos");

        jPanel2.setLayout(new java.awt.GridLayout(1, 2));

        txtTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTipoKeyTyped(evt);
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

        lbUser.setText("Tipo de plato:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(lbUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(btnBuscar)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3);

        tblTipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTipos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTiposMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTipos);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4);

        javax.swing.GroupLayout pnlCentralLayout = new javax.swing.GroupLayout(pnlCentral);
        pnlCentral.setLayout(pnlCentralLayout);
        pnlCentralLayout.setHorizontalGroup(
            pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblErrorMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlCentralLayout.setVerticalGroup(
            pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCentralLayout.createSequentialGroup()
                .addComponent(lblErrorMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        //Ejecutamos el método de busqueda
        if (txtTipo.getText().trim().isBlank()) {
            cargarTabla(controller.obtenerTiposPlato());
        } else {
            cargarTabla(controller.buscarTipos(txtTipo.getText()));
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtTipoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoKeyTyped
        //Validamos que solo acepte letras
        validaciones.soloLetras(evt, 2, txtTipo.getText());
    }//GEN-LAST:event_txtTipoKeyTyped

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        //Deshabilitamos los botones
        toggleEnableForm();
        //Ejecutamos método
        ResultadoOperacion res = controller.addTipo(txtTipo.getText().trim());

        //Evaluamos la respuesta
        if (res.isExito()) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, res.getMensaje());
            cargarTabla(controller.obtenerTiposPlato());
            reiniciarForm();
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, res.getMensaje());
        }

        toggleEnableForm();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        //Consulatomos si desea continuar
        if(!validaciones.confirmarAccion("¿Está seguro que desea eliminar este tipo de plato?", "Confirmar eliminación del plato "+txtTipo.getText())){
            return;
        }
        //Eliminar el tipo plato
        toggleEnableForm();
        int row = tblTipos.getSelectedRow();
        if (row != -1 && idTipoPlato > 0) {
            ResultadoOperacion res = controller.delTipo(idTipoPlato);

            if (res.isExito()) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, res.getMensaje());
                cargarTabla(controller.obtenerTiposPlato());
                reiniciarForm();
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, res.getMensaje());
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe seleccionar un tipo de plato");
        }

        toggleEnableForm();
    }//GEN-LAST:event_btnDelActionPerformed

    private void tblTiposMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTiposMouseClicked
        //Llenamos el formulario
        int row = tblTipos.getSelectedRow();
        txtTipo.setText(tblTipos.getValueAt(row, 0).toString());
        //Obtenemos el modelo de la tabla
        TipoPlatoTableModel model = (TipoPlatoTableModel) tblTipos.getModel();
        //Seteamos el id del tipo seleccionado
        idTipoPlato = model.getIdAt(row);
    }//GEN-LAST:event_tblTiposMouseClicked

    private void btnModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModActionPerformed
        //Consulatomos si desea continuar
        if(!validaciones.confirmarAccion("¿Está seguro que desea modificar el tipo de plato?", "Confirmar modificación del tipo de plato "+txtTipo.getText())){
            return;
        }
        //Modificar el tipo de plato
        toggleEnableForm(); //Deshabilitamos los botones
        int row = tblTipos.getSelectedRow(); //Obtenemos la fila seleccionada
        if (row != -1 && idTipoPlato > 0) {
            ResultadoOperacion res = controller.modTipo(idTipoPlato, txtTipo.getText().trim());//Ejecutamos la operación

            if (res.isExito()) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, res.getMensaje());
                cargarTabla(controller.obtenerTiposPlato());
                reiniciarForm();
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, res.getMensaje());
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe seleccionar un tipo de plato");
        }

        toggleEnableForm();
    }//GEN-LAST:event_btnModActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnMod;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbUser;
    public static javax.swing.JLabel lblErrorMsg;
    private javax.swing.JPanel pnlCentral;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JTable tblTipos;
    private javax.swing.JTextField txtTipo;
    // End of variables declaration//GEN-END:variables
}
