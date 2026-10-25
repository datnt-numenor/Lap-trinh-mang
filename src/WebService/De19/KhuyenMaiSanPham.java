/* ===== ĐỀ SỐ 19 (Dạng mới, WS + UDP) - HỆ THỐNG KHUYẾN MÃI SẢN PHẨM ===== */
/*
ĐỀ SỐ 19 (Dạng mới, WS + UDP)

Một hệ thống hỗ trợ các sản phẩm khuyến mãi cho sinh viên gồm 2 thành phần như sau:

a. WebService triển khai tại địa chỉ [http://203.162.10.109:8080/ProductService](http://203.162.10.109:8080/ProductService) /ProductS?wsdl cung cấp chức năng cho phép client nhận thông tin sản phẩm khuyến mãi (getProduct) và đặt hàng (Order)
b. UDP Server cung cấp chức năng cho phép client lấy thông tin khuyến mãi theo mã sản phẩm
Viết chương trình client giao tiếp theo kịch bản sau:

a. Triệu gọi phương thức getProduct nhận tham số đầu vào là mã sinh viên để nhận về đối tượng Product với giá trị của id và code được thiết lập
b. Gửi thông điệp là một chuỗi chứa id và code sản phẩm đã nhận được đến UDP server tại cổng 2208 theo định dạng “id;code” để nhận về chuỗi mã giảm giá ngẫu nhiên. Tổng các chữ số trong mã giảm giá này chính là giá trị khuyến mãi của sản phẩm
c. Triệu gọi phương thức order trên WS với tham số đầu vào là đối tượng Product với đầy đủ thông tin cần thiết để đặt sản phẩm
d. Kết thúc chương trình
*/
// Đáp án bổ sung (không có trong repo gốc), chưa kiểm chứng với server thật.
// LƯU Ý: đề không cho tên lớp/field của WebService ProductS nên các tên dưới đây là GIẢ ĐỊNH
// theo quy ước wsimport (ProductS_Service, getProductSPort, Product.getId/getCode/setDiscount, order).
// Sinh stub bằng NetBeans "Web Service Client" từ
// http://203.162.10.109:8080/ProductService/ProductS?wsdl rồi sửa import/tên cho khớp.
package WebService.De19;
import java.net.*;
import productservice.*; // GIẢ ĐỊNH: đổi thành package stub thực tế
public class KhuyenMaiSanPham {
    public static void main(String[] args) throws Exception {
        String msv = "B23DCCN139";
        // a. Lấy sản phẩm từ WebService
        ProductS_Service service = new ProductS_Service();
        ProductS port = service.getProductSPort();
        Product p = port.getProduct(msv);
        System.out.println(p.getId() + ";" + p.getCode());
        // b. Gửi "id;code" tới UDP server (cổng 2208) để nhận mã giảm giá ngẫu nhiên
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        byte[] req = (p.getId() + ";" + p.getCode()).getBytes();
        socket.send(new DatagramPacket(req, req.length, sA, 2208));
        DatagramPacket nhan = new DatagramPacket(new byte[4096], 4096);
        socket.receive(nhan);
        String maGiamGia = new String(nhan.getData(), 0, nhan.getLength()).trim();
        socket.close();
        // Tổng các chữ số trong mã giảm giá = giá trị khuyến mãi
        int khuyenMai = 0;
        for (char c : maGiamGia.toCharArray()) {
            if (Character.isDigit(c)) khuyenMai += c - '0';
        }
        System.out.println(maGiamGia + " -> " + khuyenMai);
        // c. Đặt hàng với Product đã có thông tin khuyến mãi
        p.setDiscount(khuyenMai);
        port.order(p);
    }
}
