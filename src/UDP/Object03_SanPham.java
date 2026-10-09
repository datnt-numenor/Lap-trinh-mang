package UDP;

/**
 * UDP Object (cổng 2209) – Bài C3: SẢN PHẨM
 * Mã câu hỏi (qCode): kZqFKEDL
 *
 * [Mã câu hỏi (qCode): kZqFKEDL]. Thông tin sản phẩm vì một lý do nào đó đã bị sửa đổi thành không đúng, cụ thể:
 * a. Tên sản phẩm bị đổi ngược từ đầu tiên và từ cuối cùng, ví dụ: "lenovo thinkpad T520" bị chuyển thành "T520 thinkpad lenovo"
 * b. Số lượng sản phẩm cũng bị đảo ngược giá trị, ví dụ từ 9981 thành 1899
 * Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2209. Yêu cầu là xây dựng một chương trình client giao tiếp với server để gửi/nhận các sản phẩm theo mô tả dưới đây:
 * a. Đối tượng trao đổi là thể hiện của lớp Product được mô tả như sau
 * - Tên đầy đủ của lớp: UDP.Product
 * - Các thuộc tính: id String, code String, name String, quantity int
 * - Một hàm khởi tạo có đầy đủ các thuộc tính được liệt kê ở trên
 * - Trường dữ liệu: private static final long serialVersionUID = 20161107;
 * b. Giao tiếp với server theo kịch bản
 * - Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN001;EE29C059"
 * - Nhận thông điệp chứa: 08 byte đầu chứa chuỗi requestId, các byte còn lại chứa một đối tượng là thể hiện của lớp Product từ server. Trong đối tượng này, các thuộc tính id, name và quantity đã được thiết lập giá trị.
 * - Sửa các thông tin sai của đối tượng về tên và số lượng như mô tả ở trên và gửi đối tượng vừa được sửa đổi lên server theo cấu trúc: 08 byte đầu chứa chuỗi requestId và các byte còn lại chứa đối tượng Product đã được sửa đổi.
 * - Đóng socket và kết thúc chương trình.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class Object03_SanPham {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2209;
        String code = ";B23DCCN139;kZqFKEDL";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String requestId = new String(nhan.getData(), 0, 8);
        Product p = (Product) new ObjectInputStream(
        new ByteArrayInputStream(nhan.getData(), 8, nhan.getLength() - 8)).readObject();
        String[] w = p.name.trim().split("\\s+");
        String t = w[0];
        w[0] = w[w.length - 1];
        w[w.length - 1] = t;
        p.name = String.join(" ", w);
        p.quantity = Integer.parseInt(new StringBuilder(p.quantity + "").reverse().toString());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(requestId.getBytes());
        new ObjectOutputStream(baos).writeObject(p);
        socket.send(new DatagramPacket(baos.toByteArray(), baos.size(), sA, port));
        socket.close();
    }
}
