/* ===== DB-Lab - [WS Character] Xoay vòng ký tự theo mã ASCII ký tự đầu | qCode: z09XhHWr ===== */
/*
Dịch vụ web SOAP 1.1 CharacterService.wsdl tại http://<Exam_IP>:2221/CharacterService?wsdl.
a. Triệu gọi requestCharacter(studentCode, qCode) để nhận danh sách ký tự (List<Integer>) từ server.
b. Xoay vòng các ký tự trong danh sách theo chiều phải, số lần xoay bằng giá trị ASCII của ký tự đầu tiên.
c. Triệu gọi submitCharacterCharArray(String studentCode, String qCode, List<Integer> data) để gửi mảng đã xoay vòng.
   Ví dụ: ['a','b','c','d'], 'a' = 97, xoay phải 97 lần -> ['d','a','b','c'].
d. Kết thúc chương trình client.
*/
// Bài lấy từ hệ thống DB-Lab (Exam Server 36.50.135.242), chưa chạy thử với server thật.
// Stub (package vn.medianews) cần sinh lại từ WSDL của đề (cổng 2221); tên lớp/phương thức theo đúng đề, nếu stub sinh ra khác thì sửa lại cho khớp.
package WebService;
import vn.medianews.*;
import java.util.*;
public class Character10_XoayVongKyTuTheoAscii {
    public static void main(String[] args) throws Exception {
        String msv = "B23DCCN139", qCode = "z09XhHWr";
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();
        List<Integer> a = new ArrayList<>(port.requestCharacter(msv, qCode));
        System.out.println(a);
        // Collections.rotate tự lấy phần dư theo kích thước danh sách (97 % 4 = 1)
        Collections.rotate(a, a.get(0));
        System.out.println(a);
        port.submitCharacterCharArray(msv, qCode, a);
    }
}
