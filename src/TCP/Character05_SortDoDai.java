package TCP;

/**
 * Character Stream (cổng 2208) – Bài 5: Sort Độ Dài
 * Mã câu hỏi (qCode): we3kcWxZ
 *
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng ký tự (BufferedReader/BufferedWriter) theo kịch bản sau:
 * a. Gửi một chuỗi chứa mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;C1234567"
 * b. Nhận từ server một chuỗi chứa nhiều từ, các từ được phân tách bởi khoảng trắng. Ví dụ: "hello world this is a test example"
 * c. Sắp xếp các từ trong chuỗi theo độ dài, thứ tự xuất hiện. Gửi danh sách các từ theo từng nhóm về server theo định dạng: "a, is, this, test, hello, world, example".
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.util.*;
import java.io.*;
import java.net.*;

public class Character05_SortDoDai {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242",2208);
        socket.setSoTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B23DCCN139;we3kcWxZ");
        out.newLine(); out.flush();
        String s = in.readLine();
        String[] parts = s.split(" ");
        ArrayList<String> ds = new ArrayList<>();
        for(String x : parts){ ds.add(x.trim()); }
        Collections.sort(ds, (a,b) -> a.length() - b.length());
        StringBuilder sb = new StringBuilder();
        for(String x : ds){ sb.append(x.trim()).append(" "); }
        String kq = sb.toString();
        if(kq.endsWith(" ")){ kq = kq.substring(0, kq.length() - 1); }
        out.write(kq);
        out.newLine(); out.flush();
        in.close(); out.close(); socket.close();
    }
}
