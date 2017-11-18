package com.emfso.henry.emfso_attendence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.emfso.henry.emfso_attendence.db.DatabaseDataSource;

import java.util.List;

public class View_Tables extends AppCompatActivity {
  private DatabaseDataSource dataSource;
  private TextView flyers;
  private TextView attendance;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view__tables);
    dataSource = dataSource.getDataSourceInstance(this);
    dataSource.open();
    loadInfo();
  }

  protected void loadInfo() {
    flyers = (TextView) findViewById(R.id.view_flyers);
    attendance = (TextView) findViewById(R.id.view_attendance);
    String flyText = getFlyerText();
    String atenText = getAttendanceText();

    flyers.setText(flyText);
    attendance.setText(atenText);

  }

  private String getFlyerText() {
    List<String> list = dataSource.getFlyerIdentification();
    String result = "";
    for (String item : list) {
      result += item + "\n";
    }

    if (list.isEmpty()) {
      list.add("");
    }
    return result;
  }

  private String getAttendanceText() {
    List<String> list = dataSource.getAttendanceRecord();
    String result = "";
    for (String item : list) {
      result += item + "\n\n";
    }

    if (list.isEmpty()) {
      list.add("");
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
