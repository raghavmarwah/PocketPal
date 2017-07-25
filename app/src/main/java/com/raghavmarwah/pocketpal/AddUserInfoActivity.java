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
        Button bAdd = (Button) findViewById(R.id.gobtn);
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText eNM = (EditText) findViewById(R.id.name);
                EditText eEM = (EditText) findViewById(R.id.email);
                EditText eIN = (EditText) findViewById(R.id.income);

                ContentValues values = new ContentValues();
                values.put(UserEntry.COLUMN_NAME_USRNAME, eNM.getText().toString());
                values.put(UserEntry.COLUMN_NAME_EMAIL, eEM.getText().toString());
                values.put(UserEntry.COLUMN_NAME_INCOME, eIN.getText().toString());
                wdb.insert(UserEntry.TABLE_NAME, null, values);

            }
        });
    }}

