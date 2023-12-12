create database QuanLyCuaHangThoiTrang2
use QuanLyCuaHangThoiTrang2

--Sản phẩm
CREATE TABLE sanPham(
	maSP nvarchar(10) not null,
	tenSP nvarchar(300) not null,
	giaNhap bigint not null,
	hinhAnh nvarchar(50) not null,
	soLuong int not null,
	maChatLieu nvarchar(50) not null,
	maKieuDang nvarchar(10) not null,
	maKichThuoc nvarchar(50) not null,
	maMauSac nvarchar(50) not null,
	maXuatXu nvarchar(10) not null,
	maPhanLoai nvarchar(10) not null,
	maNCC nvarchar(10) not null,
	maKhuyenMai nvarchar(10) not null
	)




	--Phân Loại
CREATE TABLE PhanLoai(
    maPhanLoai nvarchar(10) not null,
	loaiSP nvarchar(50) not null
	)


--Nhà cung cấp

Create table NhaCungCap
(  
    maNCC nvarchar(10) not null, 
	tenNCC nvarchar(100) not null, 
	diaChi nvarchar(200) not null, 
	email nvarchar(50) not null unique, 
	sDT nvarchar(20) not null unique, 
);

--Nhân viên

create table nhanvien(
	maNhanVien nvarchar(10) not null, 
	hoVaTen nvarchar(30) not null,
	ngaySinh date not null,
	diaChi nvarchar(200) not null,
	sdt nvarchar(10) not null unique,
	gioiTinh bit not null, --0: nam --1: nu
	luong int not null,
	email nvarchar(30) not null unique,
	chucvu nvarchar(20) not null,
	expriedAt datetime null,
	isDeleted bit null --0: con hoat dong --1: da nghi viec
)


--ACCOUNT
create table TaiKhoan(
	tenTaiKhoan nvarchar(20) not null,
	matKhau nvarchar(20) not null,
	loaiTaiKhoan bit not null, --1: quan ly -- 0: nhan vien
	maNhanVien nvarchar(10) not null unique, 
	isDeleted bit null  --0: con hoat dong --1: da nghi viec
	)
--HÓA ĐƠN
create table HoaDon(
	maHoaDon nvarchar(20) not null,
	ngaylap date not null,
	maNhanVien nvarchar(10) not null,
	maKhachHang nvarchar(10) not null,


)

--CHI TIẾT HÓA ĐƠN
create table ChiTietHoaDon(
	maSP nvarchar(10) not null,
	maHoaDon nvarchar(20) not null,
	soLuong int not null,
	thanhTien bigint not null,
	thue bigint not null,
	tongTien bigint not null
)

--KHÁCH HÀNG
create table khachHang(
	maKhachHang nvarchar(10) not null,
	hoVaTen nvarchar(30) not null,
	email nvarchar(30) not null unique,
	sdt nvarchar(10) not null unique, 
	gioiTinh bit not null --0: nam 1: nu
)

--Khuyến mãi
create table KhuyenMai(
	maKhuyenMai nvarchar(10) not null,
	phanTramKhuyenMai int not null,
	ngayBatDau date not null,
	soLuong int not null
)
create table XuatXu(
	maXuatXu nvarchar(10) not null,
	noiXuatXu nvarchar(20) not null
)
create table ChatLieu(
	maChatLieu nvarchar(50) not null,
	chatLieu nvarchar(20) not null
)
create table MauSac(
	maMauSac nvarchar(50) not null,
	mauSac nvarchar(20) not null
)
create table KichThuoc(
	maKichThuoc nvarchar(50) not null,
	kichThuoc nvarchar(10) not null
)
create table KieuDang(
	maKieuDang nvarchar(10) not null,
	kieuDang nvarchar(20) not null
)

--PRIMARY KEY

alter table ChiTietHoaDon
add primary key(maHoaDon)

alter table sanPham
add primary key(maSP)

alter table PhanLoai
add primary key(maPhanLoai)

alter table nhacungcap
add primary key(maNCC)

alter table nhanvien
add primary key(maNhanVien)

alter table TaiKhoan
add primary key(tenTaiKhoan)

alter table hoadon
add primary key(maHoaDon)

alter table KhachHang
add primary key(maKhachHang)

alter table khuyenMai
add primary key(maKhuyenMai)

alter table XuatXu
add primary key(maXuatXu)

alter table ChatLieu
add primary key(maChatLieu)

alter table MauSac
add primary key(maMauSac)

alter table KichThuoc
add primary key(maKichThuoc)

