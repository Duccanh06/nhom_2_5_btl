// Ví dụ về phương thức đăng nhập trong DichVuNguoiDung

public class DichVuNguoiDung {
    
    public String xuLyDangNhap(String ten, String mk) {
        if (MockAPIDuLieu.kiemTraDangNhap(ten, mk)) {
            String vaiTro = MockAPIDuLieu.layVaiTro(ten);
            System.out.println("Đang nhap thanh cong! Vai tro: " + vaiTro);
            return vaiTro; // Trả về "lai_xe" hoặc "quan_ly"
        } else {
            System.out.println("Ten đang nhap hoac mat khau khong đung.");
            return null;
        }
    }
    // ... các phương thức khác
}