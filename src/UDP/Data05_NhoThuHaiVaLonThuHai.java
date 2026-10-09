package UDP;

/**
 * UDP Data (cổng 2207) – Bài A5: NHỎ THỨ HAI VÀ LỚN THỨ HAI
 * Mã câu hỏi (qCode): oQifsr90
 *
 * [Mã câu hỏi (qCode): oQifsr90]. Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
 * a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN004;99D9F604"
 * b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;z1,z2,...,z50". requestId là chuỗi ngẫu nhiên duy nhất. z1 -> z50 là 50 số nguyên ngẫu nhiên.
 * c. Thực hiện tính số lớn thứ hai và số nhỏ thứ hai trong z1 -> z50 và gửi thông điệp lên server theo định dạng "requestId;secondMax,secondMin"
 * d. Đóng socket và kết thúc chương trình.
 *
 * Ghi chú:
 * Nếu WA: đề không nói rõ khi có giá trị trùng nhau. Code đang không bỏ trùng. Nếu WA thì thử bỏ trùng bằng TreeSet rồi lấy phần tử thứ 2 từ đầu và từ cuối.
 */

import java.util.*;
import java.net.*;
import java.io.*;

public class Data05_NhoThuHaiVaLonThuHai {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int port = 2207;
        String code = ";B23DCCN139;oQifsr90";
        socket.send(new DatagramPacket(code.getBytes(), code.length(), sA, port));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String s = new String(nhan.getData(), 0, nhan.getLength());
        String[] parts = s.trim().split(";");
        String requestId = parts[0];
        ArrayList<Integer> ds = new ArrayList<>();
        for (String x : parts[1].split(",")) ds.add(Integer.parseInt(x.trim()));
        Collections.sort(ds);
        int secondMin = ds.get(1);
        int secondMax = ds.get(ds.size() - 2);
        String kq = requestId + ";" + secondMax + "," + secondMin;
        socket.send(new DatagramPacket(kq.getBytes(), kq.length(), sA, port));
        socket.close();
    }
}
