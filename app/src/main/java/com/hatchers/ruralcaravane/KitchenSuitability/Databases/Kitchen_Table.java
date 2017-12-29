package com.hatchers.ruralcaravane.KitchenSuitability.Databases;

/**
 * Created by Nikam on 28/12/2017.
 */
public class Kitchen_Table {


    public static final String KITCHEN_TABLE="Kitchen_Table";

    public static final String KITCHEN_ID="kitchen_id",HOUSE_TYPE="house_type"
                               ,ROOF_TYPE="roof_type",KITCHEN_HEIGHT="kitchen_height";

    public static final String CREATE_KITCHEN_TABLE="CREATE TABLE " + KITCHEN_TABLE + "("+KITCHEN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+HOUSE_TYPE+" TEXT,"+ROOF_TYPE+" TEXT,"+KITCHEN_HEIGHT+" INTEGER)";

    private String kitchen_idValue,house_typeValue,roof_typeValue,kitchen_heightValue;

    public Kitchen_Table() {
    }

    public Kitchen_Table(String kitchen_idValue, String house_typeValue, String roof_typeValue, String kitchen_heightValue) {
        this.kitchen_idValue = kitchen_idValue;
        this.house_typeValue = house_typeValue;
        this.roof_typeValue = roof_typeValue;
        this.kitchen_heightValue = kitchen_heightValue;
    }

    public String getKitchen_idValue() {
        return kitchen_idValue;
    }

    public void setKitchen_idValue(String kitchen_idValue) {
        this.kitchen_idValue = kitchen_idValue;
    }

    public String getHouse_typeValue() {
        return house_typeValue;
    }

    public void setHouse_typeValue(String house_typeValue) {
        this.house_typeValue = house_typeValue;
    }

    public String getRoof_typeValue() {
        return roof_typeValue;
    }

    public void setRoof_typeValue(String roof_typeValue) {
        this.roof_typeValue = roof_typeValue;
    }

    public String getKitchen_heightValue() {
        return kitchen_heightValue;
    }

    public void setKitchen_heightValue(String kitchen_heightValue) {
        this.kitchen_heightValue = kitchen_heightValue;
    }
}
