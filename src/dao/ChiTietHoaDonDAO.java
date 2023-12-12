package dao;

import ConnectDB.KetNoiSQL;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChiTietHoaDonDAO {
    private SanPhamDAO sanPham_DAO = new SanPhamDAO();
    private HoaDonDAO hoaDon_DAO = new HoaDonDAO();
    private NhanVienDAO nhanVien_DAO = new NhanVienDAO();
    private KhachHangDAO khachHang_DAO = new KhachHangDAO();
    
    public ChiTietHoaDonDAO() {
    }
    
    public ArrayList<ChiTietHoaDon> getAllCTHDByHoaDon(HoaDon hoaDon){
        ArrayList<ChiTietHoaDon>listChiTietHoaDon = new ArrayList<>();
        KetNoiSQL.getInstance();
            Connection conn = KetNoiSQL.getConnection();
        try {
            String sql = "Select * from ChiTietHoaDon where maHoaDon = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, hoaDon.getMaHoaDon());
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                SanPham sanPham = sanPham_DAO.getSanPhamById(rs.getString(1));
                
                int soLuong = rs.getInt(3);
                long thanhTien = rs.getLong(4);
                long thue = rs.getLong(5);
                long tongTienHD = rs.getLong(6);
                ChiTietHoaDon CTHD = new ChiTietHoaDon(sanPham, hoaDon, soLuong,thanhTien, thue, tongTienHD);
                
                listChiTietHoaDon.add(CTHD);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MauSacDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listChiTietHoaDon;
    }

    public int deleteCTHD(HoaDon hd){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "Delete from ChiTietHoaDon where maHoaDon = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, hd.getMaHoaDon());
            
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    public ArrayList<ChiTietHoaDon> getCTHDById(String id){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
       ArrayList<ChiTietHoaDon> listCTHD = new ArrayList<ChiTietHoaDon>();

        try {
            String sql = "select * from ChiTietHoaDon where maHoaDon = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            HoaDon hoaDon = hoaDon_DAO.getHoaDonById(id);
            while(rs.next()){
               SanPham sanPham = sanPham_DAO.getSanPhamById(rs.getString(1));
               int soLuong = rs.getInt(3);
               long thanhTien = rs.getLong(4);
               long thue = rs.getLong(5);
               long tongTienHD = rs.getLong(6);
               ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(sanPham, hoaDon, soLuong,thanhTien, thue, tongTienHD);
               
               listCTHD.add(chiTietHoaDon);
            }
            return listCTHD;
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    public int addChiTietHoaDon(ChiTietHoaDon chiTietHoaDon){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
        	 String sql = "insert into ChiTietHoaDon values (?, ?, ?,?,?,?)";
             PreparedStatement stmt = conn.prepareCall(sql);
             stmt.setString(1, chiTietHoaDon.getSanPham().getMaSP());
             stmt.setString(2, chiTietHoaDon.getHoaDon().getMaHoaDon());
             stmt.setInt(3, chiTietHoaDon.getSoLuong());
             stmt.setDouble(4, chiTietHoaDon.getThanhTien());
             stmt.setDouble(5, chiTietHoaDon.getThue());
             stmt.setDouble(6, chiTietHoaDon.getTongTienHD());

            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public ArrayList<SanPham>thongKeDanhSachSanPhamVoiSoLuongBanDuoc(){
        ArrayList<SanPham> listSanPham = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql = "SELECT        sanPham.maSP,  sum(ChiTietHoaDon.soLuong) as SoLuongBan, sum(ChiTietHoaDon.thanhTien) as doanhThu\n" +
"                        FROM            sanPham INNER JOIN ChiTietHoaDon ON sanPham.maSP = ChiTietHoaDon.maSP\n" +
"                         group by  sanPham.maSP";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                SanPham sp = sanPham_DAO.getSanPhamById(rs.getString(1));
                sp.setSoLuong(rs.getInt(2));
                sp.setGiaNhap(rs.getLong(3));
                listSanPham.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSanPham;
    }
    
    public ArrayList<SanPham>thongKeDanhSachSanPhamVoiSoLuongBanDuoc(String mauSac, String phanLoai, String kichThuoc){
        ArrayList<SanPham> listSanPham = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql = "SELECT        sanPham.maSP,  sum(ChiTietHoaDon.soLuong) as SoLuongBan, sum(ChiTietHoaDon.thanhTien) as doanhThu "
                    + "FROM            ChiTietHoaDon INNER JOIN sanPham ON ChiTietHoaDon.maSP = sanPham.maSP INNER JOIN KichThuoc ON sanPham.maKichThuoc = KichThuoc.maKichThuoc INNER JOIN MauSac ON sanPham.maMauSac = MauSac.maMauSac INNER JOIN PhanLoai ON sanPham.maPhanLoai = PhanLoai.maPhanLoai "
                    + "where		KichThuoc.kichThuoc like ? and MauSac.mauSac like ? and PhanLoai.loaiSP like ? "
                    + "group by  sanPham.maSP";
            
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, "%"+kichThuoc+"%");
            stmt.setString(2, "%"+mauSac+"%");
            stmt.setString(3, "%"+phanLoai+"%");
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                SanPham sp = sanPham_DAO.getSanPhamById(rs.getString(1));
                sp.setSoLuong(rs.getInt(2));
                sp.setGiaNhap(rs.getLong(3));
                listSanPham.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSanPham;
    }
    
    
    public ArrayList<SanPham>thongKeDanhSachSanPhamVoiSoLuongBanDuoc(String mauSac, String phanLoai, String kichThuoc, String tuNgay, String denNgay){
        ArrayList<SanPham> listSanPham = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql = "SELECT	sanPham.maSP,  sum(ChiTietHoaDon.soLuong) as SoLuongBan, sum(ChiTietHoaDon.thanhTien) as doanhThu \n" +
"                            FROM            ChiTietHoaDon INNER JOIN\n" +
"                                                     HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\n" +
"                                                     sanPham ON ChiTietHoaDon.maSP = sanPham.maSP INNER JOIN\n" +
"                                                     KichThuoc ON sanPham.maKichThuoc = KichThuoc.maKichThuoc INNER JOIN\n" +
"                                                     MauSac ON sanPham.maMauSac = MauSac.maMauSac INNER JOIN\n" +
"                                                     PhanLoai ON sanPham.maPhanLoai = PhanLoai.maPhanLoai\n" +
"                            where			KichThuoc.kichThuoc like ? and MauSac.mauSac like ? \n" +
"                          and PhanLoai.loaiSP like ? and HoaDon.ngaylap >= ? and HoaDon.ngaylap <= ? \n" +
"                          group by sanPham.maSP";
            
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, "%"+kichThuoc+"%");
            stmt.setString(2, "%"+mauSac+"%");
            stmt.setString(3, "%"+phanLoai+"%");
            stmt.setString(4, tuNgay);
            stmt.setString(5, denNgay);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                SanPham sp = sanPham_DAO.getSanPhamById(rs.getString(1));
                sp.setSoLuong(rs.getInt(2));
                sp.setGiaNhap(rs.getLong(3));
                listSanPham.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSanPham;
    }
    
    
    public ArrayList<HoaDon>danhSachHoaDonTheoSanPham(String maSP, String tuNgay, String denNgay){
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql = "SELECT			HoaDon.maHoaDon, HoaDon.maKhachHang, HoaDon.maNhanVien, HoaDon.ngaylap\n" +
"                            FROM            ChiTietHoaDon INNER JOIN\n" +
"                                                    HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\n" +
"                                                    sanPham ON ChiTietHoaDon.maSP = sanPham.maSP INNER JOIN\n" +
"                                                    KichThuoc ON sanPham.maKichThuoc = KichThuoc.maKichThuoc INNER JOIN\n" +
"                                                     MauSac ON sanPham.maMauSac = MauSac.maMauSac INNER JOIN\n" +
"                                                     PhanLoai ON sanPham.maPhanLoai = PhanLoai.maPhanLoai\n" +
"     where                      HoaDon.ngaylap >= ? and HoaDon.ngaylap <= ? and sanPham.maSP = ?";
            
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, tuNgay);
            stmt.setString(2, denNgay);
            stmt.setString(3, maSP);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString(1));
                KhachHang kh = khachHang_DAO.getKhachHangById(rs.getString(2));
                hd.setKhachHang(kh);
                NhanVien nv = nhanVien_DAO.getNhanVienByID(rs.getString(3));
                hd.setNhanVien(nv);
                hd.setNgayLap(rs.getDate(4));
                listHoaDon.add(hd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listHoaDon;
    }
    
     public ArrayList<HoaDon>danhSachHoaDonTheoSanPham(String maSP){
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql = "SELECT			HoaDon.maHoaDon, HoaDon.maKhachHang, HoaDon.maNhanVien, HoaDon.ngaylap\n" +
"                            FROM            ChiTietHoaDon INNER JOIN\n" +
"                                                    HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\n" +
"                                                    sanPham ON ChiTietHoaDon.maSP = sanPham.maSP INNER JOIN\n" +
"                                                    KichThuoc ON sanPham.maKichThuoc = KichThuoc.maKichThuoc INNER JOIN\n" +
"                                                     MauSac ON sanPham.maMauSac = MauSac.maMauSac INNER JOIN\n" +
"                                                     PhanLoai ON sanPham.maPhanLoai = PhanLoai.maPhanLoai\n" +
"     where                       sanPham.maSP = ?";
            
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, maSP);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getString(1));
                KhachHang kh = khachHang_DAO.getKhachHangById(rs.getString(2));
                hd.setKhachHang(kh);
                NhanVien nv = nhanVien_DAO.getNhanVienByID(rs.getString(3));
                hd.setNhanVien(nv);
                hd.setNgayLap(rs.getDate(4));
                listHoaDon.add(hd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listHoaDon;
    }
     
    public double getTongDoanhThu(){
        KetNoiSQL.getConnection();
        Connection conn = KetNoiSQL.getConnection();
        String sql = "SELECT        sum(thanhTien)\n" +
                     "FROM            ChiTietHoaDon";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                return rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public double tongTienHoaDon(String maHD){
        KetNoiSQL.getConnection();
        Connection conn = KetNoiSQL.getConnection();
        String sql  = "select ChiTietHoaDon.maHoaDon,  tongTien = sum(ChiTietHoaDon.thanhTien) \n" +
                        "from ChiTietHoaDon \n" +
                        "where maHoaDon = ?\n" +
                        "group by ChiTietHoaDon.maHoaDon";
        
        try {
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, maHD);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                return rs.getDouble(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
