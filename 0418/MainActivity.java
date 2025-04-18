package com.example.egg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    String[] reqestPermission={ "android.permission.POST_NOTIFICATIONS" };
    public static int REQUEST_P0STNOTIFICATIONS = 10023;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.edit);

        // 초기 타이머 값을 10초로 설정 (초 단위 추가)
        mEditText.setText("10초");

        createNotificationChannel();
        ActivityCompat.requestPermissions(this, reqestPermission,
                1000);
    }

    String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Channel description");
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void sendNotification() {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        // 알림이 클릭되면 이 인텐트가 보내진다.
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.google.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_IMMUTABLE);

        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Egg Timer")
                .setContentText("계란 삶기가 완료되었습니다.")
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
    }

    public void startTimer(View view) {
        // 10초로 고정
        int totalSeconds = 10;

        new CountDownTimer(totalSeconds * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                long secondsLeft = millisUntilFinished / 1000;
                // 초 단위 추가해서 표시
                mEditText.setText(secondsLeft + "초");
            }

            public void onFinish() {
                mEditText.setText("완료!");
                sendNotification();
                showAddTimeDialog();  // 10초가 끝난 후 대화상자 띄우기
            }
        }.start();
    }

    // 10초가 끝난 후 10초를 추가할지 묻는 알림 대화상자
    private void showAddTimeDialog() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("계란 삶기 완료")
                .setMessage("10초를 더 추가하시겠습니까?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startTimer(null);  // 10초를 더 추가
                    }
                })
                .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 타이머 종료
                    }
                })
                .show();
    }
}