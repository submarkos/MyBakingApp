package com.udacity.dakosonogov.mybakingapp.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.dakosonogov.mybakingapp.R;

import com.udacity.dakosonogov.mybakingapp.models.Recipe;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder> {

    private ArrayList<Recipe> recipes;
    private Context context;
    private RecipeAdapterClickListener recipeAdapterClickListener;

    public interface RecipeAdapterClickListener {
        void onClick (int clickedRecipeIndex);
    }
    public RecipeAdapter(RecipeAdapterClickListener listener) {
        recipeAdapterClickListener = listener;
    }
    @NonNull
    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutIdForRecipeItems = R.layout.recipe_card;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(layoutIdForRecipeItems,parent,false);
        return new RecipeAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapterViewHolder holder, int position) {
        holder.recipeName.setText(recipes.get(position).getName());
        String imageRecipeUrl = recipes.get(position).getImage();
        Uri uri = Uri.parse(imageRecipeUrl).buildUpon().build();
            Picasso.get()
                    .load(uri)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .into(holder.recipeImage);
    }

    @Override
    public int getItemCount() {
        if (recipes == null)return 0;
        else return recipes.size();
    }

    public void setRecipesData(ArrayList<Recipe> recipesForLoad, Context currentContext) {
        recipes = recipesForLoad;
        context = currentContext;
        notifyDataSetChanged();
    }

    class RecipeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView recipeName;
        ImageView recipeImage;

        RecipeAdapterViewHolder (View itemView) {
            super(itemView);
            recipeName =itemView.findViewById(R.id.name_recipe);
            recipeImage = itemView.findViewById(R.id.image_icon);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            recipeAdapterClickListener.onClick(clickedPosition);
        }
    }
}

