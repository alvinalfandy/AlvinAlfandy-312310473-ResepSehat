package com.example.resepsehat;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.resepsehat.helper.FavoriteHelper;
import com.example.resepsehat.model.Recipe;

public class ResepDetailActivity extends AppCompatActivity {
    private FavoriteHelper favoriteHelper;
    private Recipe currentRecipe;
    private Button btnTambahFavorit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Menonaktifkan mode gelap
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_recipe_detail);

        favoriteHelper = new FavoriteHelper(this);

        Intent intent = getIntent();
        String recipeTitle = intent.getStringExtra("title");
        String recipeBahan = intent.getStringExtra("bahan");
        String recipeCara = intent.getStringExtra("cara");
        int recipeImage = intent.getIntExtra("image", R.drawable.nasigoreng);
        String recipeTime = intent.getStringExtra("time");
        String recipeCalories = intent.getStringExtra("calories");

        // Mendapatkan ID resep berdasarkan judul
        int recipeId = getRecipeIdByTitle(recipeTitle);

        currentRecipe = new Recipe(
                recipeId,
                recipeTitle,
                "", // description
                recipeTime,
                recipeCalories,
                recipeImage,
                recipeBahan,
                recipeCara
        );

        TextView titleTextView = findViewById(R.id.tvRecipeTitle);
        ImageView recipeImageView = findViewById(R.id.ivRecipeImage);
        TextView bahanTextView = findViewById(R.id.tvBahan);
        TextView caraTextView = findViewById(R.id.tvCaraPembuatan);
        btnTambahFavorit = findViewById(R.id.btnTambahFavorit);
        Button backButton = findViewById(R.id.btnKembali);

        titleTextView.setText(recipeTitle);
        recipeImageView.setImageResource(recipeImage);
        bahanTextView.setText(recipeBahan);
        caraTextView.setText(recipeCara);

        updateFavoriteButtonState();

        btnTambahFavorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favoriteHelper.isInFavorites(currentRecipe.getId())) {
                    favoriteHelper.removeFromFavorites(currentRecipe.getId());
                    Toast.makeText(ResepDetailActivity.this,
                            "Dihapus dari favorit", Toast.LENGTH_SHORT).show();
                } else {
                    favoriteHelper.addToFavorites(currentRecipe);
                    Toast.makeText(ResepDetailActivity.this,
                            "Ditambahkan ke favorit", Toast.LENGTH_SHORT).show();
                }
                updateFavoriteButtonState();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private int getRecipeIdByTitle(String title) {
        switch (title) {
            case "Salad Quinoa dengan Alpukat dan Edamame":
                return 1;
            case "Sup Krim Brokoli dan Kentang":
                return 2;
            case "Smoothie Berry Chia":
                return 3;
            case "Infused Water Lemon dan Mentimun":
                return 4;
            case "Bowl Buddha Sayuran Panggang":
                return 5;
            case "Salmon Panggang Lemon Dill":
                return 6;
            case "Oatmeal Protein Bowl":
                return 7;
            case "Wrap Tuna Mediterranean":
                return 8;
            case "Green Detox Smoothie":
                return 9;
            case "Matcha Energi Boost":
                return 10;
            default:
                return -1;
        }
    }

    private void updateFavoriteButtonState() {
        if (currentRecipe != null && favoriteHelper.isInFavorites(currentRecipe.getId())) {
            btnTambahFavorit.setText("Hapus dari Favorit");
            btnTambahFavorit.setBackgroundTintList(ColorStateList.valueOf(
                    getResources().getColor(android.R.color.holo_red_light)));
        } else {
            btnTambahFavorit.setText("Tambahkan ke Favorit");
            btnTambahFavorit.setBackgroundTintList(ColorStateList.valueOf(
                    getResources().getColor(R.color.colorPrimary)));
        }
    }
}