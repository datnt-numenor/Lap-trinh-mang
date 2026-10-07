package TCP;

/**
 * NIO Stream (cổng 2211) – Bài 3: Trích Xuất HTTP Request
 * Mã câu hỏi (qCode): u48syPvz
 *
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2211 (thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác tới server ở trên sử dụng SocketChannel và ByteBuffer để trao đổi thông tin theo giao thức frame: 4 byte độ dài (int32) + payload (UTF-8).
 * Lưu ý: server & client đều phải đọc đủ dữ liệu bằng vòng lặp (readFully) do server luôn chia nhỏ dữ liệu khi gửi. Trình tự trao đổi như sau:
 * a. Gửi mã sinh viên và mã câu hỏi theo định dạng studentCode;qCode.
 * Ví dụ: B16DCCN999;fkdRJYuX
 * b. Nhận dữ liệu từ server gồm đúng 3 frame liên tiếp. Payload của mỗi frame là một phần của cùng một HTTP request, client phải nối 3 payload theo đúng thứ tự để thu được chuỗi HTTP request hoàn chỉnh (các dòng phân tách bởi \r\n và kết thúc bằng \r\n\r\n).
 * c. Từ chuỗi HTTP request hoàn chỉnh, trích xuất và gửi lại lên server theo định dạng METHOD;PATH;HOST trong đó PATH luôn bao gồm query-string.
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.util.*;
import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;

public class Nio01_TrichXuatHTTPRequest {
    public static void main(String[] args) throws Exception {
        SocketChannel sc = SocketChannel.open(
        new InetSocketAddress("36.50.135.242", 2211));
        sc.configureBlocking(true);
        frameSend(sc, "B23DCCN139;u48syPvz");
        // ===== LOGIC =====
        String http = frameRecv(sc) + frameRecv(sc) + frameRecv(sc);
        String[] lines = http.split("\r\n");
        String[] requestLine = lines[0].split(" ");
        String method = requestLine[0];
        String path = requestLine[1];
        String host = "";
        for (String line : lines) {
            if (line.toLowerCase().startsWith("host:")) {
                host = line.substring(5).trim();
                break;
            }
        }
        String kq = method + ";" + path + ";" + host;
        // ==================
        frameSend(sc, kq);
        sc.close();
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
