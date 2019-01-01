package br.com.developen.sig.widget;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.developen.sig.fragment.ModifiedAddressEdificationDwellerFragment;
import br.com.developen.sig.R;
import br.com.developen.sig.database.ModifiedAddressEdificationDwellerModel;
import br.com.developen.sig.util.StringUtils;

public class ModifiedAddressEdificationDwellerRecyclerViewAdapter
        extends RecyclerView.Adapter<ModifiedAddressEdificationDwellerRecyclerViewAdapter.ModifiedAddressEdificationDwellerViewHolder> {


    public static final int INDIVIDUAL = 1;

    public static final int ORGANIZATION = 2;


    private List<ModifiedAddressEdificationDwellerModel> modifiedAddressEdificationDwellers;

    private ModifiedAddressEdificationDwellerFragment.ModifiedAddressEdificationDwellerFragmentListener fragmentListener;


    public ModifiedAddressEdificationDwellerRecyclerViewAdapter(List<ModifiedAddressEdificationDwellerModel> modifiedAddressEdifications, ModifiedAddressEdificationDwellerFragment.ModifiedAddressEdificationDwellerFragmentListener listener) {

        this.modifiedAddressEdificationDwellers = modifiedAddressEdifications;

        this.fragmentListener = listener;

        setHasStableIds(true);

    }


    public ModifiedAddressEdificationDwellerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layout = 0;

        switch (viewType){

            case INDIVIDUAL: layout = R.layout.fragment_modified_address_edification_dweller_list_individual;
                break;

            case ORGANIZATION: layout = R.layout.fragment_modified_address_edification_dweller_list_organization;
                break;

        }

        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);

        return new ModifiedAddressEdificationDwellerViewHolder(view);

    }


    public int getItemViewType(int position) {

        switch (modifiedAddressEdificationDwellers.get(position).getType()){

            case "F": return INDIVIDUAL;

            case "J": return ORGANIZATION;

            default:

                return 0;

        }

    }


    @SuppressLint("SetTextI18n")
    public void onBindViewHolder(final ModifiedAddressEdificationDwellerViewHolder holder, int position) {

        holder.modifiedAddressEdificationDwellerModel = modifiedAddressEdificationDwellers.get(position);

        holder.nameOrDenomination.setText(modifiedAddressEdificationDwellers.get(position).getNameOrDenomination());

        holder.from.setText("Desde " + StringUtils.formatDate(modifiedAddressEdificationDwellers.get(position).getFrom()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (fragmentListener != null)

                    fragmentListener.onDwellerClicked(holder.modifiedAddressEdificationDwellerModel);

            }

        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {

                if (fragmentListener != null)

                    fragmentListener.onDwellerLongClick(holder.modifiedAddressEdificationDwellerModel);

                return false;

            }

        });

    }


    public int getItemCount() {

        return modifiedAddressEdificationDwellers.size();

    }


    public long getItemId(int position){

        return modifiedAddressEdificationDwellers.get(position).hashCode();

    }


    public void setModifiedAddressEdificationDwellers(List<ModifiedAddressEdificationDwellerModel> modifiedAddressEdificationDwellers){

        this.modifiedAddressEdificationDwellers = modifiedAddressEdificationDwellers;

        notifyDataSetChanged();

    }


    public class ModifiedAddressEdificationDwellerViewHolder extends RecyclerView.ViewHolder {

        public ModifiedAddressEdificationDwellerModel modifiedAddressEdificationDwellerModel;

        public TextView nameOrDenomination;

        public TextView from;

        public ModifiedAddressEdificationDwellerViewHolder(View view) {

            super(view);

            nameOrDenomination = view.findViewById(R.id.fragment_modified_address_edification_dweller_list_nameOrDenomination_textview);

            from = view.findViewById(R.id.fragment_modified_address_edification_dweller_list_from_textview);

        }

    }


}