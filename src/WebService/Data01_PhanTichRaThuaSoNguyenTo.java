/* ===== ĐỀ SỐ 1 [B21DCCN001] - BÀI 1 PHÂN TÍCH RA THỪA SỐ NGUYÊN TỐ | qCode: TwZpbqUg ===== */
/*
BÀI 1. PHÂN TÍCH RA THỪA SỐ NGUYÊN TỐ

[Mã câu hỏi (qCode): TwZpbqUg]. Một dịch vụ web được định nghĩa và mô tả trong tệp DataService?wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/DataService?wsdl để xử lý các bài toán với dữ liệu nguyên thủy. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với DataService thực hiện các công việc sau:

a. Triệu gọi phương thức getData với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một danh sách số nguyên (List<Integer>) từ server.
b. Thực hiện phân tích số nguyên nhận được thành các thừa số nguyên tố của nó và lưu các thừa số này vào một chuỗi kết quả.
c. Triệu gọi phương thức submitDataStringArray(String studentCode, String qCode, List<String> data) để gửi danh sách chuỗi các thừa số nguyên tố đã tính được trở lại server. Ví dụ: Nếu số nguyên trong danh sách là 28 à sẽ phân tích thành các thừa số nguyên tố "2, 2, 7". Chương trình client thực hiện với từng số nguyên trong danh sách và và gửi danh sách chuỗi kết quả cho từng số này trở lại server qua phương thức submitDataStringArray.
d. Kết thúc chương trình client.
*/
package WebService;
import vn.medianews.*;
import java.util.*;
public class Data01_PhanTichRaThuaSoNguyenTo {
    public static void main(String[] args) throws Exception {
        String msv = "B21DCCN001", qCode = "TwZpbqUg"; 
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();
        List<Integer> a = port.getData(msv, qCode);
        System.out.println(a);
        List<String>ans = new java.util.ArrayList<>();
        for(int x: a){
            String tmp = "";
            for(int i = 2;i*i<=x;i++){
                while(x%i==0){
                    x/=i;
                    tmp+=String.format("%d, ", i);
                }
            }
            if(x > 1) tmp+=String.format("%d, ", x);
            tmp = tmp.trim();
            tmp = tmp.substring(0, tmp.length() - 1);
            ans.add(tmp);
            System.out.println(tmp);
        }
        port.submitDataStringArray(msv, qCode, ans);
    }
}

