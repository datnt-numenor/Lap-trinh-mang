package TCP;

/**
 * GZIP Stream (cổng 2210) – Bài 2: Sắp Xếp Ký Tự
 * Mã câu hỏi (qCode): vwyplwN8
 *
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2210 (thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác tới server ở trên sử dụng GZIPInputStream/GZIPOutputStream để trao đổi thông tin (mỗi thông điệp là một dòng text UTF-8 kết thúc bằng '\n' và toàn bộ dữ liệu truyền/nhận đều được nén GZIP), theo thứ tự sau:
 * a. Gửi mã sinh viên và mã câu hỏi theo định dạng studentCode;qCode.
 * Ví dụ: B16DCCN999;GZCRC_LEN03
 * b. Nhận dữ liệu từ server, sau khi giải nén là một chuỗi văn bản.
 * c. Sắp xếp các ký tự trong chuỗi nhận được theo thứ tự từ điển (tăng dần theo mã ASCII). Sau đó gửi chuỗi kết quả đã sắp xếp lên server.
 * Ví dụ: Nhận về "dbca1" thì gửi lên server "1abcd"
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.util.*;
import java.io.*;
import java.net.*;
import java.util.zip.*;

public class Gzip02_SapXepKyTu {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2210);
        socket.setSoTimeout(5000);
        InputStream rawIn = socket.getInputStream();
        OutputStream rawOut = socket.getOutputStream();
        gzipSend(rawOut, "B23DCCN139;vwyplwN8\n");
        String s = gzipRecv(rawIn);
        // ===== LOGIC =====
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        String kq = new String(arr);
        // ==================
        gzipSend(rawOut, kq + "\n");
        socket.close();
    }
    static void gzipSend(OutputStream rawOut, String text) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gz = new GZIPOutputStream(baos);
        gz.write(text.getBytes("UTF-8"));
        gz.close();
        rawOut.write(baos.toByteArray());
        rawOut.flush();
    }
    static String gzipRecv(InputStream rawIn) throws Exception {
        GZIPInputStream gzIn = new GZIPInputStream(rawIn);
        ByteArrayOutputStream decoded = new ByteArrayOutputStream();
        int b;
        while ((b = gzIn.read()) != -1) {
            decoded.write(b);
            if (b == '\n') break;
        }
        return decoded.toString("UTF-8").trim();
    }
}
