package TCP;

/**
 * Data Stream (cổng 2207) – Bài 3: UCLN + BCNN + Tổng + Tích
 * Mã câu hỏi (qCode): nkBwM6AE
 *
 * Một chương trình máy chủ cho phép kết nối qua TCP tại cổng 2207 (hỗ trợ thời gian liên lạc tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng chương trình client tương tác với server trên bằng các byte stream (DataInputStream/DataOutputStream) để trao đổi thông tin theo trình tự sau:
 * a. Gửi một chuỗi chứa mã sinh viên và mã câu hỏi ở định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;B1F1FDCD"
 * b. Nhận hai số nguyên a và b tương ứng từ máy chủ
 * c. Tính ước chung lớn nhất, bội chung nhỏ nhất, tổng, tích. Gửi từng giá trị số nguyên theo thứ tự trên đến máy chủ.
 * d. Đóng kết nối và kết thúc chương trình.
 */

import java.io.*;
import java.util.*;
import java.net.*;

public class Data03_UCLNBCNNTongTich {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242",2207);
        socket.setSoTimeout(5000);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF("B23DCCN139;nkBwM6AE");
        out.flush();
        int a = in.readInt();
        int b = in.readInt();
        int uc = ucln(a,b);
        int bc = bcnn(a,b);
        int tong = a + b;
        int tich = a * b;
        out.writeInt(uc);
        out.writeInt(bc);
        out.writeInt(tong);
        out.writeInt(tich);
        in.close(); out.close(); socket.close();
    }
    public static int ucln(int a, int b){
        while(b != 0){ int r = a % b; a = b; b = r; }
        return a;
    }
    public static int bcnn(int a, int b){ return a / ucln(a, b) * b; }
}
