package TCP;

/**
 * Character Stream (cổng 2208) – Bài 8: Lọc Ký Tự Đặc Biệt + Trùng
 * Mã câu hỏi (qCode): mhUhFT2v
 *
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác tới server sử dụng các luồng ký tự (BufferedReader/BufferedWriter) theo kịch bản dưới đây:
 * a. Gửi một chuỗi gồm mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;7D6265E3"
 * b. Nhận một chuỗi ngẫu nhiên từ server.
 * c. Loại bỏ ký tự đặc biệt, số, ký tự trùng và giữ nguyên thứ tự xuất hiện của ký tự. Gửi chuỗi đã được xử lý lên server.
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class Character08_LocKyTuDacBietTrung {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2208);
        socket.setSoTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B23DCCN139;mhUhFT2v");
        out.newLine(); out.flush();
        String s = in.readLine();
        int[] cnt = new int[256];
        for (char c : s.toCharArray()) { if (Character.isLetter(c)) cnt[c]++; }
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (cnt[c] > 0 && Character.isLetter(c)) { sb.append(c); cnt[c] = 0; }
        }
        String kq = sb.toString();
        out.write(kq);
        out.newLine(); out.flush();
        in.close(); out.close(); socket.close();
    }
}
