package com.example.todolistsqlite;  // 실제 패키지명으로 바꾸기

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "todo.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "tasks";
    private static final String COL_ID = "id";
    private static final String COL_TASK = "task";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // DB 최초 생성 시 실행
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TASK + " TEXT NOT NULL)";
        db.execSQL(createTable);
    }

    // DB 버전 업그레이드 시 실행
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 간단하게 테이블 삭제 후 재생성
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // 할 일 추가
    public boolean addTask(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_TASK, task);
        long result = db.insert(TABLE_NAME, null, cv);
        db.close();
        return result != -1;  // -1이면 실패
    }

    // 모든 할 일 불러오기 (리스트 반환)
    public ArrayList<String> getAllTasks() {
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                String task = cursor.getString(cursor.getColumnIndex(COL_TASK));
                taskList.add(task);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return taskList;
    }

    // 할 일 삭제 (task 이름으로 삭제)
    public boolean deleteTask(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COL_TASK + "=?", new String[]{task});
        db.close();
        return result > 0;
    }
}
