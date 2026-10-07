package TCP;

/**
 * Byte Stream (cổng 2206) – Bài 2: Lớn Thứ Hai
 * Mã câu hỏi (qCode): uELfDKlC
 *
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2206 (thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác tới server ở trên sử dụng các luồng byte (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;2B3A6510"
 * b. Nhận dữ liệu từ server là một chuỗi các giá trị số nguyên được phân tách nhau bởi ký tự ",". Ví dụ: 1,3,9,19,33,20
 * c. Tìm và gửi lên server giá trị lớn thứ hai cùng vị trí xuất hiện của nó trong chuỗi. Ví dụ: 20,5
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.io.*;
import java.util.*;
import java.net.*;

public class Byte02_LonThuHai {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242", 2206);
        socket.setSoTimeout(5000);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        out.write("B23DCCN139;uELfDKlC".getBytes());
        out.flush();
        byte[]buffer = new byte[1024];
        int len = in.read(buffer);
        String s = new String(buffer, 0, len).trim();
        String[]parts = s.split("\\,");
        ArrayList<Integer> ds = new ArrayList<>();
        for(String x : parts){ ds.add(Integer.parseInt(x.trim())); }
        int maxx = Integer.MIN_VALUE;
        int num1 = 0, num2 = 0;
        int vtri1 = 0, vtri2 = 0;
        for(int i = 0; i <= ds.size() - 1; i++){
            if(ds.get(i) > maxx){ maxx = ds.get(i); num1 = maxx; vtri1 = i; }
        }
        for(int i = 0; i <= ds.size() - 1; i++){
            if(i != vtri1 && ds.get(i) > num2){ num2 = ds.get(i); vtri2 = i; }
        }
        String kq = num2 + "," + vtri2;
        out.write(kq.getBytes());
        in.close(); out.close(); socket.close();
    }
}
