package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lama on 9/21/2017 AD.
 */

public class HabitDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "habit_track.db";
    private static final int DATABASE_VERSION = 1;


    public HabitDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableSQL = "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + " ( " +
                HabitContract.HabitEntry.COL_HABIT_NAME + " TEXT NOT NULL," +
                HabitContract.HabitEntry.COL_HABIT_START_DATE + " TEXT NOT NULL," +
                HabitContract.HabitEntry.COL_HABIT_COUNT + " INTEGER NOT NULL DEFAULT 0 );";
        sqLiteDatabase.execSQL(createTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HabitContract.HabitEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
