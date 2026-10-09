package UDP;

/**
 * UDP String (cổng 2208) – Bài B2: TỔNG HAI SỐ NHỊ PHÂN
 * Mã câu hỏi (qCode): lIQVug9S
 *
 * [Mã câu hỏi (qCode): lIQVug9S]. Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2208. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
 * a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN000;XbYdNZ3".
 * b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;b1,b2", trong đó: requestId là chuỗi ngẫu nhiên duy nhất. b1 là số nhị phân thứ nhất. b2 là số nhị phân thứ hai. Ví dụ: requestId;0100011111001101,1101000111110101
 * c. Thực hiện tính tổng hai số nhị phân nhận được, chuyển về dạng thập phân và gửi lên server theo định dạng "requestId;sum". Kết quả: requestId;72130
 * d. Đóng socket và kết thúc chương trình.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class String02_TongHaiSoNhiPhan {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2208;
        String code = ";B23DCCN139;lIQVug9S";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String s = new String(nhan.getData(), 0, nhan.getLength());
        String[] parts = s.trim().split("[;,]");
        String requestId = parts[0];
        long b1 = Long.parseLong(parts[1].trim(), 2);
        long b2 = Long.parseLong(parts[2].trim(), 2);
        String kq = requestId + ";" + (b1 + b2);
        socket.send(new DatagramPacket(kq.getBytes(), kq.length(), sA, port));
        socket.close();
    }
}
