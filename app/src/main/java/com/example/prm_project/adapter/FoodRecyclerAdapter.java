package com.example.prm_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm_project.model.Food;
import com.example.prm_project.R;

import java.util.List;

public class FoodRecyclerAdapter extends RecyclerView.Adapter<FoodRecyclerAdapter.FoodViewHolder> {

    private List<Food> foodList;
    private Context context;

    public FoodRecyclerAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bestseller_item, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.dishName.setText(food.getName());
        holder.dishDescription.setText(food.getDescription());
        holder.price.setText(food.getPrice().toString()); // Display price
        // Set image (optional) if you have a method to load images
        holder.dishImage.setImageResource(R.drawable.dish_beefsteak); // Temporary placeholder image
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView dishName, dishDescription, price;
        ImageView dishImage;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            dishName = itemView.findViewById(R.id.dish_name);
            dishDescription = itemView.findViewById(R.id.dish_description);
            price = itemView.findViewById(R.id.price);
            dishImage = itemView.findViewById(R.id.img_dish);
        }
    }
}