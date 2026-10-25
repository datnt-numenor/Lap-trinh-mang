/* ===== ĐỀ SỐ 3 [B21DCCN003] - BÀI 3 BÀI TOÁN PHÂN LOẠI HỌC LỰC SINH VIÊN | qCode: qNfIMvid ===== */
/*
BÀI 3. BÀI TOÁN PHÂN LOẠI HỌC LỰC SINH VIÊN

[Mã câu hỏi (qCode): qNfIMvid]. Một dịch vụ web được định nghĩa và mô tả trong tệp ObjectService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/ObjectService?wsdl để xử lý các bài toán với đối tượng. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với ObjectService thực hiện các công việc sau:

a. Triệu gọi phương thức requestListStudent với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một danh sách đối tượng Student từ server. Mỗi đối tượng Student có các thuộc tính:
- name: kiểu String, đại diện cho tên của sinh viên.
- score: kiểu float, đại diện cho điểm trung bình của sinh viên.
b. Thực hiện tạo danh sách phân nhóm các sinh viên dựa trên điểm trung bình theo các mức sau: A: điểm từ 8.0 trở lên; B: điểm từ 6.5 đến dưới 8.0; C: điểm từ 5.0 đến dưới 6.5; D: điểm dưới 5.0
c. Triệu gọi phương thức submitListStudent(String studentCode, String qCode, List<Student> students) để gửi danh sách sinh viên thuộc nhóm A và D.
d. Kết thúc chương trình client.
*/
package WebService;
import vn.medianews.*;
import java.util.*;
public class Object03_BaiToanPhanLoaiHocLucSinhVien {
    public static void main(String[] args) throws Exception {
        String msv = "B21DCCN003", qCode = "qNfIMvid"; // Mã câu hỏi
        ObjectService_Service service = new ObjectService_Service();
        ObjectService port = service.getObjectServicePort();
        List<Student> a = (List<Student>) port.requestListStudent(msv, qCode);
        for(Student x: a) System.out.println(x);
        System.out.println();
        List<Student>ans = new java.util.ArrayList<>();
        for(Student x: a){
            if(x.getScore() >=8.0) ans.add(x);
            else if (x.getScore() < 5) ans.add(x);
        }
        for(Student x: ans) System.out.println(x);
        port.submitListStudent(msv, qCode, ans);
    }
}
