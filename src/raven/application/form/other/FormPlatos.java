package raven.application.form.other;

import application.clasess.Plato;
import application.clasess.Producto;
import application.clasess.TipoPlato;
import application.controllers.PlatosController;
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
public class FormPlatos extends javax.swing.JPanel {

    //Atributos de la clase
    private int idPlato;
    private int idProducto;
    ImageIcon iconDetalle = new ImageIcon(Application.class.getResource("/raven/icon/png/del-small-icon.png"));
    //Importando Validaciones
    Validaciones validaciones = new Validaciones();
    //Importando controlador
    PlatosController controller;

    public FormPlatos() {
        initComponents();
        prepararAdd();
        inicializarCampos();
    }

    public FormPlatos(int id) {
        initComponents();
        this.idPlato = id;
        prepararMod();
        inicializarCampos();
    }

    //Método para inicializar los campos de las tablas
    void inicializarCampos() {
        //Estilos
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");

        lblErrorMsg.setVisible(false);

        txtNombre.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Plato tipico ...");
        txtTiempoEMn.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "15...");
        txtPrecio.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "7.50...");
        txaDescripcion.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Plato tipico salvadoreño que incluye platanos ...");

        //Validaciones
        JComponent[] componentesAValidar = {txtNombre, txtPrecio, txtTiempoEMn};
        validaciones.noPegar(componentesAValidar);

        tblIngredientesLst.setDefaultRenderer(Object.class, new CentrarColumnas());
        tblIngredientesLst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblIngredientesAgr.setDefaultRenderer(Object.class, new CentrarColumnas());
        tblIngredientesAgr.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Cargamos las tablas
        cargarTablaIngredientesLst(controller.obtenerIngredientes());
        cargarTablaIngredientesAgr(controller.obtenerIngredientesAgr());
    }

    //Método para preparar el form para cuando se deba añadir
    void prepararAdd() {
        controller = new PlatosController();
        lb.setText("Agregar Plato");
        btnMod.setVisible(false);
        cargarTipoPlatos();
    }

    //Método para preparar el formulario cuando se debe modificar
    void prepararMod() {
        controller = new PlatosController(idPlato);
        lb.setText("Modificar Plato");
        btnAdd.setVisible(false);
        Plato plato = controller.obtenerPlato();

        txtNombre.setText(plato.getNombrePlato());
        txtTiempoEMn.setText(String.valueOf(plato.getTiempoEstimadoPreparacionMn()));
        txtPrecio.setText(String.valueOf(plato.getPrecio()));
        txaDescripcion.setText(plato.getDescripcion());

        //Seteamos el combobox
        cargarTipoPlatos();
        if (!plato.getTipoPlato().isDisponibilidad()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "El tipo plato fue eliminado, seleccione uno nuevo");
        } else {
            cmbTipoPlato.setSelectedItem(plato.getTipoPlato());
        }

    }

    //Método para inicializar el combobox
    void cargarTipoPlatos() {
        cmbTipoPlato.setModel(controller.obtenerTiposPlatos());
    }

    //Método para cargar el listado de los Ingredientes a añadir
    void cargarTablaIngredientesLst(InventarioTableModel model) {
        tblIngredientesLst.setModel(model);
        tblIngredientesLst.getColumnModel().getColumn(1).setMaxWidth(0);
        tblIngredientesLst.getColumnModel().getColumn(1).setMinWidth(0);
        tblIngredientesLst.getColumnModel().getColumn(1).setPreferredWidth(0);
    }

    //Método para cargar la tabla de Ingredientes añadidos
    void cargarTablaIngredientesAgr(DefaultTableModel model) {
        tblIngredientesAgr.setModel(model);
        tblIngredientesAgr.getColumnModel().getColumn(0).setMaxWidth(0);
        tblIngredientesAgr.getColumnModel().getColumn(0).setMinWidth(0);
        tblIngredientesAgr.getColumnModel().getColumn(0).setPreferredWidth(0);

        tblIngredientesAgr.getColumnModel().getColumn(3).setMaxWidth(100);
        tblIngredientesAgr.getColumnModel().getColumn(3).setMinWidth(100);

        tblIngredientesAgr.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer(Color.RED, iconDetalle, "Eliminar ingrediente"));
        tblIngredientesAgr.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox(), tblIngredientesLst, Color.RED, iconDetalle, "Eliminar ingrediente"));
    }

    //Método para reiniciar el formulario general
    void reiniciarFormGeneral() {
        cargarTipoPlatos();
        txtNombre.setText("");
        txtPrecio.setText("");
        txtTiempoEMn.setText("");
        txaDescripcion.setText("");
        idProducto = 0;
    }

    //Método para reiniciar el formulario de agregar ingrediente
    void reiniciarFormPA() {
        idProducto = 0;
        txtCantidadLst.setText("");
        txtIngredientesLst.setText("");
    }

    //Método para habilitar e inhabilitar el formulario
    void toggleEnableForm() {
        txtNombre.setEnabled(!txtNombre.isEnabled());
        txtPrecio.setEnabled(!txtPrecio.isEnabled());
        txtTiempoEMn.setEnabled(!txtTiempoEMn.isEnabled());
        txaDescripcion.setEnabled(!txaDescripcion.isEnabled());
        btnAdd.setEnabled(!btnAdd.isEnabled());
        btnMod.setEnabled(!btnMod.isEnabled());
    }

    //Método para regresar al anterior form
    void regresarAIndex() {
        Application.showForm(new IndexPlatos());
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
        lbUser1 = new javax.swing.JLabel();
        txtTiempoEMn = new javax.swing.JTextField();
        lbUser2 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaDescripcion = new javax.swing.JTextArea();
        lbUser3 = new javax.swing.JLabel();
        cmbTipoPlato = new javax.swing.JComboBox<>();
        lbUser4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnBuscarILst = new javax.swing.JButton();
        txtIngredientesLst = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblIngredientesLst = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtCantidadLst = new javax.swing.JTextField();
        btnAddIngr = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnBuscarIAgr = new javax.swing.JButton();
        txtIngredientesAgr = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblIngredientesAgr = new javax.swing.JTable();
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

        lbUser.setText("Nombre Plato:");

        lbUser1.setText("Tiempo Preparación Estimado (En minutos):");

        txtTiempoEMn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTiempoEMnKeyTyped(evt);
            }
        });

        lbUser2.setText("Precio ($) Plato:");

        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });

        txaDescripcion.setColumns(20);
        txaDescripcion.setRows(5);
        jScrollPane1.setViewportView(txaDescripcion);

        lbUser3.setText("Descripción del Plato:");
        lbUser3.setToolTipText("");

        cmbTipoPlato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un Tipo de Plato" }));

        lbUser4.setText("Tipo Plato:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(txtNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbUser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTiempoEMn, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbUser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbUser3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTipoPlato, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUser4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbUser)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbUser2)
                        .addGap(18, 18, 18)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(lbUser1)
                        .addGap(18, 18, 18)
                        .addComponent(txtTiempoEMn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUser3)
                    .addComponent(lbUser4))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoPlato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        jLabel3.setText("Listado de Ingredientes a añadir");

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

        txtIngredientesLst.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIngredientesLstKeyTyped(evt);
            }
        });

        jLabel5.setText("Ingrediente a buscar/Añadir:");

        tblIngredientesLst.setModel(new javax.swing.table.DefaultTableModel(
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
        tblIngredientesLst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblIngredientesLstMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblIngredientesLst);

        jLabel6.setText("Cantidad a Agregar:");

        txtCantidadLst.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadLstKeyTyped(evt);
            }
        });

        btnAddIngr.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        btnAddIngr.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddIngr.setForeground(new java.awt.Color(0, 0, 0));
        btnAddIngr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/add-small-icon.png"))); // NOI18N
        btnAddIngr.setText("Agregar");
        btnAddIngr.setToolTipText("Agregar Ingrediente al plato");
        btnAddIngr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddIngrActionPerformed(evt);
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
                            .addComponent(txtIngredientesLst)
                            .addComponent(txtCantidadLst, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarILst)
                            .addComponent(btnAddIngr))))
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(121, 121, 121))
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
                        .addComponent(txtIngredientesLst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addComponent(jLabel6)
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidadLst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddIngr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel8);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Listado de Ingredientes añadidos");

        btnBuscarIAgr.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        btnBuscarIAgr.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscarIAgr.setForeground(new java.awt.Color(0, 0, 0));
        btnBuscarIAgr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/icon/png/search-icon.png"))); // NOI18N
        btnBuscarIAgr.setText("Buscar");
        btnBuscarIAgr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarIAgrActionPerformed(evt);
            }
        });

        txtIngredientesAgr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIngredientesAgrKeyTyped(evt);
            }
        });

        jLabel4.setText("Ingrediente a buscar:");

        tblIngredientesAgr.setModel(new javax.swing.table.DefaultTableModel(
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
        tblIngredientesAgr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblIngredientesAgrMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblIngredientesAgr);

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
                        .addComponent(btnBuscarIAgr)))
                .addGap(50, 50, 50))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel2)
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuscarIAgr)
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

    private void txtTiempoEMnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTiempoEMnKeyTyped
        //Validamos que solo acepte numewros
        validaciones.soloNumeros(evt, 1, txtTiempoEMn.getText());
    }//GEN-LAST:event_txtTiempoEMnKeyTyped

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        //Deshabilitamos los botones
        toggleEnableForm();
        TipoPlato tipoSeleccionado = (TipoPlato) cmbTipoPlato.getSelectedItem();
        if (tipoSeleccionado.getIdTipoPlato() == -1 || tipoSeleccionado == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe seleccionar un tipo de plato");
            toggleEnableForm();
            return;
        } else if (txtTiempoEMn.getText().trim().isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe ingresar el tiempo de realización estimado");
            toggleEnableForm();
            return;
        } else if (txtPrecio.getText().trim().isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "El precio por el restaurante, debe ser mayor o igual 0");
            toggleEnableForm();
            return;
        }

        double precio = Double.valueOf(txtPrecio.getText().trim());
        int tiempo = Integer.valueOf(txtTiempoEMn.getText().trim());

        //Como cumplio las validaciones entonces ejecutamos la operación
        ResultadoOperacion res = controller.add(txtNombre.getText().trim(), txaDescripcion.getText().trim(), precio, tiempo, tipoSeleccionado);

        if (res.isExito()) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, res.getMensaje());
            reiniciarFormGeneral();
            regresarAIndex();
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, res.getMensaje());
        }
        toggleEnableForm();
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        //Validamos que solo acepte numero y el punto
        validaciones.soloNumeros(evt, 5, txtPrecio.getText());
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void txtIngredientesAgrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIngredientesAgrKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIngredientesAgrKeyTyped

    private void txtIngredientesLstKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIngredientesLstKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIngredientesLstKeyTyped

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        regresarAIndex();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnBuscarILstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarILstActionPerformed
        //Ejecutamos el método de busqueda
        if (txtIngredientesLst.getText().trim().isBlank()) {
            cargarTablaIngredientesLst(controller.obtenerIngredientes());
        } else {
            cargarTablaIngredientesLst(controller.buscarProductos(txtIngredientesLst.getText()));
        }
    }//GEN-LAST:event_btnBuscarILstActionPerformed

    private void txtCantidadLstKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadLstKeyTyped
        validaciones.soloNumeros(evt, 1, txtPrecio.getText());
    }//GEN-LAST:event_txtCantidadLstKeyTyped

    private void btnAddIngrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddIngrActionPerformed
        //Evento para agregar un ingrediente al plato
        int row = tblIngredientesLst.getSelectedRow();
        if (row < 0 && idProducto <= 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe seleccionar un Ingredeiente para añadir");
            return;
        } else if (txtCantidadLst.getText().trim().isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe ingresar la cantidad del ingrediente que usará el plato");
            return;
        } else if(txtCantidadLst.getText().trim().isBlank()){
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Vuelva a seleccionar el plato");
            return;
        }

        int cantidad = Integer.valueOf(txtCantidadLst.getText().trim());

        ResultadoOperacion res = controller.addIngrediente(idProducto, cantidad);

        if (res.isExito()) {
            cargarTablaIngredientesAgr(controller.obtenerIngredientesAgr());
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, res.getMensaje());
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, res.getMensaje());
        }

        reiniciarFormPA();
    }//GEN-LAST:event_btnAddIngrActionPerformed

    private void tblIngredientesLstMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblIngredientesLstMouseClicked
        //Evento para seleccionar el Ingrediente a agregar
        int row = tblIngredientesLst.getSelectedRow();

        txtIngredientesLst.setText(tblIngredientesLst.getValueAt(row, 0).toString());

        //Obtenemos el modelo de la tabla
        InventarioTableModel model = (InventarioTableModel) tblIngredientesLst.getModel();
        idProducto = model.getIdAt(row);
    }//GEN-LAST:event_tblIngredientesLstMouseClicked

    private void tblIngredientesAgrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblIngredientesAgrMouseClicked
        //Controlamos si da click para eliminar el producto
        int row = tblIngredientesAgr.getSelectedRow();
        int column = tblIngredientesAgr.getSelectedColumn();

        if (column == 3) {
            int idPlatoAEliminar = Integer.valueOf(tblIngredientesAgr.getValueAt(row, 0).toString());
            ResultadoOperacion res = controller.delIngrediente(idPlatoAEliminar);

            if (res.isExito()) {
                cargarTablaIngredientesAgr(controller.obtenerIngredientesAgr());
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, res.getMensaje());
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, res.getMensaje());
            }
        }
    }//GEN-LAST:event_tblIngredientesAgrMouseClicked

    private void btnBuscarIAgrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarIAgrActionPerformed
        //Ejecutamos el método de busqueda para los ingredientes añadidos
        if (txtIngredientesAgr.getText().trim().isBlank()) {
            cargarTablaIngredientesAgr(controller.obtenerIngredientesAgr());
        } else {
            cargarTablaIngredientesAgr(controller.buscarIngredientes(txtIngredientesAgr.getText()));
        }
    }//GEN-LAST:event_btnBuscarIAgrActionPerformed

    private void btnModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModActionPerformed
        //Deshabilitamos los botones
        toggleEnableForm();
        TipoPlato tipoSeleccionado = (TipoPlato) cmbTipoPlato.getSelectedItem();
        if (tipoSeleccionado.getIdTipoPlato() == -1 || tipoSeleccionado == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe seleccionar un tipo de plato");
            toggleEnableForm();
            return;
        } else if (txtTiempoEMn.getText().trim().isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Debe ingresar el tiempo de realización estimado");
            toggleEnableForm();
            return;
        } else if (txtPrecio.getText().trim().isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "El precio por el restaurante, debe ser mayor o igual 0");
            toggleEnableForm();
            return;
        }

        double precio = Double.valueOf(txtPrecio.getText().trim());
        int tiempo = Integer.valueOf(txtTiempoEMn.getText().trim());

        //Como cumplio las validaciones entonces ejecutamos la operación
        ResultadoOperacion res = controller.mod(txtNombre.getText().trim(), txaDescripcion.getText().trim(), precio, tiempo, tipoSeleccionado);

        if (res.isExito()) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, res.getMensaje());
            reiniciarFormGeneral();
            regresarAIndex();
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, res.getMensaje());
        }
        toggleEnableForm();
    }//GEN-LAST:event_btnModActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddIngr;
    private javax.swing.JButton btnBuscarIAgr;
    private javax.swing.JButton btnBuscarILst;
    private javax.swing.JButton btnMod;
    private javax.swing.JComboBox<String> cmbTipoPlato;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbUser;
    private javax.swing.JLabel lbUser1;
    private javax.swing.JLabel lbUser2;
    private javax.swing.JLabel lbUser3;
    private javax.swing.JLabel lbUser4;
    public static javax.swing.JLabel lblErrorMsg;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JTable tblIngredientesAgr;
    private javax.swing.JTable tblIngredientesLst;
    private javax.swing.JTextArea txaDescripcion;
    private javax.swing.JTextField txtCantidadLst;
    private javax.swing.JTextField txtIngredientesAgr;
    private javax.swing.JTextField txtIngredientesLst;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtTiempoEMn;
    // End of variables declaration//GEN-END:variables
}
