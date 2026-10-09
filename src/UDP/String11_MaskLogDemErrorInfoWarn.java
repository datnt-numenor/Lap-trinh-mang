package UDP;

/**
 * UDP String (cổng 2208) – Bài B11: MASK LOG + ĐẾM ERROR/INFO/WARN (BÀI MỚI TỪ HỆ THỐNG)
 * Mã câu hỏi (qCode): cập nhật sau
 *
 * Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2208.
 * a. Gửi thông điệp theo định dạng ";studentCode;qCode".
 * b. Nhận thông điệp theo định dạng "requestId;data" trong đó data là nhiều dòng log nối bằng ||.
 * c. Che email=... → email=[EMAIL], phone=... → phone=[PHONE], token=... → token=[TOKEN]. Đếm số dòng bắt đầu bằng ERROR, INFO, WARN.
 * d. Gửi theo định dạng: "requestId;maskedLog##ERROR=n;INFO=n;WARN=n"
 * Chú ý quan trọng:
 * - Tách dòng log bằng split("\\|\\|") — phải escape đúng
 * - lines.length KHÔNG có dấu () — là thuộc tính mảng, không phải method
 * - Nối lại bằng || (thêm trước dòng từ index 1 trở đi)
 * - Phần thống kê nối sau ## (hai dấu thăng)
 *
 * Ghi chú:
 * Đổi qCode_moi thành mã câu hỏi thực tế khi thi. Port 2208.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class String11_MaskLogDemErrorInfoWarn {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2208;
        // ĐỔI qCode theo đề thực tế
        String code = ";B23DCCN139;qCode_moi";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        // Nhận: requestId;data (data = nhiều dòng log nối bằng ||)
        byte[] buf = new byte[65535];
        DatagramPacket nhan = new DatagramPacket(buf, buf.length);
        socket.receive(nhan);
        String received = new String(nhan.getData(), 0, nhan.getLength()).trim();
        String[] parts = received.split(";", 2);
        String requestId = parts[0];
        String data = parts[1];
        // Xử lý: mask email/phone/token, đếm level
        String[] lines = data.split("\\|\\|");
        int cntError = 0, cntInfo = 0, cntWarn = 0;
        StringBuilder maskedLog = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.startsWith("ERROR")) cntError++;
            else if (line.startsWith("INFO")) cntInfo++;
            else if (line.startsWith("WARN")) cntWarn++;
            line = line.replaceAll("email=\\S+", "email=[EMAIL]");
            line = line.replaceAll("phone=\\S+", "phone=[PHONE]");
            line = line.replaceAll("token=\\S+", "token=[TOKEN]");
            if (i > 0) maskedLog.append("||");
            maskedLog.append(line);
        }
        // Gửi: requestId;maskedLog##ERROR=n;INFO=n;WARN=n
        String result = requestId + ";" + maskedLog.toString()
        + "##ERROR=" + cntError + ";INFO=" + cntInfo + ";WARN=" + cntWarn;
        DatagramPacket gui2 = new DatagramPacket(result.getBytes(), result.length(), sA, port);
        socket.send(gui2);
        socket.close();
    }
}
