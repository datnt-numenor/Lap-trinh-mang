package TCP;

/**
 * Byte Stream (cổng 2206) – Bài 8: Dãy Con Không Lặp Dài Nhất
 * Mã câu hỏi (qCode): HyHAk4P5
 *
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2206 (thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client thực hiện kết nối tới server sử dụng các luồng byte (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;10048F28"
 * b. Nhận chuỗi ký tự s từ server. Ví dụ: "abcabcbb"
 * c. Tìm và gửi lên server chuỗi con dài nhất từ chuỗi nhận được mà không có ký tự lặp lại theo format "longestsubstring;length". Ví dụ: "abc;3"
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.util.*;
import java.io.*;
import java.net.*;

public class Byte08_DayConKhongLapDaiNhat {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        out.write("B23DCCN139;HyHAk4P5".getBytes());
        out.flush();
        byte[] buffer = new byte[1024];
        int len = in.read(buffer);
        String s = new String(buffer, 0, len).trim();
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, bestStart = 0, bestLen = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (map.containsKey(c) && map.get(c) >= left) { left = map.get(c) + 1; }
            map.put(c, right);
            int curLen = right - left + 1;
            if (curLen > bestLen) { bestLen = curLen; bestStart = left; }
        }
        String best = s.substring(bestStart, bestStart + bestLen);
        String result = best + ";" + bestLen;
        out.write(result.getBytes());
        out.flush();
        socket.close();
    }
}
