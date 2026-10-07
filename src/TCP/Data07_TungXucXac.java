package TCP;

/**
 * Data Stream (cổng 2207) – Bài 7: Tung Xúc Xắc
 * Mã câu hỏi (qCode): PpWEQ6F0
 *
 * Một chương trình server cho phép kết nối qua TCP tại cổng 2207 (hỗ trợ thời gian liên lạc tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu là xây dựng chương trình client tương tác với server bằng các byte stream (DataInputStream/DataOutputStream) để trao đổi thông tin theo trình tự sau:
 * a. Gửi một chuỗi chứa mã sinh viên và mã câu hỏi ở định dạng "studentCode;qCode". Ví dụ: "B10DCCN000;0D135D6A".
 * b. Nhận từ server một số nguyên n, là số lần tung xúc xắc. Ví dụ: Nếu bạn nhận được n = 21 từ máy chủ, có nghĩa bạn sẽ nhận giá trị tung xúc xắc 21 lần. Nhận từ server các giá trị sau mỗi lần tung xúc xắc. Ví dụ: Server gửi lần lượt 21 giá trị là 1,6,4,4,4,3,2,6,3,4,5,4,5,2,4,5,4,6,1,5,5
 * c. Tính xác suất xuất hiện của các giá trị [1,2,3,4,5,6] khi tung xúc sắc và gửi lần lượt xác suất này (dưới dạng float) lên server theo đúng thứ tự.
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.io.*;
import java.net.*;

public class Data07_TungXucXac {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2207);
        socket.setSoTimeout(5000);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF("B23DCCN139;PpWEQ6F0");
        int n = in.readInt();
        int[] count = new int[6];
        for (int i = 0; i < n; i++) { int val = in.readInt(); count[val - 1]++; }
        for (int i = 0; i < 6; i++) { out.writeFloat((float) count[i] / n); }
        in.close(); out.close(); socket.close();
    }
}
