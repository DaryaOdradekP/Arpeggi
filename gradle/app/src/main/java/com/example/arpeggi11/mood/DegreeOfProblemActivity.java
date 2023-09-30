package com.example.arpeggi11.mood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.arpeggi11.R;
import com.example.arpeggi11.classes.Problem;

/*
Активити, в котором пользователь определяет стпень своего настрония.
Также активити получает данные из предыдущего фрагмента(настроение), добавляет к нему степень и передает дальше.
 */

public class DegreeOfProblemActivity extends AppCompatActivity {
    Problem problem;
    Button next;
    String degree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degree_of_problem);
        problem = getIntent().getParcelableExtra("problem");
        next = findViewById(R.id.enter1Btn);
        //отрабатываем нажатие на кнопку "далее"
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (degree != null) {
                    problem.setDegree(degree);
                    Intent i = new Intent(DegreeOfProblemActivity.this, ReasonActivity.class);
                    i.putExtra("problem", (Parcelable) problem);
                    startActivity(i);
                } else {
                    //если пользователь не выбрал ни одного пункта, просим выбрать
                    Toast.makeText(DegreeOfProblemActivity.this, "Выберите степень настроения", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //обработка нажатий на чекбоксы
    public void onCheckboxClicked(View view) {
        CheckBox checkBox = (CheckBox) view;
        boolean checked = checkBox.isChecked();
        switch (view.getId()) {
            case R.id.extremelyCB:
                if (checked) {
                    uncheckAllExcept(R.id.extremelyCB);
                    degree = "много";
                } else {
                    degree = null;
                }
                break;
            case R.id.veryCB:
                if (checked) {
                    uncheckAllExcept(R.id.veryCB);
                    degree = "среднее количество";
                } else {
                    degree = null;
                }
                break;
            case R.id.aLittleCB:
                if (checked) {
                    uncheckAllExcept(R.id.aLittleCB);
                    degree = "немного";
                } else {
                    degree = null;
                }
                break;
            default:
                uncheckAll();
                degree = null;
                break;
        }
    }
    //метод, служащий для того, чтобы нельзя было выбрать более одного чекбокса
    private void uncheckAllExcept(int viewId) {
        CheckBox extremelyCB = findViewById(R.id.extremelyCB);
        CheckBox veryCB = findViewById(R.id.veryCB);
        CheckBox aLittleCB = findViewById(R.id.aLittleCB);
        CheckBox clickedCheckBox = findViewById(viewId);
        if (clickedCheckBox != extremelyCB) {
            extremelyCB.setChecked(false);
        }
        if (clickedCheckBox != veryCB) {
            veryCB.setChecked(false);
        }
        if (clickedCheckBox != aLittleCB) {
            aLittleCB.setChecked(false);
        }
    }

    private void uncheckAll() {
        CheckBox extremelyCB = findViewById(R.id.extremelyCB);
        CheckBox veryCB = findViewById(R.id.veryCB);
        CheckBox aLittleCB = findViewById(R.id.aLittleCB);
        extremelyCB.setChecked(false);
        veryCB.setChecked(false);
        aLittleCB.setChecked(false);
    }
}