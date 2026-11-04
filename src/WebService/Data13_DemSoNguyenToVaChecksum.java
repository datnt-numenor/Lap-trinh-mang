/* ===== DB-Lab - [SOAP Data] Đếm số nguyên tố và checksum | qCode: 1usPzrw5 ===== */
/*
Dịch vụ web SOAP 1.1 DataService?wsdl tại http://<Exam_IP>:2221/DataService?wsdl.
a. Dùng mã sinh viên và mã câu hỏi làm tham số khi gọi các phương thức SOAP.
b. Triệu gọi getData(studentCode, qCode) để nhận List<Integer>. Ví dụ: [11,12,13,20].
c. Đếm số nguyên tố trong danh sách và tính checksum theo công thức sum((i + 1) * value[i]) mod 100000, với i bắt đầu từ 0.
d. Triệu gọi submitDataString(studentCode, qCode, data) để gửi chuỗi primeCount=<n>;checksum=<m>. Ví dụ: primeCount=2;checksum=154.
e. Kết thúc chương trình client.
*/
// Bài lấy từ hệ thống DB-Lab (Exam Server 36.50.135.242), chưa chạy thử với server thật.
// Stub: sinh từ WSDL (cổng 2221) bằng New > Web Service Client (package ptit.dblab.judge.network.server.soap), rồi kéo vào Source Packages.
// Tên lớp/getter theo quy luật của DataService (đã chạy AC); với Character/Object là suy luận, nếu khác thì sửa theo stub sinh ra.
package WebService;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class Data13_DemSoNguyenToVaChecksum {
    public static void main(String[] args) throws Exception {
        String msv = "B23DCCN139", qCode = "1usPzrw5";
        DataService service = new DataService();
        SoapDataService port = service.getSoapDataServicePort();
        List<Integer> a = port.getData(msv, qCode);
        int dem = 0;
        long sum = 0; // dùng long để tránh tràn khi nhân (i + 1) * value
        for (int i = 0; i < a.size(); i++) {
            if (nguyenTo(a.get(i))) dem++;
            sum += (long) (i + 1) * a.get(i);
        }
        long checksum = ((sum % 100000) + 100000) % 100000;
        String res = "primeCount=" + dem + ";checksum=" + checksum;
        System.out.println(res);
        port.submitDataString(msv, qCode, res);
    }

    private static boolean nguyenTo(int n) {
        if (n < 2) return false;
        for (int i = 2; (long) i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
