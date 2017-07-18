/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UILayer;

import MiddleLayer.Controller;
import Models.BookViewModel;
import Models.Category;
import Models.Stock;
import Models.Item;
import Models.StockViewModel;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Deltawing
 */
public class StockUpdate extends javax.swing.JFrame {

    private boolean mType;
    /**
     * Creates new form UserMaster
     */
    public StockUpdate(boolean type) {
        
        initComponents();
        String formTitle, lblHistory;
        mType = type;
        
        if(mType){
            formTitle = "Stock Update(Import) - ABC Book Company Stock Manager Stock Import History";
            lblHistory = "Stock Import History";
        }else{
            formTitle = "Stock Update(History) - ABC Book Company Stock Manager Stock Import History";
            lblHistory = "Stock Export History";
        }
        
        txtHistoryTblHeader.setText(lblHistory);
        this.setTitle(formTitle);
        
        formatStockUpdateTable();
        formatStockUpdateHistoryTable();
    }

    private void clearFields() {
        txtQuantity.setText("");
        cmbBooks.setSelectedIndex(0);
    }

    private boolean validationOfRow() {

        String warning = "";

        if (cmbBooks.getSelectedIndex() == 0) {
            warning = "Book cannot be empty";
        } else if (txtQuantity.getText().equals("")) {
            warning = "Quantity cannot be empty";
        } else {

            try {
                Long qty = Long.valueOf(txtQuantity.getText());
            } catch (NumberFormatException e) {
                warning = "Quantity should be in numerical format!";
            }
        }

        if (warning == "") {
            return true;
        } else {
            JOptionPane.showMessageDialog(this, warning, "Error", 1);
            return false;
        }

    }

    private void loadBookCombo() {

        ArrayList<BookViewModel> list = Controller.getBooks("", 0);
        int count = list.size();

        Object[] itemObj = new Item[count + 1];

        itemObj[0] = new Item(0L, "");

        for (int i = 0; i < count; i++) {
            itemObj[i + 1] = new Item(list.get(i).getId(), list.get(i).getTitle());
        }

        DefaultComboBoxModel mod = new DefaultComboBoxModel(itemObj);
        cmbBooks.setModel(mod);

    }

    private void loadStockHistoryList(boolean type) {
        
        clearStockHistoryTable();
        
        DefaultTableModel model = (DefaultTableModel) tblStockHistory.getModel();
        
        ArrayList<StockViewModel> list = Controller.getStockHistoryList(type);
        
        Object row[] = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            
            row[0] = list.get(i).getItemDescription();
            row[1] = list.get(i).getISBN();
            row[2] = list.get(i).getQuantity();
            row[3] = list.get(i).getUpdateDate();
            
            model.addRow(row);
        }
        
