package TCP;

/**
 * Character Stream (cổng 2208) – Bài 7: Hiệu Hai Tập Hợp
 * Mã câu hỏi (qCode): vYbP7vOA
 *
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng ký tự (BufferedReader/BufferedWriter) theo kịch bản sau:
 * a. Gửi một chuỗi gồm mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;DE0C2BF0"
 * b. Nhận lần lượt hai chuỗi ngẫu nhiên từ server.
 * c. Loại bỏ các ký tự trong chuỗi thứ nhất mà xuất hiện trong chuỗi thứ hai, yêu cầu giữ nguyên thứ tự xuất hiện của ký tự. Gửi chuỗi thứ nhất đã được xử lý lên server.
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class Character07_HieuHaiTapHop {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2208);
        socket.setSoTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B23DCCN139;vYbP7vOA");
        out.newLine(); out.flush();
        String s1 = in.readLine();
        String s2 = in.readLine();
        int[] cnt = new int[256];
        for (char c : s2.toCharArray()) cnt[c]++;
        StringBuilder sb = new StringBuilder();
        for (char c : s1.toCharArray()) { if (cnt[c] == 0) sb.append(c); }
        String kq = sb.toString();
        out.write(kq);
        out.newLine(); out.flush();
        in.close(); out.close(); socket.close();
    }
}
