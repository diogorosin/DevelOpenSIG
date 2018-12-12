package br.com.developen.sig;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.developen.sig.database.modified.ModifiedAddressModel;
import br.com.developen.sig.repository.ModifiedAddressRepository;

public class AddressFragment extends Fragment {


    private static final String ARG_MODIFIED_ADDRESS_IDENTIFIER = "ARG_MODIFIED_ADDRESS_IDENTIFIER";

    private ModifiedAddressRepository modifiedAddressRepository;

    private TextInputEditText thoroughfareEditText;

    private TextInputEditText numberEditText;

    private TextInputEditText districtEditText;

    private TextInputEditText complementEditText;

    private TextInputEditText cityEditText;

    private TextInputEditText stateEditText;

    private TextInputEditText countryEditText;


    public static AddressFragment newInstance(Integer modifiedAddressIdentifier) {

        Bundle args = new Bundle();

        AddressFragment fragment = new AddressFragment();

        args.putInt(ARG_MODIFIED_ADDRESS_IDENTIFIER, modifiedAddressIdentifier);

        fragment.setArguments(args);

        return fragment;

    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        modifiedAddressRepository = ViewModelProviders.of(getActivity()).get(ModifiedAddressRepository.class);

        modifiedAddressRepository.getModifiedAddress(
                getArguments().getInt(ARG_MODIFIED_ADDRESS_IDENTIFIER)).
                observe(getActivity(), new Observer<ModifiedAddressModel>() {

                    public void onChanged(ModifiedAddressModel modifiedAddressModel) {

                        if (modifiedAddressModel != null){

                            thoroughfareEditText.setText(modifiedAddressModel.getDenomination());

                            numberEditText.setText(modifiedAddressModel.getNumber());

                            districtEditText.setText(modifiedAddressModel.getDistrict());

                            complementEditText.setText(modifiedAddressModel.getReference());

                            if (modifiedAddressModel.getCity() != null){

                                cityEditText.setText(modifiedAddressModel.getCity().getDenomination());

                                stateEditText.setText(modifiedAddressModel.getCity().getState().getDenomination());

                                countryEditText.setText(modifiedAddressModel.getCity().getState().getCountry().getDenomination());

                            }

                        }

                    }

                });

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_address, container, false);

        thoroughfareEditText = v.findViewById(R.id.fragment_address_thoroughfare_edittext);

        numberEditText = v.findViewById(R.id.fragment_address_number_edittext);

        districtEditText = v.findViewById(R.id.fragment_address_district_edittext);

        complementEditText = v.findViewById(R.id.fragment_address_complement_edittext);

        cityEditText = v.findViewById(R.id.fragment_address_city_edittext);

        stateEditText = v.findViewById(R.id.fragment_address_state_edittext);

        countryEditText = v.findViewById(R.id.fragment_address_country_edittext);

        return v;

    }

}