package com.example.timeusageapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecordActivity extends AppCompatActivity {

    private BarChart barChart;
    private TextView totalTimeText;
    private TextView mostActivityText;
    private TextView averageTimeText;
    private LinearLayout activityListContainer;
    private Button btnBack;
    private Button btnReset;

    // 활동별 이모지 매핑
    private Map<String, String> activityEmojis = new HashMap<String, String>() {{
        put("공부", "📚");
        put("게임", "🎮");
        put("운동", "🏃");
        put("기타", "⚡");
    }};

    // 활동별 색상 매핑
    private Map<String, Integer> activityColors = new HashMap<String, Integer>() {{
        put("공부", Color.parseColor("#3B82F6"));
        put("게임", Color.parseColor("#EF4444"));
        put("운동", Color.parseColor("#10B981"));
        put("기타", Color.parseColor("#F59E0B"));
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        initViews();
        setupData();
        setupBackButton();
    }

    private void initViews() {
        barChart = findViewById(R.id.barChart);
        totalTimeText = findViewById(R.id.totalTimeText);
        mostActivityText = findViewById(R.id.mostActivityText);
        averageTimeText = findViewById(R.id.averageTimeText);
        activityListContainer = findViewById(R.id.activityListContainer);
        btnBack = findViewById(R.id.btnBack);
        btnReset = findViewById(R.id.btnReset);
    }

    private void setupBackButton() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResetConfirmDialog();
            }
        });
    }

    private void setupData() {
        ArrayList<String> names = getIntent().getStringArrayListExtra("names");
        ArrayList<Long> durations = (ArrayList<Long>) getIntent().getSerializableExtra("durations");

        if (names == null || durations == null || names.size() != durations.size()) {
            showNoDataMessage();
            return;
        }

        // 같은 활동끼리 시간 합치기
        Map<String, Long> combinedData = combineActivities(names, durations);

        // Map을 다시 ArrayList로 변환
        ArrayList<String> combinedNames = new ArrayList<>(combinedData.keySet());
        ArrayList<Long> combinedDurations = new ArrayList<>();
        for (String name : combinedNames) {
            combinedDurations.add(combinedData.get(name));
        }

        setupChart(combinedNames, combinedDurations);
        setupStatistics(combinedNames, combinedDurations);
        setupActivityList(combinedNames, combinedDurations);
    }

    private Map<String, Long> combineActivities(ArrayList<String> names, ArrayList<Long> durations) {
        Map<String, Long> combinedData = new HashMap<>();

        for (int i = 0; i < names.size(); i++) {
            String activityName = names.get(i);
            Long currentDuration = durations.get(i);

            if (combinedData.containsKey(activityName)) {
                // 이미 존재하는 활동이면 시간을 더함
                combinedData.put(activityName, combinedData.get(activityName) + currentDuration);
            } else {
                // 새로운 활동이면 추가
                combinedData.put(activityName, currentDuration);
            }
        }

        return combinedData;
    }

    private void setupChart(ArrayList<String> names, ArrayList<Long> durations) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();

        for (int i = 0; i < durations.size(); i++) {
            float minutes = durations.get(i) / 60000f; // 분 단위로 변환
            entries.add(new BarEntry(i, minutes));

            // 활동별 색상 설정
            String activityName = names.get(i);
            Integer color = activityColors.get(activityName);
            colors.add(color != null ? color : Color.parseColor("#6B7280"));
        }

        BarDataSet dataSet = new BarDataSet(entries, "");
        dataSet.setColors(colors);
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(Color.parseColor("#374151"));

        // 값 포맷터 - 분 단위로 표시
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value < 1) {
                    return String.format("%.1f분", value);
                } else {
                    return String.format("%.0f분", value);
                }
            }
        });

        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.6f);

        barChart.setData(barData);

        // 차트 스타일링
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);
        barChart.setDrawGridBackground(false);
        barChart.setDrawBorders(false);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(false);
        barChart.setScaleEnabled(false);
        barChart.setPinchZoom(false);

        // X축 설정
        XAxis xAxis = barChart.getXAxis();
        ArrayList<String> xLabels = new ArrayList<>();
        for (String name : names) {
            String emoji = activityEmojis.get(name);
            xLabels.add((emoji != null ? emoji + " " : "") + name);
        }
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xLabels));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(12f);
        xAxis.setTextColor(Color.parseColor("#374151"));

        // Y축 설정
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setGridColor(Color.parseColor("#E5E7EB"));
        leftAxis.setTextSize(12f);
        leftAxis.setTextColor(Color.parseColor("#6B7280"));
        leftAxis.setAxisMinimum(0f);

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setEnabled(false);

        // 애니메이션
        barChart.animateY(1000, Easing.EaseOutCubic);

        barChart.invalidate();
    }

    private void setupStatistics(ArrayList<String> names, ArrayList<Long> durations) {
        long totalTime = 0;
        String mostActivity = "";
        long maxDuration = 0;

        // 총 시간 계산 및 가장 많이 한 활동 찾기
        for (int i = 0; i < durations.size(); i++) {
            long duration = durations.get(i);
            totalTime += duration;

            if (duration > maxDuration) {
                maxDuration = duration;
                mostActivity = names.get(i);
            }
        }

        // 총 시간 표시
        totalTimeText.setText(formatDuration(totalTime));

        // 가장 많이 한 활동 표시
        if (!mostActivity.isEmpty()) {
            String emoji = activityEmojis.get(mostActivity);
            mostActivityText.setText((emoji != null ? emoji + " " : "") + mostActivity);
        }

        // 평균 시간 계산
        if (durations.size() > 0) {
            long averageTime = totalTime / durations.size();
            averageTimeText.setText(formatDuration(averageTime));
        }
    }

    private void setupActivityList(ArrayList<String> names, ArrayList<Long> durations) {
        activityListContainer.removeAllViews();

        for (int i = 0; i < names.size(); i++) {
            View activityItem = createActivityItem(names.get(i), durations.get(i));
            activityListContainer.addView(activityItem);
        }
    }

    private View createActivityItem(String activityName, long duration) {
        LinearLayout itemLayout = new LinearLayout(this);
        itemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        itemLayout.setOrientation(LinearLayout.HORIZONTAL);
        itemLayout.setPadding(0, 16, 0, 16);

        // 활동 이름과 이모지
        TextView nameText = new TextView(this);
        String emoji = activityEmojis.get(activityName);
        nameText.setText((emoji != null ? emoji + " " : "") + activityName);
        nameText.setTextSize(16);
        nameText.setTextColor(Color.parseColor("#374151"));

        LinearLayout.LayoutParams nameParams = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        nameText.setLayoutParams(nameParams);

        // 시간 표시
        TextView durationText = new TextView(this);
        durationText.setText(formatDuration(duration));
        durationText.setTextSize(16);
        durationText.setTextColor(Color.parseColor("#6B7280"));
        durationText.setGravity(android.view.Gravity.END);

        itemLayout.addView(nameText);
        itemLayout.addView(durationText);

        return itemLayout;
    }

    private String formatDuration(long milliseconds) {
        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;

        if (hours > 0) {
            return String.format("%d시간 %d분", hours, minutes % 60);
        } else if (minutes > 0) {
            return String.format("%d분 %d초", minutes, seconds % 60);
        } else {
            return String.format("%d초", seconds);
        }
    }

    private void showNoDataMessage() {
        totalTimeText.setText("데이터 없음");
        mostActivityText.setText("-");
        averageTimeText.setText("-");

        TextView noDataText = new TextView(this);
        noDataText.setText("📝 아직 기록된 활동이 없습니다.\n활동을 시작해보세요!");
        noDataText.setTextSize(16);
        noDataText.setTextColor(Color.parseColor("#6B7280"));
        noDataText.setGravity(android.view.Gravity.CENTER);
        noDataText.setPadding(32, 64, 32, 64);

        activityListContainer.addView(noDataText);

        barChart.setVisibility(View.GONE);
    }

    private void showResetConfirmDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("🗑️ 기록 초기화");
        builder.setMessage("모든 기록을 삭제하시겠습니까?\n이 작업은 되돌릴 수 없습니다.");

        builder.setPositiveButton("초기화", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                resetAllData();
            }
        });

        builder.setNegativeButton("취소", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        android.app.AlertDialog dialog = builder.create();
        dialog.show();

        // 버튼 스타일링
        dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE)
                .setTextColor(Color.parseColor("#EF4444"));
        dialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(Color.parseColor("#6B7280"));
    }

    private void resetAllData() {
        try {
            // 메인 액티비티로 초기화 신호를 보냄
            Intent resultIntent = new Intent();
            resultIntent.putExtra("reset_data", true);
            setResult(RESULT_OK, resultIntent);

            // 성공 메시지 표시
            Toast.makeText(this, "✅ 모든 기록이 초기화되었습니다!", Toast.LENGTH_SHORT).show();

            // 잠시 딜레이 후 액티비티 종료 (토스트 메시지를 보여주기 위해)
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 1000); // 1초 딜레이

        } catch (Exception e) {
            // 오류 발생 시 처리
            Toast.makeText(this, "❌ 초기화 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    // 추가: 뒤로가기 버튼 처리 개선
    @Override
    public void onBackPressed() {
        // 기본 뒤로가기 동작
        super.onBackPressed();
    }

    // 선택사항: 액티비티 생명주기 메서드 추가로 디버깅
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 디버깅용 로그 (개발 중에만 사용)
        android.util.Log.d("RecordActivity", "Activity destroyed");
    }
}