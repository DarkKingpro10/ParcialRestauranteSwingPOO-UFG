package raven.application.form.other;

import application.clasess.Pedido;
import application.clasess.Plato;
import application.controllers.PedidosController;
import application.enums.EstadoPedido;
import application.tablesModel.PedidoTableModel;
import application.utils.ButtonEditor;
import application.utils.CentrarColumnas;
import application.utils.ButtonRenderer;
import application.utils.ResultadoOperacion;
import application.utils.Validaciones;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import raven.application.Application;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public class IndexPedidos extends javax.swing.JPanel {

    //Atributos de la clase
    PedidosController controller = new PedidosController();
    int idPedido;
    Pedido pedidoSeleccionado;
    private JTable tablaDetalle;
    ImageIcon iconDetalle = new ImageIcon(Application.class.getResource("/raven/icon/png/view-icon.png"));
    public static Object estado = "Todo";
    Validaciones validaciones = new Validaciones();
    
    public IndexPedidos() {
        initComponents();
        inicializarCampos();
        cargarTabla(controller.obtenerPedidos());
        cargarEstados();
    }

    //Método para inicializarCampos
    void inicializarCampos() {
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        tblPedidos.setDefaultRenderer(Object.class, new CentrarColumnas());
        lblErrorMsg.setVisible(false);
        txtBusqueda.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese el #pedido, cliente o fecha para buscar...");
    }

    //Método para cargar la tabla
    void cargarTabla(PedidoTableModel model) {
        tblPedidos.setModel(model);
        //Estilizamos
        tblPedidos.getColumnModel().getColumn(6).setMaxWidth(100);
        tblPedidos.getColumnModel().getColumn(6).setMinWidth(100);
        tblPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Añadimos los botones de detalle
        tblPedidos.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer(Color.BLACK, iconDetalle, "Visualizar Detalle del pedido"));
        tblPedidos.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox(), tblPedidos, Color.BLACK, iconDetalle, "Visualizar Detalle del pedido"));
    }

    void cargarEstados() {
        cmbEstadoF.setModel(controller.obtenerEstados());
    }

    //Método pra reiniciar el formulario
    void reiniciarForm() {
        txtBusqueda.setText("");
        idPedido = 0;
        pedidoSeleccionado = null;
        cmbEstadoF.setSelectedItem("Todos");
    }

    //Método para mostrar el detalle del pedido
    void mostrarDetalle() {
        // Creamos el modelo de la tabla
        DefaultTableModel modeloDetalle = new DefaultTableModel();
        modeloDetalle.addColumn("Plato");
        modeloDetalle.addColumn("Cantidad");
        modeloDetalle.addColumn("Precio");
        modeloDetalle.addColumn("SubTotal");

        // Llenamos el modelo de la tabla
        ArrayList<HashMap<Plato, Integer>> platos = pedidoSeleccionado.getPlatos();
        double total = 0.0;

        for (HashMap<Plato, Integer> pedido : platos) {
            for (Map.Entry<Plato, Integer> entry : pedido.entrySet()) {
                Plato plato = entry.getKey();
                Integer cantidad = entry.getValue();
                double subtotal = plato.getPrecio() * cantidad;
                total += subtotal;
                modeloDetalle.addRow(new Object[]{plato.getNombrePlato(), cantidad, plato.getPrecio(), subtotal});
            }
        }

        // Añadir la fila de Total al final
        modeloDetalle.addRow(new Object[]{"", "", "Total", total});

        // Crear y mostrar la tabla de detalle en un diálogo
        tablaDetalle = new JTable(modeloDetalle);
        tablaDetalle.setDefaultRenderer(Object.class, new CentrarColumnas());

        // Centrar la columna del "Total"
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablaDetalle.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(tablaDetalle);
        JOptionPane.showMessageDialog(null, scrollPane, "Detalle del pedido: " + pedidoSeleccionado.getIdPedido(), JOptionPane.PLAIN_MESSAGE);
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
        tblPedidos = new javax.swing.JTable();
        btnReiniciarTabla = new javax.swing.JButton();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lbUser = new javax.swing.JLabel();
        lblErrorMsg = new javax.swing.JLabel();
        cmbEstadoF = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setLayout(new java.awt.BorderLayout());

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Listado de Pedidos");
        jPanel1.add(lb, java.awt.BorderLayout.PAGE_START);

        btnDel.setBackground(new java.awt.Color(0, 255, 153));
        btnDel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDel.setForeground(new java.awt.Color(0, 0, 0));
        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/pagar-icon.png"))); // NOI18N
        btnDel.setText("Finalizar");
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

        tblPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPedidosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPedidos);

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

        lbUser.setText("Ingrese el pedido a buscar:");

        lblErrorMsg.setForeground(javax.swing.UIManager.getDefaults().getColor("Component.custom.borderColor"));
        lblErrorMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblErrorMsg.setText("No hay Pedidos añadidos");

        cmbEstadoF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "Preparandose", "Preparado", "Pagado" }));
        cmbEstadoF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoFActionPerformed(evt);
            }
        });

        jLabel1.setText("Filtar por estado:");

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
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cmbEstadoF, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscar)
                .addGap(26, 26, 26))
        );
        pnlCentralLayout.setVerticalGroup(
            pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCentralLayout.createSequentialGroup()
                .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCentralLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnReiniciarTabla))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCentralLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnBuscar)
                                .addComponent(lblErrorMsg))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCentralLayout.createSequentialGroup()
                                .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbUser)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbEstadoF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
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
        //Consulatomos si desea continuar
        if(!validaciones.confirmarAccion("¿Está seguro que dar por finalizado este pedido?", "Confirmar modificación del pedido #"+idPedido)){
            return;
        }
        
        int row = tblPedidos.getSelectedRow();
        System.out.println(row + " " + idPedido);
        if (row != -1 && idPedido > 0) {
            ResultadoOperacion res = controller.facturarPedido(idPedido);

            if (res.isExito()) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, res.getMensaje());
                cargarTabla(controller.obtenerPedidos());
                reiniciarForm();
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, res.getMensaje());
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe seleccionar un pedido");
        }

        toggleEnableForm();
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModActionPerformed
        int row = tblPedidos.getSelectedRow();
        EstadoPedido estado = pedidoSeleccionado.getEstadoPedido();
        if (row != -1 && idPedido > 0) {
            if(estado != EstadoPedido.PAGADO){
                Application.showForm(new FormPedidos(idPedido));
            }else{
               Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "No puede modificar un pedido ya pagado"); 
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe seleccionar un plato");
        }
    }//GEN-LAST:event_btnModActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Application.showForm(new FormPedidos());
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidosMouseClicked
        int row = tblPedidos.getSelectedRow();
        int column = tblPedidos.getSelectedColumn();
        PedidoTableModel model = (PedidoTableModel) tblPedidos.getModel();
        //Seteamos el id seleccionado y el Plato seleccionado
        idPedido = model.getIdAt(row);
        pedidoSeleccionado = model.getPedidoAt(row);
        if (column == 6) {
            mostrarDetalle();
        }
    }//GEN-LAST:event_tblPedidosMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (txtBusqueda.getText().trim().isBlank()) {
            cargarTabla(controller.obtenerPedidos());
        } else {
            cargarTabla(controller.buscarPedidos(txtBusqueda.getText()));
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnReiniciarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarTablaActionPerformed
        reiniciarForm();
        cargarTabla(controller.obtenerPedidos());
    }//GEN-LAST:event_btnReiniciarTablaActionPerformed

    private void cmbEstadoFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoFActionPerformed
        // Obtener el elemento seleccionado
        estado = cmbEstadoF.getSelectedItem();
        System.out.print(estado);
    }//GEN-LAST:event_cmbEstadoFActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnMod;
    private javax.swing.JButton btnReiniciarTabla;
    private javax.swing.JComboBox<String> cmbEstadoF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbUser;
    public static javax.swing.JLabel lblErrorMsg;
    private javax.swing.JPanel pnlCentral;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JTable tblPedidos;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
