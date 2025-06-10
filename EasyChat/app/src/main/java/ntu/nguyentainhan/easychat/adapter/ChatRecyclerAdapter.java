package ntu.nguyentainhan.easychat.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import ntu.nguyentainhan.easychat.R;
import ntu.nguyentainhan.easychat.model.ChatMessageModel;
import ntu.nguyentainhan.easychat.utils.FirebaseUtil;

public class ChatRecyclerAdapter extends FirestoreRecyclerAdapter<ChatMessageModel, ChatRecyclerAdapter.ChatModelViewHolder> {

    Context context;
    public ChatRecyclerAdapter(FirestoreRecyclerOptions<ChatMessageModel> options, Context applicationContext) {
        super(options);
        this.context = applicationContext;
    }

    @Override
    protected void onBindViewHolder(@NonNull ChatModelViewHolder holder, int position, @NonNull ChatMessageModel model) {
        Log.i("haushd","asjd");
        if(model.getSenderId().equals(FirebaseUtil.currentUserId())){
            holder.leftChatlayout.setVisibility(View.GONE);
            holder.rightChatlayout.setVisibility(View.VISIBLE);
            holder.rightChatTextview.setText(model.getMessage());
        }else{
            holder.rightChatlayout.setVisibility(View.GONE);
            holder.leftChatlayout.setVisibility(View.VISIBLE);
            holder.leftChatTextview.setText(model.getMessage());
        }
    }

    @NonNull
    @Override
    public ChatModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_message_recylcer_row,parent,false);
        return new ChatModelViewHolder(view);
    }

    class ChatModelViewHolder extends RecyclerView.ViewHolder{
        LinearLayout leftChatlayout, rightChatlayout;
        TextView leftChatTextview, rightChatTextview;

        public ChatModelViewHolder(@NonNull View itemView) {
            super(itemView);
            leftChatlayout = itemView.findViewById(R.id.left_chat_layout);
            rightChatlayout = itemView.findViewById(R.id.right_chat_layout);
            leftChatTextview = itemView.findViewById(R.id.left_chat_textview);
            rightChatTextview = itemView.findViewById(R.id.right_chat_textview);
        }
    }
}