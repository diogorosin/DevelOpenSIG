package br.com.developen.sig.widget;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

public class AddressClusterRenderer extends DefaultClusterRenderer<AddressClusterItem> {

    public AddressClusterRenderer(Context context, GoogleMap googleMap, ClusterManager<AddressClusterItem> clusterManager) {

        super(context, googleMap, clusterManager);

    }

}