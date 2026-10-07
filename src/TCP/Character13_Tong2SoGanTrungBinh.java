package TCP;

/**
 * Character Stream (cổng 2208) – Bài 13: Tổng 2 Số Gần Trung Bình CHARACTER
 * CHƯA CÓ qCode trong tài liệu: thay QCODE/qCode bằng mã câu hỏi thật
 *
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng ký tự (BufferedWriter/BufferedReader) theo kịch bản sau:
 * a. Gửi mã sinh viên và mã bài tập lên server theo định dạng <MSV>;<mã bài>. Ví dụ: B23DCCN139;qCode
 * b. Nhận từ server một dãy số nguyên phân tách bằng ",". Ví dụ: 3,1,4,1,5,9,2,6
 * c. Tìm cặp 2 số (không trùng vị trí) có tổng gần nhất với 2 lần trung bình cộng của dãy. Gửi lên server theo định dạng <số nhỏ>,<số lớn>. Ví dụ: 4,5
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.util.*;
import java.io.*;
import java.net.*;

public class Character13_Tong2SoGanTrungBinh {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2208);
        socket.setSoTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B23DCCN139;qCode");
        out.newLine(); out.flush();
        String s = in.readLine();
        String[] parts = s.split(",");
        ArrayList<Integer> ds = new ArrayList<>();
        for (String x : parts) { ds.add(Integer.parseInt(x.trim())); }
        Collections.sort(ds);
        int tong = 0;
        for (int x : ds) tong += x;
        float tbc = (float) tong / ds.size();
        float mucTieu = 2 * tbc;
        float kcach = Float.MAX_VALUE;
        int so1 = 0, so2 = 0;
        int n = ds.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                float hieu = Math.abs(ds.get(i) + ds.get(j) - mucTieu);
                if (hieu < kcach) { kcach = hieu; so1 = ds.get(i); so2 = ds.get(j); }
            }
        }
        String kq = so1 + "," + so2;
        out.write(kq);
        out.newLine(); out.flush();
        in.close(); out.close(); socket.close();
    }
}
