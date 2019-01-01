package br.com.developen.sig.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.developen.sig.R;
import br.com.developen.sig.database.ModifiedAddressEdificationDwellerModel;
import br.com.developen.sig.repository.ModifiedAddressEdificationDwellerRepository;

public class ModifiedAddressEdificationDwellerOrganizationFragment extends Fragment {


    public static final String MODIFIED_ADDRESS_IDENTIFIER = "ARG_MODIFIED_ADDRESS_IDENTIFIER";

    public static final String EDIFICATION_IDENTIFIER = "ARG_EDIFICATION_IDENTIFIER";

    public static final String DWELLER_IDENTIFIER = "ARG_DWELLER_IDENTIFIER";


    private ModifiedAddressEdificationDwellerRepository modifiedAddressEdificationDwellerRepository;

    private TextInputEditText socialNameEditText;

    private TextInputEditText fancyNameEditText;


    public static ModifiedAddressEdificationDwellerOrganizationFragment newInstance(Integer modifiedAddressIdentifier,
                                                                                    Integer edificationIdentifier,
                                                                                    Integer dweller) {

        Bundle args = new Bundle();

        ModifiedAddressEdificationDwellerOrganizationFragment fragment = new ModifiedAddressEdificationDwellerOrganizationFragment();

        args.putInt(MODIFIED_ADDRESS_IDENTIFIER, modifiedAddressIdentifier);

        args.putInt(EDIFICATION_IDENTIFIER, edificationIdentifier);

        args.putInt(DWELLER_IDENTIFIER, dweller);

        fragment.setArguments(args);

        return fragment;

    }


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_modified_address_edification_dweller_organization, container, false);

        socialNameEditText = v.findViewById(R.id.fragment_modified_address_edification_dweller_organization_socialname_edittext);

        fancyNameEditText = v.findViewById(R.id.fragment_modified_address_edification_dweller_organization_fancyname_edittext);

        modifiedAddressEdificationDwellerRepository = ViewModelProviders.of(getActivity()).get(ModifiedAddressEdificationDwellerRepository.class);

        modifiedAddressEdificationDwellerRepository.getModifiedAddressEdificationDweller(
                getArguments().getInt(MODIFIED_ADDRESS_IDENTIFIER),
                getArguments().getInt(EDIFICATION_IDENTIFIER),
                getArguments().getInt(DWELLER_IDENTIFIER)).
                observe(getActivity(), new Observer<ModifiedAddressEdificationDwellerModel>() {

                    public void onChanged(ModifiedAddressEdificationDwellerModel modifiedAddressEdificationDwellerModel) {

                        if (modifiedAddressEdificationDwellerModel != null){

                            socialNameEditText.setText(modifiedAddressEdificationDwellerModel.getNameOrDenomination());

                            fancyNameEditText.setText(modifiedAddressEdificationDwellerModel.getFancyName());

                        }

                    }

                });

        return v;

    }


}