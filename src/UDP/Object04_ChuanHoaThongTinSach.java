package UDP;

/**
 * UDP Object (cổng 2209) – Bài C4: CHUẨN HOÁ THÔNG TIN SÁCH
 * Mã câu hỏi (qCode): LFACr5Bi
 *
 * [Mã câu hỏi (qCode): LFACr5Bi]. Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2209. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản sau: Đối tượng trao đổi là thể hiện của lớp UDP.Book được mô tả:
 * - Tên đầy đủ lớp: UDP.Book
 * - Các thuộc tính: id (String), title (String), author (String), isbn (String), publishDate (String)
 * - Hàm khởi tạo: public Book(String id, String title, String author, String isbn, String publishDate)
 * - Trường dữ liệu: private static final long serialVersionUID = 20251107L
 * Thực hiện:
 * a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B23DCCN005;eQkvAeId"
 * b. Nhận thông điệp chứa: 08 byte đầu chứa chuỗi requestId, các byte còn lại chứa một đối tượng là thể hiện của lớp Book từ server. Trong đó, các thuộc tính id, title, author, isbn, và publishDate đã được thiết lập sẵn.
 * c. Thực hiện:
 * 1. Chuẩn hóa title: Với mỗi từ, viết hoa chữ cái đầu tiên, viết thường các chữ cái còn lại.
 * 2. Chuẩn hóa author theo định dạng "Họ, Tên": Họ là từ đầu tiên, viết hoa tất cả; Tên là các từ còn lại, mỗi từ viết hoa chữ cái đầu tiên, viết thường các chữ cái còn lại, giữa mỗi từ chỉ có đúng một dấu cách.
 * 3. Chuẩn hóa mã ISBN theo định dạng "978-3-16-148410-0"
 * 4. Chuyển đổi publishDate từ yyyy-mm-dd sang mm/yyyy.
 * d. Gửi lại đối tượng đã được chuẩn hóa về server với cấu trúc: 08 byte đầu chứa chuỗi requestId và các byte còn lại chứa đối tượng Book đã được sửa đổi.
 * e. Đóng socket và kết thúc chương trình.
 * Test ví dụ: Input title "wAsSegd NCAxhCY" → Output "Wassegd Ncaxhcy". Input author "cFPISv tlpiAvv TzfHf" → Output "CFPISV, Tlpiavv Tzfhf". ISBN 13 chữ số → xxx-x-xx-xxxxxx-x.
 * Lưu ý: Hệ thống còn có mã câu hỏi qAuSMw4o cùng đề này — chỉ đổi qCode.
 *
 * Ghi chú:
 * Chú ý: isbn cắt theo substring(0,3), (3,4), (4,6), (6,12), (12).
 * publishDate split bằng [-\\s]+ để xử lý khoảng trắng lạ.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class Object04_ChuanHoaThongTinSach {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2209;
        String code = ";B23DCCN139;LFACr5Bi";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String requestId = new String(nhan.getData(), 0, 8);
        Book b = (Book) new ObjectInputStream(
        new ByteArrayInputStream(nhan.getData(), 8, nhan.getLength() - 8)).readObject();
        String[] t = b.title.trim().split("\\s+");
        String[] a = b.author.trim().split("\\s+");
        String title = "", ten = "";
        for (String x : t) title += x.substring(0, 1).toUpperCase() + x.substring(1).toLowerCase() + " ";
        for (int i = 1; i < a.length; i++) ten += a[i].substring(0, 1).toUpperCase() + a[i].substring(1).toLowerCase() + " ";
        b.title = title.trim();
        b.author = (a[0].toUpperCase() + ", " + ten).trim();
        String s = b.isbn;
        b.isbn = s.substring(0, 3) + "-" + s.substring(3, 4) + "-" + s.substring(4, 6) + "-" + s.substring(6, 12) + "-" + s.substring(12);
        String[] d = b.publishDate.trim().split("[-\\s]+");
        b.publishDate = d[1] + "/" + d[0];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(requestId.getBytes());
        new ObjectOutputStream(baos).writeObject(b);
        socket.send(new DatagramPacket(baos.toByteArray(), baos.size(), sA, port));
        socket.close();
    }
}
