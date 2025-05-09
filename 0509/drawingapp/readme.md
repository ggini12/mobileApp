# 🎨 그림판 앱 - SMOT 방식 분석

---

### ✅ **S (Subject / 개요)**

이 앱은 Android Studio와 Java를 사용하여 만든 그림판 애플리케이션입니다. 사용자는 화면에 그림을 그리고, 선 색상과 굵기를 조절할 수 있으며, 지우개 기능도 지원됩니다. 버튼 대신 상단의 툴바를 활용해 기능을 선택할 수 있도록 구성했습니다.

---

### ✅ **M (Material / 구성 요소)**

- **언어**: Java (Android)
- **IDE**: Android Studio
- **주요 구성 파일**:
  - `MainActivity.java`: 앱의 메인 액티비티, 툴바 메뉴를 통한 기능 처리
  - `DrawingView.java`: 커스텀 뷰로 실제 드로잉 처리
  - `activity_main.xml`: 뷰 구조 정의 (툴바 + DrawingView)
  - `draw_menu.xml`: 툴바 메뉴 항목 정의

---

### ✅ **O (Operation / 동작 방식)**

- `DrawingView`에서 터치 이벤트(`onTouchEvent`)를 받아 경로(Path)를 생성하고 캔버스에 그립니다.
- `MainActivity`에서 툴바를 생성하고, 선택된 메뉴 항목에 따라 선의 색상, 굵기, 지우개 모드를 설정합니다.
- 메뉴 항목은 `res/menu/draw_menu.xml` 파일에 정의되어 있으며, 툴바는 `Toolbar`를 통해 구현됩니다.

---

### ✅ **T (Target / 목적 및 기대 효과)**

- **목적**:
  - 터치 기반 사용자 입력 이해
  - 커스텀 뷰 구현 능력 향상
  - 툴바 및 메뉴 구성 능력 습득
- **기대 효과**:
  - 그림판처럼 직관적인 UI 앱 구현 능력 배양
  - Java 기반 Android 앱 설계 및 뷰 커스터마이징 실력 향상
  - 실제 사용자와 상호작용 가능한 인터페이스 설계 경험

---

### 📂 파일 및 구조 요약

```plaintext
/app
├── java/com/example/drawapp/
│   ├── MainActivity.java
│   └── DrawingView.java
├── res/
│   ├── layout/activity_main.xml
│   ├── menu/draw_menu.xml
│   └── values/colors.xml
