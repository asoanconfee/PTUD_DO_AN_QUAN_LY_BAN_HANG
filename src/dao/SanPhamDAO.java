package dao;

import ConnectDB.KetNoiSQL;
import entity.ChatLieu;
import entity.KhuyenMai;
import entity.KichThuoc;
import entity.KieuDang;
import entity.MauSac;
import entity.NhaCungCap;
import entity.PhanLoai;
import entity.SanPham;
import entity.XuatXu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamDAO {

    public SanPhamDAO() {
    }

    public ArrayList<SanPham> getAllSanPham() {

        ChatLieuDAO chatLieuDao = new ChatLieuDAO();
        KieuDangDAO kieuDangDao = new KieuDangDAO();
        KichThuocDAO kichThuocDao = new KichThuocDAO();
        MauSacDAO mauSacDAO = new MauSacDAO();
        XuatXuDAO xuatXuDAO = new XuatXuDAO();
        PhanLoaiDAO phanLoaiDAO = new PhanLoaiDAO();
        NhaCungCapDAO nhaCungCapDao = new NhaCungCapDAO();
        KhuyenMaiDAO khuyenMaiDao = new KhuyenMaiDAO();

        ArrayList<SanPham> listSanPham = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();

        try {
            String sql = "Select * from sanpham";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maSP = rs.getString(1);
                String tenSP = rs.getString(2);
                long giaNhap = rs.getLong(3);
                String hinhAnh = rs.getString(4);
                int soLuong = rs.getInt(5);

                String maChatLieu = rs.getString(6);
                ChatLieu chatLieu = chatLieuDao.getChatLieu(maChatLieu);

                String maKieuDang = rs.getString(7);
                KieuDang kieuDang = kieuDangDao.getKieuDang(maKieuDang);

                String maKichThuoc = rs.getString(8);
                KichThuoc kichThuoc = kichThuocDao.getKichThuoc(maKichThuoc);

                String maMauSac = rs.getString(9);
                MauSac ms = mauSacDAO.getMauSac(maMauSac);

                String maXuatXu = rs.getString(10);
                XuatXu xuatXu = xuatXuDAO.getXuatXu(maXuatXu);

                String maPhanLoai = rs.getString(11);
                PhanLoai phanLoai = phanLoaiDAO.getPhanLoai(maPhanLoai);

                String maNCC = rs.getString(12);
                NhaCungCap nhaCungCap = nhaCungCapDao.getNhaCungCap(maNCC);

                String maKhuyenMai = rs.getString(13);
                KhuyenMai khuyenMai = khuyenMaiDao.getKhuyenMai(maKhuyenMai);

                SanPham sanPham = new SanPham(maSP, tenSP, giaNhap, hinhAnh, soLuong, chatLieu, kieuDang, kichThuoc, ms, xuatXu, phanLoai, nhaCungCap, khuyenMai);
                listSanPham.add(sanPham);
            }

        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSanPham;
    }
    
    public ArrayList<SanPham> getAllSanPham(String maSanPham, String tenSanPham, String loaiSanPham, String mauSac, String kichCo) {

        ChatLieuDAO chatLieuDao = new ChatLieuDAO();
        KieuDangDAO kieuDangDao = new KieuDangDAO();
        KichThuocDAO kichThuocDao = new KichThuocDAO();
        MauSacDAO mauSacDAO = new MauSacDAO();
        XuatXuDAO xuatXuDAO = new XuatXuDAO();
        PhanLoaiDAO phanLoaiDAO = new PhanLoaiDAO();
        NhaCungCapDAO nhaCungCapDao = new NhaCungCapDAO();
        KhuyenMaiDAO khuyenMaiDao = new KhuyenMaiDAO();

        ArrayList<SanPham> listSanPham = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();

        try {
            String sql= "SELECT        sanPham.*\n" +
                        "FROM            KichThuoc INNER JOIN\n" +
                        "                         sanPham ON KichThuoc.maKichThuoc = sanPham.maKichThuoc INNER JOIN\n" +
                        "                         MauSac ON sanPham.maMauSac = MauSac.maMauSac INNER JOIN\n" +
                        "                         PhanLoai ON sanPham.maPhanLoai = PhanLoai.maPhanLoai\n" +
                        "where		sanPham.maSP like ? and kichThuoc.kichThuoc like ? and PhanLoai.loaiSP like ? and MauSac.mauSac like ? and sanPham.tenSP like ?";
            
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, "%"+maSanPham+"%");
            stmt.setString(2,  "%"+kichCo +"%");
            stmt.setString(3, "%" +loaiSanPham+ "%");
            stmt.setString(4,  "%"+mauSac +"%");
            stmt.setString(5, "%" + tenSanPham +"%");
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maSP = rs.getString(1);
                String tenSP = rs.getString(2);
                long giaNhap = rs.getLong(3);
                String hinhAnh = rs.getString(4);
                int soLuong = rs.getInt(5);

                String maChatLieu = rs.getString(6);
                ChatLieu chatLieu = chatLieuDao.getChatLieu(maChatLieu);

                String maKieuDang = rs.getString(7);
                KieuDang kieuDang = kieuDangDao.getKieuDang(maKieuDang);

                String maKichThuoc = rs.getString(8);
                KichThuoc kichThuoc = kichThuocDao.getKichThuoc(maKichThuoc);

                String maMauSac = rs.getString(9);
                MauSac ms = mauSacDAO.getMauSac(maMauSac);

                String maXuatXu = rs.getString(10);
                XuatXu xuatXu = xuatXuDAO.getXuatXu(maXuatXu);

                String maPhanLoai = rs.getString(11);
                PhanLoai phanLoai = phanLoaiDAO.getPhanLoai(maPhanLoai);

                String maNCC = rs.getString(12);
                NhaCungCap nhaCungCap = nhaCungCapDao.getNhaCungCap(maNCC);

                String maKhuyenMai = rs.getString(13);
                KhuyenMai khuyenMai = khuyenMaiDao.getKhuyenMai(maKhuyenMai);

                SanPham sanPham = new SanPham(maSP, tenSP, giaNhap, hinhAnh, soLuong, chatLieu, kieuDang, kichThuoc, ms, xuatXu, phanLoai, nhaCungCap, khuyenMai);
                listSanPham.add(sanPham);
            }

        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSanPham;
    }
    
    public ArrayList<SanPham> getAllSanPhamVuotDinhMuc(String maSanPham, String tenSanPham, String loaiSanPham, String mauSac, String kichCo, int soLuongDinhMuc) {

        ChatLieuDAO chatLieuDao = new ChatLieuDAO();
        KieuDangDAO kieuDangDao = new KieuDangDAO();
        KichThuocDAO kichThuocDao = new KichThuocDAO();
        MauSacDAO mauSacDAO = new MauSacDAO();
        XuatXuDAO xuatXuDAO = new XuatXuDAO();
        PhanLoaiDAO phanLoaiDAO = new PhanLoaiDAO();
        NhaCungCapDAO nhaCungCapDao = new NhaCungCapDAO();
        KhuyenMaiDAO khuyenMaiDao = new KhuyenMaiDAO();

        ArrayList<SanPham> listSanPham = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();

        try {
            String sql= "SELECT        sanPham.*\n" +
                        "FROM            KichThuoc INNER JOIN\n" +
                        "                         sanPham ON KichThuoc.maKichThuoc = sanPham.maKichThuoc INNER JOIN\n" +
                        "                         MauSac ON sanPham.maMauSac = MauSac.maMauSac INNER JOIN\n" +
                        "                         PhanLoai ON sanPham.maPhanLoai = PhanLoai.maPhanLoai\n" +
                        "where		sanPham.maSP like ? and kichThuoc.kichThuoc like ? and PhanLoai.loaiSP like ? and MauSac.mauSac like ? and sanPham.tenSP like ?"
                    + " and sanPham.soLuong > ?";
            
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, "%"+maSanPham+"%");
            stmt.setString(2,  "%"+kichCo +"%");
            stmt.setString(3, "%" +loaiSanPham+ "%");
            stmt.setString(4,  "%"+mauSac +"%");
            stmt.setString(5, "%" + tenSanPham +"%");
            stmt.setInt(6, soLuongDinhMuc);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maSP = rs.getString(1);
                String tenSP = rs.getString(2);
                long giaNhap = rs.getLong(3);
                String hinhAnh = rs.getString(4);
                int soLuong = rs.getInt(5);

                String maChatLieu = rs.getString(6);
                ChatLieu chatLieu = chatLieuDao.getChatLieu(maChatLieu);

                String maKieuDang = rs.getString(7);
                KieuDang kieuDang = kieuDangDao.getKieuDang(maKieuDang);

                String maKichThuoc = rs.getString(8);
                KichThuoc kichThuoc = kichThuocDao.getKichThuoc(maKichThuoc);

                String maMauSac = rs.getString(9);
                MauSac ms = mauSacDAO.getMauSac(maMauSac);

                String maXuatXu = rs.getString(10);
                XuatXu xuatXu = xuatXuDAO.getXuatXu(maXuatXu);

                String maPhanLoai = rs.getString(11);
                PhanLoai phanLoai = phanLoaiDAO.getPhanLoai(maPhanLoai);

                String maNCC = rs.getString(12);
                NhaCungCap nhaCungCap = nhaCungCapDao.getNhaCungCap(maNCC);

                String maKhuyenMai = rs.getString(13);
                KhuyenMai khuyenMai = khuyenMaiDao.getKhuyenMai(maKhuyenMai);

                SanPham sanPham = new SanPham(maSP, tenSP, giaNhap, hinhAnh, soLuong, chatLieu, kieuDang, kichThuoc, ms, xuatXu, phanLoai, nhaCungCap, khuyenMai);
                listSanPham.add(sanPham);
            }

        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSanPham;
    }
    
       public ArrayList<SanPham> getAllSanPhamDuoiDinhMuc(String maSanPham, String tenSanPham, String loaiSanPham, String mauSac, String kichCo, int soLuongDinhMuc) {

        ChatLieuDAO chatLieuDao = new ChatLieuDAO();
        KieuDangDAO kieuDangDao = new KieuDangDAO();
        KichThuocDAO kichThuocDao = new KichThuocDAO();
        MauSacDAO mauSacDAO = new MauSacDAO();
        XuatXuDAO xuatXuDAO = new XuatXuDAO();
        PhanLoaiDAO phanLoaiDAO = new PhanLoaiDAO();
        NhaCungCapDAO nhaCungCapDao = new NhaCungCapDAO();
        KhuyenMaiDAO khuyenMaiDao = new KhuyenMaiDAO();

        ArrayList<SanPham> listSanPham = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();

        try {
            String sql= "SELECT        sanPham.*\n" +
                        "FROM            KichThuoc INNER JOIN\n" +
                        "                         sanPham ON KichThuoc.maKichThuoc = sanPham.maKichThuoc INNER JOIN\n" +
                        "                         MauSac ON sanPham.maMauSac = MauSac.maMauSac INNER JOIN\n" +
                        "                         PhanLoai ON sanPham.maPhanLoai = PhanLoai.maPhanLoai\n" +
                        "where		sanPham.maSP like ? and kichThuoc.kichThuoc like ? and PhanLoai.loaiSP like ? and MauSac.mauSac like ? and sanPham.tenSP like ?"
                    + " and sanPham.soLuong <= ?";
            
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, "%"+maSanPham+"%");
            stmt.setString(2,  "%"+kichCo +"%");
            stmt.setString(3, "%" +loaiSanPham+ "%");
            stmt.setString(4,  "%"+mauSac +"%");
            stmt.setString(5, "%" + tenSanPham +"%");
            stmt.setInt(6, soLuongDinhMuc);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maSP = rs.getString(1);
                String tenSP = rs.getString(2);
                long giaNhap = rs.getLong(3);
                String hinhAnh = rs.getString(4);
                int soLuong = rs.getInt(5);

                String maChatLieu = rs.getString(6);
                ChatLieu chatLieu = chatLieuDao.getChatLieu(maChatLieu);

                String maKieuDang = rs.getString(7);
                KieuDang kieuDang = kieuDangDao.getKieuDang(maKieuDang);

                String maKichThuoc = rs.getString(8);
                KichThuoc kichThuoc = kichThuocDao.getKichThuoc(maKichThuoc);

                String maMauSac = rs.getString(9);
                MauSac ms = mauSacDAO.getMauSac(maMauSac);

                String maXuatXu = rs.getString(10);
                XuatXu xuatXu = xuatXuDAO.getXuatXu(maXuatXu);

                String maPhanLoai = rs.getString(11);
                PhanLoai phanLoai = phanLoaiDAO.getPhanLoai(maPhanLoai);

                String maNCC = rs.getString(12);
                NhaCungCap nhaCungCap = nhaCungCapDao.getNhaCungCap(maNCC);

                String maKhuyenMai = rs.getString(13);
                KhuyenMai khuyenMai = khuyenMaiDao.getKhuyenMai(maKhuyenMai);

                SanPham sanPham = new SanPham(maSP, tenSP, giaNhap, hinhAnh, soLuong, chatLieu, kieuDang, kichThuoc, ms, xuatXu, phanLoai, nhaCungCap, khuyenMai);
                listSanPham.add(sanPham);
            }

        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSanPham;
    }
    
    public ArrayList<SanPham> getAllSanPhamTon(String maSanPham, String tenSanPham, String loaiSanPham, String mauSac, String kichCo) {

        ChatLieuDAO chatLieuDao = new ChatLieuDAO();
        KieuDangDAO kieuDangDao = new KieuDangDAO();
        KichThuocDAO kichThuocDao = new KichThuocDAO();
        MauSacDAO mauSacDAO = new MauSacDAO();
        XuatXuDAO xuatXuDAO = new XuatXuDAO();
        PhanLoaiDAO phanLoaiDAO = new PhanLoaiDAO();
        NhaCungCapDAO nhaCungCapDao = new NhaCungCapDAO();
        KhuyenMaiDAO khuyenMaiDao = new KhuyenMaiDAO();

        ArrayList<SanPham> listSanPham = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();

        try {
            String sql= "SELECT        sanPham.*\n" +
                        "FROM            KichThuoc INNER JOIN\n" +
                        "                         sanPham ON KichThuoc.maKichThuoc = sanPham.maKichThuoc INNER JOIN\n" +
                        "                         MauSac ON sanPham.maMauSac = MauSac.maMauSac INNER JOIN\n" +
                        "                         PhanLoai ON sanPham.maPhanLoai = PhanLoai.maPhanLoai\n" +
                        "where		sanPham.maSP like ? and kichThuoc.kichThuoc like ? and PhanLoai.loaiSP like ? and MauSac.mauSac like ? and sanPham.tenSP like ?"
                    + " and sanPham.soLuong != 0";
            
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, "%"+maSanPham+"%");
            stmt.setString(2,  "%"+kichCo +"%");
            stmt.setString(3, "%" +loaiSanPham+ "%");
            stmt.setString(4,  "%"+mauSac +"%");
            stmt.setString(5, "%" + tenSanPham +"%");
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maSP = rs.getString(1);
                String tenSP = rs.getString(2);
                long giaNhap = rs.getLong(3);
                String hinhAnh = rs.getString(4);
                int soLuong = rs.getInt(5);

                String maChatLieu = rs.getString(6);
                ChatLieu chatLieu = chatLieuDao.getChatLieu(maChatLieu);

                String maKieuDang = rs.getString(7);
                KieuDang kieuDang = kieuDangDao.getKieuDang(maKieuDang);

                String maKichThuoc = rs.getString(8);
                KichThuoc kichThuoc = kichThuocDao.getKichThuoc(maKichThuoc);

                String maMauSac = rs.getString(9);
                MauSac ms = mauSacDAO.getMauSac(maMauSac);

                String maXuatXu = rs.getString(10);
                XuatXu xuatXu = xuatXuDAO.getXuatXu(maXuatXu);

                String maPhanLoai = rs.getString(11);
                PhanLoai phanLoai = phanLoaiDAO.getPhanLoai(maPhanLoai);

                String maNCC = rs.getString(12);
                NhaCungCap nhaCungCap = nhaCungCapDao.getNhaCungCap(maNCC);

                String maKhuyenMai = rs.getString(13);
                KhuyenMai khuyenMai = khuyenMaiDao.getKhuyenMai(maKhuyenMai);

                SanPham sanPham = new SanPham(maSP, tenSP, giaNhap, hinhAnh, soLuong, chatLieu, kieuDang, kichThuoc, ms, xuatXu, phanLoai, nhaCungCap, khuyenMai);
                listSanPham.add(sanPham);
            }

        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSanPham;
    }
    
    public ArrayList<SanPham> getAllSanPhamHetHang(String maSanPham, String tenSanPham, String loaiSanPham, String mauSac, String kichCo) {

        ChatLieuDAO chatLieuDao = new ChatLieuDAO();
        KieuDangDAO kieuDangDao = new KieuDangDAO();
        KichThuocDAO kichThuocDao = new KichThuocDAO();
        MauSacDAO mauSacDAO = new MauSacDAO();
        XuatXuDAO xuatXuDAO = new XuatXuDAO();
        PhanLoaiDAO phanLoaiDAO = new PhanLoaiDAO();
        NhaCungCapDAO nhaCungCapDao = new NhaCungCapDAO();
        KhuyenMaiDAO khuyenMaiDao = new KhuyenMaiDAO();

        ArrayList<SanPham> listSanPham = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();

        try {
            String sql= "SELECT        sanPham.*\n" +
                        "FROM            KichThuoc INNER JOIN\n" +
                        "                         sanPham ON KichThuoc.maKichThuoc = sanPham.maKichThuoc INNER JOIN\n" +
                        "                         MauSac ON sanPham.maMauSac = MauSac.maMauSac INNER JOIN\n" +
                        "                         PhanLoai ON sanPham.maPhanLoai = PhanLoai.maPhanLoai\n" +
                        "where		sanPham.maSP like ? and kichThuoc.kichThuoc like ? and PhanLoai.loaiSP like ? and MauSac.mauSac like ? and sanPham.tenSP like ?"
                    + " and sanPham.soLuong = 0";
            
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, "%"+maSanPham+"%");
            stmt.setString(2,  "%"+kichCo +"%");
            stmt.setString(3, "%" +loaiSanPham+ "%");
            stmt.setString(4,  "%"+mauSac +"%");
            stmt.setString(5, "%" + tenSanPham +"%");
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maSP = rs.getString(1);
                String tenSP = rs.getString(2);
                long giaNhap = rs.getLong(3);
                String hinhAnh = rs.getString(4);
                int soLuong = rs.getInt(5);

                String maChatLieu = rs.getString(6);
                ChatLieu chatLieu = chatLieuDao.getChatLieu(maChatLieu);

                String maKieuDang = rs.getString(7);
                KieuDang kieuDang = kieuDangDao.getKieuDang(maKieuDang);

                String maKichThuoc = rs.getString(8);
                KichThuoc kichThuoc = kichThuocDao.getKichThuoc(maKichThuoc);

                String maMauSac = rs.getString(9);
                MauSac ms = mauSacDAO.getMauSac(maMauSac);

                String maXuatXu = rs.getString(10);
                XuatXu xuatXu = xuatXuDAO.getXuatXu(maXuatXu);

                String maPhanLoai = rs.getString(11);
                PhanLoai phanLoai = phanLoaiDAO.getPhanLoai(maPhanLoai);

                String maNCC = rs.getString(12);
                NhaCungCap nhaCungCap = nhaCungCapDao.getNhaCungCap(maNCC);

                String maKhuyenMai = rs.getString(13);
                KhuyenMai khuyenMai = khuyenMaiDao.getKhuyenMai(maKhuyenMai);

                SanPham sanPham = new SanPham(maSP, tenSP, giaNhap, hinhAnh, soLuong, chatLieu, kieuDang, kichThuoc, ms, xuatXu, phanLoai, nhaCungCap, khuyenMai);
                listSanPham.add(sanPham);
            }

        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSanPham;
    }
    public int capNhatSoLuong(SanPham sanPham){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();

        try {
            String sql  ="update sanPham set soLuong = ? where maSP = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, sanPham.getSoLuong());
            stmt.setString(2, sanPham.getMaSP());
            
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public SanPham getSanPhamById(String id) {

        ChatLieuDAO chatLieuDao = new ChatLieuDAO();
        KieuDangDAO kieuDangDao = new KieuDangDAO();
        KichThuocDAO kichThuocDao = new KichThuocDAO();
        MauSacDAO mauSacDAO = new MauSacDAO();
        XuatXuDAO xuatXuDAO = new XuatXuDAO();
        PhanLoaiDAO phanLoaiDAO = new PhanLoaiDAO();
        NhaCungCapDAO nhaCungCapDao = new NhaCungCapDAO();
        KhuyenMaiDAO khuyenMaiDao = new KhuyenMaiDAO();

        KetNoiSQL.getInstance();
        Connection con = KetNoiSQL.getConnection();

        try {
            String sql = "select * from sanpham where maSP = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maSP = rs.getString(1);
                String tenSP = rs.getString(2);
                long giaNhap = rs.getLong(3);
                String hinhAnh = rs.getString(4);
                int soLuong = rs.getInt(5);

                String maChatLieu = rs.getString(6);
                ChatLieu chatLieu = chatLieuDao.getChatLieu(maChatLieu);

                String maKieuDang = rs.getString(7);
                KieuDang kieuDang = kieuDangDao.getKieuDang(maKieuDang);

                String maKichThuoc = rs.getString(8);
                KichThuoc kichThuoc = kichThuocDao.getKichThuoc(maKichThuoc);

                String maMauSac = rs.getString(9);
                MauSac ms = mauSacDAO.getMauSac(maMauSac);

                String maXuatXu = rs.getString(10);
                XuatXu xuatXu = xuatXuDAO.getXuatXu(maXuatXu);

                String maPhanLoai = rs.getString(11);
                PhanLoai phanLoai = phanLoaiDAO.getPhanLoai(maPhanLoai);

                String maNCC = rs.getString(12);
                NhaCungCap nhaCungCap = nhaCungCapDao.getNhaCungCap(maNCC);

                String maKhuyenMai = rs.getString(13);
                KhuyenMai khuyenMai = khuyenMaiDao.getKhuyenMai(maKhuyenMai);

                SanPham sanPham = new SanPham(maSP, tenSP, giaNhap, hinhAnh, soLuong, chatLieu, kieuDang, kichThuoc, ms, xuatXu, phanLoai, nhaCungCap, khuyenMai);
                return sanPham;
            }

        } catch (SQLException e) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public int addSanPham(SanPham sanPham) {

        try {
            KetNoiSQL.getInstance();
            Connection con = KetNoiSQL.getConnection();

            String sql = "Insert into sanpham values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, sanPham.getMaSP());
            ps.setString(2, sanPham.getTenSP());
            ps.setLong(3, sanPham.getGiaNhap());
            ps.setString(4, sanPham.getHinhAnh());
            ps.setInt(5, sanPham.getSoLuong());
            ps.setString(6, sanPham.getChatLieu().getMaChatLieu());
            ps.setString(7, sanPham.getKieuDang().getMaKieuDang());
            ps.setString(8, sanPham.getKichThuoc().getMaKichThuoc());
            ps.setString(9, sanPham.getMauSac().getMaMauSac());
            ps.setString(10, sanPham.getXuatXu().getMaXuatXu());
            ps.setString(11, sanPham.getPhanLoai().getMaPhanLoai());
            ps.setString(12, sanPham.getNhaCungCap().getMaNhaCungCap());
            ps.setString(13, sanPham.getKhuyenMai().getMaKhuyenMai());

            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int updateSanPham(SanPham sanPham) {
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();

        try {
            String sql = "update sanpham set tenSP = ?, giaNhap = ?,hinhAnh = ?,soLuong = ?,maChatLieu = ?,maKieuDang= ?, maKichThuoc = ?, maMauSac = ?, maXuatXu = ?, maPhanLoai = ?, maNCC = ?, maKhuyenMai = ? where maSP = ?";

            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, sanPham.getTenSP());
            stmt.setLong(2, sanPham.getGiaNhap());
            stmt.setString(3, sanPham.getHinhAnh());
            stmt.setInt(4, sanPham.getSoLuong());
            stmt.setString(5, sanPham.getChatLieu().getMaChatLieu());
            stmt.setString(6, sanPham.getKieuDang().getMaKieuDang());
            stmt.setString(7, sanPham.getKichThuoc().getMaKichThuoc());
            stmt.setString(8, sanPham.getMauSac().getMaMauSac());
            stmt.setString(9, sanPham.getXuatXu().getMaXuatXu());
            stmt.setString(10, sanPham.getPhanLoai().getMaPhanLoai());
            stmt.setString(11, sanPham.getNhaCungCap().getMaNhaCungCap());
            stmt.setString(12, sanPham.getKhuyenMai().getMaKhuyenMai());
            stmt.setString(13, sanPham.getMaSP());

            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<SanPham> getAllSanPhamByName(String name) {

        ArrayList<SanPham> listSanPham = new ArrayList<>();

        ChatLieuDAO chatLieuDao = new ChatLieuDAO();
        KieuDangDAO kieuDangDao = new KieuDangDAO();
        KichThuocDAO kichThuocDao = new KichThuocDAO();
        MauSacDAO mauSacDAO = new MauSacDAO();
        XuatXuDAO xuatXuDAO = new XuatXuDAO();
        PhanLoaiDAO phanLoaiDAO = new PhanLoaiDAO();
        NhaCungCapDAO nhaCungCapDao = new NhaCungCapDAO();
        KhuyenMaiDAO khuyenMaiDao = new KhuyenMaiDAO();

        KetNoiSQL.getInstance();
        Connection con = KetNoiSQL.getConnection();

        try {
            String sql = "select * from sanpham where tenSP = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maSP = rs.getString(1);
                String tenSP = rs.getString(2);
                long giaNhap = rs.getLong(3);
                String hinhAnh = rs.getString(4);
                int soLuong = rs.getInt(5);

                String maChatLieu = rs.getString(6);
                ChatLieu chatLieu = chatLieuDao.getChatLieu(maChatLieu);

                String maKieuDang = rs.getString(7);
                KieuDang kieuDang = kieuDangDao.getKieuDang(maKieuDang);

                String maKichThuoc = rs.getString(8);
                KichThuoc kichThuoc = kichThuocDao.getKichThuoc(maKichThuoc);

                String maMauSac = rs.getString(9);
                MauSac ms = mauSacDAO.getMauSac(maMauSac);

                String maXuatXu = rs.getString(10);
                XuatXu xuatXu = xuatXuDAO.getXuatXu(maXuatXu);

                String maPhanLoai = rs.getString(11);
                PhanLoai phanLoai = phanLoaiDAO.getPhanLoai(maPhanLoai);

                String maNCC = rs.getString(12);
                NhaCungCap nhaCungCap = nhaCungCapDao.getNhaCungCap(maNCC);

                String maKhuyenMai = rs.getString(13);
                KhuyenMai khuyenMai = khuyenMaiDao.getKhuyenMai(maKhuyenMai);

                SanPham sanPham = new SanPham(maSP, tenSP, giaNhap, hinhAnh, soLuong, chatLieu, kieuDang, kichThuoc, ms, xuatXu, phanLoai, nhaCungCap, khuyenMai);
                listSanPham.add(sanPham);
            }

        } catch (SQLException e) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return listSanPham;
    }
     public ArrayList<SanPham> getAllSanPhamByPhanLoaiId(String id) {

        ArrayList<SanPham> listSanPham = new ArrayList<>();

        ChatLieuDAO chatLieuDao = new ChatLieuDAO();
        KieuDangDAO kieuDangDao = new KieuDangDAO();
        KichThuocDAO kichThuocDao = new KichThuocDAO();
        MauSacDAO mauSacDAO = new MauSacDAO();
        XuatXuDAO xuatXuDAO = new XuatXuDAO();
        PhanLoaiDAO phanLoaiDAO = new PhanLoaiDAO();
        NhaCungCapDAO nhaCungCapDao = new NhaCungCapDAO();
        KhuyenMaiDAO khuyenMaiDao = new KhuyenMaiDAO();

        KetNoiSQL.getInstance();
        Connection con = KetNoiSQL.getConnection();

        try {
            String sql = "select * from sanpham where maPhanLoai = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maSP = rs.getString(1);
                String tenSP = rs.getString(2);
                long giaNhap = rs.getLong(3);
                String hinhAnh = rs.getString(4);
                int soLuong = rs.getInt(5);

                String maChatLieu = rs.getString(6);
                ChatLieu chatLieu = chatLieuDao.getChatLieu(maChatLieu);

                String maKieuDang = rs.getString(7);
                KieuDang kieuDang = kieuDangDao.getKieuDang(maKieuDang);

                String maKichThuoc = rs.getString(8);
                KichThuoc kichThuoc = kichThuocDao.getKichThuoc(maKichThuoc);

                String maMauSac = rs.getString(9);
                MauSac ms = mauSacDAO.getMauSac(maMauSac);

                String maXuatXu = rs.getString(10);
                XuatXu xuatXu = xuatXuDAO.getXuatXu(maXuatXu);

                String maPhanLoai = rs.getString(11);
                PhanLoai phanLoai = phanLoaiDAO.getPhanLoai(maPhanLoai);

                String maNCC = rs.getString(12);
                NhaCungCap nhaCungCap = nhaCungCapDao.getNhaCungCap(maNCC);

                String maKhuyenMai = rs.getString(13);
                KhuyenMai khuyenMai = khuyenMaiDao.getKhuyenMai(maKhuyenMai);

                SanPham sanPham = new SanPham(maSP, tenSP, giaNhap, hinhAnh, soLuong, chatLieu, kieuDang, kichThuoc, ms, xuatXu, phanLoai, nhaCungCap, khuyenMai);
                listSanPham.add(sanPham);
            }

        } catch (SQLException e) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return listSanPham;
    }
    public ArrayList<SanPham>topNSanPham(){
        ArrayList<SanPham>listSanPham = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        SanPhamDAO sp_DAO = new SanPhamDAO();
        String sql = "SELECT TOP 10     sanPham.maSP, sum(ChiTietHoaDon.soLuong) as tongSoLuong \n" +
                        "FROM        ChiTietHoaDon INNER JOIN\n" +
                        "                  HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\n" +
                        "                  sanPham ON ChiTietHoaDon.maSP = sanPham.maSP\n" +
                        "\n" +
                        "group by sanPham.maSP, sanPham.tenSP\n" +
                        "order by tongSoLuong desc";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                SanPham sp = sp_DAO.getSanPhamById(rs.getString(1));
                sp.setSoLuong(rs.getInt(2));
                listSanPham.add(sp);
                
            }
            return listSanPham;
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<SanPham>topNSanPhamBanCham(){
        ArrayList<SanPham>listSanPham = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        SanPhamDAO sp_DAO = new SanPhamDAO();
        String sql = "SELECT TOP 10     sanPham.maSP, sum(ChiTietHoaDon.soLuong) as tongSoLuong \n" +
                        "FROM        ChiTietHoaDon INNER JOIN\n" +
                        "                  HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\n" +
                        "                  sanPham ON ChiTietHoaDon.maSP = sanPham.maSP\n" +
                        "\n" +
                        "group by sanPham.maSP, sanPham.tenSP\n" +
                        "order by tongSoLuong asc";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                SanPham sp = sp_DAO.getSanPhamById(rs.getString(1));
                sp.setSoLuong(rs.getInt(2));
                listSanPham.add(sp);
                
            }
            return listSanPham;
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int xoaSanPham(String maSP) {
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();

        try {
            String sql = "DELETE FROM sanPham WHERE maSP = ?";

            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, maSP);

            return stmt.executeUpdate();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return -1;
    }

}
