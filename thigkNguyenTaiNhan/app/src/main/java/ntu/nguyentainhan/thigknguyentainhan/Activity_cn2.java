package ntu.nguyentainhan.thigknguyentainhan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_cn2 extends AppCompatActivity {
    EditText edtthang,edtnam;
    Button btnkiemtra;
    TextView hienthi;
    void TimKiem(){
        edtthang = (EditText) findViewById(R.id.edtthang);
        edtnam = (EditText) findViewById(R.id.edtnam);
        btnkiemtra = (Button) findViewById(R.id.btnkiemtra);
        hienthi = (TextView) findViewById(R.id.hienthi);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cn2);
        TimKiem();
    }
    public void Kiemtra(View view) {
        int thang = Integer.parseInt(edtthang.getText().toString());
        int nam = Integer.parseInt(edtnam.getText().toString());
        if(thang == 4 && nam == 1975){
            hienthi.setText("Đúng");
        }else{
            hienthi.setText("Sai");
        }
    }
}
