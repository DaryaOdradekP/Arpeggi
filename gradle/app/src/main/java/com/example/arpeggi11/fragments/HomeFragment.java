package com.example.arpeggi11.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.arpeggi11.vidactiv.FifthActivity;
import com.example.arpeggi11.vidactiv.FirstActivity;
import com.example.arpeggi11.vidactiv.FourthActivity;
import com.example.arpeggi11.R;
import com.example.arpeggi11.vidactiv.SecondActivity;
import com.example.arpeggi11.vidactiv.ThirdActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.os.Process;

/*
                                       !!!ВАЖНО!!!
Необходимо вручную дать разрешение приложению высылать уведомления. Для этого необходимо
зажать иконку приложения на рабочем столе смартфона/эмулятора, нажать на кнопку i и разрешить
отправку уведомлений.
К сожалению, мне не удалось найти универсального способа запроса отправки уведомлений, на некоторых
устройствах они сразу доступны, на других приходится включать вручную.
 */

/*
Главный экран приложения, на котором отображается:
1) Sos-кнопка, при нажатии на которую пользователя перекидывает в приложение "телефон",
установленное на его смартфоне, с уже набранным номером доверия психологической службы.
Рядом с sos-кнопкой распологается маленькая кнопка info, при нажатии на которую можно увидеть
AlertDialog с информацией о sos-кнопке и ссылку на сайт организации, чей номер доверия открывается при нажатии на sos-кнопку.
2) Далее следуют объекты, которые хотелось бы рассмотреть подробнее.
Самый первый объект - TextView, значение которого зависит от времени суток пользователя и его имени соотвественно.
То есть код отображает "доброе утро(день, вечер, ночь), юзернейм", в зависимости от вышеперчисленных факторов.
За этим TextView следуют 4 ImageButton, которые перекидывают в активити с определенным текстом и видео.
Далее все повторяется.
Также стоит уточнить, что из этого активити пользователю отправляется уведомление-напоминание о том, что нужно зайти в приложение.
 */

public class HomeFragment extends Fragment {
    TextView helloTv;
    ImageButton firstBtn, secondBtn, thirdBtn, fourthBtn, first1Btn, second1Btn, third1Btn, fourth1Btn, first2Btn, second2Btn, third2Btn, fourth2Btn, first3Btn, second3Btn, third3Btn, fourth3Btn, first4Btn, second4Btn, third4Btn, fourth4Btn;
    ImageButton sosBtn;
    private SharedPreferences sharedPreferencesForTime;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        sharedPreferencesForTime = requireActivity().getSharedPreferences("MyAppPrefs", MODE_PRIVATE);

        //код, отображающий "доброе утро(день, вечер, ночь), юзернейм"
        helloTv = view.findViewById(R.id.helloTv);
        Calendar calendar = Calendar.getInstance();
        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        String greeting;
        if (timeOfDay >= 6 && timeOfDay < 12) {
            greeting = "Доброе утро";
        } else if (timeOfDay >= 12 && timeOfDay < 18) {
            greeting = "Добрый день";
        } else if (timeOfDay >= 18 && timeOfDay < 22) {
            greeting = "Добрый вечер";
        } else {
            greeting = "Доброй ночи";
        }
        SharedPreferences preferences = getActivity().getSharedPreferences("MY_PREFS", MODE_PRIVATE);
        String username = preferences.getString("USERNAME", "");
        String greetingMessage = greeting + ", " + username + "! Предлагаю вам заняться ежедневными практиками:";
        helloTv.setText(greetingMessage);

