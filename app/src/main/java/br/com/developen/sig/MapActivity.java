package br.com.developen.sig;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.algo.NonHierarchicalViewBasedAlgorithm;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.developen.sig.database.AddressEdificationSubjectModel;
import br.com.developen.sig.database.AddressVO;
import br.com.developen.sig.repository.AddressRepository;
import br.com.developen.sig.task.FindAddressesBySubjectNameOrDenominationAsyncTask;
import br.com.developen.sig.util.Constants;
import br.com.developen.sig.util.Messaging;
import br.com.developen.sig.widget.AddressClusterItem;
import br.com.developen.sig.widget.AddressClusterRenderer;
import br.com.developen.sig.widget.AddressEdificationSubjectSuggestions;
import br.com.developen.sig.widget.MarkerInfoWindowAdapter;


public class MapActivity extends FragmentActivity
        implements OnMapReadyCallback,
        NavigationView.OnNavigationItemSelectedListener,
        FindAddressesBySubjectNameOrDenominationAsyncTask.Listener,
        ClusterManager.OnClusterClickListener<AddressClusterItem>,
        ClusterManager.OnClusterInfoWindowClickListener<AddressClusterItem>,
        ClusterManager.OnClusterItemClickListener<AddressClusterItem>,
        ClusterManager.OnClusterItemInfoWindowClickListener<AddressClusterItem>{


    private static final int FINE_LOCATION_PERMISSION_REQUEST = 1;


    private ClusterManager<AddressClusterItem> clusterManager;


    private HashMap<Integer, Marker> markers;

    private GoogleMap googleMap;

    private FloatingSearchView searchView;

    private LocationManager locationManager;

    private SharedPreferences preferences;

    private AddressRepository addressRepository;


    private MenuItem menuItemNormal;

    private MenuItem menuItemTerrain;

    private MenuItem menuItemSatellite;


    private LocationListener locationListener = new LocationListener() {

        public void onLocationChanged(Location location) {}

        public void onStatusChanged(String s, int i, Bundle bundle) {}

        public void onProviderEnabled(String s) {}

        public void onProviderDisabled(String s) {}

    };


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map);

        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.menu_map);

        mapFragment.getMapAsync(this);


        NavigationView navigationView = findViewById(R.id.activity_main_navigator);

        navigationView.setNavigationItemSelectedListener(this);


        menuItemNormal = navigationView.getMenu().findItem(R.id.menu_map_type_normal);

        menuItemSatellite = navigationView.getMenu().findItem(R.id.menu_map_type_satellite);

        menuItemTerrain = navigationView.getMenu().findItem(R.id.menu_map_type_terrain);


        DrawerLayout drawerLayout = findViewById(R.id.activity_map_drawer);

        preferences = getSharedPreferences(
                Constants.SHARED_PREFERENCES_NAME, 0);

        TextView userTextView = navigationView.getHeaderView(0).findViewById(R.id.activity_map_navigator_header_name);

        TextView governmentTextView = navigationView.getHeaderView(0).findViewById(R.id.activity_map_navigator_header_government);

        userTextView.setText(preferences.getString(Constants.USER_NAME_PROPERTY,"Desconhecido"));

        governmentTextView.setText(preferences.getString(Constants.GOVERNMENT_DENOMINATION_PROPERTY,"Desconhecido"));


        searchView = findViewById(R.id.floating_search_view);

        searchView.attachNavigationDrawerToMenuButton(drawerLayout);

        searchView.setOnBindSuggestionCallback(new SearchSuggestionsAdapter.OnBindSuggestionCallback() {

            public void onBindSuggestion(View suggestionView, ImageView leftIcon, TextView textView, SearchSuggestion item, int itemPosition) {

                AddressEdificationSubjectSuggestions addressEdificationSubjectSuggestions = (AddressEdificationSubjectSuggestions) item;

//                leftIcon.setImageDrawable(ResourcesCompat.getDrawable(getResources(), IconUtils.getListIconByType(placeSuggestion.mAddressEdificationSubject.getType()), null));

                leftIcon.setColorFilter(Color.parseColor("#000000"));

                leftIcon.setAlpha(.36f);

            }

        });

        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {

            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                if (!oldQuery.equals("") && newQuery.equals(""))

                    searchView.clearSuggestions();

                else

                    new FindAddressesBySubjectNameOrDenominationAsyncTask<>(MapActivity.this).execute(newQuery);

            }

        });

        searchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {

            public void onSuggestionClicked(final SearchSuggestion searchSuggestion) {

     /*           AddressEdificationSubjectSuggestions suggestion = (AddressEdificationSubjectSuggestions) searchSuggestion;

                if (getMarkers().get(suggestion.getmAddressEdificationSubject().getAddress().getIdentifier()) != null) {

                    final Marker marker = getMarkers().get(suggestion.getmAddressEdificationSubject().getAddress().getIdentifier());

                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(marker.getPosition())
                            .zoom(20)
                            .bearing(0)
                            .build();

                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), new GoogleMap.CancelableCallback() {

                        public void onFinish() {

                            marker.showInfoWindow();

                        }

                        public void onCancel() {}

                    });

                }

                searchView.clearSearchFocus();

                searchView.setSearchText(((AddressEdificationSubjectSuggestions) searchSuggestion).getmAddressEdificationSubject().getSubject().getNameOrDenomination()); */

            }

            public void onSearchAction(String query) {}

        });

        searchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {

            public void onActionMenuItemSelected(MenuItem item) {

                switch(item.getItemId()) {

                    case R.id.menu_map_search_localization:

                        goToMyLocation();

                        break;

                }

            }

        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

    }




    public void onSuccess(List<AddressEdificationSubjectModel> list) {

        List<SearchSuggestion> result = new ArrayList<>();

        for (AddressEdificationSubjectModel addressEdificationSubjectModel : list)

            result.add(new AddressEdificationSubjectSuggestions(addressEdificationSubjectModel));

        searchView.swapSuggestions(result);

    }


    public void onFailure(Messaging messaging) {}



    public void onBackPressed() {

        DrawerLayout drawer = findViewById(R.id.activity_map_drawer);

        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);

        } else {

            super.onBackPressed();

        }

    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        DrawerLayout drawer = findViewById(R.id.activity_map_drawer);

        switch (item.getItemId()){

            case R.id.menu_map_type_normal:

                menuItemTerrain.setChecked(false);

                menuItemSatellite.setChecked(false);

                getGoogleMap().setMapType(GoogleMap.MAP_TYPE_NORMAL);

                drawer.closeDrawers();

                break;

            case R.id.menu_map_type_terrain:

                menuItemNormal.setChecked(false);

                menuItemSatellite.setChecked(false);

                getGoogleMap().setMapType(GoogleMap.MAP_TYPE_TERRAIN);

                drawer.closeDrawers();

                break;

            case R.id.menu_map_type_satellite:

                menuItemNormal.setChecked(false);

                menuItemTerrain.setChecked(false);

                getGoogleMap().setMapType(GoogleMap.MAP_TYPE_SATELLITE);

                drawer.closeDrawers();

                break;

            case R.id.menu_settings:

                drawer.closeDrawers();

                //Intent saleIntent = new Intent(MainActivity.this, CatalogActivity.class);

                //startActivity(saleIntent);

                break;

            case R.id.menu_logout:

                SharedPreferences.Editor editor = preferences.edit();

                editor.remove(Constants.USER_IDENTIFIER_PROPERTY);

                editor.remove(Constants.USER_NAME_PROPERTY);

                editor.remove(Constants.USER_LOGIN_PROPERTY);

                editor.apply();

                drawer.closeDrawer(GravityCompat.START);

                Intent intent = new Intent(MapActivity.this, LoginActivity.class);

                startActivity(intent);

                finish();

                break;

        }

        return true;

    }






    //MAPS------------------------------------------------------------


    public GoogleMap getGoogleMap() {

        return googleMap;

    }


    public void setGoogleMap(GoogleMap googleMap) {

        this.googleMap = googleMap;

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

    public void goToMyLocation() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    FINE_LOCATION_PERMISSION_REQUEST);

        } else {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    10, 5000, locationListener);

            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (location!=null) {

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(location.getLatitude(), location.getLongitude()))
                        .zoom(13)
                        .bearing(0)
                        .build();

                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }

            if (!googleMap.isMyLocationEnabled())

                googleMap.setMyLocationEnabled(true);

        }

    }

    public void onMapReady(GoogleMap googleMap) {

        setGoogleMap(googleMap);

        getGoogleMap().getUiSettings().setCompassEnabled(false);

        getGoogleMap().getUiSettings().setMapToolbarEnabled(false);

        getGoogleMap().getUiSettings().setMyLocationButtonEnabled(false);


        DisplayMetrics metrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        clusterManager = new ClusterManager<>(this, getGoogleMap());

        clusterManager.setAlgorithm(new NonHierarchicalViewBasedAlgorithm<AddressClusterItem>(
                metrics.widthPixels, metrics.heightPixels));

        clusterManager.setRenderer(new AddressClusterRenderer(
                getApplicationContext(),
                getGoogleMap(),
                clusterManager));

        clusterManager.getMarkerCollection().setOnInfoWindowAdapter(
                new MarkerInfoWindowAdapter(getApplicationContext()));

        getGoogleMap().setOnCameraIdleListener(clusterManager);

        getGoogleMap().setOnMarkerClickListener(clusterManager);

        getGoogleMap().setOnInfoWindowClickListener(clusterManager);

        clusterManager.setOnClusterClickListener(this);

        clusterManager.setOnClusterInfoWindowClickListener(this);

        clusterManager.setOnClusterItemClickListener(this);

        clusterManager.setOnClusterItemInfoWindowClickListener(this);

        addressRepository = ViewModelProviders.of(this).get(AddressRepository.class);

        addressRepository.getAddresses().observe(MapActivity.this, new Observer<List<AddressVO>>() {

            public void onChanged(@Nullable List<AddressVO> addresses) {

                if (addresses!=null && !addresses.isEmpty()){

                    for (AddressVO address: addresses) {

                        AddressClusterItem addressClusterItem = new AddressClusterItem();

                        addressClusterItem.setIdentifier(address.getIdentifier());

                        addressClusterItem.setDenomination(address.getDenomination());

                        addressClusterItem.setNumber(address.getNumber());

                        addressClusterItem.setReference(address.getReference());

                        addressClusterItem.setDistrict(address.getDistrict());

                        addressClusterItem.setCity(address.getCity());

                        addressClusterItem.setPostalCode(address.getPostalCode());

                        addressClusterItem.setPosition(new LatLng(address.getLatitude(), address.getLongitude()));

                        clusterManager.addItem(addressClusterItem);

                    }

                }

            }

        });

    }

    public boolean onClusterClick(Cluster<AddressClusterItem> cluster) {
        return false;
    }

    public void onClusterInfoWindowClick(Cluster<AddressClusterItem> cluster) {}

    public boolean onClusterItemClick(AddressClusterItem addressClusterItem) {
        return false;
    }

    public void onClusterItemInfoWindowClick(AddressClusterItem addressClusterItem) {}

}