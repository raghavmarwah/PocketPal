package com.csis3175.pocketpal;

import android.provider.BaseColumns;


public class UserEntry implements BaseColumns{

    public static final String TABLE_NAME = "User";
    public static final String TABLE_NAME_2 = "Budget";
    public static final String TABLE_NAME_3 = "Expenditures";

    public static final String COLUMN_NAME_USRNAME = "NAME";
    public static final String COLUMN_NAME_EMAIL = "EMAIL";
    public static final String COLUMN_NAME_INCOME = "INCOME";
    public static final String IMAGE = "image";

    public static final String COLUMN_NAME_GROCERIES = "GROCERIES";
    public static final String COLUMN_NAME_INSURANCES = "INSURANCES";
    public static final String COLUMN_NAME_BILLS = "PHONE";
    public static final String COLUMN_NAME_RENT = "RENT";
    public static final String COLUMN_NAME_EAT = "EAT";
    public static final String COLUMN_NAME_SHOP = "SHOP";
    public static final String COLUMN_NAME_MISC = "MISC";

    public static final String COLUMN_NAME_DATE = "DATE";
    public static final String COLUMN_NAME_GROCERIES_EXP = "GROCERIES_EXP";
    public static final String COLUMN_NAME_INSURANCES_EXP = "INSURANCES_EXP";
    public static final String COLUMN_NAME_BILLS_EXP = "PHONE_EXP";
    public static final String COLUMN_NAME_RENT_EXP = "RENT_EXP";
    public static final String COLUMN_NAME_EAT_EXP = "EAT_EXP";
    public static final String COLUMN_NAME_SHOP_EXP = "SHOP_EXP";
    public static final String COLUMN_NAME_MISC_EXP = "MISC_EXP";

}
