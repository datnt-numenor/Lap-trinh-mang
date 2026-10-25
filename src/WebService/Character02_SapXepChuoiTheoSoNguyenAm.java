/* ===== ĐỀ SỐ 2 [B21DCCN002] - BÀI 2 SẮP XẾP CHUỖI THEO SỐ NGUYÊN ÂM | qCode: x5TIDg1S ===== */
/*
(Đề lấy từ file "HƯỚNG DẪN SETUP WS - MỚI.md", ví dụ minh họa với CharacterService, MSV B21DCCN002)

[Mã câu hỏi (qCode): x5TIDg1S]. Một dịch vụ web được định nghĩa và mô tả trong tệp CharacterService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/CharacterService?wsdl để xử lý các bài toán về chuỗi và ký tự. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với CharacterService thực hiện các công việc sau:

a. Triệu gọi phương thức requestStringArray với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một mảng chuỗi (String[]) từ server.
b. Sắp xếp các chuỗi trong mảng theo số lượng nguyên âm tăng dần. Nếu hai chuỗi có cùng số lượng nguyên âm, giữ nguyên thứ tự xuất hiện ban đầu của chúng trong mảng.
c. Triệu gọi phương thức submitCharacterStringArray(String studentCode, String qCode, String[] data) để gửi mảng chuỗi đã sắp xếp trở lại server. Ví dụ: Nếu mảng chuỗi nhận được từ phương thức requestCharacter là ["apple", "kiwi", "banana", "pear"], số lượng nguyên âm trong các từ là:
- "apple" có 2 nguyên âm
- "kiwi" có 2 nguyên âm
- "pear" có 2 nguyên âm
- "banana" có 3 nguyên âm
Sau khi sắp xếp theo số lượng nguyên âm tăng dần, kết quả sẽ là ["apple", "kiwi", "pear", "banana"]. Mảng này sẽ được gửi lại server qua phương thức submitCharacter.
d. Kết thúc chương trình client.
*/
package WebService;
import vn.medianews.*;
import java.util.*;
public class Character02_SapXepChuoiTheoSoNguyenAm {
    public static void main(String[] args) throws Exception {
        // Cấu hình thông tin
        String msv = "B21DCCN002", qCode = "x5TIDg1S"; 
        // Tạo đối tượng dịch vụ từ web service
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();
        // a. Triệu gọi phương thức requestStringArray từ server 
        List<String> a = port.requestStringArray(msv, qCode);
        // b. Sắp xếp các chuỗi theo số lượng nguyên âm
        Collections.sort(a, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return dem(s1) - dem(s2);
            }
        });
        // c. Triệu gọi phương thức submitCharacterStringArray để gửi mảng đã sắp xếp
        port.submitCharacterStringArray(msv, qCode, a);
        System.out.println(a);
    }
    // Hàm đếm số nguyên âm trong chuỗi
    private static int dem(String str) {
        int cnt = 0;
        // Các ký tự nguyên âm
        String ngAm = "aeiouAEIOU";
        for (int i = 0; i < str.length(); i++) {
            if (ngAm.indexOf(str.charAt(i)) != -1) cnt++;
        }
        return cnt;
    }
}
