package com.csis3175.pocketpal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IncVSExp extends AppCompatActivity {

    ViewExpenseActivity v = new ViewExpenseActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inc_vsexp);

        MyDB db = new MyDB(this);
        final SQLiteDatabase rdb = db.getReadableDatabase();
        String query = "SELECT * FROM User";
        String query2 = "SELECT * FROM Expenditures";
        List<String> list = new ArrayList<>();
        BarChart bar;
        bar = (BarChart) findViewById(R.id.graph);
        float totalExpenditure=0,salary = 0;
        try {
            Cursor cursor = rdb.rawQuery(query, null);
            Cursor cursor2 = rdb.rawQuery(query2, null);
            if (cursor != null && cursor2 != null) {
                cursor.moveToFirst();cursor2.moveToFirst();
                salary = Float.parseFloat(cursor.getString(3));
        do {
            for (int i = 1; i < cursor2.getColumnCount(); i++) {
                String x = cursor2.getString(i);
                list.add(x);
            }Log.d("list",list.toString());
            list.remove(0);
            list.removeAll(Arrays.asList("0"));
            totalExpenditure += Double.parseDouble(list.get(0));
            list.clear();
        } while (cursor2.moveToNext());
            }
        } catch (Exception ex) {}

        List<BarEntry> entriesGroup1 = new ArrayList<>();
        List<BarEntry> entriesGroup2 = new ArrayList<>();
        entriesGroup1.add(new BarEntry(0, salary));
        entriesGroup2.add(new BarEntry(1, totalExpenditure));
        BarDataSet set1 = new BarDataSet(entriesGroup1, "Income");
        BarDataSet set2 = new BarDataSet(entriesGroup2, "Expenditure");
        set1.setColors(Color.LTGRAY);
        float barWidth = 0.45f;
        BarData data = new BarData(set1, set2);
        data.setBarWidth(barWidth);
        bar.setData(data);
        bar.invalidate();
        bar.animateXY(3000,3000);
        bar.setY(0);

    }
}