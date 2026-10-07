package TCP;

/**
 * Data Stream (cổng 2207) – Bài 4: Caesar Giải Mã
 * Mã câu hỏi (qCode): NYk58hZP
 *
 * Mật mã caesar, còn gọi là mật mã dịch chuyển, để giải mã thì mỗi ký tự nhận được sẽ được thay thế bằng một ký tự cách nó một đoạn s. Ví dụ: với s = 3 thì ký tự A sẽ được thay thế bằng ký tự D.
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2207 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng chương trình client tương tác với server trên, sử dụng các luồng byte (DataInputStream/DataOutputStream) để trao đổi thông tin theo thứ tự:
 * a. Gửi một chuỗi gồm mã sinh viên và mã câu hỏi theo định dạng studentCode;qCode. Ví dụ: B15DCCN999;D68C93F7
 * b. Nhận lần lượt chuỗi đã bị mã hóa caesar và giá trị dịch chuyển s nguyên
 * c. Thực hiện giải mã ra thông điệp ban đầu và gửi lên Server
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.io.*;
import java.net.*;

public class Data04_CaesarGiaiMa {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2207);
        socket.setSoTimeout(5000);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF("B23DCCN139;NYk58hZP");
        String encoded = in.readUTF();
        int s = in.readInt();
        s = ((s % 26) + 26) % 26;
        StringBuilder sb = new StringBuilder();
        for (char c : encoded.toCharArray()) {
            if (c >= 'A' && c <= 'Z') { sb.append((char) ('A' + (c - 'A' - s + 26) % 26)); }
            else if (c >= 'a' && c <= 'z') { sb.append((char) ('a' + (c - 'a' - s + 26) % 26)); }
            else { sb.append(c); }
        }
        out.writeUTF(sb.toString());
        in.close(); out.close(); socket.close();
    }
}
