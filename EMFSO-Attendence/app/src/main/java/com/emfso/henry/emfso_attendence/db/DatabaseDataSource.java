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
    Log.d(DatabaseDataSource.class.getSimpleName(), "DATABASE RETRIEVED ----------------");
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

  public boolean checkOpen() {
    if (database != null)
      return database.isOpen();
    else
      return false;
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

  public void updateFlyerInfo(User user, int flyerId) {
    ContentValues values = new ContentValues();
    values.put(DatabaseContract.MembershipList.FIRST_NAME, user.getFirstName());
    values.put(DatabaseContract.MembershipList.LAST_NAME, user.getLastName());
    values.put(DatabaseContract.MembershipList.FIXED_WING, user.isFixed_wing());
    values.put(DatabaseContract.MembershipList.ROTARY_WING, user.isRotary_wing());
    values.put(DatabaseContract.MembershipList.OUTDOOR, user.isOutdoor());
    values.put(DatabaseContract.MembershipList.JUNIOR, user.isJunior());

    String selection = DatabaseContract.MembershipList.FLYER_NUMBER + " = ?";
    String[] selectionArgs = {String.valueOf(flyerId)};
    database.update(DatabaseContract.MembershipList.MEMBERSHIP_TABLE_NAME, values, selection, selectionArgs);
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
    if (cursor.getCount() != 0) {
      do {
        String personId = "# ";
        personId += cursor.getString(cursor.getColumnIndex(DatabaseContract.MembershipList.FLYER_NUMBER));
        personId += " - " + cursor.getString(cursor.getColumnIndex(DatabaseContract.MembershipList.FIRST_NAME));
        personId += " " + cursor.getString(cursor.getColumnIndex(DatabaseContract.MembershipList.LAST_NAME));
        result.add(personId);
      } while (cursor.moveToNext());
    }
    return result;
  }

  public List<String> getAttendanceRecord() {
    List<String> result = new ArrayList<>();
    String query = "SELECT * FROM " + DatabaseContract.EventRecorder.EVENT_TABLE_NAME;

    Cursor cursor = database.rawQuery(query, null);
    cursor.moveToFirst();

    if (cursor.getCount() != 0) {
      do {
        String record = "# ";
        record += cursor.getString(cursor.getColumnIndex(DatabaseContract.EventRecorder.ATTENDENCE_ID));
        record += "\n Flyer Number: " +
                cursor.getString(cursor.getColumnIndex(DatabaseContract.EventRecorder.FLYER_NUMBER));
        record += "\n Event: " +
                cursor.getString(cursor.getColumnIndex(DatabaseContract.EventRecorder.EVENT));

        result.add(record);
      } while (cursor.moveToNext());
    }
    return result;
  }

  public User getSpecificUser(int flyerId) {
    String first_name, last_name, cursor_outdoor, cursor_fixed, cursor_rotary, cursor_junior;
    boolean fixed, rotary, junior, outdoor;

    //Retrieve row of data
    String query = "SELECT * FROM " + DatabaseContract.MembershipList.MEMBERSHIP_TABLE_NAME +
            " WHERE " + DatabaseContract.MembershipList.FLYER_NUMBER + " = " + flyerId;
    Cursor cursor = database.rawQuery(query, null);
    cursor.moveToFirst();
    first_name = cursor.getString(cursor.getColumnIndex(DatabaseContract.MembershipList.FIRST_NAME));
    last_name = cursor.getString(cursor.getColumnIndex(DatabaseContract.MembershipList.LAST_NAME));
    cursor_fixed = cursor.getString(cursor.getColumnIndex(DatabaseContract.MembershipList.FIXED_WING));
    cursor_rotary = cursor.getString(cursor.getColumnIndex(DatabaseContract.MembershipList.ROTARY_WING));
    cursor_junior = cursor.getString(cursor.getColumnIndex(DatabaseContract.MembershipList.JUNIOR));
    cursor_outdoor = cursor.getString(cursor.getColumnIndex(DatabaseContract.MembershipList.OUTDOOR));

    //Convert to boolean value using private method
    fixed = stringToBoolean(cursor_fixed);
    rotary = stringToBoolean(cursor_rotary);
    junior = stringToBoolean(cursor_junior);
    outdoor = stringToBoolean(cursor_outdoor);
    User result = new User(first_name, last_name, outdoor, fixed, rotary, junior);

    return result;
  }

  public void updateAttendance(AttendenceEntry atten, int attendanceId) {
    ContentValues values = new ContentValues();
    values.put(DatabaseContract.EventRecorder.FLYER_NUMBER, atten.getFlyer_num());
    values.put(DatabaseContract.EventRecorder.EVENT, atten.getEvent());

  }

  public AttendenceEntry getSpecificRecord(int attendanceId) {

    String flyer_number, event_name, upperStart, upperEnd, lowerStart, lowerEnd, spectators;

    String query = "SELECT * FROM " + DatabaseContract.EventRecorder.EVENT_TABLE_NAME +
            " WHERE " + DatabaseContract.EventRecorder.ATTENDENCE_ID + " = " + attendanceId;
    Cursor cursor = database.rawQuery(query, null);
    cursor.moveToFirst();

    flyer_number = cursor.getString(cursor.getColumnIndex(DatabaseContract.EventRecorder.FLYER_NUMBER));
    event_name = cursor.getString(cursor.getColumnIndex(DatabaseContract.EventRecorder.EVENT));
    upperStart = cursor.getString(cursor.getColumnIndex(DatabaseContract.EventRecorder.UPPER_START));
    upperEnd = cursor.getString(cursor.getColumnIndex(DatabaseContract.EventRecorder.UPPER_END));
    lowerStart = cursor.getString(cursor.getColumnIndex(DatabaseContract.EventRecorder.LOWER_START));
    lowerEnd = cursor.getString(cursor.getColumnIndex(DatabaseContract.EventRecorder.LOWER_END));
    spectators = cursor.getString(cursor.getColumnIndex(DatabaseContract.EventRecorder.SPECTATORS));

    AttendenceEntry result = new AttendenceEntry(flyer_number, event_name, upperStart, upperEnd,
            lowerStart, lowerEnd, spectators);

    return result;
  }

  private boolean stringToBoolean(String text) {
    if (text.equals("1")) {
      return true;
    } else {
      return false;
    }
  }

}
