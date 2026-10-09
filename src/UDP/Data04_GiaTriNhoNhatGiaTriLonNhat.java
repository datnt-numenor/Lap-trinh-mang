package UDP;

/**
 * UDP Data (cổng 2207) – Bài A4: GIÁ TRỊ NHỎ NHẤT – GIÁ TRỊ LỚN NHẤT
 * Mã câu hỏi (qCode): uWKK8u3W / l2wVNbKC
 *
 * [Mã câu hỏi (qCode): uWKK8u3W]. Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
 * a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN001;DC73CA2E"
 * b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;a1,a2,...,a50". requestId là chuỗi ngẫu nhiên duy nhất. a1 -> a50 là 50 số nguyên ngẫu nhiên.
 * c. Thực hiện tìm giá trị lớn nhất và giá trị nhỏ nhất trong a1 -> a50 và gửi thông điệp lên server theo định dạng "requestId;max,min"
 * d. Đóng socket và kết thúc chương trình.
 * Lưu ý: Hệ thống còn có mã câu hỏi l2wVNbKC cùng đề này: chỉ đổi qCode.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class Data04_GiaTriNhoNhatGiaTriLonNhat {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2207;
        String code = ";B23DCCN139;uWKK8u3W";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String s = new String(nhan.getData(), 0, nhan.getLength());
        String[] parts = s.trim().split(";");
        String requestId = parts[0];
        String[] arr = parts[1].split(",");
        int max = Integer.parseInt(arr[0].trim());
        int min = max;
        for (String x : arr) {
            int v = Integer.parseInt(x.trim());
            if (v > max) max = v;
            if (v < min) min = v;
        }
        String kq = requestId + ";" + max + "," + min;
        socket.send(new DatagramPacket(kq.getBytes(), kq.length(), sA, port));
        socket.close();
    }
}
