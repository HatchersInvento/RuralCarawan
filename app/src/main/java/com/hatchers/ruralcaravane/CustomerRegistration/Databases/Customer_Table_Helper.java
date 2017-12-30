package com.hatchers.ruralcaravane.CustomerRegistration.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.hatchers.ruralcaravane.Databases.AssetDatabaseHelper;
import com.hatchers.ruralcaravane.Databases.DatabaseHandler;

import java.util.ArrayList;


public class Customer_Table_Helper {

    public static boolean insertAnswer(Context context, Customer_Table customer_table)
    {
        try {
            SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();
            ContentValues values = new ContentValues();

            try {
                values.put(Customer_Table.CUSTOMER_ID, customer_table.getCustomerIdValue());
                values.put(Customer_Table.CUSTOMER_MOBILENO, customer_table.getCustomerMobilenoValue());
                values.put(Customer_Table.CUSTOMER_AGE, customer_table.getCustomerAgeValue());
            }
            catch(Exception e)
            {

            }

            values.put(Customer_Table.CUSTOMER_NAME,customer_table.getCustomerNameValue());
            values.put(Customer_Table.VILLAGE_NAME,customer_table.getVillageNameValue());
            values.put(Customer_Table.CUSTOMER_ADDRESS,customer_table.getCustomerAddressValue());
            values.put(Customer_Table.CUSTOMER_GENDER,customer_table.getCustomerGenderValue());


            if (db.insert(Customer_Table.CUSTOMER_TABLE, null, values) > 0)
            {
                Toast.makeText(context,"Customer data inserted",Toast.LENGTH_LONG).show();
                db.close();
                return true;
            }
            else
            {
                db.close();
                return false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean updateAnswer(Context context, Customer_Table customer_table)
    {
        try {
            SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(Customer_Table.CUSTOMER_ID, Integer.parseInt(customer_table.getCustomerIdValue()));
            values.put(Customer_Table.CUSTOMER_MOBILENO, Integer.parseInt(customer_table.getCustomerMobilenoValue()));
            values.put(Customer_Table.CUSTOMER_AGE, Integer.parseInt(customer_table.getCustomerAgeValue()));
            values.put(Customer_Table.CUSTOMER_NAME,customer_table.getCustomerNameValue());
            values.put(Customer_Table.CUSTOMER_ADDRESS,customer_table.getCustomerAddressValue());
            values.put(Customer_Table.VILLAGE_NAME,customer_table.getVillageNameValue());
            values.put(Customer_Table.CUSTOMER_ADDRESS,customer_table.getCustomerAddressValue());
            values.put(Customer_Table.CUSTOMER_GENDER,customer_table.getCustomerGenderValue());



            // upadating Row
            if(db.update(Customer_Table.CUSTOMER_TABLE, values, Customer_Table.CUSTOMER_ID+"="+customer_table.getCustomerIdValue(), null)>0)
            {
                Toast.makeText(context,"Customer data updated",Toast.LENGTH_LONG).show();
                db.close();
                return true;
            }
            else
            {
                db.close();
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }
    }


    public static Customer_Table getAnswerdata(Context context)
    {
        SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();
        // Cursor cursor = db.rawQuery("SELECT * FROM " + Message_Table.TABLE_MESSAGE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM "+Customer_Table.CUSTOMER_TABLE+" WHERE "+Customer_Table.CUSTOMER_ID,null);
        try
        {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false)
            {
               Customer_Table customer = new Customer_Table();

                customer.setCustomerIdValue(cursor.getString(cursor.getColumnIndex(Customer_Table.CUSTOMER_ID)));
                customer.setCustomerNameValue(cursor.getString(cursor.getColumnIndex(Customer_Table.CUSTOMER_NAME)));
                customer.setCustomerAddressValue(cursor.getString(cursor.getColumnIndex(Customer_Table.CUSTOMER_ADDRESS)));
                customer.setCustomerMobilenoValue(cursor.getString(cursor.getColumnIndex(Customer_Table.CUSTOMER_MOBILENO)));
                customer.setCustomerAgeValue(cursor.getString(cursor.getColumnIndex(Customer_Table.CUSTOMER_AGE)));
                customer.setCustomerGenderValue(cursor.getString(cursor.getColumnIndex(Customer_Table.CUSTOMER_GENDER)));
                customer.setVillageNameValue(cursor.getString(cursor.getColumnIndex(Customer_Table.VILLAGE_NAME)));

                cursor.moveToNext();
                return  customer;
            }
            return null;
        }
        catch (Exception e)

        {
            return null;
        }
    }


    public static ArrayList<Customer_Table> getCustomerdataList(Context context)
    {
        ArrayList<Customer_Table> customerTableArrayList = new ArrayList<Customer_Table>();
        SQLiteDatabase db =  new DatabaseHandler(context).getWritableDatabase();
        // Cursor cursor = db.rawQuery("SELECT * FROM " + Message_Table.TABLE_MESSAGE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM "+Customer_Table.CUSTOMER_TABLE,null);
        try
        {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false)
            {
                Customer_Table customer = new Customer_Table();

                customer.setCustomerIdValue(cursor.getString(cursor.getColumnIndex(Customer_Table.CUSTOMER_ID)));
                customer.setCustomerNameValue(cursor.getString(cursor.getColumnIndex(Customer_Table.CUSTOMER_NAME)));
                customer.setCustomerAddressValue(cursor.getString(cursor.getColumnIndex(Customer_Table.CUSTOMER_ADDRESS)));
                customer.setCustomerMobilenoValue(cursor.getString(cursor.getColumnIndex(Customer_Table.CUSTOMER_MOBILENO)));
                customer.setCustomerAgeValue(cursor.getString(cursor.getColumnIndex(Customer_Table.CUSTOMER_AGE)));
                customer.setCustomerGenderValue(cursor.getString(cursor.getColumnIndex(Customer_Table.CUSTOMER_GENDER)));
                customer.setVillageNameValue(cursor.getString(cursor.getColumnIndex(Customer_Table.VILLAGE_NAME)));

                customerTableArrayList.add(customer);
                cursor.moveToNext();
            }
            return customerTableArrayList;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static boolean deleteAnswerById(Context context, String customer_id)
    {
        try {
            SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();

            db.execSQL("DELETE FROM " + Customer_Table.CUSTOMER_TABLE +
                    " WHERE " + Customer_Table.CUSTOMER_ID + " = '" + customer_id + "'");
            //delete all rows in titlebackground table

            Toast.makeText(context, "Customer Data Deleted Successfully", Toast.LENGTH_SHORT).show();


            db.close();


            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean deleteAnswerData(Context context)
    {
        try {
            SQLiteDatabase db =  new DatabaseHandler(context).getWritableDatabase();

            db.execSQL("DELETE FROM " + Customer_Table.CUSTOMER_TABLE ); //delete all rows in titlebackground table


            Toast.makeText(context,"Answer data Deleted Successfully",Toast.LENGTH_SHORT).show();


            db.close();


            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
