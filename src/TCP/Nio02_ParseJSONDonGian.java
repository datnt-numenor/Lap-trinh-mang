package TCP;

/**
 * NIO Stream (cổng 2211) – Bài 4: Parse JSON đơn giản
 * Mã câu hỏi (qCode): h4VQFoET
 *
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2211 (thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác tới server ở trên sử dụng SocketChannel và ByteBuffer để trao đổi thông tin theo giao thức frame: 4 byte độ dài (int32) + payload (UTF-8).
 * Lưu ý: server & client đều phải đọc đủ dữ liệu bằng vòng lặp (readFully) do server luôn chia nhỏ dữ liệu khi gửi. Trình tự trao đổi như sau:
 * a. Gửi mã sinh viên và mã câu hỏi theo định dạng studentCode;qCode.
 * Ví dụ: B16DCCN999;ucpQ9zAh
 * b. Nhận dữ liệu từ server gồm đúng 2 frame liên tiếp. Payload của mỗi frame là một phần của cùng một chuỗi JSON đơn giản trên một dòng (không xuống dòng). Client phải nối 2 payload theo đúng thứ tự để thu được chuỗi JSON hoàn chỉnh.
 * c. Trích xuất các trường event, user, ok và gửi lại lên server theo định dạng event=<event>;user=<user>;ok=<0|1> (true=1, false=0).
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.util.*;
import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;

public class Nio02_ParseJSONDonGian {
    public static void main(String[] args) throws Exception {
        SocketChannel sc = SocketChannel.open(
        new InetSocketAddress("36.50.135.242", 2211));
        sc.configureBlocking(true);
        frameSend(sc, "B23DCCN139;h4VQFoET");
        // ===== LOGIC =====
        String json = frameRecv(sc) + frameRecv(sc);
        String event = extract(json, "event");
        String user = extract(json, "user");
        String ok = extract(json, "ok").equals("true") ? "1" : "0";
        String kq = "event=" + event + ";user=" + user + ";ok=" + ok;
        // ==================
        frameSend(sc, kq);
        sc.close();
    }
    static String extract(String json, String key) {
        String pattern = "\"" + key + "\":";
        int i = json.indexOf(pattern) + pattern.length();
        if (json.charAt(i) == '"') {
            int end = json.indexOf('"', i + 1);
            return json.substring(i + 1, end);
        } else {
            int end = json.indexOf(',', i);
            if (end == -1) end = json.indexOf('}', i);
            return json.substring(i, end).trim();
        }
    }
    static void frameSend(SocketChannel sc, String text) throws Exception {
        byte[] payload = text.getBytes("UTF-8");
        ByteBuffer buf = ByteBuffer.allocate(4 + payload.length);
        buf.putInt(payload.length);
        buf.put(payload);
        buf.flip();
        while (buf.hasRemaining()) sc.write(buf);
    }
    static String frameRecv(SocketChannel sc) throws Exception {
        ByteBuffer lenBuf = ByteBuffer.allocate(4);
        while (lenBuf.hasRemaining()) sc.read(lenBuf);
        lenBuf.flip();
        int len = lenBuf.getInt();
        ByteBuffer payBuf = ByteBuffer.allocate(len);
        while (payBuf.hasRemaining()) sc.read(payBuf);
        payBuf.flip();
        return Charset.forName("UTF-8").decode(payBuf).toString();
    }
}
