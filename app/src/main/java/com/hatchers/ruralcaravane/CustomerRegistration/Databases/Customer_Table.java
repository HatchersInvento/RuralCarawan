package com.hatchers.ruralcaravane.CustomerRegistration.Databases;


public class Customer_Table {

    public static final String CUSTOMER_TABLE = "Customer_Table";

    public static final String CUSTOMER_ID = "customer_id",CUSTOMER_NAME="customer_name",
            VILLAGE_NAME="village_name",CUSTOMER_ADDRESS="customer_address",
            CUSTOMER_MOBILENO="customer_mobileno",CUSTOMER_AGE="customer_age",
            CUSTOMER_GENDER="customer_gender";

    public static final String CREATE_CUSTOMER_TABLE="CREATE TABLE " + CUSTOMER_TABLE+ "("+CUSTOMER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CUSTOMER_NAME+" TEXT,"+CUSTOMER_ADDRESS+" TEXT,"+CUSTOMER_AGE+" INTEGER,"+CUSTOMER_GENDER+" TEXT,"+CUSTOMER_MOBILENO+" INTEGER,"+VILLAGE_NAME+" TEXT)";

    private String customerIdValue,customerNameValue,villageNameValue,customerAddressValue,
            customerMobilenoValue,customerAgeValue,customerGenderValue;


    public Customer_Table() {
    }

    public Customer_Table(String customerIdValue, String customerNameValue,
                          String villageNameValue, String customerAddressValue,
                          String customerMobilenoValue, String customerAgeValue,
                          String customerGenderValue)
    {
        this.customerIdValue = customerIdValue;
        this.customerNameValue = customerNameValue;
        this.villageNameValue = villageNameValue;
        this.customerAddressValue = customerAddressValue;
        this.customerMobilenoValue = customerMobilenoValue;
        this.customerAgeValue = customerAgeValue;
        this.customerGenderValue = customerGenderValue;
    }

    public String getCustomerIdValue() {
        return customerIdValue;
    }

    public void setCustomerIdValue(String customerIdValue) {
        this.customerIdValue = customerIdValue;
    }

    public String getCustomerNameValue() {
        return customerNameValue;
    }

    public void setCustomerNameValue(String customerNameValue) {
        this.customerNameValue = customerNameValue;
    }

    public String getVillageNameValue() {
        return villageNameValue;
    }

    public void setVillageNameValue(String villageNameValue) {
        this.villageNameValue = villageNameValue;
    }

    public String getCustomerAddressValue() {
        return customerAddressValue;
    }

    public void setCustomerAddressValue(String customerAddressValue) {
        this.customerAddressValue = customerAddressValue;
    }

    public String getCustomerMobilenoValue() {
        return customerMobilenoValue;
    }

    public void setCustomerMobilenoValue(String customerMobilenoValue) {
        this.customerMobilenoValue = customerMobilenoValue;
    }

    public String getCustomerAgeValue() {
        return customerAgeValue;
    }

    public void setCustomerAgeValue(String customerAgeValue) {
        this.customerAgeValue = customerAgeValue;
    }

    public String getCustomerGenderValue() {
        return customerGenderValue;
    }

    public void setCustomerGenderValue(String customerGenderValue) {
        this.customerGenderValue = customerGenderValue;
    }
}
