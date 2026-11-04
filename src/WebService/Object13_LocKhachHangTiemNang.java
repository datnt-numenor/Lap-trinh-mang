/* ===== DB-Lab - [WS Object] Lọc khách hàng tiềm năng | qCode: jxSi7OEM ===== */
/*
Dịch vụ web SOAP 1.1 ObjectService.wsdl tại http://<Exam_IP>:2221/ObjectService?wsdl.
a. Triệu gọi requestListCustomer(studentCode, qCode) để nhận danh sách Customer (customerId String, location String, purchaseCount int, totalSpent float).
b. Lọc và giữ lại các khách hàng có totalSpent lớn hơn 5000 và có purchaseCount từ 5 trở lên.
c. Triệu gọi submitListCustomer(String studentCode, String qCode, List<Customer> customers) để gửi danh sách khách hàng tiềm năng.
d. Kết thúc chương trình client.
*/
// Bài lấy từ hệ thống DB-Lab (Exam Server 36.50.135.242), chưa chạy thử với server thật.
// Stub: sinh từ WSDL (cổng 2221) bằng New > Web Service Client (package ptit.dblab.judge.network.server.soap), rồi kéo vào Source Packages.
// Tên lớp/getter theo quy luật của DataService (đã chạy AC); với Character/Object là suy luận, nếu khác thì sửa theo stub sinh ra.
package WebService;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class Object13_LocKhachHangTiemNang {
    public static void main(String[] args) throws Exception {
        String msv = "B23DCCN139", qCode = "jxSi7OEM";
        ObjectService service = new ObjectService();
        SoapObjectService port = service.getSoapObjectServicePort();
        List<Customer> a = port.requestListCustomer(msv, qCode);
        List<Customer> res = new ArrayList<>();
        for (Customer c : a) {
            if (c.getTotalSpent() > 5000 && c.getPurchaseCount() >= 5) res.add(c);
        }
        System.out.println(res.size() + "/" + a.size());
        port.submitListCustomer(msv, qCode, res);
    }
}
