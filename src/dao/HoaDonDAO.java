package dao;

import ConnectDB.KetNoiSQL;

import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HoaDonDAO {

    private NhanVienDAO nhanVien_DAO = new NhanVienDAO();
    private KhachHangDAO khachHang_DAO = new KhachHangDAO();

    public HoaDonDAO() {
    }

    public ArrayList<HoaDon> getAllHoaDon() {
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql = "Select * from HoaDon where maHoaDon not like 'HDC%'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maHoaDon = rs.getString(1);
                Date ngayLap = rs.getDate(2);
                NhanVien nhanVien = nhanVien_DAO.getNhanVienByID(rs.getString(3));
                KhachHang khachHang = khachHang_DAO.getKhachHangById(rs.getString(4));
                HoaDon hoaDon = new HoaDon(maHoaDon, khachHang, nhanVien, ngayLap);
                listHoaDon.add(hoaDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MauSacDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listHoaDon;
    }

    public ArrayList<HoaDon> getAllHoaDon(String tenKhachHang, String tenNhanVien) {
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql = "SELECT         HoaDon.*\n"
                    + "FROM            HoaDon INNER JOIN\n"
                    + "                         khachHang ON HoaDon.maKhachHang = khachHang.maKhachHang INNER JOIN\n"
                    + "                         nhanvien ON HoaDon.maNhanVien = nhanvien.maNhanVien\n"
                    + "where nhanvien.hoVaTen like ? and khachHang.hoVaTen like ? and maHoaDon not like 'HDC%'";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, "%" + tenNhanVien + "%");
            stmt.setString(2, "%" + tenKhachHang + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maHoaDon = rs.getString(1);
                Date ngayLap = rs.getDate(2);
                NhanVien nhanVien = nhanVien_DAO.getNhanVienByID(rs.getString(3));
                KhachHang khachHang = khachHang_DAO.getKhachHangById(rs.getString(4));
                HoaDon hoaDon = new HoaDon(maHoaDon, khachHang, nhanVien, ngayLap);
                listHoaDon.add(hoaDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MauSacDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listHoaDon;
    }

    public ArrayList<HoaDon> getAllHoaDon(String tenKhachHang, String tenNhanVien, String tuNgay, String denNgay) {
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql = "SELECT         HoaDon.*\n"
                    + "FROM            HoaDon INNER JOIN\n"
                    + "                         khachHang ON HoaDon.maKhachHang = khachHang.maKhachHang INNER JOIN\n"
                    + "                         nhanvien ON HoaDon.maNhanVien = nhanvien.maNhanVien\n"
                    + "where nhanvien.hoVaTen like ? and khachHang.hoVaTen like ? and maHoaDon not like 'HDC%' and ngaylap >= ? and ngaylap <= ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, "%" + tenNhanVien + "%");
            stmt.setString(2, "%" + tenKhachHang + "%");
            stmt.setString(3, tuNgay);
            stmt.setString(4, denNgay);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maHoaDon = rs.getString(1);
                Date ngayLap = rs.getDate(2);
                NhanVien nhanVien = nhanVien_DAO.getNhanVienByID(rs.getString(3));
                KhachHang khachHang = khachHang_DAO.getKhachHangById(rs.getString(4));
                HoaDon hoaDon = new HoaDon(maHoaDon, khachHang, nhanVien, ngayLap);
                listHoaDon.add(hoaDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MauSacDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listHoaDon;
    }

    public ArrayList<HoaDon> getAllHoaDon(String maId) {
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql = "Select * from HoaDon where maHoaDon like ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, "%" + maId + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maHoaDon = rs.getString(1);
                Date ngayLap = rs.getDate(2);
                NhanVien nhanVien = nhanVien_DAO.getNhanVienByID(rs.getString(3));
                KhachHang khachHang = khachHang_DAO.getKhachHangById(rs.getString(4));
                HoaDon hoaDon = new HoaDon(maHoaDon, khachHang, nhanVien, ngayLap);
                listHoaDon.add(hoaDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MauSacDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listHoaDon;
    }

    public ArrayList<HoaDon> getAllHoaDonCho() {
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql = "Select * from HoaDon where maHoaDon like 'HDC%'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maHoaDon = rs.getString(1);
                Date ngayLap = rs.getDate(2);
                NhanVien nhanVien = nhanVien_DAO.getNhanVienByID(rs.getString(3));
                KhachHang khachHang = khachHang_DAO.getKhachHangById(rs.getString(4));
                HoaDon hoaDon = new HoaDon(maHoaDon, khachHang, nhanVien, ngayLap);
                listHoaDon.add(hoaDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MauSacDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listHoaDon;
    }

    public ArrayList<HoaDon> getHoaDonChoBySDT(String sdt) {
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql = "SELECT        HoaDon.*\n"
                    + "FROM            HoaDon INNER JOIN\n"
                    + "                         khachHang ON HoaDon.maKhachHang = khachHang.maKhachHang\n"
                    + "where khachHang.sdt like ? and maHoaDon like 'HDC%'";

            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, "%" + sdt + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maHoaDon = rs.getString(1);
                Date ngayLap = rs.getDate(2);
                NhanVien nhanVien = nhanVien_DAO.getNhanVienByID(rs.getString(3));
                KhachHang khachHang = khachHang_DAO.getKhachHangById(rs.getString(4));
                HoaDon hoaDon = new HoaDon(maHoaDon, khachHang, nhanVien, ngayLap);
                listHoaDon.add(hoaDon);
            }
        } catch (Exception e) {
        }
        return listHoaDon;
    }

    public ArrayList<HoaDon> getHoaDonByDate(Date ngayBatDau, Date ngayKetThuc) {
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();

        try {
            String sql = "Select * from HoaDon where ngaylap between ? and ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateBatDau = sdf.format(ngayBatDau);
            String dateNgayKetThuc = sdf.format(ngayKetThuc);

            stmt.setString(1, dateBatDau);
            stmt.setString(2, dateNgayKetThuc);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String maHoaDon = rs.getString(1);
                Date ngayLap = rs.getDate(2);
                NhanVien nhanVien = nhanVien_DAO.getNhanVienByID(rs.getString(3));
                KhachHang khachHang = khachHang_DAO.getKhachHangById(rs.getString(4));
                HoaDon hoaDon = new HoaDon(maHoaDon, khachHang, nhanVien, ngayLap);
                System.out.println(hoaDon);
                listHoaDon.add(hoaDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MauSacDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listHoaDon;
    }

    public HoaDon getHoaDonById(String id) {
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();

        try {
            String sql = "select * from HoaDon where maHoaDon = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(rs.getString(1));
                hoaDon.setNgayLap(rs.getDate(2));

                NhanVien nhanVien = nhanVien_DAO.getNhanVienByID(rs.getString(3));
                hoaDon.setNhanVien(nhanVien);

                KhachHang khachHang = khachHang_DAO.getKhachHangById(rs.getString(4));
                hoaDon.setKhachHang(khachHang);

                return hoaDon;
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<HoaDon> getHoaDonByMaNV(String id) {
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        ArrayList<HoaDon> listHoaDon = new ArrayList<HoaDon>();
        try {
            String sql = "select * from HoaDon where maNhanVien = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(rs.getString(1));
                hoaDon.setNgayLap(rs.getDate(2));

                NhanVien nhanVien = nhanVien_DAO.getNhanVienByID(rs.getString(3));
                hoaDon.setNhanVien(nhanVien);

                KhachHang khachHang = khachHang_DAO.getKhachHangById(rs.getString(4));
                hoaDon.setKhachHang(khachHang);

                listHoaDon.add(hoaDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listHoaDon;
    }

    public ArrayList<HoaDon> getHoaDonByMaKH(String id) {
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        ArrayList<HoaDon> listHoaDon = new ArrayList<HoaDon>();
        try {
            String sql = "select * from HoaDon where maKhachHang = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(rs.getString(1));
                hoaDon.setNgayLap(rs.getDate(2));

                NhanVien nhanVien = nhanVien_DAO.getNhanVienByID(rs.getString(3));
                hoaDon.setNhanVien(nhanVien);

                KhachHang khachHang = khachHang_DAO.getKhachHangById(rs.getString(4));
                hoaDon.setKhachHang(khachHang);

                listHoaDon.add(hoaDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listHoaDon;
    }

    public int deleteHoaDon(HoaDon hoaDon) {
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql = "delete from HoaDon where maHoaDon = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, hoaDon.getMaHoaDon());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int addHoaDon(HoaDon hoaDon) {
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql = "insert into HoaDon(maHoaDon, ngaylap, maNhanVien, maKhachHang) values (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, hoaDon.getMaHoaDon());
            stmt.setDate(2, hoaDon.getNgayLap());
            stmt.setString(3, hoaDon.getNhanVien().getMaNhanVien());
            stmt.setString(4, hoaDon.getKhachHang().getMaKhachHang());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int tongTienHoaDon(String maHoaDon) {
        int tongTien = 0;
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql = "SELECT        sum(ChiTietHoaDon.thanhTien) as tongTien\n"
                    + "FROM            ChiTietHoaDon INNER JOIN\n"
                    + "                         HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon\n"
                    + "where			HoaDon.maHoaDon = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, maHoaDon);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tongTien = rs.getInt(1);
                return tongTien;
            }
        } catch (SQLException e) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return -1;
    }

    public int tongTienSanPhamThuocHoaDon(String maHoaDon, String maSP) {
        int tongTien = 0;
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql = "SELECT        ChiTietHoaDon.thanhTien\n"
                    + "FROM            ChiTietHoaDon INNER JOIN\n"
                    + "                         HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon\n"
                    + "where			HoaDon.maHoaDon = ? and ChiTietHoaDon.maSP = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, maHoaDon);
            stmt.setString(2, maSP);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tongTien = rs.getInt(1);
                return tongTien;
            }
        } catch (SQLException e) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return -1;
    }

    public ArrayList<Integer> getListYear() {
        ArrayList<Integer> listYear = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();

        try {
            String sql = "select Year(HoaDon.ngaylap) as YearNumber from HoaDon \n"
                    + "group by Year(HoaDon.ngaylap)";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                listYear.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listYear;

    }

    public String getLatestHoaDon() {

        String latestID = new String();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();

        try {
            String sql = "SELECT top 1 maHoaDon \n"
                    + "from HoaDon\n"
                    + "where HoaDon.ngaylap = (select top 1 HoaDon.ngaylap\n"
                    + "					from HoaDon\n"
                    + "					order by HoaDon.ngaylap desc)\n"
                    + "order by maHoaDon desc";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            latestID = rs.getNString(1);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return latestID;
    }
}
