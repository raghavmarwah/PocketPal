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

import static android.R.attr.button;

public class Budget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        Log.d("MainActivity","onCreate");
        MyDB db = new MyDB(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();
        final TableLayout theView = (TableLayout) findViewById(R.id.thetable);

        Button bAdd = (Button) findViewById(R.id.btnAdd);
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText eID = (EditText) findViewById(R.id.id);
                EditText eIN = (EditText) findViewById(R.id.income);
                EditText eAM = (EditText) findViewById(R.id.amount);

                ContentValues values = new ContentValues();
                values.put(BudgetEntry.INCOME,eIN.getText().toString());
                values.put(BudgetEntry.INCOME_AMOUNT,eAM.getText().toString());
                long newRowID = wdb.insert(BudgetEntry.TABLE_NAME,null,values);
                eID.setText(newRowID + "");

                theView.removeAllViews();

                String selectQuery = "SELECT * FROM " + BudgetEntry.TABLE_NAME;
                try {
                    Cursor cursor = rdb.rawQuery(selectQuery,null);
                    if(cursor != null) {
                        cursor.moveToFirst();
                        TextView data;
                        TableRow row;
                        int cnt = 0;
                        do{
                            row = new TableRow(Budget.this);
                            row.setPadding(2,2,2,2);
                            if(cnt++ % 2 == 0)
                                row.setBackgroundColor(Color.WHITE);
                            for(int i = 0; i < cursor.getColumnCount();i++)
                            {
                                data = new TextView(Budget.this);
                                if(i == 0)
                                {
                                    data.setTypeface(Typeface.DEFAULT_BOLD);
                                    data.setGravity(Gravity.CENTER_HORIZONTAL);
                                }
                                data.setText(cursor.getString(i));
                                row.addView(data);

                            }
                            theView.addView(row);
                        }while (cursor.moveToNext());
                        theView.setShrinkAllColumns(true);
                        theView.setStretchAllColumns(true);
                    }

                }catch (Exception ex){}

            }
        });
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}
