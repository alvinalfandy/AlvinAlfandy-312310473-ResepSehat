package com.example.resepsehat.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.resepsehat.R;
import com.example.resepsehat.ResepDetailActivity;
import com.example.resepsehat.model.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context context;
    private List<Recipe> recipeList;
    private List<Recipe> filteredList;

    public RecipeAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
        this.filteredList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe currentRecipe = filteredList.get(position);

        holder.recipeTitleTextView.setText(currentRecipe.getName());
        holder.recipeDescriptionTextView.setText(currentRecipe.getDescription());
        holder.recipeImageView.setImageResource(currentRecipe.getImageResId());
        holder.recipeTimeTextView.setText(currentRecipe.getTime());
        holder.recipeCaloriesTextView.setText(currentRecipe.getCalories());

        holder.viewRecipeButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, ResepDetailActivity.class);
            intent.putExtra("title", currentRecipe.getName());
            intent.putExtra("image", currentRecipe.getImageResId());
            intent.putExtra("bahan", currentRecipe.getBahan());
            intent.putExtra("cara", currentRecipe.getCara());
            intent.putExtra("time", currentRecipe.getTime());
            intent.putExtra("calories", currentRecipe.getCalories());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void setFilteredList(List<Recipe> filteredList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new RecipeDiffCallback(this.filteredList, filteredList));
        this.filteredList = filteredList;
        diffResult.dispatchUpdatesTo(this);
    }

    public void updateData(List<Recipe> newData) {
        this.filteredList = newData;
        notifyDataSetChanged();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView recipeTitleTextView;
        TextView recipeDescriptionTextView;
        TextView recipeTimeTextView;
        TextView recipeCaloriesTextView;
        ImageView recipeImageView;
        Button viewRecipeButton;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            recipeTitleTextView = itemView.findViewById(R.id.recipe_title);
            recipeDescriptionTextView = itemView.findViewById(R.id.recipe_description);
            recipeImageView = itemView.findViewById(R.id.recipe_image);
            recipeTimeTextView = itemView.findViewById(R.id.tvTime);
            recipeCaloriesTextView = itemView.findViewById(R.id.tvCalories);
            viewRecipeButton = itemView.findViewById(R.id.btn_view_recipe);
        }
    }

    private static class RecipeDiffCallback extends DiffUtil.Callback {

        private final List<Recipe> oldList;
        private final List<Recipe> newList;

        public RecipeDiffCallback(List<Recipe> oldList, List<Recipe> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
        }
    }
}
