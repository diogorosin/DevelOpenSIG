package br.com.developen.sig.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.developen.sig.R;
import br.com.developen.sig.database.ModifiedAddressModel;
import br.com.developen.sig.repository.ModifiedAddressRepository;

public class ModifiedAddressLocationFragment extends Fragment implements OnMapReadyCallback {


    private static final String ARG_MODIFIED_ADDRESS_IDENTIFIER = "ARG_MODIFIED_ADDRESS_IDENTIFIER";

    private ModifiedAddressRepository modifiedAddressRepository;

    private LocationListener locationListener;

    private GoogleMap googleMap;

    private MapView mapView;

    private Marker marker;


    public static ModifiedAddressLocationFragment newInstance(LocationListener locationListener, Integer modifiedAddressIdentifier) {

        Bundle args = new Bundle();

        ModifiedAddressLocationFragment fragment = new ModifiedAddressLocationFragment();

        fragment.setLocationListener(locationListener);

        args.putInt(ARG_MODIFIED_ADDRESS_IDENTIFIER, modifiedAddressIdentifier);

        fragment.setArguments(args);

        return fragment;

    }


    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_modified_address_location, container, false);

        mapView = v.findViewById(R.id.fragment_modified_address_location_mapview);

        mapView.onCreate(savedInstanceState);

        mapView.onResume();

        try {

            MapsInitializer.initialize(getActivity().getApplicationContext());

        } catch (Exception e) {

            e.printStackTrace();

        }

        mapView.getMapAsync(this);

        return v;

    }


    public void onResume() {

        super.onResume();

        mapView.onResume();

    }


    public void onPause() {

        super.onPause();

        mapView.onPause();

        marker = null;

    }


    public void onDestroy() {

        super.onDestroy();

        mapView.onDestroy();

        marker = null;

    }


    public void onLowMemory() {

        super.onLowMemory();

        mapView.onLowMemory();

        marker = null;

    }


    public void onMapReady(GoogleMap googleMap) {

        setGoogleMap(googleMap);

        getGoogleMap().setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        getGoogleMap().getUiSettings().setCompassEnabled(false);

        getGoogleMap().getUiSettings().setZoomControlsEnabled(false);

        getGoogleMap().getUiSettings().setMapToolbarEnabled(false);

        getGoogleMap().getUiSettings().setMyLocationButtonEnabled(false);

        getGoogleMap().setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            public void onMarkerDragStart(Marker marker) {}
            public void onMarkerDrag(Marker marker) {}
            public void onMarkerDragEnd(Marker marker) {

                if (getLocationListener() != null)

                    getLocationListener().onLocationChanged(marker.getPosition());

            }
        });

        getGoogleMap().setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            public void onMapClick(LatLng latLng) {

                if (getLocationListener() != null)

                    getLocationListener().onLocationChanged(latLng);

            }
        });

        modifiedAddressRepository = ViewModelProviders.of(getActivity()).get(ModifiedAddressRepository.class);

        modifiedAddressRepository.getModifiedAddress(
                getArguments().getInt(ARG_MODIFIED_ADDRESS_IDENTIFIER)).
                observe(getActivity(), new Observer<ModifiedAddressModel>() {

                    public void onChanged(ModifiedAddressModel modifiedAddressModel) {

                        if (modifiedAddressModel!=null)

                            moveToLocation(new LatLng(modifiedAddressModel.getLatitude(),
                                    modifiedAddressModel.getLongitude()));

                    }

                });

    }


    public GoogleMap getGoogleMap() {

        return googleMap;

    }


    public void setGoogleMap(GoogleMap googleMap) {

        this.googleMap = googleMap;

    }


    private void moveToLocation(LatLng latLng) {

        if (marker == null) {

            marker = getGoogleMap().addMarker(
                    new MarkerOptions().
                            position(latLng).
                            draggable(true));

        } else {

            if (!marker.getPosition().equals(latLng))

                marker.setPosition(latLng);

        }

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(18f)
                .bearing(0)
                .build();

        getGoogleMap().animateCamera(
                CameraUpdateFactory.
                        newCameraPosition(cameraPosition));

    }


    public LocationListener getLocationListener() {

        return locationListener;

    }


    public void setLocationListener(LocationListener locationListener) {

        this.locationListener = locationListener;

    }


    public interface LocationListener {

        void onLocationChanged(LatLng latLng);

    }


}