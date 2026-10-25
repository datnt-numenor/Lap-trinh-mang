/* ===== ĐỀ SỐ 2 [B21DCCN002] - BÀI 1 ĐẾM SỐ LẦN XUẤT HIỆN | qCode: TU4ULIgh ===== */
/*
BÀI 1. ĐẾM SỐ LẦN XUẤT HIỆN

[Mã câu hỏi (qCode): TU4ULIgh]. Một dịch vụ web được định nghĩa và mô tả trong tệp DataService?wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/DataService?wsdl để xử lý các bài toán với dữ liệu nguyên thủy. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với DataService thực hiện các công việc sau:

a. Triệu gọi phương thức getData với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một danh sách số nguyên (List<Integer>) từ server.
b. Với mảng số nguyên nhận được, thực hiện đếm số lần xuất hiện của từng phần tử trong mảng và lưu kết quả vào một chuỗi theo định dạng: giá trị của phần tử, số lần xuất hiện tương ứng của nó.
c. Triệu gọi phương thức submitDataStringArray(String studentCode, String qCode, List<String> data) để gửi danh sách chuỗi kết quả chứa cặp giá trị [phần tử, tần suất] của từng phần tử đã tính được trở lại server. Ví dụ: Nếu mảng số nguyên nhận được từ phương thức getData là [1, 2, 2, 3, 3, 3], kết quả tần suất sẽ là ["1, 1", "2, 2", "3, 3"], và danh sách kết quả này sẽ được gửi lại server qua phương thức submitDataStringArray.
d. Kết thúc chương trình client. Bài này chú ý sắp xếp dãy tăng dần đã nhé
*/
package WebService;
import vn.medianews.*;
import java.util.*;
public class Data02_DemSoLanXuatHien {
    public static void main(String[] args) throws Exception{
        String msv = "B23DCCN139", qCode = "TU4ULIgh"; 
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();
        List<Integer>a = port.getData(msv, qCode);
        System.out.println(a); Collections.sort(a);
        System.out.println(a);
        int []cnt = new int[10005];
        for(int x: a) cnt[x]++;
        List<String>ans = new java.util.ArrayList<>();
        for(int x: a){
            if(cnt[x]>0){
                String tmp = String.format("%d, %d", x, cnt[x]);
                ans.add(tmp);
                cnt[x] = 0;
            }
        }
        for(String x: ans){
            System.out.println(x);
        }
        port.submitDataStringArray(msv, qCode, ans);
    }
}
