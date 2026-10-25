/* ===== ĐỀ SỐ 18 [B21DCCN088] - BÀI 1 SẮP XẾP CHẴN LẺ | qCode: 0BEtJAT9 ===== */
/*
BÀI 1. SẮP XẾP CHẴN LẺ

[Mã câu hỏi (qCode): 0BEtJAT9]. Một dịch vụ web được định nghĩa và mô tả trong tệp DataService?wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/DataService?wsdl để xử lý các bài toán với dữ liệu nguyên thủy. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với DataService thực hiện các công việc sau:

a. Triệu gọi phương thức getData với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một mảng số nguyên (int[]) từ server.
b. Sắp xếp lại mảng số nguyên nhận được sao cho phần tử chẵn và lẻ xen kẽ nhau. Nếu không thể xen kẽ đều, các phần tử còn lại sẽ nối tiếp ở cuối mảng. Đảm bảo các phần tử chẵn ở vị trí đầu tiên trong mảng sắp xếp.
c. Triệu gọi phương thức submitDataIntArray(String studentCode, String qCode, int[] data) để gửi mảng đã sắp xếp theo thứ tự chẵn-lẻ xen kẽ trở lại server. Ví dụ: Nếu mảng số nguyên nhận được từ phương thức getData là [1, 2, 3, 4, 5, 6], sau khi sắp xếp xen kẽ chẵn-lẻ, mảng kết quả sẽ là [2, 1, 4, 3, 6, 5]. Mảng này sẽ được gửi lại server qua phương thức submitData.
d. Kết thúc chương trình client.
*/
package WebService.B21DCCN088;
import vn.medianews.*;
import java.util.*;
public class SXChanLe {
    public static void main(String[] args) throws Exception{
        String msv = "B21DCCN088", qCode = "0BEtJAT9"; 
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();
        List<Integer>a = port.getData(msv, qCode);
        System.out.println(a);
        List<Integer>chan = new java.util.ArrayList<>(), le = new java.util.ArrayList<>(), ans = new java.util.ArrayList<>();
        for(int x: a){
            if(x%2==0) chan.add(x);
            else le.add(x);
        }
        int k = Integer.min(chan.size(), le.size());
        for(int i = 0;i < k;i++){
            ans.add(chan.get(i)); ans.add(le.get(i));
        }
        int k1 = chan.size() - k, k2 = le.size() - k;
        if(k1 > 0){
            for(int i = k;i<chan.size();i++) ans.add(chan.get(i));
        }
        if(k2 > 0){
            for(int i = k;i<le.size();i++) ans.add(le.get(i));
        }
        System.out.println(ans);
        port.submitDataIntArray(msv, qCode, ans);
    }
}
