package com.example.resepsehat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate; // Import untuk AppCompatDelegate

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Menonaktifkan mode gelap
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_splash);

        // Timer untuk menampilkan logo beberapa detik
        new Handler().postDelayed(() -> {
            // Pindah ke MainActivity setelah 2 detik
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Tutup SplashActivity
        }, 2000); // 2000ms = 2 detik
    }
}
