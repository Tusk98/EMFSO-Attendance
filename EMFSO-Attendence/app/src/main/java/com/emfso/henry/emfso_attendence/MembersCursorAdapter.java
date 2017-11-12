package com.emfso.henry.emfso_attendence;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.emfso.henry.emfso_attendence.db.DBOpenHelper;
import com.emfso.henry.emfso_attendence.db.DatabaseContract;
import com.emfso.henry.emfso_attendence.db.DatabaseDataSource;

/**
 * Created by henry on 2017-11-07.
 */

public class MembersCursorAdapter extends CursorAdapter {
  private DatabaseDataSource dataSource;

  public MembersCursorAdapter(Context context, Cursor c, int flags) {
    super(context, c, flags);
    dataSource = DatabaseDataSource.getDataSourceInstance(context);
  }

  @Override
  public android.view.View newView(Context context, Cursor cursor, ViewGroup parent) {
    return LayoutInflater.from(context).inflate(
            R.layout.activity_view, parent, false
    );
  }

  @Override
  public void bindView(android.view.View view, Context context, Cursor cursor) {

    String noteText = cursor.getString(
            cursor.getColumnIndex(DatabaseContract.MembershipList.FIRST_NAME));

    int pos = noteText.indexOf(10);
    if (pos != -1) {
      noteText = noteText.substring(0, pos) + " ...";
    }

    TextView tv = (TextView) view.findViewById(R.id.view_flyer_name);
    tv.setText(noteText);

  }
}
