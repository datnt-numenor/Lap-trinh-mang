package UDP;

/**
 * UDP Data (cổng 2207) – Bài A6: TÌM SỐ CÒN THIẾU
 * Mã câu hỏi (qCode): wAKCZwjj
 *
 * [Mã câu hỏi (qCode): wAKCZwjj]. Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
 * a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN001;73457A17"
 * b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;n;A1,A2,...Am", với: requestId là chuỗi ngẫu nhiên duy nhất. n là một số ngẫu nhiên nhỏ hơn 100. A1, A2 ... Am (m <= n) là các giá trị ngẫu nhiên nhỏ hơn hoặc bằng n và có thể trùng nhau. Ví dụ: requestId;10;2,3,5,6,5
 * c. Tìm kiếm các giá trị còn thiếu và gửi lên server theo định dạng "requestId;B1,B2,...,Bm". Ví dụ: requestId;1,4,7,8,9,10
 * d. Đóng socket và kết thúc chương trình.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class Data06_TimSoConThieu {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2207;
        String code = ";B23DCCN139;wAKCZwjj";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String s = new String(nhan.getData(), 0, nhan.getLength());
        String[] parts = s.trim().split(";");
        String requestId = parts[0];
        int n = Integer.parseInt(parts[1].trim());
        String[] daBan = new String[0];
        if (parts.length > 2 && !parts[2].trim().isEmpty()) daBan = parts[2].split(",");
        StringBuilder sb = new StringBuilder();
        int dem = 0;
        for (int i = 1; i <= n; i++) {
            boolean coBan = false;
            for (String x : daBan) {
                if (Integer.parseInt(x.trim()) == i) coBan = true;
            }
            if (!coBan) {
                if (dem > 0) sb.append(",");
                sb.append(i);
                dem++;
            }
        }
        String kq = requestId + ";" + sb.toString();
        socket.send(new DatagramPacket(kq.getBytes(), kq.length(), sA, port));
        socket.close();
    }
}
