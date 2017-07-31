package com.csis3175.pocketpal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewExpenseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expense);

        Bundle bundle = getIntent().getExtras();
        String date = bundle.getString("date");
        TextView dateView = (TextView) findViewById(R.id.dateText);
        dateView.setText("Date: " + date);

        MyDB db = new MyDB(this);
        final SQLiteDatabase rdb = db.getReadableDatabase();
        final TableLayout theView = (TableLayout) findViewById(R.id.theTable);

        String query = "SELECT * FROM " + UserEntry.TABLE_NAME_3 + " WHERE DATE = '" + date + "'";
        List<String> list = new ArrayList<>();
        try {
            Cursor cursor = rdb.rawQuery(query, null);
            if (cursor != null) {
                cursor.move(1);
                TextView data;
                TableRow row;
                int count = 1;
                do {

                    row = new TableRow(ViewExpenseActivity.this);
                    row.setPadding(3,3,3,3);
                    data = new TextView(ViewExpenseActivity.this);
                    for (int i = 1; i < cursor.getColumnCount(); i++) {
                        if (i == 0) {
                            data.setTypeface(Typeface.DEFAULT_BOLD);
                            data.setGravity(Gravity.CENTER_HORIZONTAL);
                        }
                        String x = cursor.getString(i);
                        list.add(x);
                        Log.d("d",cursor.getString(i));
                    }
                    List<String> temp = new ArrayList<>(list);
                    list.remove(0);
                    list.removeAll(Arrays.asList("0"));
                    int i = temp.indexOf(list.get(0).toString());
                    String s = getExpense(i);
                    data.setText(count+".      $ "+list.get(0).toString()+"                         "+s);
                    list.clear();
                    data.setTextSize(TypedValue.COMPLEX_UNIT_SP,19);
                    row.addView(data);
                    theView.addView(row);
                    count++;
                } while (cursor.moveToNext());
                cursor.close();
            }
        } catch (Exception ex) {
        }
    }

    public String getExpense(int i) {
        if (i == 1)
            return "Grocery";
        else if (i == 2)
            return "Insurance";
        else if (i == 3)
            return "Phone Bill";
        else if (i == 4)
            return "Rent";
        else if (i == 5)
            return "Eating Out";
        else if (i == 6)
            return "Shopping";
        else if (i == 7)
            return "Miscellaneous";
        else
            return "Not in Directory";

    }

}
