package TCP;

/**
 * Object Stream (cổng 2209) – Bài 1: Laptop
 * Mã câu hỏi (qCode): mM5m0V4s
 *
 * Mã câu hỏi: mM5m0V4s · Cổng: 2209
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2209 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng đối tượng (ObjectInputStream / ObjectOutputStream) thực hiện gửi/nhận đối tượng máy tính xách tay và thay đổi thông tin. Cụ thể:
 * a. Đối tượng trao đổi là thể hiện của lớp Laptop được mô tả như sau:
 * • Tên đầy đủ của lớp: TCP.Laptop
 * • Các thuộc tính: id int, code String, name String, quantity int
 * • Hàm khởi tạo đầy đủ các thuộc tính được liệt kê ở trên
 * • Trường dữ liệu: private static final long serialVersionUID = 20150711L;
 * b. Tương tác với server theo kịch bản dưới đây:
 * 1) Gửi đối tượng là một chuỗi gồm mã sinh viên và mã câu hỏi ở định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;F2DA54F3"
 * 2) Nhận một đối tượng là thể hiện của lớp Laptop từ server với các thông tin đã được thiết lập
 * 3) Thay đổi thông tin theo các yêu cầu dưới đây và gán vào các thuộc tính tương ứng:
 * - Đảo vị trí từ đầu tiên và từ cuối cùng trong thuộc tính name. Ví dụ: Laptop Acer Predator Helios → Helios Acer Predator Laptop
 * - Đảo ngược các chữ số trong thuộc tính quantity. Ví dụ: 358 → 853
 * Gửi đối tượng đã được sửa đổi lên server.
 * 4) Đóng socket và kết thúc chương trình.
 */

import java.util.*; import java.io.*; import java.net.*;

public class Object01_Laptop {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2209);
        socket.setSoTimeout(5000);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        out.writeObject("B23DCCN139;mM5m0V4s");
        out.flush();
        Laptop lp = (Laptop) in.readObject();
        // Đảo từ đầu và từ cuối trong name
        String[] words = lp.getName().split(" ");
        String tmp = words[0];
        words[0] = words[words.length - 1];
        words[words.length - 1] = tmp;
        lp.setName(String.join(" ", words));
        // Đảo ngược chữ số trong quantity
        lp.setQuantity(Integer.parseInt(
        new StringBuilder(String.valueOf(lp.getQuantity())).reverse().toString()));
        out.writeObject(lp);
        out.flush();
        in.close(); out.close(); socket.close();
    }
}
