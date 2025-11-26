import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GiaoDienChinh {

    private JFrame khungChinh;
    private JTextField truongTenDangNhap;
    private JPasswordField truongMatKhau;

    private final ImageIcon LOGIN_BACKGROUND = createImageIcon("/resources/background_login.jpg", 500, 300);
    private final ImageIcon APP_ICON = createImageIcon("/resources/parking_icon.png", 32, 32);

    private ImageIcon createImageIcon(String path, int w, int h) {
        try {
            java.net.URL imgURL = getClass().getResource(path);
            if (imgURL != null) {
                return new ImageIcon(new ImageIcon(imgURL).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
            }
        } catch (Exception e) {}
        return new ImageIcon();
    }

    public GiaoDienChinh() {

        khungChinh = new JFrame("Smart Parking - Đăng Nhập & Đăng Ký");
        khungChinh.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        khungChinh.setSize(500, 300);
        khungChinh.setLocationRelativeTo(null);
        khungChinh.setLayout(new BorderLayout());

        if (APP_ICON.getImageLoadStatus() == MediaTracker.COMPLETE) {
            khungChinh.setIconImage(APP_ICON.getImage());
        }

        JPanel panelVoiAnhNen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (LOGIN_BACKGROUND.getImageLoadStatus() == MediaTracker.COMPLETE) {
                    g.drawImage(LOGIN_BACKGROUND.getImage(), 0, 0, getWidth(), getHeight(), this);
                } else {
                    g.setColor(new Color(240, 240, 240));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };

        panelVoiAnhNen.setLayout(new GridBagLayout());
        panelVoiAnhNen.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nhanTieuDe = new JLabel("ĐĂNG NHẬP HỆ THỐNG", SwingConstants.CENTER);
        nhanTieuDe.setFont(new Font("Arial", Font.BOLD, 22));
        nhanTieuDe.setForeground(Color.BLACK);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panelVoiAnhNen.add(nhanTieuDe, gbc);

        JLabel nhanTen = new JLabel("Tên Đăng Nhập:", SwingConstants.RIGHT);
        nhanTen.setFont(new Font("Arial", Font.PLAIN, 14));
        nhanTen.setForeground(Color.BLACK);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        panelVoiAnhNen.add(nhanTen, gbc);

        truongTenDangNhap = new JTextField(20);
        truongTenDangNhap.setBorder(new LineBorder(Color.GRAY, 1, true));
        gbc.gridx = 1; gbc.gridy = 1;
        panelVoiAnhNen.add(truongTenDangNhap, gbc);

        JLabel nhanMatKhau = new JLabel("Mật Khẩu:", SwingConstants.RIGHT);
        nhanMatKhau.setFont(new Font("Arial", Font.PLAIN, 14));
        nhanMatKhau.setForeground(Color.BLACK);
        gbc.gridx = 0; gbc.gridy = 2;
        panelVoiAnhNen.add(nhanMatKhau, gbc);

        truongMatKhau = new JPasswordField(20);
        truongMatKhau.setBorder(new LineBorder(Color.GRAY, 1, true));
        gbc.gridx = 1; gbc.gridy = 2;
        panelVoiAnhNen.add(truongMatKhau, gbc);

        // ---------------------- NÚT ĐĂNG NHẬP -------------------------
        JButton nutDangNhap = new JButton("Đăng Nhập");
        nutDangNhap.setFont(new Font("Arial", Font.BOLD, 14));
        nutDangNhap.setBackground(new Color(70, 130, 180));
        nutDangNhap.setForeground(Color.BLACK);
        nutDangNhap.setFocusPainted(false);

        // XÓA PHẦN TRẮNG BÊN TRONG NÚT
        nutDangNhap.setContentAreaFilled(false);
        nutDangNhap.setOpaque(true);
        nutDangNhap.setBorderPainted(false);

        nutDangNhap.setBorder(CustomBorderFactory.createRoundedBorder(10));

        gbc.gridx = 0; gbc.gridy = 3;
        panelVoiAnhNen.add(nutDangNhap, gbc);

        // ---------------------- NÚT ĐĂNG KÝ -------------------------
        JButton nutDangKy = new JButton("Đăng Ký Tài Khoản");
        nutDangKy.setFont(new Font("Arial", Font.BOLD, 14));
        nutDangKy.setBackground(new Color(60, 179, 113));
        nutDangKy.setForeground(Color.BLACK);
        nutDangKy.setFocusPainted(false);

        // XÓA PHẦN TRẮNG
        nutDangKy.setContentAreaFilled(false);
        nutDangKy.setOpaque(true);
        nutDangKy.setBorderPainted(false);

        nutDangKy.setBorder(CustomBorderFactory.createRoundedBorder(10));

        gbc.gridx = 1; gbc.gridy = 3;
        panelVoiAnhNen.add(nutDangKy, gbc);

        // HÀNH ĐỘNG
        nutDangNhap.addActionListener(e -> xuLyDangNhap());
        nutDangKy.addActionListener(e -> moCuaSoDangKy());

        khungChinh.add(panelVoiAnhNen, BorderLayout.CENTER);
        khungChinh.setVisible(true);
    }

    // ---------------------- XỬ LÝ ĐĂNG NHẬP -------------------------
    private void xuLyDangNhap() {
        String tenDangNhap = truongTenDangNhap.getText();
        String matKhau = new String(truongMatKhau.getPassword());

        if (MockAPIDuLieu.kiemTraDangNhap(tenDangNhap, matKhau)) {

            String vaiTro = MockAPIDuLieu.layVaiTro(tenDangNhap);

            JOptionPane.showMessageDialog(khungChinh,
                    "Đăng nhập thành công! Chào mừng " + tenDangNhap + "!",
                    "Thành công",
                    JOptionPane.INFORMATION_MESSAGE);

            khungChinh.dispose();

            if ("lai_xe".equals(vaiTro)) {
                new ManHinhLaiXe(
                        tenDangNhap,
                        MockAPIDuLieu.getBienSoXe(tenDangNhap),
                        MockAPIDuLieu.getLoaiXe(tenDangNhap)
                );
            } else if ("quan_ly".equals(vaiTro)) {
                new ManHinhQuanLy(tenDangNhap);
            }

        } else {
            JOptionPane.showMessageDialog(khungChinh,
                    "Đăng nhập thất bại: Sai tên hoặc mật khẩu.",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // ---------------------- CỬA SỔ ĐĂNG KÝ -------------------------
    private void moCuaSoDangKy() {

        JDialog dialogDangKy = new JDialog(khungChinh, "Đăng Ký Tài Khoản Mới", true);
        dialogDangKy.setLocationRelativeTo(khungChinh);
        dialogDangKy.setSize(400, 280);

        JPanel panelTong = new JPanel();
        panelTong.setLayout(new BoxLayout(panelTong, BoxLayout.Y_AXIS));
        panelTong.setBorder(new EmptyBorder(10, 10, 10, 10));

        JTextField truongTen = new JTextField(15);
        JPasswordField truongMK = new JPasswordField(15);
        JComboBox<String> chonVaiTro = new JComboBox<>(new String[]{"lai_xe", "quan_ly"});

        JPanel panelCoBan = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        JLabel lblTen = new JLabel("Tên Đăng Nhập:");
        lblTen.setForeground(Color.BLACK);
        panelCoBan.add(lblTen);
        panelCoBan.add(truongTen);

        JLabel lblMK = new JLabel("Mật Khẩu:");
        lblMK.setForeground(Color.BLACK);
        panelCoBan.add(lblMK);
        panelCoBan.add(truongMK);

        JLabel lblVaiTro = new JLabel("Vai Trò:");
        lblVaiTro.setForeground(Color.BLACK);
        panelCoBan.add(lblVaiTro);
        panelCoBan.add(chonVaiTro);

        JPanel panelThongTinXe = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JComboBox<String> chonLoaiXe = new JComboBox<>(new String[]{"oto", "xe_may"});
        JTextField truongBienSo = new JTextField(15);

        JLabel lblLoaiXe = new JLabel("Loại Xe (Lái xe):");
        lblLoaiXe.setForeground(Color.BLACK);
        panelThongTinXe.add(lblLoaiXe);
        panelThongTinXe.add(chonLoaiXe);

        JLabel lblBienSo = new JLabel("Biển Số Xe:");
        lblBienSo.setForeground(Color.BLACK);
        panelThongTinXe.add(lblBienSo);
        panelThongTinXe.add(truongBienSo);

        JButton nutXacNhan = new JButton("Xác Nhận Đăng Ký");
        nutXacNhan.setFont(new Font("Arial", Font.BOLD, 14));
        nutXacNhan.setBackground(new Color(60, 179, 113));
        nutXacNhan.setForeground(Color.BLACK);
        nutXacNhan.setFocusPainted(false);

        // XOÁ PHẦN TRẮNG NÚT
        nutXacNhan.setContentAreaFilled(false);
        nutXacNhan.setOpaque(true);
        nutXacNhan.setBorderPainted(false);

        nutXacNhan.setBorder(CustomBorderFactory.createRoundedBorder(10));
        nutXacNhan.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelTong.add(panelCoBan);
        panelTong.add(panelThongTinXe);
        panelTong.add(Box.createVerticalStrut(10));
        panelTong.add(nutXacNhan);

        dialogDangKy.add(panelTong);

        ActionListener logicAnHien = e -> {
            boolean laLaiXe = chonVaiTro.getSelectedItem().equals("lai_xe");
            panelThongTinXe.setVisible(laLaiXe);
            dialogDangKy.pack();
            dialogDangKy.setLocationRelativeTo(khungChinh);
        };

        chonVaiTro.addActionListener(logicAnHien);
        logicAnHien.actionPerformed(null);

        nutXacNhan.addActionListener(e -> {
            String ten = truongTen.getText();
            String mk = new String(truongMK.getPassword());
            String vaiTro = (String) chonVaiTro.getSelectedItem();

            String bienSo = vaiTro.equals("lai_xe") ? truongBienSo.getText() : "";
            String loaiXe = vaiTro.equals("lai_xe") ? (String) chonLoaiXe.getSelectedItem() : "";

            if (ten.isEmpty() || mk.isEmpty()) {
                JOptionPane.showMessageDialog(dialogDangKy,
                        "Không được để trống Tên / Mật khẩu.",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (MockAPIDuLieu.dangKyNguoiDung(ten, mk, vaiTro, bienSo, loaiXe)) {
                JOptionPane.showMessageDialog(dialogDangKy,
                        "Đăng ký thành công! Bạn có thể đăng nhập.",
                        "OK",
                        JOptionPane.INFORMATION_MESSAGE);
                dialogDangKy.dispose();
            } else {
                JOptionPane.showMessageDialog(dialogDangKy,
                        "Tên đăng nhập đã tồn tại.",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        dialogDangKy.setVisible(true);
    }

    public static void moManHinhDangNhap() {
        SwingUtilities.invokeLater(GiaoDienChinh::new);
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception ignored) {}

        moManHinhDangNhap();
    }
}

class RoundedBorder implements javax.swing.border.Border {
    private int radius;

    RoundedBorder(int r) { radius = r; }

    public Insets getBorderInsets(Component c) {
        return new Insets(radius + 1, radius + 1, radius + 2, radius);
    }

    public boolean isBorderOpaque() { return true; }

    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        g.drawRoundRect(x, y, w - 1, h - 1, radius, radius);
    }
}

class CustomBorderFactory {
    public static RoundedBorder createRoundedBorder(int radius) {
        return new RoundedBorder(radius);
    }
}
