package nguyentainhan.edu.vd3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
//khai báo các đối tượng gắn với điều khiển tương ứng
    EditText editTextSo1;
    EditText editTextSo2;
    EditText editTextKq;
    Button nutCong, nutTru, nutNhan, nutChia;
    void TimDieuKhien(){
       editTextSo1 =(EditText)findViewById(R.id.edtSo1);
       editTextSo2 =(EditText)findViewById(R.id.edtSo2);
       editTextKq = (EditText)findViewById(R.id.edtKq);
        nutCong = (Button)findViewById(R.id.btnCong);
        nutTru = (Button)findViewById(R.id.btnTru);
        nutNhan = (Button)findViewById(R.id.btnNhan);
        nutChia = (Button)findViewById(R.id.btnChia);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TimDieuKhien();
        //Gắn bộ lắng nghe sự kiến và code xử lí cho từng nút
        nutCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lấy dữ liệu từ 2 điều khiển đó
                String soThu1 = editTextSo1.getText().toString();
                String soThu2 = editTextSo2.getText().toString();
                //chuyển dữ liệu từ chuỗi sang số
                float soA = Float.parseFloat(soThu1);
                float soB = Float.parseFloat(soThu2);
                //tính toán
                float Tong = soA + soB;
                //xuất
                String chuoiKq = String.valueOf(Tong);
                //kết quả
                editTextKq.setText(chuoiKq);
            }
        });

        nutTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lấy dữ liệu từ 2 điều khiển đó
                String soThu1 = editTextSo1.getText().toString();
                String soThu2 = editTextSo2.getText().toString();
                //chuyển dữ liệu từ chuỗi sang số
                float soA = Float.parseFloat(soThu1);
                float soB = Float.parseFloat(soThu2);
                //tính toán
                float Tru = soA - soB;
                //xuất
                String chuoiKq = String.valueOf(Tru);
                //kết quả
                editTextKq.setText(chuoiKq);
            }
        });

        nutNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lấy dữ liệu từ 2 điều khiển đó
                String soThu1 = editTextSo1.getText().toString();
                String soThu2 = editTextSo2.getText().toString();
                //chuyển dữ liệu từ chuỗi sang số
                float soA = Float.parseFloat(soThu1);
                float soB = Float.parseFloat(soThu2);
                //tính toán
                float Nhan = soA * soB;
                //xuất
                String chuoiKq = String.valueOf(Nhan);
                //kết quả
                editTextKq.setText(chuoiKq);
            }
        });

        nutChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lấy dữ liệu từ 2 điều khiển đó
                String soThu1 = editTextSo1.getText().toString();
                String soThu2 = editTextSo2.getText().toString();
                //chuyển dữ liệu từ chuỗi sang số
                float soA = Float.parseFloat(soThu1);
                float soB = Float.parseFloat(soThu2);
                //tính toán
                float Chia = soA / soB;
                //xuất
                String chuoiKq = String.valueOf(Chia);
                //kết quả
                editTextKq.setText(chuoiKq);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}