package UDP;

/**
 * UDP Data (cổng 2207) – Bài A9: LIỆT KÊ SỐ NGUYÊN TỐ
 * Mã câu hỏi (qCode): 78CCQ6xD
 *
 * [Mã câu hỏi (qCode): 78CCQ6xD]. Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
 * a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN009;F3E8B2D4".
 * b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;n, n", với: requestId là chuỗi ngẫu nhiên duy nhất. n là một số nguyên ngẫu nhiên ≤ 100.
 * c. Tính và gửi về server danh sách n số nguyên tố đầu tiên theo định dạng "requestId;p1,p2,...,pk". Đây là "n số nguyên tố đầu tiên" (n = 5 thì ra 2, 3, 5, 7, 11), không phải "các số nguyên tố không vượt quá n".
 * d. Đóng socket và kết thúc chương trình.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class Data09_LietKeSoNguyenTo {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2207;
        String code = ";B23DCCN139;78CCQ6xD";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String s = new String(nhan.getData(), 0, nhan.getLength());
        String[] parts = s.trim().split(";");
        String requestId = parts[0];
        int n = Integer.parseInt(parts[1].split(",")[0].trim());
        StringBuilder sb = new StringBuilder();
        int dem = 0;
        for (int x = 2; dem < n; x++) {
            boolean nt = true;
            for (int i = 2; i < x; i++) {
                if (x % i == 0) nt = false;
            }
            if (nt) {
                if (dem > 0) sb.append(",");
                sb.append(x);
                dem++;
            }
        }
        String kq = requestId + ";" + sb.toString();
        socket.send(new DatagramPacket(kq.getBytes(), kq.length(), sA, port));
        socket.close();
    }
}
