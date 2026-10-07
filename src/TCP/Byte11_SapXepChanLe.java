package TCP;

/**
 * Byte Stream (cổng 2206) – Bài 11: Sắp Xếp Chẵn Lẻ
 * Mã câu hỏi (qCode): rMdCliDV
 *
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2206 (thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác tới server ở trên sử dụng các luồng byte (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;A63D9404"
 * b. Nhận dữ liệu từ server là một chuỗi các số nguyên được sắp xếp ngẫu nhiên, các số được phân tách nhau bởi ký tự ",". Ví dụ: "2,15,4,3,6,8,10,7,1"
 * c. Sắp xếp tăng dần các giá trị chẵn và sau đó tăng dần các giá trị lẻ trong dãy số. Ví dụ: "[2, 4, 6, 8, 10];[1, 3, 7, 15]". Gửi chuỗi được sắp xếp này lên server.
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.util.*;
import java.io.*;
import java.net.*;

public class Byte11_SapXepChanLe {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        out.write("B23DCCN139;rMdCliDV".getBytes());
        out.flush();
        byte[] buffer = new byte[1024];
        int len = in.read(buffer);
        String s = new String(buffer, 0, len).trim();
        String[] parts = s.split(",");
        List<Integer> even = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();
        for (String p : parts) {
            int n = Integer.parseInt(p.trim());
            if (n % 2 == 0) even.add(n);
            else odd.add(n);
        }
        Collections.sort(even); Collections.sort(odd);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < even.size(); i++) { if (i > 0) sb.append(", "); sb.append(even.get(i)); }
        sb.append("];[");
        for (int i = 0; i < odd.size(); i++) { if (i > 0) sb.append(", "); sb.append(odd.get(i)); }
        sb.append("]");
        out.write(sb.toString().getBytes());
        out.flush();
        socket.close();
    }
}
