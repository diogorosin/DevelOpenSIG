package br.com.developen.sig.widget;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

public class AddressClusterRenderer extends DefaultClusterRenderer<AddressClusterItem> {

    public AddressClusterRenderer(Context context, GoogleMap googleMap, ClusterManager<AddressClusterItem> clusterManager) {

        super(context, googleMap, clusterManager);

        setMinClusterSize(2);

    }

    protected void onClusterItemRendered(AddressClusterItem addressClusterItem, Marker marker) {

        marker.setTag(addressClusterItem);

    }

}