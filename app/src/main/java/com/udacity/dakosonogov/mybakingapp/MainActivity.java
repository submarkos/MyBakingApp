package com.udacity.dakosonogov.mybakingapp;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.udacity.dakosonogov.mybakingapp.adapters.RecipeAdapter;
import com.udacity.dakosonogov.mybakingapp.api.API;
import com.udacity.dakosonogov.mybakingapp.api.RestClient;
import com.udacity.dakosonogov.mybakingapp.models.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity  extends AppCompatActivity implements RecipeAdapter.RecipeAdapterClickListener{
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recipes);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
        } else {
            GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(layoutManager);
        }
        getRecipes();
    }

    private void getRecipes() {
        RestClient restClient = new RestClient();
        API api =
                restClient.getClient().create(API.class);
        Call<ArrayList<Recipe>> call = api.getRecipes();
        call.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                ArrayList<Recipe> recipes = response.body();
                recipeAdapter = new RecipeAdapter(MainActivity.this);
                recipeAdapter.setRecipesData(recipes, MainActivity.this);
                recyclerView.setAdapter(recipeAdapter);
                recipeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(int clickedRecipeIndex) {

    }
}
