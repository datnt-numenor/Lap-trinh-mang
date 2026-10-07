package TCP;

/**
 * Character Stream (cổng 2208) – Bài 10: Vị Trí Cân Bằng CHARACTER
 * Mã câu hỏi (qCode): zmNHK0Y7
 *
 * Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2208 (thời gian tối đa 5s). Xây dựng chương trình client sử dụng luồng ký tự (BufferedReader/BufferedWriter) trao đổi theo thứ tự:
 * a. Gửi mã sinh viên và mã câu hỏi: "studentCode;qCode"
 * b. Nhận chuỗi số nguyên phân tách bởi dấu ",". Ví dụ: 1,3,5,2,4
 * c. Tìm vị trí index i (không phải đầu/cuối) sao cho |tổng trái - tổng phải| là nhỏ nhất. Gửi lên server chuỗi: vị trí,tổng trái,tổng phải,độ lệch. Ví dụ: 2,4,6,2
 * d. Đóng kết nối
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class Character10_ViTriCanBang {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2208);
        socket.setSoTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B23DCCN139;zmNHK0Y7");
        out.newLine(); out.flush();
        String s = in.readLine();
        String[] parts = s.trim().split(",");
        ArrayList<Integer> ds = new ArrayList<>();
        for (String x : parts) ds.add(Integer.parseInt(x.trim()));
        int n = ds.size();
        int pos = 0, tongTrai = 0, tongPhai = 0;
        int doLech = Integer.MAX_VALUE;
        for (int i = 1; i < n - 1; i++) {
            int trai = 0, phai = 0;
            for (int j = 0; j < i; j++) trai += ds.get(j);
            for (int j = i + 1; j < n; j++) phai += ds.get(j);
            int hieu = Math.abs(trai - phai);
            if (hieu < doLech) { doLech = hieu; pos = i; tongTrai = trai; tongPhai = phai; }
        }
        String kq = pos + "," + tongTrai + "," + tongPhai + "," + doLech;
        out.write(kq);
        out.newLine(); out.flush();
        in.close(); out.close(); socket.close();
    }
}
