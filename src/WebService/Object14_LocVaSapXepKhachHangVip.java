/* ===== DB-Lab - [SOAP Object] Lọc và sắp xếp khách hàng VIP | qCode: BXAOnBSP ===== */
/*
Dịch vụ web SOAP 1.1 ObjectService?wsdl tại http://<Exam_IP>:2221/ObjectService?wsdl.
a. Dùng mã sinh viên và mã câu hỏi làm tham số khi gọi các phương thức SOAP.
b. Triệu gọi requestListCustomer(studentCode, qCode) để nhận danh sách Customer. Ví dụ một phần tử: customerId=HC700;purchaseCount=8;totalSpent=9000.
c. Giữ lại khách hàng có purchaseCount >= 6 và totalSpent >= 4000, sau đó sắp xếp giảm dần theo totalSpent; nếu bằng nhau thì tăng dần theo customerId.
d. Triệu gọi submitListCustomer(studentCode, qCode, data) để gửi danh sách Customer đã lọc và sắp xếp.
e. Kết thúc chương trình client.
*/
// Bài lấy từ hệ thống DB-Lab (Exam Server 36.50.135.242), chưa chạy thử với server thật.
// Stub: sinh từ WSDL (cổng 2221) bằng New > Web Service Client (package ptit.dblab.judge.network.server.soap), rồi kéo vào Source Packages.
// Tên lớp/getter theo quy luật của DataService (đã chạy AC); với Character/Object là suy luận, nếu khác thì sửa theo stub sinh ra.
package WebService;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class Object14_LocVaSapXepKhachHangVip {
    public static void main(String[] args) throws Exception {
        String msv = "B23DCCN139", qCode = "BXAOnBSP";
        ObjectService service = new ObjectService();
        SoapObjectService port = service.getSoapObjectServicePort();
        List<Customer> a = port.requestListCustomer(msv, qCode);
        List<Customer> res = new ArrayList<>();
        for (Customer c : a) {
            if (c.getPurchaseCount() >= 6 && c.getTotalSpent() >= 4000) res.add(c);
        }
        Collections.sort(res, (x, y) -> {
            int cmp = Float.compare(y.getTotalSpent(), x.getTotalSpent()); // totalSpent giảm dần
            return cmp != 0 ? cmp : x.getCustomerId().compareTo(y.getCustomerId()); // customerId tăng dần
        });
        for (Customer c : res) System.out.println(c.getCustomerId() + " " + c.getTotalSpent());
        port.submitListCustomer(msv, qCode, res);
    }
}
