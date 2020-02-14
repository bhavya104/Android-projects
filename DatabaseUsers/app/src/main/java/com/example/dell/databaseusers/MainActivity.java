package com.example.dell.databaseusers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.databaseusers.database.UserDbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button save = findViewById(R.id.save);
        final EditText name = findViewById(R.id.name);
        final EditText lastname = findViewById(R.id.lastname);
        final EditText age = findViewById(R.id.age);

        Button view = findViewById(R.id.view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,text.class);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDbHelper userDbHelper = new UserDbHelper(MainActivity.this);
                SQLiteDatabase sqLiteDatabase = userDbHelper.getWritableDatabase();
                userDbHelper.putInformation(name.getText().toString(),lastname.getText().toString(),age.getText().toString(),sqLiteDatabase);
                Toast.makeText(MainActivity.this, "saved", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
