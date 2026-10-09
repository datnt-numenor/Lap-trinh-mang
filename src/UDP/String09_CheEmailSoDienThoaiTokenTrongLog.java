package UDP;

/**
 * UDP String (cổng 2208) – Bài B9: CHE EMAIL, SỐ ĐIỆN THOẠI, TOKEN TRONG LOG (HAI PHA)
 * Mã câu hỏi (qCode): zFtVSq8y
 *
 * [Mã câu hỏi (qCode): zFtVSq8y]. Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2208 theo cơ chế hai pha.
 * a. Gửi datagram đầu tiên chứa chuỗi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN001;EE29C059".
 * b. Nhận phản hồi từ server theo định dạng "requestId;data". Ví dụ: "rBhDz2JJ;INFO user=minh email=minh0@example.com phone=0309453907 token=bDzj8Olxc9 action=refund".
 * c. Che email, số điện thoại và token theo quy tắc [EMAIL], [PHONE], token=[TOKEN], giữ nguyên thứ tự các dòng log.
 * d. Gửi datagram nộp kết quả theo định dạng "requestId;answer". Ví dụ: "rBhDz2JJ;INFO user=minh email=[EMAIL] phone=[PHONE] token=[TOKEN] action=refund".
 * e. Đóng kết nối hoặc kết thúc client sau khi nộp kết quả.
 * Chú ý: split(";", 2) để không cắt nhầm dấu ; trong nội dung log. Buffer 4096 byte vì log nhiều dòng.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class String09_CheEmailSoDienThoaiTokenTrongLog {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2208;
        String code = ";B23DCCN139;zFtVSq8y";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String s = new String(nhan.getData(), 0, nhan.getLength());
        String[] parts = s.trim().split(";", 2);
        String requestId = parts[0];
        String r = parts[1].replaceAll("[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+", "[EMAIL]");
        r = r.replaceAll("phone=[^\\s,;]+", "phone=[PHONE]");
        r = r.replaceAll("token=[^\\s,;]+", "token=[TOKEN]");
        String kq = requestId + ";" + r;
        socket.send(new DatagramPacket(kq.getBytes(), kq.length(), sA, port));
        socket.close();
    }
}
