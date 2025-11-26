import java.time.LocalDateTime;

public class BaiDoXe {
    private static final int TONG_SO_CHO = 24; 

    // Khu vực Ô tô
    private static boolean[] trangThaiOTo = new boolean[TONG_SO_CHO]; 
    private static String[] bienSoXeOTo = new String[TONG_SO_CHO];
    private static LocalDateTime[] thoiGianDoOTo = new LocalDateTime[TONG_SO_CHO];

    // Khu vực Xe máy
    private static boolean[] trangThaiXeMay = new boolean[TONG_SO_CHO]; 
    private static String[] bienSoXeMay = new String[TONG_SO_CHO];
    private static LocalDateTime[] thoiGianDoXeMay = new LocalDateTime[TONG_SO_CHO];

    static {
        for (int i = 0; i < TONG_SO_CHO; i++) {
            trangThaiOTo[i] = false;
            bienSoXeOTo[i] = null;
            thoiGianDoOTo[i] = null;
            trangThaiXeMay[i] = false;
            bienSoXeMay[i] = null;
            thoiGianDoXeMay[i] = null;
        }
    }

    public static int getTongSoCho() { return TONG_SO_CHO; }

    // Logic đếm chỗ
    public static int demSoChoDaDo(String loaiXe) {
        boolean[] trangThai = layMangTrangThai(loaiXe);
        int count = 0;
        for (boolean daCoXe : trangThai) {
            if (daCoXe) count++;
        }
        return count;
    }
    
    public static int demSoChoConTrong(String loaiXe) {
        return TONG_SO_CHO - demSoChoDaDo(loaiXe);
    }
    
    // Hàm trợ giúp
    private static boolean[] layMangTrangThai(String loaiXe) { return loaiXe != null && loaiXe.equals("oto") ? trangThaiOTo : trangThaiXeMay; }
    private static String[] layMangBienSo(String loaiXe) { return loaiXe != null && loaiXe.equals("oto") ? bienSoXeOTo : bienSoXeMay; }
    private static LocalDateTime[] layMangThoiGianDo(String loaiXe) { return loaiXe != null && loaiXe.equals("oto") ? thoiGianDoOTo : thoiGianDoXeMay; }

    public static boolean kiemTraTrangThai(String loaiXe, int maCho) {
        boolean[] trangThai = layMangTrangThai(loaiXe);
        if (maCho < 0 || maCho >= TONG_SO_CHO) return false;
        return trangThai[maCho];
    }

    public static String getBienSoXe(String loaiXe, int maCho) {
        String[] bienSo = layMangBienSo(loaiXe);
        if (maCho < 0 || maCho >= TONG_SO_CHO) return null;
        return bienSo[maCho];
    }
    
    public static LocalDateTime getThoiGianDo(String loaiXe, int maCho) {
        LocalDateTime[] thoiGian = layMangThoiGianDo(loaiXe);
        if (maCho < 0 || maCho >= TONG_SO_CHO) return null;
        return thoiGian[maCho];
    }

    public static boolean doXe(String loaiXe, int maCho, String bienSo) {
        boolean[] trangThai = layMangTrangThai(loaiXe);
        String[] bienSoArr = layMangBienSo(loaiXe);
        LocalDateTime[] thoiGianArr = layMangThoiGianDo(loaiXe);

        if (maCho >= 0 && maCho < TONG_SO_CHO && !trangThai[maCho]) {
            trangThai[maCho] = true;
            bienSoArr[maCho] = bienSo;
            thoiGianArr[maCho] = LocalDateTime.now();
            return true;
        }
        return false;
    }

    public static boolean roiDi(String loaiXe, int maCho) {
        boolean[] trangThai = layMangTrangThai(loaiXe);
        String[] bienSoArr = layMangBienSo(loaiXe);
        LocalDateTime[] thoiGianArr = layMangThoiGianDo(loaiXe);

        if (maCho >= 0 && maCho < TONG_SO_CHO && trangThai[maCho]) {
            trangThai[maCho] = false;
            bienSoArr[maCho] = null;
            thoiGianArr[maCho] = null;
            return true;
        }
        return false;
    }
}