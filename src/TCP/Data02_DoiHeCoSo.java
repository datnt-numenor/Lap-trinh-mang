package TCP;

/**
 * Data Stream (cổng 2207) – Bài 2: Đổi Hệ Cơ Số
 * Mã câu hỏi (qCode): 0LTGyX4p
 *
 * Một chương trình server cho phép kết nối qua TCP tại cổng 2207 (hỗ trợ thời gian liên lạc tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu xây dựng chương trình client thực hiện giao tiếp với server sử dụng luồng data (DataInputStream/DataOutputStream) để trao đổi thông tin theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B10DCCN001;A1B2C3D4"
 * b. Nhận một số nguyên hệ thập phân từ server. Ví dụ: 255
 * c. Chuyển đổi số nguyên nhận được sang hai hệ cơ số 8 và 16. Gửi lần lượt chuỗi kết quả lên server. Ví dụ: Với số 255 hệ thập phân, kết quả gửi lên sẽ là một chuỗi dạng "377;FF"
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.io.*;
import java.net.*;

public class Data02_DoiHeCoSo {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2207);
        socket.setSoTimeout(5000);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF("B23DCCN139;0LTGyX4p");
        int n = in.readInt();
        String octal = Integer.toOctalString(n);
        String hex = Integer.toHexString(n).toUpperCase();
        String result = octal + ";" + hex;
        out.writeUTF(result);
        in.close(); out.close(); socket.close();
    }
}
