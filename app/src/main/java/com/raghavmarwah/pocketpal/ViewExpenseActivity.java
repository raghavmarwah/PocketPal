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
                    Log.d("D", cursor.getString(1));
                    for (int i = 2; i <= 8; i++) {
                        temp = cursor.getString(i);
                        if(temp.equals("0"))
                            continue;
                        else{
                            if(i==2)
                                Log.d("D", "Groceries: "+temp);
                            else if(i==3)
                                Log.d("D", "Insurances: "+temp);
                            else if(i==4)
                                Log.d("D", "Phone Bill: "+temp);
                            else if(i==5)
                                Log.d("D", "Rent: "+temp);
                            else if(i==6)
                                Log.d("D", "Eating Out: "+temp);
                            else if(i==7)
                                Log.d("D", "Shopping: "+temp);
                            else if(i==8)
                                Log.d("D", "Miscellaneous: "+temp);
                            break;
                        }
                    }
                }while (cursor.moveToNext());
                cursor.close();
            }
        }catch (Exception ex){}


    }
}
