package ntu.nguyentainhan.easy_chat_64131588;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

//        if(getIntent().getExtras()!=null){
//            //from notification
//            String userId = getIntent().getExtras().getString("userId");
//            FirebaseUtil.allUserCollectionReference().document(userId).get()
//                    .addOnCompleteListener(task -> {
//                        if(task.isSuccessful()){
//                            UserModel model = task.getResult().toObject(UserModel.class);
//
//                            Intent mainIntent = new Intent(this,MainActivity.class);
//                            mainIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                            startActivity(mainIntent);
//
//                            Intent intent = new Intent(this, ChatActivity.class);
//                            AndroidUtil.passUserModelAsIntent(intent,model);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(intent);
//                            finish();
//                        }
//                    });
//
//
//        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(LogoActivity.this,LoginPhoneNumber_Activity.class));
                }
//                    if(FirebaseUtil.isLoggedIn()){
//                        startActivity(new Intent(LogoActivity.this,MainActivity.class));
//                    }else{
//                        startActivity(new Intent(LogoActivity.this,LoginPhoneNumber_Activity.class));
//                    }
//                    finish();
//                }

        },1000);
//        }

    }
}