/* ===== ĐỀ SỐ 1 [B21DCCN001] - BÀI 2 SẮP XẾP CHUỖI THEO SỐ NGUYÊN ÂM | qCode: mDN1MFUJ ===== */
/*
BÀI 2. SẮP XẾP CHUỖI THEO SỐ NGUYÊN ÂM [Mã câu hỏi (qCode): mDN1MFUJ]. Một dịch vụ web được định nghĩa và mô tả trong tệp CharacterService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/CharacterService?wsdl để xử lý các bài toán về chuỗi và ký tự. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với CharacterService thực hiện các công việc sau:
a. Triệu gọi phương thức requestStringArray với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một mảng chuỗi (List<String>) từ server.
b. Sắp xếp các chuỗi trong mảng theo số lượng nguyên âm tăng dần. Nếu hai chuỗi có cùng số lượng nguyên âm, giữ nguyên thứ tự xuất hiện ban đầu của chúng trong mảng.
c. Triệu gọi phương thức submitCharacterStringArray(String studentCode, String qCode, List<String> data) để gửi mảng chuỗi đã sắp xếp trở lại server. Ví dụ: Nếu mảng chuỗi nhận được từ phương thức requestCharacter là ["apple", "kiwi", "banana", "pear"], số lượng nguyên âm trong các từ là:

|•|"apple" có 2 nguyên âm|
|---|---|
|•|"kiwi" có 2 nguyên âm|
|•|"pear" có 2 nguyên âm|
|•|"banana" có 3 nguyên âm|
 Sau khi sắp xếp theo số lượng nguyên âm tăng dần, kết quả sẽ là ["apple", "kiwi", "pear", "banana"]. Mảng này sẽ được gửi lại server qua phương thức submitCharacter.
d. Kết thúc chương trình client.
*/
// Đáp án bổ sung (không có trong repo gốc), chưa kiểm chứng với server thật.
package WebService;
import vn.medianews.*;
import java.util.*;
public class Character01_SapXepChuoiTheoSoNguyenAm {
    public static void main(String[] args) throws Exception {
        String msv = "B21DCCN001", qCode = "mDN1MFUJ";
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();
        // a. Nhận mảng chuỗi từ server
        List<String> a = new ArrayList<>(port.requestStringArray(msv, qCode));
        System.out.println(a);
        // b. Sắp xếp theo số nguyên âm tăng dần. Collections.sort là sắp xếp ổn định
        //    nên hai chuỗi cùng số nguyên âm vẫn giữ thứ tự ban đầu.
        Collections.sort(a, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return demNguyenAm(s1) - demNguyenAm(s2);
            }
        });
        System.out.println(a);
        // c. Gửi kết quả
        port.submitCharacterStringArray(msv, qCode, a);
    }
    private static int demNguyenAm(String s) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if ("aeiouAEIOU".indexOf(c) != -1) cnt++;
        }
        return cnt;
    }
}
