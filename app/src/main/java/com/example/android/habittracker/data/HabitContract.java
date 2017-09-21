package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by lama on 9/21/2017 AD.
 */

public final class HabitContract {

    private HabitContract() {
    }

    public static class HabitEntry implements BaseColumns {
        public static final String TABLE_NAME = "habits";
        public static final String COL_HABIT_NAME = "name";
        public static final String COL_HABIT_START_DATE = "start_date";
        public static final String COL_HABIT_COUNT = "count";

    }
}
