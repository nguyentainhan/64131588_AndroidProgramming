package ntu.nguyentainhan.qlgiaohang_64131588;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ntu.nguyentainhan.qlgiaohang_64131588.FoodPanel.FoodDDHFragment;
import ntu.nguyentainhan.qlgiaohang_64131588.FoodPanel.FoodDDXLFragment;
import ntu.nguyentainhan.qlgiaohang_64131588.FoodPanel.FoodHomeFragment;
import ntu.nguyentainhan.qlgiaohang_64131588.FoodPanel.FoodProfileFragment;



public class FoodPanel_BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_panel_bottom_navigation);
        BottomNavigationView navigationView = findViewById(R.id.food_bottom_navigation);
        navigationView.setSelectedItemId(R.id.Home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        int id = item.getItemId();
        if (id == R.id.Home) {
            fragment = new FoodHomeFragment();
        } else if (id == R.id.DonXuLi) {
            fragment = new FoodDDXLFragment();
        } else if (id == R.id.Orders) {
            fragment = new FoodDDHFragment();
        } else if (id == R.id.Profile) {
            fragment = new FoodProfileFragment();
        }
        return loadfragment(fragment);
    }

    private boolean loadfragment(Fragment fragment) {

        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();
            return true;
        }
        return false;
    }
}