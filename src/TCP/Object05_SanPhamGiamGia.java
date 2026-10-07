package TCP;

/**
 * Object Stream (cổng 2209) – Bài 5: Sản phẩm giảm giá
 * Mã câu hỏi (qCode): 151GNZvT
 *
 * Mã câu hỏi: 151GNZvT · Cổng: 2209
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2209 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây).
 * Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng đối tượng (ObjectOutputStream/ObjectInputStream) theo kịch bản dưới đây:
 * Biết lớp TCP.Product gồm các thuộc tính (id int, name String, price double, discount int) và private static final long serialVersionUID = 20231107;
 * a. Gửi đối tượng là một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;1E08CA31"
 * b. Nhận một đối tượng là thể hiện của lớp TCP.Product từ server.
 * c. Tính toán giá trị giảm giá theo price theo nguyên tắc: Giá trị giảm giá (discount) bằng tổng các chữ số trong phần nguyên của giá sản phẩm (price). Thực hiện gán giá trị cho thuộc tính discount và gửi đối tượng nhận được lên server.
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.util.*; import java.io.*; import java.net.*;

public class Object05_SanPhamGiamGia {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2209);
        socket.setSoTimeout(5000);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        out.writeObject("B23DCCN139;151GNZvT");
        out.flush();
        Product p = (Product) in.readObject();
        // discount = tổng các chữ số trong phần nguyên của price
        int intPart = (int) p.getPrice();
        int discount = 0;
        while (intPart > 0) {
            discount += intPart % 10;
            intPart /= 10;
        }
        p.setDiscount(discount);
        out.writeObject(p);
        out.flush();
        in.close(); out.close(); socket.close();
    }
}
