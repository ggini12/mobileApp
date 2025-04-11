package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LocalMannersActivity extends AppCompatActivity {

    private String getManners(String country) {
        switch (country) {
            case "USA":
                return "1. 대화 중 눈 마주치기 중요.\n2. 프라이버시 존중.\n3. 작은 대화(small talk) 자주 사용.";
            case "Japan":
                return "1. 공공장소에서 조용히.\n2. 인사 시 머리 숙이기.\n3. 신발 벗고 실내 출입.";
            case "France":
                return "1. 인사할 때 볼에 키스.\n2. 식사 예절 중요.\n3. 시간 약속을 잘 지키기.";
            case "Italy":
                return "1. 식사 중 손짓 표현이 많음.\n2. 친근한 인사 문화.\n3. 느긋한 일정 이해 필요.";
            case "Thailand":
                return "1. 와이(합장 인사)를 사용.\n2. 머리는 신성하게 여김.\n3. 공공장소에서 애정표현 자제.";
            case "UK":
                return "1. 'Please', 'Sorry' 자주 사용.\n2. 줄 서기 문화 중요.\n3. 직접적 표현보다는 완곡한 말 사용.";
            case "Vietnam":
                return "1. 윗사람에 대한 예의 중요.\n2. 식사 중 젓가락 사용 주의.\n3. 음성 톤 낮게 유지.";
            case "Australia":
                return "1. 격식을 차리기보단 자유로운 대화.\n2. BBQ 문화 보편적.\n3. 자연·동물 존중.";
            default:
                return "정보 없음.";
        }
    }

    private String getEmojiCountryTitle(String country) {
        switch (country) {
            case "USA": return "🇺🇸 미국의 로컬 문화 & 매너";
            case "Japan": return "🇯🇵 일본의 로컬 문화 & 매너";
            case "France": return "🇫🇷 프랑스의 로컬 문화 & 매너";
            case "Italy": return "🇮🇹 이탈리아의 로컬 문화 & 매너";
            case "Thailand": return "🇹🇭 태국의 로컬 문화 & 매너";
            case "UK": return "🇬🇧 영국의 로컬 문화 & 매너";
            case "Vietnam": return "🇻🇳 베트남의 로컬 문화 & 매너";
            case "Australia": return "🇦🇺 호주의 로컬 문화 & 매너";
            default: return "로컬 문화 & 매너";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_manners);

        String country = getIntent().getStringExtra("country");

        TextView title = findViewById(R.id.textCountryTitle);
        TextView content = findViewById(R.id.textManners);
        Button goToPhrasesBtn = findViewById(R.id.goToPhrasesBtn);

        title.setText(getEmojiCountryTitle(country));
        content.setText(getManners(country));

        goToPhrasesBtn.setOnClickListener(v -> {
            Intent intent = new Intent(LocalMannersActivity.this, PhraseActivity.class);
            intent.putExtra("country", country);
            startActivity(intent);
        });

    }
}
