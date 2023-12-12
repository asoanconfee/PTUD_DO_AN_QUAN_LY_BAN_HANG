package gui;

import java.sql.SQLException;

import entity.NhanVien;

public class QuanLyBanHang extends javax.swing.JPanel {

        public QuanLyBanHang() throws SQLException {
            initComponents();
            addTabbedPane();
            setRole();
        }

    private void setRole() {
			// TODO Auto-generated method stub
			
		}

	private void addTabbedPane(){
        tabbedPane.add("Đặt hàng",new TabBanHang());
//        tabbedPane.add("Hóa đơn tạm",new TabThuocTinhSanPham());
    }

    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setMaximumSize(new java.awt.Dimension(1130, 773));
        setMinimumSize(new java.awt.Dimension(1130, 773));
        setPreferredSize(new java.awt.Dimension(1130, 773));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        tabbedPane.setMaximumSize(new java.awt.Dimension(1130, 775));
        tabbedPane.setMinimumSize(new java.awt.Dimension(1130, 775));
        tabbedPane.setPreferredSize(new java.awt.Dimension(1130, 775));
        add(tabbedPane);
    }

    private javax.swing.JTabbedPane tabbedPane;
        // xem quyền của nhân viên, từ đó hiện các chức năng tương ứng
   
}
