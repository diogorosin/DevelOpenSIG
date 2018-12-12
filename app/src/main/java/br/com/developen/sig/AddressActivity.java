package br.com.developen.sig;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import br.com.developen.sig.task.UpdateAddressLocationAsynTask;
import br.com.developen.sig.util.Messaging;

public class AddressActivity extends AppCompatActivity
        implements LocationFragment.LocationListener, UpdateAddressLocationAsynTask.Listener {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_address);

        Toolbar toolbar = findViewById(R.id.activity_address_toolbar);

        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.activity_address_container);

        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.activity_address_tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }


    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_add, menu);

        return true;

    }


    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings)

            return true;

        return super.onOptionsItemSelected(item);

    }


    public void onPointerCaptureChanged(boolean hasCapture) {}


    public void onLocationChanged(LatLng latLng) {

        new UpdateAddressLocationAsynTask<>(this).execute(
                getIntent().getIntExtra(MapActivity.MODIFIED_ADDRESS_IDENTIFIER,0),
                latLng.latitude,
                latLng.longitude);

    }


    public void onUpdateAddressLocationSuccess() {}


    public void onUpdateAddressLocationFailure(Messaging messaging) {}


    /*public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {}

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_address, container, false);
            TextView textView = rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

    }*/


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {

            super(fm);

        }

        public Fragment getItem(int position) {

            Fragment f = null;

            switch (position){

                case 0:

                    f = LocationFragment.newInstance(AddressActivity.this,
                            getIntent().getIntExtra(MapActivity.MODIFIED_ADDRESS_IDENTIFIER, 0));

                    break;

                case 1:

                    f = AddressFragment.newInstance(getIntent().
                            getIntExtra(MapActivity.MODIFIED_ADDRESS_IDENTIFIER, 0));

                    break;

                case 2:

                    f = EdificationFragment.newInstance(getIntent().
                            getIntExtra(MapActivity.MODIFIED_ADDRESS_IDENTIFIER, 0));

                    break;

       /*         default:

                    f = PlaceholderFragment.newInstance(position + 1); */

            }

            return f;

        }

        public int getCount() {

            return 3;

        }

    }


}