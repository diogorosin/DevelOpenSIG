package br.com.developen.sig;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.developen.sig.widget.slide.LockableRecyclerView;
import br.com.developen.sig.widget.slide.SlidingUpPanelLayout;

public class AddressActivity
        extends FragmentActivity
        implements OnMapReadyCallback,
        SlidingUpPanelLayout.PanelSlideListener {


    private static final int FINE_LOCATION_PERMISSION_REQUEST = 1;

    private static final LatLng MARAVILHA = new LatLng(-26.774482, -53.169744);


    private LatLng location = MARAVILHA;

    private Marker marker;

    private GoogleMap googleMap;

    private SupportMapFragment mapFragment;

    private LocationManager locationManager;

    private SlidingUpPanelLayout slidingUpPanelLayout;

    private LocationListener locationListener = new LocationListener() {

        public void onLocationChanged(Location location) {

            moveToLocation(location);

        }

        public void onStatusChanged(String s, int i, Bundle bundle) {}

        public void onProviderEnabled(String s) {}

        public void onProviderDisabled(String s) {}

    };


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_address);

        LockableRecyclerView mListView = findViewById(android.R.id.list);

        mListView.setOverScrollMode(ListView.OVER_SCROLL_NEVER);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().
                findFragmentById(R.id.activity_address_fragment);

        mapFragment.getMapAsync(this);

        slidingUpPanelLayout = findViewById(R.id.slidingLayout);

        slidingUpPanelLayout.setEnableDragViewTouchEvents(true);

        int mapHeight = getResources().getDimensionPixelSize(R.dimen.map_height);

        slidingUpPanelLayout.setPanelHeight(mapHeight);

        slidingUpPanelLayout.setScrollableView(mListView, mapHeight);

        slidingUpPanelLayout.setPanelSlideListener(this);

        slidingUpPanelLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            public void onGlobalLayout() {

                slidingUpPanelLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                slidingUpPanelLayout.onPanelDragged(0);

            }

        });

        setLocationManager((LocationManager) getSystemService(Context.LOCATION_SERVICE));


    }


    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {

            case FINE_LOCATION_PERMISSION_REQUEST: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    goToMyLocation();

                }

            }

        }

    }


    public void onMapReady(GoogleMap googleMap) {

        setGoogleMap(googleMap);

        getGoogleMap().setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        getGoogleMap().getUiSettings().setCompassEnabled(false);

        getGoogleMap().getUiSettings().setZoomControlsEnabled(true);

        getGoogleMap().getUiSettings().setMapToolbarEnabled(false);

        getGoogleMap().getUiSettings().setMyLocationButtonEnabled(false);

        getGoogleMap().setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            public void onMarkerDragStart(Marker marker) {}
            public void onMarkerDrag(Marker marker) {}
            public void onMarkerDragEnd(Marker marker) {

                moveToLocation(marker.getPosition());

            }
        });

        getGoogleMap().setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            public void onMapClick(LatLng latLng) {

                moveToLocation(latLng);

            }
        });

        goToMyLocation();

    }


    public void goToMyLocation() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    FINE_LOCATION_PERMISSION_REQUEST);

        } else {

            getLocationManager().requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    10, 5000, locationListener);

            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            moveToLocation(location);

        }

    }


    public GoogleMap getGoogleMap() {

        return googleMap;

    }


    public void setGoogleMap(GoogleMap googleMap) {

        this.googleMap = googleMap;

    }


    public void onPanelSlide(View panel, float slideOffset) {

    }


    public void onPanelCollapsed(View panel) {

        expandMap();

    }


    public void onPanelExpanded(View panel) {

        collapseMap();

    }


    public void onPanelAnchored(View panel) {}


    private void collapseMap() {

        if (getGoogleMap() != null){

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(getLocation().latitude+0.01, getLocation().longitude))
                    .zoom(14f)
                    .bearing(0)
                    .build();

            getGoogleMap().animateCamera(
                    CameraUpdateFactory.
                            newCameraPosition(cameraPosition));

        }

    }


    private void expandMap() {

        if (getGoogleMap() != null) {

            getGoogleMap().animateCamera(
                    CameraUpdateFactory.
                            newLatLngZoom(getLocation(), 18f), 1000, null);

        }

    }


    private void moveToLocation(Location location) {

        LatLng latLng = location == null ? MARAVILHA :
                new LatLng(location.getLatitude(),
                        location.getLongitude());

        moveToLocation(latLng);

    }


    private void moveToLocation(LatLng latLng) {

        moveToLocation(latLng, true);

    }


    private void moveToLocation(LatLng latLng, final boolean moveCamera) {

        setLocation(latLng);

        if (!getMarker().getPosition().equals(getLocation()))

            getMarker().setPosition(getLocation());

        double latitude = slidingUpPanelLayout.isExpanded() ? getLocation().latitude + 0.01 : getLocation().latitude;

        double longitude = getLocation().longitude;

        float zoom = slidingUpPanelLayout.isExpanded() ? 14f : 18f;

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude))
                .zoom(zoom)
                .bearing(0)
                .build();

        getGoogleMap().animateCamera(
                CameraUpdateFactory.
                        newCameraPosition(cameraPosition));

    }


    public Marker getMarker(){

        if (marker==null){

            marker = getGoogleMap().addMarker(
                    new MarkerOptions().
                            position(getLocation()).
                            draggable(true));

        }

        return marker;

    }


    public LocationManager getLocationManager() {

        return locationManager;

    }


    public void setLocationManager(LocationManager locationManager) {

        this.locationManager = locationManager;

    }


    public LatLng getLocation() {

        return location;

    }


    public void setLocation(LatLng location) {

        this.location = location;

    }


}