package till.edu.vd2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Gắn layout tương ứng với file này
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    //Đây là bộ lắng nghe và xử lí sự kiện click lên nút tính tổng
    public void xuliCong(View view){
        //Tìm, tham chiếu đến điều khiển trên tệp XML, maping sang java file
        EditText editTextSoA = findViewById(R.id.edtA);
        EditText editTextSoB = findViewById(R.id.edtB);
        EditText editTextKetQua = findViewById(R.id.edtKq);
        //Lấy dữ liệu về ở điều kiện số a
        String strA = editTextSoA.getText().toString();
        //Lấy dữ liệu về ở điều kiện số b
        String strB = editTextSoB.getText().toString();
        //Chuyển dữ liệu sang dạng số
        int so_A = Integer.parseInt(strA);
        int so_B = Integer.parseInt(strB);
        //Tính toán
        int tong = so_A + so_B;
        String strTong = String.valueOf(tong); //Chuyển sang dạng chuỗi
        //hiển thị ra màn hình
        editTextKetQua.setText(strTong);
    }
}