package com.example.drawingapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class DrawingView extends View {

    // 경로와 페인트 설정을 함께 저장하는 클래스
    private static class PathWithPaint {
        public Path path;
        public Paint paint;

        public PathWithPaint(Path path, Paint paint) {
            this.path = path;
            this.paint = paint;
        }
    }

    private ArrayList<PathWithPaint> paths = new ArrayList<>();
    private Path currentPath;
    private Paint currentPaint;
    private Paint canvasBackground;

    private int canvasWidth = 0;
    private int canvasHeight = 0;

    public DrawingView(Context context) {
        this(context, null);
    }

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 초기화
        currentPath = new Path();
        currentPaint = new Paint();
        setupPaint();

        // 배경 설정
        canvasBackground = new Paint();
        canvasBackground.setColor(Color.WHITE);
    }

    private void setupPaint() {
        currentPaint.setColor(Color.BLACK);
        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeJoin(Paint.Join.ROUND);
        currentPaint.setStrokeCap(Paint.Cap.ROUND);
        currentPaint.setStrokeWidth(10f);
        currentPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasWidth = w;
        canvasHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 배경 그리기
        canvas.drawRect(0, 0, canvasWidth, canvasHeight, canvasBackground);

        // 저장된 모든 경로 그리기
        for (PathWithPaint pathWithPaint : paths) {
            canvas.drawPath(pathWithPaint.path, pathWithPaint.paint);
        }

        // 현재 그리고 있는 경로 그리기
        canvas.drawPath(currentPath, currentPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchStart(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                break;
        }

        // 화면 갱신
        invalidate();
        return true;
    }

    private void touchStart(float x, float y) {
        currentPath = new Path();
        currentPath.moveTo(x, y);
    }

    private void touchMove(float x, float y) {
        currentPath.lineTo(x, y);
    }

    private void touchUp() {
        // 경로와 페인트 정보를 저장
        Path newPath = new Path(currentPath);
        Paint newPaint = new Paint(currentPaint);
        paths.add(new PathWithPaint(newPath, newPaint));

        // 현재 경로 초기화
        currentPath = new Path();
    }

    // 색상 설정
    public void setColor(int color) {
        currentPaint = new Paint();
        setupPaint();
        currentPaint.setColor(color);
    }

    // 지우개 모드 설정
    public void setEraser() {
        currentPaint = new Paint();
        setupPaint();
        currentPaint.setColor(Color.WHITE);
    }

    // 선 굵기 설정
    public void setStrokeWidth(float width) {
        currentPaint = new Paint(currentPaint);
        currentPaint.setStrokeWidth(width);
    }

    // 캔버스 지우기
    public void clearCanvas() {
        paths.clear();
        currentPath = new Path();
        invalidate();
    }
}