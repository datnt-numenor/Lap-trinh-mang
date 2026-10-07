package TCP;

/**
 * Character Stream (cổng 2208) – Bài 9: Tách Chuỗi
 * Mã câu hỏi (qCode): uQWRjN4f
 *
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client thực hiện kết nối tới server và sử dụng luồng ký tự (BufferedWriter/BufferedReader) để trao đổi thông tin theo kịch bản:
 * a. Gửi một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;5E263AE1"
 * b. Nhận một chuỗi ngẫu nhiên từ server.
 * c. Tách chuỗi đã nhận thành 2 chuỗi và gửi lần lượt theo thứ tự lên server:
 * i. Chuỗi thứ nhất gồm các ký tự và số (loại bỏ các ký tự đặc biệt)
 * ii. Chuỗi thứ hai gồm các ký tự đặc biệt
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class Character09_TachChuoi {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2208);
        socket.setSoTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B23DCCN139;uQWRjN4f");
        out.newLine(); out.flush();
        String s = in.readLine();
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) s1.append(c);
            else s2.append(c);
        }
        out.write(s1.toString()); out.newLine(); out.flush();
        out.write(s2.toString()); out.newLine(); out.flush();
        in.close(); out.close(); socket.close();
    }
}
