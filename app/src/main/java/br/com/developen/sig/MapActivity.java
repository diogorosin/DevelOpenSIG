// https://developer.android.com/training/location/retrieve-current
// https://developer.android.com/training/location/receive-location-updates

package br.com.developen.sig;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
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

import java.util.ArrayList;
import java.util.List;

import br.com.developen.sig.database.AddressEdificationDwellerModel;
import br.com.developen.sig.database.AddressModel;
import br.com.developen.sig.database.SubjectView;
import br.com.developen.sig.repository.AddressRepository;
import br.com.developen.sig.task.CreateAddressAsynTask;
import br.com.developen.sig.task.FindAddressesBySubjectNameOrDenominationAsyncTask;
import br.com.developen.sig.util.Constants;
import br.com.developen.sig.util.IconUtils;
import br.com.developen.sig.util.Messaging;
import br.com.developen.sig.widget.AddressClusterItem;
import br.com.developen.sig.widget.AddressClusterRenderer;
import br.com.developen.sig.widget.AddressEdificationDwellerSuggestions;


public class MapActivity
        extends FragmentActivity
        implements OnMapReadyCallback,
        GoogleMap.OnMyLocationChangeListener,
        CreateAddressAsynTask.Listener,
        NavigationView.OnNavigationItemSelectedListener,
        FindAddressesBySubjectNameOrDenominationAsyncTask.Listener,
        ClusterManager.OnClusterClickListener<AddressClusterItem>,
        ClusterManager.OnClusterInfoWindowClickListener<AddressClusterItem>,
        ClusterManager.OnClusterItemClickListener<AddressClusterItem>,
        ClusterManager.OnClusterItemInfoWindowClickListener<AddressClusterItem>{


    public static final int MY_LOCATION_PERMISSION_REQUEST = 1;

    public static final String MODIFIED_ADDRESS_IDENTIFIER = "ARG_MODIFIED_ADDRESS_IDENTIFIER";


    private ClusterManager<AddressClusterItem> clusterManager;

    private Location lastKnowLocation;

    private GoogleMap googleMap;

    private FloatingSearchView searchView;

    private SharedPreferences preferences;

    private AddressRepository addressRepository;


    private MenuItem menuItemNormal;

    private MenuItem menuItemTerrain;

    private MenuItem menuItemSatellite;


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map);

        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_map_fragment);

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


        searchView = findViewById(R.id.activity_map_fsv);

        searchView.attachNavigationDrawerToMenuButton(drawerLayout);

        searchView.setOnBindSuggestionCallback(new SearchSuggestionsAdapter.OnBindSuggestionCallback() {

            public void onBindSuggestion(View suggestionView, ImageView leftIcon, TextView textView, SearchSuggestion item, int itemPosition) {

                AddressEdificationDwellerSuggestions addressEdificationDwellerSuggestions = (AddressEdificationDwellerSuggestions) item;

                leftIcon.setImageDrawable(ResourcesCompat.getDrawable(getResources(), IconUtils.getListIconByType(
                        addressEdificationDwellerSuggestions.
                                getAddressEdificationDwellerModel().
                                getSubject().
                                getType()), null));

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

                final AddressEdificationDwellerSuggestions suggestion = (AddressEdificationDwellerSuggestions) searchSuggestion;

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(suggestion.
                                getAddressEdificationDwellerModel().
                                getAddressEdification().
                                getAddress().
                                getLatitude(), suggestion.
                                getAddressEdificationDwellerModel().
                                getAddressEdification().
                                getAddress().
                                getLongitude()))
                        .zoom(getGoogleMap().getMaxZoomLevel())
                        .bearing(0)
                        .build();

                getGoogleMap().animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), new GoogleMap.CancelableCallback() {

                    public void onFinish() {

                        new Handler().postDelayed(new Runnable() {

                            public void run() {

                                for (Marker marker: clusterManager.getMarkerCollection().getMarkers()) {

                                    AddressClusterItem addressClusterItem = (AddressClusterItem) marker.getTag();

                                    if (marker.getTag() != null && addressClusterItem.
                                            getIdentifier().equals(suggestion.
                                            getAddressEdificationDwellerModel().
                                            getAddressEdification().
                                            getAddress().
                                            getIdentifier())) {

                                        marker.showInfoWindow();

                                    }

                                }

                            }

                        }, 100);

                    }

                    public void onCancel() {}

                });

                searchView.clearSearchFocus();

                searchView.setSearchText(((AddressEdificationDwellerSuggestions) searchSuggestion).getAddressEdificationDwellerModel().getSubject().getNameOrDenomination());

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

        FloatingActionButton fab = findViewById(R.id.activity_map_fab);

        fab.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                if (getLastKnowLocation() != null)

                    new CreateAddressAsynTask<>(MapActivity.this).execute(
                            getLastKnowLocation().getLatitude(), getLastKnowLocation().getLongitude());

            }

        });

    }


    public void onSuccess(List<AddressEdificationDwellerModel> list) {

        List<SearchSuggestion> result = new ArrayList<>();

        for (AddressEdificationDwellerModel addressEdificationDwellerModel : list)

            result.add(new AddressEdificationDwellerSuggestions(addressEdificationDwellerModel));

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

            case MY_LOCATION_PERMISSION_REQUEST:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)

                    getGoogleMap().setMyLocationEnabled(true);

                break;

        }

    }


    public void goToMyLocation() {

        if (getLastKnowLocation() != null) {

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(getLastKnowLocation().getLatitude(), getLastKnowLocation().getLongitude()))
                    .zoom(18f)
                    .bearing(0)
                    .build();

            getGoogleMap().animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        }

    }


    public ClusterManager getClusterManager(){

        if (clusterManager==null){

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
                    new GoogleMap.InfoWindowAdapter(){
                        public View getInfoWindow(Marker marker) {
                            return null;
                        }
                        public View getInfoContents(Marker marker) {

                            View v = getLayoutInflater().inflate(R.layout.activity_map_windowinfo, null);

                            TextView subjectTextView = v.findViewById(R.id.marker_subject_textview);

                            TextView streetTextView = v.findViewById(R.id.marker_denomination_textview);

                            TextView numberTextView = v.findViewById(R.id.marker_number_textview);

                            TextView districtTextView = v.findViewById(R.id.marker_district_textview);

                            TextView cityTextView = v.findViewById(R.id.marker_city_textview);

                            if(marker.getTag() != null){

                                AddressClusterItem addressClusterItem = (AddressClusterItem) marker.getTag();

                                String subjects = "";

                                for (SubjectView subjectView: addressRepository.
                                        getDao().getSubjectsOfAddress(addressClusterItem.getIdentifier()))

                                    subjects += subjectView.getNameOrDenomination() + '\n';

                                boolean hasSubjects = !subjects.isEmpty();

                                if (hasSubjects)

                                    subjectTextView.setText(subjects);

                                boolean hasStreet = addressClusterItem.getDenomination() != null && !addressClusterItem.getDenomination().isEmpty();

                                if (hasStreet)

                                    streetTextView.setText(addressClusterItem.getDenomination());

                                boolean hasNumber = addressClusterItem.getNumber() != null && !addressClusterItem.getNumber().isEmpty();

                                if (hasNumber)

                                    numberTextView.setText(", Nº " + addressClusterItem.getNumber());

                                boolean hasDistrict = addressClusterItem.getDistrict() != null && !addressClusterItem.getDistrict().isEmpty();

                                if (hasDistrict)

                                    districtTextView.setText(addressClusterItem.getDistrict());

                                boolean hasCity = addressClusterItem.getCity() != null && !addressClusterItem.getCity().isEmpty();

                                if (hasCity)

                                    cityTextView.setText(addressClusterItem.getCity());

                            }

                            return v;

                        }
                    }
            );

        }

        return clusterManager;

    }


    public void onMapReady(GoogleMap googleMap) {


        setGoogleMap(googleMap);

        getGoogleMap().getUiSettings().setCompassEnabled(false);

        getGoogleMap().getUiSettings().setMapToolbarEnabled(false);

        getGoogleMap().getUiSettings().setMyLocationButtonEnabled(false);

        getGoogleMap().setMinZoomPreference(12f);

        getGoogleMap().setInfoWindowAdapter(getClusterManager().getMarkerManager());

        getGoogleMap().setOnMyLocationChangeListener(this);

        getGoogleMap().setOnCameraIdleListener(getClusterManager());

        getGoogleMap().setOnMarkerClickListener(getClusterManager());

        getGoogleMap().setOnInfoWindowClickListener(getClusterManager());


        getClusterManager().setOnClusterClickListener(this);

        getClusterManager().setOnClusterInfoWindowClickListener(this);

        getClusterManager().setOnClusterItemClickListener(this);

        getClusterManager().setOnClusterItemInfoWindowClickListener(this);

        addressRepository = ViewModelProviders.of(this).get(AddressRepository.class);

        addressRepository.getAddresses().observe(MapActivity.this, new Observer<List<AddressModel>>() {

            public void onChanged(@Nullable List<AddressModel> addresses) {

                getClusterManager().clearItems();

                if (addresses!=null && !addresses.isEmpty()){

                    for (AddressModel address: addresses) {

                        AddressClusterItem addressClusterItem = new AddressClusterItem();

                        addressClusterItem.setIdentifier(address.getIdentifier());

                        addressClusterItem.setDenomination(address.getDenomination());

                        addressClusterItem.setNumber(address.getNumber());

                        addressClusterItem.setReference(address.getReference());

                        addressClusterItem.setDistrict(address.getDistrict());

                        addressClusterItem.setCity(address.getCity().toString());

                        addressClusterItem.setPostalCode(address.getPostalCode());

                        addressClusterItem.setPosition(new LatLng(address.getLatitude(), address.getLongitude()));

                        getClusterManager().addItem(addressClusterItem);

                    }

                }

            }

        });

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_LOCATION_PERMISSION_REQUEST);

        } else

            getGoogleMap().setMyLocationEnabled(true);

    }


    public boolean onClusterClick(Cluster<AddressClusterItem> cluster) {

        return false;

    }


    public void onClusterInfoWindowClick(Cluster<AddressClusterItem> cluster) {}


    public boolean onClusterItemClick(AddressClusterItem addressClusterItem) {

        return false;

    }


    public void onClusterItemInfoWindowClick(AddressClusterItem addressClusterItem) {}


    public void onCreateAddressSuccess(Integer identifier) {

        Intent addIntent = new Intent(MapActivity.this, AddressActivity.class);

        addIntent.putExtra(MODIFIED_ADDRESS_IDENTIFIER, identifier);

        startActivity(addIntent);

    }


    public void onCreateAddressFailure(Messaging messaging) {}


    public void onMyLocationChange(Location location) {

        if (getLastKnowLocation()==null) {

            getGoogleMap().animateCamera(
                    CameraUpdateFactory.
                            newLatLngZoom(new LatLng(location.getLatitude(),
                                    location.getLongitude()),15f));

        }

        setLastKnowLocation(location);

    }


    public Location getLastKnowLocation() {

        return lastKnowLocation;

    }


    public void setLastKnowLocation(Location lastKnowLocation) {

        this.lastKnowLocation = lastKnowLocation;

    }


}