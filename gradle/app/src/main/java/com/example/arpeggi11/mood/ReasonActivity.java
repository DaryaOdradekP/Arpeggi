package com.example.arpeggi11.mood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.arpeggi11.R;
import com.example.arpeggi11.classes.Problem;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.arpeggi11.R;
import com.example.arpeggi11.classes.Problem;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

/*
Активити, в котором пользователь определяет причину своего настрония.
Также активити получает данные из предыдущего фрагмента(настроение и его степень), добавляет к нему ArrayList причин и передает дальше.
В активити присутствует анимация нажатия на пункт, также отслеживается, что бы не выбрал меньше 1 и более 3 причин.
 */

public class ReasonActivity extends AppCompatActivity {
    ImageButton foodBtn, bodyBtn, exerciseBtn, healthBtn, familyBtn, friendsBtn, homeBtn;
    ImageButton moneyBtn, musicBtn, myselfBtn, outdoorsBtn, partnerBtn, schoolBtn, sleepingBtn, socialMediaBtn, workBtn;

    private ArrayList<String> selectedReasons;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reason);

        selectedReasons = new ArrayList<>();

        foodBtn = findViewById(R.id.foodBtn);
        bodyBtn = findViewById(R.id.bodyBtn);
        exerciseBtn = findViewById(R.id.exerciseBtn);
        healthBtn = findViewById(R.id.healthBtn);
        familyBtn = findViewById(R.id.familyBtn);
        friendsBtn = findViewById(R.id.friendsBtn);
        homeBtn = findViewById(R.id.homeBtn);
        moneyBtn = findViewById(R.id.moneyBtn);
        musicBtn = findViewById(R.id.musicBtn);
        myselfBtn = findViewById(R.id.myselfBtn);
        outdoorsBtn = findViewById(R.id.outdoorsBtn);
        partnerBtn = findViewById(R.id.partnerBtn);
        schoolBtn = findViewById(R.id.schoolBtn);
        sleepingBtn = findViewById(R.id.sleepingBtn);
        socialMediaBtn = findViewById(R.id.socialMediaBtn);
        workBtn = findViewById(R.id.workBtn);

        //последующие кнопки, кроме последней, отвечают за формирование массива, который передается с данными из предыдущих активити дальше
        foodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonSelection(foodBtn, "еда");
            }
        });

        bodyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonSelection(bodyBtn, "тело");
            }
        });

        exerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonSelection(exerciseBtn, "тренировка");
            }
        });

        healthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonSelection(healthBtn, "здоровье");
            }
        });

        familyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonSelection(familyBtn, "семья");
            }
        });

        friendsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonSelection(friendsBtn, "друзья");
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonSelection(homeBtn, "дом");
            }
        });

        moneyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonSelection(moneyBtn, "деньги");
            }
        });

        musicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonSelection(musicBtn, "музыка");
            }
        });

        myselfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonSelection(myselfBtn, "я");
            }
        });

        outdoorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonSelection(outdoorsBtn, "внешний мир");
            }
        });

        partnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonSelection(partnerBtn, "партнер");
            }
        });

        schoolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonSelection(schoolBtn, "школа");
            }
        });

        sleepingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonSelection(sleepingBtn, "сон");
            }
        });

        socialMediaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonSelection(socialMediaBtn, "социальные сети");
            }
        });

        workBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonSelection(workBtn, "работа");
            }
        });

        //эта кнопка получает старые данные, добавляет к ним ArrayList причин и передает дальше
        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedReasons.size() >= 1 && selectedReasons.size() <= 3) {
                    // создание объекта Problem и передача данных в базу данных
                    Problem prevProblem = getIntent().getParcelableExtra("problem");
                    prevProblem.setReason(selectedReasons);
                    Intent i = new Intent(ReasonActivity.this, ChatActivity.class);
                    i.putExtra("problem", (Parcelable) prevProblem);
                    startActivity(i);
                } else {
                    Toast.makeText(ReasonActivity.this, "выберите от 1 до 3 причин", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //метод, отвечающий за анимацию выбора и за то, чтобы пользователь не выбрал более 3 причин
    private void toggleButtonSelection(ImageButton button, String reason) {
        if (selectedReasons.contains(reason)) {
            selectedReasons.remove(reason);
            button.setAlpha(1f); //визуальное отображение выбора
        } else {
            if (selectedReasons.size() < 3) {
                selectedReasons.add(reason);
                button.setAlpha(0.5f); //визуальное отображение выбора
            } else {
                Toast.makeText(this, "максимальное количество выбранных причин", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


