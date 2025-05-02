package ntu.nguyentainhan.qlgiaohang_64131588;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity2 extends AppCompatActivity {
    //đăng kí
    TextInputEditText tvHo, tvTen, btnemail, btnpass, btnconfirm, btnnum, tvdiachi, numpin;
    Spinner citySpinner;
    Button btnSignup, btnEmailAlt;

    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        tvHo = findViewById(R.id.tvho);
        tvTen = findViewById(R.id.tvTen);
        btnemail = findViewById(R.id.btnemail);
        btnpass = findViewById(R.id.btnpass);
        btnconfirm = findViewById(R.id.btnconfirm);
        btnnum = findViewById(R.id.btnnum);
        tvdiachi = findViewById(R.id.tvdiachi);
        numpin = findViewById(R.id.numpin);

        citySpinner = findViewById(R.id.Citys);
        btnSignup = findViewById(R.id.Signup);
        btnEmailAlt = findViewById(R.id.email); // Button "Đăng ký với email"
        FAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");


        String[] cities = {"Hà Nội", "Hồ Chí Minh", "Đà Nẵng", "Nha Trang"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cities);
        citySpinner.setAdapter(adapter);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ho = tvHo.getText().toString().trim();
                String ten = tvTen.getText().toString().trim();
                String email = btnemail.getText().toString().trim();
                String pass = btnpass.getText().toString();
                String confirm = btnconfirm.getText().toString();
                String phone = btnnum.getText().toString().trim();
                String diachi = tvdiachi.getText().toString().trim();
                String pin = numpin.getText().toString().trim();
                String city = citySpinner.getSelectedItem().toString();

                if (ho.isEmpty() || ten.isEmpty() || email.isEmpty() || pass.isEmpty() || confirm.isEmpty() ||
                        phone.isEmpty() || diachi.isEmpty() || pin.isEmpty()) {
                    Toast.makeText(HomeActivity2.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!pass.equals(confirm)) {
                    Toast.makeText(HomeActivity2.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tạo tài khoản Firebase
                FAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String uid = FAuth.getCurrentUser().getUid();
                        UserModel user = new UserModel(ho, ten, email, phone, diachi, pin, city);

                        // Lưu vào Realtime Database
                        databaseReference.child(uid).setValue(user).addOnCompleteListener(dbTask -> {
                            if (dbTask.isSuccessful()) {
                                Toast.makeText(HomeActivity2.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(HomeActivity2.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(HomeActivity2.this, "Lỗi lưu dữ liệu: " + dbTask.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                    } else {
                        Toast.makeText(HomeActivity2.this, "Đăng ký thất bại: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}