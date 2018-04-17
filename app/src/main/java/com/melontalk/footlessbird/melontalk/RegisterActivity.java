package com.melontalk.footlessbird.melontalk;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.melontalk.footlessbird.melontalk.model.UserModel;

public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText name;
    private EditText password;
    private Button register;
    private String splash_background;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        splash_background = mFirebaseRemoteConfig.getString(getString(R.string.rc_color));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor(splash_background));
        }

        email = findViewById(R.id.registerActivity_editText_email);
        name = findViewById(R.id.registerActivity_editText_name);
        password = findViewById(R.id.registerActivity_editText_password);
        register = findViewById(R.id.registerActivity_button_register);
        register.setBackgroundColor(Color.parseColor(splash_background));

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(email.getText().toString() == null || name.getText().toString() == null || password.getText().toString() == null){
                    return;
                }

                FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                UserModel userModel = new UserModel();

                                userModel.userName = name.getText().toString();

                                String userId = task.getResult().getUser().getUid();
                                FirebaseDatabase.getInstance().getReference().child("users").child(userId).setValue(userModel);
                            }
                        });
            }
        });
    }
}
