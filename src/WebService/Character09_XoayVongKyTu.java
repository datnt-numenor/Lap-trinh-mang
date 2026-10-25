/* ===== ĐỀ SỐ 12 [B21DCCN016] - BÀI 1 XOAY VÒNG KÝ TỰ | qCode: YDcPHFgN ===== */
/*
BÀI 1. XOAY VÒNG KÝ TỰ

[Mã câu hỏi (qCode): YDcPHFgN]. Một dịch vụ web được định nghĩa và mô tả trong tệp CharacterService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:2221/CharacterService?wsdl để xử lý các bài toán về chuỗi và ký tự. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với CharacterService thực hiện các công việc sau:

a. Triệu gọi phương thức requestCharacter với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một mảng ký tự (List<Character>) từ server.
b. Thực hiện xoay vòng các ký tự trong mảng theo chiều phải, số lần xoay vòng bằng đúng kích thước của mảng ký tự đã nhận được.
c. Triệu gọi phương thức submitCharacterCharArray(String studentCode, String qCode, List<Character> data) để gửi mảng ký tự đã xoay vòng trở lại server. Ví dụ: Nếu mảng ký tự nhận được từ phương thức requestCharacter là ['a', 'b', 'c', 'd'], kích thước mảng là 4, thực hiện xoay vòng phải 4 lần sẽ trả lại mảng ['b', 'c', 'd', 'a']. Mảng này sẽ được gửi lại server qua phương thức submitCharacter.
d. Kết thúc chương trình client.
*/
package WebService;
import vn.medianews.*;
import java.util.*;
public class Character09_XoayVongKyTu {
    public static void main(String[] args) throws Exception {
        String msv = "B23DCCN139", qCode = "YDcPHFgN"; 
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();
        List<Integer>a = port.requestCharacter(msv, qCode);
        int timeRot = a.get(0)%(a.size());
        Collections.rotate(a, timeRot);
        port.submitCharacterCharArray(msv, qCode, a);
    }
}

