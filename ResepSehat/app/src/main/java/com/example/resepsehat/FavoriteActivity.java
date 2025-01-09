package com.example.resepsehat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.resepsehat.adapter.RecipeAdapter;
import com.example.resepsehat.model.Recipe;
import com.example.resepsehat.helper.FavoriteHelper;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView tvEmpty;
    private TextView tvTotalRecipes;
    private TextView tvTotalCalories;
    private View emptyStateContainer;
    private Button btnExplore;
    private RecipeAdapter adapter;
    private FavoriteHelper favoriteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        initializeViews();
        setupBottomNavigation();
        setupRecyclerView();
        setupExploreButton();
        loadFavorites();
    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.recyclerViewFavorite);
        tvEmpty = findViewById(R.id.tvEmpty);
        tvTotalRecipes = findViewById(R.id.tvTotalRecipes);
        tvTotalCalories = findViewById(R.id.tvTotalCalories);
        emptyStateContainer = findViewById(R.id.emptyStateContainer);
        btnExplore = findViewById(R.id.btnExplore);
        favoriteHelper = new FavoriteHelper(this);
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.navigation_favorite);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_home) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return true;
            }
            return item.getItemId() == R.id.navigation_favorite;
        });
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecipeAdapter(this, favoriteHelper.getFavorites());
        recyclerView.setAdapter(adapter);
    }

    private void setupExploreButton() {
        btnExplore.setOnClickListener(v -> {
            startActivity(new Intent(FavoriteActivity.this, MainActivity.class));
            finish();
        });
    }

    private void loadFavorites() {
        List<Recipe> favorites = favoriteHelper.getFavorites();
        updateUI(favorites);
    }

    private void updateUI(List<Recipe> favorites) {
        if (favorites.isEmpty()) {
            showEmptyState();
        } else {
            showFavorites(favorites);
        }
        updateStats(favorites);
    }

    private void showEmptyState() {
        emptyStateContainer.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    private void showFavorites(List<Recipe> favorites) {
        emptyStateContainer.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.updateData(favorites);
    }

    private void updateStats(List<Recipe> favorites) {
        int totalRecipes = favorites.size();
        int totalCalories = calculateTotalCalories(favorites);

        tvTotalRecipes.setText(String.valueOf(totalRecipes));
        tvTotalCalories.setText(String.valueOf(totalCalories));
    }

    private int calculateTotalCalories(List<Recipe> recipes) {
        int total = 0;
        for (Recipe recipe : recipes) {
            String calories = recipe.getCalories().replaceAll("[^0-9]", ""); // Remove non-numeric characters
            try {
                total += Integer.parseInt(calories);
            } catch (NumberFormatException e) {
                // Skip if calories can't be parsed
            }
        }
        return total;
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFavorites();
    }
}