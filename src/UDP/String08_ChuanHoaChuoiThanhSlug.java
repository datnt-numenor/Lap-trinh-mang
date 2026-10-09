package UDP;

/**
 * UDP String (cổng 2208) – Bài B8: CHUẨN HOÁ CHUỖI THÀNH SLUG (HAI PHA)
 * Mã câu hỏi (qCode): V46plLn9
 *
 * [Mã câu hỏi (qCode): V46plLn9]. Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2208 theo cơ chế hai pha.
 * a. Gửi datagram đầu tiên chứa chuỗi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN001;EE29C059".
 * b. Nhận phản hồi từ server theo định dạng "requestId;data". Ví dụ: "7Brz6QQA;REFUND ticket Payment Payment customer Payment customer ticket REFUND customer shipping shipping Payment.."
 * c. Chuyển chuỗi về chữ thường, loại bỏ dấu câu, gom nhiều khoảng trắng thành một khoảng trắng và thay khoảng trắng bằng dấu gạch ngang.
 * d. Gửi datagram nộp kết quả theo định dạng "requestId;answer". Ví dụ: "7Brz6QQA;refund-ticket-payment-payment-customer-payment-customer-ticket-refund-customer-shipping-shipping-payment".
 * e. Đóng kết nối hoặc kết thúc client sau khi nộp kết quả.
 *
 * Ghi chú:
 * Nếu WA: thử đổi replaceAll("\\p{Punct}", "") thành replaceAll("\\p{Punct}", " ") (thay bằng khoảng trắng thay vì xóa hẳn) để tránh hai từ dính nhau.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class String08_ChuanHoaChuoiThanhSlug {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2208;
        String code = ";B23DCCN139;V46plLn9";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String s = new String(nhan.getData(), 0, nhan.getLength());
        String[] parts = s.trim().split(";", 2);
        String requestId = parts[0];
        String r = parts[1].toLowerCase().replaceAll("\\p{Punct}", "").trim().replaceAll("\\s+", "-");
        String kq = requestId + ";" + r;
        socket.send(new DatagramPacket(kq.getBytes(), kq.length(), sA, port));
        socket.close();
    }
}
