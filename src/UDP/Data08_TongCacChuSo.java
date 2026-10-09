package UDP;

/**
 * UDP Data (cổng 2207) – Bài A8: TỔNG CÁC CHỮ SỐ
 * Mã câu hỏi (qCode): 0Iend7Pp
 *
 * [Mã câu hỏi (qCode): 0Iend7Pp]. Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng chương trình client trao đổi thông tin với server theo kịch bản:
 * a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN011;A1F3D5"
 * b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;num", với: requestId là chuỗi ngẫu nhiên duy nhất. num là một số nguyên lớn.
 * c. Tính tổng các chữ số trong num và gửi lại tổng này về server theo định dạng "requestId;sumDigits".
 * d. Đóng socket và kết thúc chương trình.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class Data08_TongCacChuSo {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2207;
        String code = ";B23DCCN139;0Iend7Pp";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String s = new String(nhan.getData(), 0, nhan.getLength());
        String[] parts = s.trim().split(";");
        String requestId = parts[0];
        int sum = 0;
        for (char c : parts[1].toCharArray()) {
            if (Character.isDigit(c)) sum += c - '0';
        }
        String kq = requestId + ";" + sum;
        socket.send(new DatagramPacket(kq.getBytes(), kq.length(), sA, port));
        socket.close();
    }
}
