package com.example.henry.emfso_attendence.db;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;

/**
 * Created by henry on 2017-10-28.
 */

public class AttendenceDataSource {

  private static final String TAG = AttendenceDataSource.class.getSimpleName();
  private SQLiteDatabase database;
  private DBOpenHelper dbHelper;

  public AttendenceDataSource(Context context) {
    this.dbHelper = new DBOpenHelper(context);
  }

  public void open() {
    this.database = dbHelper.getWritableDatabase();

    Log.d(TAG, "database is opened");
  }

  public void close() {
    dbHelper.close();

    Log.d(TAG, "Database is closed");
  }

}
