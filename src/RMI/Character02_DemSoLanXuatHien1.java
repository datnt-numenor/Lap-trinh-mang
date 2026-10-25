/* ===== ĐỀ SỐ 2 [B21DCCN564] - BÀI 2 [Character] ĐẾM SỐ LẦN XUẤT HIỆN – 1 | qCode: U5BEBBOW ===== */
/*
BÀI 2. [Character]ĐẾM SỐ LẦN XUẤT HIỆN – 1

[Mã câu hỏi (qCode): U5BEBBOW]. Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý chuỗi. Giao diện từ xa: public interface CharacterService extends Remote { public String requestCharacter(String studentCode, String qCode) throws RemoteException; public void submitCharacter(String studentCode, String qCode, String strSubmit) throws RemoteException; } Trong đó:

- Interface CharacterService được viết trong package RMI.
- Đối tượng cài đặt giao diện từ xa CharacterService được đăng ký với RegistryServer với tên là: RMICharacterService. Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với chuỗi được nhận từ RMI Server:
a. Triệu gọi phương thức requestCharacter để nhận chuỗi ngẫu nhiên từ server với định dạng: "Chuỗi đầu vào".
b. Thực hiện đếm tần số xuất hiện của mỗi ký tự trong chuỗi đầu vào và tạo ra chuỗi kết quả theo định dạng <Ký tự><Số lần xuất hiện>, sắp xếp theo thứ tự xuất hiện của các ký tự trong chuỗi. Ví dụ: Chuỗi đầu vào "AAABBC" à Kết quả: "A3B2C1".
c. Triệu gọi phương thức submitCharacter để gửi chuỗi kết quả trở lại server.
d. Kết thúc chương trình client.
*Note: Đếm cả dấu cách nhé*
*/
package RMI;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import RMI.CharacterService;
public class Character02_DemSoLanXuatHien1 {
    public static void main(String[] args) throws Exception {
        // a. Nhận chuỗi từ server
        Registry rg = LocateRegistry.getRegistry("36.50.135.242", 1099);
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");
        String s = sv.requestCharacter("B23DCCN139", "U5BEBBOW");
        System.out.println(s);
        // b. Xử lý xâu
        String res = "";
        int[] cnt = new int[256]; 
        for (char x : s.toCharArray()) cnt[x]++;
        for(char x: s.toCharArray()){
            if(cnt[x] > 0){
                res+=String.format("%c%d", x, cnt[x]);
                cnt[x] = 0;
            }
        }
        System.out.println(res);
        // c. Gửi kết quả lại server
        sv.submitCharacter("B23DCCN139", "U5BEBBOW", res);
    }
}


