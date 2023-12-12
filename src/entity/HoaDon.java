package entity;

import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.itextpdf.text.List;

public class HoaDon {
    private String maHoaDon;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private Date ngayLap;

 

    private String auto_IDHoaDon() {
        // Auto generate ID hóa đơn dạng HDXXXXXXXXXX - HD + DDMMYYYY - 4 SỐ NGẪU NHIÊN
        HoaDonDAO hoaDon_DAO = new HoaDonDAO();
        String idPrefix = "HD";

        // Lấy danh sách hóa đơn của ngày hiện tại
        LocalDate ngayHienTai = LocalDate.now();
        ArrayList<HoaDon> hoaDonOfCurrentDate = hoaDon_DAO.getAllHoaDon();


     // Kiểm tra xem ngày hiện tại đã tạo hóa đơn trước đó chưa
        boolean isNewDate = hoaDonOfCurrentDate.stream().anyMatch(hd -> hd.getNgayLap().equals(ngayHienTai));

        // Nếu là ngày mới, reset số thứ tự về 1
        if (!isNewDate) {
            hoaDonOfCurrentDate.clear(); // Xóa danh sách hóa đơn cũ
        }

        int length = hoaDonOfCurrentDate.size();
        int randomNumber = (int) (Math.random() * 10000); // Số ngẫu nhiên từ 0 đến 9999

        String formattedDate = ngayHienTai.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        String finalId = idPrefix + formattedDate + String.format("%04d", length + 1);

        return finalId;
    }


    
    public HoaDon() {
        this.maHoaDon = auto_IDHoaDon();
    }
    
    public HoaDon(HoaDon hd){
        this.khachHang = hd.getKhachHang();
        this.nhanVien = hd.getNhanVien();
        this.ngayLap = hd.getNgayLap();
    }
    
    public HoaDon(KhachHang khachHang, NhanVien nhanVien, Date ngayLap) {
        this.maHoaDon = auto_IDHoaDon();
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.ngayLap = ngayLap;
    }
    
    public HoaDon(String maHoaDon, KhachHang khachHang, NhanVien nhanVien, Date ngayLap) {
        this.maHoaDon = maHoaDon;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.ngayLap = ngayLap;
    }
    
    
    public void setThanhHoaDon(){
        this.maHoaDon = auto_IDHoaDon();
    }
    
    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }
    
    public long tongTien(){
        long tongTien = 0;
        ChiTietHoaDonDAO cthd_DAO = new ChiTietHoaDonDAO();
        ArrayList<ChiTietHoaDon>listChiTietHoaDon = cthd_DAO.getAllCTHDByHoaDon(this);
        
        for(ChiTietHoaDon cthd : listChiTietHoaDon){
            tongTien += cthd.getSoLuong();
        }
        
        return tongTien;
    }
    
    
}
