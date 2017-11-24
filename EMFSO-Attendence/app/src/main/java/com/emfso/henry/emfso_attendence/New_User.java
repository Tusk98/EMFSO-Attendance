package com.emfso.henry.emfso_attendence;

import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.emfso.henry.emfso_attendence.database_content.User;
import com.emfso.henry.emfso_attendence.db.DBOpenHelper;
import com.emfso.henry.emfso_attendence.db.DatabaseDataSource;

public class New_User extends AppCompatActivity {

  private DatabaseDataSource dataSource ;

  public static EditText first_name_field;
  public static EditText last_name_field;
  public static CheckBox outdoor_box;
  public static CheckBox fixed_aircraft_box;
  public static CheckBox rotary_aircraft_box;
  public static CheckBox junior_box;
  public static Button new_user_btn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new__user);
    dataSource = dataSource.getDataSourceInstance(this);
    dataSource.open();
    newUserOnClick();
  }

  protected void newUserOnClick() {
    first_name_field = (EditText) findViewById(R.id.first_name_field);
    last_name_field = (EditText) findViewById(R.id.last_name_field);
    outdoor_box = (CheckBox) findViewById(R.id.outdoor_box);
    fixed_aircraft_box = (CheckBox) findViewById(R.id.fixed_wing_box);
    rotary_aircraft_box = (CheckBox) findViewById(R.id.rotary_wing_box);
    junior_box = (CheckBox) findViewById(R.id.junior_box);
    new_user_btn = (Button) findViewById(R.id.new_user_btn);

    new_user_btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        User newUser = new User(first_name_field.getText().toString(),
                last_name_field.getText().toString(),
                outdoor_box.isChecked(),
                fixed_aircraft_box.isChecked(),
                rotary_aircraft_box.isChecked(),
                junior_box.isChecked());

        long flyerId = dataSource.newFlyer(newUser);
        Toast toast = Toast.makeText(v.getContext(),
                "New user added with Flyer ID #: " + flyerId, Toast.LENGTH_SHORT);
        toast.show();
        dataSource.close();
        recreate();
      }
    });
  }

  @Override
  public void onStop() {
    super.onStop();
    if (dataSource.checkOpen() == true) {
      dataSource.close();
    }
  }

}