        tblStockHistory.setModel(model);
        
    }

    private void updateStock(boolean type) {

        DefaultTableModel model = (DefaultTableModel) tblImportList.getModel();

        ArrayList<Stock> arr = new ArrayList<Stock>();

        for (int i = 0; i < model.getRowCount(); i++) {

            Stock s = new Stock();
            s.setItemId((Long) model.getValueAt(i, 0));
            s.setQuantity((Long) model.getValueAt(i, 2));
            arr.add(s);
        }

        Controller.updateStock(arr, type);
        clearStockTable();
    }

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbBooks = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblImportList = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStockHistory = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        txtHistoryTblHeader = new javax.swing.JLabel();

        setTitle("Stock Update - ABC Book Company Stock Manager");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel1.setText("Book");

        cmbBooks.setToolTipText("");

        jLabel2.setText("Quantity");

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/add.png"))); // NOI18N
        btnAdd.setText("Add");
        btnAdd.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbBooks)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd))
                .addContainerGap())
        );

        tblImportList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Title", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblImportList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblImportListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblImportList);
        if (tblImportList.getColumnModel().getColumnCount() > 0) {
            tblImportList.getColumnModel().getColumn(0).setResizable(false);
            tblImportList.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblImportList.getColumnModel().getColumn(1).setResizable(false);
            tblImportList.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblImportList.getColumnModel().getColumn(2).setResizable(false);
            tblImportList.getColumnModel().getColumn(2).setPreferredWidth(60);
        }

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/NewX.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnNew.setMaximumSize(new java.awt.Dimension(57, 29));
        btnNew.setMinimumSize(new java.awt.Dimension(57, 29));
        btnNew.setPreferredSize(new java.awt.Dimension(57, 29));
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/SaveX.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Broom-20.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Delete-20.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setEnabled(false);
        btnDelete.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        tblStockHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "ISBN", "Quantity", "Updated Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStockHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStockHistoryMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblStockHistory);
        if (tblStockHistory.getColumnModel().getColumnCount() > 0) {
            tblStockHistory.getColumnModel().getColumn(0).setResizable(false);
            tblStockHistory.getColumnModel().getColumn(1).setResizable(false);
            tblStockHistory.getColumnModel().getColumn(2).setResizable(false);
            tblStockHistory.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel6.setBackground(new java.awt.Color(227, 227, 227));
        jPanel6.setAlignmentX(0.0F);
        jPanel6.setAlignmentY(0.0F);

        txtHistoryTblHeader.setBackground(new java.awt.Color(227, 227, 227));
        txtHistoryTblHeader.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtHistoryTblHeader.setForeground(new java.awt.Color(102, 102, 102));
        txtHistoryTblHeader.setText("Stock Import History");
        txtHistoryTblHeader.setAlignmentY(0.0F);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(txtHistoryTblHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtHistoryTblHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSave))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        clearFields();
        clearStockTable();
    }//GEN-LAST:event_btnNewActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        loadStockHistoryList(mType);
        loadBookCombo();
    }//GEN-LAST:event_formWindowOpened

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        String warning;
        
        if(mType){
            warning = "Import cannot be reversed! \nAre you sure want to Import?";
        }else{
            warning = "Export cannot be reversed! \nAre you sure want to Export?";
        }
        
        int result = JOptionPane.showConfirmDialog(this, warning, "Warning", JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            updateStock(mType);
            loadStockHistoryList(mType);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearFields();
        clearStockTable();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        if (validationOfRow()) {

            Item selectedItem = (Item) cmbBooks.getSelectedItem();

            Object[] item = new Object[3];

            item[0] = selectedItem.getId();
            item[1] = selectedItem.getDescription();
            item[2] = Long.valueOf(txtQuantity.getText());

            DefaultTableModel model = (DefaultTableModel) tblImportList.getModel();
            model.addRow(item);

            tblImportList.setModel(model);

            clearFields();

        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void formatStockUpdateTable() {
        if (tblImportList.getColumnModel().getColumnCount() > 0) {
            tblImportList.getColumnModel().getColumn(0).setMinWidth(0);
            tblImportList.getColumnModel().getColumn(0).setMaxWidth(0);
            tblImportList.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblImportList.getColumnModel().getColumn(1).setPreferredWidth(120);
            //tblImportList.getColumnModel().getColumn(2).setPreferredWidth(60);
            tblImportList.getColumnModel().getColumn(2).setMaxWidth(60);
        }
    }

    private void clearStockTable() {
        DefaultTableModel model = (DefaultTableModel) tblImportList.getModel();
        int rows = model.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
    
    private void clearStockHistoryTable() {
        DefaultTableModel model = (DefaultTableModel) tblStockHistory.getModel();
        int rows = model.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    private void formatStockUpdateHistoryTable() {
        if (tblStockHistory.getColumnModel().getColumnCount() > 0) {
            tblStockHistory.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblStockHistory.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblStockHistory.getColumnModel().getColumn(2).setMaxWidth(60);
        }
    }

    private void tblImportListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblImportListMouseClicked

    }//GEN-LAST:event_tblImportListMouseClicked

    private void tblStockHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStockHistoryMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblStockHistoryMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbBooks;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblImportList;
    private javax.swing.JTable tblStockHistory;
    private javax.swing.JLabel txtHistoryTblHeader;
    private javax.swing.JTextField txtQuantity;
    // End of variables declaration//GEN-END:variables
}