alter table KieuDang
add primary key(maKieuDang)
--FOREIGN KEY

alter table sanPham
add constraint FK_sanPham_KhuyenMai foreign key(maKhuyenMai) references khuyenMai(maKhuyenMai)

alter table sanPham
add constraint FK_sanPham_phanLoai foreign key(maPhanLoai) references PhanLoai(maPhanLoai)

alter table sanPham
add constraint FK_sanPham_xuatXu foreign key(maXuatXu) references XuatXu(maXuatXu)

alter table sanPham
add constraint FK_sanPham_chatLieu foreign key(maChatLieu) references ChatLieu(maChatLieu)

alter table sanPham
add constraint FK_sanPham_mauSac foreign key(maMauSac) references MauSac(maMauSac)

alter table sanPham
add constraint FK_sanPham_kichThuoc foreign key(maKichThuoc) references KichThuoc(maKichThuoc)

alter table sanPham
add constraint FK_sanPham_kieuDang foreign key(maKieuDang) references KieuDang(maKieuDang)

alter table sanPham
add constraint FK_sanPham_nhaCungCap foreign key(maNCC) references NhaCungCap(maNCC)

alter table TaiKhoan 
add constraint FK_taiKhoan_nhanVien foreign key(maNhanVien) references nhanvien(maNhanVien)

alter table hoadon
add constraint FK_hoaDon_nhanVien foreign key(maNhanVien) references NhanVien(maNhanVien)

alter table hoadon
add constraint FK_hoaDon_khachHang foreign key(maKhachHang) references khachhang(maKhachHang)

alter table ChiTietHoaDon
add constraint FK_cthd_hd foreign key(maHoaDon) references hoadon(maHoaDon)

alter table ChiTietHoaDon
add constraint FK_cthd_sp foreign key(maSP) references sanPham(maSP)


ALTER TABLE nhanvien
ADD DEFAULT(null) FOR expriedAt

ALTER TABLE nhanvien
ADD DEFAULT(0) FOR isDeleted

ALTER TABLE TaiKhoan
ADD DEFAULT(0) FOR isDeleted


select * from [dbo].[nhacungcap]
select * from [dbo].sanPham
select * from [dbo].ChiTietHoaDon
select * from [dbo].[hoadon]
select * from [dbo].[khachhang]
select * from [dbo].[nhanvien]
select * from [dbo].TaiKhoan

select * from khuyenMai
select * from XuatXu
select * from KieuDang
select * from KichThuoc
select * from MauSac
select * from ChatLieu
select * from PhanLoai

