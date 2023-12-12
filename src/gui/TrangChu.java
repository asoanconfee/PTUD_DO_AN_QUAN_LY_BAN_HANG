package gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import ConnectDB.KetNoiSQL;
import entity.NhanVien;


public class TrangChu extends javax.swing.JFrame {

	public JProgressBar progressBar;
	private static boolean kh_active = false;
    private static boolean nv_active = false;
    private static boolean sp_active = false;
    private static boolean hd_active = false;
    private static boolean ncc_active = false;
    private static boolean tk_active = false;
    private static boolean thongKe_active = false;
    private static boolean thongTinNhanVien_active = false;
    private static boolean troGiup_active = false;
    private static boolean dangXuat_active = false;


    public TrangChu() throws SQLException {

        KetNoiSQL.getInstance().connect();
        
        initComponents();
        setRole();
        nv_active = true;
        hoverMouseEntered(nhanVien_container, lbl_NhanVien);
        addPanel(new QuanLyBanHang());
        
    }

    // xem quyền của nhân viên, từ đó hiện các chức năng tương ứng
    private final NhanVien nhanVien = Login.nhanVien;

    public void setRole(){
        if(nhanVien.getChucVu().equalsIgnoreCase("Nhân Viên"))
        {
        	hoaDon_container.setVisible(false);
            thongTinNhanVien_container.setVisible(false);
            nhaCungCap_container.setVisible(false);
            TaiKhoan_container.setVisible(false);
            thongKe_container.setVisible(false);
        }
    }


    private static void clearEffect(){

    	hoverMouseExited(nhanVien_container, lbl_NhanVien);
        hoverMouseExited(sanPham_container, lbl_sanPham);
        hoverMouseExited(khachHang_container, lbl_KhachHang);
        hoverMouseExited(hoaDon_container, lbl_hoaDon);
        hoverMouseExited(thongKe_container, lbl_thongKe);
        hoverMouseExited(nhaCungCap_container, lbl_nhaCungCap);
        hoverMouseExited(thongTinNhanVien_container, lbl_thongTinNhanVien);
        hoverMouseExited(TaiKhoan_container, lbl_QuanLyTaiKhoan);
		hoverMouseExited(troGiup_container, lbl_QuanLyTaiKhoan);
        hoverMouseExited(DangXuat_container, lbl_DangXuat);


        nv_active = false;
        kh_active = false;
        sp_active = false;
        hd_active = false;
        thongKe_active = false;
        ncc_active = false;
        tk_active = false;
        thongTinNhanVien_active = false;
        troGiup_active = false;
        dangXuat_active = false;
    }
    
    private static void addPanel(JPanel pnForm){
        right_container.removeAll();
        right_container.add(pnForm);
        right_container.updateUI();
    }
     
    //tạo hàm mở tab khách hàng
    public static void activeQLKHForm(){
        clearEffect();
        kh_active = true;
        hoverMouseEntered(khachHang_container, lbl_KhachHang);
        addPanel(new QuanLyKhachHang());
    }



    // tạo hiệu ứng hover cho các button quản lý
    private static void hoverMouseEntered(javax.swing.JPanel pn_Container, javax.swing.JLabel lbl_Title){
        pn_Container.setBackground(new Color(255, 255, 255));
        lbl_Title.setForeground(new Color(24,71,133));
    }
    private static void hoverMouseExited(javax.swing.JPanel pn_Container, javax.swing.JLabel lbl_Title){
        pn_Container.setBackground(new Color(198, 226, 255));
        lbl_Title.setForeground(new Color(255, 255, 255));
    }


