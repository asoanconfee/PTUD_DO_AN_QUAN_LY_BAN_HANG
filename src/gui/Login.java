package gui;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import ConnectDB.KetNoiSQL;
import dao.NhanVienDAO;
import entity.NhanVien;

public class Login extends javax.swing.JFrame{

    private static final long serialVersionUID = 1L;
	public static NhanVien nhanVien = null;
    private NhanVienDAO nv_DAO = new NhanVienDAO();
    
    public Login() throws SQLException {
        KetNoiSQL.getInstance().connect();
        
        initComponents();
        
        txt_TaiKhoan.setBackground(new java.awt.Color(0,0,0,1));
        txt_TaiKhoan.setOpaque(false);
        pwd_MatKhau.setBackground(new java.awt.Color(0,0,0,1));
        pwd_MatKhau.setOpaque(false);
        lbl_showicon.setVisible(false);
        lbl_hiddenicon.setVisible(true);
    }


    private void initComponents() {

        new javax.swing.JScrollBar();
        pnl_ImageShop = new javax.swing.JPanel();
        pnl_ThongTin = new javax.swing.JPanel();
        lb_DangNhap = new javax.swing.JLabel();
        lb_MatKhau = new javax.swing.JLabel();
        lb_TaiKhoan = new javax.swing.JLabel();
        Lb_PhanCach1 = new javax.swing.JLabel();
        Lb_PhanCach2 = new javax.swing.JLabel();
        lbl_showicon = new javax.swing.JLabel();
        lbl_usericon = new javax.swing.JLabel();
        lbl_hiddenicon = new javax.swing.JLabel();
        lbl_QuenMatKhau = new javax.swing.JLabel();
        btn_DangNhap = new javax.swing.JButton();
        btn_Thoat = new javax.swing.JButton();
        txt_TaiKhoan = new javax.swing.JTextField();
        pwd_MatKhau = new javax.swing.JPasswordField();
        pnl_Image = new javax.swing.JPanel();
        Lbl_ImageShop = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AM Fashion / Đăng nhập hệ thống");
        setResizable(false);
        setUndecorated(true);

        pnl_ImageShop.setBackground(new java.awt.Color(198, 226, 255));
        pnl_ImageShop.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(198, 226, 255)));

        pnl_ThongTin.setBackground(new java.awt.Color(198, 226, 255));
        pnl_ThongTin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb_DangNhap.setBackground(new java.awt.Color(255, 255, 255));
        lb_DangNhap.setFont(new java.awt.Font("Dosis", 1, 36));
        lb_DangNhap.setForeground(new java.awt.Color(255, 255, 255));
        lb_DangNhap.setText("ĐĂNG NHẬP HỆ THỐNG");
        pnl_ThongTin.add(lb_DangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 500, 50));

        lb_MatKhau.setFont(new java.awt.Font("Open Sans", 1, 18));
        lb_MatKhau.setForeground(new java.awt.Color(255, 255, 255));
        lb_MatKhau.setText("Mật khẩu:");
        pnl_ThongTin.add(lb_MatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 100, 30));

        lb_TaiKhoan.setFont(new java.awt.Font("Open Sans", 1, 18));
        lb_TaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lb_TaiKhoan.setText("Tài khoản:");
        pnl_ThongTin.add(lb_TaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 100, 30));

        Lb_PhanCach1.setForeground(new java.awt.Color(255, 255, 255));
        Lb_PhanCach1.setText("_________________________________________________________________");
        Lb_PhanCach1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        pnl_ThongTin.add(Lb_PhanCach1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 460, 10));

        Lb_PhanCach2.setForeground(new java.awt.Color(255, 255, 255));
        Lb_PhanCach2.setText("_________________________________________________________________");
        Lb_PhanCach2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        pnl_ThongTin.add(Lb_PhanCach2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 470, 10));

        lbl_showicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/view-password.png")));
        lbl_showicon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_showiconMouseClicked(evt);
            }
        });

        pnl_ThongTin.add(lbl_showicon, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 60, 50));

        lbl_usericon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/supplier.png")));
        pnl_ThongTin.add(lbl_usericon, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 170, 50, 50));

        lbl_hiddenicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/view-password.png")));
        lbl_hiddenicon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_hiddeniconMouseClicked(evt);
            }
        });

        pnl_ThongTin.add(lbl_hiddenicon, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 60, 50));

        lbl_QuenMatKhau.setFont(new java.awt.Font("Open Sans", 1, 14));
        lbl_QuenMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        lbl_QuenMatKhau.setText("Quên mật khẩu ?");
        lbl_QuenMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_QuenMatKhauMouseClicked(evt);
            }
        });
        pnl_ThongTin.add(lbl_QuenMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 350, 120, 30));

        btn_DangNhap.setFont(new java.awt.Font("Open Sans", 1, 18));
        btn_DangNhap.setText("Đăng nhập");
        btn_DangNhap.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.yellow, java.awt.Color.orange, java.awt.Color.black));
        btn_DangNhap.setBorderPainted(false);
        btn_DangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_DangNhapMouseClicked(evt);
            }
        });
        pnl_ThongTin.add(btn_DangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 400, 300, 40));
        
        btn_Thoat.setFont(new java.awt.Font("Open Sans", 1, 18));
        btn_Thoat.setText("Thoát");
        btn_Thoat.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.yellow, java.awt.Color.orange, java.awt.Color.black));
        btn_Thoat.setBorderPainted(false);
        btn_Thoat.addMouseListener(new java.awt.event.MouseAdapter() {
        	public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ThoatMouseClicked(evt);
            }
        });
        pnl_ThongTin.add(btn_Thoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 180, 40));

        txt_TaiKhoan.setFont(new java.awt.Font("Open Sans", 1, 18));
        txt_TaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        txt_TaiKhoan.setText("QL0001");
        txt_TaiKhoan.setBorder(null);
        pnl_ThongTin.add(txt_TaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 470, 40));

        pwd_MatKhau.setFont(new java.awt.Font("Open Sans", 1, 18));
        pwd_MatKhau.setForeground(new java.awt.Color(255, 255, 255));
        pwd_MatKhau.setText("admin");
        pwd_MatKhau.setBorder(null);
        pnl_ThongTin.add(pwd_MatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 470, 40));

        pnl_Image.setBackground(new java.awt.Color(198, 226, 255));
        Lbl_ImageShop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/login-image.png")));

        javax.swing.GroupLayout pnl_ImageLayout = new javax.swing.GroupLayout(pnl_Image);
        pnl_Image.setLayout(pnl_ImageLayout);
        pnl_ImageLayout.setHorizontalGroup(
            pnl_ImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ImageLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(Lbl_ImageShop, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        pnl_ImageLayout.setVerticalGroup(
            pnl_ImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ImageLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(Lbl_ImageShop, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnl_ImageShopLayout = new javax.swing.GroupLayout(pnl_ImageShop);
        pnl_ImageShop.setLayout(pnl_ImageShopLayout);
        pnl_ImageShopLayout.setHorizontalGroup(
            pnl_ImageShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_ImageShopLayout.createSequentialGroup()
                .addComponent(pnl_Image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_ThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnl_ImageShopLayout.setVerticalGroup(
            pnl_ImageShopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_ThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addComponent(pnl_Image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_ImageShop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_ImageShop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void lbl_showiconMouseClicked(java.awt.event.MouseEvent evt) {
        pwd_MatKhau.setEchoChar('*');
        lbl_showicon.setVisible(false);
        lbl_showicon.setEnabled(false);
        lbl_hiddenicon.setEnabled(true);
        lbl_hiddenicon.setVisible(true);
    }

    private void lbl_hiddeniconMouseClicked(java.awt.event.MouseEvent evt) {
        pwd_MatKhau.setEchoChar((char)0);
        lbl_hiddenicon.setVisible(false);
        lbl_hiddenicon.setEnabled(false);
        lbl_showicon.setEnabled(true);
        lbl_showicon.setVisible(true);
    }

    // chỉnh sửa đăng nhập theo thông tin user trên sql
    private void btn_DangNhapMouseClicked(java.awt.event.MouseEvent evt) {
        Connection conn = KetNoiSQL.getConnection();
       try{
       String sql="SELECT * FROM TaiKhoan WHERE tenTaiKhoan=? AND matKhau=? and (isDeleted is null or isDeleted = 0)";
       PreparedStatement stmt= conn.prepareCall(sql);
       stmt.setString(1, txt_TaiKhoan.getText());
       stmt.setString(2, String.valueOf(pwd_MatKhau.getPassword()));
       ResultSet rs= stmt.executeQuery();
       if(rs.next()){
           JOptionPane.showMessageDialog(null,"Đăng nhập thành công");
           nhanVien = nv_DAO.getNhanVienByID(rs.getString(4));
           this.setVisible(false);
           new TrangChu().setVisible(true);
       }else{
           JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng");
           pwd_MatKhau.setText("");
       }
       }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex);
       }
   }


    private void lbl_QuenMatKhauMouseClicked(java.awt.event.MouseEvent evt) {
        JOptionPane.showMessageDialog(null, "Tính năng đang được phát triển", "Tính năng chưa hoàn thiện", JOptionPane.WARNING_MESSAGE);
    }
    
    private void btn_ThoatMouseClicked(java.awt.event.MouseEvent evt) {
        System.exit(0);
    }
    
    public static void main(String args[]) throws SQLException {
        new Login().setVisible(true);
    }

    private javax.swing.JButton btn_DangNhap;
    private javax.swing.JButton btn_Thoat;
    private javax.swing.JLabel lb_DangNhap;
    private javax.swing.JLabel lb_MatKhau;
    private javax.swing.JLabel lb_TaiKhoan;
    private javax.swing.JLabel Lb_PhanCach1;
    private javax.swing.JLabel Lb_PhanCach2;
    private javax.swing.JLabel Lbl_ImageShop;
    private javax.swing.JPanel pnl_ImageShop;
    private javax.swing.JPanel pnl_ThongTin;
    private javax.swing.JPanel pnl_Image;
    private javax.swing.JLabel lbl_QuenMatKhau;
    private javax.swing.JLabel lbl_hiddenicon;
    private javax.swing.JLabel lbl_showicon;
    private javax.swing.JLabel lbl_usericon;
    private javax.swing.JPasswordField pwd_MatKhau;
    private javax.swing.JTextField txt_TaiKhoan;

}


/*
 * regex xoá toàn bộ các comment dạng // ;
 * \/\/.*
 */