package ntu.nguyentainhan.easy_chat_64131588;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//import ntu.nguyentainhan.easy_chat_64131588.model.ChatMessageModel;
import ntu.nguyentainhan.easy_chat_64131588.adapter.ChatRecyclerAdapter;
import ntu.nguyentainhan.easy_chat_64131588.model.ChatMessageModel;
import ntu.nguyentainhan.easy_chat_64131588.model.ChatroomModel;
import ntu.nguyentainhan.easy_chat_64131588.model.UserModel;
import ntu.nguyentainhan.easy_chat_64131588.util.AndroidUtil;
import ntu.nguyentainhan.easy_chat_64131588.util.FirebaseUtil;

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
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Gán cố định user bot
        otherUser = new UserModel(
                null,                          // phone (bot không có số)
                "EasyChat Bot",               // username
                Timestamp.now(),             // createdTimestamp
                "BOT_001",                   // userId
                null,                        // fcmToken
                "https://example.com/bot_avatar.png"  // profileImage
        );


        chatroomId = "chat_with_bot_" + FirebaseUtil.currentUserId();

        messageInput = findViewById(R.id.chat_message_input);
        sendMessageBtn = findViewById(R.id.message_send_btn);
        backBtn = findViewById(R.id.back_btn);
        otherUsername = findViewById(R.id.other_username);
        recyclerView = findViewById(R.id.chat_recycler_view);
        imageView = findViewById(R.id.profile_pic_image_view);

        otherUsername.setText(otherUser.getUsername());

        // Không cần load avatar bot, nhưng có thể thêm ảnh mặc định nếu muốn
        // AndroidUtil.setProfilePic(this, R.drawable.bot_avatar, imageView);

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

    private String generateBotReply(String message) {
        message = message.toLowerCase();
        if (message.contains("xin chào") || message.contains("chào")) {
            return "Chào bạn, mình là EasyChat Bot! 🤖";
        } else if (message.contains("bạn tên gì")) {
            return "Mình tên là EasyChat Bot, rất vui được nói chuyện với bạn!";
        } else if (message.contains("giờ")) {
            return "Bây giờ là " + new SimpleDateFormat("HH:mm").format(new Date());
        } else {
            return "Mình chưa hiểu ý bạn 😅. Bạn có thể hỏi lại không?";
        }
    }
}
