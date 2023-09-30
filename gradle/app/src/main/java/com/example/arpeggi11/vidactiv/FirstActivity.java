package com.example.arpeggi11.vidactiv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arpeggi11.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
/*
Активити, в который можно попасть из основного фрагмента, нажав на любую кнопку из первой колонны.
Этот активити отображает видео с YouTube и текст. Вид активити зависит от выбранной кнопки.
 */
public class FirstActivity extends YouTubeBaseActivity {
    YouTubePlayerView youTubePlayerView;
    String numOfButton;
    TextView titleTv;
    ArrayList<String> text;
    ArrayList<String> link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        numOfButton = getIntent().getStringExtra("numberOfButton");
        titleTv = findViewById(R.id.titleTv);
        text = new ArrayList<>();
        link = new ArrayList<>();
        text.add("Тамара Левитт проводит эту 10-минутную медитацию осознанности." +
                "\n" +
                "Ежедневная практика медитации помогает уменьшить тревогу, беспокойство и стресс, а также повысить самооценку и самопринятие. Это также повышает устойчивость к неопределенности и невзгодам." +
                "\n" +
                "Существует множество научно доказанных преимуществ медитации, в том числе улучшение умственной силы, сосредоточенности, лучшего принятия решений и решения проблем — все это имеет первостепенное значение для достижения успеха на пути к предпринимательству.");
        text.add("В нашей занятой жизни бывает трудно найти время, которое нам нужно для себя. Время, которое нам нужно, чтобы стать лучше. Но именно маленькие вещи, которые мы делаем каждый день, со временем раскрывают наш истинный потенциал." +
                "\n" +
                "Добро пожаловать в Daily Jay — 7 минут вдохновения и медитации, которые вы можете применить в своей повседневной жизни." +
                "\n" +
                "Присоединяйтесь к бывшему монаху, целеустремленному тренеру и автору бестселлеров Джею Шетти в ежедневном путешествии, чтобы обрести спокойствие, развить новые намерения и предпринять позитивные действия на пути к жизни своей мечты.");
        text.add("Делясь своим неожиданным путем к медитации, @JeffWarrenMeditation стремится помочь вам по-другому взглянуть на свои собственные. Присоединяйтесь к Джеффу для DailyTrip.");
        text.add("Социальные сети могут подождать. Ваше тело нуждается в вас." +
                "\n" +
                "Встаньте (да, правда) и присоединитесь к Мелли Ма, чтобы сделать минутный перерыв на растяжку. Просто потратьте 60 секунд, и мы обещаем, что ваше тело — и разум — почувствуют разницу.");
        link.add("lVx3mFxML80");
        link.add("7SbTO6R-co0");
        link.add("Eqy8BUSQGAA");
        link.add("3UETApM_L-I");

        if (numOfButton.equals("1"))
            titleTv.setText(text.get(0));
        else if (numOfButton.equals("2"))
            titleTv.setText(text.get(1));
        else if (numOfButton.equals("3"))
            titleTv.setText(text.get(2));
        else
            titleTv.setText(text.get(3));

        youTubePlayerView = findViewById(R.id.youtubeOne);
        YouTubePlayer.OnInitializedListener listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (numOfButton.equals("1"))
                    youTubePlayer.loadVideo(link.get(0));
                else if (numOfButton.equals("2"))
                    youTubePlayer.loadVideo(link.get(1));
                else if (numOfButton.equals("3"))
                    youTubePlayer.loadVideo(link.get(2));
                else
                    youTubePlayer.loadVideo(link.get(3));
                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(), "ошибка при воспроизведении видео", Toast.LENGTH_LONG).show();
            }
        };
        youTubePlayerView.initialize("AIzaSyA-gFYX69WN6IkSH9eOJul1zYwkckuV1FM", listener);


    }
}