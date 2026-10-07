package TCP;

/**
 * Byte Stream (cổng 2206) – Bài 10: Vị Trí Cân Bằng
 * Mã câu hỏi (qCode): zmNHK0Y7
 *
 * Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng chương trình client thực hiện kết nối tới server sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;E56FAB67"
 * b. Nhận dữ liệu từ server là một chuỗi các số nguyên được phân tách bởi ký tự ",". Ví dụ: "3,7,2,5,8,1"
 * c. Tìm vị trí mà độ lệch của tổng bên trái và tổng bên phải là nhỏ nhất. Gửi lên server vị trí đó, tổng trái, tổng phải và độ lệch. Ví dụ: với dãy "3,7,2,5,8,1", vị trí 3 có độ lệch nhỏ nhất = 3 → Kết quả gửi server: "3,12,9,3"
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.util.*;
import java.io.*;
import java.net.*;

public class Byte10_ViTriCanBang {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        out.write("B23DCCN139;zmNHK0Y7".getBytes());
        out.flush();
        byte[] buffer = new byte[1024];
        int len = in.read(buffer);
        String s = new String(buffer, 0, len).trim();
        String[] parts = s.split(",");
        int[] arr = new int[parts.length];
        int totalSum = 0;
        for (int i = 0; i < parts.length; i++) { arr[i] = Integer.parseInt(parts[i].trim()); totalSum += arr[i]; }
        int bestPos = 1;
        int bestLeft = 0, bestRight = totalSum - arr[0];
        int bestDiff = Math.abs(bestLeft - bestRight);
        int leftSum = 0;
        for (int i = 0; i < arr.length; i++) {
            int rightSum = totalSum - leftSum - arr[i];
            int diff = Math.abs(leftSum - rightSum);
            if (diff < bestDiff) { bestDiff = diff; bestPos = i + 1; bestLeft = leftSum; bestRight = rightSum; }
            leftSum += arr[i];
        }
        String result = bestPos + "," + bestLeft + "," + bestRight + "," + bestDiff;
        out.write(result.getBytes());
        out.flush();
        socket.close();
    }
}
