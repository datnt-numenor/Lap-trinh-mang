package UDP;

/**
 * UDP String (cổng 2208) – Bài B7: CHUẨN HOÁ XÂU KÝ TỰ
 * Mã câu hỏi (qCode): i28U6aGi / tmczgSLy / NRVwBVvx ✅ AC thật
 *
 * [Mã câu hỏi (qCode): i28U6aGi]. Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
 * a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN001;5B35BCC1"
 * b. Nhận thông điệp từ server theo định dạng "requestId;data". requestId là một chuỗi ngẫu nhiên duy nhất. data là chuỗi dữ liệu cần xử lý.
 * c. Xử lý chuẩn hóa chuỗi theo nguyên tắc: (i) Ký tự đầu tiên của từng từ trong chuỗi là in hoa. (ii) Các ký tự còn lại của chuỗi là in thường. Gửi thông điệp chứa chuỗi đã được chuẩn hóa lên server theo định dạng "requestId;data".
 * d. Đóng socket và kết thúc chương trình.
 * Lưu ý: Hệ thống còn có mã tmczgSLy và NRVwBVvx cùng đề này: chỉ đổi qCode.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class String07_ChuanHoaXauKyTu {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2208;
        String code = ";B23DCCN139;i28U6aGi";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String s = new String(nhan.getData(), 0, nhan.getLength());
        String[] parts = s.trim().split(";");
        String requestId = parts[0];
        String[] words = parts[1].trim().split("\\s+");
        StringBuilder sb = new StringBuilder(requestId + ";");
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            sb.append(w.substring(0, 1).toUpperCase()).append(w.substring(1).toLowerCase());
            if (i < words.length - 1) sb.append(" ");
        }
        String kq = sb.toString();
        socket.send(new DatagramPacket(kq.getBytes(), kq.length(), sA, port));
        socket.close();
    }
}
