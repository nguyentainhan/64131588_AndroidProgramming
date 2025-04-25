package ntu.nguyentainhan.vd22;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    bottomNav = findViewById(R.id.bot_nav);
    bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int mnuItemDuocChonID = item.getItemId();
            if(mnuItemDuocChonID == R.id.mnu_home) {
                Toast.makeText(MainActivity.this, "Thay HOME", Toast.LENGTH_LONG).show();
            }
            else if (mnuItemDuocChonID == R.id.mnu_Search) {
                Toast.makeText(MainActivity.this, "Thay SEARCH", Toast.LENGTH_LONG).show();
            }
            else if (mnuItemDuocChonID == R.id.mnu_profile) {
                Toast.makeText(MainActivity.this, "Thay PROFILE", Toast.LENGTH_LONG).show();
            }
            else return false;

            return true;
        }
    });
    }
}