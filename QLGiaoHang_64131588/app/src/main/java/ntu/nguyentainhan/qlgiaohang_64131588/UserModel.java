package ntu.nguyentainhan.qlgiaohang_64131588;

public class UserModel {
    public String ho, ten, email, sdt, diachi, pincode, city;

    public UserModel() {
        // Constructor mặc định bắt buộc cho Firebase
    }

    public UserModel(String ho, String ten, String email, String sdt, String diachi, String pincode, String city) {
        this.ho = ho;
        this.ten = ten;
        this.email = email;
        this.sdt = sdt;
        this.diachi = diachi;
        this.pincode = pincode;
        this.city = city;
    }
}
