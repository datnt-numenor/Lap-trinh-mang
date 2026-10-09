package UDP;

/**
 * UDP String (cổng 2208) – Bài B4: LỌC KÝ TỰ
 * Mã câu hỏi (qCode): GfeNSBMT
 *
 * [Mã câu hỏi (qCode): GfeNSBMT]. Loại bỏ ký tự đặc biệt, số, ký tự trùng và giữ nguyên thứ tự xuất hiện. Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
 * a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN001;06D6800D"
 * b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;strInput". requestId là chuỗi ngẫu nhiên duy nhất. strInput là chuỗi thông điệp cần xử lý.
 * c. Thực hiện loại bỏ ký tự đặc biệt, số, ký tự trùng và giữ nguyên thứ tự xuất hiện của chúng. Gửi thông điệp lên server theo định dạng "requestId;strOutput".
 * d. Đóng socket và kết thúc chương trình.
 *
 * Ghi chú:
 * Nếu WA: đề không nói rõ chữ hoa và chữ thường có tính là trùng nhau không. Code đang coi "a" và "A" là hai ký tự khác nhau.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class String04_LocKyTu {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2208;
        String code = ";B23DCCN139;GfeNSBMT";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String s = new String(nhan.getData(), 0, nhan.getLength());
        String[] parts = s.trim().split(";");
        String requestId = parts[0];
        StringBuilder sb = new StringBuilder();
        for (char c : parts[1].toCharArray()) {
            if (Character.isLetter(c) && sb.indexOf(String.valueOf(c)) < 0) sb.append(c);
        }
        String kq = requestId + ";" + sb.toString();
        socket.send(new DatagramPacket(kq.getBytes(), kq.length(), sA, port));
        socket.close();
    }
}
