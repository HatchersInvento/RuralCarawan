package com.hatchers.ruralcaravane.KitchenSuitability.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.hatchers.ruralcaravane.Databases.AssetDatabaseHelper;
import com.hatchers.ruralcaravane.Databases.DatabaseHandler;

import java.util.ArrayList;

/**
 * Created by Nikam on 28/12/2017.
 */
public class Kitchen_Table_Helper {

    public static boolean insertKitchenData(Context context, Kitchen_Table kitchen_table)
    {
        try {
            SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();
            ContentValues values = new ContentValues();

            try {
                values.put(Kitchen_Table.KITCHEN_ID,kitchen_table.getKitchen_idValue());
                values.put(Kitchen_Table.KITCHEN_HEIGHT,kitchen_table.getKitchen_heightValue());
               }
            catch(Exception e)
            {

            }

            values.put(Kitchen_Table.HOUSE_TYPE,kitchen_table.getHouse_typeValue());
            values.put(Kitchen_Table.ROOF_TYPE,kitchen_table.getRoof_typeValue());


            if (db.insert(Kitchen_Table.KITCHEN_TABLE, null, values) > 0)
            {
                Toast.makeText(context,"KItchen data inserted",Toast.LENGTH_LONG).show();
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


    public static boolean updateKitchenData(Context context, Kitchen_Table kitchen_table)
    {
        try {
            SQLiteDatabase db =  new DatabaseHandler(context).getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(Kitchen_Table.KITCHEN_ID,kitchen_table.getKitchen_idValue());
            values.put(Kitchen_Table.KITCHEN_HEIGHT,kitchen_table.getKitchen_heightValue());
            values.put(Kitchen_Table.HOUSE_TYPE,kitchen_table.getHouse_typeValue());
            values.put(Kitchen_Table.ROOF_TYPE,kitchen_table.getRoof_typeValue());

            // upadating Row
            if(db.update(Kitchen_Table.KITCHEN_TABLE, values, Kitchen_Table.KITCHEN_ID+"="+kitchen_table.getKitchen_idValue(), null)>0)
            {
                Toast.makeText(context,"Kitchen data updated",Toast.LENGTH_LONG).show();
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

    public static ArrayList<Kitchen_Table> getKitchenDataList(Context context)
    {
        ArrayList<Kitchen_Table> customerTableArrayList = new ArrayList<Kitchen_Table>();
        SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();
        // Cursor cursor = db.rawQuery("SELECT * FROM " + Message_Table.TABLE_MESSAGE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM "+Kitchen_Table.KITCHEN_TABLE,null);
        try
        {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false)
            {
                Kitchen_Table kitchen_table=new Kitchen_Table();

                kitchen_table.setKitchen_idValue(cursor.getString(cursor.getColumnIndex(Kitchen_Table.KITCHEN_ID)));
                kitchen_table.setHouse_typeValue(cursor.getString(cursor.getColumnIndex(Kitchen_Table.HOUSE_TYPE)));
                kitchen_table.setRoof_typeValue(cursor.getString(cursor.getColumnIndex(Kitchen_Table.ROOF_TYPE)));
                kitchen_table.setKitchen_heightValue(cursor.getString(cursor.getColumnIndex(Kitchen_Table.KITCHEN_HEIGHT)));

                customerTableArrayList.add(kitchen_table);
                cursor.moveToNext();
            }
            return customerTableArrayList;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static boolean deleteKitchenById(Context context, String kitchen_id)
    {
        try {
            SQLiteDatabase db =  new DatabaseHandler(context).getWritableDatabase();

            db.execSQL("DELETE FROM " + Kitchen_Table.KITCHEN_TABLE +
                    " WHERE " + Kitchen_Table.KITCHEN_ID + " = '" + kitchen_id + "'");
            //delete all rows in titlebackground table

            Toast.makeText(context, "Kitchen Data Deleted Successfully", Toast.LENGTH_SHORT).show();


            db.close();


            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean deleteKitchenData(Context context)
    {
        try {
            SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();

            db.execSQL("DELETE FROM " + Kitchen_Table.KITCHEN_TABLE ); //delete all rows in titlebackground table


            Toast.makeText(context,"Kitchen data Deleted Successfully",Toast.LENGTH_SHORT).show();


            db.close();


            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
