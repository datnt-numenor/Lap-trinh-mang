package TCP;

/**
 * Object Stream (cổng 2209) – Bài 2: Khách hàng
 * Mã câu hỏi (qCode): fL6WEKh7
 *
 * Mã câu hỏi: fL6WEKh7 · Cổng: 2209
 * Thông tin khách hàng cần thay đổi định dạng lại cho phù hợp với khu vực, cụ thể:
 * a. Tên khách hàng cần được chuẩn hóa theo định dạng mới. Ví dụ: nguyen van hai duong → DUONG, Nguyen Van Hai
 * b. Ngày sinh của khách hàng hiện đang ở dạng mm-dd-yyyy, cần được chuyển thành định dạng dd/mm/yyyy. Ví dụ: 10-11-2012 → 11/10/2012
 * c. Tài khoản khách hàng là các chữ cái in thường được sinh tự động từ họ tên khách hàng. Ví dụ: nguyen van hai duong → nvhduong
 * Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2209 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng đối tượng (ObjectInputStream / ObjectOutputStream) thực hiện gửi/nhận đối tượng khách hàng và chuẩn hóa. Cụ thể:
 * a. Đối tượng trao đổi là thể hiện của lớp Customer được mô tả như sau:
 * • Tên đầy đủ của lớp: TCP.Customer
 * • Các thuộc tính: id int, code String, name String, dayOfBirth String, userName String
 * • Hàm khởi tạo đầy đủ các thuộc tính được liệt kê ở trên
 * • Trường dữ liệu: private static final long serialVersionUID = 20170711L;
 * b. Tương tác với server theo kịch bản dưới đây:
 * 1) Gửi đối tượng là một chuỗi gồm mã sinh viên và mã câu hỏi ở định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;F2DA54F3"
 * 2) Nhận một đối tượng là thể hiện của lớp Customer từ server với các thông tin đã được thiết lập
 * 3) Thay đổi định dạng theo các yêu cầu ở trên và gán vào các thuộc tính tương ứng. Gửi đối tượng đã được sửa đổi lên server.
 * 4) Đóng socket và kết thúc chương trình.
 */

import java.util.*; import java.io.*; import java.net.*;

public class Object02_KhachHang {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("36.50.135.242", 2209);
        socket.setSoTimeout(5000);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        out.writeObject("B23DCCN139;fL6WEKh7");
        out.flush();
        Customer c = (Customer) in.readObject();
        String[] words = c.getName().split(" ");
        // a. Tên: từ cuối ALL_CAPS, các từ còn lại Capitalize (lowercase trước)
        String lastName = words[words.length - 1].toUpperCase();
        StringBuilder rest = new StringBuilder();
        for (int i = 0; i < words.length - 1; i++) {
            if (i > 0) rest.append(" ");
            String w = words[i].toLowerCase();
            rest.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1));
        }
        c.setName(lastName + ", " + rest.toString());
        // b. Ngày sinh: mm-dd-yyyy → dd/mm/yyyy
        String[] parts = c.getDayOfBirth().split("-");
        c.setDayOfBirth(parts[1] + "/" + parts[0] + "/" + parts[2]);
        // c. userName: chữ đầu mỗi từ (trừ từ cuối) + từ cuối, lowercase
        StringBuilder userName = new StringBuilder();
        for (int i = 0; i < words.length - 1; i++) userName.append(words[i].charAt(0));
        userName.append(words[words.length - 1]);
        c.setUserName(userName.toString().toLowerCase());
        out.writeObject(c);
        out.flush();
        in.close(); out.close(); socket.close();
    }
}
