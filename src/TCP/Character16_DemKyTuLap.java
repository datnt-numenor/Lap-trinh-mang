package TCP;

/**
 * Character Stream (cổng 2208) – Bài 16: Đếm Ký Tự Lặp
 * Mã câu hỏi (qCode): CVkVQheX
 *
 * Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2208 (thời gian tối đa 5s). Xây dựng chương trình client sử dụng luồng ký tự (BufferedReader/BufferedWriter) trao đổi theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi: "studentCode;qCode"
 * b. Nhận một chuỗi ngẫu nhiên từ server. Ví dụ: hello world
 * c. Đếm số lần xuất hiện của từng ký tự chữ/số (bỏ qua ký tự đặc biệt). Gửi lên server các ký tự xuất hiện >= 2 lần, theo thứ tự xuất hiện đầu tiên trong chuỗi, format: ký tự:số lần,ký tự:số lần,... Ví dụ: l:3,o:2,
 * d. Đóng kết nối
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class Character16_DemKyTuLap {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2208);
        socket.setSoTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B23DCCN139;CVkVQheX");
        out.newLine(); out.flush();
        String s = in.readLine();
        int[] cnt = new int[256];
        for (char c : s.toCharArray()) { if (Character.isLetterOrDigit(c)) cnt[c]++; }
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (cnt[c] >= 2) { sb.append(c).append(":").append(cnt[c]).append(","); cnt[c] = 0; }
        }
        String kq = sb.toString();
        out.write(kq);
        out.newLine(); out.flush();
        in.close(); out.close(); socket.close();
    }
}
