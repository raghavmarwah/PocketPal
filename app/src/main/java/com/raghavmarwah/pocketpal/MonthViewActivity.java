package com.raghavmarwah.pocketpal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MonthViewActivity extends AppCompatActivity {

    private TextView mTextMessage;
    LinearLayout homeLayout;
    RelativeLayout analystLayout;
    RelativeLayout calendarLayout;
    RelativeLayout profileLayout;
    MyDB db = new MyDB(this);

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigationHome:
                    homeLayout.setVisibility(View.VISIBLE);
                    analystLayout.setVisibility(View.INVISIBLE);
                    calendarLayout.setVisibility(View.INVISIBLE);
                    profileLayout.setVisibility(View.INVISIBLE);
                    refreshEverything();
                    return true;
                case R.id.navigationAnalyst:
                    homeLayout.setVisibility(View.INVISIBLE);
                    analystLayout.setVisibility(View.VISIBLE);
                    calendarLayout.setVisibility(View.INVISIBLE);
                    profileLayout.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigationCalendar:
                    homeLayout.setVisibility(View.INVISIBLE);
                    analystLayout.setVisibility(View.INVISIBLE);
                    calendarLayout.setVisibility(View.VISIBLE);
                    profileLayout.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigationProfile:
                    homeLayout.setVisibility(View.INVISIBLE);
                    analystLayout.setVisibility(View.INVISIBLE);
                    calendarLayout.setVisibility(View.INVISIBLE);
                    profileLayout.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_view);
        refreshEverything();

        //Bottom navigation bar
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        //Layout Variables
        homeLayout = (LinearLayout) findViewById(R.id.homeLayout);
        analystLayout = (RelativeLayout) findViewById(R.id.analystLayout);
        calendarLayout = (RelativeLayout) findViewById(R.id.calendarLayout);
        profileLayout = (RelativeLayout) findViewById(R.id.profileLayout);

        //Object Variables
        Button addExpenditure = (Button) findViewById(R.id.addExpButton);
        Button viewExpenditure = (Button) findViewById(R.id.viewExpButton);

        //onClick listeners
        addExpenditure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MonthViewActivity.this, AddExpenseActivity.class);
                startActivity(intent);

            }
        });
        viewExpenditure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MonthViewActivity.this, ViewExpenseActivity.class);
                startActivity(intent);

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

        final SQLiteDatabase rdb = db.getReadableDatabase();
        String query = "SELECT * FROM Expenditures";
        try {
            Cursor cursor = rdb.rawQuery(query, null);
            if (cursor != null) {
                cursor.moveToFirst();
                groceries.setText("$"+cursor.getString(9));
                insurances.setText("$"+cursor.getString(10));
                phone.setText("$"+cursor.getString(11));
                rent.setText("$"+cursor.getString(12));
                eat.setText("$"+cursor.getString(13));
                shop.setText("$"+cursor.getString(14));
                misc.setText("$"+cursor.getString(15));
            }
        } catch (Exception ex) {
        }
    }
}
