package com.example.dell.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

// playlist adapter
public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.Bhooshan> {

    Context mContext;
    ArrayList<ExampleItemVerticle> mExampleList;

    public ExampleAdapter(Context mContext, ArrayList<ExampleItemVerticle> mExampleList) {
        this.mContext = mContext;
        this.mExampleList = mExampleList;
    }

    @NonNull
    @Override
    public Bhooshan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_product_verticle,parent,false);
        return new Bhooshan(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Bhooshan bhooshan, int position) {
        ExampleItemVerticle currentItem = mExampleList.get(position);
        bhooshan.completed.setText(currentItem.getCompleted());
        bhooshan.model.setText(currentItem.getModel());
        bhooshan.defect.setText(currentItem.getDefect());
        bhooshan.color.setText(currentItem.getColor());
        bhooshan.brand.setText(currentItem.getBrand());

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public class Bhooshan extends RecyclerView.ViewHolder{

       public TextView completed,brand,color,defect,model,id;
        public Bhooshan(@NonNull View view) {
            super(view);
            completed = view.findViewById(R.id.textView222);
            model = view.findViewById(R.id.textView333);
            brand = view.findViewById(R.id.textView555);
            color = view.findViewById(R.id.textView666);
            defect = view.findViewById(R.id.textView444);
        }
    }

}
