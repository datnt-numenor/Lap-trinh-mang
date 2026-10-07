package TCP;

/**
 * Byte Stream (cổng 2206) – Bài 6: Collatz
 * Mã câu hỏi (qCode): 2B3A6510
 *
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2206 (thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác tới server ở trên sử dụng các luồng byte (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;2B3A6510"
 * b. Nhận dữ liệu từ server là một số nguyên n nhỏ hơn 400. Ví dụ: 7
 * c. Thực hiện các bước sau đây để sinh ra chuỗi từ số nguyên n ban đầu và gửi lên server:
 * - Nếu n là số chẵn → n1 = n / 2
 * - Nếu n là số lẻ → n1 = 3n + 1
 * Lặp lại cho đến khi n = 1. Kết quả theo format "chuỗi kết quả; độ dài". Ví dụ: n=7 → "7 22 11 34 17 52 26 13 40 20 10 5 16 8 4 2 1; 17"
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class Byte06_Collatz {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242", 2206);
        socket.setSoTimeout(5000);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        out.write("B23DCCN139;2B3A6510".getBytes());
        out.flush();
        byte[]buffer = new byte[1024];
        int len = in.read(buffer);
        String s = new String(buffer,0,len).trim();
        String[]parts = s.split(",");
        ArrayList<Integer> ds = new ArrayList<>();
        for(String x : parts){ ds.add(Integer.parseInt(x.trim())); }
        int n = ds.get(0);
        StringBuilder sb = new StringBuilder();
        int stt = 1;
        sb.append(n);
        while(n != 1){
            if(n % 2 == 0) n = n /2;
            else n = 3 * n + 1;
            sb.append("," + n);
            stt++;
        }
        String kq = sb.toString() + ";" + stt;
        out.write(kq.getBytes());
        out.flush();
        in.close(); out.close(); socket.close();
    }
}
