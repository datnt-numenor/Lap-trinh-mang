package TCP;

/**
 * Character Stream (cổng 2208) – Bài 18: Lọc Ký Tự
 * Mã câu hỏi (qCode): TuTa8p7
 *
 * Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2208 (thời gian tối đa 5s). Xây dựng chương trình client sử dụng luồng ký tự (BufferedReader/BufferedWriter) trao đổi theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi: "studentCode;qCode"
 * b. Nhận một chuỗi ngẫu nhiên từ server. Ví dụ: hello world java
 * c. Lấy các ký tự chữ cái xuất hiện trong chuỗi, mỗi ký tự chỉ lấy 1 lần, theo thứ tự xuất hiện đầu tiên. Gửi lên server chuỗi kết quả. Ví dụ: helowrdjva
 * d. Đóng kết nối
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class Character18_LocKyTu {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2208);
        socket.setSoTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B23DCCN139;TuTa8p7");
        out.newLine(); out.flush();
        String s = in.readLine();
        int[] cnt = new int[256];
        for (char c : s.toCharArray()) { if (Character.isLetter(c)) cnt[c]++; }
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) { if (cnt[c] > 0) { sb.append(c); cnt[c] = 0; } }
        String kq = sb.toString();
        out.write(kq);
        out.newLine(); out.flush();
        in.close(); out.close(); socket.close();
    }
}
