package gui; 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.SanPham;

public class QuanLyHoaDon extends javax.swing.JPanel {

    private HoaDonDAO hoaDon_DAO = new HoaDonDAO();
    private ChiTietHoaDonDAO cthd_DAO = new ChiTietHoaDonDAO();

    
    public QuanLyHoaDon() {
        initComponents();
        loadTblHoaDon();
        designTable();

    }

    private void designTable() {
        //Hóa đơn
       tbl_HoaDon.getTableHeader().setFont(new java.awt.Font("Calibri", 0, 12));
       tbl_HoaDon.getTableHeader().setOpaque(false);
       tbl_HoaDon.getTableHeader().setBackground(new Color(198, 226, 255));
       tbl_HoaDon.getTableHeader().setForeground(new Color(16,54,103));
       tbl_HoaDon.setDefaultEditor(Object.class, null);
       //Sản phẩm
       tbl_SanPham.getTableHeader().setFont(new java.awt.Font("Calibri", 0, 12));
       tbl_SanPham.getTableHeader().setOpaque(false);
       tbl_SanPham.getTableHeader().setBackground(new Color(198, 226, 255));
       tbl_SanPham.getTableHeader().setForeground(new Color(16,54,103));
       tbl_SanPham.setDefaultEditor(Object.class, null);
   }

   //clear table hóa đơn
    public void clearTableHoaDon() {
        DefaultTableModel dtm = (DefaultTableModel) tbl_HoaDon.getModel();
        dtm.setRowCount(0);
    }

