package entity;

import dao.KhachHangDAO;

public class KhachHang {
    private String maKhachHang;
    private String hoVaTen;
    private String email;
    private String sdt;
    private boolean gioiTinh;
    
    public KhachHang() {
    }

    private String auto_ID(){
        KhachHangDAO khachHang_DAO = new KhachHangDAO();
        String idPrefix = "KH";
        int length = khachHang_DAO.getAllKhachHang().size();
        String finalId = idPrefix + String.format("%04d", length + 1);
        return finalId;
    }
    
    public KhachHang(String hoVaTen, String email, String sdt, boolean gioiTinh) {
        this.maKhachHang = auto_ID();
        this.hoVaTen = hoVaTen;
        this.email = email;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
    }

    
    public KhachHang(String maKhachHang, String hoVaTen, String email, String sdt, boolean gioiTinh) {
        this.maKhachHang = maKhachHang;
        this.hoVaTen = hoVaTen;
        this.email = email;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
   
    
}
