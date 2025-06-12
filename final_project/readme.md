# 📱 시간관리 앱 (Time Usage App)

Android 시간 추적 및 활동 관리 애플리케이션
## 📋 개요

이 앱은 사용자의 일상 활동을 추적하고 분석할 수 있는 Android 애플리케이션입니다. 공부, 게임, 운동, 기타 활동에 소요된 시간을 측정하고 시각적으로 분석할 수 있습니다.

## ✨ 주요 기능

### 🎯 활동 선택 및 시간 측정
- **4가지 활동 카테고리**: 📚 공부, 🎮 게임, 🏃 운동, ⚡ 기타
- **실시간 타이머**: Chronometer를 활용한 정확한 시간 측정
- **직관적인 UI**: 활동 선택부터 측정까지 원터치로 간편하게
  
![image](https://github.com/user-attachments/assets/41d3e64b-eaab-48c2-b660-e00bab2fc446)

### 📊 통계 및 기록 관리
- **활동별 시간 집계**: 같은 활동의 여러 세션을 자동으로 합산
- **시각적 차트**: MPAndroidChart를 활용한 막대 그래프
- **상세 통계**: 총 시간, 가장 많이 한 활동, 평균 세션 시간
- **활동별 색상 구분**: 각 활동마다 고유한 색상으로 표시

![image](https://github.com/user-attachments/assets/e0fc4982-668a-4259-b991-9ce43b8cb4d6)


### 🗑️ 데이터 관리
- **기록 초기화**: 모든 활동 기록을 안전하게 삭제
- **확인 다이얼로그**: 실수로 인한 데이터 손실 방지
- **즉시 반영**: 초기화 후 메인 화면 자동 업데이트

![image](https://github.com/user-attachments/assets/28c2c0ca-28b1-4024-8248-bde07f08dd44)


## 🏗️ 기술 스택

### Android 개발
- **언어**: Java
- **최소 SDK**: API 21 (Android 5.0)
- **개발 환경**: Android Studio

### 주요 라이브러리
- **MPAndroidChart**: 차트 및 그래프 생성
- **CardView**: 카드형 UI 디자인
- **Material Design**: 모던한 UI/UX

### UI 컴포넌트
- `Chronometer`: 실시간 타이머
- `BarChart`: 활동별 시간 분포 차트
- `AlertDialog`: 사용자 확인 다이얼로그
- `Toast`: 피드백 메시지

## 📁 프로젝트 구조

```
app/src/main/java/com/example/timeusageapp/
├── MainActivity.java          # 메인 화면 - 활동 선택 및 타이머
├── RecordActivity.java        # 기록 화면 - 통계 및 차트
└── res/
    ├── layout/
    │   ├── activity_main.xml      # 메인 화면 레이아웃
    │   └── activity_record.xml    # 기록 화면 레이아웃
    ├── values/
    │   ├── colors.xml            # 색상 정의
    │   └── strings.xml           # 문자열 리소스
    └── drawable/
        ├── circle_button_background.xml
        └── reset_button_background.xml
```


## 🔧 설치 및 실행

### 요구사항
- Android Studio 4.0 이상
- Android SDK API 21 이상
- Gradle 6.0 이상

### 설치 과정
1. **저장소 클론**
   ```bash
   git clone https://github.com/yourusername/time-usage-app.git
   cd time-usage-app
   ```

2. **Android Studio에서 프로젝트 열기**
   - Android Studio 실행
   - "Open an existing Android Studio project" 선택
   - 프로젝트 폴더 선택

3. **의존성 설치**
   ```gradle
   dependencies {
       implementation 'com.github.PhilJay:MPAndroidChart:v3.1.0'
       implementation 'androidx.cardview:cardview:1.0.0'
       // 기타 의존성들...
   }
   ```

4. **빌드 및 실행**
   - `Build > Make Project`
   - 디바이스 연결 또는 에뮬레이터 실행
   - `Run > Run 'app'`

## 🚀 사용 방법

### 1. 활동 시작하기
1. 메인 화면에서 원하는 활동 버튼 선택
2. "시작" 버튼을 눌러 타이머 시작
3. 활동 완료 후 "중지" 버튼으로 기록 저장

![image](https://github.com/user-attachments/assets/87167a7f-2c8c-44af-a360-ca3dba66227c)

### 2. 기록 확인하기
1. "📊 기록 보기" 버튼 클릭
2. 활동별 시간 및 통계 확인
3. 막대 그래프로 시간 분포 시각화

![image](https://github.com/user-attachments/assets/be322428-a8f9-4728-ab60-4e537ef6a8ea)

### 3. 데이터 관리하기
1. 기록 화면에서 "🗑️ 기록 초기화" 버튼 클릭
2. 확인 다이얼로그에서 "초기화" 선택
3. 모든 기록이 삭제되고 메인 화면으로 복귀

![image](https://github.com/user-attachments/assets/0b19dbc5-e424-42d2-ae8e-46b1816bc40d)

## 📊 주요 기능 상세

### 타이머 시스템
- **정확한 시간 측정**: `SystemClock.elapsedRealtime()` 사용
- **실시간 표시**: Chronometer를 통한 시각적 피드백
- **중단 방지**: 타이머 실행 중 활동 변경 불가

### 데이터 처리
- **자동 집계**: 동일 활동의 여러 세션 자동 합산
- **메모리 관리**: ArrayList를 활용한 효율적인 데이터 저장
- **데이터 전달**: Intent를 통한 액티비티 간 데이터 공유

### 차트 시각화
```java
// 막대 그래프 설정 예시
BarDataSet dataSet = new BarDataSet(entries, "");
dataSet.setColors(colors);
dataSet.setValueTextSize(12f);
barChart.setData(new BarData(dataSet));
```

## 🔒 보안 및 안정성

### 데이터 보호
- **로컬 저장**: 모든 데이터는 앱 내부에서만 관리
- **확인 절차**: 중요한 작업 전 사용자 확인 필수
- **예외 처리**: try-catch를 통한 안정적인 에러 핸들링

### 사용자 경험
- **직관적인 피드백**: Toast 메시지로 작업 결과 알림
- **실행 취소 방지**: 타이머 실행 중 앱 종료 시 확인 다이얼로그
- **일관된 UI**: 모든 화면에서 통일된 디자인 언어

## 🔮 향후 개발 계획

### 추가 예정 기능
- [ ] **데이터 영속성**: SQLite 또는 Room을 활용한 데이터베이스 구현
- [ ] **일별/주별 통계**: 기간별 활동 분석 기능
- [ ] **목표 설정**: 활동별 목표 시간 설정 및 달성률 표시
- [ ] **알림 기능**: 활동 리마인더 및 목표 달성 알림
- [ ] **데이터 내보내기**: CSV, PDF 형태의 리포트 생성
- [ ] **다크 테마**: 야간 모드 지원

### 성능 개선
- [ ] **메모리 최적화**: 대용량 데이터 처리 개선
- [ ] **UI 반응성**: 비동기 처리를 통한 부드러운 사용자 경험
- [ ] **배터리 최적화**: 백그라운드 처리 최소화
