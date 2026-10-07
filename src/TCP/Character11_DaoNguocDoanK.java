package TCP;

/**
 * Character Stream (cổng 2208) – Bài 11: Đảo Ngược Đoạn K CHARACTER
 * CHƯA CÓ qCode trong tài liệu: thay QCODE/qCode bằng mã câu hỏi thật
 *
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng ký tự (BufferedWriter/BufferedReader) theo kịch bản sau:
 * a. Gửi mã sinh viên và mã bài tập lên server theo định dạng <MSV>;<mã bài>. Ví dụ: B23DCCN139;qCode
 * b. Nhận từ server số nguyên k (độ dài mỗi đoạn), sau đó nhận chuỗi các số nguyên phân tách bằng ",". Ví dụ: k=3, chuỗi=1,2,3,4,5,6,7
 * c. Chia dãy thành các đoạn độ dài k, đảo ngược từng đoạn rồi ghép lại, gửi lên server. Ví dụ: 3,2,1,6,5,4,7
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.util.*;
import java.io.*;
import java.net.*;

public class Character11_DaoNguocDoanK {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2208);
        socket.setSoTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B23DCCN139;qCode");
        out.newLine(); out.flush();
        int k = Integer.parseInt(in.readLine().trim());
        String s = in.readLine();
        String[] parts = s.split(",");
        ArrayList<Integer> ds = new ArrayList<>();
        for (String x : parts) { ds.add(Integer.parseInt(x.trim())); }
        int n = ds.size();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i += k) {
            int j = Math.min(i + k - 1, n - 1);
            for (int o = j; o >= i; o--) { res.add(ds.get(o)); }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) { sb.append(res.get(i)); if (i != n - 1) sb.append(","); }
        String kq = sb.toString();
        out.write(kq);
        out.newLine(); out.flush();
        in.close(); out.close(); socket.close();
    }
}
