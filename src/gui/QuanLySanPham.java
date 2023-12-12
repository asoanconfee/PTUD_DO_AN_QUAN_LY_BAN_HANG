package gui;

public class QuanLySanPham extends javax.swing.JPanel {

    public QuanLySanPham() {
        initComponents();
        addTabbedPane();
    }

    private void addTabbedPane(){
        tabbedPane.add("Sản Phẩm Mới",new TabThongTinSanPham());
        tabbedPane.add("Khuyến Mãi", new TabSanPhamKhuyenMai());
        tabbedPane.add("Thêm Thuộc Tính Sản Phẩm",new TabThuocTinhSanPham());
     
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

}
