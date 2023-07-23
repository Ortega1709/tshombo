package com.ortega.tshombo.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.ortega.tshombo.R;
import com.ortega.tshombo.models.TelephoneModel;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.MyViewHolder>{

    ClickListener clickListener;
    Context context;
    ArrayList<TelephoneModel> telephoneModelArrayList;

    public PhoneAdapter(Context context, ArrayList<TelephoneModel> telephoneModelArrayList, ClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
        this.telephoneModelArrayList = telephoneModelArrayList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFilteredList(ArrayList<TelephoneModel> filteredList) {
        this.telephoneModelArrayList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.phone_item, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(telephoneModelArrayList.get(position), clickListener);

        TelephoneModel telephoneModel = telephoneModelArrayList.get(position);
        holder.phonePrice.setText(telephoneModel.getPrix() + " Fc");
        holder.phoneMarque.setText(telephoneModel.getNom());

        Picasso.get()
                .load(Uri.parse(telephoneModel.getPhoto()))
                .centerCrop()
                .resize(1000,1000)
                .into(holder.phoneSheapeImg);

    }


    @Override
    public int getItemCount() {
        return telephoneModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ShapeableImageView phoneSheapeImg;
        TextView phoneMarque;
        TextView phonePrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            phoneSheapeImg = itemView.findViewById(R.id.phoneSheapeImg);
            phoneMarque = itemView.findViewById(R.id.phoneMarque);
            phonePrice = itemView.findViewById(R.id.phonePrice);
        }

        public void bind(final TelephoneModel telephoneModel, final ClickListener listener) {
            itemView.setOnClickListener(v -> listener.onItemClick(telephoneModel));
        }

    }

    public interface ClickListener {
        void onItemClick(TelephoneModel telephoneModel);
    }

}
