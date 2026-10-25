/* ===== ĐỀ SỐ 6 [B21DCCN007] - BÀI 2 CHUẨN HOÁ CHUỖI | qCode: sBQjqANT ===== */
/*
BÀI 2. CHUẨN HOÁ CHUỖI

[Mã câu hỏi (qCode): sBQjqANT]. Một dịch vụ web được định nghĩa và mô tả trong tệp CharacterService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:2221/CharacterService?wsdl để xử lý các bài toán về chuỗi và ký tự. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với CharacterService thực hiện các công việc sau:

a. Triệu gọi phương thức requestString với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một chuỗi (String) từ server. Chuỗi có thể chứa các từ được phân tách bằng dấu cách hoặc dấu gạch dưới.
b. Chuyển đổi chuỗi đã nhận được sang ba định dạng khác nhau:
- PascalCase: Mỗi từ bắt đầu bằng chữ in hoa, không có khoảng cách giữa các từ.
- camelCase: Từ đầu tiên viết thường, các từ tiếp theo viết hoa chữ cái đầu và viết liền nhau.
- snake_case: Các từ được viết thường và nối với nhau bằng dấu gạch dưới.
c. Triệu gọi phương thức submitCharacterStringArray(String studentCode, String qCode, List<String>) để gửi mảng kết quả chứa ba chuỗi đã định dạng trở lại server, theo thứ tự: PascalCase, camelCase, snake_case. Ví dụ: Nếu chuỗi nhận được từ phương thức requestCharacter là "hello world example", các chuỗi kết quả sẽ là:

|•|PascalCase: "HelloWorldExample"|
|---|---|
|•|camelCase: "helloWorldExample"|
|•|snake_case: "hello_world_example"|
 Mảng kết quả sẽ là ["HelloWorldExample", "helloWorldExample", "hello_world_example"], và sẽ được gửi lại server qua phương thức submitCharacter.
d. Kết thúc chương trình client.
*/
package WebService;
import vn.medianews.*;
import java.util.*;
public class Character05_ChuanHoaChuoi {
    public static void main(String[] args) throws Exception {
        // Cấu hình thông tin
        String msv = "B23DCCN139", qCode = "sBQjqANT"; 
        // Tạo đối tượng dịch vụ từ web service
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();
        // a. Triệu gọi phương thức 
        String s = port.requestString(msv, qCode);
        System.out.println(s);
        //b.
        s = s.replace('_', ' ');
        String []tmp = s.trim().split("\\s+");
        String p = "", c = tmp[0].toLowerCase(), sn = "";
        for(String x: tmp) p+=chuanhoa(x); 
        for(int i = 1;i<tmp.length;i++) c+=chuanhoa(tmp[i]);
        for(String x: tmp) sn+=x.toLowerCase()+"_"; sn = sn.substring(0, sn.length() - 1);
        List<String>ans = new java.util.ArrayList<>();
        ans.add(p); ans.add(c); ans.add(sn);
        System.out.println(ans);
        //c
        port.submitCharacterStringArray(msv, qCode, ans);
    }
    public static String chuanhoa(String s){
        return Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
    }
}
