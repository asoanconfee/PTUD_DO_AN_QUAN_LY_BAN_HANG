package gui;

import dao.ChatLieuDAO;
import dao.KhuyenMaiDAO;
import dao.KichThuocDAO;
import dao.KieuDangDAO;
import dao.MauSacDAO;
import dao.NhaCungCapDAO;
import dao.PhanLoaiDAO;
import dao.SanPhamDAO;
import dao.XuatXuDAO;
import entity.ChatLieu;
import entity.KhuyenMai;
import entity.KichThuoc;
import entity.KieuDang;
import entity.MauSac;
import entity.NhaCungCap;
import entity.PhanLoai;
import entity.SanPham;
import entity.XuatXu;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


public class TabThongTinSanPham extends javax.swing.JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SanPhamDAO sanPhamDAO;
    private MauSacDAO mauSacDAO;
    private KichThuocDAO kichThuocDAO;
    private KieuDangDAO kieuDangDAO;
    private ChatLieuDAO chatLieuDAO;
    private PhanLoaiDAO phanLoaiDAO;
    private XuatXuDAO xuatXuDAO;
    private NhaCungCapDAO nhaCungCapDAO;
    private KhuyenMaiDAO khuyenMaiDAO;
    private File file = null;

    public TabThongTinSanPham() {
        initComponents();
        designTable();
        loadComboBoxThuocTinh();
        tblDanhSachSanPham();
    }

    private void designTable() {
        tbl_danhSachSanPham.getTableHeader().setFont(new java.awt.Font("Calibri", 0, 12));
        tbl_danhSachSanPham.getTableHeader().setOpaque(false);
        tbl_danhSachSanPham.getTableHeader().setBackground(new Color(198, 226, 255));
        tbl_danhSachSanPham.getTableHeader().setForeground(new Color(16,54,103));
        tbl_danhSachSanPham.setDefaultEditor(Object.class, null); // Không cho phép edit

//        tbl_danhSachNhanVien.getTableHeader()
    }


    private void initComponents() {

        Thongtinsanpham = new javax.swing.JPanel();
        txt_maSanPham = new javax.swing.JTextField();
        btn_them = new javax.swing.JButton();
        lbl_maSanPham = new javax.swing.JLabel();
        lbl_tenSanPham = new javax.swing.JLabel();
        txt_tenSanPham = new javax.swing.JTextField();
        lbl_loaiSanPham = new javax.swing.JLabel();
        lbl_mauSac = new javax.swing.JLabel();
        lbl_kichThuoc = new javax.swing.JLabel();
        lbl_chatLieu = new javax.swing.JLabel();
        lbl_giaNhap = new javax.swing.JLabel();
        txt_giaNhap = new javax.swing.JTextField();
        txt_giaBan = new javax.swing.JTextField();
        lbl_giaBan = new javax.swing.JLabel();
        lbl_nhaCungCap = new javax.swing.JLabel();
        lbl_soLuong = new javax.swing.JLabel();
        txt_soLuong = new javax.swing.JTextField();
        btn_sua = new javax.swing.JButton();
        btn_luu = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        lbl_xuatXu = new javax.swing.JLabel();
        lbl_kieuDang = new javax.swing.JLabel();
        pnlHinhAnh = new javax.swing.JPanel();
        lblHinhAnh = new javax.swing.JLabel();
        btn_chonAnh = new javax.swing.JButton();
        cb_loaiSanPham = new javax.swing.JComboBox<>();
        cb_xuatXu = new javax.swing.JComboBox<>();
        cb_nhaCungCap = new javax.swing.JComboBox<>();
        cb_mauSac = new javax.swing.JComboBox<>();
        cb_kichThuoc = new javax.swing.JComboBox<>();
        cb_kieuDang = new javax.swing.JComboBox<>();
        cb_chatLieu = new javax.swing.JComboBox<>();
        Danhsachsanpham = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_danhSachSanPham = new javax.swing.JTable();
        txt_timKiem = new javax.swing.JTextField();
        lbl_timKiem = new javax.swing.JLabel();
        btn_Tim = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setMaximumSize(new java.awt.Dimension(1130, 756));
        setMinimumSize(new java.awt.Dimension(1115, 735));
        setPreferredSize(new java.awt.Dimension(1115, 735));

        Thongtinsanpham.setBackground(new java.awt.Color(255, 255, 255));
        Thongtinsanpham.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N
        Thongtinsanpham.setPreferredSize(new java.awt.Dimension(1130, 214));

        txt_maSanPham.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_maSanPham.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_maSanPham.setEnabled(false);

        btn_them.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btn_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Approve.png"))); // NOI18N
        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        lbl_maSanPham.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_maSanPham.setText("Mã sản phẩm:");

        lbl_tenSanPham.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_tenSanPham.setText("Tên sản phẩm:");

        txt_tenSanPham.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_tenSanPham.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_tenSanPham.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_tenSanPham.setEnabled(false);

        lbl_loaiSanPham.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_loaiSanPham.setText("Loại sản phẩm:");
lbl_mauSac.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_mauSac.setText("Màu sắc:");

        lbl_kichThuoc.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_kichThuoc.setText("Kích thước:");

        lbl_chatLieu.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_chatLieu.setText("Chất liệu:");

        lbl_giaNhap.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_giaNhap.setText("Giá nhập:");

        txt_giaNhap.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_giaNhap.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_giaNhap.setEnabled(false);

        txt_giaBan.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_giaBan.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_giaBan.setEnabled(false);

        lbl_giaBan.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_giaBan.setText("Giá bán:");

        lbl_nhaCungCap.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_nhaCungCap.setText("Nhà cung cấp:");

        lbl_soLuong.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_soLuong.setText("Số lượng:");

        txt_soLuong.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txt_soLuong.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_soLuong.setEnabled(false);
        txt_soLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_soLuongActionPerformed(evt);
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

        btn_xoa.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btn_xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Pulse.png"))); // NOI18N
        btn_xoa.setText("Xoá Sản Phẩm");
        btn_xoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_xoaMouseClicked(evt);
            }
        });
btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        lbl_xuatXu.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_xuatXu.setText("Xuất xứ:");

        lbl_kieuDang.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_kieuDang.setText("Kiểu dáng:");

        pnlHinhAnh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(119, 119, 119), 3, true));

        lblHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhAnh.setText("Hình ảnh");
        lblHinhAnh.setOpaque(true);

        javax.swing.GroupLayout pnlHinhAnhLayout = new javax.swing.GroupLayout(pnlHinhAnh);
        pnlHinhAnh.setLayout(pnlHinhAnhLayout);
        pnlHinhAnhLayout.setHorizontalGroup(
            pnlHinhAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlHinhAnhLayout.setVerticalGroup(
            pnlHinhAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btn_chonAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/more.png"))); // NOI18N
        btn_chonAnh.setText("Chọn ảnh");
        btn_chonAnh.setEnabled(false);
        btn_chonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chonAnhActionPerformed(evt);
            }
        });

        cb_loaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_loaiSanPhamActionPerformed(evt);
            }
        });

        cb_mauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_mauSacActionPerformed(evt);
            }
        });

        cb_kichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_kichThuocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ThongtinsanphamLayout = new javax.swing.GroupLayout(Thongtinsanpham);
        Thongtinsanpham.setLayout(ThongtinsanphamLayout);
        ThongtinsanphamLayout.setHorizontalGroup(
            ThongtinsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongtinsanphamLayout.createSequentialGroup()
.addGroup(ThongtinsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongtinsanphamLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(pnlHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ThongtinsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_chonAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_giaNhap)
                            .addComponent(txt_giaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_giaBan)
                            .addComponent(txt_giaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ThongtinsanphamLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(ThongtinsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ThongtinsanphamLayout.createSequentialGroup()
                                .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ThongtinsanphamLayout.createSequentialGroup()
                                .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(92, 92, 92)
                .addGroup(ThongtinsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_maSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_xuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_xuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_nhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGroup(ThongtinsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbl_maSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_tenSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cb_nhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ThongtinsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_kichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_kieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_mauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_kichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_kieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_chatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_chatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_loaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_loaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_mauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );
        ThongtinsanphamLayout.setVerticalGroup(
            ThongtinsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongtinsanphamLayout.createSequentialGroup()
                .addGroup(ThongtinsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongtinsanphamLayout.createSequentialGroup()
                        .addContainerGap()
.addGroup(ThongtinsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ThongtinsanphamLayout.createSequentialGroup()
                                .addComponent(lbl_giaNhap)
                                .addGap(0, 0, 0)
                                .addComponent(txt_giaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(lbl_giaBan)
                                .addGap(0, 0, 0)
                                .addComponent(txt_giaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_chonAnh)))
                        .addGap(18, 18, 18)
                        .addGroup(ThongtinsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ThongtinsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ThongtinsanphamLayout.createSequentialGroup()
                        .addComponent(lbl_mauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cb_mauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_kichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cb_kichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_kieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cb_kieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_chatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cb_chatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_loaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cb_loaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ThongtinsanphamLayout.createSequentialGroup()
                        .addGroup(ThongtinsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ThongtinsanphamLayout.createSequentialGroup()
                                .addComponent(lbl_maSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txt_maSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(lbl_tenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ThongtinsanphamLayout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(txt_tenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txt_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_nhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cb_nhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_xuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cb_xuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );


        Danhsachsanpham.setBackground(new java.awt.Color(255, 255, 255));
        Danhsachsanpham.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N
        Danhsachsanpham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_danhSachSanPham.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tbl_danhSachSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Phân loại", "Giá bán", "Kích cỡ", "Màu sắc", "Chất liệu", "Nhà cung cấp", "Số lượng tồn kho"
            }
        ));
        tbl_danhSachSanPham.setRowHeight(30);
        tbl_danhSachSanPham.setShowHorizontalLines(true);
        tbl_danhSachSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_danhSachSanPhamMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_danhSachSanPhamMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_danhSachSanPham);

        Danhsachsanpham.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 1070, 350));

        txt_timKiem.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        Danhsachsanpham.add(txt_timKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 290, 30));
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchAndUpdate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchAndUpdate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchAndUpdate();
            }

            private void searchAndUpdate() {
                String tenSanPham_TK = txt_timKiem.getText().trim().toLowerCase();
                String maSanPham_TK = txt_timKiem.getText().trim().toLowerCase();
                
                sanPhamDAO = new SanPhamDAO();
                ArrayList<SanPham> listSanPham = sanPhamDAO.getAllSanPham();
                DefaultTableModel dtm = (DefaultTableModel) tbl_danhSachSanPham.getModel();
                clearTable();

                for (SanPham sanPham : listSanPham) {
                    if (sanPham.getTenSP().toLowerCase().contains(tenSanPham_TK)|| sanPham.getMaSP().toLowerCase().contains(maSanPham_TK)) {
                        String giaBan = NumberFormat.getInstance().format(sanPham.tinhGiaBanLucSau());
                        Object[] rowData = {sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getPhanLoai().getLoaiSanPham(), giaBan, sanPham.getKichThuoc().getKichThuoc(), sanPham.getMauSac().getMauSac(), sanPham.getChatLieu().getChatLieu(), sanPham.getNhaCungCap().getTenNhaCungCap(), sanPham.getSoLuong()};
                        dtm.addRow(rowData);
                    }
                }
            }
        };

        txt_timKiem.getDocument().addDocumentListener(documentListener);
			
      
        



        
        lbl_timKiem.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbl_timKiem.setText("Tìm kiếm sản phẩm:");
        Danhsachsanpham.add(lbl_timKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, 30));

        btn_Tim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        btn_Tim.setText("Tìm");
        btn_Tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimActionPerformed(evt);
            }
        });
        Danhsachsanpham.add(btn_Tim, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 100, 30));
javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Danhsachsanpham, javax.swing.GroupLayout.DEFAULT_SIZE, 1089, Short.MAX_VALUE)
                    .addComponent(Thongtinsanpham, javax.swing.GroupLayout.DEFAULT_SIZE, 1089, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Thongtinsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Danhsachsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_xoaMouseClicked(MouseEvent evt) {
        xoa();
    }
    private void txt_soLuongActionPerformed(java.awt.event.ActionEvent evt) {
    }//GEN-LAST:event_txt_soLuongActionPerformed

    private void tbl_danhSachSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_danhSachSanPhamMousePressed
        int row = tbl_danhSachSanPham.getSelectedRow();
        DefaultTableModel dtm = (DefaultTableModel) tbl_danhSachSanPham.getModel();
        File file = new File("");
        String path= file.getAbsolutePath();
        
        String maSP = dtm.getValueAt(row, 0).toString();

        sanPhamDAO = new SanPhamDAO();
        SanPham sanPham = sanPhamDAO.getSanPhamById(maSP);
       
        //show hình ảnh với tên file là tên hình ảnh trong database + path là đường dẫn đến thư mục chứa hình ảnh src/picture
        lblHinhAnh.setIcon(ResizeImage(path + "/data/picture/"+sanPham.getHinhAnh()));

        txt_maSanPham.setText(sanPham.getMaSP());
        txt_tenSanPham.setText(sanPham.getTenSP());
        cb_xuatXu.setSelectedItem(sanPham.getXuatXu().getNoiXuatXu());
        cb_nhaCungCap.setSelectedItem(sanPham.getNhaCungCap().getTenNhaCungCap());
        cb_mauSac.setSelectedItem(sanPham.getMauSac().getMauSac());
        cb_kichThuoc.setSelectedItem(sanPham.getKichThuoc().getKichThuoc());
        cb_kieuDang.setSelectedItem(sanPham.getKieuDang().getKieuDang());
        cb_chatLieu.setSelectedItem(sanPham.getChatLieu().getChatLieu());
        cb_loaiSanPham.setSelectedItem(sanPham.getPhanLoai().getLoaiSanPham());
txt_soLuong.setText(String.valueOf(sanPham.getSoLuong()));
        txt_giaNhap.setText(String.valueOf(sanPham.getGiaNhap()));
        txt_giaBan.setText(dtm.getValueAt(row, 3).toString());
        txt_soLuong.setText(String.valueOf(sanPham.getSoLuong()));
        
    }

    // button chọn ảnh với path ban đầu là src/picture
    private void btn_chonAnhActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser filechoose = new JFileChooser("data/picture");
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("hinh anh", "jpg", "png");
        filechoose.setFileFilter(imageFilter);
        filechoose.setMultiSelectionEnabled(false);

        int x = filechoose.showDialog(this, "Chọn Ảnh");
        if (x == JFileChooser.APPROVE_OPTION) {
            file = filechoose.getSelectedFile();
            lblHinhAnh.setText("");
            lblHinhAnh.setIcon(ResizeImage(file.getAbsolutePath()));
        }
    }

    public void xoaTrang() {
        txt_maSanPham.setText("");
        txt_tenSanPham.setText("");
        cb_xuatXu.setSelectedIndex(0);
        cb_nhaCungCap.setSelectedIndex(0);
        cb_mauSac.setSelectedIndex(0);
        cb_kichThuoc.setSelectedIndex(0);
        cb_kieuDang.setSelectedIndex(0);
        cb_chatLieu.setSelectedIndex(0);
        cb_loaiSanPham.setSelectedIndex(0);
        txt_soLuong.setText("");
        txt_giaNhap.setText("");
        txt_giaBan.setText("");
        txt_soLuong.setText("");
        lblHinhAnh.setIcon(new ImageIcon());
    }

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {
        if (btn_them.getText().equalsIgnoreCase("Thêm")) {
            xoaTrang();
            SanPham sanPham = new SanPham();
            moKhoaComponent();
            btn_chonAnh.setEnabled(true);
            btn_luu.setEnabled(true);
            btn_them.setText("Hủy");
            btn_sua.setEnabled(false);
            btn_xoa.setEnabled(false);
            txt_maSanPham.setText(sanPham.auto_ID());
        } else if (btn_them.getText().equalsIgnoreCase("Hủy")) {
            btn_them.setText("Thêm");
            khoaComponent();
            txt_maSanPham.setText("");
            btn_sua.setEnabled(true);
            btn_xoa.setEnabled(true);
            btn_chonAnh.setEnabled(false);
            btn_luu.setEnabled(false);
            lblHinhAnh.setIcon(null); // Set the icon of "lblHinhAnh" to null to remove the existing icon
        }
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {
         if (tbl_danhSachSanPham.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn 1 dòng trước khi sửa");
        } else {
            if (btn_sua.getText().equalsIgnoreCase("Sửa")) {
                moKhoaComponent();
                btn_chonAnh.setEnabled(true);
                btn_luu.setEnabled(true);
                btn_them.setEnabled(false);
                btn_xoa.setEnabled(false);
                btn_sua.setText("Hủy");
            } else if (btn_sua.getText().equalsIgnoreCase("Hủy")) {
                btn_sua.setText("Sửa");
                khoaComponent();
                btn_them.setEnabled(true);
                btn_xoa.setEnabled(true);
                btn_chonAnh.setEnabled(false);
                btn_luu.setEnabled(false);
                xoaTrang();
                tbl_danhSachSanPham.clearSelection();
                lblHinhAnh.setIcon(new ImageIcon());
            }
        }
}//GEN-LAST:event_btn_suaActionPerformed

    private void btn_luuActionPerformed(java.awt.event.ActionEvent evt) {
        sanPhamDAO = new SanPhamDAO();

        if (btn_them.getText().equalsIgnoreCase("Hủy")) {

            if (validData()) {
                SanPham sanPham = getDataOnTextField();
                SanPham sanPham_Temp = kiemTraSanPhamTrung(sanPham);
                if (sanPham_Temp == null) { // neu khong trung thi them
                    
                    if (sanPhamDAO.addSanPham(sanPham) > 0) {
                        DefaultTableModel dtm = (DefaultTableModel) tbl_danhSachSanPham.getModel();
                        JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                        String giaBan = NumberFormat.getInstance().format(sanPham.tinhGiaBanLucSau());
                        Object[] rowData = {sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getPhanLoai().getLoaiSanPham(), giaBan, sanPham.getKichThuoc().getKichThuoc(), sanPham.getMauSac().getMauSac(), sanPham.getChatLieu().getChatLieu(), sanPham.getNhaCungCap().getTenNhaCungCap(), sanPham.getSoLuong()};
                        dtm.addRow(rowData);
                    } else {
                        JOptionPane.showMessageDialog(this, "Trùng mã sản phẩm ");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Sản phẩm đã có cập nhật thêm số lượng");
                    sanPham_Temp.setSoLuong(sanPham_Temp.getSoLuong() + sanPham.getSoLuong());
                    sanPhamDAO.updateSanPham(sanPham_Temp);
                    tblDanhSachSanPham();
                }
                btn_luu.setEnabled(false);
                btn_chonAnh.setEnabled(false);
                btn_xoa.setEnabled(true);
                btn_sua.setEnabled(true);
                btn_them.setText("Thêm");
                xoaTrang();
                khoaComponent();
                tbl_danhSachSanPham.clearSelection();
            }

        }
        if (btn_sua.getText().equalsIgnoreCase("Hủy")) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc là muốn sửa không?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                sanPhamDAO = new SanPhamDAO();
                SanPham sanPham = getDataOnTextField();

                if (sanPham.getHinhAnh().isEmpty()) {
                    sanPham.setHinhAnh(sanPhamDAO.getSanPhamById(sanPham.getMaSP()).getHinhAnh().toString());
                }
                if (sanPhamDAO.updateSanPham(sanPham) > 0) {
                    btn_luu.setEnabled(false);
                    btn_chonAnh.setEnabled(false);
                    JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công");

                    xoa();
                    btn_sua.setText("Sửa");
                    btn_them.setEnabled(true);
                    btn_xoa.setEnabled(true);
                    updateValueTable(sanPham);
khoaComponent();
                    tbl_danhSachSanPham.clearSelection();
                }
            } else {
                btn_luu.setEnabled(false);
                btn_chonAnh.setEnabled(false);
                xoaTrang();
                btn_xoa.setEnabled(true);
                btn_sua.setText("Sửa");
                btn_them.setEnabled(true);
                khoaComponent();
                tbl_danhSachSanPham.clearSelection();
            }
        }
    }//GEN-LAST:event_btn_luuActionPerformed

    private void btn_TimActionPerformed(java.awt.event.ActionEvent evt) {
        String tenSanPham_TK = txt_timKiem.getText().trim();
        String maSanPham_TK = txt_timKiem.getText().trim();
        if (tenSanPham_TK.length() > 0 && maSanPham_TK.length() > 0) {
            sanPhamDAO = new SanPhamDAO();
            ArrayList<SanPham> listSanPham = sanPhamDAO.getAllSanPham();
            if (listSanPham.size() > 1) {
                DefaultTableModel dtm = (DefaultTableModel) tbl_danhSachSanPham.getModel();
                clearTable();
                for (SanPham sanPham : listSanPham) {
                    if(sanPham.getTenSP().toLowerCase().contains(tenSanPham_TK.toLowerCase()) || (!maSanPham_TK.isEmpty() && sanPham.getMaSP().equalsIgnoreCase(maSanPham_TK))){
                        String giaBan = NumberFormat.getInstance().format(sanPham.tinhGiaBanLucSau());
                     Object[] rowData = {sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getPhanLoai().getLoaiSanPham(), giaBan, sanPham.getKichThuoc().getKichThuoc(), sanPham.getMauSac().getMauSac(), sanPham.getChatLieu().getChatLieu(), sanPham.getNhaCungCap().getTenNhaCungCap(), sanPham.getSoLuong()};
                    dtm.addRow(rowData);
                    }
                    
                }

            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy");
            }
        }
    	

        else {
            tblDanhSachSanPham();
        }
    }//GEN-LAST:event_btn_TimActionPerformed

    private void cb_mauSacActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void cb_kichThuocActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void cb_loaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void tbl_danhSachSanPhamMouseClicked(java.awt.event.MouseEvent evt) {
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Tim;
    private javax.swing.JButton btn_chonAnh;
    private javax.swing.JButton btn_luu;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JComboBox<String> cb_chatLieu;
    private javax.swing.JComboBox<String> cb_kichThuoc;
    private javax.swing.JComboBox<String> cb_kieuDang;
    private javax.swing.JComboBox<String> cb_loaiSanPham;
    private javax.swing.JComboBox<String> cb_mauSac;
    private javax.swing.JComboBox<String> cb_nhaCungCap;
private javax.swing.JComboBox<String> cb_xuatXu;
    private javax.swing.JPanel Thongtinsanpham;
    private javax.swing.JPanel Danhsachsanpham;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lbl_chatLieu;
    private javax.swing.JLabel lbl_giaBan;
    private javax.swing.JLabel lbl_giaNhap;
    private javax.swing.JLabel lbl_kichThuoc;
    private javax.swing.JLabel lbl_kieuDang;
    private javax.swing.JLabel lbl_loaiSanPham;
    private javax.swing.JLabel lbl_maSanPham;
    private javax.swing.JLabel lbl_mauSac;
    private javax.swing.JLabel lbl_nhaCungCap;
    private javax.swing.JLabel lbl_soLuong;
    private javax.swing.JLabel lbl_tenSanPham;
    private javax.swing.JLabel lbl_timKiem;
    private javax.swing.JLabel lbl_xuatXu;
    private javax.swing.JPanel pnlHinhAnh;
    private javax.swing.JTable tbl_danhSachSanPham;
    private javax.swing.JTextField txt_giaBan;
    private javax.swing.JTextField txt_giaNhap;
    private javax.swing.JTextField txt_maSanPham;
    private javax.swing.JTextField txt_soLuong;
    private javax.swing.JTextField txt_tenSanPham;
    private javax.swing.JTextField txt_timKiem;
    // End of variables declaration//GEN-END:variables

    public ImageIcon ResizeImage(String imgPath) {
        ImageIcon myImage = new ImageIcon(imgPath);
        Image img = myImage.getImage();
        Image newImg = img.getScaledInstance(pnlHinhAnh.getWidth(), pnlHinhAnh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);

        return image;
    }

    private void loadComboBoxThuocTinh() {
        mauSacDAO = new MauSacDAO();
        ArrayList<MauSac> listMauSac = mauSacDAO.getAllMauSac();
        listMauSac.forEach(mauSac -> cb_mauSac.addItem(mauSac.getMauSac()));

        kichThuocDAO = new KichThuocDAO();
        ArrayList<KichThuoc> listKichThuoc = kichThuocDAO.getAllKichThuoc();
        listKichThuoc.forEach(kichThuoc -> cb_kichThuoc.addItem(kichThuoc.getKichThuoc()));

        kieuDangDAO = new KieuDangDAO();
        ArrayList<KieuDang> listKieuDang = kieuDangDAO.getAllKieuDang();
        listKieuDang.forEach(kieuDang -> cb_kieuDang.addItem(kieuDang.getKieuDang()));

        chatLieuDAO = new ChatLieuDAO();
        ArrayList<ChatLieu> listChatLieu = chatLieuDAO.getAllChatLieu();
        listChatLieu.forEach(chatLieu -> cb_chatLieu.addItem(chatLieu.getChatLieu()));

        phanLoaiDAO = new PhanLoaiDAO();
        ArrayList<PhanLoai> listPhanLoai = phanLoaiDAO.getAllPhanLoai();
        listPhanLoai.forEach(phanLoai -> cb_loaiSanPham.addItem(phanLoai.getLoaiSanPham()));

        xuatXuDAO = new XuatXuDAO();
        ArrayList<XuatXu> listXuatXu = xuatXuDAO.getAllXuatXu();
        listXuatXu.forEach(xuatXu -> cb_xuatXu.addItem(xuatXu.getNoiXuatXu()));

        nhaCungCapDAO = new NhaCungCapDAO();
        ArrayList<NhaCungCap> listNhaCungCap = nhaCungCapDAO.getAllNhaCungCap();
listNhaCungCap.forEach(nhaCungCap -> cb_nhaCungCap.addItem(nhaCungCap.getTenNhaCungCap()));

    }

    private void clearTable() {
        DefaultTableModel dtm = (DefaultTableModel) tbl_danhSachSanPham.getModel();
        dtm.setRowCount(0);
    }

    private void tblDanhSachSanPham() {
        sanPhamDAO = new SanPhamDAO();
        clearTable();
        DefaultTableModel dtm = (DefaultTableModel) tbl_danhSachSanPham.getModel();
        ArrayList<SanPham> listSanPham = sanPhamDAO.getAllSanPham();
        for (SanPham sanPham : listSanPham) {
            String giaBan = NumberFormat.getInstance().format(sanPham.tinhGiaBanLucSau());
            Object[] rowData = {sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getPhanLoai().getLoaiSanPham(), giaBan, sanPham.getKichThuoc().getKichThuoc(), sanPham.getMauSac().getMauSac(), sanPham.getChatLieu().getChatLieu(), sanPham.getNhaCungCap().getTenNhaCungCap(), sanPham.getSoLuong()};
            dtm.addRow(rowData);
        }
    }

    private void moKhoaComponent() {
        txt_tenSanPham.setEnabled(true);
        cb_xuatXu.setEnabled(true);
        cb_nhaCungCap.setEditable(true);
        cb_mauSac.setEditable(true);
        cb_kichThuoc.setEditable(true);
        cb_kieuDang.setEditable(true);
        cb_chatLieu.setEditable(true);
        cb_loaiSanPham.setEditable(true);
        txt_soLuong.setEnabled(true);
        txt_giaNhap.setEnabled(true);
    }

    private void khoaComponent() {
        txt_tenSanPham.setEnabled(false);
        cb_xuatXu.setEditable(false);
        cb_nhaCungCap.setEditable(false);
        cb_mauSac.setEditable(false);
        cb_kichThuoc.setEditable(false);
        cb_kieuDang.setEditable(false);
        cb_chatLieu.setEditable(false);
        cb_loaiSanPham.setEditable(false);

        txt_soLuong.setEnabled(false);
        txt_giaNhap.setEnabled(false);

    }

    public SanPham getDataOnTextField() {
        chatLieuDAO = new ChatLieuDAO();
        kieuDangDAO = new KieuDangDAO();
        kichThuocDAO = new KichThuocDAO();
        mauSacDAO = new MauSacDAO();
        xuatXuDAO = new XuatXuDAO();
        phanLoaiDAO = new PhanLoaiDAO();
        nhaCungCapDAO = new NhaCungCapDAO();
        khuyenMaiDAO = new KhuyenMaiDAO();

        String maSP = txt_maSanPham.getText().toString();
        String tenSP = txt_tenSanPham.getText().toString();
        long giaNhap = Integer.parseInt(txt_giaNhap.getText());
        String hinhAnh = "";
        if (file != null) {
            hinhAnh = file.getName();
        }
        int soLuong = Integer.parseInt(txt_soLuong.getText());

        ChatLieu chatLieu = chatLieuDAO.getChatLieuByName(cb_chatLieu.getSelectedItem().toString());
        KieuDang kieuDang = kieuDangDAO.getKieuDangByName(cb_kieuDang.getSelectedItem().toString());
        KichThuoc kichThuoc = kichThuocDAO.getKichThuocByName(cb_kichThuoc.getSelectedItem().toString());
MauSac mauSac = mauSacDAO.getMauSacByName(cb_mauSac.getSelectedItem().toString());
        XuatXu xuatXu = xuatXuDAO.getXuatXuByName(cb_xuatXu.getSelectedItem().toString());
        PhanLoai phanLoai = phanLoaiDAO.getPhanLoaiByName(cb_loaiSanPham.getSelectedItem().toString());
        NhaCungCap nhaCungCap = nhaCungCapDAO.getNhaCungCapByName(cb_nhaCungCap.getSelectedItem().toString());
        KhuyenMai khuyenMai = khuyenMaiDAO.getKhuyenMaiByPhanTram(0);

        return new SanPham(maSP, tenSP, giaNhap, hinhAnh, soLuong, chatLieu, kieuDang, kichThuoc, mauSac, xuatXu, phanLoai, nhaCungCap, khuyenMai);
    }

    public void xoa() {

    	
    	if (tbl_danhSachSanPham.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn dữ liệu để xoá");
            return;
        }

        String maSanPham = tbl_danhSachSanPham.getValueAt(tbl_danhSachSanPham.getSelectedRow(), 0).toString();
        if (sanPhamDAO.xoaSanPham(maSanPham) != -1) {
            
            JOptionPane.showMessageDialog(null, "Xoá thành công");
            tblDanhSachSanPham();
            clearInput();
            return;
        };

        JOptionPane.showMessageDialog(null, "Xoá thất bại");
    }

    private void clearInput() {
      txt_maSanPham.setText("");
      txt_tenSanPham.setText("");
      cb_xuatXu.setSelectedIndex(0);
      cb_nhaCungCap.setSelectedIndex(0);
      cb_mauSac.setSelectedIndex(0);
      cb_kichThuoc.setSelectedIndex(0);
      cb_kieuDang.setSelectedIndex(0);
      cb_chatLieu.setSelectedIndex(0);
      cb_loaiSanPham.setSelectedIndex(0);
      txt_soLuong.setText("");
      txt_giaNhap.setText("");
      txt_giaBan.setText("");
      txt_soLuong.setText("");
      lblHinhAnh.setIcon(new ImageIcon());
		
	}

	private boolean validData() {

        String tenSP = txt_tenSanPham.getText().trim();
        String soLuong = txt_soLuong.getText().trim();
        String giaNhap = txt_giaNhap.getText().trim();

        if (file == null) { // kiem tra da co anh chua
            JOptionPane.showMessageDialog(this, "Phải chọn ảnh cho sản phẩm");
            return false;
        }
        if (!(tenSP.length() > 0 && removeAccent(tenSP).matches("^[A-Z][A-Za-z]*((\\s)[A-Za-z]*)*$"))) { // Chu cai dau phai viet hoa va khong duoc rong
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không hợp lệ");
            txt_tenSanPham.requestFocus();
            return false;
        }
        if (soLuong.length() > 0) { // khong duoc rong
            try {
                int sl = Integer.parseInt(soLuong);
                if (sl < 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                    txt_soLuong.requestFocus();
                    return false;
                }
            } catch (NumberFormatException ex) { // phai la ky tu so
                JOptionPane.showMessageDialog(this, "Số lượng phải là số");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Số lượng không được rỗng");
            txt_soLuong.requestFocus();
            return false;
        }
        
        if (giaNhap.length() > 0) { // khong duoc rong
            try {
                long gn = Long.parseLong(giaNhap);
                if (gn <= 0) {
                    JOptionPane.showMessageDialog(this, "Giá nhập phải lớn hơn 0");
                    txt_soLuong.requestFocus();
                    return false;
                }
} catch (NumberFormatException ex) { // phai la ky tu so
                JOptionPane.showMessageDialog(this, "Giá nhập phải là số");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Giá nhập không được rỗng");
            txt_giaNhap.requestFocus();
            return false;
        }

        return true;
    }
//chuyen tu co dau sang khong dau

    private static String removeAccent(String s) {

        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    private SanPham kiemTraSanPhamTrung(SanPham sanPham) {

        sanPhamDAO = new SanPhamDAO();

        ArrayList<SanPham> listSanPhan = new ArrayList<>();

        listSanPhan = sanPhamDAO.getAllSanPham();

        for (SanPham sp : listSanPhan) {
            if (removeAccent(sp.getTenSP()).equals(removeAccent(sanPham.getTenSP()))) {
                if (sp.getKichThuoc().getKichThuoc().equals(sanPham.getKichThuoc().getKichThuoc())) {
                    if (removeAccent(sp.getMauSac().getMauSac()).equals(removeAccent(sanPham.getMauSac().getMauSac()))) {
                        if (removeAccent(sp.getChatLieu().getChatLieu()).equals(removeAccent(sanPham.getChatLieu().getChatLieu()))) {
                            return sp;      //tra ve san pham neu trung             
                        }
                    }
                }
            }
        }

        return null; //null : khong trung
    }

    



    private void updateValueTable(SanPham sanPham) {
        int row = tbl_danhSachSanPham.getSelectedRow();
        DefaultTableModel dtm = (DefaultTableModel) tbl_danhSachSanPham.getModel();

        dtm.setValueAt(sanPham.getMaSP(), row, 0);
        dtm.setValueAt(sanPham.getTenSP(), row, 1);
        dtm.setValueAt(sanPham.getPhanLoai().getLoaiSanPham(), row, 2);
        String giaBan = NumberFormat.getInstance().format(sanPham.tinhGiaBanLucSau());
        dtm.setValueAt(giaBan, row, 3);
        dtm.setValueAt(sanPham.getKichThuoc().getKichThuoc(), row, 4);
        dtm.setValueAt(sanPham.getMauSac().getMauSac(), row, 5);
        dtm.setValueAt(sanPham.getChatLieu().getChatLieu(), row, 6);
        dtm.setValueAt(sanPham.getNhaCungCap().getTenNhaCungCap(), row, 7);
        dtm.setValueAt(sanPham.getSoLuong(), row, 8);
    }

}
