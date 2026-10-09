package UDP;

/**
 * UDP Data (cổng 2207) – Bài A2: PHẦN TỬ LỚN NHẤT CỦA DÃY CON
 * Mã câu hỏi (qCode): iv00Hrq6 / mhH9GbNw
 *
 * [Mã câu hỏi (qCode): iv00Hrq6]. Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
 * a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B21DCCN795;ylrhZ6UM".
 * b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;n;k;z1,z2,...,zn", trong đó: requestId là chuỗi ngẫu nhiên duy nhất. n là số phần tử của mảng. k là kích thước cửa sổ trượt (k < n). z1 đến zn là n phần tử số nguyên của mảng.
 * c. Thực hiện tìm giá trị lớn nhất trong mỗi cửa sổ trượt với kích thước k, gửi theo định dạng "requestId;max1,max2,...,maxm". Ví dụ: "requestId;5;3;1,5,2,3,4" → Kết quả: "requestId;5,5,4"
 * d. Đóng socket và kết thúc chương trình.
 * Lưu ý: Hệ thống còn có mã câu hỏi mhH9GbNw cùng đề này: chỉ đổi qCode.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class Data02_PhanTuLonNhatCuaDayCon {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2207;
        String code = ";B23DCCN139;iv00Hrq6";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String s = new String(nhan.getData(), 0, nhan.getLength());
        String[] parts = s.trim().split(";");
        String requestId = parts[0];
        int k = Integer.parseInt(parts[2].trim());
        String[] zs = parts[3].split(",");
        int n = zs.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(zs[i].trim());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i + k <= n; i++) {
            int max = arr[i];
            for (int j = i; j < i + k; j++) {
                if (arr[j] > max) max = arr[j];
            }
            if (sb.length() > 0) sb.append(",");
            sb.append(max);
        }
        String kq = requestId + ";" + sb.toString();
        socket.send(new DatagramPacket(kq.getBytes(), kq.length(), sA, port));
        socket.close();
    }
}
