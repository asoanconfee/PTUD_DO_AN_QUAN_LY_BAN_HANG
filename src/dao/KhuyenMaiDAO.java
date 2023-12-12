package dao;

import ConnectDB.KetNoiSQL;
import entity.KhuyenMai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhuyenMaiDAO {

    public ArrayList<KhuyenMai> getAllKhuyenMai() {
        ArrayList<KhuyenMai> listKhuyenMai = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();

        try {
            String sql = "select * from khuyenmai";
            PreparedStatement stmt = conn.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                KhuyenMai khuyenMai = new KhuyenMai();
                khuyenMai.setMaKhuyenMai(rs.getString(1));
                khuyenMai.setPhanTramKhuyenMai(rs.getInt(2));
                khuyenMai.setNgayBatDau(rs.getDate(3));
                khuyenMai.setSoLuong(rs.getInt(4));

                listKhuyenMai.add(khuyenMai);
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listKhuyenMai;
    }

    public KhuyenMai getKhuyenMai(String id) {
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();

        try {
            String sql = "select * from khuyenmai where maKhuyenMai = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                KhuyenMai khuyenMai = new KhuyenMai();
                khuyenMai.setMaKhuyenMai(rs.getString(1));
                khuyenMai.setPhanTramKhuyenMai(rs.getInt(2));
                khuyenMai.setNgayBatDau(rs.getDate(3));
                khuyenMai.setSoLuong(rs.getInt(4));

                return khuyenMai;
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int addKhuyenMai(KhuyenMai khuyenMai) {
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql = "insert into khuyenMai values (?,?,?,?)";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, khuyenMai.getMaKhuyenMai());
            stmt.setInt(2, khuyenMai.getPhanTramKhuyenMai());
            long date = khuyenMai.getNgayBatDau().getTime();

            stmt.setDate(3, new Date(date));

            stmt.setInt(4, khuyenMai.getSoLuong());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public KhuyenMai getKhuyenMaiByPhanTram(int phanTram) {
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();

        try {
            String sql = "select * from khuyenmai where phanTramKhuyenMai = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, phanTram);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                KhuyenMai khuyenMai = new KhuyenMai();
                khuyenMai.setMaKhuyenMai(rs.getString(1));
                khuyenMai.setPhanTramKhuyenMai(rs.getInt(2));
                khuyenMai.setNgayBatDau(rs.getDate(3));
                khuyenMai.setSoLuong(rs.getInt(4));

                return khuyenMai;
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public int updateKhuyenMai(KhuyenMai khuyenMai){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "update khuyenMai set phanTramKhuyenMai = ?,ngayBatDau = ?,soLuong = ? where maKhuyenMai = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, khuyenMai.getPhanTramKhuyenMai());
            
            long date = khuyenMai.getNgayBatDau().getTime();

            stmt.setDate(2, new Date(date));
            
            stmt.setInt(3, khuyenMai.getSoLuong());
            stmt.setString(4, khuyenMai.getMaKhuyenMai());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
