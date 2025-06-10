package ntu.nguyentainhan.easychat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import ntu.nguyentainhan.easychat.fragmennt.ChatFragment;
import ntu.nguyentainhan.easychat.fragmennt.ProfileFragment;
import ntu.nguyentainhan.easychat.utils.FirebaseUtil;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageButton searchButton;

    ChatFragment chatFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Kiểm tra nếu người dùng chưa đăng nhập
        if (FirebaseUtil.currentUser() == null) {
            Intent intent = new Intent(this, LoginPhoneNumberActivity.class);
            startActivity(intent);
            finish(); // Kết thúc MainActivity để tránh quay lại
            return;
        }

        setContentView(R.layout.activity_main);

        chatFragment = new ChatFragment();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        searchButton = findViewById(R.id.main_search_btn);

        searchButton.setOnClickListener((v) -> {
            startActivity(new Intent(MainActivity.this, SearchUserActivity.class));
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_chat) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_frame_layout, chatFragment)
                            .commit();
                } else if (item.getItemId() == R.id.menu_profile) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_frame_layout, new ProfileFragment())
                            .commit();
                }
                return true;
            }
        });
    }
}