----THÊM GIÁ TRỊ VÀO TABLE Nhà cung cấp
insert into NhaCungCap values ('NCC00001',N'Công Ty TNHH HADES',N'45 Phan Chu Trinh, P. Bến Thành, Quận 1, TP. Hồ Chí Minh','support@hades.vn','02873011021');
insert into NhaCungCap values ('NCC00002',N'Công ty may mặc Quảng Việt',N'Đường Nguyễn Văn Ni, tổ 10, Khu phố 2, Thị Trấn Củ Chi, Huyện Củ Chi, Thành phố Hồ Chí Minh','maymacQuangViet@gmail.com','02838921389');
insert into NhaCungCap values ('NCC00003',N'Công Ty TNHH PARADOX Việt Nam',N'995 Nguyễn Trãi, Phường 14, Quận 5, TP. Hồ Chí Minh','info@paradoxworldwide.com','0344800808');
insert into NhaCungCap values ('NCC00004',N'Công Ty TNHH Tm & May Mặc Thời Trang S.A',N'Hương Giang, Cư xá Bắc Hải, 10, Thành phố Hồ Chí Minh','yatuan99@gmail.com','02839708748');
insert into NhaCungCap values ('NCC00005',N'Công ty TNHH May Thêu Giày An Phước',N'100/11-12 An Dương Vương, P.9, Q.5, Tp.Hồ Chí Minh','maydodongphuc@anphuoc.com.vn','02838350059');
insert into NhaCungCap values ('NCC00006',N'Công ty TNHH May Tam Hiệp',N'3/6C Ấp Tiền Lân, Bà Điểm, Hóc Môn, Thành phố Hồ Chí Minh','quanaongoclinh@gmail.com','0966515254');
insert into NhaCungCap values ('NCC00007',N'Công ty TNHH May Bico Kids',N'176/7 Trần Huy Liệu, P. 15, Phú Nhuận, TP.HCM','CongTyBicoKids@gmail.com','0902896323');
insert into NhaCungCap values ('NCC00008',N'Công ty TNHH May Dony',N'142/4 Bàu Cát 2, Phường 12, Quận Tân Bình, TP.HCM','TrangphucDony@gmail.com','0938842123');
insert into NhaCungCap values ('NCC00009',N'Công ty TNHH May Nuna Kids',N'247/25/4. Khu Nam Long, Hà Huy Giáp, Thạnh Lộc, Q.12, TP.HCM','quanaotreemnuna@gmail.com','0933621584');
insert into NhaCungCap values ('NCC00010',N'Công ty TNHH May VNXK Eva',N'Số 8, Ngõ 13, Giải Phóng, Hai Bà Trưng, Hà Nội','MayVNXKEVA@yahoo.com.vn','0904879533');
insert into NhaCungCap values ('NCC00011',N'Công ty TNHH May Khang Thịnh',N'125/22 Bùi Quang Là, P 12,Q Gò Vấp,TP HCM','maymackhangthinh@gmail.com','0914296114');
insert into NhaCungCap values ('NCC00012',N'Công ty TNHH May Trang Sỉ',N'133/71 Liên khu 4-5, P Bình Hưng Hòa B, Q Bình Tân, TP HCM','Lienhetrangsi@gmail.com','0968853302');
insert into NhaCungCap values ('NCC00013',N'Công ty TNHH May haymuasi.com',N'Số 23 Đường 11, Tổ 1, KP 6, P Phước Long B, Q 9, TP HCM','Haymuasi@gmail.com','1900636749');
insert into NhaCungCap values ('NCC00014',N'Công ty TNHH May áo khoác Ann',N'68 đường C2, P 13, Q Tân Bình, TP HCM','tranyenngoc9x@gmail.com',' 0918567109');
insert into NhaCungCap values ('NCC00015',N'Công ty TNHH May áo khoác Thịnh Phát',N'269/99 Phan Huy Ích, P 14, Q Gò Vấp, TP HCM','maymacthinhphat@gmail.com','0935818711');



----THÊM GIÁ TRỊ VÀO TABLE NHÂN VIÊN

insert into nhanvien values ('QL0001',N'Nguyễn Thảo Uyên','2002-03-11',N'TP. Hồ Chí Minh','0925365999',1,7000000,'thaouyen@gmail.com',N'Quản Lý',NULL,0);
insert into nhanvien values ('NV0002',N'Nguyễn Phú Sang','2002-05-01',N'TP. Hồ Chí Minh','0925365441',0,5000000,'phusang@gmail.com',N'Nhân Viên',NULL,0);
insert into nhanvien values ('NV0003',N'Nguyễn Tấn Dũng','2001-03-31',N'TP. Hồ Chí Minh','0925365112',0,4500000,'tandung@gmail.com',N'Nhân Viên',NULL,0);
insert into nhanvien values ('NV0004',N'Lê Minh Thư','2001-03-31',N'TP. Hồ Chí Minh','0925365234',1,4500000,'minhthu@gmail.com',N'Nhân Viên',NULL,0);


----THÊM GIÁ TRỊ VÀO TABLE TÀI KHOẢN


insert into TaiKhoan values ('QL0001','admin',1,'QL0001',0);
insert into TaiKhoan values ('002','1111',0,'NV0002',0);
insert into TaiKhoan values ('003','1111',0,'NV0003',0);
insert into TaiKhoan values ('004','1111',0,'NV0004',0);




----THÊM GIÁ TRỊ VÀO TABLE KHUYẾN MÃI
insert into KhuyenMai values ('KM0001','0','2023-12-31',20);
insert into KhuyenMai values ('KM0002','40','2023-12-31',20);
insert into KhuyenMai values ('KM0003','50','2023-12-12',20);
insert into KhuyenMai values ('KM0004','30','2023-11-11',20);
insert into KhuyenMai values ('KM0005','5','2023-11-30',10);




----THÊM GIÁ TRỊ VÀO TABLE XUẤT XỨ
insert into XuatXu values ('XX0001',N'Việt Nam');
insert into XuatXu values ('XX0002',N'Trung Quốc');
insert into XuatXu values ('XX0003',N'Hàn Quốc');
insert into XuatXu values ('XX0004',N'USA');
insert into XuatXu values ('XX0005',N'Đức');
insert into XuatXu values ('XX0006',N'Ý');
insert into XuatXu values ('XX0007',N'Nga');
insert into XuatXu values ('XX0008',N'Úc');
insert into XuatXu values ('XX0009',N'Ấn Độ');
insert into XuatXu values ('XX0010',N'Áo');
insert into XuatXu values ('XX0011',N'Thụy Sĩ');
insert into XuatXu values ('XX0012',N'Nhật Bản');
insert into XuatXu values ('XX0013',N'Phần Lan');
insert into XuatXu values ('XX0014',N'Romania');
insert into XuatXu values ('XX0015',N'Pháp');


