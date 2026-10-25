/* ===== ĐỀ SỐ 9 [B21DCCN011] - BÀI 1 CHUYỂN ĐỔI SỐ THẬP PHÂN SANG PHÂN SỐ | qCode: ny8Fb8BU ===== */
/*
BÀI 1. CHUYỂN ĐỔI SỐ THẬP PHÂN SANG PHÂN SỐ

[Mã câu hỏi (qCode): ny8Fb8BU]. Một dịch vụ web được định nghĩa và mô tả trong tệp DataService?wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/DataService?wsdl để xử lý các bài toán với dữ liệu nguyên thủy. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với DataService thực hiện các công việc sau:

a. Triệu gọi phương thức getDataDouble với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một số thập phân (double) từ server.
b. Chuyển đổi số thập phân nhận được thành phân số đơn giản nhất, biểu diễn dưới dạng tử số và mẫu số nguyên (List<Integer>) với phần tử đầu tiên là tử số và phần tử thứ hai là mẫu số.
c. Triệu gọi phương thức submitDataIntArray(String studentCode, String qCode, List<Integer> data) để gửi phân số đơn giản nhất đã chuyển đổi trở lại server. Ví dụ: Nếu số thập phân nhận được từ phương thức getData là 0.75, chương trình client sẽ chuyển đổi thành phân số đơn giản nhất là [3, 4], và gửi mảng [3, 4] trở lại server qua phương thức submitData.
d. Kết thúc chương trình client.
*/
package WebService;
import vn.medianews.*;
import java.util.*;
public class Data06_ChuyenDoiSoThapPhanSangPhanSo {
    public static int gcd(int a, int b){
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    public static void main(String[] args) throws Exception{
        String msv = "B23DCCN139", qCode = "ny8Fb8BU"; 
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();
        double a = port.getDataDouble(msv, qCode);
        a = Math.round(a * 100) / 100.0;
        System.out.println(a);
        int tu, mau = 100;
        tu = (int) (a * 100.0);
        int tmp = gcd(tu, mau); tu/=tmp; mau/=tmp;
        List<Integer> ans = new java.util.ArrayList<>();
        ans.add(tu); ans.add(mau);
        System.out.println(ans);
        port.submitDataIntArray(msv, qCode, ans);
    }
}

