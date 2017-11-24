package com.emfso.henry.emfso_attendence.db;

import android.provider.BaseColumns;

import static com.emfso.henry.emfso_attendence.db.DatabaseContract.EventRecorder.ATTENDENCE_ID;
import static com.emfso.henry.emfso_attendence.db.DatabaseContract.EventRecorder.CREATE_DATE;
import static com.emfso.henry.emfso_attendence.db.DatabaseContract.EventRecorder.EVENT;
import static com.emfso.henry.emfso_attendence.db.DatabaseContract.EventRecorder.EVENT_TABLE_NAME;
import static com.emfso.henry.emfso_attendence.db.DatabaseContract.EventRecorder.LOWER_END;
import static com.emfso.henry.emfso_attendence.db.DatabaseContract.EventRecorder.LOWER_START;
import static com.emfso.henry.emfso_attendence.db.DatabaseContract.EventRecorder.SPECTATORS;
import static com.emfso.henry.emfso_attendence.db.DatabaseContract.EventRecorder.UPDATE_DATE;
import static com.emfso.henry.emfso_attendence.db.DatabaseContract.EventRecorder.UPPER_END;
import static com.emfso.henry.emfso_attendence.db.DatabaseContract.EventRecorder.UPPER_START;
import static com.emfso.henry.emfso_attendence.db.DatabaseContract.MembershipList.FIRST_NAME;
import static com.emfso.henry.emfso_attendence.db.DatabaseContract.MembershipList.FIXED_WING;
import static com.emfso.henry.emfso_attendence.db.DatabaseContract.MembershipList.FLYER_NUMBER;
import static com.emfso.henry.emfso_attendence.db.DatabaseContract.MembershipList.JUNIOR;
import static com.emfso.henry.emfso_attendence.db.DatabaseContract.MembershipList.LAST_NAME;
import static com.emfso.henry.emfso_attendence.db.DatabaseContract.MembershipList.MEMBERSHIP_TABLE_NAME;
import static com.emfso.henry.emfso_attendence.db.DatabaseContract.MembershipList.OUTDOOR;
import static com.emfso.henry.emfso_attendence.db.DatabaseContract.MembershipList.ROTARY_WING;

/**
 * Created by henry on 2017-10-28.
 */

public final class DatabaseContract {

  private DatabaseContract() {}

  public static final String CREATE_MEMBERSHIP_LIST_TABLE =
          "CREATE TABLE " + MEMBERSHIP_TABLE_NAME + " (" +
                  FLYER_NUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                  FIRST_NAME + " TEXT NOT NULL, " +
                  LAST_NAME + " TEXT NOT NULL, " +
                  OUTDOOR + " INTEGER NOT NULL, " +
                  FIXED_WING + " INTEGER NOT NULL, " +
                  ROTARY_WING + " INTEGER NOT NULL, " +
                  JUNIOR + " INTEGER NOT NULL)";

  public static final String CREATE_EVENT_TRACKER_TABLE =
          "CREATE TABLE " + EVENT_TABLE_NAME + " (" +
                  ATTENDENCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                  FLYER_NUMBER + " INTEGER NOT NULL, " +
                  EVENT + " TEXT NOT NULL, " +
                  UPPER_START + " TEXT NULL, " +
                  UPPER_END + " TEXT NULL, " +
                  LOWER_START + " TEXT NULL, " +
                  LOWER_END + " TEXT NULL, " +
                  SPECTATORS + " INTEGER NULL, " +
                  CREATE_DATE + " TEXT default CURRENT_TIMESTAMP, " +
                  UPDATE_DATE + " TEXT default CURRENT_TIMESTAMP)";
                  /*+
                  " FOREIGN KEY (" + FLYER_NUMBER + ") REFERENCES " + MEMBERSHIP_TABLE_NAME +
                  "(" + FLYER_NUMBER+"))";*/

  public static class EventRecorder implements BaseColumns {
    public static final String ATTENDENCE_ID = "attendence_id";
    public static final String EVENT_TABLE_NAME = "event_tracker";
    public static final String FLYER_NUMBER = "flyer_number";
    public static final String EVENT = "event";
    public static final String UPPER_START = "upper_start_time";
    public static final String UPPER_END = "upper_end_time";
    public static final String LOWER_START = "lower_start_time";
    public static final String LOWER_END = "lower_end_time";
    public static final String SPECTATORS = "spectators";
    public static final String CREATE_DATE = "create_date";
    public static final String UPDATE_DATE = "update_date";
  }

  public static class MembershipList implements BaseColumns {
    public static final String MEMBERSHIP_TABLE_NAME = "membership_list";
    public static final String FLYER_NUMBER = "flyer_number";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String OUTDOOR = "outdoor_aircraft";
    public static final String FIXED_WING = "fixed_wing_aircraft";
    public static final String ROTARY_WING = "rotary_wing_aircraft";
    public static final String JUNIOR= "junior_flyer";

  }




}
