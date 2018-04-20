package com.melontalk.footlessbird.melontalk.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.melontalk.footlessbird.melontalk.R;
import com.melontalk.footlessbird.melontalk.model.ChatModel;

public class MessageActivity extends AppCompatActivity {

    private String destinationUid;
    private Button button;
    private EditText editText;

    private String uId;
    private String chatRoomUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        destinationUid = getIntent().getStringExtra("destinationUid");
        button = findViewById(R.id.messageActivity_button);
        editText = findViewById(R.id.messageActivity_editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatModel chatModel = new ChatModel();
                chatModel.users.put(uId, true);
                chatModel.users.put(destinationUid, true);

                if (chatRoomUid == null) {
                    FirebaseDatabase.getInstance().getReference().child("chatRooms").push().setValue(chatModel);

                }else{
                    ChatModel.Comment comment = new ChatModel.Comment();
                    comment.uId = uId;
                    comment.message = editText.getText().toString();
                    FirebaseDatabase.getInstance().getReference().child("chatRooms").child("comments").push().setValue(comment);
                }
            }
        });
        checkChatRoom();
    }

    void checkChatRoom() {
        FirebaseDatabase.getInstance().getReference().child("chatRooms").orderByChild("users/" + uId).equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    ChatModel chatModel = item.getValue(ChatModel.class);
                    if (chatModel.users.containsKey(destinationUid)) {
                        chatRoomUid = item.getKey();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
