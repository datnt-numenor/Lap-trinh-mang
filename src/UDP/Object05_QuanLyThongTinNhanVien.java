package UDP;

/**
 * UDP Object (cổng 2209) – Bài C5: QUẢN LÝ THÔNG TIN NHÂN VIÊN
 * Mã câu hỏi (qCode): ySsumsIE
 *
 * [Mã câu hỏi (qCode): ySsumsIE]. Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2209. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản sau: Đối tượng trao đổi là thể hiện của lớp UDP.Employee được mô tả:
 * - Tên đầy đủ lớp: UDP.Employee
 * - Các thuộc tính: id (String), name (String), salary (double), hireDate (String)
 * - Hàm khởi tạo: public Employee(String id, String name, double salary, String hireDate)
 * - Trường dữ liệu: private static final long serialVersionUID = 20261107L
 * Thực hiện:
 * a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B23DCCN006;ITleSdqV"
 * b. Nhận thông điệp chứa: 08 byte đầu chứa chuỗi requestId, các byte còn lại chứa một đối tượng là thể hiện của lớp Employee từ server. Trong đó, các thuộc tính id, name, salary và hireDate đã được thiết lập sẵn.
 * c. Thực hiện:
 * - Chuẩn hóa name: viết hoa chữ cái đầu của mỗi từ, ví dụ "john doe" thành "John Doe".
 * - Tăng salary: tăng x% lương, với x bằng tổng các chữ số của năm sinh.
 * - Chuyển đổi hireDate từ định dạng yyyy-mm-dd sang dd/mm/yyyy. Ví dụ: "2023-07-15" thành "15/07/2023".
 * - Gửi lại đối tượng đã được chuẩn hóa về server với cấu trúc: 08 byte đầu chứa chuỗi requestId và các byte còn lại chứa đối tượng Employee đã được sửa đổi.
 * d. Đóng socket và kết thúc chương trình.
 *
 * Ghi chú:
 * Chú ý: chia 100.0 (không phải 100) để tránh integer division. Lấy năm từ hireDate (d[0]) để tính tổng chữ số.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class Object05_QuanLyThongTinNhanVien {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2209;
        String code = ";B23DCCN139;ySsumsIE";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String requestId = new String(nhan.getData(), 0, 8);
        Employee e = (Employee) new ObjectInputStream(
        new ByteArrayInputStream(nhan.getData(), 8, nhan.getLength() - 8)).readObject();
        String[] t = e.name.trim().split("\\s+");
        String ten = "";
        for (String x : t) ten += x.substring(0, 1).toUpperCase() + x.substring(1).toLowerCase() + " ";
        e.name = ten.trim();
        String[] d = e.hireDate.trim().split("-");
        e.hireDate = d[2] + "/" + d[1] + "/" + d[0];
        int tong = 0;
        for (char c : d[0].toCharArray()) tong += c - '0';
        e.salary = e.salary * (1 + tong / 100.0);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(requestId.getBytes());
        new ObjectOutputStream(baos).writeObject(e);
        socket.send(new DatagramPacket(baos.toByteArray(), baos.size(), sA, port));
        socket.close();
    }
}
