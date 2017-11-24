package com.emfso.henry.emfso_attendence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.emfso.henry.emfso_attendence.database_content.User;
import com.emfso.henry.emfso_attendence.db.DatabaseDataSource;
import com.emfso.henry.emfso_attendence.edit_value.Edit_Attendance;
import com.emfso.henry.emfso_attendence.edit_value.Edit_User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class View_All_Records extends Activity {
  List<String> userInfo = new ArrayList<>();
  List<String> attendanceInfo = new ArrayList<>();
  private DatabaseDataSource dataSource;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view__all__records);
    dataSource = dataSource.getDataSourceInstance(this);
    dataSource.open();
    loadUsers();
    loadAttendance();
  }

  private void loadAttendance() {
    attendanceInfo = dataSource.getAttendanceRecord();
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
            this, android.R.layout.simple_list_item_1, attendanceInfo);

    ListView attendanceView = (ListView) findViewById(R.id.attendanceList);
    attendanceView.setAdapter(adapter);

    attendanceView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), Edit_Attendance.class);
        intent.putExtra("position", position);
        startActivity(intent);
        finish();
      }
    });
  }

  private void loadUsers() {
    userInfo = dataSource.getFlyerIdentification();
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
            this, android.R.layout.simple_list_item_1, userInfo);

    ListView userView = (ListView) findViewById(R.id.userList);
    userView.setAdapter(adapter);

    userView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), Edit_User.class);
        intent.putExtra("position", position);
        startActivity(intent);
        finish();
      }
    });
  }

  @Override
  public void onResume() {
    super.onResume();
    if (dataSource.checkOpen() == false) {
      dataSource.open();
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    if (dataSource.checkOpen() == true) {
      dataSource.close();
    }
  }



}