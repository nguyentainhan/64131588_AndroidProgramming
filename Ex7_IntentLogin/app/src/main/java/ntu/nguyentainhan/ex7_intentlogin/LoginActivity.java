package ntu.nguyentainhan.ex7_intentlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        //Tìm điều kiện
        Button btnXacNhan = (Button) findViewById(R.id.btnOk);

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xử lý đăng nhập
                //lấy dữ liệu
                //B1 Tìm tham chiếu đến điều kiện
                EditText edTenDN = (EditText) findViewById(R.id.edtUsername);
                EditText edPass = (EditText) findViewById(R.id.edtPass);
                //B2 lấy dữ liệu
                String tenDangNhap = edTenDN.getText().toString();
                String mk = edPass.getText().toString();
                //Kiểm tra mật khẩu
                if( tenDangNhap.equals("nguyentainhan") && mk.equals("123")) //mk đúng
                {
                    //chuyển sang màn hình home
                    Intent iQuiz = new Intent(LoginActivity.this, HomeActivity.class);
                    iQuiz.putExtra("ten_dang_nhap", tenDangNhap);
                    iQuiz.putExtra("mk_dang_nhap", mk);
                    startActivity(iQuiz);
                }
                else {
                    Toast.makeText(LoginActivity.this, "BẠN NHẬP SAI THÔNG TIN", Toast.LENGTH_LONG);
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}