----THÊM GIÁ TRỊ VÀO TABLE KÍCH THƯỚC 

insert into KichThuoc values ('KT0001',N'S');
insert into KichThuoc values ('KT0002',N'M');
insert into KichThuoc values ('KT0003',N'L');
insert into KichThuoc values ('KT0004',N'XL');
insert into KichThuoc values ('KT0005',N'XXL');


----THÊM GIÁ TRỊ VÀO TABLE MÀU SẮC

insert into MauSac values ('MS0001',N'Trắng');
insert into MauSac values ('MS0002',N'Đen');
insert into MauSac values ('MS0003',N'Xanh rêu');
insert into MauSac values ('MS0004',N'Nâu đậm');
insert into MauSac values ('MS0005',N'Vàng');
insert into MauSac values ('MS0006',N'Tím');
insert into MauSac values ('MS0007',N'Xanh than');
insert into MauSac values ('MS0008',N'Hồng');
insert into MauSac values ('MS0009',N'Xanh bơ');
insert into MauSac values ('MS0010',N'Tràm');
insert into MauSac values ('MS0011',N'Đỏ');
insert into MauSac values ('MS0012',N'Hồng phấn');
insert into MauSac values ('MS0013',N'Lam');
insert into MauSac values ('MS0014',N'Be');
insert into MauSac values ('MS0015',N'Xám');


----THÊM GIÁ TRỊ VÀO TABLE CHẤT LIỆU
insert into ChatLieu values ('CL0001',N'Cotton');
insert into ChatLieu values ('CL0002',N'Vải Nỉ');
insert into ChatLieu values ('CL0003',N'Vải dù');
insert into ChatLieu values ('CL0004',N'Kaki cotton');
insert into ChatLieu values ('CL0005',N'Da');
insert into ChatLieu values ('CL0006',N'Vải len');
insert into ChatLieu values ('CL0007',N'Vải thô');
insert into ChatLieu values ('CL0008',N'Vải canvas');
insert into ChatLieu values ('CL0009',N'Vải Đũi');
insert into ChatLieu values ('CL0010',N'Vải Bamboo');
insert into ChatLieu values ('CL0011',N'Vải Tuyết Mưa');
insert into ChatLieu values ('CL0012',N'Vải Chiffon');
insert into ChatLieu values ('CL0013',N'Vải Modal');
insert into ChatLieu values ('CL0014',N'Vải Lanh');
insert into ChatLieu values ('CL0015',N'Vải Viscose');
insert into ChatLieu values ('CL0016',N'Vải lụa');
insert into ChatLieu values ('CL0017',N'Vải Jeans');
insert into ChatLieu values ('CL0018',N'Vải denim');
insert into ChatLieu values ('CL0019',N'Vải Satin');
insert into ChatLieu values ('CL0020',N'Vải thun lạnh');
insert into ChatLieu values ('CL0021',N'Da lộn');
insert into ChatLieu values ('CL0022',N'Vải mesh');

----THÊM GIÁ TRỊ VÀO TABLE Phân loại
insert into PhanLoai values ('PL0001',N'Giày thể thao');
insert into PhanLoai values ('PL0002',N'Đầm Cocktail');
insert into PhanLoai values ('PL0003',N'Áo Thun');
insert into PhanLoai values ('PL0004',N'Áo Sơ mi');
insert into PhanLoai values ('PL0005',N'Áo Polo');
insert into PhanLoai values ('PL0006',N'Quần sooc ');
insert into PhanLoai values ('PL0007',N'Quần Jeans');
insert into PhanLoai values ('PL0008',N'Áo Khoác');
insert into PhanLoai values ('PL0009',N'Túi xách');
insert into PhanLoai values ('PL0010',N'Ví nam');
insert into PhanLoai values ('PL0011',N'Dép');
insert into PhanLoai values ('PL0012',N'Giày cao gót');
insert into PhanLoai values ('PL0013',N'Kính');
insert into PhanLoai values ('PL0014',N'Giày Sandal');