        //далее обрабатываем нажатие на sos-кнопку и info-кнопку
        ImageButton sosButton = view.findViewById(R.id.sosButton);
        sosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSOSButtonClick(v); //вызов метода, который перекинет в приложение "Телефон"
            }
        });
        ImageButton infoButton = view.findViewById(R.id.infoButton);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoPopup(); //возов метода, который отобразит вышеупомянутый AlertDialog
            }
        });

        //далее обрабатываем нажатие ImageButton, они будут перекидывать в активити с текстом и видео
        firstBtn = view.findViewById(R.id.firstBtn);
        secondBtn = view.findViewById(R.id.secondBtn);
        thirdBtn = view.findViewById(R.id.thirdBtn);
        fourthBtn = view.findViewById(R.id.fourthBtn);
        first1Btn = view.findViewById(R.id.first1Btn);
        second1Btn = view.findViewById(R.id.second1Btn);
        third1Btn = view.findViewById(R.id.third1Btn);
        fourth1Btn = view.findViewById(R.id.fourth1Btn);
        first2Btn = view.findViewById(R.id.first2Btn);
        second2Btn = view.findViewById(R.id.second2Btn);
        third2Btn = view.findViewById(R.id.third2Btn);
        fourth2Btn = view.findViewById(R.id.fourth2Btn);
        first3Btn = view.findViewById(R.id.first3Btn);
        second3Btn = view.findViewById(R.id.second3Btn);
        third3Btn = view.findViewById(R.id.third3Btn);
        fourth3Btn = view.findViewById(R.id.fourth3Btn);
        first4Btn = view.findViewById(R.id.first4Btn);
        second4Btn = view.findViewById(R.id.second4Btn);
        third4Btn = view.findViewById(R.id.third4Btn);
        fourth4Btn = view.findViewById(R.id.fourth4Btn);
        firstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FirstActivity.class);
                i.putExtra("numberOfButton", "1");
                startActivity(i);
            }
        });
        secondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FirstActivity.class);
                i.putExtra("numberOfButton", "2");
                startActivity(i);
            }
        });
        thirdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FirstActivity.class);
                i.putExtra("numberOfButton", "3");
                startActivity(i);
            }
        });
        fourthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FirstActivity.class);
                i.putExtra("numberOfButton", "4");
                startActivity(i);
            }
        });

        first1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SecondActivity.class);
                i.putExtra("numberOfButton", "1");
                startActivity(i);
            }
        });
        second1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SecondActivity.class);
                i.putExtra("numberOfButton", "2");
                startActivity(i);
            }
        });
        third1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SecondActivity.class);
                i.putExtra("numberOfButton", "3");
                startActivity(i);
            }
        });
        fourth1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SecondActivity.class);
                i.putExtra("numberOfButton", "4");
                startActivity(i);
            }
        });

        first2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ThirdActivity.class);
                i.putExtra("numberOfButton", "1");
                startActivity(i);
            }
        });
        second2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ThirdActivity.class);
                i.putExtra("numberOfButton", "2");
                startActivity(i);
            }
        });
        third2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ThirdActivity.class);
                i.putExtra("numberOfButton", "3");
                startActivity(i);
            }
        });
        fourth2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ThirdActivity.class);
                i.putExtra("numberOfButton", "4");
                startActivity(i);
            }
        });

        first3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FourthActivity.class);
                i.putExtra("numberOfButton", "1");
                startActivity(i);
            }
        });
        second3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FourthActivity.class);
                i.putExtra("numberOfButton", "2");
                startActivity(i);
            }
        });
        third3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FourthActivity.class);
                i.putExtra("numberOfButton", "3");
                startActivity(i);
            }
        });
        fourth3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FourthActivity.class);
                i.putExtra("numberOfButton", "4");
                startActivity(i);
            }
        });

        first4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FifthActivity.class);
                i.putExtra("numberOfButton", "1");
                startActivity(i);
            }
        });
        second4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FifthActivity.class);
                i.putExtra("numberOfButton", "2");
                startActivity(i);
            }
        });
        third4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FifthActivity.class);
                i.putExtra("numberOfButton", "3");
                startActivity(i);
            }
        });
        fourth4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FifthActivity.class);
                i.putExtra("numberOfButton", "4");
                startActivity(i);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //пользователь зашел в приложение
        //получаем текущую дату
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String today = dateFormat.format(currentDate);

        //проверяем, был ли пользователь сегодня в приложении
        boolean userVisitedToday = sharedPreferencesForTime.getBoolean(today, false);

        if (!userVisitedToday) {
            // Пользователь не был в приложении сегодня, отправляем уведомление
            sendNotification();
        }

        //сохраняем информацию о посещении пользователя
        SharedPreferences.Editor editor = sharedPreferencesForTime.edit();
        editor.putBoolean(today, true);
        editor.apply();
    }

    //этот метод формирует и отправляет уведомление
    private void sendNotification() {
        //отправка уведомления
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("my_notification_channel", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        int color = ContextCompat.getColor(requireContext(), R.color.button_background_color);
        String message = "Пришло время позаботиться о себе!";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), "my_notification_channel");
        builder.setContentTitle("Послушайте медитации и отметьте свое настроение в приложении.");
        builder.setContentText(message);
        builder.setSmallIcon(R.drawable.profile);
        builder.setAutoCancel(true);
        builder.setColor(color);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(requireContext());
        managerCompat.notify(1, builder.build());
    }

    //метод, показывающий AlertDialog с ссылкой при нажатии на кнопку инфо
    private void showInfoPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Информация о кнопке SOS");

        //создание SpannableString для сообщения диалога
        SpannableString message = new SpannableString("Вы можете нажать на SOS кнопку, если вам нужна срочная психологическая помощь." +
                " При нажатии на кнопку SOS, ваш телефон автоматически наберет номер психологической помощи, которая работает в круглосуточном режиме." +
                " Бесплатно. Анонимно. Конфиденциально. https://www.telefon150.kz/");

        //создание ClickableSpan для ссылки
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                //открытие ссылки в браузере
                Uri uri = Uri.parse("https://www.telefon150.kz/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        };

        //установка ClickableSpan для ссылки
        message.setSpan(clickableSpan, 250, 276, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder.setMessage(message);
        builder.setPositiveButton("ОК", null);
        AlertDialog dialog = builder.create();
        dialog.show();

        //установка ссылки кликабельной и стиля текста ссылки
        TextView textView = dialog.findViewById(android.R.id.message);
        if (textView != null) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            SpannableString spannableString = (SpannableString) textView.getText();
            UnderlineSpan[] underlineSpans = spannableString.getSpans(0, spannableString.length(), UnderlineSpan.class);
            for (UnderlineSpan span : underlineSpans) {
                spannableString.removeSpan(span);
            }
            int color = ContextCompat.getColor(requireContext(), R.color.button_background_color);
            textView.setLinkTextColor(color);
            textView.setText(spannableString);
        }
    }

    //метод, который открывает приложение "телефон" при нажатии на сос-кнопку
    public void onSOSButtonClick(View view) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:150"));
        startActivity(callIntent);
    }
}