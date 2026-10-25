/* ===== ĐỀ SỐ 7 [B21DCCN008] - BÀI 2 BÀI TOÁN QUẢN LÝ ĐƠN HÀNG | qCode: 46WF6Fg5 ===== */
/*
BÀI 2. BÀI TOÁN QUẢN LÝ ĐƠN HÀNG

[Mã câu hỏi (qCode): 46WF6Fg5]. Một dịch vụ web được định nghĩa và mô tả trong tệp ObjectService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/ObjectService?wsdl để xử lý các bài toán với đối tượng. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với ObjectService thực hiện các công việc sau:

a. Triệu gọi phương thức requestListOrderY với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về danh sách đối tượng OrderY từ server. Mỗi đối tượng OrderY có các thuộc tính:
- orderId: kiểu String, đại diện cho mã đơn hàng.
- orderDate: kiểu Date, đại diện cho ngày đặt hàng.
- status: kiểu String, đại diện cho trạng thái đơn hàng, với các trạng thái có thể là "pending", "processing", hoặc "completed".
- customerId: kiểu String, đại diện cho mã khách hàng.
b. Lọc ra các đơn hàng có trạng thái là "pending" hoặc "processing" và có ngày đặt hàng (orderDate) trong vòng 30 ngày trở lại từ ngày hiện tại để tạo danh sách các đơn hàng cần được ưu tiên xử lý.
c. Triệu gọi phương thức submitListOrderY(String studentCode, String qCode, List<OrderY> orderYs)) để gửi danh sách đơn hàng đã được lọc trở lại server.
d. Kết thúc chương trình client.
*/
// Đáp án bổ sung (không có trong repo gốc), chưa kiểm chứng với server thật.
package WebService;
import vn.medianews.*;
import java.util.*;
public class Object06_BaiToanQuanLyDonHang {
    public static void main(String[] args) throws Exception {
        String msv = "B23DCCN139", qCode = "46WF6Fg5";
        ObjectService_Service service = new ObjectService_Service();
        ObjectService port = service.getObjectServicePort();
        // a. Nhận danh sách OrderY từ server
        List<OrderY> a = port.requestListOrderY(msv, qCode);
        // b. Giữ đơn "pending" hoặc "processing" có orderDate trong 30 ngày gần nhất
        //    (từ now - 30 ngày đến now; so sánh theo mili giây).
        long now = System.currentTimeMillis();
        long tuNgay = now - 30L * 24 * 60 * 60 * 1000;
        List<OrderY> res = new ArrayList<>();
        for (OrderY o : a) {
            if (o.getOrderDate() == null || o.getStatus() == null) continue;
            long t = o.getOrderDate().toGregorianCalendar().getTimeInMillis();
            boolean dungTrangThai = o.getStatus().equals("pending") || o.getStatus().equals("processing");
            if (dungTrangThai && t >= tuNgay && t <= now) res.add(o);
        }
        System.out.println(res.size() + "/" + a.size());
        // c. Gửi danh sách đã lọc
        port.submitListOrderY(msv, qCode, res);
    }
}
