package com.raghavmarwah.pocketpal;

import android.content.ContentValues;
import android.content.Intent;
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
import android.widget.LinearLayout;
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

        final LinearLayout defineProfile = (LinearLayout) findViewById(R.id.user_data_layout);
        final LinearLayout defineBudget = (LinearLayout) findViewById(R.id.budget_define_layout);

        Log.d("MainActivity", "onCreate");
        MyDB db = new MyDB(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();
        final Button bAdd = (Button) findViewById(R.id.gobtn);
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(defineProfile.getVisibility()==View.VISIBLE){

                    EditText eNM = (EditText) findViewById(R.id.name);
                    EditText eEM = (EditText) findViewById(R.id.email);
                    EditText eIN = (EditText) findViewById(R.id.income);

                    ContentValues values = new ContentValues();
                    values.put(UserEntry.COLUMN_NAME_USRNAME, eNM.getText().toString());
                    values.put(UserEntry.COLUMN_NAME_EMAIL, eEM.getText().toString());
                    values.put(UserEntry.COLUMN_NAME_INCOME, eIN.getText().toString());
                    wdb.insert(UserEntry.TABLE_NAME, null, values);

                    bAdd.setText("FINISH");
                    defineProfile.setVisibility(View.INVISIBLE);
                    defineBudget.setVisibility(View.VISIBLE);
                }
                else{
                    EditText groceries = (EditText) findViewById(R.id.groceries);
                    EditText insuarances = (EditText) findViewById(R.id.insurances);
                    EditText phone = (EditText) findViewById(R.id.phone);
                    EditText rent = (EditText) findViewById(R.id.rent);
                    EditText eat = (EditText) findViewById(R.id.eat);
                    EditText shop = (EditText) findViewById(R.id.shop);
                    EditText misc = (EditText) findViewById(R.id.misc);

                    ContentValues values = new ContentValues();
                    values.put(UserEntry.COLUMN_NAME_GROCERIES_L, groceries.getText().toString());
                    values.put(UserEntry.COLUMN_NAME_INSURANCES_L, insuarances.getText().toString());
                    values.put(UserEntry.COLUMN_NAME_BILLS_L, phone.getText().toString());
                    values.put(UserEntry.COLUMN_NAME_RENT_L, rent.getText().toString());
                    values.put(UserEntry.COLUMN_NAME_EAT_L, eat.getText().toString());
                    values.put(UserEntry.COLUMN_NAME_SHOP_L, shop.getText().toString());
                    values.put(UserEntry.COLUMN_NAME_MISC_L, misc.getText().toString());
                    wdb.insert(UserEntry.TABLE_NAME_2, null, values);

                    Intent intent = new Intent(AddUserInfoActivity.this, MonthViewActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }}

