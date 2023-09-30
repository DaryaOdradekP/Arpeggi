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
Активити, в который можно попасть из основного фрагмента, нажав на любую кнопку из пятой колонны.
Этот активити отображает видео с YouTube и текст. Вид активити зависит от выбранной кнопки.
 */

public class FifthActivity extends YouTubeBaseActivity {
    YouTubePlayerView youTubePlayerView;
    String numOfButton;
    TextView titleTv;
    ArrayList<String> text;
    ArrayList<String> link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        numOfButton = getIntent().getStringExtra("numberOfButton");
        titleTv = findViewById(R.id.youtubeFiveTv);
        text = new ArrayList<>();
        link = new ArrayList<>();
        text.add("Этот музыкальный сборник был загружен в сотрудничестве с программой музыкальных амбассадоров Epidemic Sound." +
                "\n" +
                "1 час расслабляющего белого шума для снижения стресса и улучшения концентрации." +
                "Хорошо подходит для сна, учебы или отдыха.");
        text.add("Расслабляющая музыка для крепкого сна и облегчения стресса. Засыпайте с помощью прекрасных видео природы и используйте расслабляющую музыку \"Flying\" от Peder B. Helland как музыку для сна, успокоения, медитации, расслабления, учебы и так далее.");
        text.add("10-минутная управляемая медитация для исцеления, отпускания и внутреннего спокойствия. Исцелите свое сердце и эмоциональные раны с помощью визуализации позитивной энергии, энергетического исцеления сердечной чакры и позитивных аффирмаций. Эта исцеляющая медитация поможет вам развить благодарность, самопрощение и перспективу для вашего путешествия. Отлично подходит для начинающих и всех уровней.");
        text.add("В этом видео мы собрали все треки исцеления чакр, каждый трек длится 26 минут. Вы можете использовать этот сборник для длительной медитации, чтобы сбалансировать все чакры. Намасте");
        link.add("KUfI2rOsuGc");
        link.add("1ZYbU82GVz4");
        link.add("vtOAnC73xtk");
        link.add("FsF97Xhf9h8");

        //определяем, какой текст отобразить
        if (numOfButton.equals("1"))
            titleTv.setText(text.get(0));
        else if (numOfButton.equals("2"))
            titleTv.setText(text.get(1));
        else if (numOfButton.equals("3"))
            titleTv.setText(text.get(2));
        else
            titleTv.setText(text.get(3));

        youTubePlayerView = findViewById(R.id.youtubeFive);
        YouTubePlayer.OnInitializedListener listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                //выбираем, какое видео показать
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