    //load table hóa đơn
    public void loadTblHoaDon() {
        clearTableHoaDon();
        hoaDon_DAO = new HoaDonDAO();
        DefaultTableModel dtm = (DefaultTableModel) tbl_HoaDon.getModel();
        ArrayList<HoaDon> listHoaDon = hoaDon_DAO.getAllHoaDon();
        for (HoaDon hoaDon : listHoaDon) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(hoaDon.getNgayLap());
            Object[] rowData = {hoaDon.getMaHoaDon(), hoaDon.getNhanVien().getHoVaTen(), hoaDon.getKhachHang().getHoVaTen(), date};
            dtm.addRow(rowData);
        }

    }

    //clear table sản phẩm
    public void clearTableSanPham() {
        DefaultTableModel dtm = (DefaultTableModel) tbl_SanPham.getModel();
        dtm.setRowCount(0);
    }

    //load table sản phẩm
    public void loadTblSanPham(String id) {
        clearTableSanPham();
        DefaultTableModel dtm = (DefaultTableModel) tbl_SanPham.getModel();
        ArrayList<ChiTietHoaDon> listCTHD = getListChiTietHoaDonByHoaDon(id);
        for (ChiTietHoaDon chiTietHoaDon : listCTHD) {
            Object[] rowData = {chiTietHoaDon.getSanPham().getMaSP(), chiTietHoaDon.getSanPham().getTenSP(), chiTietHoaDon.getThanhTien() / chiTietHoaDon.getSoLuong(), chiTietHoaDon.getSoLuong(), chiTietHoaDon.getSanPham().getPhanLoai().getLoaiSanPham(), chiTietHoaDon.getSanPham().getMauSac().getMauSac(), chiTietHoaDon.getSanPham().getChatLieu().getChatLieu(), chiTietHoaDon.getSanPham().getKichThuoc().getKichThuoc()};
            dtm.addRow(rowData);
        }

    }

    public ArrayList<SanPham> sanPhamFromHoaDon() {
        return null;
    }

    public ArrayList<ChiTietHoaDon> getListChiTietHoaDonByHoaDon(String id) {

        cthd_DAO = new ChiTietHoaDonDAO();

        return cthd_DAO.getCTHDById(id);
    }

    //tính tổng tiền
    public long tinhTongTien(String id) {
        ArrayList<ChiTietHoaDon> listCTHD = getListChiTietHoaDonByHoaDon(id);
        long tongTien = 0;
        for (ChiTietHoaDon chiTietHoaDon : listCTHD) {
            tongTien += chiTietHoaDon.getThanhTien();
        }
        return tongTien;
    }

    //lấy ngày
    public Date getDate(JDateChooser dateChooser) {
        long date = dateChooser.getDate().getTime();
        return new Date(date);
    }


    private void initComponents() {

        pnl_DanhSachHoaDon = new javax.swing.JPanel();
        scr_DanhSachHoaDon = new javax.swing.JScrollPane();
        tbl_HoaDon = new javax.swing.JTable();
        pnl_DanhSachSanPhamDaMua = new javax.swing.JPanel();
        scr_DanhSachSanPhamDaMua = new javax.swing.JScrollPane();
        tbl_SanPham = new javax.swing.JTable();
        pnl_ThongTinHoaDon = new javax.swing.JPanel();
        lbl_NgayLap = new javax.swing.JLabel();
        lbl_TenNhanVien = new javax.swing.JLabel();
        lbl_TenKhachHang = new javax.swing.JLabel();
        txt_Ngay = new javax.swing.JLabel();
        lbl_TongSoTien = new javax.swing.JLabel();
        lbl_TenNV = new javax.swing.JLabel();
        lbl_TenKH = new javax.swing.JLabel();
        lbl_TongTien = new javax.swing.JLabel();
        pnl_TimKiem = new javax.swing.JPanel();
        btn_Tim = new javax.swing.JButton();
        btn_Lammoi = new javax.swing.JButton();
        lbl_TimKiemHoaDon = new javax.swing.JLabel();
        txt_KeyWord = new javax.swing.JTextField();


        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1130, 740));
        setMinimumSize(new java.awt.Dimension(1130, 740));
        setPreferredSize(new java.awt.Dimension(1130, 740));

        pnl_DanhSachHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        pnl_DanhSachHoaDon.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hoá đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14)), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        tbl_HoaDon.setFont(new java.awt.Font("Calibri", 0, 12));
        tbl_HoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Mã hoá đơn", "Tên nhân viên", "Tên khách hàng", "Ngày lập"
            }
        ));
        tbl_HoaDon.setRowHeight(30);
        tbl_HoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_HoaDonMousePressed(evt);
            }
        });
        scr_DanhSachHoaDon.setViewportView(tbl_HoaDon);

        javax.swing.GroupLayout pnl_DanhSachHoaDonLayout = new javax.swing.GroupLayout(pnl_DanhSachHoaDon);
        pnl_DanhSachHoaDon.setLayout(pnl_DanhSachHoaDonLayout);
        pnl_DanhSachHoaDonLayout.setHorizontalGroup(
            pnl_DanhSachHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scr_DanhSachHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 1065, Short.MAX_VALUE)
        );
        pnl_DanhSachHoaDonLayout.setVerticalGroup(
            pnl_DanhSachHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scr_DanhSachHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pnl_DanhSachSanPhamDaMua.setBackground(new java.awt.Color(255, 255, 255));
        pnl_DanhSachSanPhamDaMua.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết hoá đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14)), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        pnl_DanhSachSanPhamDaMua.setFont(new java.awt.Font("Segoe UI", 0, 14));

        tbl_SanPham.setFont(new java.awt.Font("Segoe UI", 0, 14));
        tbl_SanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Số lượng", "Loại sản phẩm", "Màu sắc", "Chất liệu", "Kích thước"
            }
        ));
        tbl_SanPham.setRowHeight(30);
        scr_DanhSachSanPhamDaMua.setViewportView(tbl_SanPham);

        javax.swing.GroupLayout pnl_DanhSachSanPhamDaMuaLayout = new javax.swing.GroupLayout(pnl_DanhSachSanPhamDaMua);
        pnl_DanhSachSanPhamDaMua.setLayout(pnl_DanhSachSanPhamDaMuaLayout);
        pnl_DanhSachSanPhamDaMuaLayout.setHorizontalGroup(
            pnl_DanhSachSanPhamDaMuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_DanhSachSanPhamDaMuaLayout.createSequentialGroup()
                .addComponent(scr_DanhSachSanPhamDaMua, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pnl_DanhSachSanPhamDaMuaLayout.setVerticalGroup(
            pnl_DanhSachSanPhamDaMuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scr_DanhSachSanPhamDaMua, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
        );

        pnl_ThongTinHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        pnl_ThongTinHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14)));
        pnl_ThongTinHoaDon.setFont(new java.awt.Font("Calibri", 0, 12));
        
        lbl_NgayLap.setFont(new java.awt.Font("Calibri", 0, 12));
        lbl_NgayLap.setText("Ngày lập hóa đơn:");
        lbl_NgayLap.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lbl_TenNhanVien.setFont(new java.awt.Font("Calibri", 0, 12));
        lbl_TenNhanVien.setText("Nhân Viên thanh toán:");

        lbl_TenKhachHang.setFont(new java.awt.Font("Calibri", 0, 12));
        lbl_TenKhachHang.setText("Khách Hàng:");
        lbl_TenKhachHang.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lbl_TongSoTien.setFont(new java.awt.Font("Calibri", 0, 12));
        lbl_TongSoTien.setText("Thành tiền:");

        txt_Ngay.setFont(new java.awt.Font("Segoe UI", 1, 13));
        Color clr = new Color(198, 226, 255);
        txt_Ngay.setBorder(BorderFactory.createLineBorder(clr));
        lbl_TenNV.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lbl_TenNV.setBorder(BorderFactory.createLineBorder(clr));
        lbl_TenKH.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lbl_TenKH.setBorder(BorderFactory.createLineBorder(clr));
        lbl_TongTien.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lbl_TongTien.setBorder(BorderFactory.createLineBorder(clr));

        javax.swing.GroupLayout pnl_ThongTinHoaDonLayout = new javax.swing.GroupLayout(pnl_ThongTinHoaDon);
        pnl_ThongTinHoaDon.setLayout(pnl_ThongTinHoaDonLayout);
            // set pnl_ThongTinHoaDonLayout horizontal
        pnl_ThongTinHoaDonLayout.setHorizontalGroup(
            pnl_ThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ThongTinHoaDonLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(pnl_ThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_ThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbl_TenNhanVien)
                        .addComponent(lbl_NgayLap)
                        .addComponent(lbl_TenKhachHang)
                        .addComponent(lbl_TongSoTien)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_ThongTinHoaDonLayout.createSequentialGroup()
                            .addComponent(lbl_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)))
                    .addGroup(pnl_ThongTinHoaDonLayout.createSequentialGroup()
                        .addGroup(pnl_ThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_TenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)))
                .addContainerGap(55, Short.MAX_VALUE))
        );    

        pnl_ThongTinHoaDonLayout.setVerticalGroup(
            pnl_ThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ThongTinHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_NgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txt_Ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_TenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbl_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_TenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbl_TenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_TongSoTien)
                .addGap(0, 0, 0)
                .addComponent(lbl_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pnl_TimKiem.setBackground(new java.awt.Color(255, 255, 255));
        pnl_TimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14)));
        pnl_TimKiem.setFont(new java.awt.Font("Calibri", 0, 12));
        
        btn_Tim.setFont(new java.awt.Font("Calibri", 0, 12));
        btn_Tim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png")));
        btn_Tim.setText("Tìm");
        btn_Tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimActionPerformed(evt);
            }
        });
        
        btn_Lammoi.setFont(new java.awt.Font("Calibri", 0, 12));
        btn_Lammoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Pulse.png")));
        btn_Lammoi.setText("Làm mới");
        btn_Lammoi.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btn_LammoiActionPerformed(e);				
			}
            
        });

        lbl_TimKiemHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lbl_TimKiemHoaDon.setText("Từ khóa:");

        txt_KeyWord.setFont(new java.awt.Font("Calibri", 0, 12));

        javax.swing.GroupLayout pnl_TimKiemLayout = new javax.swing.GroupLayout(pnl_TimKiem);
        pnl_TimKiem.setLayout(pnl_TimKiemLayout);
        pnl_TimKiemLayout.setHorizontalGroup(
            pnl_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_TimKiemLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lbl_TimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_KeyWord, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Tim)
                .addGap(18, 18, 18)
                .addComponent(btn_Lammoi)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        pnl_TimKiemLayout.setVerticalGroup(
            pnl_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_TimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Lammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnl_TimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_TimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_KeyWord, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                        .addComponent(pnl_DanhSachSanPhamDaMua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnl_ThongTinHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        	.addComponent(pnl_DanhSachHoaDon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnl_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_DanhSachHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnl_DanhSachSanPhamDaMua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pnl_ThongTinHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addContainerGap())
        );
    }

    private void btn_TimActionPerformed(java.awt.event.ActionEvent evt) {
        clearTableHoaDon();
        clearTableSanPham();
        String keyWord = txt_KeyWord.getText().trim().toLowerCase();
        
        hoaDon_DAO = new HoaDonDAO();
        ArrayList<HoaDon> listHoaDon = hoaDon_DAO.getAllHoaDon();
        DefaultTableModel dtml = (DefaultTableModel) tbl_HoaDon.getModel();
        for(HoaDon hoaDon : listHoaDon){
            if(hoaDon.getMaHoaDon().toLowerCase().contains(keyWord) ||
             hoaDon.getNhanVien().getHoVaTen().toLowerCase().contains(keyWord) || 
             hoaDon.getKhachHang().getHoVaTen().toLowerCase().contains(keyWord) || 
             hoaDon.getKhachHang().getSdt().contains(keyWord) || 
             hoaDon.getNhanVien().getMaNhanVien().toLowerCase().contains(keyWord)) {

            Object[] rowdata={hoaDon.getMaHoaDon(), hoaDon.getNhanVien().getHoVaTen(), 
                hoaDon.getKhachHang().getHoVaTen(), hoaDon.getNgayLap()};
            dtml.addRow(rowdata);

            }
        }
    }
    
    private void btn_LammoiActionPerformed(java.awt.event.ActionEvent evt) {
        txt_KeyWord.setText("");
    }

    private void tbl_HoaDonMousePressed(java.awt.event.MouseEvent evt) {
        int row = tbl_HoaDon.getSelectedRow();

        DefaultTableModel dtm = (DefaultTableModel) tbl_HoaDon.getModel();
        String id = dtm.getValueAt(row, 0).toString().trim();
        String tenNV = dtm.getValueAt(row, 1).toString().trim();
        String tenKH = dtm.getValueAt(row, 2).toString().trim();
        String date = dtm.getValueAt(row, 3).toString().trim();

        lbl_TenNV.setText(tenNV);
        lbl_TenKH.setText(tenKH);
        txt_Ngay.setText(date);

        String tongTien = NumberFormat.getInstance().format(tinhTongTien(id));
        lbl_TongTien.setText(tongTien +" VNĐ");

        loadTblSanPham(id);
    }

    private javax.swing.JButton btn_Tim;
    private javax.swing.JButton btn_Lammoi;
    private javax.swing.JLabel lbl_TimKiemHoaDon;
    private javax.swing.JLabel lbl_NgayLap;
    private javax.swing.JLabel lbl_TenNhanVien;
    private javax.swing.JLabel lbl_TenKhachHang;
    private javax.swing.JLabel lbl_TongSoTien;
    private javax.swing.JPanel pnl_DanhSachHoaDon;
    private javax.swing.JPanel pnl_DanhSachSanPhamDaMua;
    private javax.swing.JPanel pnl_ThongTinHoaDon;
    private javax.swing.JScrollPane scr_DanhSachHoaDon;
    private javax.swing.JScrollPane scr_DanhSachSanPhamDaMua;
    private javax.swing.JLabel lbl_TenKH;
    private javax.swing.JLabel lbl_TenNV;
    private javax.swing.JLabel lbl_TongTien;
    private javax.swing.JPanel pnl_TimKiem;
    private javax.swing.JTable tbl_HoaDon;
    private javax.swing.JTable tbl_SanPham;
    private javax.swing.JLabel txt_Ngay;
    private javax.swing.JTextField txt_KeyWord;

}
