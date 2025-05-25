package ntu.nguyentainhan.easy_chat_64131588.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import ntu.nguyentainhan.easy_chat_64131588.ChatActivity;
import ntu.nguyentainhan.easy_chat_64131588.R;
import ntu.nguyentainhan.easy_chat_64131588.model.ChatroomModel;
import ntu.nguyentainhan.easy_chat_64131588.model.UserModel;
import ntu.nguyentainhan.easy_chat_64131588.util.AndroidUtil;
import ntu.nguyentainhan.easy_chat_64131588.util.FirebaseUtil;

public class RecentChatRecyclerAdapter extends FirestoreRecyclerAdapter<ChatroomModel, RecentChatRecyclerAdapter.ChatroomModelViewHolder> {

    Context context;

    public RecentChatRecyclerAdapter(@NonNull FirestoreRecyclerOptions<ChatroomModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ChatroomModelViewHolder holder, int position, @NonNull ChatroomModel model) {
        FirebaseUtil.getOtherUserFromChatroom(model.getUserIds())
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult().exists()) {
                        UserModel otherUserModel = task.getResult().toObject(UserModel.class);

                        if (otherUserModel == null) return; // Tránh lỗi null

                        boolean lastMessageSentByMe = model.getLastMessageSenderId() != null
                                && model.getLastMessageSenderId().equals(FirebaseUtil.currentUserId());
                        holder.usernameText.setText(otherUserModel.getUsername());

                        if (lastMessageSentByMe)
                            holder.lastMessageText.setText("Bạn: " + model.getLastMessage());
                        else
                            holder.lastMessageText.setText(model.getLastMessage());

                        holder.lastMessageTime.setText(FirebaseUtil.timestampToString(model.getLastMessageTimestamp()));

                        holder.itemView.setOnClickListener(v -> {
                            Intent intent = new Intent(context, ChatActivity.class);
                            AndroidUtil.passUserModelAsIntent(intent, otherUserModel);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("chatroomId", model.getChatroomId());
                            context.startActivity(intent);
                        });
                    } else {
                    }
                });
    }

    @NonNull
    @Override
    public ChatroomModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recent_chat_recycler_row,parent,false);
        return new ChatroomModelViewHolder(view);
    }

    class ChatroomModelViewHolder extends RecyclerView.ViewHolder{
        TextView usernameText;
        TextView lastMessageText;
        TextView lastMessageTime;
        public ChatroomModelViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameText = itemView.findViewById(R.id.user_name_text);
            lastMessageText = itemView.findViewById(R.id.last_message_text);
            lastMessageTime = itemView.findViewById(R.id.last_message_time_text);
        }
    }
}