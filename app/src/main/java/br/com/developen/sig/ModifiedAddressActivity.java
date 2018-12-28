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

import com.google.android.gms.maps.model.LatLng;

import br.com.developen.sig.database.modified.ModifiedAddressEdificationModel;
import br.com.developen.sig.fragment.ModifiedAddressAddressFragment;
import br.com.developen.sig.fragment.ModifiedAddressEdificationFragment;
import br.com.developen.sig.fragment.ModifiedAddressLocationFragment;
import br.com.developen.sig.task.UpdateAddressLocationAsynTask;
import br.com.developen.sig.util.Messaging;

public class ModifiedAddressActivity extends AppCompatActivity
        implements ModifiedAddressLocationFragment.LocationListener,
        ModifiedAddressEdificationFragment.EdificationFragmentListener,
        UpdateAddressLocationAsynTask.Listener {


    public static final String MODIFIED_ADDRESS_IDENTIFIER = "ARG_MODIFIED_ADDRESS_IDENTIFIER";


    private SectionsPagerAdapter sectionsPagerAdapter;

    private FloatingActionButton floatingActionButton;

    private ViewPager viewPager;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_modified_address);

        Toolbar toolbar = findViewById(R.id.activity_modified_address_toolbar);

        setSupportActionBar(toolbar);

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.activity_modified_address_container);

        viewPager.setAdapter(sectionsPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            public void onPageSelected(int position) {

                floatingActionButton.setVisibility(position==2?View.VISIBLE:View.GONE);

            }

            public void onPageScrollStateChanged(int state) {}

        });

        final TabLayout tabLayout = findViewById(R.id.activity_modified_address_tabs);

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        floatingActionButton = findViewById(R.id.activity_modified_address_fab);

    }


    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_modified_address, menu);

        return true;

    }


    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_modified_address_action_settings)

            return true;

        return super.onOptionsItemSelected(item);

    }


    public void onPointerCaptureChanged(boolean hasCapture) {}


    public void onLocationChanged(LatLng latLng) {

        new UpdateAddressLocationAsynTask<>(this).execute(
                getIntent().getIntExtra(MODIFIED_ADDRESS_IDENTIFIER,0),
                latLng.latitude,
                latLng.longitude);

    }


    public void onUpdateAddressLocationSuccess() {}


    public void onUpdateAddressLocationFailure(Messaging messaging) {}


    public void onEdificationClicked(ModifiedAddressEdificationModel modifiedAddressEdificationModel) {

        Intent addIntent = new Intent(ModifiedAddressActivity.this, ModifiedAddressEdificationActivity.class);

        addIntent.putExtra(ModifiedAddressEdificationActivity.MODIFIED_ADDRESS_IDENTIFIER, modifiedAddressEdificationModel.
                getModifiedAddress().
                getIdentifier());

        addIntent.putExtra(ModifiedAddressEdificationActivity.EDIFICATION_IDENTIFIER, modifiedAddressEdificationModel.
                getEdification());

        startActivity(addIntent);

    }


    public void onEdificationLongClick(ModifiedAddressEdificationModel modifiedAddressEdificationModel) {}


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {

            super(fm);

        }

        public Fragment getItem(int position) {

            Fragment f = null;

            switch (position){

                case 0:

                    f = ModifiedAddressLocationFragment.newInstance(ModifiedAddressActivity.this,
                            getIntent().getIntExtra(MODIFIED_ADDRESS_IDENTIFIER, 0));

                    break;

                case 1:

                    f = ModifiedAddressAddressFragment.newInstance(getIntent().
                            getIntExtra(MODIFIED_ADDRESS_IDENTIFIER, 0));

                    break;

                case 2:

                    f = ModifiedAddressEdificationFragment.newInstance(getIntent().
                            getIntExtra(MODIFIED_ADDRESS_IDENTIFIER, 0));

                    break;

            }

            return f;

        }

        public int getCount() {

            return 3;

        }

    }


}