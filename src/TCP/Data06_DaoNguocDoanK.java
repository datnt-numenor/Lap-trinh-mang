package TCP;

/**
 * Data Stream (cổng 2207) – Bài 6: Đảo Ngược Đoạn K
 * Mã câu hỏi (qCode): dCNDHojG
 *
 * Một chương trình server cho phép kết nối qua TCP tại cổng 2207 (hỗ trợ thời gian liên lạc tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu xây dựng chương trình client thực hiện giao tiếp với server sử dụng luồng data (DataInputStream/DataOutputStream) để trao đổi thông tin theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B10DCCN003;C6D7E8F9"
 * b. Nhận lần lượt: Một số nguyên k là độ dài đoạn. Chuỗi chứa mảng số nguyên, các phần tử được phân tách bởi dấu phẩy ",". Ví dụ: Nhận k = 3 và "1,2,3,4,5,6,7,8".
 * c. Thực hiện chia mảng thành các đoạn có độ dài k và đảo ngược mỗi đoạn, sau đó gửi mảng đã xử lý lên server. Ví dụ: Với k = 3 và mảng "1,2,3,4,5,6,7,8", kết quả là "3,2,1,6,5,4,8,7". Gửi chuỗi kết quả "3,2,1,6,5,4,8,7" lên server.
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class Data06_DaoNguocDoanK {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2207);
        socket.setSoTimeout(5000);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF("B23DCCN139;dCNDHojG");
        int k = in.readInt();
        String s = in.readUTF();
        String[] parts = s.split(",");
        int n = parts.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) { arr[i] = Integer.parseInt(parts[i].trim()); }
        for (int i = 0; i < n; i += k) {
            int left = i, right = Math.min(i + k, n) - 1;
            while (left < right) { int tmp = arr[left]; arr[left] = arr[right]; arr[right] = tmp; left++; right--; }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) { if (i > 0) sb.append(","); sb.append(arr[i]); }
        out.writeUTF(sb.toString());
        in.close(); out.close(); socket.close();
    }
}
