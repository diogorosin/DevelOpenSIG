package br.com.developen.sig;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.developen.sig.database.AddressEdificationSubjectModel;
import br.com.developen.sig.task.FindAddressesBySubjectNameOrDenominationAsyncTask;
import br.com.developen.sig.util.Constants;
import br.com.developen.sig.util.Messaging;


public class MapActivity extends FragmentActivity
        implements OnMapReadyCallback,
        NavigationView.OnNavigationItemSelectedListener,
        FindAddressesBySubjectNameOrDenominationAsyncTask.Listener{


    private static final int FINE_LOCATION_PERMISSION_REQUEST = 1;


    private HashMap<Integer, Marker> markers;

    private GoogleMap googleMap;

    private FloatingSearchView searchView;

    private LocationManager locationManager;

    private SharedPreferences preferences;


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
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        NavigationView navigationView = findViewById(R.id.activity_main_navigator);

        navigationView.setNavigationItemSelectedListener(this);

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

                AddressEdificationSubjectSuggestions suggestion = (AddressEdificationSubjectSuggestions) searchSuggestion;

                if (getMarkers().get(suggestion.mAddressEdificationSubject.getAddress().getIdentifier()) != null) {

                    final Marker marker = getMarkers().get(suggestion.mAddressEdificationSubject.getAddress().getIdentifier());

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

                searchView.setSearchText(((AddressEdificationSubjectSuggestions) searchSuggestion).mAddressEdificationSubject.getSubject().getNameOrDenomination());

            }

            public void onSearchAction(String query) {}

        });

        searchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {

            public void onActionMenuItemSelected(MenuItem item) {

                switch(item.getItemId()) {

                    case R.id.action_place:

                        goToMyLocation();

                        break;

                }

            }

        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

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

        this.googleMap = googleMap;

        /*
        MarkerInfoWindowAdapter markerInfoWindowAdapter = new MarkerInfoWindowAdapter(getApplicationContext());

        googleMap.setInfoWindowAdapter(markerInfoWindowAdapter);

        googleMap.getUiSettings().setCompassEnabled(false);

        googleMap.getUiSettings().setMapToolbarEnabled(false);

        googleMap.getUiSettings().setMyLocationButtonEnabled(false);

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            public void onInfoWindowClick(Marker marker) {

                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + marker.getPosition().latitude + "," + marker.getPosition().longitude);

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                mapIntent.setPackage("com.google.android.apps.maps");

                startActivity(mapIntent);

            }

        });

        new BindPlacesOnGoogleMapAsyncTask<>(this, googleMap).execute(); */

    }

    public void onSuccess(List<AddressEdificationSubjectModel> list) {

        List<SearchSuggestion> result = new ArrayList<>();

        for (AddressEdificationSubjectModel addressEdificationSubjectModel : list)

            result.add(new AddressEdificationSubjectSuggestions(addressEdificationSubjectModel));

        searchView.swapSuggestions(result);

    }

    public void onFailure(Messaging messaging) {}

    public HashMap<Integer, Marker> getMarkers() {

        if (markers == null)

            markers = new HashMap<>();

        return markers;

    }

    private static class AddressEdificationSubjectSuggestions implements SearchSuggestion {

        private final AddressEdificationSubjectModel mAddressEdificationSubject;

        public static final Parcelable.Creator<AddressEdificationSubjectSuggestions> CREATOR = new Parcelable.Creator<AddressEdificationSubjectSuggestions>() {

            public AddressEdificationSubjectSuggestions createFromParcel(Parcel in) {

                return new AddressEdificationSubjectSuggestions(in);

            }

            public AddressEdificationSubjectSuggestions[] newArray(int size) {

                return new AddressEdificationSubjectSuggestions[size];

            }

        };

        public AddressEdificationSubjectSuggestions(AddressEdificationSubjectModel addressEdificationSubjectModel) {

            mAddressEdificationSubject = addressEdificationSubjectModel;

        }

        public String getBody() {

            return mAddressEdificationSubject.getSubject().getIdentifier().toString();

        }

        public int describeContents() {

            return 0;

        }

        public void writeToParcel(Parcel dest, int flags) {

            dest.writeString(mAddressEdificationSubject.getSubject().getNameOrDenomination());

        }

        private AddressEdificationSubjectSuggestions(Parcel in) {

            mAddressEdificationSubject = (AddressEdificationSubjectModel) in.readSerializable();

        }

    }


















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

            case R.id.menu_main_home:

                break;

            case R.id.menu_main_settings:

                drawer.closeDrawers();

                //Intent saleIntent = new Intent(MainActivity.this, CatalogActivity.class);

                //startActivity(saleIntent);

                break;

            case R.id.menu_main_logout:

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

}