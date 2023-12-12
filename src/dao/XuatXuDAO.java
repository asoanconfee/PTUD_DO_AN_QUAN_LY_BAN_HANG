package dao;

import ConnectDB.KetNoiSQL;
import entity.XuatXu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XuatXuDAO {
    
    public XuatXuDAO() {
    }
    
    public ArrayList<XuatXu>getAllXuatXu(){
        ArrayList<XuatXu>listXuatXu = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "select * from XuatXu";
            Statement stmt= conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                XuatXu xuatXu = new XuatXu();
                xuatXu.setMaXuatXu(rs.getString(1));
                xuatXu.setNoiXuatXu(rs.getString(2));
                listXuatXu.add(xuatXu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listXuatXu;
    }
    
    public XuatXu getXuatXu(String id){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
        String sql = "select * from XuatXu where maXuatXu = ?";
        PreparedStatement stmt = conn.prepareCall(sql);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            XuatXu xuatXu = new XuatXu();
            xuatXu.setMaXuatXu(rs.getString(1));
            xuatXu.setNoiXuatXu(rs.getString(2));
            return xuatXu;
        }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int updateXuatXu(XuatXu xuatXu){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "update XuatXu set noiXuatXu = (?) where maXuatXu = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, xuatXu.getNoiXuatXu());
            stmt.setString(2, xuatXu.getMaXuatXu());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int addXuatXu(XuatXu xuatXu){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();    
        try {
            String sql = "insert into XuatXu(maXuatXu, noiXuatXu) values (?, ?)";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, xuatXu.getMaXuatXu());
            stmt.setString(2, xuatXu.getNoiXuatXu());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
        public XuatXu getXuatXuByName(String name){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
        String sql = "select * from XuatXu where noiXuatXu = ?";
        PreparedStatement stmt = conn.prepareCall(sql);
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            XuatXu xuatXu = new XuatXu();
            xuatXu.setMaXuatXu(rs.getString(1));
            xuatXu.setNoiXuatXu(rs.getString(2));
            return xuatXu;
        }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
