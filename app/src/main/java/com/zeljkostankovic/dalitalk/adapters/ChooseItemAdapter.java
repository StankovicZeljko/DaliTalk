package com.zeljkostankovic.dalitalk.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zeljkostankovic.dalitalk.R;
import com.zeljkostankovic.dalitalk.modules.Item;

import java.util.Collections;
import java.util.List;

public class ChooseItemAdapter extends RecyclerView.Adapter<ChooseItemAdapter.ViewHolder> {

    private List<Item> itemList = Collections.emptyList();


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView description;
        TextView task;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.chooseListElementDesc);
            task = itemView.findViewById(R.id.chooseListElementTask);
            imageView = itemView.findViewById(R.id.chooseListElementImageView);

        }

        public TextView getDescription() {
            return description;
        }

        public TextView getTask() {
            return task;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_choose_item_list_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(holder.itemView).load(itemList.get(position).imageUrl).into(holder.imageView);
        holder.getDescription().setText(itemList.get(position).description);
        holder.getTask().setText(itemList.get(position).task);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Item> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }



}
