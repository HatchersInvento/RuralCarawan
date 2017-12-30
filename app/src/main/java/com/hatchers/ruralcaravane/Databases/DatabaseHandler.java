package com.hatchers.ruralcaravane.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hatchers.ruralcaravane.CustomerRegistration.Databases.Customer_Table;
import com.hatchers.ruralcaravane.KitchenSuitability.Databases.Kitchen_Table;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
        Context context;
    // Database Name
    public static final String DATABASE_NAME = "rural_caravane";

    // Contacts table name

    public DatabaseHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL(Kitchen_Table.CREATE_KITCHEN_TABLE);
        db.execSQL(Customer_Table.CREATE_CUSTOMER_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed

        db.execSQL("DROP TABLE IF EXISTS "+ Customer_Table.CUSTOMER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ Kitchen_Table.KITCHEN_TABLE);

        onCreate(db);
    }

}