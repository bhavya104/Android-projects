package com.example.dell.animationclock;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {
    TextView tvsplash,tvsubsplash;
    Button btnget;
    Animation atg,btgone,btgtwo;
    ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvsplash = (TextView)findViewById(R.id.tvSplash);
        tvsubsplash = (TextView) findViewById(R.id.tvSubSplash);
        btnget = (Button) findViewById(R.id.btnget);


        ivSplash = (ImageView)findViewById(R.id.ivSplash);


        atg = AnimationUtils.loadAnimation(this,R.anim.atg);
        btgone = AnimationUtils.loadAnimation(this,R.anim.btgone);
        btgtwo = AnimationUtils.loadAnimation(this,R.anim.btgtwo);

        tvsplash.startAnimation(btgone);
        tvsubsplash.startAnimation(btgone);
        btnget.startAnimation(btgtwo);
        ivSplash.startAnimation(atg);

        Typeface MlIGHT = Typeface.createFromAsset(getAssets(),"fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");
        Typeface MRegular = Typeface.createFromAsset(getAssets(),"fonts/MRegular.ttf");

        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StopWatchAct.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        tvsplash.setTypeface(MRegular);
        tvsubsplash.setTypeface(MlIGHT);
        btnget.setTypeface(MMedium);

    }
}
