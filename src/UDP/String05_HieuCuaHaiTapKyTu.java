package UDP;

/**
 * UDP String (cổng 2208) – Bài B5: HIỆU CỦA HAI TẬP KÝ TỰ
 * Mã câu hỏi (qCode): JQCO3izC
 *
 * [Mã câu hỏi (qCode): JQCO3izC]. Loại bỏ ký tự đặc biệt và ký tự trùng giữ nguyên thứ tự xuất hiện. Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
 * a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN001;B34D51E0"
 * b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;str1;str2". requestId là chuỗi ngẫu nhiên duy nhất. str1, str2 lần lượt là chuỗi thứ nhất và chuỗi thứ hai.
 * c. Loại bỏ các ký tự trong chuỗi thứ nhất mà xuất hiện trong chuỗi thứ hai, giữ nguyên thứ tự xuất hiện. Gửi thông điệp là một chuỗi lên server theo định dạng "requestId;strOutput".
 * d. Đóng socket và kết thúc chương trình.
 *
 * Ghi chú:
 * Nếu WA: thử bỏ thêm ký tự trùng và ký tự không phải chữ trong kết quả.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class String05_HieuCuaHaiTapKyTu {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2208;
        String code = ";B23DCCN139;JQCO3izC";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String s = new String(nhan.getData(), 0, nhan.getLength());
        String[] parts = s.trim().split(";");
        String requestId = parts[0];
        String str1 = parts[1];
        String str2 = parts.length > 2 ? parts[2] : "";
        StringBuilder sb = new StringBuilder();
        for (char c : str1.toCharArray()) {
            if (str2.indexOf(c) < 0) sb.append(c);
        }
        String kq = requestId + ";" + sb.toString();
        socket.send(new DatagramPacket(kq.getBytes(), kq.length(), sA, port));
        socket.close();
    }
}
