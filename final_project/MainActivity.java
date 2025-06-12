package com.example.timeusageapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnStudy, btnGame, btnExercise, btnOther;
    private Button btnStart, btnStop, btnShowRecord;
    private TextView selectedActivityText;
    private Chronometer chronometer;
    private String selectedActivity = "";
    private long startTime = 0;

    private ArrayList<String> activityNames = new ArrayList<>();
    private ArrayList<Long> activityDurations = new ArrayList<>();

    // 요청 코드 상수 추가
    private static final int RECORD_ACTIVITY_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStudy = findViewById(R.id.btnStudy);
        btnGame = findViewById(R.id.btnGame);
        btnExercise = findViewById(R.id.btnExercise);
        btnOther = findViewById(R.id.btnOther);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnShowRecord = findViewById(R.id.btnShowRecord);

        selectedActivityText = findViewById(R.id.selectedActivityText);
        chronometer = findViewById(R.id.chronometer);

        View.OnClickListener activitySelectListener = v -> {
            selectedActivity = ((Button) v).getText().toString();
            selectedActivityText.setText("선택된 활동: " + selectedActivity);
            btnStart.setVisibility(View.VISIBLE);
        };

        btnStudy.setOnClickListener(activitySelectListener);
        btnGame.setOnClickListener(activitySelectListener);
        btnExercise.setOnClickListener(activitySelectListener);
        btnOther.setOnClickListener(activitySelectListener);

        btnStart.setOnClickListener(v -> {
            if (!selectedActivity.isEmpty()) {
                startTime = SystemClock.elapsedRealtime();
                chronometer.setBase(startTime);
                chronometer.start();
                btnStop.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.GONE);

                // 활동 선택 버튼들 비활성화 (타이머 실행 중에는 활동 변경 불가)
                setActivityButtonsEnabled(false);
            }
        });

        btnStop.setOnClickListener(v -> {
            chronometer.stop();
            long endTime = SystemClock.elapsedRealtime();
            long elapsedMillis = endTime - startTime;

            activityNames.add(selectedActivity);
            activityDurations.add(elapsedMillis);

            btnStop.setVisibility(View.GONE);
            selectedActivityText.setText("활동을 선택하세요");
            selectedActivity = "";

            // 활동 선택 버튼들 다시 활성화
            setActivityButtonsEnabled(true);

            // 기록 보기 버튼 활성화 (데이터가 있으므로)
            btnShowRecord.setEnabled(true);
        });

        btnShowRecord.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RecordActivity.class);
            intent.putStringArrayListExtra("names", activityNames);

            ArrayList<Long> durations = new ArrayList<>(activityDurations);
            intent.putExtra("durations", durations);

            // startActivity 대신 startActivityForResult 사용
            startActivityForResult(intent, RECORD_ACTIVITY_REQUEST_CODE);
        });

        // 초기 상태 설정
        updateUIState();
    }

    // RecordActivity에서 돌아올 때 결과 처리
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RECORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            boolean shouldReset = data.getBooleanExtra("reset_data", false);

            if (shouldReset) {
                // 모든 데이터 초기화
                resetAllData();
            }
        }
    }

    // 모든 데이터 초기화 메서드
    private void resetAllData() {
        // 활동 목록 초기화
        activityNames.clear();
        activityDurations.clear();

        // 현재 실행 중인 타이머가 있다면 중지
        if (chronometer != null) {
            chronometer.stop();
            chronometer.setBase(SystemClock.elapsedRealtime());
        }

        // UI 상태 초기화
        selectedActivity = "";
        selectedActivityText.setText("활동을 선택하세요");
        btnStart.setVisibility(View.GONE);
        btnStop.setVisibility(View.GONE);

        // 버튼 상태 업데이트
        setActivityButtonsEnabled(true);
        updateUIState();
    }

    // 활동 선택 버튼들의 활성화/비활성화 상태 설정
    private void setActivityButtonsEnabled(boolean enabled) {
        btnStudy.setEnabled(enabled);
        btnGame.setEnabled(enabled);
        btnExercise.setEnabled(enabled);
        btnOther.setEnabled(enabled);
    }

    // UI 상태 업데이트
    private void updateUIState() {
        // 기록이 있는지 확인하여 기록 보기 버튼 활성화/비활성화
        btnShowRecord.setEnabled(!activityNames.isEmpty());
    }

    // 뒤로가기 버튼 처리 (타이머 실행 중일 때 확인)
    @Override
    public void onBackPressed() {
        if (btnStop.getVisibility() == View.VISIBLE) {
            // 타이머가 실행 중인 경우 확인 다이얼로그 표시
            new android.app.AlertDialog.Builder(this)
                    .setTitle("⏰ 타이머 실행 중")
                    .setMessage("타이머가 실행 중입니다. 앱을 종료하시겠습니까?\n현재 기록은 저장되지 않습니다.")
                    .setPositiveButton("종료", (dialog, which) -> {
                        chronometer.stop();
                        super.onBackPressed();
                    })
                    .setNegativeButton("취소", (dialog, which) -> dialog.dismiss())
                    .show();
        } else {
            super.onBackPressed();
        }
    }
}