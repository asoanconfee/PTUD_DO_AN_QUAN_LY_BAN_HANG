package gui;

import ConnectDB.KetNoiSQL;
import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import entity.NhanVien;
import entity.TaiKhoan;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QuanLyNhanVien extends javax.swing.JPanel {

    private boolean isThemActive = false;
    private boolean isSuaActive = false;
    private NhanVienDAO nhanVien_DAO = new NhanVienDAO();
    private TaiKhoanDAO taiKhoan_DAO = new TaiKhoanDAO();

    public QuanLyNhanVien() throws SQLException {
        KetNoiSQL.getInstance().connect();
        initComponents();
        designTable();
        tblDanhSachNhanVien();
    }

    private void designTable() {
        tbl_danhSachNhanVien.getTableHeader().setFont(new java.awt.Font("Open Sans", 1, 14));
        tbl_danhSachNhanVien.getTableHeader().setOpaque(false);
        tbl_danhSachNhanVien.getTableHeader().setBackground(new Color(198, 226, 255));
        tbl_danhSachNhanVien.getTableHeader().setForeground(new Color(16,54,103));
        tbl_danhSachNhanVien.setDefaultEditor(Object.class, null);
    }

    private void tblDanhSachNhanVien() {
        clearTable();
        DefaultTableModel dtm = (DefaultTableModel) tbl_danhSachNhanVien.getModel();
        ArrayList<NhanVien> listNhanVien = nhanVien_DAO.getAllNhanVienConHoatDong();

        for (NhanVien nhanVien : listNhanVien) {
            Object[] rowData = {nhanVien.getMaNhanVien(), nhanVien.getHoVaTen(), nhanVien.getChucVu(), nhanVien.getDiaChi(), nhanVien.getSdt(), nhanVien.getEmail(), nhanVien.getLuong()};
            dtm.addRow(rowData);
        }
    }

    public void clearTable() {
        DefaultTableModel dtm = (DefaultTableModel) tbl_danhSachNhanVien.getModel();
        dtm.setRowCount(0);
    }

    private boolean validateData() {
        String sdt = txt_soDienThoaiNhanVien.getText().trim();
        String email = txt_emailNhanVien.getText().trim();
        String HovaTen = txt_hoTenNhanVien.getText().trim();
        String luong = txt_luongNhanVien.getText().trim();
        if (txt_emailNhanVien.getText().equals("") || txt_soDienThoaiNhanVien.getText().equals("") || txt_hoTenNhanVien.getText().equals("")
                || txt_diaChiNhanVien.getText().equals("") || txt_luongNhanVien.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
            return false;
        }
        if (!(sdt.length() > 0 && sdt.matches("[0-9]{10}"))) {
            JOptionPane.showMessageDialog(txt_soDienThoaiNhanVien, "Số điện thoại sai định dạng");
            txt_soDienThoaiNhanVien.requestFocus();
            return false;
        }

        if (!(email.length() > 0 && email.matches("[a-zA-Z0-9._%-]+(@){1}[a-zA-Z]+(.){1}[a-zA-Z]{2,4}"))) {
            JOptionPane.showMessageDialog(this, "Email sai định dạng");
            txt_emailNhanVien.requestFocus();
            return false;
        }
        if (luong.length() > 0) {
            try {
                double y = Double.parseDouble(luong);
                if (y < 0) {
                    JOptionPane.showMessageDialog(this, "Lương phải lớn hơn 0");
                    txt_luongNhanVien.requestFocus();
                    return false;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Lương phải là số");
                return false;
            }
        }

        return true;
    }

    private void capNhatNhanVien() {
        if (!validateData()) {
            return;
        }
        NhanVien nhanVien = nhanVien_DAO.getNhanVienByID((String) tbl_danhSachNhanVien.getValueAt(tbl_danhSachNhanVien.getSelectedRow(), 0));

        nhanVien.setEmail(txt_emailNhanVien.getText());
        nhanVien.setHoVaTen(txt_hoTenNhanVien.getText());
        nhanVien.setDiaChi(txt_diaChiNhanVien.getText());
        nhanVien.setSdt(txt_soDienThoaiNhanVien.getText());
        nhanVien.setGioiTinh(cb_gioiTinh.getSelectedItem().toString() == "Nam" ? false : true);
        nhanVien.setChucVu(cb_chucVu.getSelectedItem().toString());
        try {
            nhanVien.setLuong(Integer.parseInt(txt_luongNhanVien.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(btn_luu, "vui lòng nhập lại lương");
         
        }

        Long date = date_ngaySinh.getDate().getTime();
        nhanVien.setNgaySinh(new Date(date));

        if (nhanVien_DAO.updateNhanVien(nhanVien) != -1) {
            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
            tblDanhSachNhanVien();
            clearInput();
            huyThaoTac();
            return;
        }
        JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
        huyThaoTac();
    }

    private void themNhanVien() throws SQLException {
        if (!validateData()) {
            return;
        }
        String maNhanVien = txt_maNhanVien.getText();
        String hoVaTen = txt_hoTenNhanVien.getText();
        String email = txt_emailNhanVien.getText();
        if (nhanVien_DAO.getNhanVienByGmail(email) != null) {
            JOptionPane.showMessageDialog(null, "Email đã tồn tại trong hệ thống. Vui lòng sử dụng email khác");
            return;
        }

        String sdt = txt_soDienThoaiNhanVien.getText();
        if (nhanVien_DAO.getNhanVienBySdt(sdt) != null) {
            JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại trong hệ thống. Vui lòng sử dụng sdt khác");
            return;
        }

        int luong = Integer.parseInt(txt_luongNhanVien.getText());
        String diaChi = txt_diaChiNhanVien.getText();
        boolean gioiTinh = cb_gioiTinh.getSelectedItem() == "Nam" ? false : true;
        String chucVu = cb_chucVu.getSelectedItem().toString();
        Date ngaySinh = date_ngaySinh.getDate();

        NhanVien nhanVien = new NhanVien(maNhanVien, hoVaTen, ngaySinh, diaChi, sdt, gioiTinh, luong, email, chucVu, null);
        if (nhanVien_DAO.addNhanVien(nhanVien) != -1) {
            KetNoiSQL.getInstance().connect();
            boolean loaiTaiKhoan = false;
            if (chucVu.equalsIgnoreCase("Quản lý")) {
                loaiTaiKhoan = true;
            }

            TaiKhoan tk = new TaiKhoan(maNhanVien, "1111", loaiTaiKhoan, nhanVien);
            if (taiKhoan_DAO.addTaiKhoan(tk) == -1) {
                return;
            }

            tblDanhSachNhanVien();
            clearInput();
            huyThaoTac();
            JOptionPane.showMessageDialog(null, "Thêm thành công");
            return;
        }

        JOptionPane.showMessageDialog(null, "Thêm thất bại");
    }

    private void huyThaoTac() {
        clearInput();
        isSuaActive = false;
        isThemActive = false;
        btn_them.setText("Thêm");
        btn_capNhat.setText("Cập nhật");
        btn_them.setEnabled(true);
        btn_capNhat.setEnabled(true);
        btn_xoa.setEnabled(true);
        btn_luu.setEnabled(false);
        txt_maNhanVien.setText("");
        isInputActive(false);
        tbl_danhSachNhanVien.clearSelection();
        date_ngaySinh.setDate(null);
    }

    private void clearInput() {

        txt_emailNhanVien.setText("");
        txt_hoTenNhanVien.setText("");
        txt_soDienThoaiNhanVien.setText("");
        txt_diaChiNhanVien.setText("");
        txt_luongNhanVien.setText("");
        date_ngaySinh.setDate(null);

    }

    private void isInputActive(boolean check) {
        txt_emailNhanVien.setEnabled(check);
        txt_hoTenNhanVien.setEnabled(check);
        txt_soDienThoaiNhanVien.setEnabled(check);
        txt_diaChiNhanVien.setEnabled(check);
        txt_luongNhanVien.setEnabled(check);
        cb_gioiTinh.setEnabled(check);
        cb_chucVu.setEnabled(check);
        date_ngaySinh.setEnabled(check);
    }

    private void thongTinNhanVien(NhanVien nhanVien) {
        txt_maNhanVien.setText(nhanVien.getMaNhanVien());
        txt_hoTenNhanVien.setText(nhanVien.getHoVaTen());
        cb_gioiTinh.setSelectedItem(nhanVien.getGioiTinh() == true ? "Nữ" : "Nam");
        cb_chucVu.setSelectedItem(nhanVien.getChucVu());
        date_ngaySinh.setDate(nhanVien.getNgaySinh());
        txt_diaChiNhanVien.setText(nhanVien.getDiaChi());
        txt_soDienThoaiNhanVien.setText(nhanVien.getSdt());
        txt_emailNhanVien.setText(nhanVien.getEmail());
        txt_luongNhanVien.setText(String.valueOf(nhanVien.getLuong()));

    }


    private void isThemNhanVienClicked(boolean check) {
        isThemActive = check;
        isSuaActive = !check;

        if (isThemActive) {
            NhanVien nhanVien = new NhanVien();
            txt_maNhanVien.setText(nhanVien.auto_ID());
            btn_them.setText("Huỷ");
            btn_capNhat.setEnabled(false);
            btn_xoa.setEnabled(false);

        } else if (isSuaActive) {
            btn_capNhat.setText("Huỷ");
            btn_them.setEnabled(false);
            btn_xoa.setEnabled(false);
        }

        btn_luu.setEnabled(true);
        isInputActive(true);
    }

 public void tblDanhSachNhanVienWithFilter() {
        tbl_danhSachNhanVien.clearSelection();
        clearTable();
        DefaultTableModel dtm = (DefaultTableModel) tbl_danhSachNhanVien.getModel();
        String tenNhanVien = "";
        if (txt_timNhanVien.getText().length() > 0) {
            tenNhanVien = txt_timNhanVien.getText();
        }

        ArrayList<NhanVien> listNhanVien = nhanVien_DAO.getAllNhanVien();
        for (NhanVien nv : listNhanVien) {
            if (nv.getHoVaTen().toLowerCase().contains(tenNhanVien.toLowerCase())) {
                Object[] rowData = {nv.getMaNhanVien(), nv.getHoVaTen(), nv.getChucVu(), nv.getDiaChi(), nv.getSdt(), nv.getEmail(), nv.getLuong()};
                dtm.addRow(rowData);
            }

        }
    }
    

    private void initComponents() {

        Chitietnhanvien = new javax.swing.JPanel();
        lbl_maNhanVien = new javax.swing.JLabel();
        txt_maNhanVien = new javax.swing.JTextField();
        txt_hoTenNhanVien = new javax.swing.JTextField();
        lbl_hoTenNhanVien = new javax.swing.JLabel();
        txt_diaChiNhanVien = new javax.swing.JTextField();
        lbl_diaChiNhanVien = new javax.swing.JLabel();
        lbl_soDienThoaiNhanVien = new javax.swing.JLabel();
        txt_soDienThoaiNhanVien = new javax.swing.JTextField();
        lbl_emailNhanVien = new javax.swing.JLabel();
        txt_emailNhanVien = new javax.swing.JTextField();
        lbl_luongNhanVien = new javax.swing.JLabel();
        txt_luongNhanVien = new javax.swing.JTextField();
        lbl_gioiTinh = new javax.swing.JLabel();
        cb_gioiTinh = new javax.swing.JComboBox<>();
        cb_chucVu = new javax.swing.JComboBox<>();
        lbl_chucVu = new javax.swing.JLabel();
        lbl_ngaySinh = new javax.swing.JLabel();
        date_ngaySinh = new com.toedter.calendar.JDateChooser();

        //chuyển định dạng ngày tháng đầu vào thành dd/MM/yyyy
        date_ngaySinh.setDateFormatString("dd/MM/yyyy");



        btn_capNhat = new javax.swing.JButton();
        btn_luu = new javax.swing.JButton();
        btn_them = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        btn_timNhanVien = new javax.swing.JButton();
        lbl_timNhanVien = new javax.swing.JLabel();
        txt_timNhanVien = new javax.swing.JTextField();
        Danhsachnhanvien = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_danhSachNhanVien = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setMaximumSize(new java.awt.Dimension(1130, 768));
        setPreferredSize(new java.awt.Dimension(1130, 768));

        Chitietnhanvien.setBackground(new java.awt.Color(255, 255, 255));
        Chitietnhanvien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Open Sans", 1, 14))); 

        lbl_maNhanVien.setFont(new java.awt.Font("Open Sans", 0, 12)); 
        lbl_maNhanVien.setText("Mã nhân viên:");

        txt_maNhanVien.setFont(new java.awt.Font("Open Sans", 0, 12)); 
        txt_maNhanVien.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_maNhanVien.setEnabled(false);

        lbl_hoTenNhanVien.setFont(new java.awt.Font("Open Sans", 0, 12)); 
        lbl_hoTenNhanVien.setText("Họ tên:");

        txt_hoTenNhanVien.setFont(new java.awt.Font("Open Sans", 0, 12)); 
        txt_hoTenNhanVien.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_hoTenNhanVien.setEnabled(false);
        
        lbl_diaChiNhanVien.setFont(new java.awt.Font("Open Sans", 0, 12)); 
        lbl_diaChiNhanVien.setText("Địa chỉ:");

        txt_diaChiNhanVien.setFont(new java.awt.Font("Open Sans", 0, 12)); 
        txt_diaChiNhanVien.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_diaChiNhanVien.setEnabled(false);


        lbl_soDienThoaiNhanVien.setFont(new java.awt.Font("Open Sans", 0, 12)); 
        lbl_soDienThoaiNhanVien.setText("Số điện thoại:");

        txt_soDienThoaiNhanVien.setFont(new java.awt.Font("Open Sans", 0, 12)); 
        txt_soDienThoaiNhanVien.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_soDienThoaiNhanVien.setEnabled(false);

        lbl_emailNhanVien.setFont(new java.awt.Font("Open Sans", 0, 12)); 
        lbl_emailNhanVien.setText("Email:");

        txt_emailNhanVien.setFont(new java.awt.Font("Open Sans", 0, 12)); 
        txt_emailNhanVien.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_emailNhanVien.setEnabled(false);

        lbl_luongNhanVien.setFont(new java.awt.Font("Open Sans", 0, 12)); 
        lbl_luongNhanVien.setText("Lương:");

        txt_luongNhanVien.setFont(new java.awt.Font("Open Sans", 0, 12)); 
        txt_luongNhanVien.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_luongNhanVien.setEnabled(false);

        lbl_gioiTinh.setFont(new java.awt.Font("Open Sans", 0, 12)); 
        lbl_gioiTinh.setText("Giới tính:");

        cb_gioiTinh.setFont(new java.awt.Font("Open Sans", 0, 12)); 
        cb_gioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Nam", "Nữ" }));

        lbl_chucVu.setFont(new java.awt.Font("Open Sans", 0, 12)); 
        lbl_chucVu.setText("Chức vụ:");

        cb_chucVu.setFont(new java.awt.Font("Open Sans", 0, 12)); 
        cb_chucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Quản lý", "Nhân viên" }));

        lbl_ngaySinh.setFont(new java.awt.Font("Open Sans", 0, 12)); 
        lbl_ngaySinh.setText("Ngày sinh");

        date_ngaySinh.setBackground(new java.awt.Color(255, 255, 255));

        btn_capNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png"))); 
        btn_capNhat.setText("Cập nhật");
        btn_capNhat.setFont(new java.awt.Font("Open Sans", 1, 12));
        btn_capNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_capNhatActionPerformed(evt);
            }
        });

        btn_luu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Upload.png"))); 
        btn_luu.setText("Lưu");
        btn_luu.setFont(new java.awt.Font("Open Sans", 1, 12));
        btn_luu.setEnabled(false);
        btn_luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					btn_luuActionPerformed(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        btn_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Approve.png"))); 
        btn_them.setText("Thêm");
        btn_them.setFont(new java.awt.Font("Open Sans", 1, 12));
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); 
        btn_xoa.setText("Xoá");
        btn_xoa.setFont(new java.awt.Font("Open Sans", 1, 12));
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        btn_timNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); 
        btn_timNhanVien.setText("Tìm");
        btn_timNhanVien.setFont(new java.awt.Font("Open Sans", 1, 12));
        btn_timNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timNhanVienActionPerformed(evt);
            }
        });

        lbl_timNhanVien.setFont(new java.awt.Font("Open Sans", 0, 12)); 
        lbl_timNhanVien.setText("Tìm nhân viên:");

        javax.swing.GroupLayout ChitietnhanvienLayout = new javax.swing.GroupLayout(Chitietnhanvien);
        Chitietnhanvien.setLayout(ChitietnhanvienLayout);
        ChitietnhanvienLayout.setHorizontalGroup(
            ChitietnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChitietnhanvienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ChitietnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ChitietnhanvienLayout.createSequentialGroup()
                        .addComponent(lbl_timNhanVien)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_timNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_timNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(ChitietnhanvienLayout.createSequentialGroup()
                        .addGroup(ChitietnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_maNhanVien)
                            .addComponent(lbl_hoTenNhanVien)
                            .addGroup(ChitietnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txt_hoTenNhanVien, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_maNhanVien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_gioiTinh)
                            .addComponent(cb_gioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addGroup(ChitietnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_ngaySinh)
                            .addComponent(lbl_chucVu)
                            .addComponent(lbl_luongNhanVien)
                            .addComponent(cb_chucVu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(date_ngaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_luongNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(ChitietnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_diaChiNhanVien)
                            .addComponent(txt_diaChiNhanVien)
                            .addComponent(lbl_soDienThoaiNhanVien)
                            .addComponent(txt_soDienThoaiNhanVien)
                            .addComponent(lbl_emailNhanVien)
                            .addComponent(txt_emailNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)))
                .addGroup(ChitietnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ChitietnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn_xoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_capNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        ChitietnhanvienLayout.setVerticalGroup(
            ChitietnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChitietnhanvienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ChitietnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ChitietnhanvienLayout.createSequentialGroup()
                        .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_capNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ChitietnhanvienLayout.createSequentialGroup()
                        .addGroup(ChitietnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ChitietnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn_timNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ChitietnhanvienLayout.createSequentialGroup()
                            	.addGap(5, 5, 5)
                            	.addComponent(lbl_timNhanVien))
                                .addGap(2, 2, 2)
                                .addComponent(txt_timNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(ChitietnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(ChitietnhanvienLayout.createSequentialGroup()
                                .addComponent(date_ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_chucVu)
                                .addGap(0, 0, 0)
                                .addComponent(cb_chucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(lbl_luongNhanVien)
                                .addGap(0, 0, 0)
                                .addComponent(txt_luongNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ChitietnhanvienLayout.createSequentialGroup()
                                .addGroup(ChitietnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_maNhanVien)
                                    .addComponent(lbl_ngaySinh))
                                .addGap(2, 2, 2)
                                .addComponent(txt_maNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_hoTenNhanVien)
                                .addGap(2, 2, 2)
                                .addComponent(txt_hoTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_gioiTinh)
                                .addGap(0, 0, 0)
                                .addComponent(cb_gioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(ChitietnhanvienLayout.createSequentialGroup()
                        .addComponent(lbl_emailNhanVien)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_emailNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_soDienThoaiNhanVien)
                        .addGap(0, 0, 0)
                        .addComponent(txt_soDienThoaiNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lbl_diaChiNhanVien)
                        .addGap(0, 0, 0)
                        .addComponent(txt_diaChiNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Danhsachnhanvien.setBackground(new java.awt.Color(255, 255, 255));
        Danhsachnhanvien.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách nhân viên"));

        tbl_danhSachNhanVien.setFont(new java.awt.Font("Open Sans", 1, 14)); 
        tbl_danhSachNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
   
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Chức vụ", "Địa chỉ", "Số điện thoại", "Email", "Lương"
            }
        ));
        tbl_danhSachNhanVien.setRowHeight(30);
        tbl_danhSachNhanVien.setShowVerticalLines(true);
        tbl_danhSachNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_danhSachNhanVienMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_danhSachNhanVien);

        javax.swing.GroupLayout DanhsachnhanvienLayout = new javax.swing.GroupLayout(Danhsachnhanvien);
        Danhsachnhanvien.setLayout(DanhsachnhanvienLayout);
        DanhsachnhanvienLayout.setHorizontalGroup(
            DanhsachnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DanhsachnhanvienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        DanhsachnhanvienLayout.setVerticalGroup(
            DanhsachnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DanhsachnhanvienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Danhsachnhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Chitietnhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Chitietnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Danhsachnhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    private void tbl_danhSachNhanVienMousePressed(java.awt.event.MouseEvent evt) {
        String id = (String) tbl_danhSachNhanVien.getValueAt(tbl_danhSachNhanVien.getSelectedRow(), 0);
        NhanVien nhanVien = nhanVien_DAO.getNhanVienByID(id);
        if (nhanVien == null) {
            System.out.println("Không tìm thấy nhân viên");
            return;
        }
        thongTinNhanVien(nhanVien);
    }

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {
        if (btn_them.getText().equals("Thêm")) {
            isThemActive = true;
            isThemNhanVienClicked(isThemActive);
            clearInput();
        } else if (btn_them.getText().equals("Huỷ")) {
            huyThaoTac();
        }
    }

    private void btn_capNhatActionPerformed(java.awt.event.ActionEvent evt) {
        if (tbl_danhSachNhanVien.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn dữ liệu để cập nhật");
            return;
        }

        if (btn_capNhat.getText().equals("Cập nhật")) {
            isThemActive = false;
            isThemNhanVienClicked(isThemActive);
        } else if (btn_capNhat.getText().equals("Huỷ")) {
            huyThaoTac();
            date_ngaySinh.setDate(null);
        }
    }

    private void btn_luuActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
        if (isThemActive) {
            themNhanVien();
        } else if (isSuaActive) {
            capNhatNhanVien();
        }
    }

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {
        if (tbl_danhSachNhanVien.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn dữ liệu để xoá");
            return;
        }

        String maNhanVien = tbl_danhSachNhanVien.getValueAt(tbl_danhSachNhanVien.getSelectedRow(), 0).toString();
        if (nhanVien_DAO.xoaNhanVien(maNhanVien) != -1) {
            if (taiKhoan_DAO.xoaTaiKhoan(maNhanVien) == -1) {
                JOptionPane.showMessageDialog(null, "xoá tài khoản thất bại");
                return;
            }
            JOptionPane.showMessageDialog(null, "Xoá thành công");
            tblDanhSachNhanVien();
            clearInput();
            return;
        };

        JOptionPane.showMessageDialog(null, "Xoá thất bại");
    }

    private void btn_timNhanVienActionPerformed(java.awt.event.ActionEvent evt) {
        tblDanhSachNhanVienWithFilter();
        
    }


    
    private javax.swing.JButton btn_capNhat;
    private javax.swing.JButton btn_luu;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_timNhanVien;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JComboBox<String> cb_chucVu;
    private javax.swing.JComboBox<String> cb_gioiTinh;
    private com.toedter.calendar.JDateChooser date_ngaySinh;
    private javax.swing.JPanel Danhsachnhanvien;
    private javax.swing.JPanel Chitietnhanvien;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_chucVu;
    private javax.swing.JLabel lbl_diaChiNhanVien;
    private javax.swing.JLabel lbl_emailNhanVien;
    private javax.swing.JLabel lbl_gioiTinh;
    private javax.swing.JLabel lbl_hoTenNhanVien;
    private javax.swing.JLabel lbl_luongNhanVien;
    private javax.swing.JLabel lbl_maNhanVien;
    private javax.swing.JLabel lbl_ngaySinh;
    private javax.swing.JLabel lbl_soDienThoaiNhanVien;
    private javax.swing.JLabel lbl_timNhanVien;
    private javax.swing.JTable tbl_danhSachNhanVien;
    private javax.swing.JTextField txt_diaChiNhanVien;
    private javax.swing.JTextField txt_emailNhanVien;
    private javax.swing.JTextField txt_hoTenNhanVien;
    private javax.swing.JTextField txt_luongNhanVien;
    private javax.swing.JTextField txt_maNhanVien;
    private javax.swing.JTextField txt_soDienThoaiNhanVien;
    private javax.swing.JTextField txt_timNhanVien;
    
}
