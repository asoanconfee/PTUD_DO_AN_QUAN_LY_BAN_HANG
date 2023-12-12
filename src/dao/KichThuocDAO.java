package dao;

import ConnectDB.KetNoiSQL;
import entity.KichThuoc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KichThuocDAO {

    public KichThuocDAO() {
    }
    
    public ArrayList<KichThuoc> getAllKichThuoc(){
        ArrayList<KichThuoc>listKichThuoc = new ArrayList<>();
        KetNoiSQL.getInstance();
            Connection conn = KetNoiSQL.getConnection(); 
        try {
            String sql = "Select * from KichThuoc";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maKichThuoc = rs.getString(1);
                String kichThuoc =  rs.getString(2);
                KichThuoc kt = new KichThuoc(maKichThuoc, kichThuoc);
                listKichThuoc.add(kt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MauSacDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listKichThuoc;
    }
    
    public KichThuoc getKichThuoc(String id){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "select * from KichThuoc where maKichThuoc = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                KichThuoc kichThuoc = new KichThuoc();
                kichThuoc.setMaKichThuoc(rs.getString(1));
                kichThuoc.setKichThuoc(rs.getString(2));
                return kichThuoc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int updateKichThuoc(KichThuoc kichThuoc){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "update KichThuoc set kichThuoc = (?) where maKichThuoc = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, kichThuoc.getKichThuoc());
            stmt.setString(2, kichThuoc.getMaKichThuoc());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int addKichThuoc(KichThuoc kichThuoc){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();    
        try {
            String sql = "insert into KichThuoc(maKichThuoc, kichThuoc) values (?, ?)";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, kichThuoc.getMaKichThuoc());
            stmt.setString(2, kichThuoc.getKichThuoc());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
     public KichThuoc getKichThuocByName(String name){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "select * from KichThuoc where kichThuoc = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                KichThuoc kichThuoc = new KichThuoc();
                kichThuoc.setMaKichThuoc(rs.getString(1));
                kichThuoc.setKichThuoc(rs.getString(2));
                return kichThuoc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
