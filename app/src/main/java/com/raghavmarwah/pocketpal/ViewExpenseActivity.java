package com.raghavmarwah.pocketpal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ViewExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expense);

        Bundle bundle = getIntent().getExtras();
        String date = bundle.getString("date");
        TextView dateView = (TextView) findViewById(R.id.dateText);
        dateView.setText("Date: "+date);


        MyDB db = new MyDB(this);
        final SQLiteDatabase rdb = db.getReadableDatabase();

        String query = "SELECT * FROM " + UserEntry.TABLE_NAME_2 + " WHERE DATE = '" + date + "'";
        try{
            Cursor cursor = rdb.rawQuery(query, null);
            if(cursor!=null) {
                cursor.move(1);
                String temp = "";
                do{
                    for (int i = 1; i < cursor.getColumnCount(); i++) {
                        Log.d("D", cursor.getString(i));
                    }
                }while (cursor.moveToNext());
                cursor.close();
            }
        }catch (Exception ex){}


    }
}