    // giao diện
    private void initComponents() {

    	container = new javax.swing.JPanel();
        left_container = new javax.swing.JPanel();
        menu_container = new javax.swing.JPanel();
        lbl_Menu = new javax.swing.JLabel();
        header_container = new javax.swing.JPanel();
        txt_Header = new javax.swing.JLabel();
        sanPham_container = new javax.swing.JPanel();
        lbl_sanPham = new javax.swing.JLabel();
        nhanVien_container = new javax.swing.JPanel();
        lbl_NhanVien = new javax.swing.JLabel();
        khachHang_container = new javax.swing.JPanel();
        lbl_KhachHang = new javax.swing.JLabel();
        thongKe_container = new javax.swing.JPanel();
        lbl_thongKe = new javax.swing.JLabel();
        thongTinNhanVien_container = new javax.swing.JPanel();
        lbl_thongTinNhanVien = new javax.swing.JLabel();
        TaiKhoan_container = new javax.swing.JPanel();
        lbl_QuanLyTaiKhoan = new javax.swing.JLabel();
        hoaDon_container = new javax.swing.JPanel();
        lbl_hoaDon = new javax.swing.JLabel();
        nhaCungCap_container = new javax.swing.JPanel();
        lbl_nhaCungCap = new javax.swing.JLabel();
        troGiup_container = new javax.swing.JPanel();
        lbl_troGiup = new javax.swing.JLabel();
        DangXuat_container = new javax.swing.JPanel();
        lbl_DangXuat = new javax.swing.JLabel();
        right_container = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1383, 780));
        setResizable(false);
        setTitle("AM Fashion / Trang chủ");

        left_container.setBackground(new java.awt.Color(198, 226, 255));
        left_container.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 0, new java.awt.Color(198, 226, 255)));
        left_container.setMaximumSize(new java.awt.Dimension(230, 768));
        left_container.setMinimumSize(new java.awt.Dimension(230, 768));

        menu_container.setBackground(new java.awt.Color(198, 226, 255));
        menu_container.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(198, 226, 255)));
        //menu_container.setForeground(new java.awt.Color(255, 255, 255));
        menu_container.setAlignmentX(0.0F);
        menu_container.setMaximumSize(new java.awt.Dimension(204, 60));
        menu_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_Menu.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lbl_Menu.setBackground(new java.awt.Color(198, 226, 255));
        lbl_Menu.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Menu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_Menu.setAlignmentY(0.0F);
        lbl_Menu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbl_Menu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_Menu.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_Menu.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_Menu.setPreferredSize(new java.awt.Dimension(236, 60));
        menu_container.add(lbl_Menu);

        header_container.setBackground(new java.awt.Color(198, 226, 255));
        header_container.setForeground(new java.awt.Color(255, 255, 255));
        header_container.setAlignmentX(0.0F);
        header_container.setMaximumSize(new java.awt.Dimension(204, 60));
        header_container.setMinimumSize(new java.awt.Dimension(204, 60));

        // tên nhóm header menu
        txt_Header.setFont(new java.awt.Font("Dosis", 1, 30));
        txt_Header.setForeground(new java.awt.Color(255, 255, 255));
        txt_Header.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txt_Header.setText("AM Fashion");
        txt_Header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logomain.png")));
        txt_Header.setAlignmentY(0.0F);
        txt_Header.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        //txt_Header.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        txt_Header.setMaximumSize(new java.awt.Dimension(204, 60));
        txt_Header.setPreferredSize(new java.awt.Dimension(250, 60));
        header_container.add(txt_Header);

        // bán hàng
        nhanVien_container.setBackground(new java.awt.Color(198, 226, 255));
        nhanVien_container.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(198, 226, 255)));
        nhanVien_container.setForeground(new java.awt.Color(255, 255, 255));
        nhanVien_container.setAlignmentX(0.0F);
        nhanVien_container.setMaximumSize(new java.awt.Dimension(204, 60));
        nhanVien_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_NhanVien.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_NhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lbl_NhanVien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_NhanVien.setText("Bán Hàng");
        lbl_NhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/brand.png")));
        lbl_NhanVien.setAlignmentY(0.0F);
        lbl_NhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        //lbl_NhanVien.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_NhanVien.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_NhanVien.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_NhanVien.setPreferredSize(new java.awt.Dimension(236, 60));
        lbl_NhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
					lbl_NhanVienMouseClicked(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_NhanVienMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_NhanVienMouseExited(evt);
            }
        });
        nhanVien_container.add(lbl_NhanVien);


        // sản phẩm
        sanPham_container.setBackground(new java.awt.Color(198, 226, 255));
        sanPham_container.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(198, 226, 255)));
        sanPham_container.setForeground(new java.awt.Color(255, 255, 255));
        sanPham_container.setAlignmentX(0.0F);
        sanPham_container.setMaximumSize(new java.awt.Dimension(204, 60));
        sanPham_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_sanPham.setFont(new java.awt.Font("Segoe UI", 0, 24));
        lbl_sanPham.setForeground(new java.awt.Color(255, 255, 255));
        lbl_sanPham.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_sanPham.setText("Sản Phẩm");
        lbl_sanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/product.png")));
        lbl_sanPham.setAlignmentY(0.0F);
        lbl_sanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        //lbl_sanPham.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_sanPham.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_sanPham.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_sanPham.setPreferredSize(new java.awt.Dimension(236, 60));
        lbl_sanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_sanPhamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_sanPhamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_sanPhamMouseExited(evt);
            }
        });
        sanPham_container.add(lbl_sanPham);


        // khách hàng
        khachHang_container.setBackground(new java.awt.Color(198, 226, 255));
        khachHang_container.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(198, 226, 255)));
        khachHang_container.setForeground(new java.awt.Color(255, 255, 255));
        khachHang_container.setAlignmentX(0.0F);
        khachHang_container.setMaximumSize(new java.awt.Dimension(204, 60));
        khachHang_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_KhachHang.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_KhachHang.setForeground(new java.awt.Color(255, 255, 255));
        lbl_KhachHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_KhachHang.setText("Khách Hàng");
        lbl_KhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/customer.png")));
        lbl_KhachHang.setAlignmentY(0.0F);
        lbl_KhachHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        //setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_KhachHang.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_KhachHang.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_KhachHang.setPreferredSize(new java.awt.Dimension(236, 60));
        lbl_KhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_KhachHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_KhachHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_KhachHangMouseExited(evt);
            }
        });
        khachHang_container.add(lbl_KhachHang);


        // hóa đơn
        hoaDon_container.setBackground(new java.awt.Color(198, 226, 255));
        hoaDon_container.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(198, 226, 255)));
        hoaDon_container.setForeground(new java.awt.Color(255, 255, 255));
        hoaDon_container.setAlignmentX(0.0F);
        hoaDon_container.setMaximumSize(new java.awt.Dimension(204, 60));
        hoaDon_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_hoaDon.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_hoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lbl_hoaDon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_hoaDon.setText("Hóa Đơn");
        lbl_hoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/export.png")));
        lbl_hoaDon.setAlignmentY(0.0F);
        lbl_hoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        //lbl_hoaDon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_hoaDon.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_hoaDon.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_hoaDon.setPreferredSize(new java.awt.Dimension(236, 60));
        lbl_hoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_hoaDonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_hoaDonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_hoaDonMouseExited(evt);
            }
        });
        hoaDon_container.add(lbl_hoaDon);
        
        // Thống Kê
        thongKe_container.setBackground(new java.awt.Color(198, 226, 255));
        thongKe_container.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(198, 226, 255)));
        thongKe_container.setForeground(new java.awt.Color(255, 255, 255));
        thongKe_container.setAlignmentX(0.0F);
        thongKe_container.setMaximumSize(new java.awt.Dimension(204, 60));
        thongKe_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_thongKe.setFont(new java.awt.Font("Segoe UI", 0, 24));
        lbl_thongKe.setForeground(new java.awt.Color(255, 255, 255));
        lbl_thongKe.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_thongKe.setText("Thống Kê");
        lbl_thongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/statistical.png")));
        lbl_thongKe.setAlignmentY(0.0F);
        lbl_thongKe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        //lbl_thongKe.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lbl_thongKe.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_thongKe.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_thongKe.setPreferredSize(new java.awt.Dimension(236, 60));
        lbl_thongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_thongKeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_thongKeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_thongKeMouseExited(evt);
            }
        });
        thongKe_container.add(lbl_thongKe);

        // nhà cung cấp
        nhaCungCap_container.setBackground(new java.awt.Color(198, 226, 255));
        nhaCungCap_container.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(198, 226, 255)));
        nhaCungCap_container.setForeground(new java.awt.Color(255, 255, 255));
        nhaCungCap_container.setAlignmentX(0.0F);
        nhaCungCap_container.setMaximumSize(new java.awt.Dimension(204, 60));
        nhaCungCap_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_nhaCungCap.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_nhaCungCap.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nhaCungCap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_nhaCungCap.setText("Nhà Cung Cấp");
        lbl_nhaCungCap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/supplier.png")));
        lbl_nhaCungCap.setAlignmentY(0.0F);
        lbl_nhaCungCap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        //lbl_nhaCungCap.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_nhaCungCap.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_nhaCungCap.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_nhaCungCap.setPreferredSize(new java.awt.Dimension(236, 60));
        lbl_nhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_nhaCungCapMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_nhaCungCapMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_nhaCungCapMouseExited(evt);
            }
        });
        nhaCungCap_container.add(lbl_nhaCungCap);
        
        // thông tin nhân viên
        thongTinNhanVien_container.setBackground(new java.awt.Color(198, 226, 255));
        thongTinNhanVien_container.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(198, 226, 255)));
        thongTinNhanVien_container.setForeground(new java.awt.Color(255, 255, 255));
        thongTinNhanVien_container.setAlignmentX(0.0F);
        thongTinNhanVien_container.setMaximumSize(new java.awt.Dimension(204, 60));
        thongTinNhanVien_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_thongTinNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_thongTinNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lbl_thongTinNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_thongTinNhanVien.setText("Nhân Viên");
        lbl_thongTinNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/staff.png")));
        lbl_thongTinNhanVien.setAlignmentY(0.0F);
        lbl_thongTinNhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        //lbl_thongTinNhanVien.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lbl_thongTinNhanVien.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_thongTinNhanVien.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_thongTinNhanVien.setPreferredSize(new java.awt.Dimension(236, 60));
        lbl_thongTinNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
					lbl_thongTinNhanVienMouseClicked(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_thongTinNhanVienMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_thongTinNhanVienMouseExited(evt);
            }
        });
        thongTinNhanVien_container.add(lbl_thongTinNhanVien);

        // tài khoản
        TaiKhoan_container.setBackground(new java.awt.Color(198, 226, 255));
        TaiKhoan_container.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(198, 226, 255)));
        TaiKhoan_container.setForeground(new java.awt.Color(255, 255, 255));
        TaiKhoan_container.setAlignmentX(0.0F);
        TaiKhoan_container.setMaximumSize(new java.awt.Dimension(204, 60));
        TaiKhoan_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_QuanLyTaiKhoan.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_QuanLyTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lbl_QuanLyTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_QuanLyTaiKhoan.setText("Tài Khoản");
        lbl_QuanLyTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/account.png")));
        lbl_QuanLyTaiKhoan.setAlignmentY(0.0F);
        lbl_QuanLyTaiKhoan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        //lbl_QuanLyTaiKhoan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_QuanLyTaiKhoan.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_QuanLyTaiKhoan.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_QuanLyTaiKhoan.setPreferredSize(new java.awt.Dimension(236, 60));
        lbl_QuanLyTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_QuanLyTaiKhoanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_QuanLyTaiKhoanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_QuanLyTaiKhoanMouseExited(evt);
            }
        });
        TaiKhoan_container.add(lbl_QuanLyTaiKhoan);
        
     // trợ giúp
        troGiup_container.setBackground(new java.awt.Color(198, 226, 255));
        troGiup_container.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(198, 226, 255)));
        troGiup_container.setForeground(new java.awt.Color(255, 255, 255));
        troGiup_container.setAlignmentX(0.0F);
        troGiup_container.setMaximumSize(new java.awt.Dimension(204, 60));
        troGiup_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_troGiup.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_troGiup.setForeground(new java.awt.Color(255, 255, 255));
        lbl_troGiup.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_troGiup.setText("Trợ Giúp");
        lbl_troGiup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/detail.png")));
        lbl_troGiup.setAlignmentY(0.0F);
        lbl_troGiup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        //lbl_QuanLyTaiKhoan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_troGiup.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_troGiup.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_troGiup.setPreferredSize(new java.awt.Dimension(236, 60));
        lbl_troGiup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_TroGiupMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	lbl_TroGiupMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	lbl_TroGiupMouseExited(evt);
            }
        });
        troGiup_container.add(lbl_troGiup);


        // Button đăng xuất
        DangXuat_container.setBackground(new java.awt.Color(198, 226, 255));
        DangXuat_container.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(198, 226, 255)));
        DangXuat_container.setForeground(new java.awt.Color(255, 255, 255));
        DangXuat_container.setAlignmentX(0.0F);
        DangXuat_container.setMaximumSize(new java.awt.Dimension(204, 60));
        DangXuat_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_DangXuat.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_DangXuat.setForeground(new java.awt.Color(255, 255, 255));
        lbl_DangXuat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_DangXuat.setText("Đăng Xuất");
        lbl_DangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/log_out.png")));
        lbl_DangXuat.setAlignmentY(0.0F);
        lbl_DangXuat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        //lbl_DangXuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_DangXuat.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_DangXuat.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_DangXuat.setPreferredSize(new java.awt.Dimension(236, 45));
        lbl_DangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
					lbl_DangXuatMouseClicked(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_DangXuatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_DangXuatMouseExited(evt);
            }
        });
        DangXuat_container.add(lbl_DangXuat);


        // layout
        javax.swing.GroupLayout left_containerLayout = new javax.swing.GroupLayout(left_container);
        left_container.setLayout(left_containerLayout);
        left_containerLayout.setHorizontalGroup(
            left_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu_container, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
            .addComponent(sanPham_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(khachHang_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(nhanVien_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(header_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(TaiKhoan_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(hoaDon_container, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(thongKe_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(nhaCungCap_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(thongTinNhanVien_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(troGiup_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(DangXuat_container, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        left_containerLayout.setVerticalGroup(
            left_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(left_containerLayout.createSequentialGroup()
            		.addComponent(header_container, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(menu_container, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(nhanVien_container, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(sanPham_container, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(khachHang_container, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(hoaDon_container, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(thongKe_container, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(nhaCungCap_container, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(thongTinNhanVien_container, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(TaiKhoan_container, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(troGiup_container, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                    .addGap(0, 0, 0)
                    .addComponent(DangXuat_container, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );

        right_container.setBackground(new java.awt.Color(255, 255, 255));
        right_container.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 255, 255)));
        right_container.setAlignmentY(0.0F);
        right_container.setMaximumSize(new java.awt.Dimension(1130, 768));//kích thước pannel con 
        right_container.setMinimumSize(new java.awt.Dimension(1130, 768));
        right_container.setPreferredSize(new java.awt.Dimension(1130, 768));
        right_container.setLayout(new javax.swing.BoxLayout(right_container, javax.swing.BoxLayout.PAGE_AXIS));

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addComponent(left_container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(right_container, javax.swing.GroupLayout.PREFERRED_SIZE, 1124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(left_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(right_container, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }
    
    protected void lbl_thongTinNhanVienMouseEntered(MouseEvent evt) {
		// TODO Auto-generated method stub
		
	}


	protected void lbl_QuanLyTaiKhoanMouseEntered(MouseEvent evt) {
		// TODO Auto-generated method stub
		
	}

	//các xử lí sự kiện
    private void lbl_NhanVienMouseClicked(java.awt.event.MouseEvent evt) throws SQLException {
        clearEffect();
        nv_active = true;
        hoverMouseEntered(nhanVien_container, lbl_NhanVien);
        addPanel( new QuanLyBanHang());
    }

    private void lbl_NhanVienMouseEntered(java.awt.event.MouseEvent evt) {
        hoverMouseEntered(nhanVien_container, lbl_NhanVien);
    }

    private void lbl_NhanVienMouseExited(java.awt.event.MouseEvent evt) {
        if(nv_active) return;
        hoverMouseExited(nhanVien_container, lbl_NhanVien);
    }


    private void lbl_sanPhamMouseClicked(java.awt.event.MouseEvent evt) {
        clearEffect();
        sp_active = true;
        hoverMouseEntered(sanPham_container, lbl_sanPham);
        addPanel(new QuanLySanPham());
    }

    private void lbl_sanPhamMouseEntered(java.awt.event.MouseEvent evt) {
        hoverMouseEntered(sanPham_container, lbl_sanPham);
    }

    private void lbl_sanPhamMouseExited(java.awt.event.MouseEvent evt) {
        if(sp_active) return;
        hoverMouseExited(sanPham_container, lbl_sanPham);
    }


    private void lbl_KhachHangMouseClicked(java.awt.event.MouseEvent evt) {
        clearEffect();
        kh_active = true;
        hoverMouseEntered(khachHang_container, lbl_KhachHang);
        addPanel(new QuanLyKhachHang());
    }


    private void lbl_KhachHangMouseEntered(java.awt.event.MouseEvent evt) {
        hoverMouseEntered(khachHang_container, lbl_KhachHang);
    }

    private void lbl_KhachHangMouseExited(java.awt.event.MouseEvent evt) {
        if(kh_active) return;
        hoverMouseExited(khachHang_container, lbl_KhachHang);
    }


    
    private void lbl_hoaDonMouseClicked(java.awt.event.MouseEvent evt) {
        clearEffect();
        hd_active = true;
        hoverMouseEntered(hoaDon_container, lbl_hoaDon);
        addPanel(new QuanLyHoaDon());
    }
    
    private void lbl_hoaDonMouseEntered(java.awt.event.MouseEvent evt) {
        hoverMouseEntered(hoaDon_container, lbl_hoaDon);
    }
    
    private void lbl_hoaDonMouseExited(java.awt.event.MouseEvent evt) {
        if(hd_active) return;
        hoverMouseExited(hoaDon_container, lbl_hoaDon);
    }
    
    private void lbl_thongKeMouseClicked(java.awt.event.MouseEvent evt) {
        clearEffect();
        hd_active = true;
        hoverMouseEntered(thongKe_container, lbl_thongKe);
        addPanel(new QuanLyThongKe());
    }
    
    private void lbl_thongKeMouseEntered(java.awt.event.MouseEvent evt) {
        hoverMouseEntered(thongKe_container, lbl_thongKe);
    }
    
    private void lbl_thongKeMouseExited(java.awt.event.MouseEvent evt) {
        if(hd_active) return;
        hoverMouseExited(thongKe_container, lbl_thongKe);
    }

    private void lbl_nhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {
        clearEffect();
        ncc_active = true;
        hoverMouseEntered(nhaCungCap_container, lbl_nhaCungCap);
        addPanel(new QuanLyNhaCungCap());
    }
    
    private void lbl_nhaCungCapMouseEntered(java.awt.event.MouseEvent evt) {
        hoverMouseEntered(nhaCungCap_container, lbl_nhaCungCap);
    }
    
    private void lbl_nhaCungCapMouseExited(java.awt.event.MouseEvent evt) {
        if(ncc_active) return;
        hoverMouseExited(nhaCungCap_container, lbl_nhaCungCap);
    }

    private void lbl_thongTinNhanVienMouseClicked(java.awt.event.MouseEvent evt) throws SQLException {
        clearEffect();
        thongTinNhanVien_active = true;
        hoverMouseEntered(thongTinNhanVien_container, lbl_thongTinNhanVien);
        addPanel(new QuanLyNhanVien());
    }

    private void thongTinNhanVienMouseEntered(java.awt.event.MouseEvent evt) {
        hoverMouseEntered(thongTinNhanVien_container, lbl_thongTinNhanVien);
    }

    private void lbl_thongTinNhanVienMouseExited(java.awt.event.MouseEvent evt) {
        if(thongTinNhanVien_active) return;
        hoverMouseExited(thongTinNhanVien_container, lbl_thongTinNhanVien);
    }
    
    private void lbl_QuanLyTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {
        clearEffect();
        tk_active = true;
        hoverMouseEntered(TaiKhoan_container, lbl_QuanLyTaiKhoan);
        addPanel(new QuanLyTaiKhoan());
    }

    private void QuanLyTaiKhoanMouseEntered(java.awt.event.MouseEvent evt) {
        hoverMouseEntered(TaiKhoan_container, lbl_QuanLyTaiKhoan);
    }

    private void lbl_QuanLyTaiKhoanMouseExited(java.awt.event.MouseEvent evt) {
        if(tk_active) return;
        hoverMouseExited(TaiKhoan_container, lbl_QuanLyTaiKhoan);
    }
    
    private void lbl_TroGiupMouseClicked(java.awt.event.MouseEvent evt) {
        clearEffect();
        troGiup_active = true;
        hoverMouseEntered(troGiup_container, lbl_troGiup);
        addPanel(new FrmTroGiup());
    }

    private void addPanel(FrmTroGiup frmTroGiup) {
		// TODO Auto-generated method stub
		
	}


	private void lbl_TroGiupMouseEntered(java.awt.event.MouseEvent evt) {
        hoverMouseEntered(troGiup_container, lbl_troGiup);
    }

    private void lbl_TroGiupMouseExited(java.awt.event.MouseEvent evt) {
        if(troGiup_active) return;
        hoverMouseExited(troGiup_container, lbl_troGiup);
    }
    

    private void lbl_DangXuatMouseClicked(java.awt.event.MouseEvent evt) throws SQLException {
        clearEffect();
        dangXuat_active = true;
        hoverMouseEntered(DangXuat_container, lbl_DangXuat);
        new Login().setVisible(true);
        this.setVisible(false);
    }

    private void lbl_DangXuatMouseEntered(java.awt.event.MouseEvent evt) {
           hoverMouseEntered(DangXuat_container, lbl_DangXuat);
    }

    private void lbl_DangXuatMouseExited(java.awt.event.MouseEvent evt) {
                if(dangXuat_active) return;
        hoverMouseExited(DangXuat_container, lbl_DangXuat);
    }

    

    private javax.swing.JPanel header_container;
    private javax.swing.JPanel left_container;
    private javax.swing.JPanel menu_container;
    private javax.swing.JLabel lbl_Menu;
    private javax.swing.JPanel container;
    private static javax.swing.JPanel right_container;

    private static javax.swing.JPanel hoaDon_container;
    private static javax.swing.JPanel khachHang_container;
    private static javax.swing.JPanel DangXuat_container;
    private static javax.swing.JPanel nhanVien_container;
    private static javax.swing.JPanel thongTinNhanVien_container;
    private static javax.swing.JPanel nhaCungCap_container;
    private static javax.swing.JPanel TaiKhoan_container;
    private static javax.swing.JPanel thongKe_container;
    private static javax.swing.JPanel Trangchu_container;
    private static javax.swing.JPanel troGiup_container;

    private static javax.swing.JPanel sanPham_container;

    private static javax.swing.JLabel lbl_NhanVien;
    private static javax.swing.JLabel lbl_DangXuat;
    private static javax.swing.JLabel lbl_KhachHang;
    private static javax.swing.JLabel lbl_QuanLyTaiKhoan;
    private static javax.swing.JLabel lbl_hoaDon;
    private static javax.swing.JLabel lbl_nhaCungCap;
    private static javax.swing.JLabel lbl_thongTinNhanVien;
    private static javax.swing.JLabel lbl_sanPham;
    private static javax.swing.JLabel lbl_thongKe;
    private static javax.swing.JLabel lbl_Trangchu;
    private static javax.swing.JLabel lbl_troGiup;

    private javax.swing.JLabel txt_Header;

}
