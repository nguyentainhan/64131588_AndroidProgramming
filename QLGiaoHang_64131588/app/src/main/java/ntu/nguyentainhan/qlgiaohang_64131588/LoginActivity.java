package ntu.nguyentainhan.qlgiaohang_64131588;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout Lemail, Lpassword;
    private Button btnLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Tham chiếu các View từ layout
        Lemail = findViewById(R.id.Lemail);
        Lpassword = findViewById(R.id.Lpassword);
        btnLogin = findViewById(R.id.btnlogin);
        mAuth = FirebaseAuth.getInstance(); // Khởi tạo FirebaseAuth

        // Xử lý sự kiện nhấn nút đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy chuỗi email và mật khẩu từ TextInputLayout
                String email = Lemail.getEditText().getText().toString().trim();
                String password = Lpassword.getEditText().getText().toString().trim();

                // Kiểm tra nếu email hoặc mật khẩu bị bỏ trống
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this,
                            "Vui lòng nhập đầy đủ email và mật khẩu!",
                            Toast.LENGTH_SHORT).show();
                    return; // Dừng xử lý tiếp
                }

                // Thực hiện đăng nhập với Firebase Authentication
                mAuth.signInWithEmailAndPassword("nhan.nt.64cntt@ntu.edu.vn", "nguyentainhan1909")
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Đăng nhập thành công
                                    startActivity(new Intent(LoginActivity.this,FoodPanel_BottomNavigation.class));
                                    // TODO: Chuyển sang Activity khác hoặc xử lý tiếp
                                } else {
                                    // Đăng nhập thất bại, hiển thị thông báo lỗi rõ ràng
                                    String errorMsg = "Đăng nhập thất bại.";
                                    if (task.getException() != null) {
                                        errorMsg = "Đăng nhập thất bại: ";
                                    }
                                    Toast.makeText(LoginActivity.this,
                                            errorMsg,
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}