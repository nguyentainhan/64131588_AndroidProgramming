package ntu.nguyentainhan.qlgiaohang_64131588.FoodPanel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ntu.nguyentainhan.qlgiaohang_64131588.R;

public class FoodHomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_food_home,null);
        getActivity().setTitle("Home");
        return v;
    }
}