package UDP;

/**
 * UDP Data (cổng 2207) – Bài A7: MÃ HOÁ CAESAR
 * Mã câu hỏi (qCode): J5SE2YXc
 *
 * [Mã câu hỏi (qCode): J5SE2YXc]. Mật mã caesar, còn gọi là mật mã dịch chuyển, để giải mã thì mỗi ký tự nhận được sẽ được thay thế bằng một ký tự cách nó một đoạn s. Ví dụ: với s = 3 thì ký tự "A" sẽ được thay thế bằng ký tự "D". Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207.
 * a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN001;825EE3A7"
 * b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;strEncode;s". requestId là chuỗi ngẫu nhiên duy nhất. strEncode là chuỗi thông điệp bị mã hóa. s là số nguyên chứa giá trị độ dịch của mã.
 * c. Giải mã tìm thông điệp ban đầu và gửi lên server theo định dạng "requestId;strDecode"
 * d. Đóng socket và kết thúc chương trình.
 *
 * Ghi chú:
 * Nếu WA: hướng xoay theo đề là cộng +s; nếu WA thì đổi "+ shift" thành "- shift".
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class Data07_MaHoaCaesar {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2207;
        String code = ";B23DCCN139;J5SE2YXc";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String s = new String(nhan.getData(), 0, nhan.getLength());
        String[] parts = s.trim().split(";");
        String requestId = parts[0];
        int shift = Integer.parseInt(parts[2].trim());
        StringBuilder sb = new StringBuilder();
        for (char c : parts[1].toCharArray()) {
            char base = Character.isUpperCase(c) ? 'A' : 'a';
            sb.append(Character.isLetter(c) ? (char) ((c - base + shift % 26 + 26) % 26 + base) : c);
        }
        String kq = requestId + ";" + sb.toString();
        socket.send(new DatagramPacket(kq.getBytes(), kq.length(), sA, port));
        socket.close();
    }
}
