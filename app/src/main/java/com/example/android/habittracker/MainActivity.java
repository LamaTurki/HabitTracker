package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDBHelper;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    HabitDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new HabitDBHelper(this);
        insertDummyData();
        String dataString = "";
        Cursor cursor = readData();
        try {
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COL_HABIT_NAME);
            int startDateColumnIndex = cursor.getColumnIndex(HabitEntry.COL_HABIT_START_DATE);
            int countColumnIndex = cursor.getColumnIndex(HabitEntry.COL_HABIT_COUNT);
            while (cursor.moveToNext()) {
                dataString += cursor.getString(nameColumnIndex) + " - " + cursor.getString(startDateColumnIndex)
                        + " - " + cursor.getString(countColumnIndex);
            }
        } finally {
            cursor.close();
        }
        Log.i(TAG, dataString);
    }

    public void insertDummyData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COL_HABIT_NAME, "Drink Water");
        values.put(HabitEntry.COL_HABIT_START_DATE, "01-09-2017");
        values.put(HabitEntry.COL_HABIT_COUNT, 8);
        long rowId = db.insert(HabitEntry.TABLE_NAME, null, values);
        if (rowId == -1)
            Log.i(TAG, "Error inserting dummy data");
        else
            Log.i(TAG, "Dummy data inserted, rowId:" + rowId);
    }

    public Cursor readData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = new String[]{
                HabitEntry.COL_HABIT_NAME,
                HabitEntry.COL_HABIT_START_DATE,
                HabitEntry.COL_HABIT_COUNT
        };
        return db.query(HabitEntry.TABLE_NAME, projection, null, null, null, null, null);
    }

}
