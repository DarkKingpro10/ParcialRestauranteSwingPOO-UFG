package raven.application.form.other;

import application.clasess.Pedido;
import application.clasess.Plato;
import application.clasess.Restaurante;
import application.clasess.TipoPlato;
import application.controllers.PedidosController;
import application.enums.EstadoPedido;
import application.tablesModel.InventarioTableModel;
import application.utils.ButtonEditor;
import application.utils.ButtonRenderer;
import application.utils.CentrarColumnas;
import application.utils.ResultadoOperacion;
import application.utils.Validaciones;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import raven.application.Application;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public class FormPedidos extends javax.swing.JPanel {

    //Atributos de la clase
    private int idPedido;
    private int idPlato;
    private double total = 0;
    private int tiempoEstimadoMinutos = 0;
    public static Object estado = "Seleccionar Estado";
    ImageIcon iconDetalle = new ImageIcon(Application.class.getResource("/raven/icon/png/del-small-icon.png"));
    //Importando Validaciones
    Validaciones validaciones = new Validaciones();
    //Importando controlador
    PedidosController controller;

    public FormPedidos() {
        initComponents();
        prepararAdd();
        inicializarCampos();
    }

    public FormPedidos(int id) {
        initComponents();
        this.idPedido = id;
        prepararMod();
        inicializarCampos();
    }

    //Método para inicializar los campos de las tablas
    void inicializarCampos() {
        //Estilos
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");

        lblErrorMsg.setVisible(false);

        txtNombre.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nombre del cliente ...");

        //Validaciones
        JComponent[] componentesAValidar = {txtNombre};
        validaciones.noPegar(componentesAValidar);

        tblPlatosLst.setDefaultRenderer(Object.class, new CentrarColumnas());
        tblPlatosLst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblPlatosAgr.setDefaultRenderer(Object.class, new CentrarColumnas());
        tblPlatosAgr.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Cargamos las tablas
        cargarTablaPlatosLst(controller.obtenerPlatos());
        cargarTablaPlatosAgr(controller.obtenerPlatosAgr());
    }

    //Método para asignar el total a pagar y el tiempo estimado en minutos
    void recalcular() {
        //Obtenemos los platos agregados
        /*ArrayList<HashMap<Plato, Integer>> platos = controller.obtenerPlatosAgr();

        //Los recorremos 
        for (HashMap<Plato, Integer> map : platos) {
            //Al ser un HashMap necesitamos obtener sus llaves tmb por ende los recorremos
            for (Map.Entry<Plato, Integer> entry : map.entrySet()) {
                Plato plato = entry.getKey(); //Creamos un objeto de tipo plato
                int cantidad = entry.getValue();//Obtenemos la cantidad

                //Calculamos el subtotal
                double subTotal = plato.getPrecio() * cantidad;
                double promo = 0.0;

                // Consultar descuento que devuelve un HashMap
                HashMap<String, Double> descuento = Restaurante.consultarDescuento(plato, cantidad);
                //Recorremos el hashMap
                for (Map.Entry<String, Double> desc : descuento.entrySet()) {
                    if (!desc.getKey().isEmpty()) {//Validamos que si halla promoción
                        promo = desc.getValue();//Asignamos el descuento
                    }
                }
                
                //Asignamos valores
                total = Math.round((total + (subTotal - promo)) * 100.0) / 100.0;
                tiempoEstimadoMinutos += plato.getTiempoEstimadoPreparacionMn();
            }
        }*/

        Pedido pedido = controller.obtenerPedido();
        //Mostramos valores
        txtTotal.setText("$" + Math.round((pedido.getTotal()) * 100.0) / 100.0);
        txtTiempoEMn.setText(pedido.getTiempoEntregaEstimado() + " minutos");
    }

    //Método para preparar el form para cuando se deba añadir
    void prepararAdd() {
        controller = new PedidosController();
        lb.setText("Agregar Pedido");
        btnMod.setVisible(false);

        cargarEstados();
    }

    //Método para preparar el formulario cuando se debe modificar
    void prepararMod() {
        controller = new PedidosController(idPedido);
        lb.setText("Modificar Pedido");
        btnAdd.setVisible(false);
        Pedido pedido = controller.obtenerPedido();

        txtNombre.setText(pedido.getCliente());
        tiempoEstimadoMinutos = pedido.getTiempoEntregaEstimado();
        total = pedido.getTotal();

        //Seteamos el combobox
        cargarEstados();
        cmbEstado.setSelectedItem(pedido.getEstadoPedido());

    }

    //Método para inicializar el combobox
    void cargarEstados() {
        cmbEstado.setModel(controller.obtenerEstadosForForm());
    }

    //Método para cargar el listado de los Ingredientes a añadir
    void cargarTablaPlatosLst(DefaultTableModel model) {
        tblPlatosLst.setModel(model);
        tblPlatosLst.getColumnModel().getColumn(0).setMaxWidth(0);
        tblPlatosLst.getColumnModel().getColumn(0).setMinWidth(0);
        tblPlatosLst.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    //Método para cargar la tabla de Ingredientes añadidos
    void cargarTablaPlatosAgr(ArrayList<HashMap<Plato, Integer>> platos) {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID Plato");
        model.addColumn("Nombre Plato");
        model.addColumn("Cantidad");
        model.addColumn("SubTotal");
        model.addColumn("Acción");

        tblPlatosAgr.setModel(model);
        tblPlatosAgr.getColumnModel().getColumn(0).setMaxWidth(0);
        tblPlatosAgr.getColumnModel().getColumn(0).setMinWidth(0);
        tblPlatosAgr.getColumnModel().getColumn(0).setPreferredWidth(0);

        tblPlatosAgr.getColumnModel().getColumn(4).setMaxWidth(150);
        tblPlatosAgr.getColumnModel().getColumn(4).setMinWidth(150);

        for (HashMap<Plato, Integer> map : platos) {
            for (Map.Entry<Plato, Integer> entry : map.entrySet()) {
                Plato plato = entry.getKey();
                int cantidad = entry.getValue();

                double subTotal = plato.getPrecio() * cantidad;
                double promo = 0.0;

                // Añadir fila del plato
                model.addRow(new Object[]{plato.getIdPlato(), plato.getNombrePlato(), cantidad, "$" + subTotal, "Eliminar"});

                // Consultar descuento
                HashMap<String, Double> descuento = Restaurante.consultarDescuento(plato, cantidad);
                for (Map.Entry<String, Double> desc : descuento.entrySet()) {
                    if (desc.getValue() != 0.0) {
                        promo = desc.getValue();
                        model.addRow(new Object[]{"", desc.getKey(), "", desc.getValue(), ""});
                    }
                }
                total = Math.round((total + (subTotal - promo)) * 100.0) / 100.0;
                tiempoEstimadoMinutos += plato.getTiempoEstimadoPreparacionMn();
            }
        }
        tblPlatosAgr.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer(Color.RED, iconDetalle, "Eliminar plato"));
        tblPlatosAgr.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox(), tblPlatosAgr, Color.RED, iconDetalle, "Eliminar plato"));
        recalcular();
    }

    //Método para reiniciar el formulario general
    void reiniciarFormGeneral() {
        cargarEstados();
        txtNombre.setText("");
        txtTotal.setText("");
        txtTiempoEMn.setText("");
        idPlato = 0;
    }

    //Método para reiniciar el formulario de agregar ingrediente
    void reiniciarFormPA() {
        idPlato = 0;
        txtCantidadLst.setText("");
        txtPlatosLst.setText("");
    }

    //Método para habilitar e inhabilitar el formulario
    void toggleEnableForm() {
        txtNombre.setEnabled(!txtNombre.isEnabled());
        txtTotal.setEnabled(!txtTotal.isEnabled());
        txtTiempoEMn.setEnabled(!txtTiempoEMn.isEnabled());
        cmbEstado.setEnabled(!cmbEstado.isEnabled());
        btnAdd.setEnabled(!btnAdd.isEnabled());
        btnMod.setEnabled(!btnMod.isEnabled());
    }

    //Método para regresar al anterior form
    void regresarAIndex() {
        Application.showForm(new IndexPedidos());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lb = new javax.swing.JLabel();
        pnlContenedor = new javax.swing.JPanel();
        lblErrorMsg = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        lbUser = new javax.swing.JLabel();
        lbUser3 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        lbUser4 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        lbUser5 = new javax.swing.JLabel();
        txtTiempoEMn = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnBuscarILst = new javax.swing.JButton();
        txtPlatosLst = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPlatosLst = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtCantidadLst = new javax.swing.JTextField();
        btnAddPlt = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnBuscarPAgr = new javax.swing.JButton();
        txtIngredientesAgr = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPlatosAgr = new javax.swing.JTable();
        pnlFooter = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnMod = new javax.swing.JButton();

        jPanel1.setLayout(new java.awt.BorderLayout());

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Inventario");
        jPanel1.add(lb, java.awt.BorderLayout.PAGE_START);

        pnlContenedor.setToolTipText("Regresar al listado");

        lblErrorMsg.setForeground(javax.swing.UIManager.getDefaults().getColor("Component.custom.borderColor"));
        lblErrorMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblErrorMsg.setText("No hay productos/ingredientes añadidos");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        lbUser.setText("Nombre Cliente:");

        lbUser3.setText("Descripción del Plato:");
        lbUser3.setToolTipText("");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un Tipo de Plato" }));

        lbUser4.setText("Estado Pedido");

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(204, 0, 51));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setText("$150.5");
        txtTotal.setFocusable(false);

        lbUser5.setText("Tiempo estimado de preparación:");
        lbUser5.setToolTipText("");

        txtTiempoEMn.setEditable(false);
        txtTiempoEMn.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        txtTiempoEMn.setForeground(new java.awt.Color(255, 255, 0));
        txtTiempoEMn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTiempoEMn.setText("15MINUTOS");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(lbUser3, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(txtTotal))
                .addGap(184, 184, 184)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUser4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbUser5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTiempoEMn, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbUser4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbUser5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTiempoEMn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbUser3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/regresar-icon.png"))); // NOI18N
        jLabel1.setToolTipText("Regresar al listado de platos");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jPanel5.setLayout(new java.awt.GridLayout(1, 2));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Listado de Platos a añadir");

        btnBuscarILst.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        btnBuscarILst.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscarILst.setForeground(new java.awt.Color(0, 0, 0));
        btnBuscarILst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/search-icon.png"))); // NOI18N
        btnBuscarILst.setText("Buscar");
        btnBuscarILst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarILstActionPerformed(evt);
            }
        });

        txtPlatosLst.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlatosLstKeyTyped(evt);
            }
        });

        jLabel5.setText("Platos a buscar/Añadir:");

        tblPlatosLst.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre Plato"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPlatosLst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPlatosLstMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblPlatosLst);

        jLabel6.setText("Cantidad a Agregar:");

        txtCantidadLst.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadLstKeyTyped(evt);
            }
        });

        btnAddPlt.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        btnAddPlt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddPlt.setForeground(new java.awt.Color(0, 0, 0));
        btnAddPlt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/add-small-icon.png"))); // NOI18N
        btnAddPlt.setText("Agregar");
        btnAddPlt.setToolTipText("Agregar Ingrediente al plato");
        btnAddPlt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPltActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPlatosLst)
                            .addComponent(txtCantidadLst, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarILst)
                            .addComponent(btnAddPlt))))
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuscarILst)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(7, 7, 7)
                        .addComponent(txtPlatosLst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addComponent(jLabel6)
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidadLst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddPlt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel8);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Listado de Platos añadidos");

        btnBuscarPAgr.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        btnBuscarPAgr.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscarPAgr.setForeground(new java.awt.Color(0, 0, 0));
        btnBuscarPAgr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/search-icon.png"))); // NOI18N
        btnBuscarPAgr.setText("Buscar");
        btnBuscarPAgr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPAgrActionPerformed(evt);
            }
        });

        txtIngredientesAgr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIngredientesAgrKeyTyped(evt);
            }
        });

        jLabel4.setText("Ingrediente a buscar:");

        tblPlatosAgr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPlatosAgr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPlatosAgrMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPlatosAgr);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtIngredientesAgr, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarPAgr)))
                .addGap(50, 50, 50))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel2)
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuscarPAgr)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(7, 7, 7)
                        .addComponent(txtIngredientesAgr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel7);

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

        btnMod.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Yellow"));
        btnMod.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMod.setForeground(new java.awt.Color(0, 0, 0));
        btnMod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/mod-icon.png"))); // NOI18N
        btnMod.setText(" Modificar ");
        btnMod.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFooterLayout = new javax.swing.GroupLayout(pnlFooter);
        pnlFooter.setLayout(pnlFooterLayout);
        pnlFooterLayout.setHorizontalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFooterLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMod, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFooterLayout.setVerticalGroup(
            pnlFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFooterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMod, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblErrorMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlFooter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblErrorMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(25, 25, 25)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlFooter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(pnlContenedor, java.awt.BorderLayout.CENTER);

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

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        //Validamos que solo acepte letras
        validaciones.soloLetras(evt, 2, txtNombre.getText());
    }//GEN-LAST:event_txtNombreKeyTyped

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        //Deshabilitamos los botones
        toggleEnableForm();
        if (txtNombre.getText().trim().isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe ingresar el cliente");
            toggleEnableForm();
            return;
        }

        //Como cumplio las validaciones entonces ejecutamos la operación
        ResultadoOperacion res = controller.add(txtNombre.getText().trim(), total, tiempoEstimadoMinutos);

        if (res.isExito()) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, res.getMensaje());
            reiniciarFormGeneral();
            regresarAIndex();
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, res.getMensaje());
        }
        toggleEnableForm();
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtIngredientesAgrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIngredientesAgrKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIngredientesAgrKeyTyped

    private void txtPlatosLstKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlatosLstKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlatosLstKeyTyped

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        regresarAIndex();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnBuscarILstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarILstActionPerformed
        //Ejecutamos el método de busqueda
        if (txtPlatosLst.getText().trim().isBlank()) {
            cargarTablaPlatosLst(controller.obtenerPlatos());
        } else {
            cargarTablaPlatosLst(controller.buscarPlatos(txtPlatosLst.getText()));
        }
    }//GEN-LAST:event_btnBuscarILstActionPerformed

    private void txtCantidadLstKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadLstKeyTyped
        validaciones.soloNumeros(evt, 1, txtCantidadLst.getText());
    }//GEN-LAST:event_txtCantidadLstKeyTyped

    private void btnAddPltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPltActionPerformed
        try {
            //Evento para agregar un ingrediente al plato
            int row = tblPlatosLst.getSelectedRow();
            if (row < 0 && idPlato <= 0) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe seleccionar un plato para añadir");
                return;
            } else if (txtCantidadLst.getText().trim().isBlank()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe ingresar la cantidad del plato que ordenará");
                return;
            }

            int cantidad = Integer.valueOf(txtCantidadLst.getText().trim());

            ResultadoOperacion res = controller.addPlato(idPlato, cantidad);

            if (res.isExito()) {
                cargarTablaPlatosAgr(controller.obtenerPlatosAgr());
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, res.getMensaje());
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, res.getMensaje());
            }

            reiniciarFormPA();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnAddPltActionPerformed

    private void tblPlatosLstMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPlatosLstMouseClicked
        try {
            //Evento para seleccionar el Ingrediente a agregar
            int row = tblPlatosLst.getSelectedRow();

            txtPlatosLst.setText(tblPlatosLst.getValueAt(row, 1).toString());

            idPlato = Integer.valueOf(tblPlatosLst.getValueAt(row, 0).toString());
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_tblPlatosLstMouseClicked

    private void tblPlatosAgrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPlatosAgrMouseClicked
        //Controlamos si da click para eliminar el producto
        int row = tblPlatosAgr.getSelectedRow();
        int column = tblPlatosAgr.getSelectedColumn();

        if (column == 4) {
            int idPlatoAEliminar = Integer.valueOf(tblPlatosAgr.getValueAt(row, 0).toString());
            int cantidad = Integer.valueOf(tblPlatosAgr.getValueAt(row, 2).toString());
            ResultadoOperacion res = controller.delPlato(idPlatoAEliminar, cantidad);

            if (res.isExito()) {
                cargarTablaPlatosAgr(controller.obtenerPlatosAgr());
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, res.getMensaje());
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, res.getMensaje());
            }
        }
    }//GEN-LAST:event_tblPlatosAgrMouseClicked

    private void btnBuscarPAgrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPAgrActionPerformed
        //Ejecutamos el método de busqueda para los platos añadidos
        if (txtIngredientesAgr.getText().trim().isBlank()) {
            cargarTablaPlatosAgr(controller.obtenerPlatosAgr());
        } else {
            cargarTablaPlatosAgr(controller.buscarPlatosAgr(txtIngredientesAgr.getText()));
        }
    }//GEN-LAST:event_btnBuscarPAgrActionPerformed

    private void btnModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModActionPerformed
        //Deshabilitamos los botones
        toggleEnableForm();
        Object estadoSeleccionado = cmbEstado.getSelectedItem();
        if (estadoSeleccionado instanceof String) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe seleccionar un estado del pedido");
            toggleEnableForm();
            return;
        } else if (txtNombre.getText().trim().isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe ingresar el nombre del cliente");
            toggleEnableForm();
            return;
        }

        //Como cumplio las validaciones entonces ejecutamos la operación
        EstadoPedido estado = (EstadoPedido) estadoSeleccionado;

        ResultadoOperacion res = controller.mod(idPedido, txtNombre.getText().trim(), total, tiempoEstimadoMinutos, estado);

        if (res.isExito()) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, res.getMensaje());
            reiniciarFormGeneral();

            if (estado == EstadoPedido.PAGADO) {
                controller.facturarPedido(idPedido);
            }

            regresarAIndex();
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, res.getMensaje());
        }
        toggleEnableForm();
    }//GEN-LAST:event_btnModActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddPlt;
    private javax.swing.JButton btnBuscarILst;
    private javax.swing.JButton btnBuscarPAgr;
    private javax.swing.JButton btnMod;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbUser;
    private javax.swing.JLabel lbUser3;
    private javax.swing.JLabel lbUser4;
    private javax.swing.JLabel lbUser5;
    public static javax.swing.JLabel lblErrorMsg;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JTable tblPlatosAgr;
    private javax.swing.JTable tblPlatosLst;
    private javax.swing.JTextField txtCantidadLst;
    private javax.swing.JTextField txtIngredientesAgr;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPlatosLst;
    private javax.swing.JTextField txtTiempoEMn;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
