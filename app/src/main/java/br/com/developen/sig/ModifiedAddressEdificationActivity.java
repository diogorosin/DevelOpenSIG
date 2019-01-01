package br.com.developen.sig;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import br.com.developen.sig.database.ModifiedAddressEdificationDwellerModel;
import br.com.developen.sig.fragment.ModifiedAddressEdificationDwellerFragment;
import br.com.developen.sig.fragment.ModifiedAddressEdificationTypeFragment;

public class ModifiedAddressEdificationActivity extends AppCompatActivity
        implements ModifiedAddressEdificationDwellerFragment.ModifiedAddressEdificationDwellerFragmentListener {


    public static final String MODIFIED_ADDRESS_IDENTIFIER = "ARG_MODIFIED_ADDRESS_IDENTIFIER";

    public static final String EDIFICATION_IDENTIFIER = "ARG_EDIFICATION_IDENTIFIER";


    private SectionsPagerAdapter sectionsPagerAdapter;

    private FloatingActionButton floatingActionButton;

    private ViewPager viewPager;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_modified_address_edification);

        Toolbar toolbar = findViewById(R.id.activity_modified_address_edification_toolbar);

        setSupportActionBar(toolbar);

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.activity_modified_address_edification_container);

        viewPager.setAdapter(sectionsPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            public void onPageSelected(int position) {

                floatingActionButton.setVisibility(position==2?View.VISIBLE:View.GONE);

            }

            public void onPageScrollStateChanged(int state) {}

        });

        final TabLayout tabLayout = findViewById(R.id.activity_modified_address_edification_tabs);

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        floatingActionButton = findViewById(R.id.activity_modified_address_edification_fab);

    }


    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_modified_address_edification, menu);

        return true;

    }


    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_modified_address_edification_action_settings)

            return true;

        return super.onOptionsItemSelected(item);

    }


    public void onPointerCaptureChanged(boolean hasCapture) {}


    public void onDwellerClicked(ModifiedAddressEdificationDwellerModel modifiedAddressEdificationDwellerModel) {

        Intent dwellerIntent = new Intent(ModifiedAddressEdificationActivity.this, ModifiedAddressEdificationDwellerActivity.class);

        dwellerIntent.putExtra(ModifiedAddressEdificationDwellerActivity.MODIFIED_ADDRESS_IDENTIFIER,
                modifiedAddressEdificationDwellerModel.
                        getModifiedAddressEdification().
                        getModifiedAddress().
                        getIdentifier());

        dwellerIntent.putExtra(ModifiedAddressEdificationDwellerActivity.EDIFICATION_IDENTIFIER,
                modifiedAddressEdificationDwellerModel.
                        getModifiedAddressEdification().
                        getEdification());

        dwellerIntent.putExtra(ModifiedAddressEdificationDwellerActivity.DWELLER_IDENTIFIER,
                modifiedAddressEdificationDwellerModel.
                        getDweller());

        startActivity(dwellerIntent);

    }


    public void onDwellerLongClick(ModifiedAddressEdificationDwellerModel modifiedAddressEdificationDwellerModel) {}


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {

            super(fm);

        }

        public Fragment getItem(int position) {

            Fragment f = null;

            switch (position){

                case 0:

                    f = ModifiedAddressEdificationTypeFragment.newInstance(
                            getIntent().getIntExtra(MODIFIED_ADDRESS_IDENTIFIER, 0),
                            getIntent().getIntExtra(EDIFICATION_IDENTIFIER, 0));

                    break;

                case 1:

                    f = ModifiedAddressEdificationDwellerFragment.newInstance(
                            getIntent().getIntExtra(MODIFIED_ADDRESS_IDENTIFIER, 0),
                            getIntent().getIntExtra(EDIFICATION_IDENTIFIER, 0));

                    break;

            }

            return f;

        }

        public int getCount() {

            return 2;

        }

    }


}