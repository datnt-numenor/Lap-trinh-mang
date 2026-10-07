package TCP;

/**
 * Character Stream (cổng 2208) – Bài 17: Đổi Chiều Biến Thiên CHARACTER
 * Mã câu hỏi (qCode): oNGj55wV
 *
 * Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2208 (thời gian tối đa 5s). Xây dựng chương trình client sử dụng luồng ký tự (BufferedReader/BufferedWriter) trao đổi theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi: "studentCode;qCode"
 * b. Nhận chuỗi số nguyên phân tách bởi dấu ",". Ví dụ: 1,5,3,7,2,6
 * c. Tính 2 giá trị và gửi lên server 2 dòng riêng biệt: Dòng 1: số lần đổi chiều (phần tử là cực trị cục bộ). Dòng 2: tổng biến thiên = tổng |a[i] - a[i+1]|. Ví dụ: 3 rồi 18
 * d. Đóng kết nối
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class Character17_DoiChieuBienThien {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2208);
        socket.setSoTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B23DCCN139;oNGj55wV");
        out.newLine(); out.flush();
        String s = in.readLine();
        String[] parts = s.trim().split(",");
        ArrayList<Integer> ds = new ArrayList<>();
        for (String x : parts) ds.add(Integer.parseInt(x.trim()));
        int n = ds.size();
        int bienThien = 0;
        for (int i = 0; i < n - 1; i++) { bienThien += Math.abs(ds.get(i) - ds.get(i + 1)); }
        int doiChieu = 0;
        for (int i = 1; i < n - 1; i++) {
            boolean cucTieu = ds.get(i) < ds.get(i-1) && ds.get(i) < ds.get(i+1);
            boolean cucDai = ds.get(i) > ds.get(i-1) && ds.get(i) > ds.get(i+1);
            if (cucTieu || cucDai) doiChieu++;
        }
        out.write(String.valueOf(doiChieu)); out.newLine(); out.flush();
        out.write(String.valueOf(bienThien)); out.newLine(); out.flush();
        in.close(); out.close(); socket.close();
    }
}
