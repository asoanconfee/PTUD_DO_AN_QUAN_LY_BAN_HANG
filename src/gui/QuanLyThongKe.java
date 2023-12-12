package gui;

public class QuanLyThongKe extends javax.swing.JPanel {

    public QuanLyThongKe() {
        initComponents();
        addTabedpane();
    }

    private void addTabedpane(){
        tabbedPane.add("Hóa Đơn", new TabThongKeHoaDon());
        tabbedPane.add("Sản Phẩm", new TabThongKeSanPham());
        tabbedPane.add("Doanh Thu", new TabThongKeDoanhThu());
        
    }
    
    private void initComponents() {
        tabbedPane = new javax.swing.JTabbedPane();
        setPreferredSize(new java.awt.Dimension(1130, 680));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
        add(tabbedPane);
    }

    private javax.swing.JTabbedPane tabbedPane;

}
