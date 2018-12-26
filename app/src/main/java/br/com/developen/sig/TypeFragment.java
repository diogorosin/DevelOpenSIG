package br.com.developen.sig;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.developen.sig.database.modified.ModifiedAddressEdificationModel;
import br.com.developen.sig.repository.ModifiedAddressEdificationRepository;

public class TypeFragment extends Fragment {


    private static final String ARG_MODIFIED_ADDRESS_IDENTIFIER = "ARG_MODIFIED_ADDRESS_IDENTIFIER";

    private static final String ARG_EDIFICATION_IDENTIFIER = "ARG_EDIFICATION_IDENTIFIER";


    private ModifiedAddressEdificationRepository modifiedAddressEdificationRepository;

    private TextInputEditText typeEditText;

    private TextInputEditText referenceEditText;


    public static TypeFragment newInstance(Integer modifiedAddressIdentifier, Integer edificationIdentifier) {

        Bundle args = new Bundle();

        TypeFragment fragment = new TypeFragment();

        args.putInt(ARG_MODIFIED_ADDRESS_IDENTIFIER, modifiedAddressIdentifier);

        args.putInt(ARG_EDIFICATION_IDENTIFIER, edificationIdentifier);

        fragment.setArguments(args);

        return fragment;

    }


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        modifiedAddressEdificationRepository = ViewModelProviders.of(getActivity()).get(ModifiedAddressEdificationRepository.class);

        modifiedAddressEdificationRepository.getModifiedAddressEdification(
                getArguments().getInt(ARG_MODIFIED_ADDRESS_IDENTIFIER),
                getArguments().getInt(ARG_EDIFICATION_IDENTIFIER)).
                observe(getActivity(), new Observer<ModifiedAddressEdificationModel>() {

                    public void onChanged(ModifiedAddressEdificationModel modifiedAddressEdificationModel) {

                        if (modifiedAddressEdificationModel != null){

                            typeEditText.setText(modifiedAddressEdificationModel.getType().toString());

                            referenceEditText.setText(modifiedAddressEdificationModel.getReference());

                        }

                    }

                });

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_type, container, false);

        typeEditText = v.findViewById(R.id.fragment_type_type_edittext);

        referenceEditText = v.findViewById(R.id.fragment_type_reference_edittext);

        return v;

    }


}