----THÊM GIÁ TRỊ VÀO TABLE KIỂU DÁNG
insert into KieuDang values ('KD0001',N'Basic');
insert into KieuDang values ('KD0002',N'Dáng chữ A');
insert into KieuDang values ('KD0003',N'Oversize');
insert into KieuDang values ('KD0004',N'Dáng ôm sát');
insert into KieuDang values ('KD0005',N'Dáng suông form rộng');
insert into KieuDang values ('KD0006',N'Form Chữ Nhật');
insert into KieuDang values ('KD0007',N'Giày cao gót nhọn');
insert into KieuDang values ('KD0008',N'Dép Quai Ngang');
insert into KieuDang values ('KD0009',N'Form Vuông');
insert into KieuDang values ('KD0015',N'Giày Sandal');



----THÊM GIÁ TRỊ VÀO TABLE KHÁCH HÀNG

insert into khachHang values ('KH0001',N'Lê Thị Hồng Vương', 'hvlt@gmail.com','0337098001',1);
insert into khachHang values ('KH0002',N'Đinh Hoàng Chiến', 'dinhlemy@gmail.com','0917798113',0);
insert into khachHang values ('KH0003',N'Đan Mỹ Chi', 'danmychi@gmail.com','0925197741',1);
insert into khachHang values ('KH0004',N'Nguyễn Văn Tùng', 'nguyenvantung@gmail.com','0566920965',0);
insert into khachHang values ('KH0005',N'Phạm Phương Duy', 'phamphuongduy@gmail.com','0919191923',1);
insert into khachHang values ('KH0006',N'Nguyễn Minh Khuê', 'nguyenminhkhueh@gmail.com','0914625893',1);
insert into khachHang values ('KH0007',N'Bùi Hải Lâm', 'buihailam@gmail.com','0912596378',1);
insert into khachHang values ('KH0008',N'Phạm Thị Hằng Nga', 'phamthihangnga@gmail.com','0917458963',1);
insert into khachHang values ('KH0009',N'Lê Tiến Tâm', 'letientam@gmail.com','0912225036',1);
insert into khachHang values ('KH0010',N'Ngô Ngọc Bích', 'ngobichngoc@gmail.com','0915578964',1);
insert into khachHang values ('KH0011',N'Lê Tuấn Kiệt', 'letuankiet@gmail.com','0910236669',1);
insert into khachHang values ('KH0012',N'Nguyễn Hà My', 'nguyenhamy@gmail.com','0911455720',1);
insert into khachHang values ('KH0013',N'Nguyễn Minh Nghĩa', 'nguyenminhnghia@gmail.com','0911555420',1);
insert into khachHang values ('KH0014',N'Vũ Quang Bách', 'vuquangbach@gmail.com','0910233212',1);
insert into khachHang values ('KH0015',N'Nguyễn Thùy Trang', 'nguyenthuytrang@gmail.com','0914747852',1);
insert into khachHang values ('KH0016',N'Nguyễn Thanh Vân', 'nguyenthanhvan@gmail.com','0910211147',1);
insert into khachHang values ('KH0017',N'Phan Thị Phương Thùy', 'phanthiphuongthuy@gmail.com','0911233028',1);
insert into khachHang values ('KH0018',N'Lê Đức Khánh', 'leduckhanh@gmail.com','0917822226',1);
insert into khachHang values ('KH0019',N'Phan Trung Kiên', 'phantrungkien@gmail.com','0914554896',1);
insert into khachHang values ('KH0020',N'Vương Đình Hào', 'vuongdinhhao@gmail.com','0916969632',1);
----THÊM GIÁ TRỊ VÀO TABLE Sản phẩm

insert into sanPham values ('SP0001',N'Giày Sneaker NB530 màu xám, giày thể thao new balance 530 bản cao cấp nhất',1000000,'SP0001.jpg',1000,'CL0005','KD0001','KT0002','MS0001','XX0001','PL0001','NCC00001','KM0001');
insert into sanPham values ('SP0002',N'Giày thể thao unisex New Balance 574 / Familiar Ground',1850000 ,'SP0002.jpg',900 ,'CL0008','KD0001','KT0002','MS0001 ','XX0002','PL0001','NCC00002','KM0002');

insert into sanPham values ('SP0003',N'La Rosa Dress',2670000 ,'SP0003.jpg',500 ,'CL0002','KD0002','KT0001','MS0008','XX0001','PL0002','NCC00006','KM0003');
insert into sanPham values ('SP0004',N'Clara Dress',2760000 ,'SP0004.jpg',800 ,'CL0002','KD0002','KT0002','MS0001','XX0001','PL0002','NCC00003','KM0004');

