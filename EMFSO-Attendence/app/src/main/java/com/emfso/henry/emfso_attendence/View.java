package com.emfso.henry.emfso_attendence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.emfso.henry.emfso_attendence.db.DatabaseDataSource;

public class View extends AppCompatActivity {
  private DatabaseDataSource dataSource;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view);
    dataSource = dataSource.getDataSourceInstance(this);

  }
}
