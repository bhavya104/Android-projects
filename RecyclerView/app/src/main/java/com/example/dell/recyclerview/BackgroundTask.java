package com.example.dell.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
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

import java.util.HashMap;
import java.util.Map;

public class BackgroundTask extends AsyncTask<Void, Void, Void> {

    Context context;
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    ExampleAdapter adapter;
    Activity activity;
    String ordermeapi = "https://samarth-rare-app.herokuapp.com/orders";

    public BackgroundTask(Context context) {
        this.context = context;

    }

    @Override
    protected Void doInBackground(Void... voids) {

            StringRequest stringRequest = new StringRequest(Request.Method.GET, ordermeapi, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        OrderDbHelper orderDbHelper = new OrderDbHelper(context);
                        SQLiteDatabase db = orderDbHelper.getWritableDatabase();

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
                            orderDbHelper.putInfromation(completed, brand, model, color, defects, id, db);

                        }

                        orderDbHelper.close();
                        Toast.makeText(context, "yes finallt", Toast.LENGTH_SHORT).show();
//                    adapter.notifyDataSetChanged();
//                    recyclerView.setVisibility(View.VISIBLE);
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


            requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
