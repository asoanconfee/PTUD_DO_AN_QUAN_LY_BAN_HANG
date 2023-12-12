package dao;

import ConnectDB.KetNoiSQL;
import entity.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhachHangDAO {

    public KhachHangDAO() {
    }
    
    public ArrayList<KhachHang> getAllKhachHang(){
        ArrayList<KhachHang>listKhachHang = new ArrayList<>();
        KetNoiSQL.getInstance();
            Connection conn = KetNoiSQL.getConnection(); 
        try {
            String sql = "Select * from khachHang";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maKhachHang = rs.getString(1);
                String hoVaTen =  rs.getString(2);
                String email = rs.getString(3);
                String sdt = rs.getString(4);
                boolean gioiTinh = rs.getBoolean(5);
                KhachHang kh = new KhachHang(maKhachHang, hoVaTen, email, sdt, gioiTinh);
                listKhachHang.add(kh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MauSacDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listKhachHang;
    }
    
    public ArrayList<KhachHang> getKhachHangByName(String tenKhachHang){
       
        ArrayList<KhachHang>listKhachHang = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "select * from khachHang where hoVaTen like ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, "%" + tenKhachHang + "%");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKhachHang(rs.getString(1));
                khachHang.setHoVaTen(rs.getString(2));
                khachHang.setEmail(rs.getString(3));
                khachHang.setSdt(rs.getString(4));
                khachHang.setGioiTinh(rs.getBoolean(5));
                listKhachHang.add(khachHang);
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listKhachHang;
    }
    
    public KhachHang getKhachHangById(String id){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "select * from khachHang where maKhachHang = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKhachHang(rs.getString(1));
                khachHang.setHoVaTen(rs.getString(2));
                khachHang.setEmail(rs.getString(3));
                khachHang.setSdt(rs.getString(4));
                khachHang.setGioiTinh(rs.getBoolean(5));
                return khachHang;
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public KhachHang getKhachHangBySdt(String sdt){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "select * from khachHang where sdt = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, sdt);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKhachHang(rs.getString(1));
                khachHang.setHoVaTen(rs.getString(2));
                khachHang.setEmail(rs.getString(3));
                khachHang.setSdt(rs.getString(4));
                khachHang.setGioiTinh(rs.getBoolean(5));
                return khachHang;
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int updateKhachHang(KhachHang khachHang){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "update khachHang set hoVaTen = ?,"
                    + "                         email = ?,"
                    + "                         sdt = ?,"
                    + "                         gioiTinh = ?"
                    + " where maKhachHang = ?";
            
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, khachHang.getHoVaTen());
            stmt.setString(2, khachHang.getEmail());
            stmt.setString(3, khachHang.getSdt());
            stmt.setBoolean(4, khachHang.getGioiTinh());
            stmt.setString(5, khachHang.getMaKhachHang());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int addKhachHang(KhachHang khachHang){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();    
        try {
            String sql = "insert into khachHang(maKhachHang, hoVaTen, email, sdt, gioiTinh) values (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, khachHang.getMaKhachHang());
            stmt.setString(2, khachHang.getHoVaTen());
            stmt.setString(3, khachHang.getEmail());
            stmt.setString(4, khachHang.getSdt());
            stmt.setBoolean(5, khachHang.getGioiTinh());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int xoaKhachHang(String maKhachHang){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
           
            String sql = "update khachHang where maKhachHang = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, maKhachHang); 
            

            
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;

    }
    
}


