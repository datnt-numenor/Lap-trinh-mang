package UDP;

/**
 * UDP String (cổng 2208) – Bài B3: ĐẾM SỐ LẦN XUẤT HIỆN
 * Mã câu hỏi (qCode): vSgxl3HQ / 1CSILxVz
 *
 * [Mã câu hỏi (qCode): vSgxl3HQ]. Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2208. Yêu cầu xây dựng chương trình client trao đổi thông tin với server theo kịch bản:
 * a. Gửi một thông điệp chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN001;9F8C2D3A".
 * b. Nhận một thông điệp từ server theo định dạng "requestId;data", với: requestId là chuỗi ngẫu nhiên duy nhất. data là một chuỗi ký tự liên tiếp cần xử lý. Ví dụ: "requestId;aaabbbccdaa"
 * c. Xử lý chuỗi bằng cách đếm số lượng ký tự và gom chúng lại theo định dạng "so_lan_ky_tu". Gửi kết quả về server theo định dạng "requestId;processedData". Ví dụ: Với chuỗi "aaabbbccdaa", kết quả sẽ là: "requestId;5a3b2c1d" (đếm trong cả xâu, liệt kê theo thứ tự xuất hiện lần đầu).
 * d. Đóng socket và kết thúc chương trình.
 * Lưu ý: đếm trong CẢ XÂU (5a = "aaa" đầu + "aa" cuối). Hệ thống còn có mã 1CSILxVz cùng đề.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class String03_DemSoLanXuatHien {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2208;
        String code = ";B23DCCN139;vSgxl3HQ";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String s = new String(nhan.getData(), 0, nhan.getLength());
        String[] parts = s.trim().split(";");
        String requestId = parts[0];
        String data = parts[1];
        String daDem = "";
        StringBuilder sb = new StringBuilder();
        for (char c : data.toCharArray()) {
            if (daDem.indexOf(c) < 0) {
                daDem += c;
                int dem = 0;
                for (char d : data.toCharArray()) {
                    if (d == c) dem++;
                }
                sb.append(dem).append(c);
            }
        }
        String kq = requestId + ";" + sb.toString();
        socket.send(new DatagramPacket(kq.getBytes(), kq.length(), sA, port));
        socket.close();
    }
}
