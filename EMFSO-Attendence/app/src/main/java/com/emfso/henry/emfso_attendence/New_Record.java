package com.emfso.henry.emfso_attendence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.emfso.henry.emfso_attendence.database_content.AttendenceEntry;
import com.emfso.henry.emfso_attendence.db.DatabaseDataSource;

import java.util.List;

public class New_Record extends AppCompatActivity {

  private static DatabaseDataSource dataSource;

  public static TextView flyer_id;
  public static EditText flyer_input;
  public static EditText event_name;
  public static EditText upperStart;
  public static EditText upperEnd;
  public static EditText lowerStart;
  public static EditText lowerEnd;
  public static EditText spectators;
  public static Button new_record_btn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_record);
    dataSource = dataSource.getDataSourceInstance(this);
    newAttendenceOnClick();
  }

  protected void newAttendenceOnClick() {
    flyer_id = (TextView) findViewById(R.id.flyer_names);
    flyer_input = (EditText) findViewById(R.id.flyer_num_input);
    event_name = (EditText) findViewById(R.id.event_name_input);
    upperStart = (EditText) findViewById(R.id.upperStart_input);
    upperEnd = (EditText) findViewById(R.id.upperEnd_input);
    lowerStart = (EditText) findViewById(R.id.lowerStart_input);
    lowerEnd = (EditText) findViewById(R.id.lowerEnd_input);
    spectators = (EditText) findViewById(R.id.spectator_input);
    new_record_btn = (Button) findViewById(R.id.create_record_btn);
    String flyers = getFlyerText();
    flyer_id.setText(flyers);

    new_record_btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AttendenceEntry atten = new AttendenceEntry(flyer_input.getText().toString(),
                event_name.getText().toString(),
                upperStart.getText().toString(),
                upperEnd.getText().toString(),
                lowerStart.getText().toString(),
                lowerEnd.getText().toString(),
                spectators.getText().toString());

        long rowId = dataSource.newAttendence(atten);
        Toast toast = Toast.makeText(v.getContext(),
                "New attendence added with ID #: " + rowId, Toast.LENGTH_SHORT);
        toast.show();
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

}
