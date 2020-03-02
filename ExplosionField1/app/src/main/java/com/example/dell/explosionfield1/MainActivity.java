package com.example.dell.explosionfield1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import tyrantgit.explosionfield.ExplosionField;

public class MainActivity extends AppCompatActivity {

    Button btn_myicon ;
    ExplosionField explosionField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_myicon = (Button) findViewById(R.id.Btn_explode);
        explosionField = ExplosionField.attach2Window(this);

        btn_myicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explosionField.explode(btn_myicon);
            }
        });

    }
}
