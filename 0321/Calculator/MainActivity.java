package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText eText1;
    EditText eText2;
    EditText eText3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button bPlus = (Button) findViewById(R.id.button1);
        eText1 = (EditText) findViewById(R.id.edit1);
        eText2 = (EditText) findViewById(R.id.edit2);
        eText3 = (EditText) findViewById(R.id.edit3);
    }
    public void cal_plus(View e){
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();
        int result = Integer.parseInt(s1)+Integer.parseInt(s2);
        eText3.setText(""+result);
    }
    public void cal_minus(View e){
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();
        int result = Integer.parseInt(s1)-Integer.parseInt(s2);
        eText3.setText(""+result);
    }
    public void cal_double(View e){
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();
        int result = Integer.parseInt(s1)*Integer.parseInt(s2);
        eText3.setText(""+result);
    }
    public void cal_split(View e){
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();
        int result = Integer.parseInt(s1)/Integer.parseInt(s2);
        eText3.setText(""+result);
    }
}