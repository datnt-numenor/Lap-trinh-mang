/* ===== ĐỀ SỐ 14 [B21DCCN025] - BÀI 1 HỆ CƠ SỐ 8 VÀ 16 | qCode: QBDJ1GGL ===== */
/*
BÀI 1. HỆ CƠ SỐ 8 VÀ 16

[Mã câu hỏi (qCode): QBDJ1GGL]. Một dịch vụ web được định nghĩa và mô tả trong tệp DataService?wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/DataService?wsdl để xử lý các bài toán với dữ liệu nguyên thủy. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với DataService thực hiện các công việc sau:

a. Triệu gọi phương thức getData với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một danh sách số nguyên (List<Integer>) từ server.
b. Chuyển đổi số nguyên nhận được từ hệ thập phân sang cả hệ cơ số 8 (bát phân) và hệ cơ số 16 (thập lục phân). Biểu diễn kết quả dưới dạng chuỗi cho mỗi hệ cơ số.
c. Triệu gọi phương thức submitDataStringArray(String studentCode, String qCode, List<String> data) để gửi danh sách chuỗi kết quả. Trong đó: Mỗi phần tử là chuỗi kết hợp giữa kết quả chuyển đổi sang hệ cơ số 8 và chuỗi kết quả chuyển đổi sang hệ cơ số 16. Ví dụ: Nếu số nguyên nhận được là 64 thì: Chuyển đổi sang hệ cơ số 8, tạo thành chuỗi "100". Chuyển đổi sang hệ cơ số 16, tạo thành chuỗi "40". -> Chuỗi kết quả tương ứng sẽ là "100|40". Chương trình client thực hiện lần lượt và gửi danh sách chuỗi này trở lại server qua phương thức submitDataStringArray.
d. Kết thúc chương trình client. Bài này chú ý là in ra hệ hexa phải viết hoa
*/
package WebService.B21DCCN025;
import vn.medianews.*;
import java.util.*;
public class HeCoSo8 {
    public static void main(String[] args) throws Exception{
        String msv = "B21DCCN025", qCode = "QBDJ1GGL"; 
        DataService_Service service = new DataService_Service();
        DataService port = service.getDataServicePort();
        List<Integer>a = port.getData(msv, qCode);
        System.out.println(a);
        List<String>res = new java.util.ArrayList<>();
        for(int n: a){
            String oct = Integer.toOctalString(n), hex = Integer.toHexString(n).toUpperCase();
            String tmp = oct + "|" + hex;
            res.add(tmp);
        }
        System.out.println(res);
        port.submitDataStringArray(msv, qCode, res);
    }
}

