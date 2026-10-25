/* ===== ĐỀ SỐ 3 [B21DCCN003] - BÀI 2 SẮP XẾP CHUỖI THEO ĐỘ DÀI CHUỖI | qCode: mpu9xCxR ===== */
/*
BÀI 2. SẮP XẾP CHUỖI THEO ĐỘ DÀI CHUỖI

[Mã câu hỏi (qCode): mpu9xCxR]. Một dịch vụ web được định nghĩa và mô tả trong tệp CharacterService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/CharacterService?wsdl để xử lý các bài toán về chuỗi và ký tự. Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với CharacterService thực hiện các công việc sau:

a. Triệu gọi phương thức requestStringArray với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một mảng chuỗi (List<String>) từ server.
b. Sắp xếp mảng theo độ dài của từ theo thứ tự tăng dần độ dài của từ.
c. Triệu gọi phương thức submitCharacterStringArray(String studentCode, String qCode, List<String>) để gửi mảng chuỗi đã sắp xếp trở lại server. Ví dụ: Nếu mảng chuỗi nhận được từ phương thức requestCharacter là ["apple", "banana", "fig", "pineapple"] mảng sắp xếp tăng dần theo độ dài sẽ là ["fig", "apple", "banana", "pineapple"].
d. Kết thúc chương trình client.
*/
package WebService;
import vn.medianews.*;
import java.util.*;
public class Character03_SapXepChuoiTheoDoDaiChuoi {
    public static void main(String[] args) throws Exception {
        // Cấu hình thông tin
        String msv = "B21DCCN003", qCode = "mpu9xCxR"; 
        // Tạo đối tượng dịch vụ từ web service
        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();
        // a. Triệu gọi phương thức requestStringArray từ server 
        List<String> a = port.requestStringArray(msv, qCode);
        // b. Sắp xếp các chuỗi theo số lượng nguyên âm
        Collections.sort(a, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        // c. Triệu gọi phương thức submitCharacterStringArray để gửi mảng đã sắp xếp
        port.submitCharacterStringArray(msv, qCode, a);
        System.out.println(a);
    }
}