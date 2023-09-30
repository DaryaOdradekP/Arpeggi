package com.example.arpeggi11.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.arpeggi11.mood.DegreeOfProblemActivity;
import com.example.arpeggi11.classes.Problem;
import com.example.arpeggi11.R;

/*
Фрагмент, в котором пользователь отмечает, какое настроение сейчас испытывает.
Здесь настроение запоминается и с помощью Intent передается в следующий активити.
Таким образом, из каждого последующего активити мы получаем все больше и больше информации о состоянии пользователя,
формируем объект Problem для последующей его отправки в базу данных.
 */

public class CheckInFragment extends Fragment {
    Button happyBtn, lovingBtn, proudBtn, calmBtn, optimisticBtn, gratefulBtn, tiredBtn, numbBtn, boredBtn;
    Button angryBtn, annoyedBtn, stressedBtn, anxiousBtn, insecureBtn, afraidBtn, sadBtn, ashamedBtn, guiltyBtn;

    public CheckInFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_in, container, false);
        //Каждый последующий onClick нужен для того, чтобы передать настроение пользователя
        // в новую активность, и соответсвенно перейти в нее.
        happyBtn = view.findViewById(R.id.happyBtn);
        happyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String login = preferences.getString("LOGIN", null);
                Problem problem = new Problem(login, "счастье");
                Intent i = new Intent(getActivity(), DegreeOfProblemActivity.class);
                i.putExtra("problem", problem);
                startActivity(i);
            }
        });

        lovingBtn = view.findViewById(R.id.lovingBtn);
        lovingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String login = preferences.getString("LOGIN", null);
                Problem problem = new Problem(login, "нежность");
                Intent i = new Intent(getActivity(), DegreeOfProblemActivity.class);
                i.putExtra("problem", problem);
                startActivity(i);
            }
        });

        proudBtn = view.findViewById(R.id.proudBtn);
        proudBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String login = preferences.getString("LOGIN", null);
                Problem problem = new Problem(login, "гордость");
                Intent i = new Intent(getActivity(), DegreeOfProblemActivity.class);
                i.putExtra("problem", problem);
                startActivity(i);
            }
        });

        calmBtn = view.findViewById(R.id.calmBtn);
        calmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String login = preferences.getString("LOGIN", null);
                Problem problem = new Problem(login, "спокойствие");
                Intent i = new Intent(getActivity(), DegreeOfProblemActivity.class);
                i.putExtra("problem", problem);
                startActivity(i);
            }
        });

        optimisticBtn = view.findViewById(R.id.optimisticBtn);
        optimisticBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String login = preferences.getString("LOGIN", null);
                Problem problem = new Problem(login, "оптимизм");
                Intent i = new Intent(getActivity(), DegreeOfProblemActivity.class);
                i.putExtra("problem", problem);
                startActivity(i);
            }
        });

        gratefulBtn = view.findViewById(R.id.gratefulBtn);
        gratefulBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String login = preferences.getString("LOGIN", null);
                Problem problem = new Problem(login, "благодарность");
                Intent i = new Intent(getActivity(), DegreeOfProblemActivity.class);
                i.putExtra("problem", problem);
                startActivity(i);
            }
        });

        tiredBtn = view.findViewById(R.id.tiredBtn);
        tiredBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String login = preferences.getString("LOGIN", null);
                Problem problem = new Problem(login, "усталость");
                Intent i = new Intent(getActivity(), DegreeOfProblemActivity.class);
                i.putExtra("problem", problem);
                startActivity(i);
            }
        });

        numbBtn = view.findViewById(R.id.numbBtn);
        numbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String login = preferences.getString("LOGIN", null);
                Problem problem = new Problem(login, "оцепенение");
                Intent i = new Intent(getActivity(), DegreeOfProblemActivity.class);
                i.putExtra("problem", problem);
                startActivity(i);
            }
        });

        boredBtn = view.findViewById(R.id.boredBtn);
        boredBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String login = preferences.getString("LOGIN", null);
                Problem problem = new Problem(login, "скука");
                Intent i = new Intent(getActivity(), DegreeOfProblemActivity.class);
                i.putExtra("problem", problem);
                startActivity(i);
            }
        });

        angryBtn = view.findViewById(R.id.angryBtn);
        angryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String login = preferences.getString("LOGIN", null);
                Problem problem = new Problem(login, "злость");
                Intent i = new Intent(getActivity(), DegreeOfProblemActivity.class);
                i.putExtra("problem", problem);
                startActivity(i);
            }
        });

        annoyedBtn = view.findViewById(R.id.annoyedBtn);
        annoyedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String login = preferences.getString("LOGIN", null);
                Problem problem = new Problem(login, "раздраженность");
                Intent i = new Intent(getActivity(), DegreeOfProblemActivity.class);
                i.putExtra("problem", problem);
                startActivity(i);
            }
        });

        stressedBtn = view.findViewById(R.id.stressedBtn);
        stressedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String login = preferences.getString("LOGIN", null);
                Problem problem = new Problem(login, "стресс");
                Intent i = new Intent(getActivity(), DegreeOfProblemActivity.class);
                i.putExtra("problem", problem);
                startActivity(i);
            }
        });

        anxiousBtn = view.findViewById(R.id.anxiousBtn);
        anxiousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String login = preferences.getString("LOGIN", null);
                Problem problem = new Problem(login, "тревога");
                Intent i = new Intent(getActivity(), DegreeOfProblemActivity.class);
                i.putExtra("problem", problem);
                startActivity(i);
            }
        });

        insecureBtn = view.findViewById(R.id.insecureBtn);
        insecureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String login = preferences.getString("LOGIN", null);
                Problem problem = new Problem(login, "небезопасность");
                Intent i = new Intent(getActivity(), DegreeOfProblemActivity.class);
                i.putExtra("problem", problem);
                startActivity(i);
            }
        });

        afraidBtn = view.findViewById(R.id.afraidBtn);
        afraidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String login = preferences.getString("LOGIN", null);
                Problem problem = new Problem(login, "страх");
                Intent i = new Intent(getActivity(), DegreeOfProblemActivity.class);
                i.putExtra("problem", problem);
                startActivity(i);
            }
        });

        sadBtn = view.findViewById(R.id.sadBtn);
        sadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String login = preferences.getString("LOGIN", null);
                Problem problem = new Problem(login, "грусть");
                Intent i = new Intent(getActivity(), DegreeOfProblemActivity.class);
                i.putExtra("problem", problem);
                startActivity(i);
            }
        });

        ashamedBtn = view.findViewById(R.id.ashamedBtn);
        ashamedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String login = preferences.getString("LOGIN", null);
                Problem problem = new Problem(login, "стыд");
                Intent i = new Intent(getActivity(), DegreeOfProblemActivity.class);
                i.putExtra("problem", problem);
                startActivity(i);
            }
        });

        guiltyBtn = view.findViewById(R.id.guiltyBtn);
        guiltyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String login = preferences.getString("LOGIN", null);
                Problem problem = new Problem(login, "вина");
                Intent i = new Intent(getActivity(), DegreeOfProblemActivity.class);
                i.putExtra("problem", problem);
                startActivity(i);
            }
        });
        return view;
    }
}