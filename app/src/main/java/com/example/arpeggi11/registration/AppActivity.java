package com.example.arpeggi11.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.arpeggi11.fragments.CheckInFragment;
import com.example.arpeggi11.fragments.HomeFragment;
import com.example.arpeggi11.fragments.ProfileFragment;
import com.example.arpeggi11.R;
import com.example.arpeggi11.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
/*
Активити, отвечающий за отображение и распределение кнопок BottomNavigationMenu
 */

public class AppActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.menuItemHome);
    }
    HomeFragment homeFragment = new HomeFragment();
    SettingsFragment settingsFragment = new SettingsFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    CheckInFragment checkInFragment = new CheckInFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemHome:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, homeFragment)
                        .commit();
                return true;

            case R.id.menuItemProfile:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, profileFragment)
                        .commit();
                return true;

            case R.id.menuItemSettings:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, settingsFragment)
                        .commit();
                return true;
            case R.id.menuItemCheckIn:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, checkInFragment)
                        .commit();
                return true;
        }
        return false;
    }
    //метод, уточняющий действительно ли пользователь хочет выйти из приложения
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Выход")
                .setMessage("Вы уверены, что хотите выйти из приложения?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                }).setNegativeButton("Нет", null);
        builder.show();
    }
}

