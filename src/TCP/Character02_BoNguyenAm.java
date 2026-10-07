package TCP;

/**
 * Character Stream (cổng 2208) – Bài 2: Bỏ Nguyên Âm
 * Mã câu hỏi (qCode): x8c45mq
 *
 * Một chương trình server tại địa chỉ 172.188.19.218 cho phép kết nối qua giao thức TCP tại cổng 1606 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng byte (BufferedWriter/BufferedReader) theo kịch bản sau:
 * a. Gửi chuỗi là mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B20DCCN999;ABCDEF" với ABCDEF là mã bài tập đã đề cập ở trên.
 * b. Nhận một chuỗi từ server (Chỉ chứa kí tự thường).
 * c. Thực hiện loại bỏ các nguyên âm trong chuỗi và gửi kết quả lên server.
 * d. Đóng kết nối và kết thúc.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class Character02_BoNguyenAm {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("172.188.19.218",1606);
        socket.setSoTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B23DCCN139;x8c45mq");
        out.newLine(); out.flush();
        String s = in.readLine();
        String[]parts = s.split("");
        String nguyenam = "ueoai";
        StringBuilder sb = new StringBuilder();
        for(String x : parts){ if(!nguyenam.contains(x)){ sb.append(x); } }
        String kq = sb.toString();
        out.write(kq);
        out.newLine(); out.flush();
        in.close(); out.close(); socket.close();
    }
}
