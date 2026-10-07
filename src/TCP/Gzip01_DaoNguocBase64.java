package TCP;

/**
 * GZIP Stream (cổng 2210) – Bài 1: Đảo Ngược + Base64
 * Mã câu hỏi (qCode): huPEuHGB
 *
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2210 (thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác tới server ở trên sử dụng GZIPInputStream/GZIPOutputStream để trao đổi thông tin (mỗi thông điệp là một dòng text UTF-8 kết thúc bằng '\n' và toàn bộ dữ liệu truyền/nhận đều được nén GZIP), theo thứ tự sau:
 * a. Gửi mã sinh viên và mã câu hỏi theo định dạng studentCode;qCode.
 * Ví dụ: B16DCCN999;GZLEN01
 * b. Nhận dữ liệu từ server, sau khi giải nén là một chuỗi văn bản.
 * c. Thực hiện đảo ngược chuỗi nhận được, sau đó mã hóa chuỗi đã đảo ngược sang định dạng Base64. Gửi kết quả lên server theo khuôn dạng: <reversed_string>|<base64_string>
 * Ví dụ: Nhận "123" → Đảo ngược thành "321" → Base64 của "321" là "MzIx" → Gửi lên: 321|MzIx
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.util.*;
import java.io.*;
import java.net.*;
import java.util.zip.*;
import java.util.Base64;

public class Gzip01_DaoNguocBase64 {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2210);
        socket.setSoTimeout(5000);
        InputStream rawIn = socket.getInputStream();
        OutputStream rawOut = socket.getOutputStream();
        gzipSend(rawOut, "B23DCCN139;huPEuHGB\n");
        String s = gzipRecv(rawIn);
        // ===== LOGIC =====
        String reversed = new StringBuilder(s).reverse().toString();
        String b64 = Base64.getEncoder().withoutPadding()
        .encodeToString(reversed.getBytes("UTF-8"));
        String kq = reversed + "|" + b64;
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
