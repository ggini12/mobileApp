package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CountryTipsActivity extends AppCompatActivity {

    private void setTips(String country) {
        TextView title = findViewById(R.id.countryTitle);
        TextView tip1 = findViewById(R.id.tip1);
        TextView tip2 = findViewById(R.id.tip2);
        TextView tip3 = findViewById(R.id.tip3);

        switch (country) {
            case "USA":
                title.setText("🇺🇸 미국 여행 팁");
                tip1.setText("1. 팁 문화가 있으니 레스토랑에서는 15~20%를 줘야 해요.");
                tip2.setText("2. 전압은 110V, 한국 제품 사용 시 변압기 필요할 수도 있어요.");
                tip3.setText("3. 치안이 안 좋은 지역은 미리 확인하세요.");
                break;

            case "Japan":
                title.setText("🇯🇵 일본 여행 팁");
                tip1.setText("1. 대중교통 시간표가 매우 정확해요.");
                tip2.setText("2. 식당에서 팁은 필요 없어요.");
                tip3.setText("3. 공공장소에서 통화는 자제하는 게 좋아요.");
                break;

            case "France":
                title.setText("🇫🇷 프랑스 여행 팁");
                tip1.setText("1. 소매치기 조심! 가방은 앞으로 메세요.");
                tip2.setText("2. 식사 시간 외에는 식당이 닫는 경우가 많아요.");
                tip3.setText("3. 간단한 프랑스어 인사말은 외워두면 좋아요.");
                break;

            case "Italy":
                title.setText("🇮🇹 이탈리아 여행 팁");
                tip1.setText("1. 식당 서비스는 느릴 수 있으니 여유롭게 즐기세요.");
                tip2.setText("2. 관광지 주변에서는 물가가 높아요.");
                tip3.setText("3. 길거리 공연자들도 종종 팁을 요구해요.");
                break;

            case "Thailand":
                title.setText("🇹🇭 태국 여행 팁");
                tip1.setText("1. 사원 방문 시 복장은 단정하게 입으세요.");
                tip2.setText("2. 음식은 대부분 맵고 향신료가 강할 수 있어요.");
                tip3.setText("3. 택시는 미터기 켜달라고 꼭 말하세요.");
                break;

            case "UK":
                title.setText("🇬🇧 영국 여행 팁");
                tip1.setText("1. 차는 왼쪽으로 다녀요. 길 건널 때 주의하세요.");
                tip2.setText("2. 우산은 필수! 날씨가 자주 변해요.");
                tip3.setText("3. 'Queue' 문화가 중요해요. 줄은 꼭 서야 해요.");
                break;

            case "Vietnam":
                title.setText("🇻🇳 베트남 여행 팁");
                tip1.setText("1. 오토바이 교통량이 많으니 조심하세요.");
                tip2.setText("2. 길거리 음식은 깨끗한 곳에서 드세요.");
                tip3.setText("3. 동은 흥정이 기본이에요!");
                break;

            case "Australia":
                title.setText("🇦🇺 호주 여행 팁");
                tip1.setText("1. 자외선이 강하니 선크림 필수!");
                tip2.setText("2. 와이파이가 느릴 수 있어요. 유심 챙기세요.");
                tip3.setText("3. 자연보호구역에선 쓰레기 절대 버리면 안돼요.");
                break;

            default:
                title.setText("여행 팁");
                tip1.setText("1. 나라를 선택해주세요.");
                tip2.setText("");
                tip3.setText("");
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_tips);

        String country = getIntent().getStringExtra("country");
        setTips(country);

        Button goToMannersBtn = findViewById(R.id.goToMannersBtn);
        goToMannersBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CountryTipsActivity.this, LocalMannersActivity.class);
            intent.putExtra("country", country);
            startActivity(intent);
        });
    }
}
