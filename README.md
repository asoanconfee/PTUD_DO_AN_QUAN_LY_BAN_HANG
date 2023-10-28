### Các tài liệu liên quan
- Tài liệu thực thi đề tài: https://drive.google.com/drive/folders/1NZHdj6Z8kHSEQIxuaA3VItKTSQUetCSS?usp=sharing
- Soucrce code đề tài: https://github.com/asoanconfee/PTUD_DO_AN_QUAN_LY_BAN_HANG
- File ppt thuyết trình: https://www.canva.com/design/DAFyXuzaCRk/vU9YoJIMIRdL664V_4IBmw/edit?     utm_content=DAFyXuzaCRk&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton

### Run src
- Download src qua link github ở trên
- Và download file dữ liệu SQL và chạy trên SQL sever
- Sử dụng công cụ NetBeans
    Import project
- Sử dụng Eclipse IDE
    Tạo Workspace trước sau đó import project

### Thư viện sửa dụng
- flatlaf-3.2.jar
- flatlaf-extras-3.2.jar
- jsvg-1.2.0.jar
- flatlaf-fonts-roboto-2.137.jar
- swing-toast-notifications-1.0.1.jar

### Các items của menu
``` java
//  Modify this code in raven.menu.Menu.java
public class Menu extends JPanel {

    private final String menuItems[][] = {
        {"Trang chủ"},
        {"~BÁN HÀNG~"},
        {"Hóa đơn", "Lập hóa đơn", "Tìm hóa đơn", "Trả hàng"},
        {"Sản phẩm"},
        {"Khách hàng"},
        {"Nhà cung cấp"},
        {"Nhân viên"},
        {"~BÁO CÁO~"},
        {"Thống kê", "Thống kê doanh thu", "Thống kê sản phẩm", "Thống kê hóa đơn"},
        {"~CÁ NHÂN~"},
        {"Tài khoản", "Thông tin cá nhân"},
        {"~KHÁC~"},
        {"Trợ giúp"},
        {"Đăng xuất"}
    };
```

### Sử dụng để hiển thị các form
``` java
Application.showForm(new PanelForm());
//  Đặt menu với chỉ mục và các chỉ mục con
Application.setSelectedMenu(0, 0);
```
### Menu Event
``` java
menu.addMenuEvent(new MenuEvent() {
    @Override
    private void initMenuEvent() {
        menu.addMenuEvent((int index, int subIndex, MenuAction action) -> {
            if (index == 0) {
                Application.showForm(new FormTrangChu());
            } else if (index == 1) {
                if (subIndex == 1) {
                    Application.showForm(new FormHoaDon());
                } else {
                    action.cancel();
                }
            } else if (index == 2) {
                Application.showForm(new FormSanPham());
            } else if (index == 3) {
                Application.showForm(new FormKhachHang());
            } else if (index == 4) {
                Application.showForm(new FormNhaCungCap());
            } else if (index == 5) {
                Application.showForm(new FormNhanVien());
            } else if (index == 6) {
                if (subIndex == 1) {
                    Application.showForm(new FormThongKe());
                } else if (subIndex == 2) {
                    //Application.showForm(new FormThongKeSanPhamBanChay());
                } else {
                    action.cancel();
                }
            } else if (index == 7) {
                Application.showForm(new FormTaiKhoan());
            } else if (index ==8) {
                Application.showForm(new FormTroGiup());
            } else if (index == 9) {
                Application.logout();
            } else {
                action.cancel();
            }
        });
}
```
