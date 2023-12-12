
package gui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import entity.TaiKhoan;


public class QuanLyTaiKhoan extends javax.swing.JPanel {

    private TaiKhoanDAO taiKhoanDao;
    private NhanVienDAO nhanVienDao;
    
    public QuanLyTaiKhoan() {
        initComponents();
        tblDanhSachTaiKhoan();
        designTable();
    }

    //tạo table
    private void designTable() {
        tbl_DanhSachTaiKhoan.getTableHeader().setFont(new java.awt.Font("Calibri", 0, 12));
        tbl_DanhSachTaiKhoan.getTableHeader().setOpaque(false);
        tbl_DanhSachTaiKhoan.getTableHeader().setBackground(new Color(198, 226, 255));
        tbl_DanhSachTaiKhoan.getTableHeader().setForeground(new Color(16,54,103));
        tbl_DanhSachTaiKhoan.setDefaultEditor(Object.class, null); //Không cho phép chỉnh sửa

    }

    //lấy dữ liệu từ database và đổ vào table
    private void tblDanhSachTaiKhoan() {
        taiKhoanDao = new TaiKhoanDAO();
        nhanVienDao = new NhanVienDAO();
        clearTable();
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachTaiKhoan.getModel();
        ArrayList<TaiKhoan> listTaiKhoan = taiKhoanDao.getAllTaiKhoanConHoatDong();
        for (TaiKhoan taiKhoan : listTaiKhoan) {
            Object[] rowData = {taiKhoan.getTenTaiKhoan(),taiKhoan.getMatKhau(), taiKhoan.getNhanVien().getHoVaTen(), taiKhoan.getLoaiTaiKhoan() == true ? "Quản lý" : "Nhân viên"};
            dtm.addRow(rowData);
        }
    }

    //xoá dữ liệu trong table
    private void clearTable() {
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachTaiKhoan.getModel();
        dtm.setRowCount(0);
    }

    //xoá dữ liệu trong textfield
    private void clearInformation(){
        txt_ChucVu.setText("");
        txt_MaNhanVien.setText("");
        txt_MatKhau.setText("");
        txt_TenNhanVien.setText("");
        txt_TenTaiKhoan.setText("");
    }

    //button chức năng đổi mật khẩu
    private void buttonHandler(){
        if(tbl_DanhSachTaiKhoan.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn dữ liệu để cập nhật");
            return;
        }
        
        TaiKhoan taiKhoan = taiKhoanDao.getTaiKhoanByTK((String) tbl_DanhSachTaiKhoan.getValueAt(tbl_DanhSachTaiKhoan.getSelectedRow(), 0));
        if(taiKhoan == null){
            JOptionPane.showMessageDialog(null, "Không tìm thấy tài khoản");
            return;
        }
        //lấy mật khẩu vừa nhập ở txt_MatKhau
        taiKhoan.setMatKhau(txt_MatKhau.getText());
        if(taiKhoanDao.updateMatKhau(taiKhoan) == -1){
            JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
            return;
        }
        JOptionPane.showMessageDialog(null, "Cập nhật thành công");
        tblDanhSachTaiKhoan();
        clearInformation();
    }




