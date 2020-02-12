package com.example.dell.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dell.recyclerview.database.OrderDbHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    String ordermeapi = "https://samarth-rare-app.herokuapp.com/orders";

    ArrayList<ExampleItemVerticle> list;
    ExampleAdapter adapter;
    RecyclerView recyclerView;
   ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.Progress);
        list = new ArrayList<>();
//
//        list.add(new ExampleItemVerticle("true","samsung","red","a2","screen broken","1"));
//        list.add(new ExampleItemVerticle("true","samsung","red","a2","screen broken","2"));
//        list.add(new ExampleItemVerticle("true","samsung","red","a2","screen broken","3"));
//        list.add(new ExampleItemVerticle("true","samsung","red","a2","screen broken","4"));
//        list.add(new ExampleItemVerticle("true","samsung","red","a2","screen broken","5"));
//        list.add(new ExampleItemVerticle("true","samsung","red","a2","screen broken","6"));
//        list.add(new ExampleItemVerticle("true","samsung","red","a2","screen broken","7"));
//        list.add(new ExampleItemVerticle("true","samsung","red","a2","screen broken","6"));
//        list.add(new ExampleItemVerticle("true","samsung","red","a2","screen broken","7"));


//        parseData();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ExampleAdapter(RecyclerViewActivity.this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        OrderDbHelper orderDbHelper = new OrderDbHelper(this);
        SQLiteDatabase sqLiteDatabase = orderDbHelper.getReadableDatabase();

        Cursor cursor = orderDbHelper.getInformation(sqLiteDatabase);
        cursor.moveToFirst();

        do{
            list.add(new ExampleItemVerticle(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
        }while(cursor.moveToNext());
    }

    private void parseData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ordermeapi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jsonArray = new JSONArray(response);

                    int x = jsonArray.length() - 1;
                    for (int i = x; i >= 0; i--) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String completed = jsonObject.getString("completed");
                        String brand = jsonObject.getString("phoneBrand");
                        String model = jsonObject.getString("phoneModel");
                        String color = jsonObject.getString("phoneColor");
                        String defects = jsonObject.getString("phoneDefects");
                        String id = jsonObject.getString("_id");
                        list.add(new ExampleItemVerticle(completed,brand,model,color,defects));
                    }
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
//                    mShimmerViewContainer.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(RecyclerViewActivity.this);
        requestQueue.add(stringRequest);

    }
}
