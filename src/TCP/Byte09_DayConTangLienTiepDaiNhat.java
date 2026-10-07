package TCP;

/**
 * Byte Stream (cổng 2206) – Bài 9: Dãy Con Tăng Liên Tiếp Dài Nhất
 * Mã câu hỏi (qCode): RnPqP3f7
 *
 * Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng chương trình client thực hiện kết nối tới server sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;76B68B3B"
 * b. Nhận dữ liệu từ server là một chuỗi các giá trị số nguyên được phân tách bởi ký tự ",". Ví dụ: 5,10,20,25,50,40,30,35
 * c. Tìm chuỗi con tăng dần dài nhất và gửi độ dài của chuỗi đó lên server. Ví dụ: 5,10,20,25 có độ dài 4.
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.util.*;
import java.io.*;
import java.net.*;

public class Byte09_DayConTangLienTiepDaiNhat {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        out.write("B23DCCN139;RnPqP3f7".getBytes());
        out.flush();
        byte[] buffer = new byte[1024];
        int len = in.read(buffer);
        String s = new String(buffer, 0, len).trim();
        String[] parts = s.split(",");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) arr[i] = Integer.parseInt(parts[i].trim());
        int best = 1, cur = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) { cur++; if (cur > best) best = cur; }
            else { cur = 1; }
        }
        out.write(String.valueOf(best).getBytes());
        out.flush();
        socket.close();
    }
}
