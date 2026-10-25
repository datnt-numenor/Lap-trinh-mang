/* ===== ĐỀ SỐ 8 [B21DCCN010] - BÀI 2 TỪ DÀI NHẤT VÀ TỪ NGẮN NHẤT – 2 | qCode: sGJc3iD5 ===== */
/*
BÀI 2. TỪ DÀI NHẤT VÀ TỪ NGẮN NHẤT – 2

[Mã câu hỏi (qCode): sGJc3iD5]. Một dịch vụ web được định nghĩa và mô tả trong tệp CharacterService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/CharacterService?wsdl để xử lý các bài toán về chuỗi và ký tự. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với CharacterService thực hiện các công việc sau:

a. Triệu gọi phương thức requestStringArray với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một danh sách chuỗi (List<String>) từ server.
b. Xác định từ có độ dài lớn nhất và từ có độ dài nhỏ nhất trong mảng chuỗi. Nếu có nhiều từ có cùng độ dài lớn nhất hoặc nhỏ nhất, chọn từ xuất hiện đầu tiên trong mảng.
c. Tạo một chuỗi mới theo định dạng: "Longest; Shortest".
d. Triệu gọi phương thức submitCharacterString(String studentCode, String qCode, String data) để gửi chuỗi kết quả đã xử lý trở lại server. Ví dụ: Nếu mảng chuỗi nhận được từ phương thức requestCharacter là ["apple", "banana", "fig", "pineapple"], từ có độ dài lớn nhất là "pineapple" và từ có độ dài nhỏ nhất là "fig". Chuỗi kết quả sẽ là "pineapple;fig", và được gửi lại server qua phương thức submitCharacter.
e. Kết thúc chương trình client.
*/
package WebService.B21DCCN010;
import vn.medianews.*;
import java.util.*;
public class LenMaxMin2 {
    public static void main(String[] args) throws Exception {
        String msv = "B21DCCN010", qCode = "sGJc3iD5"; 
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();
        List<String> a = port.requestStringArray(msv, qCode);
        String Max = a.get(0), Min = a.get(0);
        for(String x: a){
            if(x.length() > Max.length()) Max = x;
            if(x.length() < Min.length()) Min = x;
        }
        String ans = Max + ";" + Min;
        port.submitCharacterString(msv, qCode, ans);
    }
}
