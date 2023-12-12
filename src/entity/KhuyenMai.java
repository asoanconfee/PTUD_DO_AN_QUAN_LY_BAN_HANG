package entity;

import dao.KhuyenMaiDAO;
import java.util.Date;

public class KhuyenMai {
    private String maKhuyenMai;
    private int phanTramKhuyenMai;
    private Date ngayBatDau;
    private int soLuong;
    
     private String auto_ID(){
         KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();
        String idPrefix = "KM";
        int length = khuyenMaiDAO.getAllKhuyenMai().size();
        String finalId = idPrefix + String.format("%04d", length + 1);
        return finalId;
    }

    public KhuyenMai() {
    }
    
    public KhuyenMai(String maKhuyenMai) {
         this.maKhuyenMai = maKhuyenMai;
    }

    public KhuyenMai(int phanTramKhuyenMai, Date ngayBatDau, int soLuong) {
        this.maKhuyenMai = auto_ID();
        this.phanTramKhuyenMai = phanTramKhuyenMai;
        this.ngayBatDau = ngayBatDau;
        this.soLuong = soLuong;
    }

    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public int getPhanTramKhuyenMai() {
        return phanTramKhuyenMai;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public void setPhanTramKhuyenMai(int phanTramKhuyenMai) {
        this.phanTramKhuyenMai = phanTramKhuyenMai;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    public long tinhTienKhuyenMai(long giaBan){
        long giaTienKM = 0;
        
        giaTienKM = giaBan * this.phanTramKhuyenMai / 100;
        
        return giaTienKM;
    }

    @Override
    public String toString() {
        return "KhuyenMai{" + "maKhuyenMai=" + maKhuyenMai + ", phanTramKhuyenMai=" + phanTramKhuyenMai + ", ngayBatDau=" + ngayBatDau + ", soLuong=" + soLuong + '}';
    }
    
    
}
