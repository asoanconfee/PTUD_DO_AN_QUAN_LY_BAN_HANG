package entity;

import dao.PhanLoaiDAO;

public class PhanLoai {
    private String maPhanLoai;
    private String loaiSanPham;

    public PhanLoai() {
    }
    
     private String auto_ID(){
         PhanLoaiDAO phanLoai_DAO = new PhanLoaiDAO();
         String idPrefix = "PL";
        int length = phanLoai_DAO.getAllPhanLoai().size();
        String finalId = idPrefix + String.format("%04d", length + 1);
        return finalId;
    }

     public PhanLoai( String loaiSanPham) {
        this.maPhanLoai = auto_ID();
        this.loaiSanPham = loaiSanPham;
    }
     
    public PhanLoai(String maPhanLoai, String loaiSanPham) {
        this.maPhanLoai = maPhanLoai;
        this.loaiSanPham = loaiSanPham;
    }

    public String getMaPhanLoai() {
        return maPhanLoai;
    }

    public void setMaPhanLoai(String maPhanLoai) {
        this.maPhanLoai = maPhanLoai;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }
    
    
}
