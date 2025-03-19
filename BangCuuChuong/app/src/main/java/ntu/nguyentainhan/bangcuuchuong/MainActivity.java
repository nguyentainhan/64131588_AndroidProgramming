package ntu.nguyentainhan.bangcuuchuong;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    Button btnKiemTra, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    TextView tvSoA, tvSoB, tvKetQua, tvDapAn;
    void TimDieuKhien(){
        btnKiemTra = (Button) findViewById(R.id.btnKiemTra);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        tvSoA = (TextView) findViewById(R.id.tvSoA);
        tvSoB = (TextView) findViewById(R.id.tvSoB);
        tvKetQua = (TextView) findViewById(R.id.tvKetQua);
        tvDapAn = (TextView) findViewById(R.id.tvDapAn);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TimDieuKhien();
        //Sinh so ngau nhien
        int a = (int)(Math.random()*5);
        int b = (int)(Math.random()*5);
        int kqDung = (a+b);

        tvSoA.setText(String.valueOf(a));
        tvSoB.setText(String.valueOf(b));

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDapAn.setText("1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDapAn.setText("2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDapAn.setText("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDapAn.setText("4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDapAn.setText(("5"));
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDapAn.setText("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDapAn.setText("7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDapAn.setText("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDapAn.setText("9");
            }
        });

        btnKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lấy dữ liệu từ tvDapAn về
                String strDapAn = tvDapAn.getText().toString();

                if(strDapAn.isEmpty()){
                    tvKetQua.setText("Bạn chưa chọn đáp án!");
                    return;
                }
                int dapan = Integer.parseInt(strDapAn);

                if(dapan == kqDung){
                    tvKetQua.setText("Kết quả đúng!");
                }
                else tvKetQua.setText("Kết quả sai!");
            }
        });
    }

}