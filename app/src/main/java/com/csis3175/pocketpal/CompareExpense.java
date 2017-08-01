package com.csis3175.pocketpal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompareExpense extends AppCompatActivity {
        private static String TAG = "PieChart";
        private String[] xdata = {"groc", "bills", "wd","dfs", "ewfsds" ,"sec","wee"};
        PieChart pie;
        MyDB db = new MyDB(this);
        public int ydata[] = new int[7];
        ViewExpenseActivity v = new ViewExpenseActivity();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_compare_expense);
            pie = (PieChart) findViewById(R.id.CompareExpense);
            pie.setHoleRadius(0);
            pie.setTransparentCircleAlpha(0);
            pie.setRotationEnabled(true);
            pie.setTransparentCircleAlpha(0);
            final SQLiteDatabase rdb = db.getReadableDatabase();
            String query = "SELECT * FROM Expenditures";
            Integer groc2=0,ins2=0,ph2=0,rnt2=0,et2=0,shp2=0,msc2=0;
            List<String> list = new ArrayList<>();
            try {
                Cursor cursor = rdb.rawQuery(query, null);
                if (cursor != null) {
                    cursor.move(1);
                    do {
                        for (int i = 1; i < cursor.getColumnCount(); i++) {
                            String x = cursor.getString(i);
                            list.add(x);
                            Log.d("d",cursor.getString(i));
                        }
                        List<String> temp = new ArrayList<>(list);
                        list.remove(0);
                        list.removeAll(Arrays.asList("0"));
                        int i = temp.indexOf(list.get(0).toString());

                        String s = v.getExpense(i);
                        if (s == "Grocery")
                            groc2 += Integer.parseInt(list.get(0));
                        else if (s == "Insurance")
                            ins2 += Integer.parseInt(list.get(0));
                        else if (s == "Phone Bill")
                            ph2 += Integer.parseInt(list.get(0));
                        else if (s == "Rent")
                            rnt2 += Integer.parseInt(list.get(0));
                        else if (s == "Dinning Out")
                            et2 += Integer.parseInt(list.get(0));
                        else if (s == "Shopping")
                            shp2 += Integer.parseInt(list.get(0));
                        else if (s == "Miscellaneous")
                            msc2  += Integer.parseInt(list.get(0));
                        list.clear();
                    } while (cursor.moveToNext());
                    ydata[0] = groc2;ydata[1] = ins2;
                    ydata[2] = ph2;ydata[3] = rnt2;
                    ydata[4] = et2;ydata[5] = shp2;
                    ydata[6] = msc2;
                    Log.d("arraymain",Arrays.toString(ydata));
                }
            } catch (Exception ex) {}
            addDataSet();
        }
        private void addDataSet() {
            Log.d(TAG, "addDataSet: add data set");
            ArrayList<PieEntry> yentrys = new ArrayList<>();
            ArrayList<String> xentry = new ArrayList<>();

            for (int i = 0; i < ydata.length; i++) {
                yentrys.add(new PieEntry(ydata[i], i));
            }
            for (int i = 1; i < xdata.length; i++) {
                xentry.add(xdata[i]);

            }
            PieDataSet piedataset = new PieDataSet(yentrys, "Spendings");

            piedataset.setSliceSpace(1);
            piedataset.setValueTextSize(12);
            ArrayList<Integer> colors = new ArrayList<Integer>();
            for (int i : ColorTemplate.JOYFUL_COLORS)
                colors.add(i);
            for (int i : ColorTemplate.LIBERTY_COLORS)
                colors.add(i);
            for (int i : ColorTemplate.VORDIPLOM_COLORS)
                colors.add(i);
            for (int i : ColorTemplate.COLORFUL_COLORS)
                colors.add(i);
            for (int i : ColorTemplate.PASTEL_COLORS)
                colors.add(i);
            for (int i : ColorTemplate.MATERIAL_COLORS)
                colors.add(i);

            piedataset.setColors(colors);
            Legend legend = pie.getLegend();
            legend.setForm(Legend.LegendForm.CIRCLE);
            legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

            PieData piedataobject = new PieData(piedataset);
            pie.setData(piedataobject);
            pie.setTouchEnabled(true);
            pie.setEnabled(true);
            pie.setUsePercentValues(true);
            pie.invalidate();
            pie.animateXY(3000, 3000);

        }

}
