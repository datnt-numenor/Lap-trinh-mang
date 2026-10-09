package UDP;

/**
 * UDP String (cổng 2208) – Bài B10: LỌC KÝ TỰ + LOẠI TRÙNG (BÀI MỚI TỪ HỆ THỐNG)
 * Mã câu hỏi (qCode): cập nhật sau
 *
 * Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2208.
 * a. Gửi thông điệp theo định dạng ";studentCode;qCode".
 * b. Nhận thông điệp theo định dạng "requestId;strInput".
 * c. Loại bỏ ký tự đặc biệt và số (chỉ giữ chữ cái), loại bỏ ký tự đã xuất hiện trước đó, giữ nguyên thứ tự xuất hiện lần đầu. Ví dụ: "a1b!a2c" → "abc". Gửi theo định dạng "requestId;strOutput".
 * d. Đóng socket và kết thúc chương trình.
 * Khác B4 ở chỗ: B10 dùng ArrayList<Character> seen để kiểm tra trùng (ArrayList.contains thay vì sb.indexOf).
 *
 * Ghi chú:
 * Đổi qCode_moi thành mã câu hỏi thực tế khi thi. Port 2208.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class String10_LocKyTuLoaiTrung {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2208;
        // ĐỔI qCode theo đề thực tế
        String code = ";B23DCCN139;qCode_moi";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        // Nhận: requestId;strInput
        byte[] buf = new byte[4096];
        DatagramPacket nhan = new DatagramPacket(buf, buf.length);
        socket.receive(nhan);
        String received = new String(nhan.getData(), 0, nhan.getLength()).trim();
        String[] parts = received.split(";", 2);
        String requestId = parts[0];
        String strInput = parts[1];
        // Xử lý: giữ chữ cái, loại trùng, giữ thứ tự xuất hiện
        ArrayList<Character> seen = new ArrayList<Character>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strInput.length(); i++) {
            char c = strInput.charAt(i);
            if (Character.isLetter(c) && !seen.contains(c)) {
                seen.add(c);
                sb.append(c);
            }
        }
        String strOutput = sb.toString();
        // Gửi: requestId;strOutput
        String result = requestId + ";" + strOutput;
        DatagramPacket gui2 = new DatagramPacket(result.getBytes(), result.length(), sA, port);
        socket.send(gui2);
        socket.close();
    }
}
