package ntu.nguyentainhan.easychat.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.Timestamp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//import ntu.nguyentainhan.easychat.ChatActivity;
import ntu.nguyentainhan.easychat.ChatActivity;
import ntu.nguyentainhan.easychat.R;
import ntu.nguyentainhan.easychat.model.ChatroomModel;
import ntu.nguyentainhan.easychat.model.UserModel;
import ntu.nguyentainhan.easychat.utils.AndroidUtil;
import ntu.nguyentainhan.easychat.utils.FirebaseUtil;

public class SearchUserRecyclerAdapter extends FirestoreRecyclerAdapter<UserModel, SearchUserRecyclerAdapter.UserModelViewHolder> {

    Context context;

    public SearchUserRecyclerAdapter(FirestoreRecyclerOptions<UserModel> options, Context applicationContext) {
        super(options);
        this.context = applicationContext;
    }

    @Override
    protected void onBindViewHolder(@NonNull UserModelViewHolder holder, int position, @NonNull UserModel model) {
        holder.usernameText.setText(model.getUsername());
        holder.phoneText.setText(model.getPhone());

        if (model.getUserId().equals(FirebaseUtil.currentUserId())) {
            holder.usernameText.setText(model.getUsername() + " (Me)");
        }

        holder.itemView.setOnClickListener(v -> {
            List<String> userIds = Arrays.asList(FirebaseUtil.currentUserId(), model.getUserId());
            Collections.sort(userIds);
            String chatroomId = userIds.get(0) + "_" + userIds.get(1);

            FirebaseUtil.getChatroomReference(chatroomId).get().addOnSuccessListener(documentSnapshot -> {
                if (!documentSnapshot.exists()) {
                    ChatroomModel chatroom = new ChatroomModel();
                    chatroom.setChatroomId(chatroomId);
                    chatroom.setUserIds(userIds);
                    chatroom.setLastMessage("");
                    chatroom.setLastMessageSenderId("");
                    chatroom.setLastMessageTimestamp(Timestamp.now());

                    FirebaseUtil.getChatroomReference(chatroomId).set(chatroom);
                }

                Intent intent = new Intent(context, ChatActivity.class);
                AndroidUtil.passUserModelAsIntent(intent, model);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            });
        });
    }

    @NonNull
    @Override
    public UserModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_user_recycler_row, parent, false);
        return new UserModelViewHolder(view);
    }

    class UserModelViewHolder extends RecyclerView.ViewHolder {
        TextView usernameText;
        TextView phoneText;

        public UserModelViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameText = itemView.findViewById(R.id.user_name_text);
            phoneText = itemView.findViewById(R.id.phone_text);
        }
    }
}

