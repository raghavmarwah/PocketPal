package com.csis3175.pocketpal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.concurrent.TimeUnit;

public class GetStartedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        MyDB db = new MyDB(this);
        final SQLiteDatabase rdb = db.getReadableDatabase();

        String selectQuery = "SELECT COUNT(*) FROM " + UserEntry.TABLE_NAME;
        int check = 0;
        try {
            Cursor cursor = rdb.rawQuery(selectQuery, null);
            if (cursor != null) {
                cursor.moveToFirst();
                check = Integer.parseInt(cursor.getString(0));
            }

        } catch (Exception ex) {}

        if(check==0) {

            Button next = (Button) findViewById(R.id.get_started_button);
            next.setVisibility(View.VISIBLE);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                Intent myIntent = new Intent(GetStartedActivity.this, AddUserInfoActivity.class);
                startActivity(myIntent);
                finish();

                }
            });
        }
        else{

            ProgressBar progressIndeterminate = (ProgressBar) findViewById(R.id.progressIndeterminate);
            progressIndeterminate.setVisibility(View.VISIBLE);

            new CountDownTimer(1000,1000){
                @Override
                public void onTick(long millisUntilFinished){}

                @Override
                public void onFinish(){

                    Intent myIntent = new Intent(GetStartedActivity.this,MonthViewActivity.class);
                    startActivity(myIntent);
                    finish();

                }
            }.start();
        }

    }

}
