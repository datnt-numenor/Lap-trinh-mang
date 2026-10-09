package UDP;

/**
 * UDP Data (cổng 2207) – Bài A1: TỔNG HAI SỐ NGUYÊN LỚN
 * Mã câu hỏi (qCode): 2sIjAYaU
 *
 * [Mã câu hỏi (qCode): 2sIjAYaU]. Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
 * a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN010;D3F9A7B8"
 * b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;a;b", với: requestId là chuỗi ngẫu nhiên duy nhất. a và b là chuỗi thể hiện hai số nguyên lớn (hơn hoặc bằng 100 chữ số). Ví dụ: "X1Y2Z3;9876543210;123456789"
 * c. Tính tổng và hiệu của hai số a và b, gửi thông điệp lên server theo định dạng "requestId;sum,difference". Ví dụ: "X1Y2Z3;9999999999,9753086421"
 * d. Đóng socket và kết thúc chương trình.
 * Chú ý: Yêu cầu sử dụng BigInteger. Tính a – b chứ không tính |a – b|.
 *
 * Ghi chú:
 * Nếu WA: đề tự mâu thuẫn ở dấu giữa tổng và hiệu (phần định dạng ghi ";", ví dụ ghi ","). Code đang dùng dấu ","; nếu WA thì đổi thành ";".
 */

import java.util.*;
import java.net.*;
import java.io.*;
import java.math.*;

public class Data01_TongHaiSoNguyenLon {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2207;
        String code = ";B23DCCN139;2sIjAYaU";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[8192], 8192);
        socket.receive(nhan);
        String s = new String(nhan.getData(), 0, nhan.getLength());
        String[] parts = s.trim().split("[;,]");
        String requestId = parts[0];
        BigInteger a = new BigInteger(parts[1].trim());
        BigInteger b = new BigInteger(parts[2].trim());
        String kq = requestId + ";" + a.add(b) + "," + a.subtract(b);
        socket.send(new DatagramPacket(kq.getBytes(), kq.length(), sA, port));
        socket.close();
    }
}
