package com.hatchers.ruralcaravane.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;


import com.hatchers.ruralcaravane.CustomerRegistration.CustomerRegistrationActivity;
import com.hatchers.ruralcaravane.KitchenSuitability.kitchen_Suitability_Fragment;
import com.hatchers.ruralcaravane.R;


public class MenuFragment extends Fragment implements View.OnClickListener{

    private  FragmentTransaction fragmentTransaction;
    private Button btnContinue;
    private LinearLayout customer_linear,kitchen_linear,
            construction_linear,payment_linear;


    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_menu, container, false);

        customer_linear=(LinearLayout)view.findViewById(R.id.customer_linear);
        kitchen_linear=(LinearLayout)view.findViewById(R.id.kitchen_linear);
        construction_linear=(LinearLayout)view.findViewById(R.id.construction_linear);
        payment_linear=(LinearLayout)view.findViewById(R.id.payment_linear);
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        customer_linear.setOnClickListener(this);
        kitchen_linear.setOnClickListener(this);
        construction_linear.setOnClickListener(this);
        payment_linear.setOnClickListener(this);


        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window =getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.DarkBrown));
        }

        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.customer_linear:
                Intent intent=new Intent(getActivity(), CustomerRegistrationActivity.class);
                startActivity(intent);
                 break;

            case R.id.kitchen_linear:
                kitchen_Suitability_Fragment kitchenSuitabilityFragment=new  kitchen_Suitability_Fragment();
                fragmentTransaction.replace(R.id.frame_layout,kitchenSuitabilityFragment).addToBackStack(null).commit();
                break;

            case R.id.construction_linear:

                break;

            case R.id.payment_linear:

                break;

            default:
                break;
        }
    }
}
