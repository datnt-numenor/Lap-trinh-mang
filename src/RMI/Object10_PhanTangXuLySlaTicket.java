/* ===== DB-Lab - [RMI Object] Phân tầng xử lý SLA ticket | qCode: OKVHS9qH ===== */
/*
Một chương trình RMI Server cung cấp giao diện từ xa ObjectService, được đăng ký với RegistryServer bằng tên RMIObjectService.
Tạo interface và lớp trong package RMI của project client, đúng tên, chữ ký phương thức, serialVersionUID, tên và kiểu của từng thuộc tính:
  ObjectService: Serializable requestObject(String studentCode, String qCode); void submitObject(String studentCode, String qCode, Serializable object)
  TicketSla (serialVersionUID = 20260517L): ticketId String, priority String (LOW, MEDIUM, HIGH, CRITICAL), openedHoursAgo int, breached boolean, action String;
  hàm khởi dựng TicketSla() và TicketSla(String ticketId, String priority, int openedHoursAgo); getter/setter cho tất cả thuộc tính.
a. Dùng mã sinh viên và mã câu hỏi làm tham số khi gọi RMI.
b. Triệu gọi requestObject(studentCode, qCode) để nhận đối tượng RMI.TicketSla. Ví dụ: priority=HIGH; openedHoursAgo=10.
c. Thiết lập breached = true khi vượt ngưỡng theo priority:
   CRITICAL: openedHoursAgo > 2; HIGH: > 8; MEDIUM: > 24; LOW: > 72.
   Thiết lập action: MONITOR nếu không vi phạm; ESCALATE_L2 nếu vi phạm và (priority là CRITICAL hoặc openedHoursAgo > 96); ESCALATE_L1 cho các trường hợp vi phạm còn lại.
d. Triệu gọi submitObject(studentCode, qCode, object) để gửi lại đối tượng đã cập nhật. Ví dụ: breached=true; action=ESCALATE_L1.
e. Kết thúc chương trình client.
*/
// Bài lấy từ hệ thống DB-Lab (Exam Server 36.50.135.242), chưa chạy thử với server thật.
// Lớp RMI.TicketSla nằm ở file TicketSla.java cùng package.
package RMI;
import java.rmi.registry.*;
public class Object10_PhanTangXuLySlaTicket {
    public static void main(String[] args) throws Exception {
        String msv = "B23DCCN139", qCode = "OKVHS9qH";
        Registry rg = LocateRegistry.getRegistry("36.50.135.242", 1099);
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");
        TicketSla t = (TicketSla) sv.requestObject(msv, qCode);
        System.out.println(t);
        int nguong;
        switch (t.getPriority()) {
            case "CRITICAL": nguong = 2; break;
            case "HIGH": nguong = 8; break;
            case "MEDIUM": nguong = 24; break;
            default: nguong = 72; // LOW
        }
        boolean breached = t.getOpenedHoursAgo() > nguong;
        t.setBreached(breached);
        if (!breached) {
            t.setAction("MONITOR");
        } else if (t.getPriority().equals("CRITICAL") || t.getOpenedHoursAgo() > 96) {
            t.setAction("ESCALATE_L2");
        } else {
            t.setAction("ESCALATE_L1");
        }
        System.out.println(t);
        sv.submitObject(msv, qCode, t);
    }
}
