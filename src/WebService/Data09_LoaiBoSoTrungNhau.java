/* ===== ĐỀ SỐ 15 [B21DCCN033] - BÀI 1 LOẠI BỎ SỐ TRÙNG NHAU | qCode: E2Axwwf3 ===== */
/*
BÀI 1. LOẠI BỎ SỐ TRÙNG NHAU

[Mã câu hỏi (qCode): E2Axwwf3]. Một dịch vụ web được định nghĩa và mô tả trong tệp DataService?wsdl, được triển khai trên server tại URL http://<Exam_IP>:2221/DataService?wsdl để xử lý các bài toán với dữ liệu nguyên thủy. Trang 12

Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với DataService thực hiện các công việc sau:

a. Triệu gọi phương thức getData với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một danh sách số nguyên (List<Integer>) từ server.
b. Với mảng số nguyên nhận được, xóa tất cả các phần tử trùng lặp, giữ lại lần xuất hiện đầu tiên của mỗi phần tử. Mảng kết quả sẽ chỉ chứa các phần tử duy nhất theo thứ tự xuất hiện ban đầu.
c. Triệu gọi phương thức submitDataIntArray(String studentCode, String qCode, List<Integer> data) để gửi mảng kết quả đã loại bỏ các phần tử trùng lặp trở lại server. Ví dụ: Nếu mảng số nguyên nhận được từ phương thức getData là [1, 2, 2, 3, 4, 3, 5], mảng kết quả sau khi loại bỏ phần tử trùng lặp là [1, 2, 3, 4, 5]. Mảng này sẽ được gửi lại server qua phương thức submitData.
d. Kết thúc chương trình client.
*/
package WebService;
import vn.medianews.*;
import java.util.*;
public class Data09_LoaiBoSoTrungNhau {
    public static void main(String[] args) throws Exception{
        String msv = "B23DCCN139", qCode = "E2Axwwf3"; 
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();
        List<Integer>a = port.getData(msv, qCode);
        System.out.println(a);
        int []cnt = new int[10005];
        for(int x: a) cnt[x]++;
        List<Integer>ans = new java.util.ArrayList<>();
        for(int x: a){
            if(cnt[x]>0){
                ans.add(x);
                cnt[x] = 0;
            }
        }
        System.out.println(ans);
        port.submitDataIntArray(msv, qCode, ans);
    }
}
