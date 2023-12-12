package entity;

public class ChiTietHoaDon {
    private SanPham sanPham;
    private HoaDon hoaDon;
    private int soLuong;
    private double thanhTien;
    private double thue;
    private double tongTienHD;
    
    
    public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}


	public double getTongTienHD() {
		return tongTienHD;
	}

	public void setTongTienHD(double tongTienHD) {
		this.tongTienHD = tongTienHD;
	}

	public double getThue() {
		return thue;
	}

	public void setThue(double thue) {
		this.thue = thue;
	}

	
	
	

	public ChiTietHoaDon(SanPham sanPham, HoaDon hoaDon, int soLuong, double thanhTien, double thue,
			double tongTienHD) {
		super();
		this.sanPham = sanPham;
		this.hoaDon = hoaDon;
		this.soLuong = soLuong;
		this.thanhTien = thanhTien;
		this.thue = thue;
		this.tongTienHD = tongTienHD;
	}

	public ChiTietHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
    public String toString() {
        return "ChiTietHoaDon{" + "sanPham=" + sanPham + ", hoaDon=" + hoaDon + ", soLuong=" + soLuong + ", thanhTien=" + thanhTien +  ", thue=" + thue + ", tongTienHD=" + tongTienHD +'}';
    }
    
    
}
