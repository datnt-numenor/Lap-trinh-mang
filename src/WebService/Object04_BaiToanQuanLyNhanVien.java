/* ===== ĐỀ SỐ 4 [B21DCCN004] - BÀI 3 BÀI TOÁN QUẢN LÝ NHÂN VIÊN | qCode: vewbvojZ ===== */
/*
BÀI 3. BÀI TOÁN QUẢN LÝ NHÂN VIÊN

[Mã câu hỏi (qCode): vewbvojZ]. Một dịch vụ web được định nghĩa và mô tả trong tệp ObjectService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:2221/ObjectService?wsdl để xử lý các bài toán với đối tượng. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với ObjectService thực hiện các công việc sau:

a. Triệu gọi phương thức requestListEmployeeY với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một danh sách đối tượng EmployeeY từ server. Mỗi đối tượng Employee có các thuộc tính:
- name: kiểu String, đại diện cho tên của nhân viên.
- startDate: kiểu Date, đại diện cho ngày bắt đầu làm việc của nhân viên.
b. Sắp xếp danh sách Employee theo thứ tự thâm niên từ cao đến thấp (người có ngày bắt đầu làm việc sớm nhất sẽ đứng đầu danh sách). Nếu hai nhân viên có cùng ngày bắt đầu làm việc, giữ nguyên thứ tự ban đầu của họ trong danh sách.
c. Triệu gọi phương thức submitListEmployeeY(String studentCode, String qCode, List<EmployeeY> data) để gửi danh sách nhân viên đã sắp xếp trở lại server.
d. Kết thúc chương trình client.
*/
package WebService;
import vn.medianews.*;
import java.util.*;
public class Object04_BaiToanQuanLyNhanVien {
    public static void main(String[] args) throws Exception {
        String msv = "B23DCCN139", qCode = "vewbvojZ"; 
        ObjectService_Service service = new ObjectService_Service();
        ObjectService port = service.getObjectServicePort(); 
        List<EmployeeY>a = port.requestListEmployeeY(msv, qCode);
        for(EmployeeY x: a) System.out.println(x);
        Collections.sort(a, new Comparator<EmployeeY>(){
            @Override
            public int compare(EmployeeY o1, EmployeeY o2) {
                return o1.getStartDate().compare(o2.getStartDate());
            }
        });
        System.out.println();
        for(EmployeeY x: a) System.out.println(x);
        port.submitListEmployeeY(msv, qCode, a);
    }
}
