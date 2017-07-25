package com.raghavmarwah.pocketpal;

import android.provider.BaseColumns;

/**
 * Created by xerox on 2017-07-24.
 */

public class UserEntry implements BaseColumns{

    public static final String TABLE_NAME = "Entry";
    public static final String COLUMN_NAME_USRNAME = "NAME";
    public static final String COLUMN_NAME_EMAIL = "EMAIL";
    public static final String COLUMN_NAME_INCOME = "INCOME";


    public static final String TABLE_NAME_2 = "Expenditures";
    public static final String COLUMN_NAME_DATE = "DATE";
    public static final String COLUMN_NAME_GROCERIES = "GROCERIES";
    public static final String COLUMN_NAME_INSURANCES = "INSURANCES";
    public static final String COLUMN_NAME_BILLS = "PHONE BILLS";
    public static final String COLUMN_NAME_RENT = "RENT";
    public static final String COLUMN_NAME_EAT = "EATING OUT";
    public static final String COLUMN_NAME_SHOP = "SHOPPING";
    public static final String COLUMN_NAME_MISC = "MISCELLANEOUS";

}
