package kr.co.company.framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv1, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.view1);
        tv2 = (TextView) findViewById(R.id.view2);
        tv3 = (TextView) findViewById(R.id.view3);
    }

    public void onClick(View view) {
        tv1.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);
        tv3.setVisibility(View.INVISIBLE);
        switch (view.getId()) {
            case R.id.button1:
                tv1.setVisibility(View.VISIBLE);
                break;
            case R.id.button2:
                tv2.setVisibility(View.VISIBLE);
                break;
            case R.id.button3:
                tv3.setVisibility(View.VISIBLE);
                break;
        }
    }
}