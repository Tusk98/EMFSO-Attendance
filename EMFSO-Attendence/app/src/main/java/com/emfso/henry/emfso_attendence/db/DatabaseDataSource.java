package com.emfso.henry.emfso_attendence.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;

import com.emfso.henry.emfso_attendence.database_content.AttendenceEntry;
import com.emfso.henry.emfso_attendence.database_content.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henry on 2017-10-28.
 */

public class DatabaseDataSource {
  private static DatabaseDataSource dbDataSource;

  private static final String TAG = DatabaseDataSource.class.getSimpleName();
  private SQLiteDatabase database;
  private DBOpenHelper dbHelper;

  private DatabaseDataSource(Context context) {
    this.dbHelper = new DBOpenHelper(context);
  }

  public static DatabaseDataSource getDataSourceInstance(Context context) {
    if (dbDataSource == null) {
      dbDataSource = new DatabaseDataSource(context);
    }

    return dbDataSource;
  }

  public void open() {
    this.database = dbHelper.getWritableDatabase();
    Log.d(TAG, "database is opened");
  }

  public void close() {
    dbHelper.close();
    Log.d(TAG, "Database is closed");
  }

  public long newFlyer(User newUser) {
    ContentValues values = new ContentValues();
    values.put(DatabaseContract.MembershipList.FIRST_NAME, newUser.getFirstName());
    values.put(DatabaseContract.MembershipList.LAST_NAME, newUser.getLastName());
    values.put(DatabaseContract.MembershipList.FIXED_WING, newUser.isFixed_wing());
    values.put(DatabaseContract.MembershipList.ROTARY_WING, newUser.isRotary_wing());
    values.put(DatabaseContract.MembershipList.OUTDOOR, newUser.isOutdoor());
    values.put(DatabaseContract.MembershipList.JUNIOR, newUser.isJunior());

    long rowId = database.insert(DatabaseContract.MembershipList.MEMBERSHIP_TABLE_NAME,
            null, values);
    Log.d(TAG, rowId + " id for new user");
    return rowId;
  }

  public long newAttendence(AttendenceEntry atten) {
    ContentValues values = new ContentValues();
    values.put(DatabaseContract.EventRecorder.FLYER_NUMBER, atten.getFlyer_num());
    values.put(DatabaseContract.EventRecorder.EVENT, atten.getEvent());
    values.put(DatabaseContract.EventRecorder.UPPER_START, atten.getUpperStart());
    values.put(DatabaseContract.EventRecorder.UPPER_END, atten.getUpperEnd());
    values.put(DatabaseContract.EventRecorder.LOWER_START, atten.getLowerStart());
    values.put(DatabaseContract.EventRecorder.LOWER_END, atten.getLowerEnd());
    values.put(DatabaseContract.EventRecorder.SPECTATORS, atten.getSpectators());

    long rowId = database.insert(DatabaseContract.EventRecorder.EVENT_TABLE_NAME,
            null, values);
    Log.d(TAG, rowId + " id for new attendance record");
    return rowId;
  }

  public List<String> getFlyerIdentification() {
    List<String> result = new ArrayList<>();
    String query = "SELECT * FROM " + DatabaseContract.MembershipList.MEMBERSHIP_TABLE_NAME;

    Cursor cursor = database.rawQuery(query, null);
    cursor.moveToFirst();

    do {
      String personId = "# ";
      personId += cursor.getString(cursor.getColumnIndex(DatabaseContract.MembershipList.FLYER_NUMBER));
      personId += " - " + cursor.getString(cursor.getColumnIndex(DatabaseContract.MembershipList.FIRST_NAME));
      personId += " " + cursor.getString(cursor.getColumnIndex(DatabaseContract.MembershipList.LAST_NAME));
      result.add(personId);
    }while (cursor.moveToNext());

    return result;
  }

  public List<String> getAttendanceRecord() {
    List<String> result = new ArrayList<>();
    String query = "SELECT * FROM " + DatabaseContract.EventRecorder.EVENT_TABLE_NAME;

    Cursor cursor = database.rawQuery(query, null);
    cursor.moveToFirst();

    do {
      String record = "# ";
      record += cursor.getString(cursor.getColumnIndex(DatabaseContract.EventRecorder.ATTENDENCE_ID));
      record += "\n Flyer Number: " +
              cursor.getString(cursor.getColumnIndex(DatabaseContract.EventRecorder.FLYER_NUMBER));
      record += "\n Event: " +
              cursor.getString(cursor.getColumnIndex(DatabaseContract.EventRecorder.EVENT));

      result.add(record);
    } while (cursor.moveToNext());
    return result;
  }

}
