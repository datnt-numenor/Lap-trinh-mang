package TCP;

/**
 * Byte Stream (cổng 2206) – Bài 5: Tổng Tuyệt Đối
 * Mã câu hỏi (qCode): PUh9Ki1
 *
 * Một chương trình server tại địa chỉ 172.188.19.218 hỗ trợ kết nối qua giao thức TCP tại cổng 1604 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng chương trình client thực hiện kết nối tới server trên sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B20DCCN999;PUh9Ki1"
 * b. Nhận dữ liệu từ server là một chuỗi gồm các giá trị nguyên được phân tách với nhau bằng "|". Ví dụ: 2|5|9|11
 * c. Thực hiện tìm giá trị tổng của các số nguyên trong chuỗi và gửi lên server. Ví dụ: 27
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.util.*;
import java.io.*;
import java.net.*;

public class Byte05_TongTuyetDoi {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("172.188.19.218",1604);
        socket.setSoTimeout(5000);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        out.write("B23DCCN139;PUh9Ki1".getBytes());
        out.flush();
        byte[]buffer = new byte[1024];
        int len = in.read(buffer);
        String s = new String(buffer,0,len).trim();
        String[]parts = s.split("\\|");
        ArrayList<Integer> ds = new ArrayList<>();
        for(String x : parts){ ds.add(Integer.parseInt(x.trim())); }
        long tong = 0;
        for(int i = 0; i <= ds.size() - 1; i++){ tong += ds.get(i); }
        String kq = tong + "";
        out.write(kq.getBytes());
        out.flush();
        in.close(); out.close(); socket.close();
    }
}
