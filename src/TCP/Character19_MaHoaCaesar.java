package TCP;

/**
 * Character Stream (cổng 2208) – Bài 19: Mã Hoá Caesar CHARACTER
 * Mã câu hỏi (qCode): doW5fnkq
 *
 * Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2208 (thời gian tối đa 5s). Xây dựng chương trình client sử dụng luồng ký tự (BufferedReader/BufferedWriter) trao đổi theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi: "studentCode;qCode"
 * b. Nhận 2 dòng từ server: Dòng 1: chuỗi cần giải mã. Dòng 2: số bước dịch k. Ví dụ: Khoor và 3
 * c. Giải mã Caesar — dịch ngược k bước (chỉ xử lý chữ cái, giữ nguyên ký tự khác, phân biệt hoa/thường). Gửi lên server chuỗi đã giải mã. Ví dụ: Hello
 * d. Đóng kết nối
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class Character19_MaHoaCaesar {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2208);
        socket.setSoTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B23DCCN139;doW5fnkq");
        out.newLine(); out.flush();
        String s = in.readLine();
        int k = Integer.parseInt(in.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char) (((c - base - k + 26) % 26) + base);
            }
            sb.append(c);
        }
        String kq = sb.toString();
        out.write(kq);
        out.newLine(); out.flush();
        in.close(); out.close(); socket.close();
    }
}