    //giao diện
    private void initComponents() {

        pnl_DanhSachTaiKhoan = new javax.swing.JPanel();
        scr_DanhSachTaiKhoan = new javax.swing.JScrollPane();
        tbl_DanhSachTaiKhoan = new javax.swing.JTable();
        pnl_ChiTietTaiKhoan = new javax.swing.JPanel();
        lbl_TentaiKhoan = new javax.swing.JLabel();
        txt_TenTaiKhoan = new javax.swing.JTextField();
        lbl_MatKhau = new javax.swing.JLabel();
        txt_MatKhau = new javax.swing.JTextField();
        lbl_MaNhanVien = new javax.swing.JLabel();
        txt_MaNhanVien = new javax.swing.JTextField();
        lbl_TenNhanVien = new javax.swing.JLabel();
        txt_TenNhanVien = new javax.swing.JTextField();
        lbl_ChucVu = new javax.swing.JLabel();
        txt_ChucVu = new javax.swing.JTextField();
        btn_DatMatKhauMacDinh = new javax.swing.JButton();
        lbl_TimKiem = new javax.swing.JLabel();
        txt_TimKiem = new javax.swing.JTextField();
        btn_TimKiem = new javax.swing.JButton();
        

        txt_MaNhanVien.setEnabled(false);
        txt_MaNhanVien.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_TenNhanVien.setEnabled(false);
        txt_TenNhanVien.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_ChucVu.setEnabled(false);
        txt_ChucVu.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_TenTaiKhoan.setEnabled(false);
        txt_TenTaiKhoan.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        setBackground(new java.awt.Color(255, 255, 255));

        pnl_DanhSachTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        pnl_DanhSachTaiKhoan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách tài khoản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14)));

        tbl_DanhSachTaiKhoan.setFont(new java.awt.Font("Calibri", 0, 12));
        tbl_DanhSachTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Tài Khoản", "Mật khẩu", "Tên Nhân Viên", "Chức vụ"
            }
        ));
        tbl_DanhSachTaiKhoan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbl_DanhSachTaiKhoan.setRowHeight(30);
        tbl_DanhSachTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_DanhSachTaiKhoanMousePressed(evt);
            }
        });
        scr_DanhSachTaiKhoan.setViewportView(tbl_DanhSachTaiKhoan);

        javax.swing.GroupLayout pnl_DanhSachTaiKhoanLayout = new javax.swing.GroupLayout(pnl_DanhSachTaiKhoan);
        pnl_DanhSachTaiKhoan.setLayout(pnl_DanhSachTaiKhoanLayout);
        pnl_DanhSachTaiKhoanLayout.setHorizontalGroup(
            pnl_DanhSachTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_DanhSachTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scr_DanhSachTaiKhoan)
                .addContainerGap())
        );
        pnl_DanhSachTaiKhoanLayout.setVerticalGroup(
            pnl_DanhSachTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_DanhSachTaiKhoanLayout.createSequentialGroup()
                .addComponent(scr_DanhSachTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnl_ChiTietTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        pnl_ChiTietTaiKhoan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết Tài Khoản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14)));

        lbl_TentaiKhoan.setFont(new java.awt.Font("Calibri", 0, 12));
        lbl_TentaiKhoan.setText("Tên tài khoản:");

        txt_TenTaiKhoan.setFont(new java.awt.Font("Calibri", 0, 12));
        txt_TenTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TenTaiKhoanActionPerformed(evt);
            }
        });

        lbl_MatKhau.setFont(new java.awt.Font("Calibri", 0, 12));
        lbl_MatKhau.setText("Mật khẩu:");

        txt_MatKhau.setFont(new java.awt.Font("Calibri", 0, 12));


        lbl_MaNhanVien.setFont(new java.awt.Font("Calibri", 0, 12));
        lbl_MaNhanVien.setText("Mã nhân viên:");

        txt_MaNhanVien.setFont(new java.awt.Font("Calibri", 0, 12));

        lbl_TenNhanVien.setFont(new java.awt.Font("Calibri", 0, 12));
        lbl_TenNhanVien.setText("Tên nhân viên:");

        txt_TenNhanVien.setFont(new java.awt.Font("Calibri", 0, 12));

        lbl_ChucVu.setFont(new java.awt.Font("Calibri", 0, 12));
        lbl_ChucVu.setText("Chức vụ:");

        txt_ChucVu.setFont(new java.awt.Font("Calibri", 0, 12));

        btn_DatMatKhauMacDinh.setFont(new java.awt.Font("Calibri", 0, 12));
        btn_DatMatKhauMacDinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png")));
        btn_DatMatKhauMacDinh.setText("Đổi mật khẩu");
        btn_DatMatKhauMacDinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_DatMatKhauMacDinhMouseClicked(evt);
            }
        });

        lbl_TimKiem.setFont(new java.awt.Font("Calibri", 0, 12));
        lbl_TimKiem.setText("Tìm kiếm tài khoản:");

        txt_TimKiem.setFont(new java.awt.Font("Calibri", 0, 12));

        btn_TimKiem.setFont(new java.awt.Font("Calibri", 0, 12));
        btn_TimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png")));
        btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_ChiTietTaiKhoanLayout = new javax.swing.GroupLayout(pnl_ChiTietTaiKhoan);
        pnl_ChiTietTaiKhoan.setLayout(pnl_ChiTietTaiKhoanLayout);
        pnl_ChiTietTaiKhoanLayout.setHorizontalGroup(
            pnl_ChiTietTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ChiTietTaiKhoanLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnl_ChiTietTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_ChiTietTaiKhoanLayout.createSequentialGroup()
                        .addComponent(lbl_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGap(5, 5, 5)
                        .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_DatMatKhauMacDinh, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(pnl_ChiTietTaiKhoanLayout.createSequentialGroup()
                        .addGroup(pnl_ChiTietTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_TenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_MaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_MaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_TenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnl_ChiTietTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_TentaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_TenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnl_ChiTietTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)))))
        );
        pnl_ChiTietTaiKhoanLayout.setVerticalGroup(
            pnl_ChiTietTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ChiTietTaiKhoanLayout.createSequentialGroup()
                .addGroup(pnl_ChiTietTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_ChiTietTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_DatMatKhauMacDinh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnl_ChiTietTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_ChiTietTaiKhoanLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnl_ChiTietTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_ChiTietTaiKhoanLayout.createSequentialGroup()
                                .addComponent(lbl_MaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txt_MaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_TenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txt_TenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_ChiTietTaiKhoanLayout.createSequentialGroup()
                                .addComponent(lbl_TentaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txt_TenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txt_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(71, 71, 71))
                    .addGroup(pnl_ChiTietTaiKhoanLayout.createSequentialGroup()
                        .addComponent(lbl_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txt_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                        
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnl_ChiTietTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_DanhSachTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_ChiTietTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_DanhSachTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnl_DanhSachTaiKhoan.getAccessibleContext().setAccessibleDescription("");
    }

    private void txt_TenTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {
        
    }

    private void tbl_DanhSachTaiKhoanMousePressed(java.awt.event.MouseEvent evt) {
        int row = tbl_DanhSachTaiKhoan.getSelectedRow();
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachTaiKhoan.getModel();

        String tenTaiKhoan = dtm.getValueAt(row, 0).toString();
        taiKhoanDao = new TaiKhoanDAO();
        TaiKhoan taiKhoan = taiKhoanDao.getTaiKhoanByName(tenTaiKhoan);

        txt_TenTaiKhoan.setText(taiKhoan.getTenTaiKhoan());
        txt_MatKhau.setText(taiKhoan.getMatKhau());
        txt_MaNhanVien.setText(taiKhoan.getNhanVien().getMaNhanVien());
        txt_TenNhanVien.setText(taiKhoan.getNhanVien().getHoVaTen());
        txt_ChucVu.setText(taiKhoan.getNhanVien().getChucVu());
    }

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {
        clearTable();
        String keyWord = txt_TimKiem.getText().trim().toLowerCase();
        taiKhoanDao = new TaiKhoanDAO();
        ArrayList<TaiKhoan> listTaiKhoan = taiKhoanDao.getAllTaiKhoanConHoatDong();
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachTaiKhoan.getModel();
        for (TaiKhoan taiKhoan : listTaiKhoan) {
            if (taiKhoan.getTenTaiKhoan().toLowerCase().contains(keyWord) || taiKhoan.getNhanVien().getHoVaTen().toLowerCase().contains(keyWord) || (taiKhoan.getLoaiTaiKhoan() == true ? "Quản lý" : "Nhân viên").toLowerCase().contains(keyWord)) {
                    Object[] rowData = {taiKhoan.getTenTaiKhoan(),taiKhoan.getMatKhau(), taiKhoan.getNhanVien().getHoVaTen(), taiKhoan.getLoaiTaiKhoan() == true ? "Quản lý" : "Nhân viên"};
                    dtm.addRow(rowData);
            }
        }
    }

    private void btn_DatMatKhauMacDinhMouseClicked(java.awt.event.MouseEvent evt) {   
        buttonHandler();
    }


    private javax.swing.JButton btn_DatMatKhauMacDinh;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JPanel pnl_DanhSachTaiKhoan;
    private javax.swing.JPanel pnl_ChiTietTaiKhoan;
    private javax.swing.JScrollPane scr_DanhSachTaiKhoan;
    private javax.swing.JLabel lbl_ChucVu;
    private javax.swing.JLabel lbl_MaNhanVien;
    private javax.swing.JLabel lbl_MatKhau;
    private javax.swing.JLabel lbl_TenNhanVien;
    private javax.swing.JLabel lbl_TentaiKhoan;
    private javax.swing.JLabel lbl_TimKiem;
    private javax.swing.JTable tbl_DanhSachTaiKhoan;
    private javax.swing.JTextField txt_ChucVu;
    private javax.swing.JTextField txt_MaNhanVien;
    private javax.swing.JTextField txt_MatKhau;
    private javax.swing.JTextField txt_TenNhanVien;
    private javax.swing.JTextField txt_TenTaiKhoan;
    private javax.swing.JTextField txt_TimKiem;

}
