package com.emfso.henry.emfso_attendence.edit_value;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.emfso.henry.emfso_attendence.R;
import com.emfso.henry.emfso_attendence.database_content.User;
import com.emfso.henry.emfso_attendence.db.DatabaseDataSource;

public class Edit_User extends AppCompatActivity {
  public static EditText edit_first;
  public static EditText edit_last;
  public static Button edit_user_btn;
  public static CheckBox edit_fixed;
  public static CheckBox edit_rotary;
  public static CheckBox edit_outdoor;
  public static CheckBox edit_junior;
  private int userId;
  private User userToEdit;

  private DatabaseDataSource dataSource;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit__user);
    dataSource = dataSource.getDataSourceInstance(this);
    dataSource.open();

    Bundle extras = getIntent().getExtras();
    userId = extras.getInt("position") + 1;
    userToEdit = dataSource.getSpecificUser(userId);
    editOnClick();
  }

  protected void editOnClick() {
    edit_first = (EditText) findViewById(R.id.edit_first_name);
    edit_last = (EditText) findViewById(R.id.edit_last_name);
    edit_user_btn = (Button) findViewById(R.id.edit_user_btn);
    edit_fixed = (CheckBox) findViewById(R.id.edit_fixed);
    edit_rotary = (CheckBox) findViewById(R.id.edit_rotary);
    edit_junior = (CheckBox) findViewById(R.id.edit_junior);
    edit_outdoor = (CheckBox) findViewById(R.id.edit_outdoor);

    edit_first.setText(userToEdit.getFirstName());
    edit_last.setText(userToEdit.getLastName());
    edit_fixed.setChecked(userToEdit.isFixed_wing());
    edit_outdoor.setChecked(userToEdit.isOutdoor());
    edit_rotary.setChecked(userToEdit.isRotary_wing());
    edit_junior.setChecked(userToEdit.isJunior());

    edit_user_btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        User upUser= new User(edit_first.getText().toString(),
                edit_last.getText().toString(),
                edit_outdoor.isChecked(),
                edit_fixed.isChecked(),
                edit_rotary.isChecked(),
                edit_junior.isChecked());

        dataSource.updateFlyerInfo(upUser, userId);
        finish();
      }
    });
  }

  @Override
  public void onPause() {
    super.onPause();
    if (dataSource.checkOpen() == true) {
      dataSource.close();
    }
  }

}
