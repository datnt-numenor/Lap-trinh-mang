/* ===== DB-Lab - [RMI Data] Tìm vị trí đỉnh cục bộ | qCode: TPD9fy3r ===== */
/*
Một chương trình RMI Server cung cấp giao diện DataService trong package RMI, được đăng ký với RegistryServer bằng tên RMIDataService.
a. Sử dụng mã sinh viên và mã câu hỏi làm tham số khi gọi RMI.
b. Triệu gọi requestData(studentCode, qCode) để nhận một mảng số nguyên từ server. Ví dụ: [3,8,5,10,7].
c. Một phần tử là đỉnh cục bộ nếu lớn hơn phần tử liền kề bên trái và bên phải; phần tử đầu/cuối chỉ cần lớn hơn phần tử kề duy nhất. Trả về vị trí theo chỉ số bắt đầu từ 1.
d. Triệu gọi submitData(studentCode, qCode, data) để gửi List<Integer> các vị trí đỉnh cục bộ. Ví dụ: [2,4].
e. Kết thúc chương trình client.
*/
// Bài lấy từ hệ thống DB-Lab (Exam Server 36.50.135.242), chưa chạy thử với server thật.
package RMI;
import java.rmi.registry.*;
import java.util.*;
public class Data11_TimViTriDinhCucBo {
    public static void main(String[] args) throws Exception {
        String msv = "B23DCCN139", qCode = "TPD9fy3r";
        Registry rg = LocateRegistry.getRegistry("36.50.135.242", 1099);
        DataService sv = (DataService) rg.lookup("RMIDataService");
        // a-b. Nhận dữ liệu (đề không nêu rõ kiểu: xử lý cả int[], Integer[], List, String)
        Object raw = sv.requestData(msv, qCode);
        System.out.println(raw);
        List<Integer> a = new ArrayList<>();
        if (raw instanceof int[]) {
            for (int x : (int[]) raw) a.add(x);
        } else if (raw instanceof Integer[]) {
            a.addAll(Arrays.asList((Integer[]) raw));
        } else if (raw instanceof List) {
            for (Object x : (List<?>) raw) a.add(((Number) x).intValue());
        } else {
            String s = raw.toString().replaceAll("[\\[\\]\\s]", "");
            for (String t : s.split(",")) if (!t.isEmpty()) a.add(Integer.parseInt(t));
        }
        // c. Đỉnh cục bộ, vị trí tính từ 1
        int n = a.size();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean trai = (i == 0) || a.get(i) > a.get(i - 1);
            boolean phai = (i == n - 1) || a.get(i) > a.get(i + 1);
            if (trai && phai) res.add(i + 1);
        }
        System.out.println(res);
        // d. Gửi List<Integer> (ArrayList là Serializable)
        sv.submitData(msv, qCode, (java.io.Serializable) res);
    }
}
