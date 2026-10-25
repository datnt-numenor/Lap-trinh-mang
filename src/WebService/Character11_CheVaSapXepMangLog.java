/* ===== DB-Lab - [SOAP Character] Che và sắp xếp mảng log | qCode: 43odKOzj ===== */
/*
Dịch vụ SOAP 1.1 CharacterService?wsdl tại http://<Exam_IP>:2221/CharacterService?wsdl.
a. Dùng mã sinh viên và mã câu hỏi làm tham số khi gọi các phương thức SOAP.
b. Triệu gọi requestStringArray(studentCode, qCode) để nhận danh sách chuỗi log List<String>.
   Ví dụ một phần tử: ERROR user=a email=a@example.com phone=0123456789 token=abc.
c. Che email, số điện thoại và token trong từng dòng, sau đó sắp xếp các dòng theo mức ERROR, WARN, INFO.
d. Triệu gọi submitStringArray(studentCode, qCode, data) để gửi danh sách chuỗi đã xử lý.
   Ví dụ phần tử nộp lại: ERROR user=a email=[EMAIL] phone=[PHONE] token=[TOKEN].
e. Kết thúc chương trình client.
*/
// Bài lấy từ hệ thống DB-Lab (Exam Server 36.50.135.242), chưa chạy thử với server thật.
// Stub (package vn.medianews) cần sinh lại từ WSDL của đề (cổng 2221); tên lớp/phương thức theo đúng đề, nếu stub sinh ra khác thì sửa lại cho khớp.
package WebService;
import vn.medianews.*;
import java.util.*;
public class Character11_CheVaSapXepMangLog {
    public static void main(String[] args) throws Exception {
        String msv = "B23DCCN139", qCode = "43odKOzj";
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();
        List<String> a = port.requestStringArray(msv, qCode);
        List<String> res = new ArrayList<>();
        for (String line : a) {
            String s = line.replaceAll("email=\\S+", "email=[EMAIL]")
                           .replaceAll("phone=\\S+", "phone=[PHONE]")
                           .replaceAll("token=\\S+", "token=[TOKEN]");
            res.add(s);
        }
        // Sắp xếp ổn định theo mức log (từ đầu dòng): ERROR < WARN < INFO
        final List<String> thuTu = Arrays.asList("ERROR", "WARN", "INFO");
        Collections.sort(res, (x, y) -> thuTu.indexOf(x.split("\\s+")[0]) - thuTu.indexOf(y.split("\\s+")[0]));
        for (String x : res) System.out.println(x);
        // Tên phương thức theo đề: submitStringArray (stub cũ có thể là submitCharacterStringArray)
        port.submitStringArray(msv, qCode, res);
    }
}
