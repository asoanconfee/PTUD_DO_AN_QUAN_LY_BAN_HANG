package dao;

import ConnectDB.KetNoiSQL;
import entity.NhanVien;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhanVienDAO {

    public NhanVienDAO() {
    }
    
    public ArrayList<NhanVien>getAllNhanVien(){
        ArrayList<NhanVien> listNhanVien = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "Select * from nhanvien";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maNhanVien = rs.getString(1);
                String hoVaTen =rs.getString(2);
                Date ngaySinh = rs.getDate(3);
                String diaChi = rs.getString(4);
                String sdt = rs.getString(5);
                boolean gioiTinh = rs.getBoolean(6);
                int luong = rs.getInt(7);
                String email = rs.getString(8);
                String chucVu = rs.getString(9);
                Timestamp expriedAt = rs.getTimestamp(10);
                NhanVien nhanVien = new NhanVien(maNhanVien, hoVaTen, ngaySinh, diaChi, sdt, gioiTinh, luong, email, chucVu, expriedAt);
                listNhanVien.add(nhanVien);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return listNhanVien;
    }
           
    public ArrayList<NhanVien>getAllNhanVienConHoatDong(){
        ArrayList<NhanVien> listNhanVien = new ArrayList<>();
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "  Select * from nhanvien where isDeleted IS NULL OR isDeleted = 0";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maNhanVien = rs.getString(1);
                String hoVaTen =rs.getString(2);
                Date ngaySinh = rs.getDate(3);
                String diaChi = rs.getString(4);
                String sdt = rs.getString(5);
                boolean gioiTinh = rs.getBoolean(6);
                int luong = rs.getInt(7);
                String email = rs.getString(8);
                String chucVu = rs.getString(9);
                Timestamp expriedAt = rs.getTimestamp(10);
                NhanVien nhanVien = new NhanVien(maNhanVien, hoVaTen, ngaySinh, diaChi, sdt, gioiTinh, luong, email, chucVu, expriedAt);
                listNhanVien.add(nhanVien);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return listNhanVien;
    }
    
    public NhanVien getNhanVienByID(String id){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
           
            String sql = "select * from nhanvien where maNhanVien = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String maNhanVien = rs.getString(1);
                String hoVaTen =rs.getString(2);
                Date ngaySinh = rs.getDate(3);
                String diaChi = rs.getString(4);
                String sdt = rs.getString(5);
                boolean gioiTinh = rs.getBoolean(6);
                int luong = rs.getInt(7);
                String email = rs.getString(8);
                String chucVu = rs.getString(9);
                Timestamp expriedAt = rs.getTimestamp(10);
                NhanVien nhanVien = new NhanVien(maNhanVien, hoVaTen, ngaySinh, diaChi, sdt, gioiTinh, luong, email, chucVu, expriedAt);
                
                
                return nhanVien;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
	
     public NhanVien getNhanVienByName(String name){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
           
            String sql = "select * from nhanvien where hoVaTen = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String maNhanVien = rs.getString(1);
                String hoVaTen =rs.getString(2);
                Date ngaySinh = rs.getDate(3);
                String diaChi = rs.getString(4);
                String sdt = rs.getString(5);
                boolean gioiTinh = rs.getBoolean(6);
                int luong = rs.getInt(7);
                String email = rs.getString(8);
                String chucVu = rs.getString(9);
                Timestamp expriedAt = rs.getTimestamp(10);
                NhanVien nhanVien = new NhanVien(maNhanVien, hoVaTen, ngaySinh, diaChi, sdt, gioiTinh, luong, email, chucVu, expriedAt);
                
                
                return nhanVien;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     	public NhanVien getNhanVienBySdt(String sdt) {
		KetNoiSQL.getInstance();
		Connection conn = KetNoiSQL.getConnection();

		try {
			String sql = "select * from nhanvien where sdt = ?";
			PreparedStatement stmt = conn.prepareCall(sql);
			stmt.setString(1, sdt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = new NhanVien();
				nhanVien.setMaNhanVien(rs.getString(1));
				nhanVien.setHoVaTen(rs.getString(2));
				nhanVien.setNgaySinh(rs.getDate(3));
				nhanVien.setDiaChi(rs.getString(4));
				nhanVien.setSdt(rs.getString(5));
				nhanVien.setGioiTinh(rs.getBoolean(6));
				nhanVien.setLuong(rs.getInt(7));
				nhanVien.setEmail(rs.getString(8));
				nhanVien.setChucVu(rs.getString(9));
				return nhanVien;
			}
		} catch (SQLException ex) {
			Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

    public NhanVien getNhanVienByGmail(String gmail){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
           
            String sql = "select * from nhanvien where email = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, gmail);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String maNhanVien = rs.getString(1);
                String hoVaTen =rs.getString(2);
                Date ngaySinh = rs.getDate(3);
                String diaChi = rs.getString(4);
                String sdt = rs.getString(5);
                boolean gioiTinh = rs.getBoolean(6);
                int luong = rs.getInt(7);
                String email = rs.getString(8);
                String chucVu = rs.getString(9);
                Timestamp expriedAt = rs.getTimestamp(10);
                NhanVien nhanVien = new NhanVien(maNhanVien, hoVaTen, ngaySinh, diaChi, sdt, gioiTinh, luong, email, chucVu, expriedAt);
                
                return nhanVien;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int updateNhanVien(NhanVien nhanVien){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        System.out.println(nhanVien);
        try {
            String sql = "update nhanvien set hoVaTen = ?,ngaySinh = ?,diaChi = ?,sdt = ?, gioiTinh = ?,luong = ?,email = ?,chucVu = ? where maNhanVien = ?";
            
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, nhanVien.getHoVaTen());
            stmt.setDate(2,new Date(nhanVien.getNgaySinh().getTime()));
            stmt.setString(3, nhanVien.getDiaChi());
            stmt.setString(4, nhanVien.getSdt());
            stmt.setBoolean(5, nhanVien.getGioiTinh());
            stmt.setLong(6, nhanVien.getLuong());
            stmt.setString(7, nhanVien.getEmail());
            stmt.setString(8, nhanVien.getChucVu());
            stmt.setString(9, nhanVien.getMaNhanVien());
            
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int updateOTP(String gmail, String OTP, Timestamp expiredAt){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
           
            String sql = "update nhanvien set OTP = ?, expriedAt = ? where email = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, OTP);
            stmt.setTimestamp(2, expiredAt);
            stmt.setString(3, gmail);
            
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int xoaNhanVien(String maNhanVien){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
           
            String sql = "update nhanvien set isDeleted = 1 where maNhanVien = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, maNhanVien); 
            
//            if(taiKhoan_DAO.xoaTaiKhoan(maNhanVien) != -1);
            
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;

    }
    
    public int addNhanVien(NhanVien nhanVien){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        try {
            String sql = "insert into nhanvien(maNhanVien, hoVaTen, ngaySinh, diaChi, sdt, gioiTinh, luong, email, chucVu)"
                + "                 values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, nhanVien.getMaNhanVien());
            stmt.setString(2, nhanVien.getHoVaTen());
            stmt.setDate(3, new Date(nhanVien.getNgaySinh().getTime()));
            stmt.setString(4, nhanVien.getDiaChi());
            stmt.setString(5, nhanVien.getSdt());
            stmt.setBoolean(6, nhanVien.getGioiTinh());
            stmt.setInt(7, nhanVien.getLuong());
            stmt.setString(8, nhanVien.getEmail());
            stmt.setString(9, nhanVien.getChucVu());
            
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}

