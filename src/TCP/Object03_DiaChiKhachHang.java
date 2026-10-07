package TCP;

/**
 * Object Stream (cổng 2209) – Bài 3: Địa chỉ khách hàng
 * Mã câu hỏi (qCode): XtWjagNp
 *
 * Mã câu hỏi: XtWjagNp · Cổng: 2209
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2209 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng đối tượng (ObjectOutputStream/ObjectInputStream) để gửi/nhận và chuẩn hóa thông tin địa chỉ của khách hàng.
 * Biết rằng lớp TCP.Address có các thuộc tính (id int, code String, addressLine String, city String, postalCode String) và trường dữ liệu private static final long serialVersionUID = 20180801L.
 * a. Gửi đối tượng là một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;A1B2C3D4"
 * b. Nhận một đối tượng là thể hiện của lớp TCP.Address từ server. Thực hiện chuẩn hóa thông tin addressLine bằng cách:
 * • Chuẩn hóa addressLine: Viết hoa chữ cái đầu mỗi từ, in thường các chữ còn lại, loại bỏ ký tự đặc biệt và khoảng trắng thừa (ví dụ: "123 nguyen!!! van cu" → "123 Nguyen Van Cu")
 * • Chuẩn hóa postalCode: Chỉ giữ lại số và ký tự "-" ví dụ: "123-456"
 * c. Gửi đối tượng đã được chuẩn hóa thông tin địa chỉ lên server.
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.util.*; import java.io.*; import java.net.*;

public class Object03_DiaChiKhachHang {
    static String chuanHoaWord(String s) {
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray())
        if (Character.isLetterOrDigit(c)) res.append(c);
        String clean = res.toString().toLowerCase();
        if (clean.isEmpty()) return "";
        return Character.toUpperCase(clean.charAt(0)) + clean.substring(1);
    }
    static String chuanHoaAddress(String s) {
        String[] parts = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            if (i > 0) sb.append(" ");
            sb.append(chuanHoaWord(parts[i]));
        }
        return sb.toString();
    }
    static String chuanHoaPostal(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray())
        if (Character.isDigit(c) || c == '-') sb.append(c);
        return sb.toString();
    }
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2209);
        socket.setSoTimeout(5000);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        out.writeObject("B23DCCN139;XtWjagNp");
        out.flush();
        Address ad = (Address) in.readObject();
        ad.setAddressLine(chuanHoaAddress(ad.getAddressLine()));
        ad.setPostalCode(chuanHoaPostal(ad.getPostalCode()));
        out.writeObject(ad);
        out.flush();
        in.close(); out.close(); socket.close();
    }
}
