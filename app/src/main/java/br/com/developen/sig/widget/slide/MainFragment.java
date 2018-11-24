package br.com.developen.sig.widget.slide;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListView;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import br.com.developen.sig.R;

public class MainFragment extends Fragment implements
        SlidingUpPanelLayout.PanelSlideListener,
        OnMapReadyCallback,
        LocationListener,
        HeaderAdapter.ItemClickListener {

    private static final String ARG_LOCATION = "arg.location";

    private LockableRecyclerView mListView;
    private SlidingUpPanelLayout mSlidingUpPanelLayout;
    private View mTransparentView;
    private HeaderAdapter mHeaderAdapter;
    private LatLng mLocation;
    private Marker mLocationMarker;
    private SupportMapFragment mMapFragment;
    private GoogleMap mMap;
    private boolean mIsNeedLocationUpdate = true;
    private LocationRequest mLocationRequest;

    public MainFragment() {}

    public static MainFragment newInstance(LatLng location) {

        MainFragment f = new MainFragment();

        Bundle args = new Bundle();

        args.putParcelable(ARG_LOCATION, location);

        f.setArguments(args);

        return f;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mListView = rootView.findViewById(android.R.id.list);
        mListView.setOverScrollMode(ListView.OVER_SCROLL_NEVER);


        final SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager()
                .findFragmentById(R.id.mapContainer);

        mapFragment.getMapAsync(this);

        mSlidingUpPanelLayout = rootView.findViewById(R.id.slidingLayout);
        mSlidingUpPanelLayout.setEnableDragViewTouchEvents(true);

        int mapHeight = getResources().getDimensionPixelSize(R.dimen.map_height);
        mSlidingUpPanelLayout.setPanelHeight(mapHeight); // you can use different height here
        mSlidingUpPanelLayout.setScrollableView(mListView, mapHeight);

        mSlidingUpPanelLayout.setPanelSlideListener(this);

        // transparent view at the top of ListView
        mTransparentView = rootView.findViewById(R.id.transparentView);

        // init header view for ListView
        // mTransparentHeaderView = inflater.inflate(R.layout.transparent_header_view, mListView, false);
        // mSpaceView = mTransparentHeaderView.findViewById(R.id.space);

        collapseMap();

        mSlidingUpPanelLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mSlidingUpPanelLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mSlidingUpPanelLayout.onPanelDragged(0);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        mLocation = getArguments().getParcelable(ARG_LOCATION);
        if (mLocation == null) {

            mLocation = getLastKnownLocation(false);

        }

        mMapFragment = SupportMapFragment.newInstance();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mapContainer, mMapFragment, "map");
        fragmentTransaction.commit();

        ArrayList<String> testData = new ArrayList<String>(100);
        for (int i = 0; i < 100; i++) {
            testData.add("Item " + i);
        }
        // show white bg if there are not too many items
        // mWhiteSpaceView.setVisibility(View.VISIBLE);

        // ListView approach
        /*mListView.addHeaderView(mTransparentHeaderView);
        mListView.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.simple_list_item, testData));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSlidingUpPanelLayout.collapsePane();
            }
        });*/
        mHeaderAdapter = new HeaderAdapter(getActivity(), testData, this);
        mListView.setItemAnimator(null);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListView.setLayoutManager(layoutManager);
        mListView.setAdapter(mHeaderAdapter);

    }

    private LatLng getLastKnownLocation(boolean isMoveMarker) {

/*        LocationManager lm = (LocationManager) TheApp.getAppContext().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_LOW);
        String provider = lm.getBestProvider(criteria, true);
        if (provider == null) {
            return null;
        }
        Activity activity = getActivity();
        if (activity == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
        }
        Location loc = lm.getLastKnownLocation(provider);
        if (loc != null) {
            LatLng latLng = new LatLng(loc.getLatitude(), loc.getLongitude());
            if (isMoveMarker) {
                moveMarker(latLng);
            }
            return latLng;
        } */
        return new LatLng(-52.000, -28.000);

    }

    private void moveMarker(LatLng latLng) {

        if (mLocationMarker != null)

            mLocationMarker.remove();

        mLocationMarker = mMap.addMarker(new MarkerOptions()
                .position(latLng).anchor(0.5f, 0.5f));

    }

    private void moveToLocation(Location location) {

        if (location == null)

            return;

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        moveToLocation(latLng);

    }

    private void moveToLocation(LatLng latLng) {

        moveToLocation(latLng, true);

    }

    private void moveToLocation(LatLng latLng, final boolean moveCamera) {

        if (latLng == null)

            return;

        moveMarker(latLng);

        mLocation = latLng;

        mListView.post(new Runnable() {

            public void run() {

                if (mMap != null && moveCamera)

                    mMap.moveCamera(
                            CameraUpdateFactory.
                                    newCameraPosition(CameraPosition.fromLatLngZoom(mLocation, 11.0f)));

            }

        });

    }

    private void collapseMap() {

        if (mHeaderAdapter != null)

            mHeaderAdapter.showSpace();

        mTransparentView.setVisibility(View.GONE);

        if (mMap != null) {
            mMap.animateCamera(
                    CameraUpdateFactory.
                            newLatLngZoom(new LatLng(-52.000, -28.000), 11f), 1000, null);

            Log.d("DIOGO", "OnCollapse");

        }

        mListView.setScrollingEnabled(true);

    }

    private void expandMap() {

        if (mHeaderAdapter != null)

            mHeaderAdapter.hideSpace();

        mTransparentView.setVisibility(View.INVISIBLE);

        if (mMap != null) {

            mMap.animateCamera(CameraUpdateFactory.zoomTo(14f), 1000, null);

            Log.d("DIOGO", "OnExpand");

        }

        mListView.setScrollingEnabled(false);



    }

    public void onPanelSlide(View view, float v) {}

    public void onPanelCollapsed(View view) {

        expandMap();

    }

    public void onPanelExpanded(View view) {

        collapseMap();

    }

    public void onPanelAnchored(View view) {}

    public void onLocationChanged(Location location) {

        if (mIsNeedLocationUpdate) {

            moveToLocation(location);

        }

    }

    public void onStatusChanged(String s, int i, Bundle bundle) {}

    public void onProviderEnabled(String s) {}

    public void onProviderDisabled(String s) {}

    public void onItemClicked(int position) {
        mSlidingUpPanelLayout.collapsePane();
    }

    public void onMapReady(GoogleMap googleMap) {

        Log.d("DIOGO", "OnMapReady");

        mMap = googleMap;
        mMap.getUiSettings().setCompassEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }

}