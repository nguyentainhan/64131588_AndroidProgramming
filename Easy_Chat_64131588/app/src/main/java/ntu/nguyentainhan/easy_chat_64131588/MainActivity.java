package ntu.nguyentainhan.easy_chat_64131588;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import ntu.nguyentainhan.easy_chat_64131588.fragment.ChatFragment;
import ntu.nguyentainhan.easy_chat_64131588.fragment.ProfileFragment;
import ntu.nguyentainhan.easy_chat_64131588.util.FirebaseUtil;

public class MainActivity extends AppCompatActivity {
        BottomNavigationView bottomNavigationView;
        ImageButton searchButton;

        ChatFragment chatFragment;
        ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Kiểm tra nếu người dùng chưa đăng nhập
        if (FirebaseUtil.currentUser() == null) {
            Intent intent = new Intent(this, LoginPhoneNumber_Activity.class);
            startActivity(intent);
            finish(); // Kết thúc MainActivity để tránh quay lại
            return;
        }

        setContentView(R.layout.activity_main);

            chatFragment = new ChatFragment();
            profileFragment = new ProfileFragment();

            bottomNavigationView = findViewById(R.id.bottom_navigation);
            searchButton = findViewById(R.id.main_search_btn);

            searchButton.setOnClickListener((v)->{
                startActivity(new Intent(MainActivity.this,SearchUserActivity.class));
            });

            bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if(item.getItemId()==R.id.menu_chat){
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,chatFragment).commit();
                    }
                    if(item.getItemId()==R.id.menu_profile){
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,profileFragment).commit();
                    }
                    return true;
                }
            });
            bottomNavigationView.setSelectedItemId(R.id.menu_chat);

//            getFCMToken();

        }

//        void getFCMToken(){
//            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
//                if(task.isSuccessful()){
//                    String token = task.getResult();
//                    FirebaseUtil.currentUserDetails().update("fcmToken",token);
//
//                }
//            });
//        }
    }