package TCP;

/**
 * Byte Stream (cổng 2206) – Bài 7: Hai Số Gần Trung Bình
 * Mã câu hỏi (qCode): TL9Pol9D
 *
 * Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng chương trình client thực hiện kết nối tới server sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;D45EFA12"
 * b. Nhận dữ liệu từ server là một chuỗi các số nguyên được phân tách bởi ký tự ",". Ví dụ: "10,5,15,20,25,30,35"
 * c. Xác định hai số trong dãy có tổng gần nhất với gấp đôi giá trị trung bình. Ví dụ: gấp đôi trung bình = 40, hai số gần nhất là 15 và 25 → gửi "15,25"
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.util.*;
import java.io.*;
import java.net.*;

public class Byte07_HaiSoGanTrungBinh {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        String code = "B23DCCN139;TL9Pol9D";
        out.write(code.getBytes());
        out.flush();
        byte[] buffer = new byte[1024];
        int len = in.read(buffer);
        String s = new String(buffer, 0, len).trim();
        String[] parts = s.split(",");
        int[] arr = new int[parts.length];
        long sum = 0;
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i].trim());
            sum += arr[i];
        }
        double target = 2.0 * sum / arr.length;
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        int left = 0, right = sorted.length - 1;
        int bestA = sorted[0], bestB = sorted[1];
        double bestDiff = Double.MAX_VALUE;
        while (left < right) {
            double curSum = sorted[left] + sorted[right];
            double diff = Math.abs(curSum - target);
            if (diff < bestDiff) { bestDiff = diff; bestA = sorted[left]; bestB = sorted[right]; }
            if (curSum < target) left++;
            else right--;
        }
        String result = bestA + "," + bestB;
        out.write(result.getBytes());
        out.flush();
        socket.close();
    }
}
