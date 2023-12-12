
package gui;

import dao.KhuyenMaiDAO;
import dao.PhanLoaiDAO;
import dao.SanPhamDAO;

import entity.KhuyenMai;
import entity.PhanLoai;

import entity.SanPham;
import java.awt.Color;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class TabSanPhamKhuyenMai extends javax.swing.JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SanPhamDAO sanPhamDAO;
    private KhuyenMaiDAO khuyenMaiDAO;
    private PhanLoaiDAO phanLoaiDAO;
    private ArrayList<SanPham> listSanPham;

    /**
     * Creates new form FormKhuyenMai
     */
    public TabSanPhamKhuyenMai() {
        initComponents();
        tblDanhSachSanPham();
        tblDanhSachSanPhamKhyenMai();
        loadComboBoxKhuyenMai();
        designTable();

    }

    private void designTable() {
        //table san pham
        tblSanPham.getTableHeader().setFont(new java.awt.Font("Calibri", 0, 12));
        tblSanPham.getTableHeader().setOpaque(false);
        tblSanPham.getTableHeader().setBackground(new Color(198, 226, 255));
        tblSanPham.getTableHeader().setForeground(new Color(16,54,103));
        tblSanPham.setDefaultEditor(Object.class, null); // Không cho phép edit
        //table san phẩm khuyến mãi
        tblSanPhamKhuyenMai.getTableHeader().setFont(new java.awt.Font("Calibri", 0, 12));
        tblSanPhamKhuyenMai.getTableHeader().setOpaque(false);
        tblSanPhamKhuyenMai.getTableHeader().setBackground(new Color(198, 226, 255));
        tblSanPhamKhuyenMai.getTableHeader().setForeground(new Color(16,54,103));
        tblSanPhamKhuyenMai.setDefaultEditor(Object.class, null); // Không cho phép edit
//        tbl_danhSachNhanVien.getTableHeader()
    }

    
    private void initComponents() {

        Danhsachsanpham = new javax.swing.JPanel();
        lbl_phanLoai = new javax.swing.JLabel();
        cb_phanLoai = new javax.swing.JComboBox<>();
        lbl_tenSanPham = new javax.swing.JLabel();
        txt_tenSanPhamTK = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        btn_Tim = new javax.swing.JButton();
        Danhsachkhuyenmai = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPhamKhuyenMai = new javax.swing.JTable();
        Thongtinkhuyenmai = new javax.swing.JPanel();
        lbl_maSanPham = new javax.swing.JLabel();
        txt_maSanPham = new javax.swing.JTextField();
        lbl_mucKhuyenMai = new javax.swing.JLabel();
        lbl_ngayBatDau = new javax.swing.JLabel();
        lbl_soLuong = new javax.swing.JLabel();
        txt_soLuong = new javax.swing.JTextField();
        date_ngayBatDau = new com.toedter.calendar.JDateChooser();
        //chuyển định dạng ngày tháng sang dd/MM/yyyy của date_ngayBatDau
        date_ngayBatDau.setDateFormatString("dd/MM/yyyy");

        lbl_tenSP = new javax.swing.JLabel();
        txt_tenSP = new javax.swing.JTextField();
        lbl_kichThuoc = new javax.swing.JLabel();
        lbl_mauSac = new javax.swing.JLabel();
        lbl_kieuDang = new javax.swing.JLabel();
        lbl_chatLieu = new javax.swing.JLabel();
        lbl_giaBan = new javax.swing.JLabel();
        txt_giaBan = new javax.swing.JTextField();
        btn_them = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_luu = new javax.swing.JButton();
        btn_xoaTrang = new javax.swing.JButton();
        txt_KichThuoc = new javax.swing.JTextField();
        txt_mauSac = new javax.swing.JTextField();
        txt_kieuDang = new javax.swing.JTextField();
        txt_chatLieu = new javax.swing.JTextField();
        cb_KhuyenMai = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Danhsachsanpham.setBackground(new java.awt.Color(255, 255, 255));
        Danhsachsanpham.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N
        Danhsachsanpham.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        lbl_phanLoai.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_phanLoai.setText("Phân loại:");

        cb_phanLoai.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        cb_phanLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cb_phanLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_phanLoaiActionPerformed(evt);
            }
        });

        lbl_tenSanPham.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_tenSanPham.setText("Tên sản phẩm:");

        txt_tenSanPhamTK.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        tblSanPham.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên sản phẩm", "Kích thước", "Màu sắc", "Kiểu dáng", "Chất liệu", "Giá bán", "Số lượng"
            }
        ));
        tblSanPham.setRowHeight(30);
        tblSanPham.setShowHorizontalLines(true);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSanPhamMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        btn_Tim.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btn_Tim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        btn_Tim.setText("Tìm");
        btn_Tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DanhsachsanphamLayout = new javax.swing.GroupLayout(Danhsachsanpham);
        Danhsachsanpham.setLayout(DanhsachsanphamLayout);
        DanhsachsanphamLayout.setHorizontalGroup(
            DanhsachsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DanhsachsanphamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DanhsachsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, DanhsachsanphamLayout.createSequentialGroup()
                        .addComponent(lbl_phanLoai)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_phanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_tenSanPham)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tenSanPhamTK, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Tim)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DanhsachsanphamLayout.setVerticalGroup(
            DanhsachsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DanhsachsanphamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DanhsachsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_phanLoai)
                    .addComponent(cb_phanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_tenSanPham)
                    .addComponent(txt_tenSanPhamTK, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Tim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Danhsachkhuyenmai.setBackground(new java.awt.Color(255, 255, 255));
        Danhsachkhuyenmai.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản ohaamr đã áp dụng khuyến mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N
        Danhsachkhuyenmai.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        tblSanPhamKhuyenMai.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tblSanPhamKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Ngày bắt đầu", "Số lượng", "Giá lúc đầu ", "Giá lúc sau", "Phần trăm khuyến mãi"
            }
        ));
        tblSanPhamKhuyenMai.setRowHeight(30);
        tblSanPhamKhuyenMai.setShowHorizontalLines(true);
        tblSanPhamKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSanPhamKhuyenMaiMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPhamKhuyenMai);

        javax.swing.GroupLayout DanhsachkhuyenmaiLayout = new javax.swing.GroupLayout(Danhsachkhuyenmai);
        Danhsachkhuyenmai.setLayout(DanhsachkhuyenmaiLayout);
        DanhsachkhuyenmaiLayout.setHorizontalGroup(
            DanhsachkhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DanhsachkhuyenmaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DanhsachkhuyenmaiLayout.setVerticalGroup(
            DanhsachkhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DanhsachkhuyenmaiLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        Thongtinkhuyenmai.setBackground(new java.awt.Color(255, 255, 255));
        Thongtinkhuyenmai.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khuyến mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 12))); // NOI18N

        lbl_maSanPham.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_maSanPham.setText("Mã sản phẩm:");

        txt_maSanPham.setEditable(false);
        txt_maSanPham.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        lbl_mucKhuyenMai.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_mucKhuyenMai.setText("Mức khuyến mãi:");

        lbl_ngayBatDau.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_ngayBatDau.setText("Ngày bắt đầu:");

        lbl_soLuong.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_soLuong.setText("Số lượng:");

        txt_soLuong.setEditable(false);
        txt_soLuong.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_soLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_soLuongActionPerformed(evt);
            }
        });

        date_ngayBatDau.setEnabled(false);

        lbl_tenSP.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_tenSP.setText("Tên sản phẩm:");

        txt_tenSP.setEditable(false);
        txt_tenSP.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        lbl_kichThuoc.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_kichThuoc.setText("Kích thước:");

        lbl_mauSac.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_mauSac.setText("Màu sắc:");

        lbl_kieuDang.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_kieuDang.setText("Kiểu dáng:");

        lbl_chatLieu.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_chatLieu.setText("Chất liệu:");

        lbl_giaBan.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_giaBan.setText("Giá bán:");

        txt_giaBan.setEditable(false);
        txt_giaBan.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        btn_them.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btn_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Approve.png"))); // NOI18N
        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_sua.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btn_sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/edit.png"))); // NOI18N
        btn_sua.setText("Sửa");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        btn_luu.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btn_luu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Upload.png"))); // NOI18N
        btn_luu.setText("Lưu");
        btn_luu.setEnabled(false);
        btn_luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_luuActionPerformed(evt);
            }
        });

        btn_xoaTrang.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btn_xoaTrang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Pulse.png"))); // NOI18N
        btn_xoaTrang.setText("Xóa trắng");
        btn_xoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaTrangActionPerformed(evt);
            }
        });

        txt_KichThuoc.setEditable(false);
        txt_KichThuoc.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_KichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_KichThuocActionPerformed(evt);
            }
        });

        txt_mauSac.setEditable(false);
        txt_mauSac.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        txt_kieuDang.setEditable(false);
        txt_kieuDang.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        txt_chatLieu.setEditable(false);
        txt_chatLieu.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        javax.swing.GroupLayout ThongtinkhuyenmaiLayout = new javax.swing.GroupLayout(Thongtinkhuyenmai);
        Thongtinkhuyenmai.setLayout(ThongtinkhuyenmaiLayout);
        ThongtinkhuyenmaiLayout.setHorizontalGroup(
            ThongtinkhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongtinkhuyenmaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ThongtinkhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_tenSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_kichThuoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_giaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(ThongtinkhuyenmaiLayout.createSequentialGroup()
                        .addGroup(ThongtinkhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_mucKhuyenMai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_mauSac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_kieuDang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_chatLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(265, 265, 265))
                    .addComponent(lbl_ngayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_soLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(ThongtinkhuyenmaiLayout.createSequentialGroup()
                        .addGroup(ThongtinkhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ThongtinkhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_maSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_maSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_tenSP, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                                .addComponent(txt_KichThuoc)
                                .addComponent(txt_mauSac)
                                .addComponent(txt_kieuDang)
                                .addComponent(txt_chatLieu))
                            .addComponent(txt_giaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_KhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(date_ngayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(ThongtinkhuyenmaiLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(ThongtinkhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_them, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(btn_luu, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ThongtinkhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_sua, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(btn_xoaTrang, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        ThongtinkhuyenmaiLayout.setVerticalGroup(
            ThongtinkhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongtinkhuyenmaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_maSanPham)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_maSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_tenSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_tenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_kichThuoc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_KichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_mauSac)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_mauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_kieuDang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_kieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_chatLieu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_chatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_giaBan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_giaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_mucKhuyenMai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_KhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_ngayBatDau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_ngayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_soLuong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ThongtinkhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ThongtinkhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xoaTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(250, 250, 250))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Danhsachsanpham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Danhsachkhuyenmai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Thongtinkhuyenmai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Danhsachsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Danhsachkhuyenmai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(Thongtinkhuyenmai, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMousePressed(java.awt.event.MouseEvent evt) {
        int row = tblSanPham.getSelectedRow();
        tblSanPhamKhuyenMai.clearSelection();
        if (btn_sua.getText().equals("Hủy")) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn trên sản phẩm khuyến mãi");
            tblSanPham.clearSelection();
            return;
        }
        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();

        txt_maSanPham.setText(dtm.getValueAt(row, 0).toString());
        txt_tenSP.setText(dtm.getValueAt(row, 1).toString());
        txt_KichThuoc.setText(dtm.getValueAt(row, 2).toString());
        txt_mauSac.setText(dtm.getValueAt(row, 3).toString());
        txt_kieuDang.setText(dtm.getValueAt(row, 4).toString());
        txt_chatLieu.setText(dtm.getValueAt(row, 5).toString());
        txt_giaBan.setText(dtm.getValueAt(row, 6).toString());

    }//GEN-LAST:event_tblSanPhamMousePressed

    private void cb_phanLoaiActionPerformed(java.awt.event.ActionEvent evt) {
        sanPhamDAO = new SanPhamDAO();
        phanLoaiDAO = new PhanLoaiDAO();

        String loaiSanPham = cb_phanLoai.getSelectedItem().toString();
        PhanLoai phanLoai = phanLoaiDAO.getPhanLoaiByName(loaiSanPham);

        if (cb_phanLoai.getSelectedItem().toString().trim().equalsIgnoreCase("All")) {
            tblDanhSachSanPham();
        } else {
            ArrayList<SanPham> listSanPham = sanPhamDAO.getAllSanPhamByPhanLoaiId(phanLoai.getMaPhanLoai());
            DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
            clearTableSanPham();
            for (SanPham sanPham : listSanPham) {
                String giaBan = NumberFormat.getInstance().format(sanPham.tinhGiaBanLucDau());
                Object[] rowData = {sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getKichThuoc().getKichThuoc(), sanPham.getMauSac().getMauSac(), sanPham.getKieuDang().getKieuDang(), sanPham.getChatLieu().getChatLieu(), giaBan, sanPham.getSoLuong()};
                dtm.addRow(rowData);
            }
        }

    }//GEN-LAST:event_cb_phanLoaiActionPerformed

    private void btn_TimActionPerformed(java.awt.event.ActionEvent evt) {
        String tenSanPham_TK = txt_tenSanPhamTK.getText().trim();
        if (tenSanPham_TK.length() > 0) {
            sanPhamDAO = new SanPhamDAO();
            ArrayList<SanPham> listSanPham = sanPhamDAO.getAllSanPhamByName(tenSanPham_TK);
            if (listSanPham.size() > 1) {
                DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
                clearTableSanPham();
                for (SanPham sanPham : listSanPham) {
                    String giaBan = NumberFormat.getInstance().format(sanPham.tinhGiaBanLucDau());
                    Object[] rowData = {sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getKichThuoc().getKichThuoc(), sanPham.getMauSac().getMauSac(), sanPham.getKieuDang().getKieuDang(), sanPham.getChatLieu().getChatLieu(), giaBan, sanPham.getSoLuong()};
                    dtm.addRow(rowData);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy");
            }
        } else {
            tblDanhSachSanPham();
        }
    }//GEN-LAST:event_btn_TimActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {
        if (btn_them.getText().equalsIgnoreCase("Thêm")) {

            SanPham sanPham = new SanPham();

            btn_luu.setEnabled(true);
            btn_them.setText("Hủy");
            btn_sua.setEnabled(false);
            btn_xoaTrang.setEnabled(false);
            txt_soLuong.setEditable(true);
            date_ngayBatDau.setEnabled(true);
            txt_maSanPham.setText(sanPham.auto_ID());
            tblSanPham.clearSelection();
            tblSanPhamKhuyenMai.clearSelection();

        } else if (btn_them.getText().equalsIgnoreCase("Hủy")) {
            btn_them.setText("Thêm");
            txt_maSanPham.setText("");
            btn_sua.setEnabled(true);
            btn_xoaTrang.setEnabled(true);
            txt_soLuong.setEditable(false);
            date_ngayBatDau.setEnabled(false);
            btn_luu.setEnabled(false);
            tblSanPham.clearSelection();
            tblSanPhamKhuyenMai.clearSelection();
        }
        xoaTrang();
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblSanPhamKhuyenMai.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn 1 dòng trước khi sửa");
        } else {
            if (btn_sua.getText().equalsIgnoreCase("Sửa")) {
                btn_luu.setEnabled(true);
                btn_them.setEnabled(false);
                btn_xoaTrang.setEnabled(false);
                txt_soLuong.setEditable(true);
                date_ngayBatDau.setEnabled(true);
                btn_sua.setText("Hủy");
            } else if (btn_sua.getText().equalsIgnoreCase("Hủy")) {
                btn_sua.setText("Sửa");
                btn_them.setEnabled(true);
                btn_xoaTrang.setEnabled(true);
                txt_soLuong.setEditable(false);
                date_ngayBatDau.setEnabled(false);
                btn_luu.setEnabled(false);
                tblSanPhamKhuyenMai.clearSelection();
                xoaTrang();
            }
        }
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_luuActionPerformed
        sanPhamDAO = new SanPhamDAO();
        khuyenMaiDAO = new KhuyenMaiDAO();

        int row = tblSanPham.getSelectedRow();

        if (btn_them.getText().equalsIgnoreCase("Hủy")) {
            try {
                if (validData()) {
                    DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
                    KhuyenMai khuyenMai = loadDataFromTextField();
                    SanPham sanPham = sanPhamDAO.getSanPhamById(String.valueOf(dtm.getValueAt(row, 0)));
                    sanPham.setKhuyenMai(khuyenMai);
                    if (checkDiscountIsActive()) {
                        tblSanPham.clearSelection();
                        xoaTrang();
                        btn_luu.setEnabled(false);
                        btn_xoaTrang.setEnabled(true);
                        btn_sua.setEnabled(true);
                        btn_them.setText("Thêm");
                        tblSanPham.clearSelection();
                        
                        return;
                    }
                    
                    if (khuyenMaiDAO.addKhuyenMai(khuyenMai) > 0 && sanPhamDAO.updateSanPham(sanPham) > 0) {
                        JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thành công");
                        
                        DefaultTableModel dtm1 = (DefaultTableModel) tblSanPhamKhuyenMai.getModel();
                        SimpleDateFormat sfm = new SimpleDateFormat("yyyy-MM-dd");
                        String date = sfm.format(khuyenMai.getNgayBatDau());
                        
                        String giaBanLucSau = NumberFormat.getInstance().format(sanPham.tinhGiaBanLucSau());
                        Object[] rowData = {sanPham.getMaSP(), sanPham.getTenSP(), date, sanPham.getKhuyenMai().getSoLuong(), sanPham.tinhGiaBanLucDau(), giaBanLucSau, sanPham.getKhuyenMai().getPhanTramKhuyenMai()};
                        dtm1.addRow(rowData);
                        tblSanPham.clearSelection();
                        xoaTrang();
                        btn_luu.setEnabled(false);
                        btn_xoaTrang.setEnabled(true);
                        btn_sua.setEnabled(true);
                        btn_them.setText("Thêm");
                        tblSanPham.clearSelection();
                    }
                }
            } catch (ParseException ex) {
                Logger.getLogger(TabSanPhamKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (btn_sua.getText().equalsIgnoreCase("Hủy")) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc là muốn sửa không?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                KhuyenMai khuyenMai = loadDataFromTextField();

                SanPham sanPham = sanPhamDAO.getSanPhamById(txt_maSanPham.getText().trim());
                sanPham.setKhuyenMai(khuyenMai);

                if (khuyenMaiDAO.addKhuyenMai(khuyenMai) > 0 && sanPhamDAO.updateSanPham(sanPham) > 0) {
                    JOptionPane.showMessageDialog(this, "Sửa khuyến mãi thành công");
                    tblDanhSachSanPhamKhyenMai();
                    btn_luu.setEnabled(false);
                    btn_xoaTrang.setEnabled(true);
                    btn_them.setEnabled(true);
                    btn_sua.setEnabled(true);
                    btn_sua.setText("Sửa");
                    tblSanPhamKhuyenMai.clearSelection();
                }
            } else {
                btn_sua.setText("Sửa");
                btn_them.setEnabled(true);
                btn_xoaTrang.setEnabled(true);
                txt_soLuong.setEditable(false);
                date_ngayBatDau.setEnabled(false);
                btn_luu.setEnabled(false);
                tblSanPhamKhuyenMai.clearSelection();
                xoaTrang();
            }
        }
    }//GEN-LAST:event_btn_luuActionPerformed

    private void btn_xoaTrangActionPerformed(java.awt.event.ActionEvent evt) {
        xoaTrang();
    }//GEN-LAST:event_btn_xoaTrangActionPerformed

    private void tblSanPhamKhuyenMaiMousePressed(java.awt.event.MouseEvent evt) {
        int row = tblSanPhamKhuyenMai.getSelectedRow();
        if (btn_them.getText().equalsIgnoreCase("Hủy")) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn trên bảng sản phẩm");
            tblSanPhamKhuyenMai.clearSelection();
            return;
        }
        // System.out.println(row);
        if (row >= 0) {
            tblSanPham.clearSelection();
        }

        DefaultTableModel dtm = (DefaultTableModel) tblSanPhamKhuyenMai.getModel();
        String maSP = dtm.getValueAt(row, 0).toString();

        sanPhamDAO = new SanPhamDAO();
        SanPham sanPham = sanPhamDAO.getSanPhamById(maSP);

        txt_maSanPham.setText(sanPham.getMaSP());
        txt_tenSP.setText(sanPham.getTenSP());
        txt_mauSac.setText(sanPham.getMauSac().getMauSac());
        txt_KichThuoc.setText(sanPham.getKichThuoc().getKichThuoc());
        txt_kieuDang.setText(sanPham.getKieuDang().getKieuDang());
        txt_chatLieu.setText(sanPham.getChatLieu().getChatLieu());
        txt_giaBan.setText(dtm.getValueAt(row, 5).toString());
        cb_KhuyenMai.setSelectedItem(dtm.getValueAt(row, 6).toString() + "%");

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(dtm.getValueAt(row, 2)));
            date_ngayBatDau.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(TabSanPhamKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }

        txt_soLuong.setText(String.valueOf(dtm.getValueAt(row, 3)));


    }//GEN-LAST:event_tblSanPhamKhuyenMaiMousePressed

    private void txt_KichThuocActionPerformed(java.awt.event.ActionEvent evt) {
    }//GEN-LAST:event_txt_KichThuocActionPerformed

    private void txt_soLuongActionPerformed(java.awt.event.ActionEvent evt) {
    }//GEN-LAST:event_txt_soLuongActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Tim;
    private javax.swing.JButton btn_luu;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_xoaTrang;
    private javax.swing.JComboBox<String> cb_KhuyenMai;
    private javax.swing.JComboBox<String> cb_phanLoai;
    private com.toedter.calendar.JDateChooser date_ngayBatDau;
    private javax.swing.JPanel Danhsachsanpham;
    private javax.swing.JPanel Danhsachkhuyenmai;
    private javax.swing.JPanel Thongtinkhuyenmai;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_chatLieu;
    private javax.swing.JLabel lbl_giaBan;
    private javax.swing.JLabel lbl_kichThuoc;
    private javax.swing.JLabel lbl_kieuDang;
    private javax.swing.JLabel lbl_maSanPham;
    private javax.swing.JLabel lbl_mauSac;
    private javax.swing.JLabel lbl_mucKhuyenMai;
    private javax.swing.JLabel lbl_ngayBatDau;
    private javax.swing.JLabel lbl_phanLoai;
    private javax.swing.JLabel lbl_soLuong;
    private javax.swing.JLabel lbl_tenSP;
    private javax.swing.JLabel lbl_tenSanPham;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSanPhamKhuyenMai;
    private javax.swing.JTextField txt_KichThuoc;
    private javax.swing.JTextField txt_chatLieu;
    private javax.swing.JTextField txt_giaBan;
    private javax.swing.JTextField txt_kieuDang;
    private javax.swing.JTextField txt_maSanPham;
    private javax.swing.JTextField txt_mauSac;
    private javax.swing.JTextField txt_soLuong;
    private javax.swing.JTextField txt_tenSP;
    private javax.swing.JTextField txt_tenSanPhamTK;
    // End of variables declaration//GEN-END:variables

    private void clearTableSanPham() {
        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
        dtm.setRowCount(0);
    }

    private void clearTableSanPhamKhuyenMai() {
        DefaultTableModel dtm = (DefaultTableModel) tblSanPhamKhuyenMai.getModel();
        dtm.setRowCount(0);
    }

    private void tblDanhSachSanPham() {
        sanPhamDAO = new SanPhamDAO();
        clearTableSanPham();
        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
        listSanPham = sanPhamDAO.getAllSanPham();
        for (SanPham sanPham : listSanPham) {
            Object[] rowData = {sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getKichThuoc().getKichThuoc(), sanPham.getMauSac().getMauSac(), sanPham.getKieuDang().getKieuDang(), sanPham.getChatLieu().getChatLieu(), sanPham.tinhGiaBanLucDau(), sanPham.getSoLuong()};
            dtm.addRow(rowData);
        }
    }

    private void tblDanhSachSanPhamKhyenMai() {
        clearTableSanPhamKhuyenMai();
        DefaultTableModel dtm = (DefaultTableModel) tblSanPhamKhuyenMai.getModel();
        listSanPham = sanPhamDAO.getAllSanPham();
        for (SanPham sanPham : listSanPham) {
            if (sanPham.getKhuyenMai().getPhanTramKhuyenMai() > 0 && sanPham.getKhuyenMai().getSoLuong() > 0) {

                Object[] rowData = {sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getKhuyenMai().getNgayBatDau(), sanPham.getKhuyenMai().getSoLuong(), sanPham.tinhGiaBanLucDau(), sanPham.tinhGiaBanLucSau(), sanPham.getKhuyenMai().getPhanTramKhuyenMai()};

                dtm.addRow(rowData);
            }
        }
    }

    private void loadComboBoxKhuyenMai() {
        cb_KhuyenMai.addItem("0%");
        cb_KhuyenMai.addItem("10%");
        cb_KhuyenMai.addItem("20%");
        cb_KhuyenMai.addItem("30%");
        cb_KhuyenMai.addItem("40%");

        phanLoaiDAO = new PhanLoaiDAO();
        ArrayList<PhanLoai> listPhanLoai = phanLoaiDAO.getAllPhanLoai();
        listPhanLoai.forEach(phanLoai -> cb_phanLoai.addItem(phanLoai.getLoaiSanPham()));
    }

    public void xoaTrang() {
        txt_maSanPham.setText("");
        txt_tenSP.setText("");
        txt_KichThuoc.setText("");
        txt_mauSac.setText("");
        txt_kieuDang.setText("");
        txt_chatLieu.setText("");
        cb_KhuyenMai.setSelectedIndex(0);
        date_ngayBatDau.setDate(null);
        txt_giaBan.setText("");
        txt_soLuong.setText("");
    }

    public boolean validData() throws ParseException {

        int row = tblSanPham.getSelectedRow();
        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();

        String[] phanTramKhuyenMai = String.valueOf(cb_KhuyenMai.getSelectedItem()).split("%");

        if (!(Integer.parseInt(phanTramKhuyenMai[0]) > 0)) {
            JOptionPane.showMessageDialog(this, "Phần trăm khuyến mãi phải lớn hơn 0");
            return false;
        }
        Date ngayBD = new java.sql.Date(date_ngayBatDau.getDate().getTime());
        Date ngayHT = new java.sql.Date(System.currentTimeMillis()); 
        if (!(ngayBD.toString().equals(ngayHT.toString()) || ngayBD.after(ngayHT))) {
            JOptionPane.showMessageDialog(null, "Ngày khuyến mãi phải lớn >= ngày hiện tại");
            return false;
        }
        int sl = 0;
        try {
            sl = Integer.parseInt(txt_soLuong.getText().trim());
            if (txt_soLuong.getText().trim().isEmpty()) {
                txt_soLuong.requestFocus();
                JOptionPane.showMessageDialog(this, "Số lượng không được rỗng");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Số lượng phải là chữ số");
            return false;
        }

        
        if (sl > 0) {
            if (sl > Integer.parseInt(String.valueOf(dtm.getValueAt(row, 7)))) {
                JOptionPane.showMessageDialog(null, "Sản phẩm tồn không đủ số lượng để khuyến mãi");
                txt_soLuong.requestFocus();
                txt_soLuong.selectAll();
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0");
            return false;
        }

        return true;
    }

    public KhuyenMai loadDataFromTextField() {
        String[] phanTramKhuyenMai = cb_KhuyenMai.getSelectedItem().toString().split("%");

        Date date = date_ngayBatDau.getDate();


        int soLuong = Integer.parseInt(txt_soLuong.getText().trim());

        return new KhuyenMai(Integer.parseInt(phanTramKhuyenMai[0]), date, soLuong);
    }

    public boolean checkDiscountIsActive() {

        DefaultTableModel defaultTableMode = (DefaultTableModel) tblSanPhamKhuyenMai.getModel();

        for (int i = 0; i < defaultTableMode.getRowCount(); i++) {
            String maSP = defaultTableMode.getValueAt(i, 0).toString();
            if (txt_maSanPham.getText().trim().toString().equalsIgnoreCase(maSP)) {
                JOptionPane.showMessageDialog(null, "Sản phẩm đang khuyến mãi, không thể thêm được nữa");
                return true;
            }
        }

        return false;
    }

}
