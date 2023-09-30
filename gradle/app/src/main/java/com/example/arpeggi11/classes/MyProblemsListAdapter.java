package com.example.arpeggi11.classes;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.arpeggi11.R;

import java.util.ArrayList;

/*
 * Класс, отвечающий за то, чтобы доставать информацию о проблеме юзера из базы данных (для ProfileFragment)
 */

public class MyProblemsListAdapter extends ArrayAdapter<Problem> {
    Context context;
    ArrayList<Problem> problems;
    ArrayList<Problem> filteredProblems;

    public MyProblemsListAdapter(Context context, ArrayList<Problem> problems) {
        super(context, R.layout.item_problem, problems);
        this.context = context;
        this.problems = problems;
        this.filteredProblems = filterProblems();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Problem p = this.filteredProblems.get(position);

        LayoutInflater inflater = LayoutInflater.from(this.context);
        View v = inflater.inflate(R.layout.item_problem, null, false);

        TextView tvDate = v.findViewById(R.id.tvDate);
        TextView tvProblem = v.findViewById(R.id.tvProblem);
        TextView tvReason = v.findViewById(R.id.tvReason);

        tvDate.setText(p.getTime());
        tvProblem.setText(p.getProblem());
        tvReason.setText(TextUtils.join(", ", p.getReason().toArray(new String[p.getReason().size()])));
        v.setTag(p.getKey());

        return v;
    }

    @Override
    public int getCount() {
        return filteredProblems.size();
    }

    @Nullable
    @Override
    public Problem getItem(int position) {
        return filteredProblems.get(position);
    }

    //Метод, который ищет проблемы конкретного пользователя, чтобы затем отобразить
    private ArrayList<Problem> filterProblems() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MY_PREFS", MODE_PRIVATE);
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
