package com.hatchers.ruralcaravane.CustomerRegistration;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.hatchers.ruralcaravane.R;


public class CustomerRegistrationActivity extends AppCompatActivity
{

    private Toolbar toolbar;
    private TabLayout tabLayout;
    public static ViewPager viewPager;

    private int[] tabIcons = {
            R.drawable.customer_list,
            R.drawable.add_customer
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        tabLayout = (TabLayout)findViewById(R.id.tabs_layout);

        initializations();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        SetupViewPager();
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    private void initializations() {
        toolbar=(Toolbar)findViewById(R.id.customer_toolbar);
    }

  private void SetupViewPager()
{
    CustomerAdapter adapter = new CustomerAdapter(getSupportFragmentManager());
    adapter.addFragment(new CustomerListFragment(), "Customer List");
    adapter.addFragment(new AddCustomerFragment(), "Add Customer");
    viewPager.setAdapter(adapter);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menus, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.search:

                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
