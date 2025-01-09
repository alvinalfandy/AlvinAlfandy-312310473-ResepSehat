package com.example.resepsehat;

import android.content.Intent;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.resepsehat.adapter.RecipeAdapter;
import com.example.resepsehat.model.Recipe;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recipeRecyclerView;
    private SearchView searchView;
    private Chip btnSemua, btnMakanan, btnMinuman;

    private List<Recipe> recipeList;
    private List<Recipe> filteredRecipeList;
    private RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate: Aplikasi dimulai");

        initializeViews();
        setupRecyclerView();
        loadRecipeData();
        setupNavigationAndListeners();

        // Set default style untuk Chip Semua
        setActiveChip(btnSemua);
    }

    private void initializeViews() {
        recipeRecyclerView = findViewById(R.id.recyclerViewResep);
        btnSemua = findViewById(R.id.btnSemua);
        btnMakanan = findViewById(R.id.btnMakanan);
        btnMinuman = findViewById(R.id.btnMinuman);
        searchView = findViewById(R.id.searchView);
    }

    private void setupRecyclerView() {
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipeList = new ArrayList<>();
        filteredRecipeList = new ArrayList<>();
        recipeAdapter = new RecipeAdapter(this, filteredRecipeList);
        recipeRecyclerView.setAdapter(recipeAdapter);
    }

    private void loadRecipeData() {
        // Menambahkan data resep
        recipeList.add(new Recipe(
                1,
                "Salad Quinoa dengan Alpukat dan Edamame",
                "Salad sehat kaya nutrisi, dibuat dari quinoa, alpukat, dan edamame. Cocok untuk diet seimbang dan penuh energi.",
                "30 menit",
                "320 kcal",
                R.drawable.saladquinoa,
                "- 100 gram quinoa\n- 1 buah alpukat (potong dadu)\n- 50 gram edamame (kupas)\n- 1 buah tomat (potong kecil)\n- 1 sdm minyak zaitun\n- Perasan lemon secukupnya\n- Garam dan lada secukupnya",
                "1. Cuci quinoa hingga bersih untuk menghilangkan rasa pahit alami. Masak quinoa dalam air mendidih selama 15-20 menit hingga matang dan air terserap.\n" +
                        "2. Kukus edamame hingga matang, kemudian kupas kulitnya.\n" +
                        "3. Potong alpukat dan tomat menjadi ukuran kecil sesuai selera.\n" +
                        "4. Campurkan quinoa yang telah matang dengan alpukat, edamame, dan tomat dalam mangkuk besar.\n" +
                        "5. Tambahkan minyak zaitun, perasan lemon, garam, dan lada. Aduk rata hingga semua bahan tercampur.\n" +
                        "6. Sajikan salad dalam piring saji. Bisa dinikmati langsung atau disimpan sebentar di kulkas untuk sensasi yang lebih segar."
        ));

        recipeList.add(new Recipe(
                2,
                "Sup Krim Brokoli dan Kentang",
                "Sup krim hangat yang terbuat dari brokoli dan kentang, kaya serat dan rendah kalori. Cocok untuk makan malam yang sehat.",
                "40 menit",
                "250 kcal",
                R.drawable.supkrim,
                "- 200 gram brokoli (cuci bersih, potong kecil)\n- 1 buah kentang (potong dadu kecil)\n- 1 siung bawang putih (cincang halus)\n- 1/2 buah bawang bombay (cincang kasar)\n- 200 ml kaldu sayur\n- 100 ml susu rendah lemak\n- 1 sdm minyak zaitun\n- Garam dan lada secukupnya",
                "1. Panaskan minyak zaitun dalam panci, tumis bawang putih dan bawang bombay hingga harum dan berwarna kecokelatan.\n" +
                        "2. Tambahkan kentang yang telah dipotong dadu, tumis selama 2-3 menit hingga kentang sedikit melunak.\n" +
                        "3. Masukkan brokoli ke dalam panci, aduk rata bersama kentang dan bumbu.\n" +
                        "4. Tuangkan kaldu sayur ke dalam panci, tutup, dan biarkan semua bahan mendidih selama 15-20 menit hingga sayuran matang dan empuk.\n" +
                        "5. Gunakan blender atau hand blender untuk menghaluskan campuran sayuran hingga teksturnya menjadi lembut seperti krim.\n" +
                        "6. Tambahkan susu rendah lemak ke dalam sup yang sudah dihaluskan, aduk rata. Panaskan kembali dengan api kecil hingga mendidih.\n" +
                        "7. Beri sedikit garam dan lada sesuai selera. Sajikan sup krim brokoli dan kentang dalam mangkuk saji hangat."
        ));

        recipeList.add(new Recipe(
                3,
                "Smoothie Berry Chia",
                "Minuman sehat dan menyegarkan, dibuat dari campuran buah berry dan chia seeds yang kaya antioksidan.",
                "10 menit",
                "180 kcal",
                R.drawable.smoothieberry,
                "- 100 gram stroberi (cuci bersih)\n- 50 gram blueberry (cuci bersih)\n- 1 sdm chia seeds (rendam dalam air selama 10 menit)\n- 200 ml susu almond\n- 1-2 sdm madu (sesuai selera)\n- Es batu secukupnya",
                "1. Rendam chia seeds dalam air selama 10 menit hingga mengembang dan berbentuk seperti gel.\n" +
                        "2. Masukkan stroberi, blueberry, susu almond, chia seeds yang telah direndam, dan madu ke dalam blender.\n" +
                        "3. Blender semua bahan hingga halus dan tercampur rata.\n" +
                        "4. Jika ingin minuman lebih dingin, tambahkan es batu ke dalam blender dan haluskan bersama bahan lainnya.\n" +
                        "5. Tuang smoothie ke dalam gelas, hias dengan potongan buah di atasnya jika diinginkan. Sajikan segera."
        ));

        recipeList.add(new Recipe(
                4,
                "Infused Water Lemon dan Mentimun",
                "Minuman rendah kalori yang menyegarkan, dibuat dari kombinasi lemon, mentimun, dan daun mint. Cocok untuk detoks tubuh.",
                "10 menit",
                "10 kcal",
                R.drawable.infusedwater,
                "- 1 liter air mineral\n- 1 buah lemon (iris tipis)\n- 1/2 buah mentimun (iris tipis)\n- Beberapa lembar daun mint segar\n- Es batu secukupnya",
                "1. Cuci lemon, mentimun, dan daun mint hingga bersih untuk menghilangkan kotoran.\n" +
                        "2. Iris tipis lemon dan mentimun, pastikan ukurannya merata agar mudah melepaskan rasa.\n" +
                        "3. Masukkan irisan lemon, mentimun, dan daun mint ke dalam wadah besar atau botol minum.\n" +
                        "4. Tuangkan air mineral ke dalam wadah, lalu tambahkan es batu jika diinginkan.\n" +
                        "5. Simpan di dalam kulkas selama 2-4 jam agar rasa dari bahan-bahan tercampur dengan air.\n" +
                        "6. Sajikan infused water dingin untuk hasil terbaik."
        ));
        recipeList.add(new Recipe(
                5,
                "Bowl Buddha Sayuran Panggang",
                "Bowl sehat dengan campuran sayuran panggang, quinoa, dan saus tahini. Kaya serat dan protein nabati.",
                "45 menit",
                "380 kcal",
                R.drawable.buddhabowl,
                "- 100g sweet potato (potong dadu)\n- 100g brokoli\n- 100g quinoa\n- 1 buah wortel (potong panjang)\n- 50g bayam\n- 2 sdm tahini\n- 1 sdm minyak zaitun\n- Garam dan lada hitam secukupnya",
                "1. Panaskan oven di suhu 200°C.\n2. Tata sweet potato dan wortel di loyang, olesi minyak zaitun, panggang 20 menit.\n3. Tambahkan brokoli, panggang 10 menit lagi.\n4. Masak quinoa dengan rasio 1:2 hingga empuk.\n5. Siapkan saus: campurkan tahini dengan air lemon dan sedikit air.\n6. Susun quinoa, sayuran panggang, dan bayam segar dalam mangkuk.\n7. Siram dengan saus tahini, beri garam dan lada."
        ));

        recipeList.add(new Recipe(
                6,
                "Salmon Panggang Lemon Dill",
                "Salmon panggang sehat dengan bumbu lemon dan dill. Kaya omega-3 dan protein.",
                "25 menit",
                "290 kcal",
                R.drawable.salmon,
                "- 200g fillet salmon\n- 1 buah lemon\n- 2 sdm minyak zaitun\n- 2 siung bawang putih (cincang)\n- Fresh dill\n- Asparagus\n- Garam dan lada hitam",
                "1. Marinasi salmon dengan jus lemon, minyak zaitun, bawang putih, dill.\n2. Diamkan 15 menit.\n3. Panaskan oven 180°C.\n4. Panggang salmon 15-20 menit.\n5. Panggang asparagus dengan sedikit minyak zaitun.\n6. Sajikan salmon dengan asparagus dan hiasan dill segar."
        ));

        recipeList.add(new Recipe(
                7,
                "Oatmeal Protein Bowl",
                "Sarapan sehat dengan oatmeal, buah-buahan, dan protein tambahan.",
                "15 menit",
                "320 kcal",
                R.drawable.oatmeal,
                "- 50g oat\n- 200ml susu almond\n- 1 scoop protein bubuk\n- 1 buah pisang\n- Blueberry\n- 1 sdm almond butter\n- Madu secukupnya",
                "1. Masak oat dengan susu almond hingga mengental.\n2. Tambahkan protein bubuk, aduk rata.\n3. Tata dalam mangkuk.\n4. Hias dengan pisang iris, blueberry.\n5. Tambahkan almond butter dan madu."
        ));

        recipeList.add(new Recipe(
                8,
                "Wrap Tuna Mediterranean",
                "Wrap sehat dengan tuna dan sayuran Mediterranean.",
                "20 menit",
                "310 kcal",
                R.drawable.tunawrap,
                "- Tortilla whole wheat\n- 150g tuna kaleng\n- Selada\n- Tomat cherry\n- Timun\n- Zaitun hitam\n- Yogurt Greek\n- Oregano",
                "1. Campur tuna dengan yogurt Greek dan oregano.\n2. Hangatkan tortilla.\n3. Tata selada, campuran tuna.\n4. Tambahkan tomat, timun, zaitun.\n5. Gulung rapi dan potong diagonal."
        ));

        recipeList.add(new Recipe(
                9,
                "Green Detox Smoothie",
                "Smoothie detox dengan sayuran hijau dan buah.",
                "10 menit",
                "150 kcal",
                R.drawable.greendetox,
                "- Bayam\n- 1 buah apel\n- 1 batang seledri\n- 1/2 lemon\n- Jahe\n- Air kelapa\n- Madu",
                "1. Cuci semua bahan.\n2. Masukkan ke blender.\n3. Tambahkan air kelapa.\n4. Blend hingga halus.\n5. Tambahkan madu sesuai selera."
        ));

        recipeList.add(new Recipe(
                10,
                "Matcha Energi Boost",
                "Minuman sehat dengan matcha dan superfoods.",
                "5 menit",
                "130 kcal",
                R.drawable.matcha,
                "- 1 sdt matcha powder\n- 200ml susu almond\n- 1 sdt madu\n- 1/4 sdt spirulina\n- Es batu",
                "1. Aduk matcha dengan sedikit air panas.\n2. Tambahkan susu almond.\n3. Masukkan spirulina, madu.\n4. Kocok hingga rata.\n5. Sajikan dengan es batu."
        ));

        filteredRecipeList.addAll(recipeList);
    }

    private void setupNavigationAndListeners() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                return true;
            } else if (itemId == R.id.navigation_favorite) {
                startActivity(new Intent(MainActivity.this, FavoriteActivity.class));
                return true;
            }
            return false;
        });

        setupSearchView();
        setupCategoryButtons();
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Mencari: " + query, Toast.LENGTH_SHORT).show();
                    filterRecipes(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    filteredRecipeList.clear();
                    filteredRecipeList.addAll(recipeList);
                } else {
                    filterRecipes(newText);
                }
                recipeAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    private void setupCategoryButtons() {
        btnSemua.setOnClickListener(view -> {
            setActiveChip(btnSemua);
            filteredRecipeList.clear();
            filteredRecipeList.addAll(recipeList);
            recipeAdapter.notifyDataSetChanged();
        });

        btnMakanan.setOnClickListener(view -> {
            setActiveChip(btnMakanan);
            List<Recipe> makananList = new ArrayList<>();
            makananList.add(recipeList.get(0));
            makananList.add(recipeList.get(1));
            makananList.add(recipeList.get(4));
            makananList.add(recipeList.get(5));
            makananList.add(recipeList.get(6));
            makananList.add(recipeList.get(7));
            filteredRecipeList.clear();
            filteredRecipeList.addAll(makananList);
            recipeAdapter.notifyDataSetChanged();
        });

        btnMinuman.setOnClickListener(view -> {
            setActiveChip(btnMinuman);
            List<Recipe> minumanList = new ArrayList<>();
            minumanList.add(recipeList.get(2));
            minumanList.add(recipeList.get(3));
            minumanList.add(recipeList.get(8));
            minumanList.add(recipeList.get(9));
            filteredRecipeList.clear();
            filteredRecipeList.addAll(minumanList);
            recipeAdapter.notifyDataSetChanged();
        });
    }

    private void setActiveChip(Chip selectedChip) {
        btnSemua.setChipBackgroundColor(ColorStateList.valueOf(Color.WHITE));
        btnSemua.setTextColor(Color.parseColor("#669900"));
        btnMakanan.setChipBackgroundColor(ColorStateList.valueOf(Color.WHITE));
        btnMakanan.setTextColor(Color.parseColor("#669900"));
        btnMinuman.setChipBackgroundColor(ColorStateList.valueOf(Color.WHITE));
        btnMinuman.setTextColor(Color.parseColor("#669900"));

        selectedChip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#669900")));
        selectedChip.setTextColor(Color.WHITE);
    }

    private void filterRecipes(String query) {
        List<Recipe> filteredList = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            if (recipe.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(recipe);
            }
        }
        filteredRecipeList.clear();
        filteredRecipeList.addAll(filteredList);
        recipeAdapter.notifyDataSetChanged();
    }
}