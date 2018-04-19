package com.melontalk.footlessbird.melontalk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.melontalk.footlessbird.melontalk.fragment.PeopleFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().replace(R.id.mainActivity_frameLayout, new PeopleFragment()).commit();

    }
}
