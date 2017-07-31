package com.csis3175.pocketpal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddExpenseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        Bundle bundle = getIntent().getExtras();
        final String date = bundle.getString("date");
        TextView dateView = (TextView) findViewById(R.id.date);
        dateView.setText("Date: "+date);

        final EditText expenseAmount = (EditText) findViewById(R.id.expense_amount);
        final Spinner expenseType = (Spinner) findViewById(R.id.expense_type);


        MyDB db = new MyDB(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        Button addExpense = (Button) findViewById(R.id.addExpense);
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Double amount = Double.parseDouble(expenseAmount.getText().toString());
                String type = expenseType.getSelectedItem().toString();

                Log.d("D", type);

                addDataToDb(date, type, amount);
                Toast toast = Toast.makeText(getApplicationContext(),"Expense Added!!",Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        });
    }
    private void addDataToDb(String date, String type, Double amount){
        MyDB db = new MyDB(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserEntry.COLUMN_NAME_DATE, date);

        if(type.equals("Groceries")){
            values.put(UserEntry.COLUMN_NAME_GROCERIES_EXP, amount);
        }
        else{
            values.put(UserEntry.COLUMN_NAME_GROCERIES_EXP, 0);
        }

        if(type.equals("Insurances")){
            values.put(UserEntry.COLUMN_NAME_INSURANCES_EXP, amount);
        }
        else{
            values.put(UserEntry.COLUMN_NAME_INSURANCES_EXP, 0);
        }

        if(type.equals("Phone Bill")){
            values.put(UserEntry.COLUMN_NAME_BILLS_EXP, amount);
        }
        else{
            values.put(UserEntry.COLUMN_NAME_BILLS_EXP, 0);
        }

        if(type.equals("Rent")){
            values.put(UserEntry.COLUMN_NAME_RENT_EXP, amount);
        }
        else{
            values.put(UserEntry.COLUMN_NAME_RENT_EXP, 0);
        }

        if(type.equals("Eating Out")){
            values.put(UserEntry.COLUMN_NAME_EAT_EXP, amount);
        }
        else{
            values.put(UserEntry.COLUMN_NAME_EAT_EXP, 0);
        }

        if(type.equals("Shopping")){
            values.put(UserEntry.COLUMN_NAME_SHOP_EXP, amount);
        }
        else{
            values.put(UserEntry.COLUMN_NAME_SHOP_EXP, 0);
        }

        if(type.equals("Miscellaneous")){
            values.put(UserEntry.COLUMN_NAME_MISC_EXP, amount);
        }
        else{
            values.put(UserEntry.COLUMN_NAME_MISC_EXP, 0);
        }
        Log.d("d",values.toString());
        wdb.insert(UserEntry.TABLE_NAME_3, null, values);
    }
}