insert into sanPham values ('SP0005',N'Áo thun Unisex Eleven Original',285000 ,'SP0005.jpg',300 ,'CL0007','KD0003','KT0001','MS0001','XX0001','PL0003','NCC00001','KM0005');
insert into sanPham values ('SP0006',N'Áo thun Unisex Eleven Original',285000 ,'SP0006.jpg',300 ,'CL0007','KD0003','KT0002','MS0001','XX0001','PL0003','NCC00004','KM0001');

insert into sanPham values ('SP0007',N'Áo Thun Polo Dry',380000  ,'SP0007.jpg',300 ,'CL0007','KD0003','KT0002','MS0001','XX0001','PL0003','NCC00005','KM0002');
insert into sanPham values ('SP0008',N'AIRism Áo Polo Gài Nút',480000  ,'SP0008.jpg',300 ,'CL0007','KD0003','KT0002','MS0002','XX0001','PL0005','NCC00006','KM0003');

insert into sanPham values ('SP0009',N'Áo Sơ Mi Vải Brushed Twill Kẻ Caro Dài Tay',600000,'SP0009.jpg',400 ,'CL0007','KD0003','KT0002','MS0015','XX0001','PL0004','NCC00002','KM0004');
insert into sanPham values ('SP0010',N'Áo Sơ Mi Không Cần Ủi Vải Jersey Kẻ Sọc Dài Tay',600000  ,'SP0010.jpg',400 ,'CL0010','KD0003','KT0002','MS0009','XX0001','PL0004','NCC00001','KM0005');

insert into sanPham values ('SP0011',N'Quần Sooc Nữ Jeans Dáng A',260000 ,'SP0011.jpg',400 ,'CL0011','KD0002','KT0001','MS0002','XX0001','PL0006','NCC00009','KM0001');
insert into sanPham values ('SP0012',N'Quần Jean Nữ',360000 ,'SP0012.jpg',400 ,'CL0011','KD0002','KT0003 ','MS0007 ','XX0001','PL0007','NCC00001','KM0002');

insert into sanPham values ('SP0013',N'Quần tây đen Việt Tiến',500000 ,'SP0013.jpg',400 ,'CL0013 ','KD0005 ','KT0004','MS0003','XX0001','PL0006','NCC00003','KM0003');
insert into sanPham values ('SP0014',N'Quần Jean NữAIRism Áo Khoác Có Mũ Chống UV Dài Tay ',500000  ,'SP0014.jpg',200 ,'CL0012 ','KD0003','KT0002','MS0002','XX0001','PL0008','NCC00007','KM0004');

insert into sanPham values ('SP0015',N'Áo Khoác Nữ Chống Nắng Siêu Nhẹ Thân Thiện Môi Trường',299000  ,'SP0015.jpg',250 ,'CL0013','KD0003','KT0003','MS0008','XX0001','PL0008','NCC00008','KM0005');
insert into sanPham values ('SP0016',N'Túi đeo vai phom chữ nhật Austen Multi-Pocket',1190000  ,'SP0016.jpg',150 ,'CL0018  ','KD0006','KT0001','MS0014','XX0001','PL0009','NCC00001','KM0001');

insert into sanPham values ('SP0017',N'Giày sandals cao gót Metallic Cap Ankle-Strap',1190000,'SP0017.jpg',150 ,'CL0019','KD0007','KT0003 ','MS0001','XX0001','PL0012','NCC00003','KM0002');
insert into sanPham values ('SP0018',N'Ví Gucci Men	s Black Leather Monogram Wallet Màu Đen',1190000,'SP0018.jpg',150 ,'CL0020','KD0006','KT0001','MS0002','XX0006','PL0010','NCC00003','KM0003');

insert into sanPham values ('SP0019',N'Dép Nam 2 Quai Chéo Cách Điệu Thời Trang Phong Cách Cá Tính',190000,'SP0019.jpg',150 ,'CL0021','KD0008','KT0003','MS0002','XX0001','PL0011','NCC00001','KM0004');
insert into sanPham values ('SP0020',N'Mắt Kính Bosco',1190000 ,'SP0020.jpg',150 ,'CL0022','KD0009','KT0001','MS0002','XX0001','PL0013','NCC00006','KM0005');

select * from sanPham

DELETE FROM sanPham
WHERE MaSP BETWEEN 'SP0019' AND 'SP0020';