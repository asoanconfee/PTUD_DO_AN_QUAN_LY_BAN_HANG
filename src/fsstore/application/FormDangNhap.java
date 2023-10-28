package fsstore.application;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

/**
 *
 * @author Raven
 */
public class FormDangNhap extends javax.swing.JPanel {

    public FormDangNhap() {
        initComponents();
        init();
    }

    private void init() {
        setLayout(new LoginFormLayout());
        login.setLayout(new LoginLayout());
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        login.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Login.background;"
                + "arc:20;"
                + "border:30,40,50,40");

        txtPass.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true;"
                + "showCapsLock:true");
        cmdLogin.putClientProperty(FlatClientProperties.STYLE, ""
                + "borderWidth:0;"
                + "focusWidth:0");
        txtUser.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên người dùng");
        txtPass.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập mật khẩu");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        cmdLogin = new javax.swing.JButton();
        lbTitle = new javax.swing.JLabel();
        lbUser = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        lbPass = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        cmdExit = new javax.swing.JButton();

        cmdLogin.setText("Đăng nhập");
        cmdLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLoginActionPerformed(evt);
            }
        });

        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setIcon(new javax.swing.ImageIcon("D:\\SalesProgram\\src\\fsstore\\icon\\png\\logomain.png")); // NOI18N
        lbTitle.setText("Đăng nhập");

        lbUser.setText("Tên người dùng");

        lbPass.setText("Mật khẩu");

        cmdExit.setText("Thoát");
        cmdExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLayout.createSequentialGroup()
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lbUser)
                        .addGap(0, 93, Short.MAX_VALUE))
                    .addGroup(loginLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(loginLayout.createSequentialGroup()
                                .addComponent(lbPass)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(loginLayout.createSequentialGroup()
                                .addComponent(cmdLogin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmdExit))))
                    .addComponent(txtPass, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtUser, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle)
                .addGap(10, 10, 10)
                .addComponent(lbUser)
                .addGap(5, 5, 5)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lbPass)
                .addGap(5, 5, 5)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdLogin)
                    .addComponent(cmdExit))
                .addGap(79, 79, 79))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(319, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLoginActionPerformed
        Application.login();
    }//GEN-LAST:event_cmdLoginActionPerformed

    private void cmdExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_cmdExitActionPerformed

    private class LoginFormLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                int width = parent.getWidth();
                int height = parent.getHeight();
                int loginWidth = UIScale.scale(350);
                int loginHeight = login.getPreferredSize().height;
                int x = (width - loginWidth) / 2;
                int y = (height - loginHeight) / 2;
                login.setBounds(x, y, loginWidth, loginHeight);
            }
        }
    }

    private class LoginLayout implements LayoutManager {

        private final int titleGap = 30;
        private final int textGap = 10;
        private final int labelGap = 10;
        private final int buttonGap = 60;

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                Insets insets = parent.getInsets();
                int height = insets.top + insets.bottom;

                height += lbTitle.getPreferredSize().height;
                height += UIScale.scale(titleGap);
                height += lbUser.getPreferredSize().height;
                height += UIScale.scale(labelGap);
                height += txtUser.getPreferredSize().height;
                height += UIScale.scale(textGap);

                height += lbPass.getPreferredSize().height;
                height += UIScale.scale(labelGap);
                height += txtPass.getPreferredSize().height;
                height += UIScale.scale(buttonGap);
                height += cmdLogin.getPreferredSize().height;
                return new Dimension(0, height);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                Insets insets = parent.getInsets();
                int x = insets.left;
                int y = insets.top;
                int width = parent.getWidth() - (insets.left + insets.right);

                lbTitle.setBounds(x, y, width, lbTitle.getPreferredSize().height);
                y += lbTitle.getPreferredSize().height + UIScale.scale(titleGap);

                lbUser.setBounds(x, y, width, lbUser.getPreferredSize().height);
                y += lbUser.getPreferredSize().height + UIScale.scale(labelGap);
                txtUser.setBounds(x, y, width, txtUser.getPreferredSize().height);
                y += txtUser.getPreferredSize().height + UIScale.scale(textGap);

                lbPass.setBounds(x, y, width, lbPass.getPreferredSize().height);
                y += lbPass.getPreferredSize().height + UIScale.scale(labelGap);
                txtPass.setBounds(x, y, width, txtPass.getPreferredSize().height);
                y += txtPass.getPreferredSize().height + UIScale.scale(buttonGap);

                cmdLogin.setBounds(x + 100, y, width/2 - 50, cmdLogin.getPreferredSize().height);
                cmdExit.setBounds(x + 200, y, width/2 - 70, cmdExit.getPreferredSize().height);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdExit;
    private javax.swing.JButton cmdLogin;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbUser;
    private javax.swing.JPanel login;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
