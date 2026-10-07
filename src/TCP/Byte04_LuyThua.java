package TCP;

/**
 * Byte Stream (cổng 2206) – Bài 4: Lũy Thừa
 * Mã câu hỏi (qCode): nxMRj8z
 *
 * Một chương trình server tại địa chỉ 172.188.19.218 hỗ trợ kết nối qua giao thức TCP tại cổng 1604 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng chương trình client thực hiện kết nối tới server trên sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B20DCCN999;nxMRj8z"
 * b. Nhận dữ liệu từ server là một chuỗi gồm hai giá trị nguyên a, b được phân tách với nhau bằng "|". Ví dụ: 2|5
 * c. Thực hiện tìm giá trị a^b và gửi lên server. Ví dụ: 32
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.util.*;
import java.io.*;
import java.net.*;

public class Byte04_LuyThua {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("172.188.19.218", 1604);
        socket.setSoTimeout(5000);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        out.write("B23DCCN139;nxMRj8z".getBytes());
        out.flush();
        byte[] buffer = new byte[1024];
        int len = in.read(buffer);
        String s = new String(buffer,0,len).trim();
        String[]parts = s.split("\\|");
        ArrayList<Integer> ds = new ArrayList<>();
        for(String x : parts){ ds.add(Integer.parseInt(x.trim())); }
        int a = ds.get(0);
        int b = ds.get(1);
        long luythua = (long)Math.pow(a, b);
        String kq = luythua + "";
        out.write(kq.getBytes());
        out.flush();
        in.close(); out.close(); socket.close();
    }
}
