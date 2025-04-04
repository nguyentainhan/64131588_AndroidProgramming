package ntu.nguyentainhan.thigknguyentainhan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btncn2,btncn3,btncn4,btnaboutme;
    void TimKiem(){
        btncn2 = (Button) findViewById(R.id.btncn2);
        btncn3 = (Button) findViewById(R.id.btncn3);
        btncn4 = (Button) findViewById(R.id.btncn4);
        btnaboutme = (Button) findViewById(R.id.btnaboutme);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TimKiem();
        btncn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Cau2 = new Intent(MainActivity.this, Activity_cn2.class);
                startActivity(Cau2);
            }
        });
        btncn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Cau3 = new Intent(MainActivity.this, Activity_cn3.class);
                startActivity(Cau3);
            }
        });
        btncn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Cau4 = new Intent(MainActivity.this, Activity_cn4.class);
                startActivity(Cau4);
            }
        });
        btnaboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Profile = new Intent(MainActivity.this, Activity_Aboutme.class);
                startActivity(Profile);
            }
        });
    }
}