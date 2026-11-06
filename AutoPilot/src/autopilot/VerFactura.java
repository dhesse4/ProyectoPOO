/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package autopilot;

/**
 *
 * @author MarÍa Cajas
 */
public class VerFactura extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VerFactura.class.getName());
    private Factura facturaActual;
    /**
     * Creates new form VerFactura
     */
    public VerFactura() {
        initComponents();
        setDefaultCloseOperation(
        javax.swing.WindowConstants.DISPOSE_ON_CLOSE
    );
        setLocationRelativeTo(null);

    jTable1.setModel(new javax.swing.table.DefaultTableModel(
        
            new Object[][]{},
        new String[]{
            "Descripción",
            "Cantidad",
            "Precio unitario",
            "Importe"
        }
            
            
    ) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    });
    
    estilizarTabla();
    ajustarColumnas();
    mejorarTabla();
    
    
    jTable1.setAutoResizeMode(
        javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN
    );
        jTable1.setFillsViewportHeight(true);

        javax.swing.table.TableColumnModel columnas =
                jTable1.getColumnModel();

        columnas.getColumn(0).setPreferredWidth(420); // Descripción
        columnas.getColumn(1).setPreferredWidth(100); // Cantidad
        columnas.getColumn(2).setPreferredWidth(180); // Precio unitario
        columnas.getColumn(3).setPreferredWidth(180); // Importe

        jScrollPane1.getViewport().setBackground(java.awt.Color.WHITE);
    }
    
    public VerFactura(Factura factura) {
    this();
    this.facturaActual = factura;
    mostrarFactura(factura);
}
    
    private void ajustarColumnas() {
    jTable1.setAutoResizeMode(
            javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN
    );

    javax.swing.table.TableColumnModel columnas =
            jTable1.getColumnModel();

    columnas.getColumn(0).setMinWidth(350);
    columnas.getColumn(0).setPreferredWidth(380);

    columnas.getColumn(1).setMinWidth(90);
    columnas.getColumn(1).setPreferredWidth(100);

    columnas.getColumn(2).setMinWidth(170);
    columnas.getColumn(2).setPreferredWidth(190);

    columnas.getColumn(3).setMinWidth(170);
    columnas.getColumn(3).setPreferredWidth(190);
}
    
    private void mejorarTabla() {
    jTable1.setShowGrid(true);
    jTable1.setGridColor(new java.awt.Color(190, 200, 215));
    jTable1.setIntercellSpacing(new java.awt.Dimension(1, 1));
    jTable1.setRowHeight(38);

    javax.swing.table.DefaultTableCellRenderer izquierda =
            new javax.swing.table.DefaultTableCellRenderer();
    izquierda.setHorizontalAlignment(
            javax.swing.SwingConstants.LEFT
    );

    javax.swing.table.DefaultTableCellRenderer centrado =
            new javax.swing.table.DefaultTableCellRenderer();
    centrado.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    jTable1.getColumnModel()
            .getColumn(0)
            .setCellRenderer(izquierda);

    jTable1.getColumnModel()
            .getColumn(1)
            .setCellRenderer(centrado);

    jTable1.getColumnModel()
            .getColumn(2)
            .setCellRenderer(centrado);
}

private void mostrarFactura(Factura factura) {
    lblNumeroFactura.setText("No. " + factura.getNumero());
    lblFechaFactura.setText("Fecha: " + factura.getFecha());
    lblClienteFactura.setText("Cliente: " + factura.getCliente());
    lblUsuarioFactura.setText("Usuario: " + factura.getUsuario());

    javax.swing.table.DefaultTableModel modelo =
            (javax.swing.table.DefaultTableModel) jTable1.getModel();

    modelo.addRow(new Object[]{
        factura.getDescripcion(),
        factura.getCantidad(),
        String.format(
            java.util.Locale.US,
            "Q %,.2f",
            factura.getPrecioUnitario()
        ),
        String.format(
            java.util.Locale.US,
            "Q %,.2f",
            factura.getTotal()
        )
    });

    lblMontoTotal.setText(
        String.format(
            java.util.Locale.US,
            "Q %,.2f",
            factura.getTotal()
        )
    );
}

