package com.csis3175.pocketpal;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MonthViewActivity extends AppCompatActivity {

    LinearLayout homeLayout;
    RelativeLayout calendarLayout;
    RelativeLayout profileLayout;
    LinearLayout analystLayout;

    MyDB db = new MyDB(this);
    private ImageView pic;
    ViewExpenseActivity v = new ViewExpenseActivity();
    public double groc=0,ins=0,ph=0,rnt=0,et=0,shp=0,msc=0;
    public double groc2=0,ins2=0,ph2=0,rnt2=0,et2=0,shp2=0,msc2=0;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigationHome:
                    homeLayout.setVisibility(View.VISIBLE);
                    calendarLayout.setVisibility(View.INVISIBLE);
                    profileLayout.setVisibility(View.INVISIBLE);
                    analystLayout.setVisibility(View.INVISIBLE);
                    refreshEverything();
                    return true;
                case R.id.navigationAnalyst:
                    homeLayout.setVisibility(View.INVISIBLE);
                    calendarLayout.setVisibility(View.INVISIBLE);
                    profileLayout.setVisibility(View.INVISIBLE);
                    analystLayout.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigationCalendar:
                    homeLayout.setVisibility(View.INVISIBLE);
                    calendarLayout.setVisibility(View.VISIBLE);
                    profileLayout.setVisibility(View.INVISIBLE);
                    analystLayout.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigationProfile:
                    homeLayout.setVisibility(View.INVISIBLE);
                    calendarLayout.setVisibility(View.INVISIBLE);
                    profileLayout.setVisibility(View.VISIBLE);
                    analystLayout.setVisibility(View.INVISIBLE);
                    DisplayProfile();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_view);
        refreshEverything();DisplayProfile();

        //Bottom navigation bar
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        //Layout Variables
        homeLayout = (LinearLayout) findViewById(R.id.homeLayout);
        calendarLayout = (RelativeLayout) findViewById(R.id.calendarLayout);
        profileLayout = (RelativeLayout) findViewById(R.id.profileLayout);
        analystLayout = (LinearLayout) findViewById(R.id.analystLayout);
        pic = (ImageView) findViewById(R.id.propic);

        //Object Variables
        Button addExpenditure = (Button) findViewById(R.id.addExpButton);
        Button viewExpenditure = (Button) findViewById(R.id.viewExpButton);
        ImageButton  incvsexp = (ImageButton) findViewById(R.id.image1);
        ImageButton  piechart = (ImageButton) findViewById(R.id.image2);
        piechart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(MonthViewActivity.this,CompareExpense.class);
                startActivity(s);
            }
        });
        incvsexp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MonthViewActivity.this,IncVSExp.class);
                startActivity(i);
            }
        });

        //onClick listeners
        addExpenditure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePicker selectedDate = (DatePicker) findViewById(R.id.calendarView);
                String date = selectedDate.getYear()+"-"+(selectedDate.getMonth()+1)+"-"+selectedDate.getDayOfMonth();

                Intent intent = new Intent(MonthViewActivity.this, AddExpenseActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);

            }
        });
        viewExpenditure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePicker selectedDate = (DatePicker) findViewById(R.id.calendarView);
                String date = selectedDate.getYear()+"-"+(selectedDate.getMonth()+1)+"-"+selectedDate.getDayOfMonth();

                Intent intent = new Intent(MonthViewActivity.this, ViewExpenseActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);

            }
        });
    }

    private void DisplayProfile() {

        TextView username = (TextView) findViewById(R.id.usrname);
        TextView email = (TextView) findViewById(R.id.email);
        TextView inc = (TextView) findViewById(R.id.income);
        Button delete = (Button) findViewById(R.id.delete);

        final SQLiteDatabase rdb = db.getReadableDatabase();
        final SQLiteDatabase wdb = db.getWritableDatabase();
        String query = "SELECT * FROM User";
        final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS User , Budget , Expenditures;";
        try {
            Cursor cursor = rdb.rawQuery(query, null);
            if (cursor != null) {
                cursor.moveToFirst();
                username.setText("Name: " + cursor.getString(1));
                email.setText("email: " + cursor.getString(2));
                inc.setText("Salary: $" + cursor.getString(3));
                String uriimage = cursor.getString(4).toString();
                Uri finalUri = Uri.parse("file:// "+ uriimage);
                InputStream inputStream;
                try
                {
                    inputStream = getContentResolver().openInputStream(finalUri);
                    Bitmap image = BitmapFactory.decodeStream(inputStream);
                    pic.setImageBitmap(image);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception ex) {}
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    wdb.rawQuery(SQL_DELETE_ENTRIES, null);
                    Log.d("delete:no error","dd");
                }catch (Exception ex){
                Log.d("delete:error",ex.toString());}
            }
        });
    }

    private void refreshEverything() {

        TextView groceries = (TextView) findViewById(R.id.groceriesTotal);
        TextView insurances = (TextView) findViewById(R.id.insurancesTotal);
        TextView phone = (TextView) findViewById(R.id.phoneTotal);
        TextView rent = (TextView) findViewById(R.id.rentTotal);
        TextView eat = (TextView) findViewById(R.id.eatTotal);
        TextView shop = (TextView) findViewById(R.id.shopTotal);
        TextView misc = (TextView) findViewById(R.id.miscTotal);

        TextView groceries2 = (TextView) findViewById(R.id.groceriesCurrent);
        TextView insurances2 = (TextView) findViewById(R.id.insurancesCurrent);
        TextView phone2 = (TextView) findViewById(R.id.phoneCurrent);
        TextView rent2 = (TextView) findViewById(R.id.rentCurrent);
        TextView eat2 = (TextView) findViewById(R.id.eatCurrent);
        TextView shop2 = (TextView) findViewById(R.id.shopCurrent);
        TextView misc2 = (TextView) findViewById(R.id.miscCurrent);

        TextView monthlyBudget = (TextView) findViewById(R.id.monthlyBudget);
        TextView currentExpenditure = (TextView) findViewById(R.id.currentExpenditure);
        ProgressBar progressgroc = (ProgressBar) findViewById(R.id.groceriesProgress);
        ProgressBar progressinc = (ProgressBar) findViewById(R.id.insurancesProgress);
        ProgressBar progressphn = (ProgressBar) findViewById(R.id.phoneProgress);
        ProgressBar progressrent = (ProgressBar) findViewById(R.id.rentProgress);
        ProgressBar progresseat = (ProgressBar) findViewById(R.id.eatProgress);
        ProgressBar progressshop = (ProgressBar) findViewById(R.id.shopProgress);
        ProgressBar progressmisc = (ProgressBar) findViewById(R.id.miscProgress);


        double totalBudget = 0;
        double totalExpenditure = 0;

        groc=0;ins=0;ph=0;rnt=0;et=0;shp=0;msc=0;

        final SQLiteDatabase rdb = db.getReadableDatabase();
        String query = "SELECT * FROM Budget";
        try {
            Cursor cursor = rdb.rawQuery(query, null);
            if (cursor != null) {
                cursor.moveToFirst();
                groceries.setText("$" + cursor.getString(1));
                groc2 = Double.parseDouble(cursor.getString(1));
                insurances.setText("$" + cursor.getString(2));
                ins2 = Double.parseDouble(cursor.getString(2));
                phone.setText("$" + cursor.getString(3));
                ph2 = Double.parseDouble(cursor.getString(3));
                rent.setText("$" + cursor.getString(4));
                rnt2 = Double.parseDouble(cursor.getString(4));
                eat.setText("$" + cursor.getString(5));
                et2 = Double.parseDouble(cursor.getString(5));
                shop.setText("$" + cursor.getString(6));
                shp2 = Double.parseDouble(cursor.getString(6));
                misc.setText("$" + cursor.getString(7));
                msc2  = Double.parseDouble(cursor.getString(7));
                totalBudget = groc2+ins2+ph2+rnt2+et2+shp2+msc2;
                monthlyBudget.setText("$" + totalBudget);
            }
        } catch (Exception ex) {
        }

        String query2 = "SELECT * FROM " + UserEntry.TABLE_NAME_3;
        List<String> list = new ArrayList<>();
        try {
            Cursor cursor = rdb.rawQuery(query2, null);
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
                        groc += Double.parseDouble(list.get(0));
                    else if (s == "Insurance")
                        ins += Double.parseDouble(list.get(0));
                    else if (s == "Phone Bill")
                        ph += Double.parseDouble(list.get(0));
                    else if (s == "Rent")
                        rnt += Double.parseDouble(list.get(0));
                    else if (s == "Dinning Out")
                        et += Double.parseDouble(list.get(0));
                    else if (s == "Shopping")
                        shp += Double.parseDouble(list.get(0));
                    else if (s == "Miscellaneous")
                        msc += Double.parseDouble(list.get(0));

                    totalExpenditure += Double.parseDouble(list.get(0));
                    list.clear();

                } while (cursor.moveToNext());

                currentExpenditure.setText("$" + totalExpenditure);
                groceries2.setText("$" + groc);
                insurances2.setText("$" + ins);
                phone2.setText("$" + ph);
                rent2.setText("$" + rnt);
                eat2.setText("$" + et);
                shop2.setText("$" + shp);
                misc2.setText("$" + msc);

                progressgroc.setProgress(checkProgress(groc2,groc));
                progressinc.setProgress(checkProgress(ins2,ins));
                progressphn.setProgress(checkProgress(ph2,ph));
                progressrent.setProgress(checkProgress(rnt2,rnt));
                progresseat.setProgress(checkProgress(et2,et));
                progressshop.setProgress(checkProgress(shp2,shp));
                progressmisc.setProgress(checkProgress(msc2,msc));
                ProgressExceeded(progressgroc);
                ProgressExceeded(progressinc);
                ProgressExceeded(progressphn);
                ProgressExceeded(progressrent);
                ProgressExceeded(progresseat);
                ProgressExceeded(progressshop);
                ProgressExceeded(progressmisc);

                cursor.close();
            }
        } catch (Exception ex) {
        }
    }

    public int checkProgress(Double x,Double y) {
        if(y == 0)
            return 0;
        else {
            double p = (y/x)*100;
            int fp = (int) p;
            if(fp <= 0)
                return 0;
            else if (fp > 100)
                return 100;
            else
            return fp;
        }

    }

    public void ProgressExceeded(ProgressBar p){
        if(p.getProgress()>=50 && p.getProgress() <= 99)
        {
            p.setProgressTintList(ColorStateList.valueOf(Color.argb(100,253,95,0)));
        }
        else if(p.getProgress()==100)
        {
            p.setProgressTintList(ColorStateList.valueOf(Color.RED));
        }
    }
}
