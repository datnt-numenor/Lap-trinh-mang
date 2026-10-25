/* ===== ĐỀ SỐ 5 [B21DCCN005] - BÀI 1 TỪ DÀI NHẤT VÀ TỪ NGẮN NHẤT | qCode: 9n2rfqST ===== */
/*
BÀI 1. TỪ DÀI NHẤT VÀ TỪ NGẮN NHẤT

[Mã câu hỏi (qCode): 9n2rfqST]. Một dịch vụ web được định nghĩa và mô tả trong tệp CharacterService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:2221/CharacterService?wsdl để xử lý các bài toán về chuỗi và ký tự. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với CharacterService thực hiện các công việc sau:

a. Triệu gọi phương thức requestString với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một chuỗi (String) từ server. Chuỗi này có thể chứa các từ và khoảng trắng.
b. Xử lý chuỗi nhận được để tìm từ có độ dài lớn nhất và từ có độ dài nhỏ nhất trong chuỗi. Nếu có nhiều từ có cùng độ dài lớn nhất hoặc nhỏ nhất, chọn từ xuất hiện đầu tiên trong chuỗi.
c. Tạo một chuỗi mới theo định dạng: "[từ lớn nhất]; [từ nhỏ nhất]".
d. Triệu gọi phương thức submitCharacterString(String studentCode, String qCode, String data) để

gửi chuỗi kết quả đã xử lý trở lại server. Ví dụ: Nếu chuỗi nhận được từ phương thức requestCharacter là "this is a sample test", từ có độ dài lớn nhất là "sample" và từ có độ dài nhỏ nhất là "a". Chuỗi kết quả sẽ là "sample; a", và được gửi lại server qua phương thức submitCharacter.

e. Kết thúc chương trình client.
*/
package WebService;
import vn.medianews.*;
import java.util.*;
public class Character04_TuDaiNhatVaTuNganNhat {
    public static void main(String[] args) throws Exception {
        String msv = "B23DCCN139", qCode = "9n2rfqST"; 
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();
        String s = port.requestString(msv, qCode);
        System.out.println(s);
        String []tmp = s.trim().split("\\s+");
        int lenMax = tmp[0].length(), lenMin  = tmp[0].length();
        String strMax = tmp[0], strMin = tmp[0];
        for(String x: tmp){
            if(x.length() < lenMin){
                strMin = x;
                lenMin = x.length();
            }
            if(x.length() > lenMax){
                strMax = x;
                lenMax = x.length();
            }
        }
        String ans = strMax + ";" + strMin;
        System.out.println(ans);
        port.submitCharacterString(msv, qCode, ans);
    }
}
