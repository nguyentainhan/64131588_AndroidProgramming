package ntu.nguyentainhan.thigknguyentainhan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Activity_cn3 extends AppCompatActivity {
    ListView LvBaiHat;
    ArrayList<String> dsBaiHat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cn3);
        LvBaiHat = (ListView) findViewById(R.id.listbaihat);
        dsBaiHat = new ArrayList<>();
        dsBaiHat.add("Giải phóng Miền nam");
        dsBaiHat.add("Tiến về Sài Gòn");
        dsBaiHat.add("Đất nước trọn niềm vui");
        dsBaiHat.add("Bài ca thống nhất");
        dsBaiHat.add("Mùa xuân trên thành phố Hồ Chí Minh");
        dsBaiHat.add("Hát mãi khúc quân hành");
        dsBaiHat.add("Lá cờ Đảng");
        dsBaiHat.add("Cô gái Sài Gòn đi tải đạn");
        dsBaiHat.add("Bước chân trên dải Trường Sơn");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsBaiHat);
        LvBaiHat.setAdapter(adapter);
        LvBaiHat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) { // Nếu chọn bài hát đầu tiên
                    // Khởi động activityBaiHat
                    Intent intent = new Intent(Activity_cn3.this, Activity_Baihat.class );
                    intent.putExtra("baihat", dsBaiHat.get(0)); // Truyền tên bài hát
                    startActivity(intent);
                } else {
                    String baihat = dsBaiHat.get(position);
                    Toast.makeText(Activity_cn3.this, baihat, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}