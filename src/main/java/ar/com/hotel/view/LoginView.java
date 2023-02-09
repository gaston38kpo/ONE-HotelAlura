package ar.com.hotel.view;

import ar.com.hotel.App;
import ar.com.hotel.controller.UserController;
import ar.com.hotel.utils.UtilsUI;

public class LoginView extends javax.swing.JFrame {

    int xMouse, yMouse;

    public LoginView() {
        initComponents();
        myInitComponents();
    }

    private void myInitComponents() {
        UtilsUI.setTextFieldPadding(userInput);
        UtilsUI.setTextFieldPadding(passwordInput);
    }

    private void login() {
        String userInputStr = userInput.getText();
        String passwordInputStr = new String(passwordInput.getPassword());

        if (isValidAccount(userInputStr, passwordInputStr)) {
            App.openHotelNavigation();
            this.dispose();
        }
    }

    private boolean isValidAccount(String userInputStr, String passwordInputStr) {
        if (userInputStr.isBlank() || passwordInputStr.isBlank()) {
            App.openQuestion(this, "Los campos Usuario y Contraseña son Requeridos para Continuar.");
        } else if (!new UserController().isUserAndPasswordInDB(userInputStr, passwordInputStr)) {
            App.openQuestion(this, "Usuario y/o Contraseña no Válidos");
        } else {
            return true;
        }

        return false;

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
        userInputLabel = new javax.swing.JLabel();
        userInput = new javax.swing.JTextField();
        passwordInputLabel = new javax.swing.JLabel();
        passwordInput = new javax.swing.JPasswordField();
        loginBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        returnBtn = new javax.swing.JButton();
        backgroundImg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        background.setBackground(new java.awt.Color(55, 0, 0));
        background.setOpaque(false);
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
        mainTitle.setText("Iniciar Sesión");
        background.add(mainTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 854, -1));

        userInputLabel.setFont(new java.awt.Font("Minecraftia", 0, 14)); // NOI18N
        userInputLabel.setForeground(new java.awt.Color(160, 160, 160));
        userInputLabel.setText("Contraseña");
        background.add(userInputLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 170, -1, -1));

        userInput.setBackground(new java.awt.Color(0, 0, 0));
        userInput.setFont(new java.awt.Font("Minecraftia", 0, 16)); // NOI18N
        userInput.setForeground(new java.awt.Color(224, 224, 224));
        userInput.setText("admin");
        userInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        userInput.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        userInput.setPreferredSize(new java.awt.Dimension(400, 40));
        background.add(userInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 118, -1, -1));

        passwordInputLabel.setFont(new java.awt.Font("Minecraftia", 0, 14)); // NOI18N
        passwordInputLabel.setForeground(new java.awt.Color(160, 160, 160));
        passwordInputLabel.setText("Usuario");
        background.add(passwordInputLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 94, -1, -1));

        passwordInput.setBackground(new java.awt.Color(0, 0, 0));
        passwordInput.setFont(new java.awt.Font("Minecraftia", 0, 12)); // NOI18N
        passwordInput.setForeground(new java.awt.Color(224, 224, 224));
        passwordInput.setText("admin");
        passwordInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        passwordInput.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        passwordInput.setPreferredSize(new java.awt.Dimension(400, 40));
        passwordInput.setScrollOffset(3);
        background.add(passwordInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 194, -1, -1));

        loginBtn.setBackground(new java.awt.Color(107, 107, 107));
        loginBtn.setFont(new java.awt.Font("Minecraftia", 0, 16)); // NOI18N
        loginBtn.setForeground(new java.awt.Color(224, 224, 224));
        loginBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/hotel/img/stone-bar-large.png"))); // NOI18N
        loginBtn.setText("ENTRAR");
        loginBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        loginBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginBtn.setFocusable(false);
        loginBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        loginBtn.setPreferredSize(new java.awt.Dimension(400, 40));
        loginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginBtnMouseClicked(evt);
            }
        });
        background.add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 270, -1, -1));

        exitBtn.setBackground(new java.awt.Color(107, 107, 107));
        exitBtn.setFont(new java.awt.Font("Minecraftia", 0, 16)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(224, 224, 224));
        exitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/hotel/img/stone-bar-small.png"))); // NOI18N
        exitBtn.setText("SALIR");
        exitBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        exitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitBtn.setFocusable(false);
        exitBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exitBtn.setPreferredSize(new java.awt.Dimension(196, 40));
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        background.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 384, -1, -1));

        returnBtn.setBackground(new java.awt.Color(107, 107, 107));
        returnBtn.setFont(new java.awt.Font("Minecraftia", 0, 16)); // NOI18N
        returnBtn.setForeground(new java.awt.Color(224, 224, 224));
        returnBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/hotel/img/stone-bar-small.png"))); // NOI18N
        returnBtn.setText("VOLVER");
        returnBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        returnBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        returnBtn.setFocusable(false);
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
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBtnMouseClicked
        this.login();
    }//GEN-LAST:event_loginBtnMouseClicked

    private void topBarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topBarMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_topBarMouseDragged

    private void topBarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topBarMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_topBarMousePressed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitBtnActionPerformed

    private void returnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnBtnActionPerformed
        this.dispose();
        App.openWelcome();
    }//GEN-LAST:event_returnBtnActionPerformed

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
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JLabel backgroundImg;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton loginBtn;
    private javax.swing.JLabel mainTitle;
    private javax.swing.JPasswordField passwordInput;
    private javax.swing.JLabel passwordInputLabel;
    private javax.swing.JButton returnBtn;
    private javax.swing.JPanel topBar;
    private javax.swing.JTextField userInput;
    private javax.swing.JLabel userInputLabel;
    // End of variables declaration//GEN-END:variables
}
