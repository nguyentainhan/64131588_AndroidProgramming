package ntu.nguyentainhan.thigknguyentainhan;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Baihat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baihat);

        String baihat = getIntent().getStringExtra("baihat");
        Log.d("Activity_Baihat", "baihat: " + baihat);

        TextView tvTenBaiHat = findViewById(R.id.txtSong);
        tvTenBaiHat.setText(baihat);

        TextView tvLoiBaiHat = findViewById(R.id.txtSong);
        if (tvLoiBaiHat != null) {
            tvLoiBaiHat.setText("Giải phóng miền Nam, chúng ta cùng quyết tiến bước.\n" +
                    "Diệt Đế quốc Mỹ, phá tan bè lũ bán nước.\n" +
                    "Ôi xương tan máu rơi, long hân thù ngất trời.\n" +
                    "Sông núi bao nhiêu năm cắt rời.\n" +
                    "Đây Cửu Long hùng tráng, Đây Trường Sơn vinh quang.\n" +
                    "Thúc giục đoàn ta xung phong đi giết thù.\n" +
                    "Vai sát vai chung một bóng cờ.\n" +
                    "Vùng lên! Nhân dân miền Nam anh hùng!\n" +
                    "Vùng lên! Xông pha vượt qua bão bùng.\n" +
                    "Thề cứu lấy nước nhà! Thề hy sinh đến cùng!\n" +
                    "Cầm gươm, ôm sung, xông tới!\n" +
                    "Vận nước đã đên rồi. Bình minh chiếu khắp nơi.\n" +
                    "Nguyện xây non nước sáng tươi muôn đời.");
        } else {
            Log.e("Activity_Baihat", "tvLoiBaiHat is null");
        }
    }
}