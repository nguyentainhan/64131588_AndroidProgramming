package ntu.nguyentainhan.easychat.fragmennt;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import ntu.nguyentainhan.easychat.LogoActivity;
import ntu.nguyentainhan.easychat.R;
import ntu.nguyentainhan.easychat.model.UserModel;
import ntu.nguyentainhan.easychat.utils.AndroidUtil;
import ntu.nguyentainhan.easychat.utils.FirebaseUtil;

public class ProfileFragment extends Fragment {

    ImageView profilePic;
    EditText usernameInput;
    EditText phoneInput;
    EditText subjectInput;
    EditText teacherInput;
    EditText majorInput;
    Button updateProfileBtn;
    ProgressBar progressBar;
    TextView logoutBtn;
    UserModel currentUserModel;

    public ProfileFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        profilePic = view.findViewById(R.id.profile_image_view);
        usernameInput = view.findViewById(R.id.profile_username);
        phoneInput = view.findViewById(R.id.profile_phone);
        updateProfileBtn = view.findViewById(R.id.profle_update_btn);
        progressBar = view.findViewById(R.id.profile_progress_bar);
        logoutBtn = view.findViewById(R.id.logout_btn);
        subjectInput = view.findViewById(R.id.profile_subject);
        teacherInput = view.findViewById(R.id.profile_teacher);
        majorInput = view.findViewById(R.id.profile_major);


        getUserData();

        updateProfileBtn.setOnClickListener((v -> {
            updateBtnClick();
        }));
        logoutBtn.setOnClickListener((v)->{
            FirebaseUtil.logout();
            Intent intent = new Intent(getContext(), LogoActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        return view;
    }

    void updateBtnClick(){
        String major = majorInput.getText().toString();
        String subject = subjectInput.getText().toString();
        String teacher = teacherInput.getText().toString();
        currentUserModel.setMajor(major);
        currentUserModel.setSubject(subject);
        currentUserModel.setTeacher(teacher);

        String newUsername = usernameInput.getText().toString();
        if(newUsername.isEmpty() || newUsername.length()<3){
            usernameInput.setError("Tên người dùng phải có ít nhất 3 kí tự!");
            return;
        }
        currentUserModel.setUsername(newUsername);
        setInProgress(true);
        updateToFirestore();
    }

    void updateToFirestore(){
        FirebaseUtil.currentUserDetails().set(currentUserModel)
                .addOnCompleteListener(task -> {
                    setInProgress(false);
                    if(task.isSuccessful()){
                        AndroidUtil.showToast(getContext(),"Cập nhật thành công");
                    }else{
                        AndroidUtil.showToast(getContext(),"Cập nhật thất bại");
                    }
                });
    }



    void getUserData(){
        setInProgress(true);
        FirebaseUtil.currentUserDetails().get().addOnCompleteListener(task -> {
            setInProgress(false);
            currentUserModel = task.getResult().toObject(UserModel.class);
            usernameInput.setText(currentUserModel.getUsername());
            phoneInput.setText(currentUserModel.getPhone());
            subjectInput.setText(currentUserModel.getSubject());
            teacherInput.setText(currentUserModel.getTeacher());
            majorInput.setText(currentUserModel.getMajor());

        });
    }


    void setInProgress(boolean inProgress){
        if(inProgress){
            progressBar.setVisibility(View.VISIBLE);
            updateProfileBtn.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            updateProfileBtn.setVisibility(View.VISIBLE);
        }
    }
}