/* ===== DB-Lab - [WS Object] Sắp xếp EmployeeY theo thâm niên | qCode: Ss4mjFa9 ===== */
/*
Dịch vụ web SOAP 1.1 ObjectService.wsdl tại http://<Exam_IP>:2221/ObjectService?wsdl.
a. Triệu gọi requestListEmployeeY(studentCode, qCode) để nhận danh sách EmployeeY (name: String, startDate: Date).
b. Sắp xếp theo thâm niên từ cao đến thấp (ngày bắt đầu sớm nhất đứng đầu). Cùng ngày bắt đầu thì giữ nguyên thứ tự ban đầu.
c. Triệu gọi submitListEmployeeY(String studentCode, String qCode, List<EmployeeY> data) để gửi danh sách đã sắp xếp.
d. Kết thúc chương trình client.
*/
// Bài lấy từ hệ thống DB-Lab (Exam Server 36.50.135.242), chưa chạy thử với server thật.
// Stub (package vn.medianews) cần sinh lại từ WSDL của đề (cổng 2221); tên lớp/phương thức theo đúng đề, nếu stub sinh ra khác thì sửa lại cho khớp.
package WebService;
import vn.medianews.*;
import java.util.*;
public class Object12_SapXepNhanVienTheoThamNien {
    public static void main(String[] args) throws Exception {
        String msv = "B23DCCN139", qCode = "Ss4mjFa9";
        ObjectService_Service service = new ObjectService_Service();
        ObjectService port = service.getObjectServicePort();
        List<EmployeeY> a = new ArrayList<>(port.requestListEmployeeY(msv, qCode));
        // Collections.sort ổn định: cùng ngày giữ nguyên thứ tự ban đầu
        Collections.sort(a, (x, y) -> Long.compare(
                x.getStartDate().toGregorianCalendar().getTimeInMillis(),
                y.getStartDate().toGregorianCalendar().getTimeInMillis()));
        for (EmployeeY e : a) System.out.println(e.getName() + " " + e.getStartDate());
        port.submitListEmployeeY(msv, qCode, a);
    }
}
