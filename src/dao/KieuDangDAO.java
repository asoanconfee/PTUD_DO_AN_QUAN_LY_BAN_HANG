package dao;

import ConnectDB.KetNoiSQL;

import entity.KieuDang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KieuDangDAO {

    public KieuDangDAO() {
    }
    
    public ArrayList<KieuDang> getAllKieuDang(){
        ArrayList<KieuDang>listKieuDang = new ArrayList<>();
        KetNoiSQL.getInstance();
            Connection conn = KetNoiSQL.getConnection(); 
        try {
            String sql = "Select * from KieuDang";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maKieuDang = rs.getString(1);
                String kieuDang =  rs.getString(2);
                KieuDang kd = new KieuDang(maKieuDang, kieuDang);
                listKieuDang.add(kd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MauSacDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listKieuDang;
    }
    
    public KieuDang getKieuDang(String id){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "select * from KieuDang where maKieuDang = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                KieuDang kieuDang = new KieuDang();
                kieuDang.setMaKieuDang(rs.getString(1));
                kieuDang.setKieuDang(rs.getString(2));
                return kieuDang;
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int updateKieuDang(KieuDang kieuDang){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "update KieuDang set kieuDang = (?) where maKieuDang = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, kieuDang.getKieuDang());
            stmt.setString(2, kieuDang.getMaKieuDang());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int addKieuDang(KieuDang kieuDang){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();    
        try {
            String sql = "insert into KieuDang(maKieuDang, kieuDang) values (?, ?)";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, kieuDang.getMaKieuDang());
            stmt.setString(2, kieuDang.getKieuDang());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    public KieuDang getKieuDangByName(String name){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "select * from KieuDang where kieuDang = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                KieuDang kieuDang = new KieuDang();
                kieuDang.setMaKieuDang(rs.getString(1));
                kieuDang.setKieuDang(rs.getString(2));
                return kieuDang;
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
