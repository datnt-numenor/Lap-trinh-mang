package TCP;

/**
 * Character Stream (cổng 2208) – Bài 15: Dãy Con Tăng Dài Nhất LIS
 * Mã câu hỏi (qCode): XGIm2Fc7
 *
 * Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2208 (thời gian tối đa 5s). Xây dựng chương trình client sử dụng luồng ký tự (BufferedReader/BufferedWriter) trao đổi theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi: "studentCode;qCode"
 * b. Nhận chuỗi số nguyên phân tách bởi dấu ",". Ví dụ: 3,1,4,1,5,9,2,6
 * c. Tìm dãy con tăng dài nhất (LIS). Gửi lên server: phần tử 1,phần tử 2,...;độ dài. Ví dụ: 1,4,5,9;4
 * d. Đóng kết nối
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class Character15_DayConTangDaiNhat {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2208);
        socket.setSoTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B23DCCN139;XGIm2Fc7");
        out.newLine(); out.flush();
        String s = in.readLine();
        String[] parts = s.trim().split(",");
        ArrayList<Integer> ds = new ArrayList<>();
        for (String x : parts) ds.add(Integer.parseInt(x.trim()));
        int n = ds.size();
        int[] f = new int[n];
        int[] trace = new int[n];
        Arrays.fill(f, 1); Arrays.fill(trace, -1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (ds.get(j) < ds.get(i) && f[j] + 1 > f[i]) { f[i] = f[j] + 1; trace[i] = j; }
            }
        }
        int maxLen = 0, endIdx = 0;
        for (int i = 0; i < n; i++) { if (f[i] > maxLen) { maxLen = f[i]; endIdx = i; } }
        ArrayList<Integer> lis = new ArrayList<>();
        while (endIdx != -1) { lis.add(0, ds.get(endIdx)); endIdx = trace[endIdx]; }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lis.size(); i++) { sb.append(lis.get(i)); if (i != lis.size() - 1) sb.append(","); }
        sb.append(";").append(lis.size());
        String kq = sb.toString();
        out.write(kq);
        out.newLine(); out.flush();
        in.close(); out.close(); socket.close();
    }
}
