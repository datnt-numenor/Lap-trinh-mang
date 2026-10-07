package TCP;

/**
 * Character Stream (cổng 2208) – Bài 3: Đảo Ngược Chuỗi
 * CHƯA CÓ qCode trong tài liệu: thay QCODE/qCode bằng mã câu hỏi thật
 *
 * Một chương trình server tại địa chỉ 172.188.19.218 cho phép kết nối qua giao thức TCP tại cổng 1606 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng byte (BufferedWriter/BufferedReader) theo kịch bản sau:
 * a. Gửi chuỗi là mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B20DCCN999;ABCDEF"
 * b. Nhận một chuỗi từ server.
 * c. Thực hiện đảo ngược lại chuỗi và gửi lên server.
 * d. Đóng kết nối và kết thúc.
 */

import java.util.*;
import java.io.*;
import java.net.*;

public class Character03_DaoNguocChuoi {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("172.188.19.218",1606);
        socket.setSoTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B23DCCN139;QCODE");
        out.newLine(); out.flush();
        String s = in.readLine();
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        String kq = sb.toString();
        out.write(kq);
        out.newLine(); out.flush();
        in.close(); out.close(); socket.close();
    }
}
