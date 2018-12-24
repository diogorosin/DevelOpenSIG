package br.com.developen.sig;

import android.os.Build;
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
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;

import com.google.android.gms.maps.model.LatLng;

import br.com.developen.sig.database.modified.ModifiedAddressEdificationModel;
import br.com.developen.sig.task.UpdateAddressLocationAsynTask;
import br.com.developen.sig.util.Messaging;

public class AddressActivity extends AppCompatActivity
        implements LocationFragment.LocationListener,
        EdificationFragment.EdificationFragmentListener,
        UpdateAddressLocationAsynTask.Listener {


//    int[] colorIntArray = {R.color.colorGreyDark,R.color.colorGreyDark,R.color.colorGreyDark};

//    int[] iconIntArray = {R.drawable.icon_add_24,R.drawable.icon_add_24,R.drawable.icon_add_24};

    private SectionsPagerAdapter sectionsPagerAdapter;

    private FloatingActionButton floatingActionButton;

    private ViewPager viewPager;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_address);

        Toolbar toolbar = findViewById(R.id.activity_address_toolbar);

        setSupportActionBar(toolbar);

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.activity_address_container);

        viewPager.setAdapter(sectionsPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            public void onPageSelected(int position) {

                floatingActionButton.setVisibility(position==2?View.VISIBLE:View.GONE);

            }
            public void onPageScrollStateChanged(int state) {}
        });

        final TabLayout tabLayout = findViewById(R.id.activity_address_tabs);

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        floatingActionButton = findViewById(R.id.activity_address_fab);

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


    public void onEdificationClicked(ModifiedAddressEdificationModel modifiedAddressEdificationModel) {}


    public void onEdificationLongClick(ModifiedAddressEdificationModel modifiedAddressEdificationModel) {}


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

            }

            return f;

        }

        public int getCount() {

            return 3;

        }

    }

    /*protected void animateFab(final int position) {

        floatingActionButton.clearAnimation();

        ScaleAnimation shrink =  new ScaleAnimation(1f, 0.2f, 1f, 0.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        shrink.setDuration(150);

        shrink.setInterpolator(new DecelerateInterpolator());

        shrink.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {}

            public void onAnimationEnd(Animation animation) {

                floatingActionButton.setBackgroundTintList(getResources().getColorStateList(colorIntArray[position]));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)

                    floatingActionButton.setImageDrawable(getResources().getDrawable(iconIntArray[position], null));

                ScaleAnimation expand = new ScaleAnimation(0.2f, 1f, 0.2f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

                expand.setDuration(100);

                expand.setInterpolator(new AccelerateInterpolator());

                floatingActionButton.startAnimation(expand);

            }

            public void onAnimationRepeat(Animation animation) {}

        });

        floatingActionButton.startAnimation(shrink);

    }*/

}