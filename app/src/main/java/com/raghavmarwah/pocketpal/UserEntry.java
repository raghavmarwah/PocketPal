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
    public static final String IMAGE = "image";


    public static final String TABLE_NAME_2 = "Expenditures";
    public static final String COLUMN_NAME_DATE = "DATE";
    public static final String COLUMN_NAME_GROCERIES = "GROCERIES";
    public static final String COLUMN_NAME_INSURANCES = "INSURANCES";
    public static final String COLUMN_NAME_BILLS = "PHONE";
    public static final String COLUMN_NAME_RENT = "RENT";
    public static final String COLUMN_NAME_EAT = "EAT";
    public static final String COLUMN_NAME_SHOP = "SHOP";
    public static final String COLUMN_NAME_MISC = "MISC";
    public static final String COLUMN_NAME_GROCERIES_L = "GROCERIES_L";
    public static final String COLUMN_NAME_INSURANCES_L = "INSURANCES_L";
    public static final String COLUMN_NAME_BILLS_L = "PHONE_L";
    public static final String COLUMN_NAME_RENT_L = "RENT_L";
    public static final String COLUMN_NAME_EAT_L = "EAT_L";
    public static final String COLUMN_NAME_SHOP_L = "SHOP_L";
    public static final String COLUMN_NAME_MISC_L = "MISC_L";

}
