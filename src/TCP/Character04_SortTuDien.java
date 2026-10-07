package TCP;

/**
 * Character Stream (cổng 2208) – Bài 4: Sort Từ Điển
 * Mã câu hỏi (qCode): lXo9m21K
 *
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu là xây dựng một chương trình client thực hiện kết nối tới server và sử dụng luồng ký tự (BufferedWriter/BufferedReader) để trao đổi thông tin theo kịch bản sau:
 * a. Gửi một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;X1107ABC".
 * b. Nhận từ server một chuỗi ngẫu nhiên chứa nhiều từ, các từ phân tách bởi khoảng trắng.
 * c. Thực hiện các bước xử lý: Bước 1: Tách chuỗi thành các từ dựa trên khoảng trắng. Bước 2: Sắp xếp các từ theo thứ tự từ điển (có phân biệt chữ cái hoa thường).
 * d. Gửi lại chuỗi đã sắp xếp theo thứ tự từ điển lên server.
 */

import java.util.*;
import java.io.*;
import java.net.*;

public class Character04_SortTuDien {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("172.188.19.218",1606);
        socket.setSoTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B23DCCN139;lXo9m21K");
        out.newLine(); out.flush();
        String s = in.readLine();
        String[]parts = s.split(" ");
        ArrayList<String> ds = new ArrayList<>();
        for(String x : parts){ ds.add(x.trim()); }
        Collections.sort(ds);
        StringBuilder sb = new StringBuilder();
        for(String x : ds){ sb.append(x.trim()).append(" "); }
        String kq = sb.toString();
        if(kq.endsWith(" ")){ kq = kq.substring(0, kq.length() - 1); }
        out.write(kq);
        out.newLine(); out.flush();
        in.close(); out.close(); socket.close();
    }
}
