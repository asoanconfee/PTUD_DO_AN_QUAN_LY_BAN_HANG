package dao;

import ConnectDB.KetNoiSQL;
import entity.MauSac;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MauSacDAO {

    public MauSacDAO() {
    }
    
    public ArrayList<MauSac> getAllMauSac(){
        ArrayList<MauSac>listMauSac = new ArrayList<>();
        KetNoiSQL.getInstance();
            Connection conn = KetNoiSQL.getConnection(); 
        try {
            String sql = "Select * from MauSac";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maMauSac = rs.getString(1);
                String mauSac=  rs.getString(2);
                MauSac ms = new MauSac(maMauSac, mauSac);
                listMauSac.add(ms);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MauSacDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listMauSac;
    }
    
    public MauSac getMauSac(String id){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "select * from MauSac where maMauSac = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                MauSac mauSac = new MauSac();
                mauSac.setMaMauSac(rs.getString(1));
                mauSac.setMauSac(rs.getString(2));
                return mauSac;
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int updateMauSac(MauSac mauSac){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "update MauSac set mauSac = (?) where maMauSac = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, mauSac.getMauSac());
            stmt.setString(2, mauSac.getMaMauSac());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int addMauSac(MauSac mauSac){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();    
        try {
            String sql = "insert into MauSac(maMauSac, mauSac) values (?, ?)";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, mauSac.getMaMauSac());
            stmt.setString(2, mauSac.getMauSac());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    } 
    public MauSac getMauSacByName(String name){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "select * from MauSac where mauSac = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                MauSac mauSac = new MauSac();
                mauSac.setMaMauSac(rs.getString(1));
                mauSac.setMauSac(rs.getString(2));
                return mauSac;
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
