package entity;

import dao.TaiKhoanDAO;


public class TaiKhoan {
    private String tenTaiKhoan;
    private String matKhau;
    private boolean loaiTaiKhoan; //1: quan ly 0: nhan vien
    private NhanVien nhanVien;

      public String auto_ID(){
        TaiKhoanDAO taiKhoan_DAO = new TaiKhoanDAO();
        // String idPrefix = "TK";
        int length = taiKhoan_DAO.getAllTaiKhoan().size();
        String finalId = String.format("%03d", length + 1);
        return finalId;
    }
    
    public TaiKhoan(String tenTaiKhoan, String matKhau, boolean loaiTaiKhoan, NhanVien nhanVien) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.loaiTaiKhoan = loaiTaiKhoan;
        this.nhanVien = nhanVien;
    }

      public TaiKhoan() {
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public boolean getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(boolean loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" + "tenTaiKhoan=" + tenTaiKhoan + ", matKhau=" + matKhau + ", loaiTaiKhoan=" + loaiTaiKhoan + ", nhanVien=" + nhanVien + '}';
    }

    
    
}
