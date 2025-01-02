package com.example.resepsehat.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.example.resepsehat.model.Recipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavoriteHelper {
    private static final String PREF_NAME = "RecipeFavorites";
    private static final String KEY_FAVORITES = "favorites";
    private final SharedPreferences preferences;
    private final Gson gson;

    public FavoriteHelper(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void addToFavorites(Recipe recipe) {
        List<Recipe> favorites = getFavorites();
        if (!isInFavorites(recipe.getId())) {
            favorites.add(recipe);
            saveFavorites(favorites);
        }
    }

    public void removeFromFavorites(int recipeId) {
        List<Recipe> favorites = getFavorites();
        favorites.removeIf(recipe -> recipe.getId() == recipeId);
        saveFavorites(favorites);
    }

    public boolean isInFavorites(int recipeId) {
        List<Recipe> favorites = getFavorites();
        for (Recipe recipe : favorites) {
            if (recipe.getId() == recipeId) {
                return true;
            }
        }
        return false;
    }

    public List<Recipe> getFavorites() {
        String json = preferences.getString(KEY_FAVORITES, null);
        if (json == null) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<List<Recipe>>(){}.getType();
        return gson.fromJson(json, type);
    }

    private void saveFavorites(List<Recipe> favorites) {
        String json = gson.toJson(favorites);
        preferences.edit().putString(KEY_FAVORITES, json).apply();
    }
}