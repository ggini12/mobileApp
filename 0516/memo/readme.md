# 📱 Android Studio 메모장 앱 만들기 (내부 저장소 저장용)

## ✅ 목표

- Android 앱에서 사용자가 입력한 내용을 내부 저장소에 저장하는 **간단한 메모장 앱**을 만든다.
- **불러오기 없이 저장만** 한다.
- 내부 저장소 경로: `/data/data/패키지명/files/my_note.txt`

---

## 🧰 1. 개발 환경

- Android Studio (언어: Java)
- 최소 SDK: API 21

---

## 🧱 2. 프로젝트 생성

1. Android Studio 실행 → **New Project**
2. 템플릿: **Empty Activity**
3. 이름: `SimpleNoteApp`
4. Language: `Java`
5. Minimum SDK: `API 21`

---

## 🖼️ 3. 디자인 - `activity_main.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:padding="20dp"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#121212">

    <TextView
        android:text="메모를 입력하세요"
        android:textColor="#FFFFFF"
        android:textSize="18sp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <EditText
        android:id="@+id/editTextNote"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:gravity="top|start"
        android:hint="여기에 메모를 작성하세요"
        android:textColor="#FFFFFF"
        android:background="#1E1E1E"
        android:textColorHint="#888888"
        android:padding="12dp"
        android:textSize="16sp"
        android:inputType="textMultiLine"
        android:lines="10" />

    <Button
        android:id="@+id/buttonSave"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="저장하기"
        android:background="#03DAC5"
        android:textColor="#000000"
        android:layout_marginTop="16dp" />
</LinearLayout>
