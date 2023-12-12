package gui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.NhaCungCapDAO;
import entity.NhaCungCap;


public class QuanLyNhaCungCap extends javax.swing.JPanel {

    private NhaCungCapDAO nhaCC_DAO = new NhaCungCapDAO();
    private boolean isThemActive = false;
    private boolean isSuaActive = false;
    
    public QuanLyNhaCungCap() {
        initComponents();
        designTable();
        tblDanhSachNhaCungCap();

    }
    
    private void designTable(){
        tbl_DanhSachNhaCungCap.getTableHeader().setFont(new java.awt.Font("Calibri", 0, 12));
        tbl_DanhSachNhaCungCap.getTableHeader().setOpaque(false);
        tbl_DanhSachNhaCungCap.getTableHeader().setBackground(new Color(198, 226, 255));
        tbl_DanhSachNhaCungCap.getTableHeader().setForeground(new Color(16,54,103));
        tbl_DanhSachNhaCungCap.setDefaultEditor(Object.class, null);
    }
    
    //button thêm nhà cung cấp
    private void isThemNhaCungCapClicked(boolean check){
        isThemActive = check;
        isSuaActive = !check;
        
        if(isThemActive){
            btn_Them.setText("Huỷ");
            btn_sua.setEnabled(false);
        }
        else if(isSuaActive){
            btn_sua.setText("Huỷ");
            btn_Them.setEnabled(false);
        }
        
        btn_luu.setEnabled(true);
        isInputActive(true);
    }
    
    //button huỷ thao tác
    public void huyThaoTac(){
        isSuaActive = false;
        isThemActive = false;
        btn_Them.setText("Thêm");
        btn_sua.setText("Cập nhật");
        btn_Them.setEnabled(true);
        btn_sua.setEnabled(true);
        btn_luu.setEnabled(false);
        tbl_DanhSachNhaCungCap.clearSelection();    
        isInputActive(false);
    }

    //set enable cho các input textfield
    private void isInputActive(boolean check){
        txt_diaChi.setEnabled(check);
        txt_email.setEnabled(check);
        txt_nhaCungCap.setEnabled(check);
        txt_soDienThoai.setEnabled(check);
    }
    
    //lấy dữ liệu từ database và đổ vào table
    private void tblDanhSachNhaCungCap(){
        clearTable();
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachNhaCungCap.getModel();
        ArrayList<NhaCungCap> listNhaCungCap = nhaCC_DAO.getAllNhaCungCap();
        
        for(NhaCungCap ncc : listNhaCungCap){
            Object[] rowData = {ncc.getMaNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getSdt(), ncc.getEmail(), ncc.getDiaChi()};
            dtm.addRow(rowData);
        }
    }
    
    //xoá các dòng trong table
    private void clearTable(){
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachNhaCungCap.getModel();
        dtm.setRowCount(0);
    }

    //xoá các input textfield
    private void clearInput(){
        txt_diaChi.setText("");
        txt_email.setText("");
        txt_nhaCungCap.setText("");
        txt_soDienThoai.setText("");
    }

    //regex đầu vào khi thêm hoặc sửa dữ liệu nhà cung cấp
    private boolean isValidInput(){
        //tạo regex cho các textfield
        String regexSoDienThoai = "^[0-9]{10,11}$"; //-> 0123456789
        String regexEmail = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)$"; // -> abc@abc.abc

        //lấy giá trị từ các textfield
        String tenNhaCungCap = txt_nhaCungCap.getText();
        String sdt = txt_soDienThoai.getText();
        String email = txt_email.getText();
        String diaChi = txt_diaChi.getText();

