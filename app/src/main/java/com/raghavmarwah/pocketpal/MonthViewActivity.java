package com.raghavmarwah.pocketpal;

import android.content.Intent;
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

public class MonthViewActivity extends AppCompatActivity {

    private TextView mTextMessage;
    LinearLayout homeLayout;
    RelativeLayout analystLayout;
    RelativeLayout calendarLayout;
    RelativeLayout profileLayout;

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

}
