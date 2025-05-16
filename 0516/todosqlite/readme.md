# 📱 Android Studio SQLite ToDoList 앱 만들기

## ✅ 목표
- SQLite 데이터베이스를 사용해서 간단한 할 일 목록(ToDoList) 앱 만들기
- 할 일 추가, 조회, 삭제 기능 구현

---

## 🧰 1. 개발 환경
- Android Studio (Java)
- 최소 SDK: API 21 이상

---

## 🧱 2. 프로젝트 생성

1. Android Studio 실행 → **New Project**
2. 템플릿: **Empty Activity**
3. 프로젝트 이름: `SQLiteToDoList`
4. Language: `Java`
5. Minimum SDK: `API 21`

---

## 🔧 3. SQLite 헬퍼 클래스 생성 (`DBHelper.java`)

```java
package com.example.sqlitetodolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "todo.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 테이블 생성 SQL
        String sql = "CREATE TABLE tasks (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "task TEXT NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 버전 변경 시 테이블 삭제 후 재생성
        db.execSQL("DROP TABLE IF EXISTS tasks");
        onCreate(db);
    }
}
