package TCP;

/**
 * Character Stream (cổng 2208) – Bài 14: Tìm Từ Dài Nhất
 * Mã câu hỏi (qCode): oKOoB5Fc
 *
 * Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2208 (thời gian tối đa 5s). Xây dựng chương trình client sử dụng luồng ký tự (BufferedReader/BufferedWriter) trao đổi theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi: "studentCode;qCode"
 * b. Nhận một chuỗi các từ phân tách nhau bởi khoảng trắng. Ví dụ: hello world java programming
 * c. Tìm từ dài nhất trong chuỗi và vị trí xuất hiện (index trong chuỗi gốc). Gửi lên server 2 dòng riêng biệt: Dòng 1: từ dài nhất. Dòng 2: vị trí (index). Ví dụ: programming rồi 12
 * d. Đóng kết nối
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class Character14_TimTuDaiNhat {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2208);
        socket.setSoTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B23DCCN139;oKOoB5Fc");
        out.newLine(); out.flush();
        String s = in.readLine();
        String[] parts = s.trim().split("\\s+");
        String tuDaiNhat = parts[0];
        for (String x : parts) { if (x.length() > tuDaiNhat.length()) { tuDaiNhat = x; } }
        int viTri = s.indexOf(tuDaiNhat);
        out.write(tuDaiNhat); out.newLine(); out.flush();
        out.write(String.valueOf(viTri)); out.newLine(); out.flush();
        in.close(); out.close(); socket.close();
    }
}
