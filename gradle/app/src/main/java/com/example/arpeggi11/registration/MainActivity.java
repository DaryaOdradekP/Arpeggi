package com.example.arpeggi11.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arpeggi11.Person;
import com.example.arpeggi11.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/*
Активити, который появляется первым после установки приложения.
В этом активити пользователь может войти в свой аккаунт или зарегистрироваться.
Если пользователь верно ввел свои данные при входе, то они записываются в SharedPreferences вместе с его именем и кеем.
 */
public class MainActivity extends AppCompatActivity {
    Button signInBtn, logInBtn;
    FirebaseDatabase db;
    DatabaseReference people;
    EditText login, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signInBtn = findViewById(R.id.signInBtn);
        logInBtn = findViewById(R.id.logInBtn);
        db = FirebaseDatabase.getInstance("https://arpeggi-default-rtdb.europe-west1.firebasedatabase.app/");
        people = db.getReference().child("People");
        //переход в активити регистрации
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
            }
        });
        login = findViewById(R.id.loginTxt);
        password = findViewById(R.id.passwordTxt);

        SharedPreferences preferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE);
        String savedLogin = preferences.getString("LOGIN", null);
        String savedPassword = preferences.getString("PASSWORD", null);

        if (savedLogin != null && savedPassword != null) {
            login.setText(savedLogin);
            password.setText(savedPassword);
            //проверка сохраненных логина и пароля
            checkCredentials(savedLogin, savedPassword);
        }
        //если данные верные, идет в основной фрагмент
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usersLogin = login.getText().toString();
                String usersPassword = password.getText().toString();
                checkCredentials(usersLogin, usersPassword);
            }
        });
    }

    //метод, отвечающий за проверку правильности пароля и логина при входе(и существуют ли они вообще)
    private void checkCredentials(String login, String password) {
        Query query = people.orderByChild("login").equalTo(login);
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot document : snapshot.getChildren()) {
                        Person per = document.getValue(Person.class);
                        String perPas = per.getPassword();
                        String perKey = per.getKey();
                        String perName = per.getName();
                        if (perPas.equals(password)) {
                            //пароль и логин верные - идем в новый активити и запоминаем данные
                            saveCredentials(login, password, perName, perKey);
                            Intent intent = new Intent(MainActivity.this, AppActivity.class);
                            startActivity(intent);
                            finish();
                            return;
                        } else {
                            //логин верный, а пароль нет - сообщаем об этом
                            Toast.makeText(getApplicationContext(), "неверный пароль!", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                } else {
                    //логина не существует - говорим об этом
                    Toast.makeText(getApplicationContext(), "такого пользователя не существует", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "ошибка при чтении базы данных", Toast.LENGTH_LONG).show();
            }
        };
        query.addListenerForSingleValueEvent(listener);
    }

    //сохраняем данные в SharedPreferences
    private void saveCredentials(String login, String password, String username, String key) {
        SharedPreferences preferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("LOGIN", login);
        editor.putString("PASSWORD", password);
        editor.putString("USERNAME", username);
        editor.putString("KEY", key);
        editor.apply();
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