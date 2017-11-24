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

    Bundle extras = getIntent().getExtras();
    attendanceId = extras.getInt("position") + 1;
    attendanceEntryToEdit = dataSource.getSpecificRecord(attendanceId);
    editOnClick();
  }

  protected void editOnClick() {
    flyer_id = (TextView) findViewById(R.id.edit_flyer_names);
    flyer_id.setText(getFlyerText());

    edit_flyer_input = (EditText) findViewById(R.id.edit_flyer_num);
    edit_eventName = (EditText) findViewById(R.id.edit_event_name);
    edit_upperStart = (EditText) findViewById(R.id.edit_upperStart);
    edit_upperEnd = (EditText) findViewById(R.id.edit_upperEnd);
    edit_lowerStart = (EditText) findViewById(R.id.edit_lowerStart);
    edit_LowerEnd = (EditText) findViewById(R.id.edit_lowerEnd);
    edit_spectators = (EditText) findViewById(R.id.edit_spectator);
    edit_attendance_btn = (Button) findViewById(R.id.edit_record_btn);

    edit_flyer_input.setText(String.valueOf(attendanceEntryToEdit.getFlyer_num()));
    edit_eventName.setText(attendanceEntryToEdit.getEvent());
    edit_upperStart.setText(attendanceEntryToEdit.getUpperStart());
    edit_upperEnd.setText(attendanceEntryToEdit.getUpperEnd());
    edit_lowerStart.setText(attendanceEntryToEdit.getLowerStart());
    edit_LowerEnd.setText(attendanceEntryToEdit.getLowerEnd());
    edit_spectators.setText(String.valueOf(attendanceEntryToEdit.getSpectators()));

    edit_attendance_btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AttendenceEntry newAtten = new AttendenceEntry(
                edit_flyer_input.getText().toString(),
                edit_eventName.getText().toString(),
                edit_upperStart.getText().toString(),
                edit_upperEnd.getText().toString(),
                edit_lowerStart.getText().toString(),
                edit_LowerEnd.getText().toString(),
                edit_spectators.getText().toString());

        dataSource.updateAttendance(newAtten, attendanceId);
        finish();
      }
    });


  }

  private String getFlyerText() {
    List<String> list = dataSource.getFlyerIdentification();
    String result = "";
    for (String item : list) {
      result += item + "\n";
    }
    return result;
  }

  @Override
  public void onStop() {
    super.onStop();
    if (dataSource.checkOpen() == true) {
      dataSource.close();
    }
  }

}
