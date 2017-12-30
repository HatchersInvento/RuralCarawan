package com.hatchers.ruralcaravane.CustomerRegistration;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hatchers.ruralcaravane.CustomerRegistration.Databases.Customer_Table;
import com.hatchers.ruralcaravane.CustomerRegistration.Databases.Customer_Table_Helper;
import com.hatchers.ruralcaravane.R;


public class AddCustomerFragment extends Fragment {

    Customer_Table customer_table;
    private String selected_gender = "";
    private Button save;
    private RadioGroup radioGroupGender;
    private RadioButton male, female;
    private EditText customer_name, village_name, customer_address, customer_mobileno, customer_age;


    public AddCustomerFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add__customer, container, false);

        initializations(view);
        setGender();
        onclicklisteners();

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.DarkBrown));
        }

        return view;
    }


    private void initializations(View view) {

        save = (Button) view.findViewById(R.id.saveBtn);
        customer_name = (EditText) view.findViewById(R.id.customer_name);
        village_name = (EditText) view.findViewById(R.id.village_name);
        customer_address = (EditText) view.findViewById(R.id.customer_address);
        customer_mobileno = (EditText) view.findViewById(R.id.customer_mobileno);
        customer_age = (EditText) view.findViewById(R.id.customer_age);
        radioGroupGender = (RadioGroup) view.findViewById(R.id.radio_gender);
        male = (RadioButton) view.findViewById(R.id.male);
        female = (RadioButton) view.findViewById(R.id.female);

    }

    private void onclicklisteners() {

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCustomerData();
                Customer_Table_Helper.insertAnswer(getContext(), customer_table);

            }
        });
    }

    private void setCustomerData() {

        customer_table = new Customer_Table();


        customer_table.setCustomerNameValue(customer_name.getText().toString());
        customer_table.setVillageNameValue(village_name.getText().toString());
        customer_table.setCustomerAddressValue(customer_address.getText().toString());
        customer_table.setCustomerMobilenoValue(customer_mobileno.getText().toString());
        customer_table.setCustomerAgeValue(customer_age.getText().toString());
        customer_table.setCustomerGenderValue(selected_gender);
    }

    public void setGender() {
        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.male) {
                    selected_gender = "M";
                } else if (checkedId == R.id.female) {
                    selected_gender = "F";
                }
            }
        });

    }


}
