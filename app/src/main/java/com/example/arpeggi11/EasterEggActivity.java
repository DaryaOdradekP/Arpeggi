package com.example.arpeggi11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
/*
Активити, в который можно попасть из фрагмента настроек, нажав на кнопку с лого.
Этот активити отображает видео с YouTube и текст.
 */
public class EasterEggActivity extends YouTubeBaseActivity {
    YouTubePlayerView youTubePlayerView;
    String numOfButton;
    TextView titleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easter_egg);
        titleTv = findViewById(R.id.titleEasterTv);
        titleTv.setText("Песня «Weird Fishes/Arpeggi» из альбома In Rainbows группы Radiohead —" +
                " красивая, запоминающаяся композиция. В этом произведении рассказчик стоит в" +
                " глубинах великой печали и тишины, океан — метафора его подсознания, пока " +
                "его не соблазнит новая любовь. Он задается вопросом, почему он когда-либо решил" +
                " остаться в своем депрессивном стазисе, спрашивая: «Почему я должен оставаться здесь?»," +
                " И решает следовать этой любви, несмотря на риск. Однако то, что он чувствует, не " +
                "связано с реальностью, и, хотя он отчаянно хочет что-то почувствовать, оно проваливается — вероятно," +
                " из-за его собственной неуверенности в себе. К сожалению, он снова впадает в отчаяние." +
                " Его «съели черви», и его надежда теперь разлагается вместе с ним. Тем не менее, есть оттенок " +
                "облегчения, поскольку он принимает неудачу и теперь снова может полностью видеть реальность. " +
                "В конечном счете, следование ошибочной любви возвращает его к депрессии, но другой вариант — " +
                "вырваться из этого круга и принять правду о ситуации без ложной надежды."+'\n'+"Arpeggi - " +
                "ваш путь к пониманию и поддержке. Именно эта смесь эмоций, которую вы ощущаете в песне" +
                " \"Weird Fishes/Arpeggi\" группы Radiohead, вдохновила на создание моего приложения, " +
                "предназначенного для тех, кто борется с депрессией, тревожностью и другими недугами.");
        youTubePlayerView = findViewById(R.id.youtubeEaster);
        YouTubePlayer.OnInitializedListener listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("ejdZEe4Rd0o&t=3s");
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