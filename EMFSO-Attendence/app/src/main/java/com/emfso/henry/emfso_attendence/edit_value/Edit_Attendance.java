package com.emfso.henry.emfso_attendence.edit_value;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.emfso.henry.emfso_attendence.R;
import com.emfso.henry.emfso_attendence.database_content.AttendenceEntry;
import com.emfso.henry.emfso_attendence.db.DatabaseDataSource;

import java.util.List;

public class Edit_Attendance extends AppCompatActivity {

  private DatabaseDataSource dataSource;

  public static TextView flyer_id;
  public static EditText edit_flyer_input;
  public static EditText edit_eventName;
  public static EditText edit_upperStart;
  public static EditText edit_upperEnd;
  public static EditText edit_lowerStart;
  public static EditText edit_LowerEnd;
  public static EditText edit_spectators;
  public static Button edit_attendance_btn;
  private int attendanceId;
  private AttendenceEntry attendanceEntryToEdit;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit__attendance);
    dataSource = dataSource.getDataSourceInstance(this);
    dataSource.open();
  }

}
