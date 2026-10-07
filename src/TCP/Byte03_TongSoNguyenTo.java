package TCP;

/**
 * Byte Stream (cổng 2206) – Bài 3: Tổng Số Nguyên Tố
 * CHƯA CÓ qCode trong tài liệu: thay QCODE/qCode bằng mã câu hỏi thật
 *
 * Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng chương trình client thực hiện kết nối tới server sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;C89DAB45"
 * b. Nhận dữ liệu từ server là một chuỗi các số nguyên được phân tách bởi ký tự ",". Ví dụ: "8,4,2,10,5,6,1,3"
 * c. Tính tổng của tất cả các số nguyên tố trong chuỗi và gửi kết quả lên server. Ví dụ: Với dãy "8,4,2,10,5,6,1,3", các số nguyên tố là 2, 5, 3, tổng là 10. Gửi lên server chuỗi "10".
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.util.*;
import java.io.*;
import java.net.*;

public class Byte03_TongSoNguyenTo {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242", 2206);
        socket.setSoTimeout(5000);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        out.write("B23DCCN139;QCODE".getBytes());
        out.flush();
        byte[]buffer = new byte[1024];
        int len = in.read(buffer);
        String s = new String(buffer, 0, len).trim();
        String[]parts = s.split("\\,");
        ArrayList<Integer> ds = new ArrayList<>();
        for(String x : parts){ ds.add(Integer.parseInt(x.trim())); }
        int tong = 0;
        for(int i = 0; i <= ds.size() - 1; i++){
            if(nt(ds.get(i))){ tong += ds.get(i); }
        }
        String kq = tong + "";
        out.write(kq.getBytes());
        out.flush();
        in.close(); out.close(); socket.close();
    }
    public static boolean nt(int n){
        if(n < 2) return false;
        for(int i = 2; i <= Math.sqrt(n); i++){
            if(n % i == 0) return false;
        }
        return true;
    }
}
