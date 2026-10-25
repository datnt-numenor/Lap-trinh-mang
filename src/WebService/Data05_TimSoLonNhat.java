/* ===== ĐỀ SỐ 6 [B21DCCN007] - BÀI 1 TÌM SỐ LỚN NHẤT | qCode: RiRH8wfk ===== */
/*
BÀI 1. TÌM SỐ LỚN NHẤT

[Mã câu hỏi (qCode): RiRH8wfk]. Một dịch vụ web được định nghĩa và mô tả trong tệp DataService?wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/DataService?wsdl để xử lý các bài toán với dữ liệu nguyên thủy. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với DataService thực hiện các công việc sau:

a. Triệu gọi phương thức getData với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một mảng số nguyên (int[]) từ server. Ví dụ: 7602,9136,1090,34319,7830,6179,10584,20166,28199,30250,32179,22544,3222,10320,30590,19 279
b. Thực hiện tìm số lớn nhất có thể tạo dược từ a,b,c,d...
c. Triệu gọi phương thức submitDataString(String studentCode, String qCode, String data) để gửi kết quả tổng đã tính được trở lại server. Ví dụ:

91367830760261793431932223217930590302502819922544201661927910901058410320

d. Kết thúc chương trình client.
*/
package WebService;
import vn.medianews.*;
import java.util.*;
public class Data05_TimSoLonNhat {
    public static void main(String[] args) throws Exception{
        String msv = "B21DCCN007", qCode = "RiRH8wfk"; 
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();
        List<Integer>a = port.getData(msv, qCode);
        List<String>a1 = new java.util.ArrayList<>();
        for(int x: a) a1.add(x + "");
        Collections.sort(a1);
        String ans = "";
        for(String x: a1) ans+=x+ans;
        port.submitDataString(msv, qCode, ans);
    }
}
