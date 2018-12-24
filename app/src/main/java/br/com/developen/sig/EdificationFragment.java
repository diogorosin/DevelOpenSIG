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

import br.com.developen.sig.database.modified.ModifiedAddressEdificationModel;
import br.com.developen.sig.repository.ModifiedAddressEdificationRepository;
import br.com.developen.sig.widget.ModifiedAddressEdificationRecyclerViewAdapter;

public class EdificationFragment extends Fragment {


    private static final String ARG_MODIFIED_ADDRESS_IDENTIFIER = "ARG_MODIFIED_ADDRESS_IDENTIFIER";

    private static final String ARG_COLUMNS = "ARG_COLUMNS";


    private ModifiedAddressEdificationRepository repository;

    private ModifiedAddressEdificationRecyclerViewAdapter recyclerViewAdapter;

    private EdificationFragmentListener fragmentListener;


    public static EdificationFragment newInstance(Integer modifiedAddressIdentifier) {

        Bundle args = new Bundle();

        EdificationFragment fragment = new EdificationFragment();

        args.putInt(ARG_MODIFIED_ADDRESS_IDENTIFIER, modifiedAddressIdentifier);

        args.putInt(ARG_COLUMNS, 3);

        fragment.setArguments(args);

        return fragment;

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_edification, container, false);

        int columns = getArguments().getInt(ARG_COLUMNS);

        if (columns <= 1)

            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        else

            recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), columns));

        recyclerViewAdapter = new ModifiedAddressEdificationRecyclerViewAdapter(new ArrayList<ModifiedAddressEdificationModel>(), fragmentListener);

        recyclerView.setAdapter(recyclerViewAdapter);

        repository = ViewModelProviders.of(this).get(ModifiedAddressEdificationRepository.class);

        repository.getEdificationsOfModifiedAddress(getArguments().getInt(ARG_COLUMNS)).observe(EdificationFragment.this, new Observer<List<ModifiedAddressEdificationModel>>() {

            public void onChanged(@Nullable List<ModifiedAddressEdificationModel> modifiedAddressEdifications) {

                recyclerViewAdapter.setModifiedAddressEdifications(modifiedAddressEdifications);

            }

        });

        return recyclerView;

    }


    public interface EdificationFragmentListener {

        void onEdificationClicked(ModifiedAddressEdificationModel modifiedAddressEdificationModel);

        void onEdificationLongClick(ModifiedAddressEdificationModel modifiedAddressEdificationModel);

    }


    public void onAttach(Context context) {

        super.onAttach(context);

        if (context instanceof EdificationFragmentListener)

            fragmentListener = (EdificationFragmentListener) context;

        else

            throw new RuntimeException(context.toString()
                    + " must implement EdificationFragmentListener");

    }


    public void onDetach() {

        super.onDetach();

        fragmentListener = null;

    }


}