        //kiểm tra các giá trị với regex
        if(txt_diaChi.getText().equals("") || txt_email.getText().equals("") || txt_nhaCungCap.getText().equals("") || txt_soDienThoai.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Điền đầy đủ thông tin");
            return false;
        }
        if(!sdt.matches(regexSoDienThoai)){
            JOptionPane.showMessageDialog(null, "Số điện thoại phải từ 10 đến 11 số");
            return false;
        }
        if(!email.matches(regexEmail)){
            JOptionPane.showMessageDialog(null, "Email không hợp lệ");
            return false;
        }
        return true;
    }
    
    //thêm nhà cung cấp
    private void themNhaCungCapHandler(){
        if(!isValidInput()) return;
        
        String tenNhaCungCap = txt_nhaCungCap.getText();
        String sdt = txt_soDienThoai.getText();
        String diaChi = txt_diaChi.getText();
        String email = txt_email.getText();
        
        NhaCungCap ncc = new NhaCungCap(tenNhaCungCap, diaChi, sdt, email);
        
        if(nhaCC_DAO.addNhaCungCap(ncc) != -1){
            JOptionPane.showMessageDialog(null, "Thêm thành công");
            tblDanhSachNhaCungCap();
            clearInput();
            huyThaoTac();
            return;
        }
        JOptionPane.showMessageDialog(null, "Thêm thất bại");
        huyThaoTac();
    }
    
    //cập nhật nhà cung cấp
    private void capNhatNhaCungCapHandler(){
        
        if (!isValidInput()) {
            return;
        }
        String id = (String) tbl_DanhSachNhaCungCap.getValueAt(tbl_DanhSachNhaCungCap.getSelectedRow(), 0);
        NhaCungCap ncc = nhaCC_DAO.getNhaCungCap(id);

        ncc.setDiaChi(txt_diaChi.getText());
        ncc.setEmail(txt_email.getText());
        ncc.setSdt(txt_soDienThoai.getText().trim());
        ncc.setTenNhaCungCap(txt_nhaCungCap.getText());
        

        if (nhaCC_DAO.updateNhaCungCap(ncc) != -1) {
            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
           tblDanhSachNhaCungCap();
            clearInput();    
            huyThaoTac();
            return;
        }
        JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
        huyThaoTac();
    }


    private void initComponents() {

        pnl_DanhSachNhaCungCap = new javax.swing.JPanel();
        scr_DanhSachNhaCungCap = new javax.swing.JScrollPane();
        tbl_DanhSachNhaCungCap = new javax.swing.JTable();
        pnl_ChiTietNhaCungCap = new javax.swing.JPanel();
        lbl_tenNhaCungCap = new javax.swing.JLabel();
        txt_nhaCungCap = new javax.swing.JTextField();
        txt_diaChi = new javax.swing.JTextField();
        lbl_diaChi = new javax.swing.JLabel();
        lbl_soDienThoai = new javax.swing.JLabel();
        txt_soDienThoai = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        lbl_email = new javax.swing.JLabel();
        btn_Them = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_luu = new javax.swing.JButton();
        lbl_timNhaCungCap = new javax.swing.JLabel();
        txt_timNhaCungCap = new javax.swing.JTextField();
        btn_Tim = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_DanhSachNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        pnl_DanhSachNhaCungCap.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách nhà cung cấp"));

        tbl_DanhSachNhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Email", "Địa chỉ"
            }
        ));
        tbl_DanhSachNhaCungCap.setRowHeight(30);
        tbl_DanhSachNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_DanhSachNhaCungCapMousePressed(evt);
            }
        });
        scr_DanhSachNhaCungCap.setViewportView(tbl_DanhSachNhaCungCap);

        javax.swing.GroupLayout pnl_DanhSachNhaCungCapLayout = new javax.swing.GroupLayout(pnl_DanhSachNhaCungCap);
        pnl_DanhSachNhaCungCap.setLayout(pnl_DanhSachNhaCungCapLayout);
        pnl_DanhSachNhaCungCapLayout.setHorizontalGroup(
            pnl_DanhSachNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_DanhSachNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scr_DanhSachNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 1078, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_DanhSachNhaCungCapLayout.setVerticalGroup(
            pnl_DanhSachNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_DanhSachNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scr_DanhSachNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(pnl_DanhSachNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 1100, 520));

        pnl_ChiTietNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        pnl_ChiTietNhaCungCap.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết nhà cung cấp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14)));

        lbl_tenNhaCungCap.setFont(new java.awt.Font("Calibri", 0, 12));
        lbl_tenNhaCungCap.setText("Tên nhà cung cấp:");

        txt_nhaCungCap.setFont(new java.awt.Font("Calibri", 0, 12));
        txt_nhaCungCap.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_nhaCungCap.setEnabled(false);

        txt_diaChi.setFont(new java.awt.Font("Calibri", 0, 12));
        txt_diaChi.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_diaChi.setEnabled(false);

        lbl_diaChi.setFont(new java.awt.Font("Calibri", 0, 12));
        lbl_diaChi.setText("Địa chỉ:");

        lbl_soDienThoai.setFont(new java.awt.Font("Calibri", 0, 12));
        lbl_soDienThoai.setText("Số điện thoại:");

        txt_soDienThoai.setFont(new java.awt.Font("Calibri", 0, 12));
        txt_soDienThoai.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_soDienThoai.setEnabled(false);

        txt_email.setFont(new java.awt.Font("Calibri", 0, 12));
        txt_email.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_email.setEnabled(false);

        lbl_email.setFont(new java.awt.Font("Calibri", 0, 12));
        lbl_email.setText("Email:");

        btn_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Approve.png")));
        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        btn_sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png")));
        btn_sua.setText("Cập nhật");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        btn_luu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Upload.png")));
        btn_luu.setText("Lưu");
        btn_luu.setEnabled(false);
        btn_luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_luuActionPerformed(evt);
            }
        });

        lbl_timNhaCungCap.setFont(new java.awt.Font("Calibri", 0, 12));
        lbl_timNhaCungCap.setText("Tìm kiếm nhà cung cấp:");


        btn_Tim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png")));
        btn_Tim.setText("Tìm");
        btn_Tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_ChiTietNhaCungCapLayout = new javax.swing.GroupLayout(pnl_ChiTietNhaCungCap);
        pnl_ChiTietNhaCungCap.setLayout(pnl_ChiTietNhaCungCapLayout);
        pnl_ChiTietNhaCungCapLayout.setHorizontalGroup(
            pnl_ChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ChiTietNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_ChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_ChiTietNhaCungCapLayout.createSequentialGroup()
                        .addGroup(pnl_ChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_tenNhaCungCap)
                            .addComponent(lbl_diaChi)
                            .addComponent(txt_nhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .addComponent(txt_diaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnl_ChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_email)
                            .addComponent(txt_soDienThoai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_soDienThoai, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_email, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(pnl_ChiTietNhaCungCapLayout.createSequentialGroup()
                        .addComponent(lbl_timNhaCungCap)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_timNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE)))
                .addGap(20, 20, 20)
                .addGroup(pnl_ChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        pnl_ChiTietNhaCungCapLayout.setVerticalGroup(
            pnl_ChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_ChiTietNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_ChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnl_ChiTietNhaCungCapLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_ChiTietNhaCungCapLayout.createSequentialGroup()
                        .addGroup(pnl_ChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_timNhaCungCap)
                            .addComponent(txt_timNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(pnl_ChiTietNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_ChiTietNhaCungCapLayout.createSequentialGroup()
                                .addComponent(lbl_tenNhaCungCap)
                                .addGap(2, 2, 2)
                                .addComponent(txt_nhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_diaChi)
                                .addGap(2, 2, 2)
                                .addComponent(txt_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl_ChiTietNhaCungCapLayout.createSequentialGroup()
                                .addComponent(lbl_soDienThoai)
                                .addGap(2, 2, 2)
                                .addComponent(txt_soDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(lbl_email)
                                .addGap(2, 2, 2)
                                .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        add(pnl_ChiTietNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1100, 200));
    }
    

    private void tbl_DanhSachNhaCungCapMousePressed(java.awt.event.MouseEvent evt) {
        int row = tbl_DanhSachNhaCungCap.getSelectedRow();
        
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachNhaCungCap.getModel();
        
        txt_diaChi.setText(dtm.getValueAt(row, 4).toString());
        txt_email.setText(dtm.getValueAt(row, 3).toString());
        txt_nhaCungCap.setText(dtm.getValueAt(row, 1).toString());
        txt_soDienThoai.setText(dtm.getValueAt(row, 2).toString());
    }

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {        
        if(btn_Them.getText().equalsIgnoreCase("Thêm")){
            isThemNhaCungCapClicked(true);
            clearInput();
        }
        else if(btn_Them.getText().equalsIgnoreCase("Huỷ")){
            clearInput();
            huyThaoTac();
        }
    }

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {
        if(tbl_DanhSachNhaCungCap.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn dữ liệu cần sửa");
            return;
        }
        
        if(btn_sua.getText().equals("Cập nhật")){
            isThemNhaCungCapClicked(false);//tắt button thêm
        }
        else if(btn_sua.getText().equals("Huỷ")){
            clearInput();
            huyThaoTac();
        }
    }

    private void btn_luuActionPerformed(java.awt.event.ActionEvent evt) {
        if(isThemActive){
            themNhaCungCapHandler();
        }else if(isSuaActive){
            capNhatNhaCungCapHandler();
        }
    }

    private void btn_TimActionPerformed(java.awt.event.ActionEvent evt) {
        clearTable();
        String keyWord = txt_timNhaCungCap.getText().trim().toLowerCase();
        NhaCungCapDAO nccDAO = new NhaCungCapDAO();
        ArrayList<NhaCungCap> listNhaCungCaps = nccDAO.getAllNhaCungCap();
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachNhaCungCap.getModel();
        for(NhaCungCap ncc : listNhaCungCaps){
            if(ncc.getMaNhaCungCap().toLowerCase().contains(keyWord) || ncc.getTenNhaCungCap().toLowerCase().contains(keyWord) || ncc.getSdt().toLowerCase().contains(keyWord) || ncc.getEmail().toLowerCase().contains(keyWord))
            {
                Object[] rowdata={ncc.getMaNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getSdt(), ncc.getEmail(), ncc.getDiaChi()};
                dtm.addRow(rowdata);
            }
        }
    }



    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Tim;
    private javax.swing.JButton btn_luu;
    private javax.swing.JButton btn_sua;
    private javax.swing.JPanel pnl_DanhSachNhaCungCap;
    private javax.swing.JPanel pnl_ChiTietNhaCungCap;
    private javax.swing.JScrollPane scr_DanhSachNhaCungCap;
    private javax.swing.JLabel lbl_diaChi;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_soDienThoai;
    private javax.swing.JLabel lbl_tenNhaCungCap;
    private javax.swing.JLabel lbl_timNhaCungCap;
    private javax.swing.JTable tbl_DanhSachNhaCungCap;
    private javax.swing.JTextField txt_diaChi;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nhaCungCap;
    private javax.swing.JTextField txt_soDienThoai;
    private javax.swing.JTextField txt_timNhaCungCap;

}
