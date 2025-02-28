package nguyentainhan.edu.btcd2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText edtInput, edtKq;
    private Spinner spinnerUnit;
    private Button btnChuyendoi;
    private String selectedUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        edtInput = findViewById(R.id.edtInput);
        edtKq = findViewById(R.id.edtKq);
        spinnerUnit = findViewById(R.id.spinnerUnit);
        btnChuyendoi = findViewById(R.id.btnChuyendoi);

        // Danh sách đơn vị đo
        String[] units = {"Chọn đơn vị đo", "cm → inch", "inch → cm", "kg → pound", "pound → kg"};
        // Gán danh sách vào Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit.setAdapter(adapter);
        spinnerUnit.setSelection(0, false); // Chọn mặc định là "Chọn đơn vị đo"

        // Lắng nghe sự kiện chọn Spinner
        spinnerUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUnit = parent.getItemAtPosition(position).toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                selectedUnit = "Chọn đơn vị đo";
            }
        });

        // Xử lý sự kiện nhấn nút
        btnChuyendoi.setOnClickListener(view -> chuyendoi(view));

    }
    // Hàm chuyển đổi đơn vị
    public void chuyendoi(View view) {
        String inputStr = edtInput.getText().toString().trim();

        if (inputStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập giá trị!", Toast.LENGTH_SHORT).show();
            return;
        }

        double inputValue = Double.parseDouble(inputStr);
        double result = 0;

        switch (selectedUnit) {
            case "cm → inch":
                result = inputValue * 0.393701;
                break;
            case "inch → cm":
                result = inputValue * 2.54;
                break;
            case "kg → pound":
                result = inputValue * 2.20462;
                break;
            case "pound → kg":
                result = inputValue * 0.453592;
                break;
            default:
                Toast.makeText(this, "Vui lòng chọn đơn vị đo!", Toast.LENGTH_SHORT).show();
                return;
        }

        edtKq.setText(String.format("%.2f", result));
    }

}