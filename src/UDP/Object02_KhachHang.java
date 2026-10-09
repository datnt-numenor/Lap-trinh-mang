package UDP;

/**
 * UDP Object (cổng 2209) – Bài C2: KHÁCH HÀNG
 * Mã câu hỏi (qCode): 8nxPat2M
 *
 * [Mã câu hỏi (qCode): 8nxPat2M]. Thông tin khách hàng được yêu cầu thay đổi định dạng lại cho phù hợp với khu vực, cụ thể:
 * a. Tên khách hàng cần được chuẩn hóa theo định dạng mới. Ví dụ: nguyen van hai duong -> DUONG, Nguyen Van Hai
 * b. Ngày sinh của khách hàng đang ở dạng mm-dd-yyyy, cần được chuyển thành định dạng dd/mm/yyyy. Ví dụ: 10-11-2012 -> 11/10/2012
 * c. Tài khoản khách hàng được tạo từ các chữ cái in thường được sinh tự động từ họ tên khách hàng. Ví dụ: nguyen van hai duong -> nvhduong
 * Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2209. Yêu cầu là xây dựng một chương trình client giao tiếp với server theo mô tả sau:
 * a. Đối tượng trao đổi là thể hiện của lớp UDP.Customer được mô tả như sau
 * - Tên đầy đủ của lớp: UDP.Customer
 * - Các thuộc tính: id String, code String, name String, dayOfBirth String, userName String
 * - Một Hàm khởi tạo với đầy đủ các thuộc tính được liệt kê ở trên
 * - Trường dữ liệu: private static final long serialVersionUID = 20151107;
 * b. Client giao tiếp với server theo các bước
 * - Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN001;EE29C059"
 * - Nhận thông điệp chứa: 08 byte đầu chứa chuỗi requestId, các byte còn lại chứa một đối tượng là thể hiện của lớp Customer từ server. Trong đó, các thuộc tính id, code, name, dayOfBirth đã được thiết lập sẵn.
 * - Yêu cầu thay đổi thông tin các thuộc tính như yêu cầu ở trên và gửi lại đối tượng khách hàng đã được sửa đổi lên server với cấu trúc: 08 byte đầu chứa chuỗi requestId và các byte còn lại chứa đối tượng Customer đã được sửa đổi.
 * - Đóng socket và kết thúc chương trình.
 *
 * Ghi chú:
 * Chú ý: ngày d[1]/d[0]/d[2] (đổi chỗ ngày và tháng).
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class Object02_KhachHang {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2209;
        String code = ";B23DCCN139;8nxPat2M";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String requestId = new String(nhan.getData(), 0, 8);
        Customer c = (Customer) new ObjectInputStream(
        new ByteArrayInputStream(nhan.getData(), 8, nhan.getLength() - 8)).readObject();
        String[] w = c.name.trim().split("\\s+");
        int n = w.length;
        String ten = "", user = "";
        for (int i = 0; i < n - 1; i++) {
            ten += w[i].substring(0, 1).toUpperCase() + w[i].substring(1).toLowerCase() + " ";
            user += Character.toLowerCase(w[i].charAt(0));
        }
        c.name = (w[n - 1].toUpperCase() + ", " + ten).trim();
        c.userName = user + w[n - 1].toLowerCase();
        String[] d = c.dayOfBirth.split("-");
        c.dayOfBirth = d[1] + "/" + d[0] + "/" + d[2];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(requestId.getBytes());
        new ObjectOutputStream(baos).writeObject(c);
        socket.send(new DatagramPacket(baos.toByteArray(), baos.size(), sA, port));
        socket.close();
    }
}
