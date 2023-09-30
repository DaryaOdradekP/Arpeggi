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
Активити, в который можно попасть из основного фрагмента, нажав на любую кнопку из третьей колонны.
Этот активити отображает видео с YouTube и текст. Вид активити зависит от выбранной кнопки.
 */
public class ThirdActivity extends YouTubeBaseActivity {
    YouTubePlayerView youTubePlayerView;
    String numOfButton;
    TextView titleTv;
    ArrayList<String> text;
    ArrayList<String> link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        numOfButton = getIntent().getStringExtra("numberOfButton");
        titleTv = findViewById(R.id.youtubeThreeTv);
        text = new ArrayList<>();
        link = new ArrayList<>();
        text.add("Осознанность — это практика признания того, что вы чувствуете, без осуждения эмоций или ощущений, когда вы их испытываете. Узнайте больше о том, почему вы должны практиковать осознанность и как воспользоваться ее преимуществами. Практика осознанности с помощью медитации или других техник улучшает как психическое, так и физическое здоровье. Следуйте этому упражнению по сканированию тела, чтобы улучшить концентрацию и общее самочувствие.");
        text.add("Первым в списке стоит чистить зубы противоположной рукой." + "\n" +
                "Во-вторых, переключите свои чувства." + "\n" +
                "В-третьих, головоломки — отличный способ бросить вызов своему уму и держать его в тонусе." + "\n" +
                "В-четвертых, поиграйте в игру переключения." + "\n" +
                "В-пятых, отказаться от технологий." + "\n" +
                "Наконец, сделайте что-нибудь другое. Итак, вот оно, 6 упражнений для мозга, которые укрепят ваш ум. Включите эти упражнения в свой распорядок дня и начните видеть преимущества уже сегодня.");
        text.add("Попробуйте эти забавные упражнения для мозга, которые сделают ваш мозг более гибким и объединят левое и правое полушария. Отлично подходит для повышения нейропластичности и создания баланса в вашем мозгу. Вы можете сделать все 6?");
        text.add("«Уверенность — это необходимая искра перед всем, что последует», — говорит педагог и активистка Бриттани Пакнетт Каннингем. В вдохновляющем выступлении она делится тремя способами взлома кодекса уверенности и своей мечтой о мире, в котором революционная уверенность помогает воплотить наши самые смелые мечты в реальность.");
        link.add("WGG7MGgptxE");
        link.add("haRSl3mThoY");
        link.add("jwlNOUnGqYA");
        link.add("b5ZESpOAolU");

        if (numOfButton.equals("1"))
            titleTv.setText(text.get(0));
        else if (numOfButton.equals("2"))
            titleTv.setText(text.get(1));
        else if (numOfButton.equals("3"))
            titleTv.setText(text.get(2));
        else
            titleTv.setText(text.get(3));

        youTubePlayerView = findViewById(R.id.youtubeThree);
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