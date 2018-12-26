package br.com.developen.sig;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.developen.sig.database.modified.ModifiedAddressEdificationDwellerModel;
import br.com.developen.sig.database.modified.ModifiedAddressEdificationModel;
import br.com.developen.sig.repository.ModifiedAddressEdificationDwellerRepository;
import br.com.developen.sig.widget.ModifiedAddressEdificationDwellerRecyclerViewAdapter;

public class DwellerFragment extends Fragment {


    private static final String ARG_MODIFIED_ADDRESS_IDENTIFIER = "ARG_MODIFIED_ADDRESS_IDENTIFIER";

    private static final String ARG_EDIFICATION_IDENTIFIER = "ARG_EDIFICATION_IDENTIFIER";


    private ModifiedAddressEdificationDwellerRepository repository;

    private ModifiedAddressEdificationDwellerRecyclerViewAdapter recyclerViewAdapter;

    private DwellerFragmentListener fragmentListener;


    public static DwellerFragment newInstance(Integer modifiedAddressIdentifier, Integer edificationIdentifier) {

        Bundle args = new Bundle();

        DwellerFragment fragment = new DwellerFragment();

        args.putInt(ARG_MODIFIED_ADDRESS_IDENTIFIER, modifiedAddressIdentifier);

        args.putInt(ARG_EDIFICATION_IDENTIFIER, edificationIdentifier);

        fragment.setArguments(args);

        return fragment;

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_dweller, container, false);

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        recyclerViewAdapter = new ModifiedAddressEdificationDwellerRecyclerViewAdapter(new ArrayList<ModifiedAddressEdificationDwellerModel>(), fragmentListener);

        recyclerView.setAdapter(recyclerViewAdapter);

        repository = ViewModelProviders.of(this).get(ModifiedAddressEdificationDwellerRepository.class);

        repository.getDwellersOfModifiedAddressEdification(
                getArguments().getInt(ARG_MODIFIED_ADDRESS_IDENTIFIER),
                getArguments().getInt(ARG_EDIFICATION_IDENTIFIER)).observe(DwellerFragment.this,
                new Observer<List<ModifiedAddressEdificationDwellerModel>>() {

                    public void onChanged(List<ModifiedAddressEdificationDwellerModel> modifiedAddressEdifications) {

                        recyclerViewAdapter.setModifiedAddressEdificationDwellers(modifiedAddressEdifications);

                    }

                });

        return recyclerView;

    }


    public interface DwellerFragmentListener {

        void onDwellerClicked(ModifiedAddressEdificationDwellerModel modifiedAddressEdificationDwellerModel);

        void onDwellerLongClick(ModifiedAddressEdificationDwellerModel modifiedAddressEdificationDwellerModel);

    }


    public void onAttach(Context context) {

        super.onAttach(context);

        if (context instanceof DwellerFragmentListener)

            fragmentListener = (DwellerFragmentListener) context;

        else

            throw new RuntimeException(context.toString()
                    + " must implement DwellerFragmentListener");

    }


    public void onDetach() {

        super.onDetach();

        fragmentListener = null;

    }


}