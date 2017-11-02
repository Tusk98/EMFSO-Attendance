package com.example.henry.emfso_attendence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.henry.emfso_attendence.db.DatabaseDataSource;

public class New_Record extends AppCompatActivity {
  private DatabaseDataSource dbSource;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_record);
  }
}
