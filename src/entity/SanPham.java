package entity;

import dao.SanPhamDAO;

import java.util.Date;

public class SanPham {
    private String maSP;
    private String tenSP;
    private long giaNhap ;
    private String hinhAnh;
    private int soLuong;
    private ChatLieu chatLieu;
    private KieuDang kieuDang ;
    private KichThuoc kichThuoc;
    private MauSac mauSac;
    private XuatXu xuatXu;
    private PhanLoai phanLoai;
    private NhaCungCap nhaCungCap;
    private KhuyenMai khuyenMai;

    public String auto_ID(){
        SanPhamDAO sanPham_DAO = new SanPhamDAO();
         String idPrefix = "SP";
        int length = sanPham_DAO.getAllSanPham().size();
        String finalId = idPrefix + String.format("%04d", length + 1);
        return finalId;
    }

    public SanPham() {
    }
     
    public SanPham(SanPham sp){
        this.maSP = sp.maSP;
        this.tenSP = sp.tenSP;
        this.giaNhap = sp.giaNhap;
        this.hinhAnh = sp.hinhAnh;
        this.soLuong = sp.soLuong;
        this.chatLieu = sp.chatLieu;
        this.kieuDang = sp.kieuDang;
        this.kichThuoc = sp.kichThuoc;
        this.mauSac = sp.mauSac;
        this.xuatXu = sp.xuatXu;
        this.phanLoai = sp.phanLoai;
        this.nhaCungCap = sp.nhaCungCap;
        this.khuyenMai = sp.khuyenMai;
    }
    
    public SanPham(String maSP, String tenSP, long giaNhap, String hinhAnh, int soLuong, ChatLieu chatLieu, KieuDang kieuDang, KichThuoc kichThuoc, MauSac mauSac, XuatXu xuatXu, PhanLoai phanLoai, NhaCungCap nhaCungCap, KhuyenMai khuyenMai) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaNhap = giaNhap;
        this.hinhAnh = hinhAnh;
        this.soLuong = soLuong;
        this.chatLieu = chatLieu;
        this.kieuDang = kieuDang;
        this.kichThuoc = kichThuoc;
        this.mauSac = mauSac;
        this.xuatXu = xuatXu;
        this.phanLoai = phanLoai;
        this.nhaCungCap = nhaCungCap;
        this.khuyenMai = khuyenMai;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public long getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(long giaNhap) {
        this.giaNhap = giaNhap;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public ChatLieu getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(ChatLieu chatLieu) {
        this.chatLieu = chatLieu;
    }

    public KieuDang getKieuDang() {
        return kieuDang;
    }

    public void setKieuDang(KieuDang kieuDang) {
        this.kieuDang = kieuDang;
    }

    public KichThuoc getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(KichThuoc kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public MauSac getMauSac() {
        return mauSac;
    }

    public void setMauSac(MauSac mauSac) {
        this.mauSac = mauSac;
    }

    public XuatXu getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(XuatXu xuatXu) {
        this.xuatXu = xuatXu;
    }

    public PhanLoai getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(PhanLoai phanLoai) {
        this.phanLoai = phanLoai;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public KhuyenMai getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMai = khuyenMai;
    }
    
    public long tinhGiaBanLucDau(){
        long giaBanLucDau = 0;
        
        giaBanLucDau = this.giaNhap + this.giaNhap /2;
        
        return giaBanLucDau;
    }
    public long tinhGiaBanLucSau(){
        long giaBanLucSau = 0;
        Date ngayBD = new java.sql.Date(this.getKhuyenMai().getNgayBatDau().getTime());
        Date ngayHT = new java.sql.Date(System.currentTimeMillis()); 
        if (ngayHT.toString().equals(ngayBD.toString()) || ngayHT.after(ngayBD)) {
            giaBanLucSau = this.tinhGiaBanLucDau() - this.getKhuyenMai().tinhTienKhuyenMai(tinhGiaBanLucDau());
        }
        else
            giaBanLucSau = this.tinhGiaBanLucDau();
        return giaBanLucSau; 
    }

    @Override
    public String toString() {
        return "SanPham{" + "maSP=" + maSP + ", tenSP=" + tenSP + ", giaNhap=" + giaNhap + ", hinhAnh=" + hinhAnh + ", soLuong=" + soLuong + ", chatLieu=" + chatLieu + ", kieuDang=" + kieuDang + ", kichThuoc=" + kichThuoc + ", mauSac=" + mauSac + ", xuatXu=" + xuatXu + ", phanLoai=" + phanLoai + ", nhaCungCap=" + nhaCungCap + ", khuyenMai=" + khuyenMai + '}';
    }
    
    
    
    
    
}
