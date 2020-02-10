package com.example.dell.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {


    ArrayList<ExampleItemVerticle> list;
    ExampleAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();

        list.add(new ExampleItemVerticle("true","samsung","red","a2","screen broken","1"));
        list.add(new ExampleItemVerticle("true","samsung","red","a2","screen broken","2"));
        list.add(new ExampleItemVerticle("true","samsung","red","a2","screen broken","3"));
        list.add(new ExampleItemVerticle("true","samsung","red","a2","screen broken","4"));
        list.add(new ExampleItemVerticle("true","samsung","red","a2","screen broken","5"));
        list.add(new ExampleItemVerticle("true","samsung","red","a2","screen broken","6"));
        list.add(new ExampleItemVerticle("true","samsung","red","a2","screen broken","7"));
        list.add(new ExampleItemVerticle("true","samsung","red","a2","screen broken","6"));
        list.add(new ExampleItemVerticle("true","samsung","red","a2","screen broken","7"));

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ExampleAdapter(RecyclerViewActivity.this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }
}
