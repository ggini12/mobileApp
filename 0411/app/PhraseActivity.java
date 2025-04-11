package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PhraseActivity extends AppCompatActivity {

    private String getPhrases(String country) {
        switch (country) {
            case "Japan":
                return "1. 안녕하세요 - こんにちは\n2. 감사합니다 - ありがとう\n3. 화장실 어디예요? - トイレはどこですか？";
            case "France":
                return "1. 안녕하세요 - Bonjour\n2. 감사합니다 - Merci\n3. 얼마인가요? - Combien ça coûte ?";
            case "Thailand":
                return "1. 안녕하세요 - สวัสดี\n2. 감사합니다 - ขอบคุณ\n3. 화장실 어디예요? - ห้องน้ำอยู่ที่ไหน?";
            case "USA":
                return "1. 안녕하세요 - Hello\n2. 감사합니다 - Thank you\n3. 어디로 가야 하나요? - Where should I go?";
            case "Italy":
                return "1. 안녕하세요 - Ciao\n2. 감사합니다 - Grazie\n3. 얼마예요? - Quanto costa?";
            case "UK":
                return "1. 안녕하세요 - Hello\n2. 감사합니다 - Thank you\n3. 이거 어디서 사요? - Where can I buy this?";
            case "Vietnam":
                return "1. 안녕하세요 - Xin chào\n2. 감사합니다 - Cảm ơn\n3. 얼마예요? - Bao nhiêu tiền?";
            case "Australia":
                return "1. 안녕하세요 - G’day\n2. 감사합니다 - Cheers\n3. 화장실 어디예요? - Where’s the toilet?";
            default:
                return "기본 회화 표현이 없습니다.";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        String country = getIntent().getStringExtra("country");

        TextView title = findViewById(R.id.phraseTitle);
        TextView content = findViewById(R.id.phrasesText);
        Button backToHomeBtn = findViewById(R.id.backToHomeBtn);

        title.setText(country + " 기본 회화 표현");
        content.setText(getPhrases(country));

        // 버튼 클릭 시 처음 화면으로 이동
        backToHomeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(PhraseActivity.this, CountrySelectActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }
}
