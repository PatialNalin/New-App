package com.lotusdev.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CardView cardView = findViewById(R.id.iconBody);
        TextView textView = findViewById(R.id.icon);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent inext = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(inext);

                finish();
            }
        },3000);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.float_anim);
        cardView.startAnimation(animation);
    }
}