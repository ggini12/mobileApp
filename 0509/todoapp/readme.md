# 📱 To-Do 앱 분석 (SMOT 방식)

## 🧱 Structure (구조)
- 단일 `MainActivity` 기반으로 구성된 앱
- 주요 UI 구성:
  - `EditText`: 할 일 입력
  - `Button`: 할 일 추가
  - `RecyclerView`: 할 일 목록 표시
  - 각 아이템은 체크박스, 날짜(TextView), 삭제 버튼(ImageButton)으로 구성
- 데이터 모델: `TodoItem` 클래스 (할 일 내용, 완료 여부, 생성 날짜 포함)

## 🔧 Method (방법)
- **할 일 추가**:
  - 사용자가 입력 후 버튼 클릭 시 `TodoItem` 생성
  - 생성 시 `SimpleDateFormat`과 `Asia/Seoul` 타임존을 통해 한국 시간으로 날짜 포맷
  - RecyclerView에 항목 추가 및 `notifyItemInserted()`로 갱신

- **할 일 완료 처리**:
  - 체크박스를 클릭하면 완료 상태 저장
  - 완료된 항목은 `STRIKE_THRU_TEXT_FLAG`로 취소선 적용

- **할 일 삭제**:
  - 삭제 버튼 클릭 시 해당 항목 제거
  - `notifyItemRemoved()` 및 `notifyItemRangeChanged()`로 목록 갱신

## ⚙️ Operation (동작)
- 앱 시작 시 샘플 할 일 2개 자동 추가
- 사용자 입력 → 할 일 생성 → 목록에 추가 및 날짜 표시
- 체크박스 클릭 → 완료 상태 반영 및 스타일 변경
- 삭제 버튼 클릭 → 해당 항목 삭제 및 RecyclerView 갱신

## 🛠 Tool (도구)
- 개발 환경: Android Studio
- 언어: Java
- 주요 라이브러리/컴포넌트:
  - `RecyclerView` (할 일 목록)
  - `SimpleDateFormat`, `TimeZone` (날짜 및 시간 처리)
  - `Toast` (사용자 피드백 제공)
