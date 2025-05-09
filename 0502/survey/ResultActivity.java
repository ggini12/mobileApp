package com.example.surveyapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultText = findViewById(R.id.resultText);
        int[] answers = getIntent().getIntArrayExtra("answers");

        int yesCount = 0;
        for (int ans : answers) {
            if (ans == 1) yesCount++;
        }

        resultText.setText("‘예’ 라고 답한 문항 수: " + yesCount + "개");
    }
}
