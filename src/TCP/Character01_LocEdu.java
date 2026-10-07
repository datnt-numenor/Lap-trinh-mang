package TCP;

/**
 * Character Stream (cổng 2208) – Bài 1: Lọc .edu
 * Mã câu hỏi (qCode): kCAeDRzX
 *
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng byte (BufferedWriter/BufferedReader) theo kịch bản sau:
 * a. Gửi một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng studentCode;qCode. Ví dụ: B15DCCN999;EC4F899B
 * b. Nhận một chuỗi ngẫu nhiên là danh sách các một số tên miền từ server. Ví dụ: giHgWHwkLf0Rd0.io, I7jpjuRw13D.io, wXf6GP3KP.vn, MdpIzhxDVtTFTF.edu, TUHuMfn25chmw.vn, HHjE9.com, 4hJld2m2yiweto.vn, y2L4SQwH.vn, s2aUrZGdzS.com, 4hXfJe9giAA.edu
 * c. Tìm kiếm các tên miền .edu và gửi lên server. Ví dụ: MdpIzhxDVtTFTF.edu, 4hXfJe9giAA.edu
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.io.*;
import java.util.*;
import java.net.*;

public class Character01_LocEdu {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2208);
        socket.setSoTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B23DCCN139;kCAeDRzX");
        out.newLine(); out.flush();
        String s = in.readLine();
        String[] parts = s.split(", ");
        StringBuilder sb = new StringBuilder();
        for (String x : parts) { if (x.trim().endsWith(".edu")) { sb.append(x.trim()).append(", "); } }
        String kq = sb.toString();
        if (kq.endsWith(", ")) { kq = kq.substring(0, kq.length() - 2); }
        out.write(kq);
        out.newLine(); out.flush();
        in.close(); out.close(); socket.close();
    }
}
