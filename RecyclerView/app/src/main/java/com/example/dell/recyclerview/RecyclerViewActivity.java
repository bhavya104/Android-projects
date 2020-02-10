package com.example.dell.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ExampleAdapter exampleAdapter;
    ArrayList<ExampleItemVerticle> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        list = new ArrayList<>();
        list.add(new ExampleItemVerticle("false","samsung","red","j2","screenBroken","1"));
        list.add(new ExampleItemVerticle("true","realme one","black","realme one","Broken","2"));
        list.add(new ExampleItemVerticle("false","samsung","red","j2","screenBroken","1"));
        list.add(new ExampleItemVerticle("true","realme one","black","realme one","Broken","2"));
        list.add(new ExampleItemVerticle("false","samsung","red","j2","screenBroken","1"));
        list.add(new ExampleItemVerticle("true","realme one","black","realme one","Broken","2"));
        list.add(new ExampleItemVerticle("false","samsung","red","j2","screenBroken","1"));
        list.add(new ExampleItemVerticle("true","realme one","black","realme one","Broken","2"));
        list.add(new ExampleItemVerticle("false","samsung","red","j2","screenBroken","1"));
        list.add(new ExampleItemVerticle("true","realme one","black","realme one","Broken","2"));
        list.add(new ExampleItemVerticle("false","samsung","red","j2","screenBroken","1"));
        list.add(new ExampleItemVerticle("true","realme one","black","realme one","Broken","2"));

        exampleAdapter = new ExampleAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(exampleAdapter);

    }
}
