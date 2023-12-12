package entity;

import dao.KieuDangDAO;

public class KieuDang {
    private String maKieuDang;
    private String kieuDang;

    public KieuDang() {
    }
    
     private String auto_ID(){
         KieuDangDAO kieuDang_DAO = new KieuDangDAO();
         String idPrefix = "KD";
        int length = kieuDang_DAO.getAllKieuDang().size();
        String finalId = idPrefix + String.format("%04d", length + 1);
        return finalId;
    }

    public KieuDang( String kieuDang) {
        this.maKieuDang = auto_ID();
        this.kieuDang = kieuDang;
    }
    
    public KieuDang(String maKieuDang, String kieuDang) {
        this.maKieuDang = maKieuDang;
        this.kieuDang = kieuDang;
    }

    public String getMaKieuDang() {
        return maKieuDang;
    }

    public void setMaKieuDang(String maKieuDang) {
        this.maKieuDang = maKieuDang;
    }

    public String getKieuDang() {
        return kieuDang;
    }

    public void setKieuDang(String kieuDang) {
        this.kieuDang = kieuDang;
    }

}
