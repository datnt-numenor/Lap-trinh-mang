/* ===== ĐỀ SỐ 13 [B21DCCN021] - BÀI 1 CHUYỂN ĐỔI THẬP PHÂN SANG NHỊ PHÂN | qCode: 2jS1DTpU ===== */
/*
BÀI 1. CHUYỂN ĐỔI THẬP PHÂN SANG NHỊ PHÂN

[Mã câu hỏi (qCode): 2jS1DTpU]. Một dịch vụ web được định nghĩa và mô tả trong tệp DataService?wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/DataService?wsdl để xử lý các bài toán với dữ liệu nguyên thủy. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với DataService thực hiện các công việc sau:

a. Triệu gọi phương thức getData với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một mảng số nguyên (List<Integer>) từ server.
b. Chuyển đổi số nguyên nhận được từ hệ thập phân sang hệ nhị phân và biểu diễn kết quả dưới dạng chuỗi nhị phân.
c. Triệu gọi phương thức submitDataStringArray(String studentCode, String qCode, List<String> data) để gửi chuỗi nhị phân đã chuyển đổi trở lại server. Ví dụ: Nếu mỗi số nguyên nhận được từ phương thức getData, chương trình client sẽ chuyển đổi sang chuỗi nhị phân là "1010", và gửi mảng chuỗi này trở lại server qua phương thức submitData.
d. Kết thúc chương trình client.
*/
package WebService.B21DCCN021;
import vn.medianews.*;
import java.util.*;
public class DecToBin {
    public static void main(String[] args) throws Exception{
        String msv = "B21DCCN021", qCode = "2jS1DTpU"; 
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();
        List<Integer>a = port.getData(msv, qCode);
        System.out.println(a);
        List<String>ans = new java.util.ArrayList<>();
        for(int x: a) ans.add(Integer.toBinaryString(x));
        System.out.println(ans);
        port.submitDataStringArray(msv, qCode, ans);
    }
}
