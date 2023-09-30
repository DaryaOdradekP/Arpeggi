package com.example.arpeggi11.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.arpeggi11.R;
import com.example.arpeggi11.classes.MyProblemsListAdapter;
import com.example.arpeggi11.classes.Problem;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/*
Этот фрагмент отображает статистику настроения пользователя за последние 7 дней
(сколько раз было позитивное, нейтральное, негативное настроение).
Также тут отображается ListView всех настроений пользователя, что он отмечал.
При нажатии на каждый айтем можно увидеть совет "бота", который был дан, когда
пользователь отмечал настроение. Этот ответ отображается в AlertDialog.
 */

public class ProfileFragment extends Fragment {
    ListView myList;
    FirebaseDatabase db;
    DatabaseReference problemsRef;
    ArrayList<Problem> problems = new ArrayList<>();
    MyProblemsListAdapter adapter;
    FirebaseAnalytics firebaseAnalytics;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        firebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
        myList = view.findViewById(R.id.myList);
        db = FirebaseDatabase.getInstance("https://arpeggi-default-rtdb.europe-west1.firebasedatabase.app/");
        problemsRef = db.getReference().child("Problem");
        TextView textPositiveCount = view.findViewById(R.id.textPositiveCount);
        TextView textNegativeCount = view.findViewById(R.id.textNegativeCount);
        TextView textNeutralCount = view.findViewById(R.id.textNeutralCount);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                problems.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //получаем данные проблемы
                    Problem problem = snapshot.getValue(Problem.class);
                    problems.add(problem);
                }

                //фильтруем проблемы по текущему пользователю
                ArrayList<Problem> filteredProblems = filterProblems();
                adapter = new MyProblemsListAdapter(getActivity().getApplicationContext(), filteredProblems);

                //полуаем текущую дату и время
                Calendar calendar = Calendar.getInstance();
                Date currentDate = calendar.getTime();

                //определяем дату начала недели (7 дней назад)
                calendar.add(Calendar.DAY_OF_YEAR, -7);
                Date weekStartDate = calendar.getTime();
                int positiveCount = 0;
                int negativeCount = 0;
                int neutralCount = 0;

                //формат даты, который соответствует формату в вашей базе данных
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());

                //проходим по всем проблемам за последнюю неделю
                for (Problem problem : filteredProblems) {
                    String problemDateString = problem.getTime();
                    if (problemDateString != null) {
                        try {
                            //разбиваем строку даты в объект Date
                            Date problemDate = dateFormat.parse(problemDateString);
                            //проверяем, что проблема находится в пределах последней недели
                            if (problemDate.after(weekStartDate) && problemDate.before(currentDate)) {
                                if (problem.getProblem().equals("счастье") || problem.getProblem().equals("нежность") || problem.getProblem().equals("гордость") ||
                                        problem.getProblem().equals("спокойствие") || problem.getProblem().equals("оптимизм") || problem.getProblem().equals("благодарность")) {
                                    positiveCount++;
                                } else if (problem.getProblem().equals("злость") || problem.getProblem().equals("раздраженность") || problem.getProblem().equals("стресс") ||
                                        problem.getProblem().equals("тревога") || problem.getProblem().equals("небезопасность") || problem.getProblem().equals("страх") ||
                                        problem.getProblem().equals("грусть") || problem.getProblem().equals("стыд") || problem.getProblem().equals("вина")) {
                                    negativeCount++;
                                } else {
                                    neutralCount++;
                                }
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }

                //отправляем статистику в Firebase Analytics
                Bundle params = new Bundle();
                params.putInt("PositiveCount", positiveCount);
                params.putInt("NegativeCount", negativeCount);
                params.putInt("NeutralCount", neutralCount);
                firebaseAnalytics.logEvent("WeekMoodStatistics", params);

                //обновляем значения TextView
                textPositiveCount.setText("Позитивных эмоций: " + positiveCount);
                textNegativeCount.setText("Негативных эмоций: " + negativeCount);
                textNeutralCount.setText("Нейтральных эмоций: " + neutralCount);
                myList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };

        //применяем слушателя событий к запросу базы данных
        problemsRef.addListenerForSingleValueEvent(valueEventListener);
        //соответственно отображаем сообщения "бота"
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Совет чат-бота:");
                Problem selectedProblem = (Problem) adapter.getItem(position);
                String problemMessage = selectedProblem.getText();
                builder.setMessage(problemMessage);
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
        return view;
    }

    //метод, который лезет за проблемами по пользователю в базу данных
    private ArrayList<Problem> filterProblems() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE);
        String currentUserLogin = sharedPreferences.getString("LOGIN", "");
        ArrayList<Problem> filteredList = new ArrayList<>();
        for (Problem problem : problems) {
            if (problem.getLogin().equals(currentUserLogin)) {
                filteredList.add(problem);
            }
        }
        return filteredList;
    }

}