package com.melontalk.footlessbird.melontalk;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.melontalk.footlessbird.melontalk.fragment.ChatFragment;
import com.melontalk.footlessbird.melontalk.fragment.PeopleFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.mainActivity_bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_people:

                        getFragmentManager().beginTransaction().replace(R.id.mainActivity_frameLayout, new PeopleFragment()).commit();
                        return true;
                    case R.id.action_chat:

                        getFragmentManager().beginTransaction().replace(R.id.mainActivity_frameLayout, new ChatFragment()).commit();
                        return true;

                }
                return false;
            }
        });

    }
}
