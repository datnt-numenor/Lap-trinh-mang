/* ===== ĐỀ SỐ 13 [B21DCCN021] - BÀI 2 BÀI TOÁN QUẢN LÝ ĐƠN HÀNG - 2 | qCode: CuW1L0ev ===== */
/*
BÀI 2. BÀI TOÁN QUẢN LÝ ĐƠN HÀNG - 2

[Mã câu hỏi (qCode): CuW1L0ev]. Một dịch vụ web được định nghĩa và mô tả trong tệp

ObjectService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:2221/ObjectService?wsdl để xử lý các bài toán với đối tượng. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với ObjectService thực hiện các công việc sau:

a. Triệu gọi phương thức requestListOrder với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về danh sách đối tượng Order từ server. Mỗi đối tượng Order có các thuộc tính:
- customerId: kiểu String, đại diện cho mã khách hàng.
- amount: kiểu float, đại diện cho giá trị của đơn hàng.
- status: kiểu String, đại diện cho trạng thái của đơn hàng, với các trạng thái có thể là "completed", "pending", hoặc "canceled".
b. Thực hiện lọc và chỉ giữ lại các hóa đơn của khách hàng có tổng giá trị đơn hàng cao nhất
c. Triệu gọi phương thức submitListOrder(String studentCode, String qCode, List<Order> data) để gửi danh sách hóa đơn của khách hàng có tổng giá trị đơn hàng cao nhất.
d. Kết thúc chương trình client.
*/
package WebService;
import vn.medianews.*;
import java.util.*;
public class Object09_BaiToanQuanLyDonHang2 {
    public static void main(String[] args) throws Exception{
        String msv = "B23DCCN139", qCode = "CuW1L0ev"; 
        ObjectService_Service service = new ObjectService_Service();
        ObjectService port = service.getObjectServicePort();
        List<Order>a = (List<Order>)port.requestListOrder(msv, qCode);
        for(Order x: a) System.out.println(x);
        HashMap<String, Float>mp = new HashMap<>();
        for(Order x: a){
            String maKH = x.getCustomerId(); float giaTri = x.getAmount();
            if(!mp.containsKey(maKH)) mp.put(maKH, giaTri);
            else mp.put(maKH, mp.get(maKH) + giaTri);
        }
        float maxGiaTri = 0; String maKHmax = "";
        for (String maKH: mp.keySet()) {
            float giaTri = mp.get(maKH);
            if (giaTri > maxGiaTri) {
                maxGiaTri = giaTri;
                maKHmax = maKH;
            }
        }
        List<Order>ans = new java.util.ArrayList<>();
        for(Order x: a){
            if(x.getCustomerId().equals(maKHmax)) ans.add(x);
        }
        port.submitListOrder(msv, qCode, ans);
    }
}
