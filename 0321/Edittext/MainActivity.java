package com.example.practice23;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;  // TextView를 사용하려면 임포트가 필요합니다.

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextPhoneNumber;
    private TextView textViewUserInfo;  // EditText가 아니라 TextView로 선언해야 합니다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI 요소 초기화
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        textViewUserInfo = findViewById(R.id.textViewUserInfo);  // TextView로 수정

    }

    // 버튼 클릭 시 실행되는 메서드
    public void onSignupButtonClick(View view) {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        String phoneNumber = editTextPhoneNumber.getText().toString();

        // 사용자 정보 문자열 생성
        String userInfo = "아이디: " + username + "\n패스워드: " + password + "\n전화번호: " + phoneNumber;

        // TextView에 사용자 정보 표시
        textViewUserInfo.setText(userInfo);  // textViewUserInfo로 수정
    }
}
