package com.murgray.savehouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class itemsViewAdapter extends RecyclerView.Adapter<itemsViewAdapter.ViewHolder> {

    ArrayList<Integer> itemID;
    ArrayList<String> itemNames;
    ArrayList<String> brands;
    ArrayList<Double> prices;
    ArrayList<String> types;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;


    itemsViewAdapter(Context context, ArrayList<Integer> itemID,
                     ArrayList<String>  itemNames,
                     ArrayList<String> brands,
                     ArrayList<Double>  prices,
                     ArrayList<String> types) {

        this.mInflater = LayoutInflater.from(context);
        this.itemID = itemID;
        this.itemNames = itemNames;
        this.brands = brands;
        this.prices = prices;
        this.types = types;
        saveHouseObject allItems = new saveHouseObject(itemID,itemNames,brands,prices,types);
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.items_recycler_layout, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String itemName = itemNames.get(position);
        holder.itemTextView.setText(itemName);

        String brand = brands.get(position);
        holder.brandTextView.setText(brand);

        String price = "$" + prices.get(position);
        holder.priceTextView.setText(price);

        String type = types.get(position);
        holder.typeTextView.setText(type);


    }

    // total number of rows
    @Override
    public int getItemCount() {
        return -1;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemTextView;
        TextView brandTextView;
        TextView priceTextView;
        TextView typeTextView;

        ViewHolder(View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.itemNameTextView);
            priceTextView =  itemView.findViewById(R.id.priceTextView);
            brandTextView = itemView.findViewById(R.id.brandTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return itemNames.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
