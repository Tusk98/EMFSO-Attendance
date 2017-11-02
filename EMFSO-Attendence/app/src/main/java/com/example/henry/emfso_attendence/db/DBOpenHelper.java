package com.example.henry.emfso_attendence.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by henry on 2017-10-28.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "emfso_attendence.db";
  private static final int DATABASE_VERSION = 1;

  public DBOpenHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(DatabaseContract.CREATE_MEMBERSHIP_LIST_TABLE);
    //db.execSQL(DatabaseContract.CREATE_EVENT_TRACKER_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.MembershipList.MEMBERSHIP_TABLE_NAME);
   // db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.EventRecorder.EVENT_TABLE_NAME);
    onCreate(db);
  }

}
