package ar.com.hotel.view;

import ar.com.hotel.App;
import javax.swing.JFrame;

public class QuestionView extends javax.swing.JFrame {

    private JFrame frame = null;
    int xMouse, yMouse;

    public QuestionView() {
        initComponents();
    }

    public QuestionView(JFrame frame) {
        this.frame = frame;
        this.frame.setVisible(false);
        initComponents();
    }

    public QuestionView(JFrame frame, String title) {
        initComponents();
        this.frame = frame;
        this.frame.setVisible(false);
        questionLabel.setText("<html><center> " + title + "</center></html>");
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
        questionLabel = new javax.swing.JLabel();
        mainTitle = new javax.swing.JLabel();
        continueBtn = new javax.swing.JButton();
        returnHomeBtn = new javax.swing.JButton();
        backgroundImg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        background.setBackground(new java.awt.Color(60, 0, 65));
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

        questionLabel.setFont(new java.awt.Font("Minecraftia", 0, 17)); // NOI18N
        questionLabel.setForeground(new java.awt.Color(160, 160, 160));
        questionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        questionLabel.setText("Desea Continuar?");
        questionLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        questionLabel.setPreferredSize(new java.awt.Dimension(400, 100));
        background.add(questionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 170, -1, -1));

        mainTitle.setFont(new java.awt.Font("Minecraftia", 0, 16)); // NOI18N
        mainTitle.setForeground(new java.awt.Color(255, 255, 255));
        mainTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainTitle.setText("< Hotelcraft >");
        background.add(mainTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 854, -1));

        continueBtn.setFont(new java.awt.Font("Minecraftia", 0, 16)); // NOI18N
        continueBtn.setForeground(new java.awt.Color(224, 224, 224));
        continueBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/hotel/img/stone-bar-small.png"))); // NOI18N
        continueBtn.setText("OK");
        continueBtn.setBorder(null);
        continueBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        continueBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        continueBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueBtnActionPerformed(evt);
            }
        });
        background.add(continueBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 384, -1, -1));

        returnHomeBtn.setFont(new java.awt.Font("Minecraftia", 0, 16)); // NOI18N
        returnHomeBtn.setForeground(new java.awt.Color(224, 224, 224));
        returnHomeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/hotel/img/stone-bar-small.png"))); // NOI18N
        returnHomeBtn.setText("VOLVER AL INICIO");
        returnHomeBtn.setBorder(null);
        returnHomeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        returnHomeBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        returnHomeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnHomeBtnActionPerformed(evt);
            }
        });
        background.add(returnHomeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 384, -1, -1));

        backgroundImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ar/com/hotel/img/dirt-background.png"))); // NOI18N
        backgroundImg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        background.add(backgroundImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void continueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continueBtnActionPerformed
        this.frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_continueBtnActionPerformed

    private void returnHomeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnHomeBtnActionPerformed
        if (frame != null) {
            frame.dispose();
        }
        this.dispose();
        App.openWelcome();
    }//GEN-LAST:event_returnHomeBtnActionPerformed

    private void topBarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topBarMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_topBarMouseDragged

    private void topBarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topBarMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_topBarMousePressed

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
            java.util.logging.Logger.getLogger(QuestionView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuestionView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuestionView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuestionView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuestionView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JLabel backgroundImg;
    private javax.swing.JButton continueBtn;
    private javax.swing.JLabel mainTitle;
    private javax.swing.JLabel questionLabel;
    private javax.swing.JButton returnHomeBtn;
    private javax.swing.JPanel topBar;
    // End of variables declaration//GEN-END:variables
}
