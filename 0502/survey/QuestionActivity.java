package com.example.surveyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

    String[] questions = {
            "1. 게임을 자주 하시나요?",
            "2. 하루에 게임 몇 시간 하시나요?",
            "3. 어떤 장르를 선호하시나요?",
            "4. 친구들과 함께 하시나요?",
            "5. 스트레스 해소에 도움이 되나요?",
            "6. 게임에 과금을 하나요?",
            "7. 게임할 때 기분이 좋은가요?",
            "8. 게임 외 다른 취미가 있나요?",
            "9. 게임으로 얻은 교훈이 있나요?",
            "10. 앞으로도 게임을 할 건가요?"
    };

    int currentQuestion = 0;
    int[] answers = new int[10]; // 0~2값 저장 (예: 1=예, 2=아니오)

    TextView questionText;
    RadioGroup optionsGroup;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionText = findViewById(R.id.questionText);
        optionsGroup = findViewById(R.id.optionsGroup);
        nextBtn = findViewById(R.id.nextBtn);

        loadQuestion();

        nextBtn.setOnClickListener(v -> {
            int selectedId = optionsGroup.getCheckedRadioButtonId();
            if (selectedId == -1) return; // 선택 안함

            if (selectedId == R.id.option1) answers[currentQuestion] = 1;
            else if (selectedId == R.id.option2) answers[currentQuestion] = 2;

            currentQuestion++;

            if (currentQuestion < questions.length) {
                loadQuestion();
            } else {
                Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
                intent.putExtra("answers", answers);
                startActivity(intent);
                finish();
            }
        });
    }

    void loadQuestion() {
        questionText.setText(questions[currentQuestion]);
        optionsGroup.clearCheck();
    }
}
