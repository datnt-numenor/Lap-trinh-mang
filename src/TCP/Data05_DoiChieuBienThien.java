package TCP;

/**
 * Data Stream (cổng 2207) – Bài 5: Đổi Chiều Biến Thiên
 * Mã câu hỏi (qCode): oNGj55wV
 *
 * Một chương trình server cho phép kết nối qua TCP tại cổng 807 (hỗ trợ thời gian liên lạc tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu xây dựng chương trình client thực hiện giao tiếp với server sử dụng luồng data (DataInputStream/DataOutputStream) để trao đổi thông tin theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B10DCCN002;B4C5D6E7"
 * b. Nhận chuỗi chứa mảng số nguyên từ server, các phần tử được phân tách bởi dấu phẩy ",". Ví dụ: "1,3,2,5,4,7,6"
 * c. Tính số lần đổi chiều và tổng độ biến thiên trong dãy số. Đổi chiều: Khi dãy chuyển từ tăng sang giảm hoặc từ giảm sang tăng. Độ biến thiên: Tổng giá trị tuyệt đối của các hiệu số liên tiếp. Gửi lần lượt lên server: số nguyên đại diện cho số lần đổi chiều, sau đó là số nguyên đại diện cho tổng độ biến thiên. Ví dụ: Với mảng "1,3,2,5,4,7,6", số lần đổi chiều: 5 lần, Tổng độ biến thiên 11 → Gửi lần lượt số nguyên 5 và 11 lên server.
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.io.*;
import java.net.*;

public class Data05_DoiChieuBienThien {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 807);
        socket.setSoTimeout(5000);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF("B23DCCN139;oNGj55wV");
        String s = in.readUTF();
        String[] parts = s.split(",");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) { arr[i] = Integer.parseInt(parts[i].trim()); }
        int doiChieu = 0, bienThien = 0;
        for (int i = 1; i < arr.length; i++) { bienThien += Math.abs(arr[i] - arr[i - 1]); }
        for (int i = 2; i < arr.length; i++) {
            int prev = arr[i - 1] - arr[i - 2];
            int curr = arr[i] - arr[i - 1];
            if ((prev > 0 && curr < 0) || (prev < 0 && curr > 0)) { doiChieu++; }
        }
        out.writeInt(doiChieu);
        out.writeInt(bienThien);
        in.close(); out.close(); socket.close();
    }
}
