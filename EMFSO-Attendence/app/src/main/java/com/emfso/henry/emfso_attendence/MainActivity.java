package com.emfso.henry.emfso_attendence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.emfso.henry.emfso_attendence.db.DatabaseDataSource;

public class MainActivity extends AppCompatActivity {

  private DatabaseDataSource dataSource;

  public static Button create_user_btn;
  public static Button attendence_btn;
  public static Button records_btn;
  public static Button db_btn;
  private boolean dbOpen = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    dataSource = dataSource.getDataSourceInstance(this);
    onClickButtonListenerMainMenu();
  }

  public void onClickButtonListenerMainMenu() {
    create_user_btn = (Button) findViewById(R.id.create_user_btn);
    attendence_btn = (Button) findViewById(R.id.attendence_btn);
    records_btn = (Button) findViewById(R.id.records_btn);
    db_btn = (Button) findViewById(R.id.dbButton);
    updateDB_BtnText();

    create_user_btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), New_User.class);
        startActivity(intent);
      }
    });

    attendence_btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), New_Record.class);
        startActivity(intent);
      }
    });

    records_btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent (v.getContext(), View_Tables.class);
        startActivity(intent);
      }
    });

    db_btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dbHandler();
      }
    });
  }

  private void updateDB_BtnText() {
    if (dbOpen)
      db_btn.setText("Database OPEN");
    else
      db_btn.setText("Database CLOSED");
  }

  private void dbHandler() {
    if (dbOpen == false) {
      dataSource.open();
      dbOpen = true;
    } else {
      dataSource.close();
      dbOpen = false;
    }
    updateDB_BtnText();

  }

}
