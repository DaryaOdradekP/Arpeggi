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
Активити, в который можно попасть из основного фрагмента, нажав на любую кнопку из четвертой колонны.
Этот активити отображает видео с YouTube и текст. Вид активити зависит от выбранной кнопки.
 */
public class FourthActivity extends YouTubeBaseActivity {
    YouTubePlayerView youTubePlayerView;
    String numOfButton;
    TextView titleTv;
    ArrayList<String> text;
    ArrayList<String> link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        numOfButton = getIntent().getStringExtra("numberOfButton");
        titleTv = findViewById(R.id.youtubeFourTv);
        text = new ArrayList<>();
        link = new ArrayList<>();
        text.add("Наши сказки на ночь, озвученные отмеченными наградами дикторами, призваны удерживать ваше внимание ровно настолько, чтобы вы могли заснуть естественным образом. Мечтательный." +
                "\n" +
                "О подводном городе, часть 2:" +
                "Отправляйтесь с нами в сказочное путешествие по неизведанным землям подводного города Крымменоса. Мы будем следовать вместе с командиром специального отряда Ocean Floor, когда она отправится на специальную миссию.");
        text.add("Первым в списке стоит чистить зубы противоположной рукой." +"\n" +
                "Во-вторых, переключите свои чувства." +"\n" +
                "В-третьих, головоломки — отличный способ бросить вызов своему уму и держать его в тонусе." +"\n" +
                " В-четвертых, поиграйте в игру переключения." +"\n" +
                "В-пятых, отказаться от технологий." +"\n" +
                "Наконец, сделайте что-нибудь другое. Итак, вот оно, 6 упражнений для мозга, которые укрепят ваш ум. Включите эти упражнения в свой распорядок дня и начните видеть преимущества уже сегодня.");
        text.add("Путешествуйте по миру и узнайте о любви, красоте и воображении с этим пересказом любимой классики «Маленький принц» Антуана де Сент-Экзюпери. Эта сказка на ночь мягко убаюкает вас.");
        text.add("Наши сказки на ночь, озвученные отмеченными наградами дикторами, призваны удерживать ваше внимание ровно настолько, чтобы вы могли заснуть естественным образом. Мечтательный." +
                "\n" +
                "В этой захватывающей истории вы — пассажир космического корабля, который доставит вас в самое мечтательное путешествие в нашу галактику. Приготовьтесь к звездному путешествию и прекрасному ночному сну. Футуристический опыт от BetterSleep." +
                "\n" +
                "Эта история помогла тебе уснуть?");
        link.add("D8eplSV1wus");
        link.add("VA-CaC6ONqM");
        link.add("5wvgJePdZ7s");
        link.add("9d--1Cyi0eI");

        if (numOfButton.equals("1"))
            titleTv.setText(text.get(0));
        else if (numOfButton.equals("2"))
            titleTv.setText(text.get(1));
        else if (numOfButton.equals("3"))
            titleTv.setText(text.get(2));
        else
            titleTv.setText(text.get(3));

        youTubePlayerView = findViewById(R.id.youtubeFour);
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