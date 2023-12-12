package dao;

import entity.TaiKhoan;
import entity.NhanVien;

import java.sql.Connection;
import ConnectDB.KetNoiSQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TaiKhoanDAO {
    private NhanVienDAO nhanVien_DAO = new NhanVienDAO();

    public TaiKhoanDAO() {
    }
    
    
    public TaiKhoan getTaiKhoanByName(String name){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql="SELECT * FROM TaiKhoan WHERE tenTaiKhoan=?";
            PreparedStatement stmt=conn.prepareCall(sql);
            stmt.setString(1, name);
            ResultSet rs=stmt.executeQuery();
            while (rs.next()) {
                String tenTaiKhoan= rs.getString(1);
                String matKhau= rs.getString(2);
                boolean loaiTaiKhoan=rs.getBoolean(3);
                NhanVien nhanVien = nhanVien_DAO.getNhanVienByID(rs.getString(4));
                 TaiKhoan taiKhoan= new TaiKhoan(tenTaiKhoan, matKhau, loaiTaiKhoan, nhanVien);
                 return taiKhoan;
            }
        } catch (SQLException e) {
              Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
   
    return null;
    }
    
    public TaiKhoan getTaiKhoanByMNV(String maNV){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql="SELECT * FROM TaiKhoan WHERE maNhanVien=?";
            PreparedStatement stmt=conn.prepareCall(sql);
            stmt.setString(1, maNV);
            ResultSet rs=stmt.executeQuery();
            while (rs.next()) {
                String tenTaiKhoan= rs.getString(1);
                String matKhau= rs.getString(2);
                boolean loaiTaiKhoan=rs.getBoolean(3);
                NhanVien nhanVien = nhanVien_DAO.getNhanVienByID(rs.getString(4));
                 TaiKhoan taiKhoan= new TaiKhoan(tenTaiKhoan, matKhau, loaiTaiKhoan, nhanVien);
                 return taiKhoan;
            }
        } catch (SQLException e) {
              Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
   
    return null;
    }
    
    public TaiKhoan getTaiKhoanByTK(String tk){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql="SELECT * FROM TaiKhoan WHERE tenTaiKhoan=?";
            PreparedStatement stmt=conn.prepareCall(sql);
            stmt.setString(1, tk);
            ResultSet rs=stmt.executeQuery();
            while (rs.next()) {
                String tenTaiKhoan= rs.getString(1);
                String matKhau= rs.getString(2);
                boolean loaiTaiKhoan=rs.getBoolean(3);
                NhanVien nhanVien = nhanVien_DAO.getNhanVienByID(rs.getString(4));
                 TaiKhoan taiKhoan= new TaiKhoan(tenTaiKhoan, matKhau, loaiTaiKhoan, nhanVien);
                 return taiKhoan;
            }
        } catch (SQLException e) {
              Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
   
    return null;
    }
    public ArrayList<TaiKhoan> getAllTaiKhoan(){
      
        ArrayList<TaiKhoan> listTaiKhoan=new ArrayList<>();
         KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
           
        try {
            String sql = "Select * from TaiKhoan";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String tenTaiKhoan =rs.getString(1);
                String matKhau=rs.getString(2);
                Boolean loaiTaiKhoan=rs.getBoolean(3);
               NhanVien nhanVien = nhanVien_DAO.getNhanVienByID(rs.getString(4));
                
                TaiKhoan taiKhoan = new TaiKhoan(tenTaiKhoan, matKhau, loaiTaiKhoan, nhanVien);
                listTaiKhoan.add(taiKhoan);
                
            }
        } catch (SQLException ex) {
          Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return listTaiKhoan;
    }
    
     public ArrayList<TaiKhoan> getAllTaiKhoanConHoatDong(){
      
        ArrayList<TaiKhoan> listTaiKhoan=new ArrayList<>();
         KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
           
        try {
            String sql = "  select * from TaiKhoan where isDeleted is null or isDeleted = 0";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String tenTaiKhoan =rs.getString(1);
                String matKhau=rs.getString(2);
                Boolean loaiTaiKhoan=rs.getBoolean(3);
               NhanVien nhanVien = nhanVien_DAO.getNhanVienByID(rs.getString(4));
                
                TaiKhoan taiKhoan = new TaiKhoan(tenTaiKhoan, matKhau, loaiTaiKhoan, nhanVien);
                listTaiKhoan.add(taiKhoan);
                
            }
        } catch (SQLException ex) {
          Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return listTaiKhoan;
    }
    
    public int updateMatKhau(TaiKhoan tk){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql = "update TaiKhoan set matKhau = ? where tenTaiKhoan = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, tk.getMatKhau());
            stmt.setString(2, tk.getTenTaiKhoan());
            return stmt.executeUpdate();
        } catch (Exception e) {
        }
        return -1;
    }
    
    public int xoaTaiKhoan(String maNhanVien){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql = "update TaiKhoan set isDeleted = 1 where maNhanVien = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, maNhanVien);
            return stmt.executeUpdate();
        } catch (Exception e) {
        }
        return -1;
    }
    
    
    public int addTaiKhoan(TaiKhoan taiKhoan) {
            System.out.println(taiKhoan);
        try {
            KetNoiSQL.getInstance();
            Connection con = KetNoiSQL.getConnection();

            String sql = "Insert into TaiKhoan (tenTaiKhoan, matKhau, loaiTaiKhoan, maNhanVien) values(?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, taiKhoan.getTenTaiKhoan());
            ps.setString(2, taiKhoan.getMatKhau());
            ps.setBoolean(3, taiKhoan.getLoaiTaiKhoan());
            ps.setString(4, taiKhoan.getNhanVien().getMaNhanVien());

            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
