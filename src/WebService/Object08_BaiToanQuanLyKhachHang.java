/* ===== ĐỀ SỐ 11 [B21DCCN014] - BÀI 1 BÀI TOÁN QUẢN LÝ KHÁCH HÀNG | qCode: lgKj7lIF ===== */
/*
BÀI 1. BÀI TOÁN QUẢN LÝ KHÁCH HÀNG

[Mã câu hỏi (qCode): lgKj7lIF]. Một dịch vụ web được định nghĩa và mô tả trong tệp ObjectService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/ObjectService?wsdl để xử lý các bài toán với đối tượng. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với ObjectService thực hiện các công việc sau:

a. Triệu gọi phương thức requestListCustomer với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về danh sách đối tượng Customer từ server. Mỗi đối tượng Customer có các thuộc tính:
- customerId: kiểu String, đại diện cho mã khách hàng.
- location: kiểu String, đại diện cho địa điểm của khách hàng.
- purchaseCount: kiểu int, đại diện cho số lần mua hàng của khách hàng.
- totalSpent: kiểu float, đại diện cho tổng số tiền đã chi tiêu của khách hàng.
b. Lọc và giữ lại các khách hàng tại từng địa điểm (location) có totalSpent lớn hơn 5000 và có purchaseCount từ 5 trở lên.
c. Triệu gọi phương thức submitListCustomer(String studentCode, String qCode,

List<Customer> customers) để gửi danh sách các khách hàng tiềm năng theo từng địa điểm trở lại server.

d. Kết thúc chương trình client.
*/
package WebService;
import vn.medianews.*;
import java.util.*;
public class Object08_BaiToanQuanLyKhachHang {
    public static void main(String[] args) throws Exception{
        String msv = "B23DCCN139", qCode = "lgKj7lIF"; 
        ObjectService_Service service = new ObjectService_Service();
        ObjectService port = service.getObjectServicePort();
        List<Customer>a = (List<Customer>)port.requestListCustomer(msv, qCode);
        for(Customer x: a) System.out.println(x);
        List<Customer>ans = new java.util.ArrayList<>();
        for(Customer x: a){
            if(x.getTotalSpent() > 5000 && x.getPurchaseCount() >=5) ans.add(x);
        }
        System.out.println();
        for(Customer x: ans) System.out.println(x);
        port.submitListCustomer(msv, qCode, ans);
    }
}
