package com.lotusdev.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.ViewHolder> {
    private final ArrayList<CatagoryRVModel> catagoryRVModels;
    public final Context context;
    private final CategoryClickInterface categoryClickInterface;

    public CategoryRVAdapter(ArrayList<CatagoryRVModel> catagoryRVModels, Context context, CategoryClickInterface categoryClickInterface) {
        this.catagoryRVModels = catagoryRVModels;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public CategoryRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_news,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRVAdapter.ViewHolder holder, int position) {
            CatagoryRVModel catagoryRVModel = catagoryRVModels.get(position);
            holder.categ.setText(catagoryRVModel.getCategory());
        Picasso.get().load(catagoryRVModel.getCatagoryImageUrl()).into(holder.img);
        holder.itemView.setOnClickListener(v -> categoryClickInterface.onCategoryClickInterface(position));
    }

    @Override
    public int getItemCount() {
        return catagoryRVModels.size();
    }
    public interface CategoryClickInterface{
        void onCategoryClickInterface(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView categ;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categ = itemView.findViewById(R.id.categoriestext);
            img = itemView.findViewById(R.id.categoriesImag);
        }
    }
}
