package br.com.developen.sig.widget;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.developen.sig.fragment.ModifiedAddressEdificationFragment;
import br.com.developen.sig.R;
import br.com.developen.sig.database.ModifiedAddressEdificationModel;

public class ModifiedAddressEdificationRecyclerViewAdapter
        extends RecyclerView.Adapter<ModifiedAddressEdificationRecyclerViewAdapter.ModifiedAddressEdificationViewHolder> {


    public static final int HOUSE_EDIFICATION = 1;


    private List<ModifiedAddressEdificationModel> modifiedAddressEdifications;

    private ModifiedAddressEdificationFragment.EdificationFragmentListener fragmentListener;


    public ModifiedAddressEdificationRecyclerViewAdapter(List<ModifiedAddressEdificationModel> modifiedAddressEdifications, ModifiedAddressEdificationFragment.EdificationFragmentListener listener) {

        this.modifiedAddressEdifications = modifiedAddressEdifications;

        this.fragmentListener = listener;

        setHasStableIds(true);

    }


    public ModifiedAddressEdificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layout = 0;

        switch (viewType){

            case HOUSE_EDIFICATION: layout = R.layout.fragment_modified_address_edification_list_house;
                break;

        }

        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);

        return new ModifiedAddressEdificationViewHolder(view);

    }


    public int getItemViewType(int position) {

        switch (modifiedAddressEdifications.get(position).getType()){

            case 1: return HOUSE_EDIFICATION;

            default:

                return 0;

        }

    }


    @SuppressLint("SetTextI18n")
    public void onBindViewHolder(final ModifiedAddressEdificationViewHolder holder, int position) {

        holder.modifiedAddressEdificationModel = modifiedAddressEdifications.get(position);

        holder.title.setText(modifiedAddressEdifications.get(position).getEdification().toString());

/*      holder.dwellersCount.setText("x" +
                StringUtils.formatQuantity(modifiedAddressEdifications.get(position).getQuantity()) + " " +
                modifiedAddressEdifications.get(position).getMeasureUnit().getAcronym());

        holder.dwellersCount.setVisibility(modifiedAddressEdifications.get(position).getQuantity() > 0 ? View.VISIBLE : View.GONE); */

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (fragmentListener != null)

                    fragmentListener.onEdificationClicked(holder.modifiedAddressEdificationModel);

            }

        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {

                if (fragmentListener != null)

                    fragmentListener.onEdificationLongClick(holder.modifiedAddressEdificationModel);

                return false;

            }

        });

    }


    public int getItemCount() {

        return modifiedAddressEdifications.size();

    }


    public long getItemId(int position){

        return modifiedAddressEdifications.get(position).hashCode();

    }


    public void setModifiedAddressEdifications(List<ModifiedAddressEdificationModel> modifiedAddressEdifications){

        this.modifiedAddressEdifications = modifiedAddressEdifications;

        notifyDataSetChanged();

    }


    public class ModifiedAddressEdificationViewHolder extends RecyclerView.ViewHolder {

        public ModifiedAddressEdificationModel modifiedAddressEdificationModel;

        public TextView dwellersCount;

        public TextView title;

        public ModifiedAddressEdificationViewHolder(View view) {

            super(view);

            dwellersCount = view.findViewById(R.id.fragment_modified_address_edification_list_dwellers_count_textview);

            title = view.findViewById(R.id.fragment_modified_address_edification_list_title_textview);

        }

    }


}