package com.example.drawingapp;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawingView;
    private int currentColor = Color.BLACK;
    private float currentStrokeWidth = 10f;
    private boolean isEraserActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = findViewById(R.id.drawing_view);

        // 색상 버튼 설정
        Button btnBlack = findViewById(R.id.btn_black);
        Button btnRed = findViewById(R.id.btn_red);
        Button btnBlue = findViewById(R.id.btn_blue);
        Button btnGreen = findViewById(R.id.btn_green);
        Button btnEraser = findViewById(R.id.btn_eraser);
        Button btnClear = findViewById(R.id.btn_clear);

        btnBlack.setOnClickListener(v -> {
            currentColor = Color.BLACK;
            isEraserActive = false;
            drawingView.setColor(currentColor);
        });

        btnRed.setOnClickListener(v -> {
            currentColor = Color.RED;
            isEraserActive = false;
            drawingView.setColor(currentColor);
        });

        btnBlue.setOnClickListener(v -> {
            currentColor = Color.BLUE;
            isEraserActive = false;
            drawingView.setColor(currentColor);
        });

        btnGreen.setOnClickListener(v -> {
            currentColor = Color.GREEN;
            isEraserActive = false;
            drawingView.setColor(currentColor);
        });

        // 지우개 버튼 설정
        btnEraser.setOnClickListener(v -> {
            isEraserActive = true;
            drawingView.setEraser();
        });

        // 전체 지우기 버튼 설정
        btnClear.setOnClickListener(v -> {
            drawingView.clearCanvas();
        });

        // 선 굵기 조절 SeekBar 설정
        SeekBar strokeWidthSeekBar = findViewById(R.id.stroke_width_seekbar);
        strokeWidthSeekBar.setProgress((int) currentStrokeWidth);
        strokeWidthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentStrokeWidth = progress;
                if (currentStrokeWidth < 1) currentStrokeWidth = 1;
                drawingView.setStrokeWidth(currentStrokeWidth);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // 필요하면 여기에 코드 추가
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 필요하면 여기에 코드 추가
            }
        });
    }
}