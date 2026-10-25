/* ===== ĐỀ SỐ 3 [B21DCCN003] - BÀI 1 PHẦN TỬ LỚN THỨ K VÀ NHỎ THỨ K | qCode: WpaUTFMu ===== */
/*
BÀI 1. PHẦN TỬ LỚN THỨ K VÀ NHỎ THỨ K

[Mã câu hỏi (qCode): WpaUTFMu]. Một dịch vụ web được định nghĩa và mô tả trong tệp DataService?wsdl, được triển khai trên server tại URL http://<Exam_IP>:2221/DataService?wsdl để xử lý các bài toán với dữ liệu nguyên thủy. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với DataService thực hiện các công việc sau:

a. Triệu gọi phương thức getData với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một mảng số nguyên (List<Integer>) từ server. Phần tử đầu tiên của mảng này là giá trị K.
b. Với mảng số nguyên nhận được, sử dụng giá trị K (phần tử đầu tiên của mảng) để xác định phần tử lớn thứ K và nhỏ thứ K trong các phần tử còn lại của mảng (loại bỏ phần tử đầu tiên khi tính toán).
c. Triệu gọi phương thức submitDataIntArray(String studentCode, String qCode, List<Integer> data) để gửi mảng kết quả chứa: phần tử lớn thứ K, phần tử nhỏ thứ K đã tìm được trở lại server. Ví dụ: Nếu mảng số nguyên nhận được từ phương thức getData là [3, 5, 1, 4, 2], giá trị K là 3. Chương trình client sẽ tìm phần tử lớn thứ 3 và nhỏ thứ 3 trong mảng [3, 5, 1, 4, 2], kết quả là mảng [2, 4] và gửi kết quả này trở lại server qua phương thức submitData.
d. Kết thúc chương trình client.
*/
package WebService;
import vn.medianews.*;
import java.util.*;
public class Data03_PhanTuLonThuKVaNhoThuK {
    public static void main(String[] args) throws Exception{
        String msv = "B23DCCN139", qCode = "WpaUTFMu"; 
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();
        List<Integer>a = port.getData(msv, qCode);
        int k = a.get(0); a.remove(0);
        System.out.println(a + " " + k);
        Collections.sort(a);
        int nhoThuK = a.get(k - 1), lonThuK = a.get(a.size() - k);
        List<Integer>ans = new java.util.ArrayList<>(Arrays.asList(lonThuK, nhoThuK));
        port.submitDataIntArray(msv, qCode, ans);
    }
}

