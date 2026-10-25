/* ===== ĐỀ SỐ 17 [B21DCCN084] - BÀI 1 BÀI TOÁN QUẢN LÝ NHÂN VIÊN – 2 | qCode: L8CainEX ===== */
/*
BÀI 1. BÀI TOÁN QUẢN LÝ NHÂN VIÊN – 2

[Mã câu hỏi (qCode): L8CainEX]. Một dịch vụ web được định nghĩa và mô tả trong tệp ObjectService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/ObjectService?wsdl để xử lý các bài toán với đối tượng. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với ObjectService thực hiện các công việc sau:

a. Triệu gọi phương thức requestEmployee với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một đối tượng Employee từ server. Đối tượng này có các thuộc tính:
- startDate: kiểu Date, đại diện cho ngày bắt đầu công việc của nhân viên.
- endDate: kiểu Date, đại diện cho ngày kết thúc công việc của nhân viên.
b. Tính toán số ngày làm việc (workingDays) giữa startDate và endDate, loại trừ các ngày cuối tuần (thứ Bảy và Chủ Nhật). Cập nhật thuộc tính workingDays trong đối tượng Employee với giá trị đã

tính toán.

c. Triệu gọi phương thức submitEmployee(String studentCode, String qCode, Employee object) để gửi đối tượng Employee với số ngày làm việc đã được tính toán trở lại server. Ví dụ: Nếu đối tượng Employee có startDate là 01-09-2023 (thứ Sáu) và endDate là 07-09-2023 (thứ Năm), số ngày làm việc sẽ tính từ ngày 01-09 đến 07-09, loại trừ ngày cuối tuần, kết quả là 5 ngày làm việc. Đối tượng Employee với giá trị workingDays = 5 sẽ được gửi lại server qua phương thức submitObject.
d. Kết thúc chương trình client.
*/
package WebService.B21DCCN084;
import vn.medianews.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class QuanLyNhanVien2 {
    public static void main(String[] args) throws Exception{
        String msv = "B21DCCN084", qCode = "L8CainEX"; 
        ObjectService_Service service = new ObjectService_Service();
        ObjectService port = service.getObjectServicePort();
        Employee e = port.requestEmployee(msv, qCode);
        System.out.println(e);
        String bd = e.getStartDate().toString(), kt = e.getEndDate().toString();
        bd = bd.substring(0, 10); kt = kt.substring(0, 10);
        LocalDate stDay = LocalDate.parse(bd), enDay = LocalDate.parse(kt);
        long tmp = ChronoUnit.DAYS.between(stDay, enDay);
        int cnt = 0;
        for(int i = 0;i<=tmp;i++){
            LocalDate curDay = stDay.plusDays(i);
            if(curDay.getDayOfWeek().getValue()<=5) cnt++;
        }
        e.setWorkingDays(cnt);
        System.out.println(e);
        port.submitEmployee(msv, qCode, e);
    }
}
