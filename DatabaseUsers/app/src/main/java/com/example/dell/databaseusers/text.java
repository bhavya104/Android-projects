package com.example.dell.databaseusers;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dell.databaseusers.database.UserDbHelper;

import java.util.ArrayList;

public class text extends AppCompatActivity {

    ArrayList<User> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        TextView name = findViewById(R.id.namedisplay);
        TextView lastname = findViewById(R.id.lastnameDisplay);
        TextView age = findViewById(R.id.Agedisplay);


        UserDbHelper userDbHelper = new UserDbHelper(text.this);
        SQLiteDatabase sqLiteDatabase = userDbHelper.getReadableDatabase();

        StringBuilder builder = new StringBuilder();

//        DBManager dbManager = new DBManager(this).open();
//        User user = dbManager.fetchUser();
//
//        name.setText(user.getName());
//        lastname.setText(user.getLastname());
//        age.setText(user.getAge());
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Bhooshan",new String[]{});


        if(cursor != null){
            cursor.moveToFirst();
        }

        do{
            String name1 = cursor.getString(0);
            String lst = cursor.getString(1);
            String ag = cursor.getString(2);
            builder.append("Name = " + name1 + "\n" + "lastname = " + lst + "\n" + "age = " + ag+"\n"+"\n");
        }while(cursor.moveToNext());

        name.setText(builder.toString());

    }
}
