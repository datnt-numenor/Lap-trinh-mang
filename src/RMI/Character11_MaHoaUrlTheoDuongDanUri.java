/* ===== DB-Lab - [RMI Character] Mã hóa URL theo quy tắc đường dẫn URI | qCode: QOZD6MLM ===== */
/*
Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý chuỗi.
Giao diện từ xa:
public interface CharacterService extends Remote {
    public String requestCharacter(String studentCode, String qCode) throws RemoteException;
    public void submitCharacter(String studentCode, String qCode, String strSubmit) throws RemoteException;
}
Trong đó:
- Interface CharacterService được viết trong package RMI.
- Đối tượng cài đặt giao diện từ xa CharacterService được đăng ký với RegistryServer với tên là: RMICharacterService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với chuỗi được nhận từ RMI Server:
a. Triệu gọi phương thức requestCharacter để nhận chuỗi ngẫu nhiên từ server. Chuỗi này gồm chữ, số, dấu cách và các ký tự đặc biệt như ~=+%^* /()[]{}!@#$?|. (trong đề không có dấu cách sau dấu *; thêm để không đóng comment)
b. Thực hiện thao tác mã hóa URL (URL Encoding) cho chuỗi đầu vào nhận được từ server. Mã hóa theo quy tắc phần đường dẫn của URI (tương đương new java.net.URI(null, null, s, null).toASCIIString()): dấu cách, ký tự không hợp lệ trong URI (như % " < > [ ] { } | \ ^), ?, # và ký tự ngoài ASCII được thay bằng %XX (UTF-8); chữ, số và - _ . ~ ! * ' ( ) ; : @ & = + $ , và dấu gạch chéo giữ nguyên.
Ví dụ: Chuỗi ban đầu "Hello World!" -> Chuỗi mã hóa URL là: "Hello%20World!"
c. Triệu gọi phương thức submitCharacter để gửi chuỗi đã được mã hóa trở lại server.
d. Kết thúc chương trình client.
*/
// Bài lấy từ hệ thống DB-Lab (Exam Server 36.50.135.242), chưa chạy thử với server thật.
// Khác Character10_MaHoaUrl: bài đó dùng URLEncoder (dấu cách -> "+"), bài này dùng java.net.URI nên dấu cách -> "%20".
package RMI;
import java.net.URI;
import java.rmi.registry.*;
public class Character11_MaHoaUrlTheoDuongDanUri {
    public static void main(String[] args) throws Exception {
        String msv = "B23DCCN139", qCode = "QOZD6MLM";
        // a. Nhận chuỗi từ server
        Registry rg = LocateRegistry.getRegistry("36.50.135.242", 1099);
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");
        String s = sv.requestCharacter(msv, qCode);
        System.out.println(s);
        // b. Mã hóa theo quy tắc phần path của URI. Constructor nhiều tham số tự mã hóa các ký tự không hợp lệ
        String res = new URI(null, null, s, null).toASCIIString();
        System.out.println(res);
        // c. Gửi kết quả
        sv.submitCharacter(msv, qCode, res);
    }
}
