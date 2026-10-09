package UDP;

/**
 * UDP String (cổng 2208) – Bài B6: KÝ TỰ XUẤT HIỆN NHIỀU LẦN NHẤT
 * Mã câu hỏi (qCode): KAmLHOdi ✅ AC thật
 *
 * [Mã câu hỏi (qCode): KAmLHOdi]. Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208. Yêu cầu là xây dựng một chương trình client tương tác với server:
 * a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN001;EE29C059"
 * b. Nhận thông điệp từ server theo định dạng "requestId;data". requestId là một chuỗi ngẫu nhiên duy nhất. data là chuỗi dữ liệu đầu vào cần xử lý. Ví dụ: "requestId;Qnc8d5x78aldSGWWmaAAjyg3"
 * c. Tìm kiếm ký tự xuất hiện nhiều nhất trong chuỗi và gửi lên server theo định dạng "requestId;ký tự xuất hiện nhiều nhất: các vị trí xuất hiện ký tự đó". Ví dụ: "requestId;8:4,9," (vị trí đếm từ 1, dấu phẩy cuối giữ nguyên).
 * d. Đóng socket và kết thúc chương trình.
 *
 * Ghi chú:
 * Chú ý: dấu phẩy CUỐI CÙNG PHẢI GIỮ NGUYÊN như ví dụ "8:4,9,". Vị trí tính từ 1.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class String06_KyTuXuatHienNhieuLanNhat {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2208;
        String code = ";B23DCCN139;KAmLHOdi";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String s = new String(nhan.getData(), 0, nhan.getLength());
        String[] parts = s.trim().split(";");
        String requestId = parts[0];
        String data = parts[1];
        char best = ' ';
        int max = 0;
        for (char c : data.toCharArray()) {
            int dem = 0;
            for (char d : data.toCharArray()) {
                if (d == c) dem++;
            }
            if (dem > max) {
                max = dem;
                best = c;
            }
        }
        StringBuilder sb = new StringBuilder(requestId + ";" + best + ":");
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == best) sb.append(i + 1).append(",");
        }
        String kq = sb.toString();
        socket.send(new DatagramPacket(kq.getBytes(), kq.length(), sA, port));
        socket.close();
    }
}
