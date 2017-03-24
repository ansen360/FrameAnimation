package com.ansen.frameanimation.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ansen.frameanimation.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickOne(View v) {
        startActivity(new Intent(this, AnimationOne.class));
    }

    public void clickTwo(View v) {
        startActivity(new Intent(this, AnimationTwo.class));
    }
}
