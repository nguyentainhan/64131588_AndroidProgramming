package ntu.nguyentainhan.healthcare_project_64131588;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Dang_ki_Activity extends AppCompatActivity {
    EditText edtUsername, edtEmail, edtPassword, edtCofirm;
    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        edtUsername = findViewById(R.id.edtNewUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtNewPassword);
        edtCofirm = findViewById(R.id.edtCofirmPassword);
        btn = findViewById(R.id.btnDangki);
        tv = findViewById(R.id.tvAccount);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dang_ki_Activity.this, LoginActivity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String cofirmpassword = edtCofirm.getText().toString();
                if (username.length() == 0 || password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đúng", Toast.LENGTH_LONG).show();
                } else {
                    if (password.compareTo(cofirmpassword) == 0) {
                        if(isValid(password)){

                            Toast.makeText(getApplicationContext(), "Thêm dữ liệu thành công ", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Dang_ki_Activity.this, LoginActivity.class));
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Mật khẩu phải có ít nhất 8 ký tự, 1 chữ cái, 1 chữ số, 1 ký tự đặc biệt. ", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp! Hãy nhập lại. ", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    public  static boolean isValid(String passwordhere){
        int f1=0, f2=0, f3=0;
        if(passwordhere.length() < 8){
            return false;
        }else {
            for(int p = 0; p < passwordhere.length(); p++){
                if(Character.isLetter(passwordhere.charAt(p))){
                    f1=1;
                }
            }
            for(int r = 0; r < passwordhere.length(); r++){
                if(Character.isDigit(passwordhere.charAt(r))){
                    f2=1;
                }
            }
            for(int s = 0; s < passwordhere.length(); s++){
                char c = passwordhere.charAt(s);
                if(c>=33&&c<=46||c==64){
                    f3=1;
                }
            }
            if(f1 == 1 && f2==1&&f3==1)
                return true;
            return false;
        }
    }
}