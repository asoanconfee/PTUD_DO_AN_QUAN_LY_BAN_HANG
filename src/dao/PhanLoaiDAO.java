package dao;

import ConnectDB.KetNoiSQL;
import entity.PhanLoai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhanLoaiDAO {

    public PhanLoaiDAO() {
    }
    
    public ArrayList<PhanLoai> getAllPhanLoai(){
        ArrayList<PhanLoai>listPhanLoai = new ArrayList<>();
        KetNoiSQL.getInstance();
            Connection conn = KetNoiSQL.getConnection(); 
        try {
            String sql = "Select * from PhanLoai";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maPhanLoai = rs.getString(1);
                String phanLoaiSanPham =  rs.getString(2);
                PhanLoai pl = new PhanLoai(maPhanLoai, phanLoaiSanPham);
                listPhanLoai.add(pl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MauSacDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPhanLoai;
    }
    
    public PhanLoai getPhanLoai(String id){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "select * from PhanLoai where maPhanLoai = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                PhanLoai phanLoai = new PhanLoai();
                phanLoai.setMaPhanLoai(rs.getString(1));
                phanLoai.setLoaiSanPham(rs.getString(2));
                return phanLoai;
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int updatePhanLoai(PhanLoai phanLoai){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "update PhanLoai set loaiSP = (?) where maPhanLoai = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, phanLoai.getLoaiSanPham());
            stmt.setString(2, phanLoai.getMaPhanLoai());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int addPhanLoai(PhanLoai phanLoai){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();    
        try {
            String sql = "insert into PhanLoai(maPhanLoai, loaiSP) values (?, ?)";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, phanLoai.getMaPhanLoai());
            stmt.setString(2, phanLoai.getLoaiSanPham());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
     
    public PhanLoai getPhanLoaiByName(String name){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "select * from PhanLoai where loaiSP = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                PhanLoai phanLoai = new PhanLoai();
                phanLoai.setMaPhanLoai(rs.getString(1));
                phanLoai.setLoaiSanPham(rs.getString(2));
                return phanLoai;
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
