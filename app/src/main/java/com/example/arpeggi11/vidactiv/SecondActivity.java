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
Активити, в который можно попасть из основного фрагмента, нажав на любую кнопку из второй колонны.
Этот активити отображает видео с YouTube и текст. Вид активити зависит от выбранной кнопки.
 */
public class SecondActivity extends YouTubeBaseActivity {
    YouTubePlayerView youTubePlayerView;
    String numOfButton;
    TextView titleTv;
    ArrayList<String> text;
    ArrayList<String> link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        numOfButton = getIntent().getStringExtra("numberOfButton");
        titleTv = findViewById(R.id.youtubeTwoTv);
        text = new ArrayList<>();
        link = new ArrayList<>();
        text.add("Ваше тело имеет встроенную, естественную способность успокаивать тревогу. Узнайте о четырех простых, телесных способах успокоить тревогу, включив парасимпатическую нервную систему." + "\n" +
                "Успокаивать тревогу — это то, чему вы можете научиться, когда узнаете, как парасимпатическая реакция работает как антитревожная реакция." + "\n" +
                "  Тревога, посттравматическое стрессовое расстройство, травма и другие сильные эмоции коренятся в нервной системе, особенно в симпатической реакции, но наше тело имеет встроенную естественную способность успокаивать тревогу, включив парасимпатическую нервную систему.");
        text.add("Наскучила медитация? Возьмите практику осознанности на прогулку. Наслаждайтесь этим управляемым занятием, пока вы занимаетесь своим телом и сосредотачиваете свой разум.");
        text.add("Медитация сканирования тела — это форма осознанности, когда вы просто уделяете время тому, чтобы заметить, что происходит в вашем теле. Сканирование тела может помочь при беспокойстве, стрессе, мышечном напряжении, панических атаках и депрессии.");
        text.add("Наслаждайтесь этими 12 музыкальными композициями с глубоким фокусом, чтобы улучшить концентрацию во время учебы на фоне прекрасной подборки природных пейзажей." +
                "\n" +
                "Эта расслабляющая эмбиент-музыка для учебы идеально подходит для прослушивания в фоновом режиме, когда вы сосредоточены на своей работе, позволяя вам расслабиться и сконцентрироваться на учебе.");
        link.add("WGG7MGgptxE");
        link.add("ElHCp33OIOo");
        link.add("6IATiVQ1u58");
        link.add("3bkzw5csX5c");

        if (numOfButton.equals("1"))
            titleTv.setText(text.get(0));
        else if (numOfButton.equals("2"))
            titleTv.setText(text.get(1));
        else if (numOfButton.equals("3"))
            titleTv.setText(text.get(2));
        else
            titleTv.setText(text.get(3));

        youTubePlayerView = findViewById(R.id.youtubeTwo);
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