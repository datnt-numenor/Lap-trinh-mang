/* ===== DB-Lab - [SOAP Data] Đếm số chẵn và số lẻ | qCode: QLFU3nZw ===== */
/*
Dịch vụ web SOAP 1.1 DataService?wsdl tại http://<Exam_IP>:2221/DataService?wsdl.
a. Dùng mã sinh viên và mã câu hỏi làm tham số khi gọi các phương thức SOAP.
b. Triệu gọi getData(studentCode, qCode) để nhận List<Integer> từ server.
c. Đếm số lượng phần tử chẵn và số lượng phần tử lẻ.
d. Triệu gọi submitDataStringArray(studentCode, qCode, data) để gửi danh sách chuỗi gồm EVEN=<n> và ODD=<m>.
e. Kết thúc client. Ví dụ: [652, 139, 822, 95, 755, 70, 809, 165, 580, 514, 921, 995, 97, 988, 666, 654] -> [EVEN=8, ODD=8].
*/
// Bài lấy từ hệ thống DB-Lab (Exam Server 36.50.135.242), chưa chạy thử với server thật.
// Stub (package vn.medianews) cần sinh lại từ WSDL của đề (cổng 2221); tên lớp/phương thức theo đúng đề, nếu stub sinh ra khác thì sửa lại cho khớp.
package WebService;
import vn.medianews.*;
import java.util.*;
public class Data12_DemSoChanVaLe {
    public static void main(String[] args) throws Exception {
        String msv = "B23DCCN139", qCode = "QLFU3nZw";
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();
        List<Integer> a = port.getData(msv, qCode);
        int chan = 0, le = 0;
        for (int x : a) {
            if (x % 2 == 0) chan++; else le++;
        }
        List<String> res = new ArrayList<>();
        res.add("EVEN=" + chan);
        res.add("ODD=" + le);
        System.out.println(res);
        port.submitDataStringArray(msv, qCode, res);
    }
}
