package UDP;

/**
 * UDP Object (cổng 2209) – Bài C1: QUẢN LÝ SINH VIÊN
 * Mã câu hỏi (qCode): 8VdcIxPQ
 *
 * [Mã câu hỏi (qCode): 8VdcIxPQ]. Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2209. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản sau: Đối tượng trao đổi là thể hiện của lớp UDP.Student được mô tả:
 * - Tên đầy đủ lớp: UDP.Student
 * - Các thuộc tính: id String, code String, name String, email String
 * - 02 Hàm khởi tạo: public Student(String id, String code, String name, String email) và public Student(String code)
 * - Trường dữ liệu: private static final long serialVersionUID = 20171107
 * Thực hiện:
 * a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN001;EE29C059"
 * b. Nhận thông điệp chứa: 08 byte đầu chứa chuỗi requestId, các byte còn lại chứa một đối tượng là thể hiện của lớp Student từ server. Trong đó, các thông tin được thiết lập gồm id và name.
 * c. Yêu cầu:
 * - Chuẩn hóa tên theo quy tắc: Chữ cái đầu tiên in hoa, các chữ cái còn lại in thường và gán lại thuộc tính name của đối tượng
 * - Tạo email ptit.edu.vn từ tên người dùng bằng cách lấy tên và các chữ cái bắt đầu của họ và tên đệm. Ví dụ: nguyen van tuan nam -> namnvt@ptit.edu.vn. Gán giá trị này cho thuộc tính email của đối tượng nhận được
 * - Gửi thông điệp chứa đối tượng xử lý ở bước c lên Server với cấu trúc: 08 byte đầu chứa chuỗi requestId và các byte còn lại chứa đối tượng Student đã được sửa đổi.
 * d. Đóng socket và kết thúc chương trình.
 * Lưu ý: Hệ thống còn có mã câu hỏi 8yXucQXb cùng đề này — chỉ đổi qCode.
 *
 * Ghi chú:
 * Nếu WA: kiểm tra trim() trước split, và baos.write() trước ObjectOutputStream.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class Object01_QuanLySinhVien {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2209;
        String code = ";B23DCCN139;8VdcIxPQ";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String requestId = new String(nhan.getData(), 0, 8);
        Student st = (Student) new ObjectInputStream(
        new ByteArrayInputStream(nhan.getData(), 8, nhan.getLength() - 8)).readObject();
        String[] w = st.name.trim().split("\\s+");
        String ten = "", email = w[w.length - 1].toLowerCase();
        for (int i = 0; i < w.length; i++) {
            ten += w[i].substring(0, 1).toUpperCase() + w[i].substring(1).toLowerCase() + " ";
            if (i < w.length - 1) email += Character.toLowerCase(w[i].charAt(0));
        }
        st.name = ten.trim();
        st.email = email + "@ptit.edu.vn";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(requestId.getBytes());
        new ObjectOutputStream(baos).writeObject(st);
        socket.send(new DatagramPacket(baos.toByteArray(), baos.size(), sA, port));
        socket.close();
    }
}
