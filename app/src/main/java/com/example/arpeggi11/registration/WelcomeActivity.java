package com.example.arpeggi11.registration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.arpeggi11.R;

/*
Активити, отвечающий за экран прогрузки. Как только пользователь заходит в приложение,
данные из базы должны прогрузиться, на это уходит около двух секунд.
Чтобы пользователь не видел какой-то статичный активити, с которым нельзя взаимодействовать,
отображаем ему этот активити, просто с картинкой(лого приложения).
Также этот активити определяет куда кинуть юзера, на экран входа или в основной фрагмент.
 */

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String savedLogin = preferences.getString("LOGIN", null);
                //если есть данные в SharedPrefences отсутствуют - идем на экран входа
                if (savedLogin == null) {
                    Intent intent = new Intent(getApplicationContext(),
                            MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                //иначе, отправляемся в основной фрагмент
                else {
                    Intent intent = new Intent(getApplicationContext(),
                           AppActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        }, 3000);
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