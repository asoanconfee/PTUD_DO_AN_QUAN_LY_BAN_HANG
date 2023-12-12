package entity;

import dao.NhaCungCapDAO;

public class NhaCungCap {
    private String maNhaCungCap;
    private String tenNhaCungCap;
    private String diaChi;
    private String sdt;
    private String email;

    public NhaCungCap() {
    }

     private String auto_ID(){
         NhaCungCapDAO nhaCungCap_DAO = new NhaCungCapDAO();
         String idPrefix = "NCC";
        int length = nhaCungCap_DAO.getAllNhaCungCap().size();
        String finalId = idPrefix + String.format("%03d", length + 1);
        return finalId;
    }
    
    public NhaCungCap(String tenNhaCungCap, String diaChi, String sdt, String email) {
        this.maNhaCungCap = auto_ID();
        this.tenNhaCungCap = tenNhaCungCap;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
    }

    
    public NhaCungCap(String maNhaCungCap, String tenNhaCungCap, String diaChi, String sdt, String email) {
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
    }

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    
}
