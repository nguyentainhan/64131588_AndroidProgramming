package ntu.nguyentainhan.easychat;

import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import ntu.nguyentainhan.easychat.adapter.ChatRecyclerAdapter;
import ntu.nguyentainhan.easychat.model.ChatMessageModel;
import ntu.nguyentainhan.easychat.model.ChatroomModel;
import ntu.nguyentainhan.easychat.model.UserModel;
import ntu.nguyentainhan.easychat.utils.FirebaseUtil;

public class ChatActivity extends AppCompatActivity {
    UserModel otherUser;
    String chatroomId;
    ChatroomModel chatroomModel;
    ChatRecyclerAdapter adapter;

    EditText messageInput;
    ImageButton sendMessageBtn;
    ImageButton backBtn;
    TextView otherUsername;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Gán cố định user bot
        otherUser = new UserModel(
                null,
                "EasyChat Bot",
                Timestamp.now(),
                "BOT_001"
        );


        chatroomId = "chat_with_bot_" + FirebaseUtil.currentUserId();

        messageInput = findViewById(R.id.chat_message_input);
        sendMessageBtn = findViewById(R.id.message_send_btn);
        backBtn = findViewById(R.id.back_btn);
        otherUsername = findViewById(R.id.other_username);
        recyclerView = findViewById(R.id.chat_recycler_view);

        otherUsername.setText(otherUser.getUsername());
        backBtn.setOnClickListener((v)->{
            onBackPressed();
        });

        sendMessageBtn.setOnClickListener((v -> {
            String message = messageInput.getText().toString().trim();
            if(message.isEmpty()) return;

            sendMessageToUser(message, false);
            messageInput.setText("");

            // Bot trả lời sau 1 giây
            new Handler().postDelayed(() -> {
                String botReply = generateBotReply(message);
                sendMessageToUser(botReply, true);
            }, 1000);
        }));

        getOrCreateChatroomModel();
        setupChatRecyclerView();
    }

    void getOrCreateChatroomModel(){
        FirebaseUtil.getChatroomReference(chatroomId).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                chatroomModel = task.getResult().toObject(ChatroomModel.class);
                if(chatroomModel == null){
                    chatroomModel = new ChatroomModel(
                            chatroomId,
                            Arrays.asList(FirebaseUtil.currentUserId(), otherUser.getUserId()),
                            Timestamp.now(),
                            ""
                    );
                    FirebaseUtil.getChatroomReference(chatroomId).set(chatroomModel);
                }
            }
        });
    }

    void setupChatRecyclerView(){
        Query query = FirebaseUtil.getChatroomMessageReference(chatroomId)
                .orderBy("timestamp", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<ChatMessageModel> options = new FirestoreRecyclerOptions.Builder<ChatMessageModel>()
                .setQuery(query,ChatMessageModel.class).build();

        adapter = new ChatRecyclerAdapter(options,getApplicationContext());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.startListening();
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }

    private String generateBotReply(String message) {
        message = message.toLowerCase();
        if (message.contains("xin chào") || message.contains("chào")|| message.contains("hi")|| message.contains("hello")) {
            return "Chào bạn, mình là EasyChat Bot! 🤖";
        } else if (message.contains("bạn tên gì")) {
            return "Mình tên là EasyChat Bot, rất vui được nói chuyện với bạn!";
        } else if (message.contains("giờ")) {
            return "Bây giờ là " + new SimpleDateFormat("HH:mm").format(new Date());
        }else if (message.contains("tên tôi là")) {
            return "Rất vui được biết bạn! Mình sẽ nhớ tên bạn nếu mình có trí nhớ 🤖";
        } else if (message.contains("bạn làm được gì")) {
            return "Mình có thể trò chuyện, nhắc giờ, và trả lời một số câu hỏi đơn giản!";
        } else if (message.contains("cảm ơn")) {
            return "Không có chi! 🤗";
        } else if (message.matches(".*(buồn|chán|không vui).*")) {
            return "Có chuyện gì xảy ra vậy? Bạn có thể tâm sự với mình mà!";
        }
        else if (message.contains("hôm nay là thứ mấy")) {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
            String day = sdf.format(new Date());
            return "Hôm nay là " + day;
        } else if (message.contains("tạm biệt") || message.contains("bye")) {
            return "Tạm biệt! Hẹn gặp lại.";
        }
        else {
            return "Mình chưa hiểu ý bạn 😅. Bạn có thể hỏi lại không?";
        }
    }
    private void sendMessageToUser(String message, boolean isBot) {
        chatroomModel.setLastMessageTimestamp(Timestamp.now());
        chatroomModel.setLastMessageSenderId(FirebaseUtil.currentUserId());
        chatroomModel.setLastMessage(message);
        ChatMessageModel chatMessage = new ChatMessageModel(
                message,
                isBot ? otherUser.getUserId() : FirebaseUtil.currentUserId(),
                Timestamp.now()
        );


        FirebaseUtil.getChatroomMessageReference(chatroomId)
                .add(chatMessage)
                .addOnSuccessListener(doc -> {
                    FirebaseUtil.getChatroomReference(chatroomId)
                            .update("lastMessage", message,
                                    "lastMessageSenderId", chatMessage.getSenderId(),
                                    "lastMessageTimestamp", Timestamp.now());
                });
    }
}
