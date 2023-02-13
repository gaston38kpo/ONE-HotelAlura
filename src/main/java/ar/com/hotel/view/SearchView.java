package ar.com.hotel.view;

import ar.com.hotel.App;
import ar.com.hotel.controller.*;
import ar.com.hotel.model.Guest;
import ar.com.hotel.model.Reservation;
import ar.com.hotel.model.User;
import ar.com.hotel.utils.UtilsUI;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class SearchView extends javax.swing.JFrame {

    int xMouse, yMouse;
    GuestController guestController;
    ReservationController reservationController;
    UserController userController;

    public SearchView() {
        this.reservationController = new ReservationController();
        this.guestController = new GuestController();
        this.userController = new UserController();
        initComponents();
        myInitComponents();
    }

    private void myInitComponents() {
        UtilsUI.setTextFieldPadding(searchInput);

        guestsTable.getTableHeader().setFont(new java.awt.Font("Minecraftia", 0, 12));
        reservationsTable.getTableHeader().setFont(new java.awt.Font("Minecraftia", 0, 12));
        usersTable.getTableHeader().setFont(new java.awt.Font("Minecraftia", 0, 12));

        setTableColumnWidths(guestsTable, 25, 100, 100, 80, 90, 100);
        reservationsTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        setTableColumnWidths(usersTable, 25, 200, 200);

        loadGuestTable(guestController.read());
        loadReservationTable(reservationController.read());
        loadUserTable(userController.read());
    }

    private void setTableColumnWidths(JTable table, int... widths) {
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < widths.length; i++) {
            if (i < columnModel.getColumnCount()) {
                columnModel.getColumn(i).setPreferredWidth(widths[i]);
            } else {
                break;
            }
        }
    }

    private void loadGuestTable(List<Guest> readGuests) {
        var guests = readGuests;

        var guestsTableModel = (DefaultTableModel) guestsTable.getModel();

        guests.forEach(guest -> guestsTableModel.addRow(
                new Object[]{
                    guest.getId(),
                    guest.getName(),
                    guest.getLastname(),
                    guest.getBirthdate(),
                    guest.getNationality(),
                    guest.getPhone()
                }
        ));
    }

    private void loadReservationTable(List<Reservation> readReservations) {
        var reservations = readReservations;

        var reservationTableModel = (DefaultTableModel) reservationsTable.getModel();

        reservations.forEach(reservation -> reservationTableModel.addRow(
                new Object[]{
                    reservation.getId(),
                    reservation.getEntryDate(),
                    reservation.getExitDate(),
                    reservation.getValue(),
                    reservation.getPaymentMethod()
                }
        ));
    }

    private void loadUserTable(List<User> readUsers) {
        var users = readUsers;

        var userTableModel = (DefaultTableModel) usersTable.getModel();

        users.forEach(user -> userTableModel.addRow(
                new Object[]{
                    user.getId(),
                    user.getUser(),
                    user.getPassword()
                }
        ));
    }

    private void deleteTableItem() {
        if (guestsTab.isShowing()) {
            deleteItem(guestsTable);
        } else if (reservationsTab.isShowing()) {
            deleteItem(reservationsTable);
        } else if (usersTab.isShowing()) {
            deleteItem(usersTable);
        }
    }

    private void deleteItem(JTable table) {
        if (table.getSelectedRowCount() == 0 || table.getSelectedColumnCount() == 0) {
            App.openQuestion(this, "Por favor, elije un item");
            return;
        }

        var tableModel = (DefaultTableModel) table.getModel();

        Optional.ofNullable(tableModel.getValueAt(table.getSelectedRow(), table.getSelectedColumn()))
                .ifPresentOrElse(row -> {
                    Integer id = Integer.valueOf(tableModel.getValueAt(table.getSelectedRow(), 0).toString());

                    int amountDeleted = controllerChooser(id);

                    tableModel.removeRow(table.getSelectedRow());

                    App.openQuestion(this, amountDeleted == 0 ? "Hubo un error y nada se ah eliminado!" : amountDeleted + " Item eliminado con Éxito!");

                }, () -> App.openQuestion(this, "Por Favor Elija un Ítem"));
    }

    private int controllerChooser(int id) {
        if (guestsTab.isShowing()) {
            return guestController.delete(id);
        } else if (reservationsTab.isShowing()) {
            return reservationController.delete(id);
        } else if (usersTab.isShowing()) {
            return userController.delete(id);
        }

        return 0;
    }

    private void updateTableItem() {
        if (guestsTab.isShowing()) {
            updateItem(guestsTable);
        } else if (reservationsTab.isShowing()) {
            updateItem(reservationsTable);
        } else if (usersTab.isShowing()) {
            updateItem(usersTable);
        }
    }

    private void updateItem(JTable table) {
        if (table.getSelectedRowCount() == 0 || table.getSelectedColumnCount() == 0) {
            App.openQuestion(this, "Por favor, elije un item");
            return;
        }

        var tableModel = (DefaultTableModel) table.getModel();

        Optional.ofNullable(tableModel.getValueAt(table.getSelectedRow(), table.getSelectedColumn()))
                .ifPresentOrElse(row -> {

                    int amountUpdated = updateAccordingToVisibleTable(tableModel, table);

                    clearTable(table);

                    if (guestsTab.isShowing()) {
                        loadGuestTable(guestController.read());
                    } else if (reservationsTab.isShowing()) {
                        loadReservationTable(reservationController.read());
                    } else if (usersTab.isShowing()) {
                        loadUserTable(userController.read());
                    }

                    App.openQuestion(this, amountUpdated == 0 ? "Hubo un error y el Item no se ah Editado!" : amountUpdated + " Ítem Editado con Éxito!");

                }, () -> App.openQuestion(this, "Por Favor Elija un Ítem"));
    }

    private int updateAccordingToVisibleTable(DefaultTableModel tableModel, JTable table) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Integer id = Integer.valueOf(tableModel.getValueAt(table.getSelectedRow(), 0).toString());

        if (guestsTab.isShowing()) {
            String name = (String) tableModel.getValueAt(table.getSelectedRow(), 1);
            String lastname = (String) tableModel.getValueAt(table.getSelectedRow(), 2);
            String birthdateString = String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 3));
            java.sql.Date birthdate;

            try {
                birthdate = new java.sql.Date(dateFormat.parse(birthdateString).getTime());
            } catch (ParseException e) {
                App.openQuestion(this, "Verifique que la Fecha ingresada corresponda al formato YYYY-MM-DD");
                return 0;
            }

            String nationality = (String) tableModel.getValueAt(table.getSelectedRow(), 4);
            String phone = (String) tableModel.getValueAt(table.getSelectedRow(), 5);

            Guest editedGuest = new Guest(id, name, lastname, birthdate, nationality, phone);

            return guestController.update(editedGuest);
        } else if (reservationsTab.isShowing()) {
            String entryDateString = String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 1));
            String exitDateString = String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 2));
            java.sql.Date entryDate;
            java.sql.Date exitDate;
            try {
                entryDate = new java.sql.Date(dateFormat.parse(entryDateString).getTime());
                exitDate = new java.sql.Date(dateFormat.parse(exitDateString).getTime());
            } catch (ParseException ex) {
                App.openQuestion(this, "Verifique que la Fecha ingresada corresponda al formato YYYY-MM-DD");
                return 0;
            }

            BigDecimal value;
            try {
                value = new BigDecimal(String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 3)));
            } catch (Exception ex) {
                App.openQuestion(this, "Verifique que el Valor Ingresado Corresponda al Formato Numérico, sin Comas(solo punto) y 2 Decimales, por ejemplo 5000.00");
                return 0;
            }

            String paymentMethod = (String) tableModel.getValueAt(table.getSelectedRow(), 4);

            Reservation editedReservation = new Reservation(id, entryDate, exitDate, value, paymentMethod);

            return reservationController.update(editedReservation);
        } else if (usersTab.isShowing()) {
            String user = (String) tableModel.getValueAt(table.getSelectedRow(), 1);
            String password = (String) tableModel.getValueAt(table.getSelectedRow(), 2);

            User editedUser = new User(id, user, password);

            return userController.update(editedUser);
        }
        return 0;
    }

    private void clearTable(JTable table) {
        var tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
    }

    private void resetCurrentTable() {
        if (guestsTab.isShowing()) {
            clearTable(guestsTable);
            loadGuestTable(guestController.read());
        } else if (reservationsTab.isShowing()) {
            clearTable(reservationsTable);
            loadReservationTable(reservationController.read());
        } else if (usersTab.isShowing()) {
            clearTable(usersTable);
            loadUserTable(userController.read());
        }
        searchInput.setText(null);
    }

    private void searchTableItems() {
        if (guestsTab.isShowing()) {
            searchItem(guestsTable);
        } else if (reservationsTab.isShowing()) {
            searchItem(reservationsTable);
        } else if (usersTab.isShowing()) {
            searchItem(usersTable);
        }
    }

    private void searchItem(JTable table) {
        String keyword = searchInput.getText();

        if (keyword.isBlank()) {
            resetCurrentTable();
            return;
        }

        clearTable(table);

        if (guestsTab.isShowing()) {
            List<Guest> result = guestController.read(keyword);
            loadGuestTable(result);
            if (result.isEmpty()) {
                clearTable(guestsTable);
            }
        } else if (reservationsTab.isShowing()) {
            List<Reservation> result = reservationController.read(keyword);
            loadReservationTable(result);
            if (result.isEmpty()) {
                clearTable(reservationsTable);
            }
        } else if (usersTab.isShowing()) {
            List<User> result = userController.read(keyword);
            loadUserTable(result);
            if (result.isEmpty()) {
                clearTable(usersTable);
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        topBar = new javax.swing.JPanel();
        mainTitle = new javax.swing.JLabel();
        searchLabel = new javax.swing.JLabel();
        searchInput = new javax.swing.JTextField();
        cleanBtn = new javax.swing.JButton();
        tablesPane = new javax.swing.JTabbedPane();
        guestsTab = new javax.swing.JScrollPane();
        guestsTable = new javax.swing.JTable();
        reservationsTab = new javax.swing.JScrollPane();
        reservationsTable = new javax.swing.JTable();
        usersTab = new javax.swing.JScrollPane();
        usersTable = new javax.swing.JTable();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        returnBtn = new javax.swing.JButton();
        backgroundImg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        background.setPreferredSize(new java.awt.Dimension(854, 480));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        topBar.setOpaque(false);
        topBar.setPreferredSize(new java.awt.Dimension(0, 30));
        topBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                topBarMouseDragged(evt);
            }
        });
        topBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                topBarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout topBarLayout = new javax.swing.GroupLayout(topBar);
        topBar.setLayout(topBarLayout);
        topBarLayout.setHorizontalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        topBarLayout.setVerticalGroup(
            topBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        background.add(topBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 854, -1));

        mainTitle.setFont(new java.awt.Font("Minecraftia", 0, 16)); // NOI18N
        mainTitle.setForeground(new java.awt.Color(255, 255, 255));
        mainTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainTitle.setText("Sistema de Búsqueda");
        background.add(mainTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 854, -1));

        searchLabel.setFont(new java.awt.Font("Minecraftia", 0, 14)); // NOI18N
        searchLabel.setForeground(new java.awt.Color(160, 160, 160));
        searchLabel.setText("Buscar");
        background.add(searchLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 90, -1, -1));

        searchInput.setBackground(new java.awt.Color(0, 0, 0));
        searchInput.setFont(new java.awt.Font("Minecraftia", 0, 14)); // NOI18N
        searchInput.setForeground(new java.awt.Color(224, 224, 224));
        searchInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        searchInput.setPreferredSize(new java.awt.Dimension(196, 40));
        searchInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchInputKeyReleased(evt);
            }
        });
        background.add(searchInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 120, -1, -1));

        cleanBtn.setFont(new java.awt.Font("Minecraftia", 0, 16)); // NOI18N
        cleanBtn.setForeground(new java.awt.Color(224, 224, 224));
        cleanBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/hotel/img/stone-bar-small.png"))); // NOI18N
        cleanBtn.setText("LIMPIAR");
        cleanBtn.setBorder(null);
        cleanBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cleanBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cleanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanBtnActionPerformed(evt);
            }
        });
        background.add(cleanBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 170, -1, -1));

        tablesPane.setFont(new java.awt.Font("Minecraftia", 0, 16)); // NOI18N
        tablesPane.setPreferredSize(new java.awt.Dimension(600, 200));
        tablesPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tablesPaneStateChanged(evt);
            }
        });

        guestsTable.setFont(new java.awt.Font("Minecraftia", 0, 12)); // NOI18N
        guestsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "APELLIDO", "NACIMIENTO", "NACIONALIDAD", "TELÉFONO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        guestsTab.setViewportView(guestsTable);

        tablesPane.addTab("HUESPEDES", guestsTab);

        reservationsTab.setFont(new java.awt.Font("Minecraftia", 0, 12)); // NOI18N

        reservationsTable.setFont(new java.awt.Font("Minecraftia", 0, 12)); // NOI18N
        reservationsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RESERVA NÚMERO", "CHECK-IN", "CHECK-OUT", "VALOR", "MÉTODO DE PAGO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        reservationsTab.setViewportView(reservationsTable);

        tablesPane.addTab("RESERVAS", reservationsTab);

        usersTable.setFont(new java.awt.Font("Minecraftia", 0, 12)); // NOI18N
        usersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "USUARIO", "CONTRASEÑA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        usersTab.setViewportView(usersTable);

        tablesPane.addTab("USUARIOS", usersTab);

        background.add(tablesPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 620, 280));

        editBtn.setFont(new java.awt.Font("Minecraftia", 0, 16)); // NOI18N
        editBtn.setForeground(new java.awt.Color(224, 224, 224));
        editBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/hotel/img/stone-bar-small.png"))); // NOI18N
        editBtn.setText("EDITAR");
        editBtn.setBorder(null);
        editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        background.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 270, -1, -1));

        deleteBtn.setFont(new java.awt.Font("Minecraftia", 0, 16)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(224, 224, 224));
        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/hotel/img/stone-bar-small.png"))); // NOI18N
        deleteBtn.setText("ELIMINAR");
        deleteBtn.setBorder(null);
        deleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        background.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 320, -1, -1));

        exitBtn.setFont(new java.awt.Font("Minecraftia", 0, 16)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(224, 224, 224));
        exitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/hotel/img/stone-bar-small.png"))); // NOI18N
        exitBtn.setText("SALIR");
        exitBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        exitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exitBtn.setPreferredSize(new java.awt.Dimension(196, 40));
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        background.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 384, -1, -1));

        returnBtn.setFont(new java.awt.Font("Minecraftia", 0, 16)); // NOI18N
        returnBtn.setForeground(new java.awt.Color(224, 224, 224));
        returnBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/hotel/img/stone-bar-small.png"))); // NOI18N
        returnBtn.setText("VOLVER");
        returnBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        returnBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        returnBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        returnBtn.setPreferredSize(new java.awt.Dimension(196, 40));
        returnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnBtnActionPerformed(evt);
            }
        });
        background.add(returnBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 384, -1, -1));

        backgroundImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/hotel/img/dirt-background.png"))); // NOI18N
        backgroundImg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        background.add(backgroundImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void topBarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topBarMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_topBarMouseDragged

    private void topBarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topBarMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_topBarMousePressed

    private void returnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnBtnActionPerformed
        this.dispose();
        App.openHotelNavigation();
    }//GEN-LAST:event_returnBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        deleteTableItem();
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        updateTableItem();
    }//GEN-LAST:event_editBtnActionPerformed

    private void cleanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanBtnActionPerformed
        resetCurrentTable();
    }//GEN-LAST:event_cleanBtnActionPerformed

    private void searchInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchInputKeyReleased
        searchTableItems();
    }//GEN-LAST:event_searchInputKeyReleased

    private void tablesPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tablesPaneStateChanged
        searchInput.setText(null);
        resetCurrentTable();        
    }//GEN-LAST:event_tablesPaneStateChanged

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SearchView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JLabel backgroundImg;
    private javax.swing.JButton cleanBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JScrollPane guestsTab;
    private javax.swing.JTable guestsTable;
    private javax.swing.JLabel mainTitle;
    private javax.swing.JScrollPane reservationsTab;
    private javax.swing.JTable reservationsTable;
    private javax.swing.JButton returnBtn;
    private javax.swing.JTextField searchInput;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTabbedPane tablesPane;
    private javax.swing.JPanel topBar;
    private javax.swing.JScrollPane usersTab;
    private javax.swing.JTable usersTable;
    // End of variables declaration//GEN-END:variables
}
