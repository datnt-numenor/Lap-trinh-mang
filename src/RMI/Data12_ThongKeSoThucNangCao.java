/* ===== DB-Lab - [RMI Data] Thống kê số thực nâng cao | qCode: HNCj8VEs ===== */
/*
Một chương trình RMI Server cung cấp giao diện DataService trong package RMI, được đăng ký với RegistryServer bằng tên RMIDataService.
a. Sử dụng mã sinh viên và mã câu hỏi làm tham số khi gọi RMI.
b. Triệu gọi requestData(studentCode, qCode) để nhận chuỗi CSV các số thực. Ví dụ: 10.00,20.00,30.00,40.00.
c. Tính giá trị trung bình, độ lệch chuẩn trên toàn bộ dãy và phân vị 95 tại vị trí ceil(n * 0.95) - 1 sau khi sắp xếp tăng dần.
d. Triệu gọi submitData(studentCode, qCode, data) để gửi chuỗi average=<avg>;stddev=<stddev>;p95=<p95>, các số thực làm tròn 02 chữ số thập phân. Ví dụ: average=25.00;stddev=11.18;p95=40.00.
e. Kết thúc chương trình client.
*/
// Bài lấy từ hệ thống DB-Lab (Exam Server 36.50.135.242), chưa chạy thử với server thật.
// Độ lệch chuẩn: chia cho n (độ lệch chuẩn tổng thể): với 10,20,30,40 cho 11.18 khớp ví dụ của đề.
package RMI;
import java.rmi.registry.*;
import java.util.*;
public class Data12_ThongKeSoThucNangCao {
    public static void main(String[] args) throws Exception {
        String msv = "B23DCCN139", qCode = "HNCj8VEs";
        Registry rg = LocateRegistry.getRegistry("36.50.135.242", 1099);
        DataService sv = (DataService) rg.lookup("RMIDataService");
        String s = (String) sv.requestData(msv, qCode);
        System.out.println(s);
        String[] t = s.trim().split("\\s*,\\s*");
        int n = t.length;
        double[] a = new double[n];
        double tong = 0;
        for (int i = 0; i < n; i++) {
            a[i] = Double.parseDouble(t[i]);
            tong += a[i];
        }
        double tb = tong / n;
        double bp = 0;
        for (double x : a) bp += (x - tb) * (x - tb);
        double stddev = Math.sqrt(bp / n);
        Arrays.sort(a);
        double p95 = a[(int) Math.ceil(n * 0.95) - 1];
        // Locale.US để dấu thập phân luôn là "."
        String kq = String.format(Locale.US, "average=%.2f;stddev=%.2f;p95=%.2f", tb, stddev, p95);
        System.out.println(kq);
        sv.submitData(msv, qCode, kq);
    }
}
