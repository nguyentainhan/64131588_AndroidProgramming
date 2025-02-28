package nguyentainhan.edu.btcd;

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
//    EditText editTextCannang;
//    EditText editTextChieucao;
//    EditText editTextKq;
//    Button tinhBMI;
//    void TimDieuKhien(){
//        editTextCannang =(EditText)findViewById(R.id.edtWeight);
//        editTextChieucao =(EditText)findViewById(R.id.edtHeight);
//        tinhBMI = (Button)findViewById(R.id.btnCalculate);
//        editTextKq = (EditText)findViewById(R.id.edtKq);
//
//    }
    private  EditText editWeight, edtHeight, edtKq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editWeight = findViewById(R.id.edtWeight);
        edtHeight = findViewById(R.id.edtHeight);
        edtKq = findViewById(R.id.edtKq);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void tinhBMI(View v){
        String weightStr = editWeight.getText().toString().trim();
        String heightStr = edtHeight.getText().toString().trim();
        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            edtKq.setText("Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr);

            if (height <= 0 || weight <= 0) {
                edtKq.setText("Giá trị không hợp lệ!");
                return;
            }

        float bmi = weight / (height * height);
        String resultText = String.format("%.2f", bmi) + " - ";

        if (bmi < 18.5) {
            resultText += "Gầy";
        } else if (bmi < 24.9) {
            resultText += "Bình thường";
        } else if (bmi < 29.9) {
            resultText += "Thừa cân";
        } else {
            resultText += "Béo phì";
        }

        edtKq.setText(resultText);
    } catch (NumberFormatException e) {
        edtKq.setText("Lỗi nhập liệu!");
    }
}

}
