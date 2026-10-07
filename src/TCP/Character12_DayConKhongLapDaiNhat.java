package TCP;

/**
 * Character Stream (cổng 2208) – Bài 12: Dãy Con Không Lặp Dài Nhất CHARACTER
 * CHƯA CÓ qCode trong tài liệu: thay QCODE/qCode bằng mã câu hỏi thật
 *
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng ký tự (BufferedWriter/BufferedReader) theo kịch bản sau:
 * a. Gửi mã sinh viên và mã bài tập lên server theo định dạng <MSV>;<mã bài>. Ví dụ: B23DCCN139;qCode
 * b. Nhận từ server một chuỗi ký tự bất kỳ. Ví dụ: abcabcbb
 * c. Tìm dãy con liên tiếp dài nhất không có ký tự lặp, gửi lên server theo định dạng <dãy con>;<độ dài>. Ví dụ: abc;3
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.util.*;
import java.io.*;
import java.net.*;

public class Character12_DayConKhongLapDaiNhat {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2208);
        socket.setSoTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B23DCCN139;qCode");
        out.newLine(); out.flush();
        String s = in.readLine();
        String strMax = "";
        for (int i = 0; i < s.length(); i++) {
            int[] cnt = new int[256];
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < s.length(); j++) {
                if (cnt[s.charAt(j)] == 1) break;
                cnt[s.charAt(j)] = 1;
                sb.append(s.charAt(j));
                if (sb.length() > strMax.length()) { strMax = sb.toString(); }
            }
        }
        String kq = strMax + ";" + strMax.length();
        out.write(kq);
        out.newLine(); out.flush();
        in.close(); out.close(); socket.close();
    }
}
