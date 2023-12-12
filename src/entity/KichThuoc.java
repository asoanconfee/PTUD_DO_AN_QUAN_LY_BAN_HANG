package entity;

import dao.KichThuocDAO;

public class KichThuoc {
   private String maKichThuoc;
   private String kichThuoc;

    public KichThuoc() {
    }

    private String auto_ID(){
         KichThuocDAO kichThuoc_DAO = new KichThuocDAO();
         String idPrefix = "KT";
        int length = kichThuoc_DAO.getAllKichThuoc().size();
        String finalId = idPrefix + String.format("%04d", length + 1);
        return finalId;
    }
    
    public KichThuoc(String kichThuoc) {
        this.maKichThuoc = auto_ID();
        this.kichThuoc = kichThuoc;
    }

    public KichThuoc(String maKichThuoc, String kichThuoc) {
        this.maKichThuoc = maKichThuoc;
        this.kichThuoc = kichThuoc;
    }
    
    public String getMaKichThuoc() {
        return maKichThuoc;
    }

    public void setMaKichThuoc(String maKichThuoc) {
        this.maKichThuoc = maKichThuoc;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

}
