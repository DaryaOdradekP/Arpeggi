package com.example.arpeggi11.fragments;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.arpeggi11.ChangeActivity;
import com.example.arpeggi11.EasterEggActivity;
import com.example.arpeggi11.R;
import com.example.arpeggi11.registration.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/*
Фрагмент с настройками, в котором находятся 5 кнопок:
1) ImageButton, при нажатии на которую пользователя перекидывают в активити, в котором отображается видео и описание, почему
приложение так называется.
2) Кнопка выхода из аккаунта, которая очищает SharedPreferences.
3) Кнопка, при нажатии на которую появляется AlertDialog с информацией о пользователе.
4) Кнопка, которая перекидывает в активити, в котором можно сменить пароль.
5) Кнопка, которая удаляет аккаунт
 */

public class SettingsFragment extends Fragment {
    Button logOutBtn, myAccountBtn, changeBtn, deleteBtn;
    ImageButton arpeggiBtn;

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        Context context = getContext();
        logOutBtn = view.findViewById(R.id.logOutBtn);
        myAccountBtn = view.findViewById(R.id.myAccountBtn);
        changeBtn = view.findViewById(R.id.changeBtn);
        deleteBtn = view.findViewById(R.id.deleteBtn);
        arpeggiBtn = view.findViewById(R.id.arpeggiBtn);
        //обработка нажатия на кнопку, которая перекинет в активити с объянением
        arpeggiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EasterEggActivity.class);
                startActivity(intent);
            }
        });
        //кнопка выхода из аккаунта. перекинет на экран входа, удалит все из SharedPreferences
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCredentials();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        //кнопка, которая отбразит имя и логин пользователя в AlertDialog
        myAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Данные вашего аккаунта:");

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String username = sharedPreferences.getString("USERNAME", "");
                String login = sharedPreferences.getString("LOGIN", "");
                String accountInfo = "Ваше имя: " + username + '\n' + "Ваш логин: " + login;
                builder.setMessage(accountInfo);

                builder.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangeActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        //кнопка удаление пользователя. очищает SharedPreferences, удаляет ветвь пользователя из базы, перекидывает на экран входа.
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String key = sharedPreferences.getString("KEY", "");
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference peopleRef = database.getReference("People");
                DatabaseReference perRef = peopleRef.child(key);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Вы уверены, что хотите удалить аккаунт?");
                String accountInfo = "Если вы удалите аккаунт, его уже нельзя будет восстановить.";
                builder.setMessage(accountInfo);
                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        perRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    deleteCredentials();
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                            | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    sharedPreferences.edit().remove(key).apply();
                                    Toast.makeText(getActivity(), "Аккаунт был успешно удален", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getActivity(), "Что-то пошло не так", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });
                builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return view;
    }

    //метод, который очищает SharedPreferences
    private void deleteCredentials() {
        SharedPreferences preferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("LOGIN");
        editor.remove("PASSWORD");
        editor.remove("USERNAME");
        editor.remove("KEY");
        editor.apply();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
        }
    }

}