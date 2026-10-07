package TCP;

/**
 * Object Stream (cổng 2209) – Bài 4: Sản phẩm Laptop v2
 * Mã câu hỏi (qCode): W7S23nSu
 *
 * Mã câu hỏi: W7S23nSu · Cổng: 2209
 * Thông tin sản phẩm laptop vì một lý do nào đó đã bị sửa đổi thành không đúng, cụ thể:
 * a. Tên sản phẩm bị đổi ngược từ đầu tiên và từ cuối cùng. Ví dụ: "lenovo thinkpad T520" bị chuyển thành "T520 thinkpad lenovo"
 * b. Số lượng sản phẩm cũng bị đảo ngược các chữ số. Ví dụ: từ 358 thành 853
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2209 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng đối tượng (ObjectInputStream / ObjectOutputStream) để gửi/nhận và sửa các thông tin bị sai của sản phẩm. Chi tiết dưới đây:
 * a. Đối tượng trao đổi là thể hiện của lớp Laptop được mô tả như sau:
 * • Tên đầy đủ của lớp: TCP.Laptop
 * • Các thuộc tính: id int, code String, name String, quantity int
 * • Hàm khởi tạo đầy đủ các thuộc tính được liệt kê ở trên
 * • Trường dữ liệu: private static final long serialVersionUID = 20150711L;
 * b. Tương tác với server theo kịch bản:
 * 1) Gửi đối tượng là chuỗi chứa mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode"
 * 2) Nhận một đối tượng là thể hiện của lớp TCP.Laptop từ server
 * 3) Sửa lại tên (đảo vị trí từ đầu và từ cuối) và số lượng (đảo ngược chữ số). Gửi đối tượng đã sửa lên server
 * 4) Đóng socket và kết thúc chương trình
 * ⚠ Đề dịch ngược từ code SanPham.java — logic giống bài 1, khác qCode và serialVersionUID
 */

import java.util.*; import java.io.*; import java.net.*;

public class Object04_SanPhamLaptopV2 {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2209);
        socket.setSoTimeout(5000);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        out.writeObject("B23DCCN139;W7S23nSu"); // ← chỉ khác chỗ này
        out.flush();
        Laptop lp = (Laptop) in.readObject();
        String[] words = lp.getName().trim().split("\\s+");
        String tmp = words[0];
        words[0] = words[words.length - 1];
        words[words.length - 1] = tmp;
        lp.setName(String.join(" ", words));
        lp.setQuantity(Integer.parseInt(
        new StringBuilder(String.valueOf(lp.getQuantity())).reverse().toString()));
        out.writeObject(lp);
        out.flush();
        in.close(); out.close(); socket.close();
    }
}