private void estilizarTabla() {
    java.awt.Color azul = new java.awt.Color(0, 51, 153);

    jTable1.setBackground(java.awt.Color.WHITE);
    jTable1.setForeground(java.awt.Color.BLACK);
    jTable1.setFont(
            new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14)
    );
    jTable1.setRowHeight(32);
    jTable1.setGridColor(new java.awt.Color(210, 220, 235));

    javax.swing.table.JTableHeader encabezado =
            jTable1.getTableHeader();

    encabezado.setOpaque(true);
    encabezado.setBackground(azul);
    encabezado.setForeground(java.awt.Color.WHITE);
    encabezado.setFont(
            new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14)
    );
    encabezado.setReorderingAllowed(false);

    encabezado.setDefaultRenderer(
        new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(
                    javax.swing.JTable tabla,
                    Object valor,
                    boolean seleccionado,
                    boolean enfocado,
                    int fila,
                    int columna) {

                super.getTableCellRendererComponent(
                        tabla, valor, seleccionado, enfocado, fila, columna
                );

                setBackground(azul);
                setForeground(java.awt.Color.WHITE);
                setHorizontalAlignment(
                        javax.swing.SwingConstants.CENTER
                );
                setOpaque(true);

                return this;
            }
        }
    );
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblNumeroFactura = new javax.swing.JLabel();
        lblFechaFactura = new javax.swing.JLabel();
        lblUsuarioFactura = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblMontoTotal = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblClienteFactura = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("AUTOPILOT");

        jPanel3.setBackground(new java.awt.Color(0, 51, 153));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setText("FACTURA");

        lblNumeroFactura.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNumeroFactura.setText("No.");

        lblFechaFactura.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFechaFactura.setText("Fecha: ");

        lblUsuarioFactura.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUsuarioFactura.setText("Usuario:");

        jPanel4.setBackground(new java.awt.Color(0, 51, 153));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jPanel5.setBackground(new java.awt.Color(0, 51, 153));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        lblMontoTotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMontoTotal.setText("Q 0.00");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(lblMontoTotal)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMontoTotal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TOTAL PAGADO");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(0, 153, 0));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("PAGADA");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblClienteFactura.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblClienteFactura.setText("Cliente: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsuarioFactura)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(29, 29, 29)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(38, 38, 38)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblFechaFactura)
                                .addComponent(jLabel2)
                                .addComponent(lblNumeroFactura))
                            .addGap(6, 6, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(19, 19, 19))
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblClienteFactura)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNumeroFactura))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFechaFactura))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(20, 20, 20)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(lblClienteFactura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUsuarioFactura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        jButton1.setBackground(new java.awt.Color(102, 204, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Descargar PDF");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setText("Cerrar");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (facturaActual == null) {
    javax.swing.JOptionPane.showMessageDialog(
            this,
            "No hay una factura para descargar."
    );
    return;
}

javax.swing.JFileChooser selector =
        new javax.swing.JFileChooser();

selector.setDialogTitle("Guardar factura PDF");
selector.setSelectedFile(new java.io.File(
        "Factura_" + facturaActual.getNumero() + ".pdf"
));

int resultado = selector.showSaveDialog(this);

if (resultado != javax.swing.JFileChooser.APPROVE_OPTION) {
    return;
}

java.io.File archivo = selector.getSelectedFile();

if (!archivo.getName().toLowerCase().endsWith(".pdf")) {
    archivo = new java.io.File(
            archivo.getParentFile(),
            archivo.getName() + ".pdf"
    );
}

try {
    FacturaPDF.generar(facturaActual, archivo);

    javax.swing.JOptionPane.showMessageDialog(
            this,
            "La factura se guardó correctamente en:\n"
                    + archivo.getAbsolutePath()
    );

} catch (Exception e) {
    logger.log(
            java.util.logging.Level.SEVERE,
            "No se pudo crear el PDF",
            e
    );

    javax.swing.JOptionPane.showMessageDialog(
            this,
            "No se pudo guardar la factura.\n" + e.getMessage(),
            "Error",
            javax.swing.JOptionPane.ERROR_MESSAGE
    );
}
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new VerFactura().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblClienteFactura;
    private javax.swing.JLabel lblFechaFactura;
    private javax.swing.JLabel lblMontoTotal;
    private javax.swing.JLabel lblNumeroFactura;
    private javax.swing.JLabel lblUsuarioFactura;
    // End of variables declaration//GEN-END:variables
}
