/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package raven.application.form.other;

import application.clasess.Plato;
import application.controllers.PromocionController;
import application.tablesModel.PromocionTableModel;
import application.utils.CentrarColumnas;
import application.utils.ResultadoOperacion;
import application.utils.Validaciones;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JComponent;
import javax.swing.ListSelectionModel;
import raven.toast.Notifications;

/**
 *
 * @author Jesus Esquivel
 */
public class IndexPromociones extends javax.swing.JPanel {

    //Atributos de la clase
    int idPromocion;
    //Importando las clases
    Validaciones validaciones = new Validaciones();
    PromocionController controller = new PromocionController();

    /**
     * Creates new form IndexTiposPlato
     */
    public IndexPromociones() {
        initComponents();
        inicializarCampos();
        cargarTabla(controller.obtenerPromociones());
        cargarPlatos();
    }

    //Método para inicializar la vista
    void inicializarCampos() {
        //Aplicando estilos
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        lblErrorMsg.setVisible(false);
        txtNombre.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Donas 2x1");
        txtPlatosMn.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "1..");
        txtPorcentaje.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "10...");
        txaDescripcion.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "En Septiembre hay Donas al 2x1...");

        //Aplicando validaciones
        JComponent[] componentesAValidar = {txtPlatosMn, txtPorcentaje};
        validaciones.noPegar(componentesAValidar);
        tblPromociones.setDefaultRenderer(Object.class, new CentrarColumnas());
        tblPromociones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    //Método para cargar la tabla
    void cargarTabla(PromocionTableModel model) {
        tblPromociones.setModel(model);
    }

    //Método para cargar el combo box de los platos
    void cargarPlatos() {
        cmbPlatos.setModel(controller.obtenerPlatos());
    }

    //Método para reiniciar el formulario
    void reiniciarForm() {
        txtNombre.setText("");
        txtPlatosMn.setText("");
        txtPlatosMn.setText("");
        idPromocion = 0;
        cargarPlatos();
    }

    //Deshabilitar el formulario
    void toggleEnableForm() {
        txtNombre.setEnabled(!txtNombre.isEnabled());
        txtPlatosMn.setEnabled(!txtPlatosMn.isEnabled());
        txtPlatosMn.setEnabled(!txtPlatosMn.isEnabled());
        txaDescripcion.setEnabled(!txaDescripcion.isEnabled());
        cmbPlatos.setEnabled(!cmbPlatos.isEnabled());
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
        txtNombre = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lbUser = new javax.swing.JLabel();
        cmbPlatos = new javax.swing.JComboBox<>();
        lbUser1 = new javax.swing.JLabel();
        lbUser2 = new javax.swing.JLabel();
        txtPorcentaje = new javax.swing.JTextField();
        lbUser3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaDescripcion = new javax.swing.JTextArea();
        lbUser4 = new javax.swing.JLabel();
        txtPlatosMn = new javax.swing.JTextField();
        btnReiniciarTabla = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPromociones = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(806, 460));

        jPanel1.setLayout(new java.awt.BorderLayout());

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Promociones");
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

        jPanel2.setLayout(new java.awt.GridLayout(2, 1));

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

        lbUser.setText("Nombre Promocion:");

        cmbPlatos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbUser1.setText("Plato de Promoción:");

        lbUser2.setText("Porcentaje Descuento:");

        txtPorcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcentajeKeyTyped(evt);
            }
        });

        lbUser3.setText("Descripción Promoción:");

        txaDescripcion.setColumns(20);
        txaDescripcion.setRows(5);
        jScrollPane2.setViewportView(txaDescripcion);

        lbUser4.setText("Platos Minimos:");

        txtPlatosMn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlatosMnKeyTyped(evt);
            }
        });

        btnReiniciarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/reload-icon.png"))); // NOI18N
        btnReiniciarTabla.setToolTipText("Recargar Tabla/Form");
        btnReiniciarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarTablaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReiniciarTabla))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbUser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbUser4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtPlatosMn, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbUser3, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbUser, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                    .addComponent(txtNombre))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbPlatos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbUser1, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUser)
                    .addComponent(lbUser1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPlatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUser2)
                    .addComponent(lbUser3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbUser4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPlatosMn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReiniciarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel2.add(jPanel3);

        tblPromociones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblPromociones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPromocionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPromociones);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        //Ejecutamos el método de busqueda
        if (txtNombre.getText().trim().isBlank()) {
            cargarTabla(controller.obtenerPromociones());
        } else {
            cargarTabla(controller.buscarPromociones(txtNombre.getText()));
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        //Deshabilitamos los botones
        toggleEnableForm();
        //Ejecutamos método
        Plato platoSeleccionado = (Plato) cmbPlatos.getSelectedItem();

        //Validaciones
        if (platoSeleccionado.getIdPlato() == -1 || platoSeleccionado == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe seleccionar un plato");
            toggleEnableForm();
            return;
        } else if (txtPlatosMn.getText().trim().isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe ingresar los platos minimos para aplicar la oferta");
            toggleEnableForm();
            return;
        } else if (txtPorcentaje.getText().trim().isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe ingresar el porcentaje de descuento (Entero)");
            toggleEnableForm();
            return;
        }

        ResultadoOperacion res = controller.addPromo(txtNombre.getText(), txaDescripcion.getText(), platoSeleccionado, Integer.valueOf(txtPorcentaje.getText()), Integer.valueOf(txtPlatosMn.getText()));

        //Evaluamos la respuesta
        if (res.isExito()) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, res.getMensaje());
            cargarTabla(controller.obtenerPromociones());
            reiniciarForm();
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, res.getMensaje());
        }

        toggleEnableForm();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        //Eliminar el tipo plato
        toggleEnableForm();
        int row = tblPromociones.getSelectedRow();
        if (row != -1 && idPromocion > 0) {
            ResultadoOperacion res = controller.delPromo(idPromocion);

            //Evaluamos la respuesta
            if (res.isExito()) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, res.getMensaje());
                cargarTabla(controller.obtenerPromociones());
                reiniciarForm();
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, res.getMensaje());
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe seleccionar una promocion");
        }

        toggleEnableForm();
    }//GEN-LAST:event_btnDelActionPerformed

    private void tblPromocionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPromocionesMouseClicked
        //Llenamos el formulario
        int row = tblPromociones.getSelectedRow();
        txtNombre.setText(tblPromociones.getValueAt(row, 0).toString());
        txaDescripcion.setText(tblPromociones.getValueAt(row, 1).toString());
        txtPlatosMn.setText(tblPromociones.getValueAt(row, 4).toString());
        txtPorcentaje.setText(tblPromociones.getValueAt(row, 3).toString().replace("%", ""));     
        //Obtenemos el modelo de la tabla
        PromocionTableModel model = (PromocionTableModel) tblPromociones.getModel();
        //Seteamos el id del tipo seleccionado
        idPromocion = model.getIdAt(row);
        
        Plato platoSeleccionado = model.getPlatoAt(row);
        cargarPlatos();
        if(platoSeleccionado == null || !platoSeleccionado.isDisponibilidad()){
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "El plato fue eliminado, seleccione uno nuevo");
        } else {
            cmbPlatos.setSelectedItem(platoSeleccionado);
        }
    }//GEN-LAST:event_tblPromocionesMouseClicked

    private void btnModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModActionPerformed
        //Eliminar el tipo plato
        toggleEnableForm();
        int row = tblPromociones.getSelectedRow();
        if (row != -1 && idPromocion > 0) {
            Plato platoSeleccionado = (Plato) cmbPlatos.getSelectedItem();

            //Validaciones
            if (platoSeleccionado.getIdPlato() == -1 || platoSeleccionado == null) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe seleccionar un plato");
                toggleEnableForm();
                return;
            } else if (txtPlatosMn.getText().trim().isBlank()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe ingresar los platos minimos para aplicar la oferta");
                toggleEnableForm();
                return;
            } else if (txtPorcentaje.getText().trim().isBlank()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe ingresar el porcentaje de descuento (Entero)");
                toggleEnableForm();
                return;
            }

            ResultadoOperacion res = controller.modPromo(idPromocion,txtNombre.getText(), txaDescripcion.getText(), platoSeleccionado, Integer.valueOf(txtPorcentaje.getText()), Integer.valueOf(txtPlatosMn.getText()));

            //Evaluamos la respuesta
            if (res.isExito()) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, res.getMensaje());
                cargarTabla(controller.obtenerPromociones());
                reiniciarForm();
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, res.getMensaje());
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe seleccionar una promocion");
        }

        toggleEnableForm();
    }//GEN-LAST:event_btnModActionPerformed

    private void txtPorcentajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeKeyTyped
        //Validamos que solo acepte numewros
        validaciones.soloNumeros(evt, 1, txtPorcentaje.getText());
    }//GEN-LAST:event_txtPorcentajeKeyTyped

    private void txtPlatosMnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlatosMnKeyTyped
        //Validamos que solo acepte numewros
        validaciones.soloNumeros(evt, 1, txtPorcentaje.getText());
    }//GEN-LAST:event_txtPlatosMnKeyTyped

    private void btnReiniciarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarTablaActionPerformed
        reiniciarForm();
        cargarTabla(controller.obtenerPromociones());
    }//GEN-LAST:event_btnReiniciarTablaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnMod;
    private javax.swing.JButton btnReiniciarTabla;
    private javax.swing.JComboBox<String> cmbPlatos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbUser;
    private javax.swing.JLabel lbUser1;
    private javax.swing.JLabel lbUser2;
    private javax.swing.JLabel lbUser3;
    private javax.swing.JLabel lbUser4;
    public static javax.swing.JLabel lblErrorMsg;
    private javax.swing.JPanel pnlCentral;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JTable tblPromociones;
    private javax.swing.JTextArea txaDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPlatosMn;
    private javax.swing.JTextField txtPorcentaje;
    // End of variables declaration//GEN-END:variables
}