package br.com.developen.sig.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import br.com.developen.sig.R;


public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public MarkerInfoWindowAdapter(Context context) {

        this.context = context.getApplicationContext();

    }

    public View getInfoWindow(Marker arg0) {

        return null;

    }

    public View getInfoContents(Marker arg0) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View v = inflater.inflate(R.layout.activity_map_windowinfo, null);

        TextView subjectTextView = v.findViewById(R.id.marker_subject_textview);

        TextView streetTextView = v.findViewById(R.id.marker_denomination_textview);

        TextView numberTextView = v.findViewById(R.id.marker_number_textview);

        TextView districtTextView = v.findViewById(R.id.marker_district_textview);

        TextView cityTextView = v.findViewById(R.id.marker_city_textview);


        AddressClusterItem addressClusterItem = (AddressClusterItem) arg0.getTag();

        subjectTextView.setText("Diogo Buzin Rosin");


        //Street
        boolean hasStreet = addressClusterItem.getDenomination() != null && !addressClusterItem.getDenomination().isEmpty();

        if (hasStreet)

            streetTextView.setText(addressClusterItem.getDenomination());

//        streetTextView.setVisibility(hasStreet ? View.VISIBLE : View.GONE);


        //Number
        boolean hasNumber = addressClusterItem.getNumber() != null && !addressClusterItem.getNumber().isEmpty();

        if (hasNumber)

            numberTextView.setText(addressClusterItem.getNumber());

//        numberTextView.setVisibility(hasNumber ? View.VISIBLE : View.GONE);


        //District
        boolean hasDistrict = addressClusterItem.getDistrict() != null && !addressClusterItem.getDistrict().isEmpty();

        if (hasDistrict)

            districtTextView.setText(addressClusterItem.getDistrict());

        //districtTextView.setVisibility(hasDistrict ? View.VISIBLE : View.GONE);


        //City
        boolean hasCity = addressClusterItem.getCity() != null; //&& !addressClusterItem.getCity().isEmpty();

        if (hasCity)

            cityTextView.setText(addressClusterItem.getCity().toString());

        //cityTextView.setVisibility(hasCity ? View.VISIBLE : View.GONE);


        return v;

    }

}