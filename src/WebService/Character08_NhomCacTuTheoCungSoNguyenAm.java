/* ===== ĐỀ SỐ 10 [B21DCCN012] - BÀI 1 NHÓM CÁC TỪ THEO CÙNG SỐ NGUYÊN ÂM | qCode: 32He7sZg ===== */
/*
BÀI 1. NHÓM CÁC TỪ THEO CÙNG SỐ NGUYÊN ÂM

[Mã câu hỏi (qCode): 32He7sZg]. Một dịch vụ web được định nghĩa và mô tả trong tệp CharacterService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:2221/CharacterService?wsdl để xử lý các bài toán về chuỗi và ký tự. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với CharacterService thực hiện các công việc sau:

a. Triệu gọi phương thức requestStringArray với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một mảng chuỗi (String[]) từ server.
b. Phân loại các từ trong mảng chuỗi thành các nhóm có cùng số lượng nguyên âm. Tạo một chuỗi cho mỗi nhóm, trong đó liệt kê các từ cách nhau bằng dấu phẩy, và sắp xếp các từ theo thứ tự từ điển trong mỗi nhóm.
c. Triệu gọi phương thức submitCharacterStringArray(String studentCode, String qCode, String[] data) để gửi mảng chuỗi kết quả trở lại server, trong đó mỗi phần tử là một nhóm từ với cùng số lượng nguyên âm. Ví dụ: Nếu mảng chuỗi nhận được từ phương thức requestCharacter là ["apple", "banana", "pear", "grape", "kiwi"], các nhóm có thể là:
- Nhóm 2 nguyên âm: "apple, banana"
- Nhóm 1 nguyên âm: "grape, kiwi, pear" Mảng kết quả sẽ là ["apple, banana", "grape, kiwi, pear"], và mảng này sẽ được gửi lại server qua phương thức submitCharacter. Sắp xếp theo nguyên tắc sau: Số nguyên âm tăng dần theo từng nhón
d. Kết thúc chương trình client.
*/
package WebService;
import vn.medianews.*;
import java.util.*;
public class Character08_NhomCacTuTheoCungSoNguyenAm {
    public static void main(String[] args) throws Exception {
        String msv = "B23DCCN139", qCode = "32He7sZg"; 
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();
        List<String> a = port.requestStringArray(msv, qCode);
        System.out.println(a);
        Collections.sort(a, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if(dem(s1)!=dem(s2)) return dem(s1) - dem(s2);
                return s1.compareTo(s2);
            }
        });
        List<String>ans = new java.util.ArrayList<>();
        String gr = a.get(0);
        for(int i = 1;i<a.size();i++){
            if(dem(a.get(i))==dem(a.get(i - 1))) gr+=", " + a.get(i);
            else{
                ans.add(gr);
                gr = a.get(i);
            }
        }
        ans.add(gr);
        // c. Triệu gọi phương thức submitCharacterStringArray để gửi mảng đã sắp xếp
        port.submitCharacterStringArray(msv, qCode, ans);
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
