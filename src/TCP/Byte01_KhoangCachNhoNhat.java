package TCP;

/**
 * Byte Stream (cổng 2206) – Bài 1: Khoảng Cách Nhỏ Nhất
 * Mã câu hỏi (qCode): zurZGVAs
 *
 * Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng chương trình client thực hiện kết nối tới server trên sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;FF49DC02"
 * b. Nhận dữ liệu từ server là một chuỗi các giá trị số nguyên được phân tách nhau bởi ký tự "," Ex: 1,3,9,19,33,20
 * c. Thực hiện tìm giá trị khoảng cách nhỏ nhất của các phần tử nằm trong chuỗi và hai giá trị lớn nhất tạo nên khoảng cách đó. Gửi lên server chuỗi gồm "khoảng cách nhỏ nhất, số thứ nhất, số thứ hai". Ex: 1,19,20
 * d. Đóng kết nối và kết thúc
 */

import java.io.*;
import java.util.*;
import java.net.*;

public class Byte01_KhoangCachNhoNhat {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242", 2206);
        socket.setSoTimeout(5000);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        out.write("B23DCCN139;zurZGVAs".getBytes());
        out.flush();
        byte[]buffer = new byte[1024];
        int len = in.read(buffer);
        String s = new String(buffer, 0, len).trim();
        String[]parts = s.split("\\,");
        ArrayList<Integer> ds = new ArrayList<>();
        for(String x : parts){
            ds.add(Integer.parseInt(x.trim()));
        }
        Collections.sort(ds);
        int minn = Integer.MAX_VALUE;
        int num1 = 0, num2 = 0;
        for(int i = 0; i <= ds.size() - 2; i++){
            int hieu = Math.abs(ds.get(i) - ds.get(i + 1));
            if(hieu < minn){
                minn = hieu;
                num1 = ds.get(i);
                num2 = ds.get(i+1);
            }
        }
        String kq = minn + "," + num1 + "," + num2;
        out.write(kq.getBytes());
        out.flush();
        in.close(); out.close(); socket.close();
    }
}
