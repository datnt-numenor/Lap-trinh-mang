package TCP;

/**
 * Character Stream (cổng 2208) – Bài 6: Nén RLE
 * Mã câu hỏi (qCode): ji3fQD3Q
 *
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng ký tự (BufferedReader/BufferedWriter) theo kịch bản sau:
 * a. Gửi một chuỗi chứa mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;1D08FX21"
 * b. Nhận từ server một chuỗi chứa nhiều từ, các từ được phân tách bởi khoảng trắng. Ví dụ: "hello world programming is fun"
 * c. Thực hiện đảo ngược từ và mã hóa RLE để nén chuỗi ("aabb" nén thành "a2b2"). Gửi chuỗi đã được xử lý lên server. Ví dụ: "ol2eh dlrow gnim2argorp si nuf".
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class Character06_NenRLE {
    static String xauDao(String s) { return new StringBuilder(s).reverse().toString(); }
    static String RLE(String s) {
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) { cnt++; }
            else { sb.append(s.charAt(i - 1)); if (cnt >= 2) sb.append(cnt); cnt = 1; }
        }
        sb.append(s.charAt(s.length() - 1));
        if (cnt >= 2) sb.append(cnt);
        return sb.toString();
    }
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2208);
        socket.setSoTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B23DCCN139;ji3fQD3Q");
        out.newLine(); out.flush();
        String s = in.readLine();
        String[] parts = s.trim().split("\\s+");
        ArrayList<String> ds = new ArrayList<>();
        for (String word : parts) { ds.add(RLE(xauDao(word))); }
        String kq = String.join(" ", ds);
        out.write(kq);
        out.newLine(); out.flush();
        in.close(); out.close(); socket.close();
    }
}
