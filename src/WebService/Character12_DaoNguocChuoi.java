/* ===== DB-Lab - [SOAP Character] Reverse a String | qCode: USwyQ8vH ===== */
/*
Dịch vụ SOAP CharacterService, WSDL: http://<Exam_IP>:2221/CharacterService?wsdl.
- Tạo Web Service Client từ WSDL (wsimport hoặc NetBeans/IntelliJ Web Service Client wizard).
- Gọi requestString(studentCode, qCode) để nhận về một chuỗi ký tự từ server. Ví dụ: "HelloWorld".
- Đảo ngược chuỗi nhận được. Ví dụ: "HelloWorld" -> "dlroWolleH".
- Gọi submitString(studentCode, qCode, reversedString) để gửi kết quả trở lại server.
- Kết thúc chương trình client. Server trả về status string ("AC" hoặc "WA").
Ví dụ: Input ovCLzARFinQhdzF -> Output FzdhQniFRAzLCvo
*/
// Bài lấy từ hệ thống DB-Lab (Exam Server 36.50.135.242), chưa chạy thử với server thật.
// Stub (package vn.medianews) cần sinh lại từ WSDL của đề (cổng 2221); tên lớp/phương thức theo đúng đề, nếu stub sinh ra khác thì sửa lại cho khớp.
package WebService;
import vn.medianews.*;
import java.util.*;
public class Character12_DaoNguocChuoi {
    public static void main(String[] args) throws Exception {
        String msv = "B23DCCN139", qCode = "USwyQ8vH";
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();
        String s = port.requestString(msv, qCode);
        String res = new StringBuilder(s).reverse().toString();
        System.out.println(s + " -> " + res);
        // Tên phương thức theo đề: submitString (stub cũ có thể là submitCharacterString)
        port.submitString(msv, qCode, res);
    }
}
