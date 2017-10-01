package com.kelvinhado.privy.privies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kelvinhado.privy.R;
import com.kelvinhado.privy.data.Privy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kelvin on 01/10/2017.
 */

class PriviesListAdapter extends RecyclerView.Adapter<PriviesListAdapter.PrivyViewHolder> {

    private List<Privy> mPrivyItems;

    private ListItemClickListener mListener;

    public PriviesListAdapter(List<Privy> privyItems, ListItemClickListener listener) {
        mPrivyItems = privyItems;
        mListener = listener;
    }

    @Override
    public PriviesListAdapter.PrivyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.privies_list_item, viewGroup, false);
        ButterKnife.bind(this, view);
        return new PriviesListAdapter.PrivyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PriviesListAdapter.PrivyViewHolder holder, int position) {
        Privy privy = mPrivyItems.get(position);
        holder.itemView.setTag(privy.getId());
        holder.bind(privy);
    }

    @Override
    public int getItemCount() {
        return mPrivyItems.size();
    }

    public void swap(List<Privy> list){
        if (mPrivyItems != null) {
            mPrivyItems.clear();
            mPrivyItems.addAll(list);
        }
        else {
            mPrivyItems = list;
        }
        notifyDataSetChanged();
    }

    public interface ListItemClickListener {
        void onListItemClicked(int itemPosition);
    }

    class PrivyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_tv_address)
        TextView address;
        @BindView(R.id.item_tv_opening_hour)
        TextView openingHour;

        public PrivyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void bind(Privy privy) {
            address.setText(privy.getAddressName().toLowerCase());
            openingHour.setText(privy.getOpeningHours().toLowerCase());
        }

        @Override
        public void onClick(View view) {
            mListener.onListItemClicked(getAdapterPosition());
        }
    }
}