package com.example.dell.animationclock;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

public class StopWatchAct extends AppCompatActivity {
    Button btnStart,btnstop;
    ImageView icanchor;
    Animation roundingalone;
    Chronometer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        btnStart = (Button)findViewById(R.id.btnstart);
        icanchor = (ImageView) findViewById(R.id.bganchor);
        roundingalone = AnimationUtils.loadAnimation(this,R.anim.roundingalone);
        btnstop = (Button)findViewById(R.id.btnstop);
        timer = (Chronometer)findViewById(R.id.timer);

        btnstop.setAlpha(0);



        Typeface MlIGHT = Typeface.createFromAsset(getAssets(),"fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");
        Typeface MRegular = Typeface.createFromAsset(getAssets(),"fonts/MRegular.ttf");

        btnStart.setTypeface(MMedium);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                icanchor.startAnimation(roundingalone);
                btnstop.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnStart.animate().alpha(0).setDuration(300).start();

                timer.setBase(SystemClock.elapsedRealtime());
                timer.start();

            }

        });
    }
}
