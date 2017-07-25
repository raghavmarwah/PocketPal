package com.raghavmarwah.pocketpal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class AddUserInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_info);

        //Making activity fullscreen!!
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        //Hiding the ActionBar
        getSupportActionBar().hide();

        Log.d("MainActivity", "onCreate");
        MyDB db = new MyDB(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();
        //final TableLayout theView = (TableLayout) findViewById(R.id.thetable);

        Button bAdd = (Button) findViewById(R.id.gobtn);
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText eNM = (EditText) findViewById(R.id.name);
                EditText eEM = (EditText) findViewById(R.id.email);
                EditText eIn = (EditText) findViewById(R.id.income);

                ContentValues values = new ContentValues();
                values.put(UserEntry.COLUMN_NAME_USRNAME, eNM.getText().toString());
                values.put(UserEntry.COLUMN_NAME_EMAIL, eEM.getText().toString());
                long newRowID = wdb.insert(UserEntry.TABLE_NAME, null, values);

                /*theView.removeAllViews();

                String selectQuery = "SELECT * FROM " + UserEntry.TABLE_NAME;
                try {
                    Cursor cursor = rdb.rawQuery(selectQuery, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        TextView data;
                        TableRow row;
                        int cnt = 0;
                        do {
                            row = new TableRow(AddUserInfoActivity.this);
                            row.setPadding(2, 2, 2, 2);
                            if (cnt++ % 2 == 0)
                                row.setBackgroundColor(Color.WHITE);
                            for (int i = 0; i < cursor.getColumnCount(); i++) {
                                data = new TextView(AddUserInfoActivity.this);
                                if (i == 0) {
                                    data.setTypeface(Typeface.DEFAULT_BOLD);
                                    data.setGravity(Gravity.CENTER_HORIZONTAL);
                                }
                                data.setText(cursor.getString(i));
                                row.addView(data);

                            }
                            theView.addView(row);
                        } while (cursor.moveToNext());
                        theView.setShrinkAllColumns(true);
                        theView.setStretchAllColumns(true);
                    }

                } catch (Exception ex) {
                }*/

            }
        });

        Button next = (Button) findViewById(R.id.gobtn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }}

