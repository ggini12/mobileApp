package com.example.drawingapp; // ← 본인의 패키지 이름으로 변경하세요!

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DrawingView 연결
        drawingView = findViewById(R.id.drawingView);

        // 툴바 설정
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 툴바 메뉴 등록
        getMenuInflater().inflate(R.menu.draw_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // 색상 설정
        if (id == R.id.color_red) {
            drawingView.setColor(Color.RED);
        } else if (id == R.id.color_blue) {
            drawingView.setColor(Color.BLUE);
        } else if (id == R.id.color_black) {
            drawingView.setColor(Color.BLACK);

            // 선 굵기 설정
        } else if (id == R.id.thin) {
            drawingView.setStrokeWidth(5f);
        } else if (id == R.id.medium) {
            drawingView.setStrokeWidth(10f);
        } else if (id == R.id.thick) {
            drawingView.setStrokeWidth(20f);

            // 지우개 모드 설정
        } else if (id == R.id.eraser) {
            drawingView.setEraserMode();
        } else {
            return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
