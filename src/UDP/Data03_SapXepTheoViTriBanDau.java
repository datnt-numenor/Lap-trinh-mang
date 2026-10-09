package UDP;

/**
 * UDP Data (cổng 2207) – Bài A3: SẮP XẾP THEO VỊ TRÍ BAN ĐẦU
 * Mã câu hỏi (qCode): aKZwZxWk
 *
 * [Mã câu hỏi (qCode): aKZwZxWk]. Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
 * a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN009;F3E8B2D4".
 * b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;string", với: requestId là chuỗi ngẫu nhiên duy nhất. string là một chuỗi chứa các chuỗi con bị thay đổi vị trí. Ví dụ: "veM3xgA1g:4,IPFfgEanY:5,aWXlSzDwe:2,PHupvPc:3,PR3gH8ahN:6,UEEKHLIt:7,M6dpWTE:1"
 * c. Xử lý chuỗi xáo trộn và gửi về chuỗi sau khi sắp xếp: "requestId;string". Ví dụ: "M6dpWTE,aWXlSzDwe,PHupvPc,veM3xgA1g,IPFfgEanY,PR3gH8ahN,UEEKHLIt"
 * d. Đóng socket và kết thúc chương trình.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class Data03_SapXepTheoViTriBanDau {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2207;
        String code = ";B23DCCN139;aKZwZxWk";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String s = new String(nhan.getData(), 0, nhan.getLength());
        String[] parts = s.trim().split(";");
        String requestId = parts[0];
        TreeMap<Integer, String> mp = new TreeMap<>();
        for (String x : parts[1].split(",")) {
            String[] the = x.split(":");
            mp.put(Integer.parseInt(the[1].trim()), the[0].replaceAll("\\s", ""));
        }
        String kq = requestId + ";" + String.join(",", mp.values());
        socket.send(new DatagramPacket(kq.getBytes(), kq.length(), sA, port));
        socket.close();
    }
}
