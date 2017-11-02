package com.example.henry.emfso_attendence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.henry.emfso_attendence.db.DatabaseDataSource;

public class MainActivity extends AppCompatActivity {

  private DatabaseDataSource dataSource;

  public static Button create_user_btn;
  public static Button attendence_btn;
  public static Button records_btn;

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

    create_user_btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), New_User.class);
        startActivity(intent);
      }
    });

  }


  @Override
  public void onResume() {
    super.onResume();
    //dataSource.open();
  }

  @Override
  public void onPause() {
    super.onPause();
    //dataSource.close();
  }

}
