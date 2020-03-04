package com.example.dell.bmiapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class activity_two extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
    }

    public void calcBMI(View view){
        double hieght = 0;
        double weight = 0;
        double BMI = 0;

        String message = "";

        EditText et1 = (EditText) findViewById(R.id.editText);
        EditText et2 = (EditText) findViewById(R.id.editText2);

        Button button = (Button) findViewById(R.id.button2);

        TextView t1 = (TextView) findViewById(R.id.textViewOne);
        TextView t2 = (TextView) findViewById(R.id.textViewTwo);

        weight = Double.parseDouble(et1.getText().toString());
        hieght = Double.parseDouble(et2.getText().toString());

        BMI = hieght*hieght;
        BMI = weight/BMI;

        t1.setText(String.valueOf(String.format("%.2f",BMI)));

        if(BMI < 18.5){
            message = "Under Weight";
        }else if(BMI >18.5 && BMI<25){
            message = "Normal";
        }else{
            message = "Over Weight";
        }

        t2.setText(message);
    }
}
