package UDP;

/**
 * UDP String (cổng 2208) – Bài B1: SẮP XẾP THEO TỪ ĐIỂN NGƯỢC
 * Mã câu hỏi (qCode): 9UfU4Vky
 *
 * [Mã câu hỏi (qCode): 9UfU4Vky]. Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2208. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
 * a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN009;EF56GH78"
 * b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;data", với: requestId là chuỗi ngẫu nhiên duy nhất. data là một chuỗi ký tự chứa nhiều từ, được phân cách bởi dấu cách. Ví dụ: "EF56GH78;The quick brown fox"
 * c. Sắp xếp các từ trong chuỗi theo thứ tự từ điển ngược (z đến a) và gửi thông điệp lên server theo định dạng "requestId;word1,word2,...,wordN". Ví dụ: Với data = "The quick brown fox", kết quả là: "EF56GH78;quick,fox,brown,The"
 * d. Đóng socket và kết thúc chương trình.
 * Chú ý: Khi so sánh theo thứ tự từ điển thì không phân biệt chữ hoa chữ thường, đưa tất cả về cùng 1 kiểu rồi mới so sánh thì mới AC.
 *
 * Ghi chú:
 * Nếu WA: thử bỏ toLowerCase() trong comparator (đổi thành y.compareTo(x)).
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class String01_SapXepTheoTuDienNguoc {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2208;
        String code = ";B23DCCN139;9UfU4Vky";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String s = new String(nhan.getData(), 0, nhan.getLength());
        String[] parts = s.trim().split(";");
        String requestId = parts[0];
        ArrayList<String> ds = new ArrayList<>(Arrays.asList(parts[1].trim().split("\\s+")));
        Collections.sort(ds, (x, y) -> y.toLowerCase().compareTo(x.toLowerCase()));
        String kq = requestId + ";" + String.join(",", ds);
        socket.send(new DatagramPacket(kq.getBytes(), kq.length(), sA, port));
        socket.close();
    }
}
