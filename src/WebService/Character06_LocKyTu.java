/* ===== ĐỀ SỐ 7 [B21DCCN008] - BÀI 1 LỌC KÝ TỰ | qCode: rT6Ql5GH ===== */
/*
BÀI 1. LỌC KÝ TỰ

[Mã câu hỏi (qCode): rT6Ql5GH]. Một dịch vụ web được định nghĩa và mô tả trong tệp CharacterService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/CharacterService?wsdl để xử lý các bài toán về chuỗi và ký tự. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với CharacterService thực hiện các công việc sau:

a. Triệu gọi phương thức requestString với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một chuỗi (String) từ server. Chuỗi có thể chứa các ký tự đặc biệt và khoảng trắng.
b. Tạo một chuỗi mới từ chuỗi nhận được bằng cách:
- Loại bỏ tất cả các ký tự đặc biệt, số, chỉ các ký tự chữ cái.
- Đảo ngược chuỗi kết quả sau khi đã loại bỏ các ký tự đặc biệt.

c. Triệu gọi phương thức submitCharacterString(String studentCode, String qCode, String cleanedAndReversedString) để gửi chuỗi đã được làm sạch và đảo ngược trở lại server. Ví dụ: Nếu chuỗi nhận được từ phương thức requestCharacter là "hello@ world! 2024", sau khi loại bỏ các ký tự đặc biệt sẽ thành "helloworld", và khi đảo ngược sẽ là "dlrowolleh". Chuỗi kết quả "dlrowolleh" sẽ được gửi lại server qua phương thức submitCharacter.
d. Kết thúc chương trình client.
*/
package WebService;
import vn.medianews.*;
import java.util.*;
public class Character06_LocKyTu {
    public static void main(String[] args) throws Exception {
        // Cấu hình thông tin
        String msv = "B23DCCN139", qCode = "rT6Ql5GH"; 
        // Tạo đối tượng dịch vụ từ web service
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();
        // a. Triệu gọi phương thức 
        String s = port.requestString(msv, qCode);
        System.out.println(s);
        //b.
        String ans = "";
        for(char x: s.toCharArray()){
            if(Character.isAlphabetic(x)) ans = x + ans;
        }
        System.out.println(ans);
        //c
        port.submitCharacterString(msv, qCode, ans);
    }
}
