package com.udacity.dakosonogov.mybakingapp.api;

import com.udacity.dakosonogov.mybakingapp.models.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("baking.json")
    Call<ArrayList<Recipe>> getRecipes();
}
