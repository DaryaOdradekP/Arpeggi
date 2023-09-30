package com.example.arpeggi11.mood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.arpeggi11.R;
import com.example.arpeggi11.classes.Problem;
import com.example.arpeggi11.fragments.HomeFragment;
import com.example.arpeggi11.registration.AppActivity;
import com.example.arpeggi11.registration.MainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.ArrayList;
import java.util.List;

/*
Активити, отображающий сообщения "бота", значение которых зависит от того, что ответил пользователь в предыдущих активностях.
Ответ анимированный, заключен в двух сообщениях. После того, как сообщение напечатолось, отображается кнопка перехода на главный фрагмент.
 */

public class ChatActivity extends AppCompatActivity {
    private LinearLayout greetingContainer;
    private LinearLayout messageContainer;
    private TextView greetingTextView;
    private TextView chatTextView;
    private String greetingMessage;
    private String chatMessage;
    private Problem prevProblem;
    private SpannableStringBuilder spannableStringBuilder;
    private Handler handler;
    private int charIndex = 0;
    Button nextButton;
    private int delay = 30; // Задержка между символами
    FirebaseDatabase db;
    Button nextBtn;
    DatabaseReference problem;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        db = FirebaseDatabase.getInstance("https://arpeggi-default-rtdb.europe-west1.firebasedatabase.app/");
        problem = db.getReference().child("Problem");
        prevProblem = getIntent().getParcelableExtra("problem");
        nextBtn = findViewById(R.id.nextBtn);

        greetingContainer = findViewById(R.id.greetingContainer);
        messageContainer = findViewById(R.id.messageContainer);
        greetingTextView = findViewById(R.id.greetingTextView);
        chatTextView = findViewById(R.id.chatTextView);
        prevProblem = getIntent().getParcelableExtra("problem");

        //создаем объект SpannableStringBuilder для форматирования текста с разными стилями
        spannableStringBuilder = new SpannableStringBuilder();

        //задаем прямоугольники для контейнеров
        setRectangleBorder(greetingContainer);
        setRectangleBorder(messageContainer);

        handler = new Handler();
        SharedPreferences preferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE);
        String username = preferences.getString("USERNAME", null);

        greetingMessage = "Здравствуйте, " + username + "!!";
        chatMessage = "";

        animateGreetingText();

        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this, AppActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //метод, отвечающий за печать первого сообщения
    private void animateGreetingText() {
        int backgroundColor = getResources().getColor(R.color.white);
        messageContainer.setBackgroundColor(backgroundColor);
        //останавливаем обновление, если текст полностью отображен
        if (charIndex >= greetingMessage.length()) {
            //задержка перед началом печати второго сообщения
            handler.postDelayed(this::startChat, 1000);
            return;
        }
        //добавляем следующий символ к SpannableStringBuilder
        SpannableString spannableString = new SpannableString(greetingMessage.substring(0, charIndex));
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(android.R.color.white)),
                0, spannableString.length(), 0);
        spannableStringBuilder.clear();
        spannableStringBuilder.append(spannableString);
        //устанавливаем текст в TextView
        greetingTextView.setText(spannableStringBuilder);
        //увеличиваем индекс символа
        charIndex++;
        //задержка перед добавлением следующего символа
        handler.postDelayed(this::animateGreetingText, delay);
    }

    private void startChat() {
        charIndex = 0;
        if (prevProblem != null) {
            String problemName = prevProblem.getProblem();
            String problemDegree = prevProblem.getDegree();
            List<String> problemCauses = prevProblem.getReason();
            //немного меняем значения настроения для корректного отображения
            if(problemName.equals("счастье")){
                problemName = "счастья";
            }
            if(problemName.equals("нежность")){
                problemName = "нежности";
            }
            if(problemName.equals("гордость")){
                problemName = "гордости";
            }
            if(problemName.equals("спокойствие")){
                problemName = "спокойствия";
            }
            if(problemName.equals("оптимизм")){
                problemName = "оптимизма";
            }
            if(problemName.equals("благодарность")){
                problemName = "благодарности";
            }
            if(problemName.equals("усталость")){
                problemName = "усталости";
            }
            if(problemName.equals("оцепенение")){
                problemName = "оцепенения";
            }
            if(problemName.equals("скука")){
                problemName = "скуки";
            }
            if(problemName.equals("злость")){
                problemName = "злости";
            }
            if(problemName.equals("раздраженность")){
                problemName = "раздраженности";
            }
            if(problemName.equals("стресс")){
                problemName = "стресса";
            }
            if(problemName.equals("тревога")){
                problemName = "тревоги";
            }
            if(problemName.equals("небезопасность")){
                problemName = "небезопасности";
            }
            if(problemName.equals("страх")){
                problemName = "страха";
            }
            if(problemName.equals("грусть")){
                problemName = "грусти";
            }
            if(problemName.equals("стыд")){
                problemName = "стыда";
            }
            if(problemName.equals("вина")){
                problemName = "вины";
            }
            //формируем сообщение на основе полученных данных
            if (problemName.equals("счастья") || problemName.equals("нежности") || problemName.equals("гордости")) {
                chatMessage = "Я рад, что сегодня вы испытали " + problemDegree + " " + problemName + "!" + '\n';

                if (problemCauses.contains("тело")) {
                    chatMessage += "Это чудесно, что вы довольны своим телом, ведь любить себя очень важно." + '\n';
                }
                if (problemCauses.contains("музыка")) {
                    chatMessage += "Музыка - неотъемлемая часть жизни каждого. То, что музыка улучшает ваше настроение - потрясающе!" + '\n';
                }
                if (problemCauses.contains("тренировка")) {
                    chatMessage += "Хорошая тренировка полезна и для тела, и для духа! " +
                            "Во время занятий спортом вырабатываются гормоны-нейромедиаторы, которые поднимают настроение." + '\n';
                }
                if (problemCauses.contains("еда")) {
                    chatMessage += "Еда, без нее человек не может жить. " +
                            "Она участвует во всех процессах тела. " +
                            "Пища - людское топпливо, я рад, что у вас был хороший прием пищи!" + '\n';
                }
                if (problemCauses.contains("я")) {
                    chatMessage += "Это чудесно, что вы довольны собой, ведь любить себя очень важно." + '\n';
                }
                if (problemCauses.contains("внешний мир")) {
                    chatMessage += "Внешний мир - это то, на что мы никак не можем повлиять, это всегда русская рулетка. " +
                            "Это чудесно, что сегодня вас ничего не задело извне!" + '\n';
                }
                if (problemCauses.contains("здоровье")) {
                    chatMessage += "Физическое здоровье - залог крепкого ментального. " +
                            "Чтобы оставаться здоровым, необходимо правильно питаться," +
                            " достаточно спать и чаще проводить время на свежем воздухе." + '\n';
                }
                if (problemCauses.contains("партнер")) {
                    chatMessage += "Партнер - это человек, на которого всегда можно положиться. " +
                            "Ваш спутник должен приносить вам радость и любовь." + '\n';
                }
                if (problemCauses.contains("семья")) {
                    chatMessage += "Семья - важная часть жизни каждого. " +
                            "Общение с семьей может принести много радости и позитивных эмоций." + '\n';
                }
                if (problemCauses.contains("школа")) {
                    chatMessage += "Как же чудесно получать удовольствие от образования!  " +
                            "Продолжайте в том же духе." + '\n';
                }
                if (problemCauses.contains("дружба")) {
                    chatMessage += "Дружба - важная часть жизни каждого. " +
                            "Общение с друзьями может принести много радости и позитивных эмоций." + '\n';
                }
                if (problemCauses.contains("сон")) {
                    chatMessage += "Здоровый и крепкий сон - залог успешного дня. " +
                            "Не забывайте вовремя ложиться." + '\n';
                }
                if (problemCauses.contains("дом")) {
                    chatMessage += "Домашняя обстановка может оказать значительное влияние на наше общее самочувствие. " +
                            "Это чудесно чувствовать себя в безопасности дома." + '\n';
                }
                if (problemCauses.contains("социальные сети")) {
                    chatMessage += "Социальные сети - это то, на что мы никак не можем повлиять, это всегда русская рулетка. " +
                            "Это чудесно, что сегодня вас ничего не задело извне!" + '\n';
                }
                if (problemCauses.contains("деньги")) {
                    chatMessage += "Финансовые вопросы могут быть источником как стресса, так и успеха. " + '\n';
                }
                if (problemCauses.contains("работа")) {
                    chatMessage += "Уверенность может оказать значительное " +
                            "положительное влияние на вашу производительность труда и общее самочувствие. " + '\n';
                }
                //запоминаем время, когда было напечататно сообщение и весь Problem закодываем в базу данных
                Problem prevProblem = getIntent().getParcelableExtra("problem");
                calendar=Calendar.getInstance();
                simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Time = simpleDateFormat.format(calendar.getTime());
                prevProblem.setTime(Time);
                prevProblem.setText(chatMessage);

            } else if (problemName.equals("спокойствия") || problemName.equals("оптимизма") || problemName.equals("благодарности")) {
                chatMessage = "Это хорошо, что сегодня вы испытали " + problemDegree + " " + problemName + "!" + '\n';
                if (problemCauses.contains("тело")) {
                    chatMessage += "Это чудесно, что вы довольны своим телом, ведь любить себя очень важно." + '\n';
                }
                if (problemCauses.contains("музыка")) {
                    chatMessage += "Музыка - неотъемлемая часть жизни каждого. То, что музыка улучшает ваше настроение - потрясающе!" + '\n';
                }
                if (problemCauses.contains("тренировка")) {
                    chatMessage += "Хорошая тренировка полезна и для тела, и для духа! " +
                            "Во время занятий спортом вырабатываются гормоны-нейромедиаторы, которые поднимают настроение." + '\n';
                }
                if (problemCauses.contains("еда")) {
                    chatMessage += "Еда, без нее человек не может жить. " +
                            "Она участвует во всех процессах тела. " +
                            "Пища - людское топпливо, я рад, что у вас был хороший прием пищи!" + '\n';
                }
                if (problemCauses.contains("я")) {
                    chatMessage += "Это чудесно, что вы довольны собой, ведь любить себя очень важно." + '\n';
                }
                if (problemCauses.contains("внешний мир")) {
                    chatMessage += "Внешний мир - это то, на что мы никак не можем повлиять, это всегда русская рулетка. " +
                            "Это чудесно, что сегодня вас ничего не задело извне!" + '\n';
                }
                if (problemCauses.contains("здоровье")) {
                    chatMessage += "Физическое здоровье - залог крепкого ментального. " +
                            "Чтобы оставаться здоровым, необходимо правильно питаться, " +
                            "достаточно спать и чаще проводить время на свежем воздухе." + '\n';
                }
                if (problemCauses.contains("партнер")) {
                    chatMessage += "Партнер - это человек, на которого всегда можно положиться. " +
                            "Ваш спутник должен приносить вам радость и любовь." + '\n';
                }
                if (problemCauses.contains("семья")) {
                    chatMessage += "Семья - важная часть жизни каждого. " +
                            "Общение с семьей может принести много радости и позитивных эмоций." + '\n';
                }
                if (problemCauses.contains("школа")) {
                    chatMessage += "Как же чудесно получать удовольствие от образования!  " +
                            "Продолжайте в том же духе." + '\n';
                }
                if (problemCauses.contains("дружба")) {
                    chatMessage += "Дружба - важная часть жизни каждого. " +
                            "Общение с друзьями может принести много радости и позитивных эмоций." + '\n';
                }
                if (problemCauses.contains("сон")) {
                    chatMessage += "Здоровый и крепкий сон - залог успешного дня. " +
                            "Не забывайте вовремя ложиться." + '\n';
                }
                if (problemCauses.contains("дом")) {
                    chatMessage += "Домашняя обстановка может оказать значительное влияние на наше общее самочувствие. " +
                            "Это чудесно чувствовать себя в безопасности дома." + '\n';
                }
                if (problemCauses.contains("социальные сети")) {
                    chatMessage += "Социальные сети - это то, на что мы никак не можем повлиять, это всегда русская рулетка. " +
                            "Это чудесно, что сегодня вас ничего не задело извне!" + '\n';
                }
                if (problemCauses.contains("деньги")) {
                    chatMessage += "Финансовые вопросы могут быть источником как стресса, так и успеха. " + '\n';
                }
                if (problemCauses.contains("работа")) {
                    chatMessage += "Уверенность может оказать значительное положительное влияние" +
                            " на вашу производительность труда и общее самочувствие. " + '\n';
                }
                //запоминаем время, когда было напечататно сообщение и весь Problem закодываем в базу данных
                Problem prevProblem = getIntent().getParcelableExtra("problem");
                calendar=Calendar.getInstance();
                simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Time = simpleDateFormat.format(calendar.getTime());
                prevProblem.setTime(Time);
                prevProblem.setText(chatMessage);

            } else if (problemName.equals("усталости") || problemName.equals("оцепенения") || problemName.equals("скуки") ||
                    problemName.equals("злости") || problemName.equals("раздраженности") || problemName.equals("стресса") ||
                    problemName.equals("тревоги") || problemName.equals("небезопасности") || problemName.equals("страха") ||
                    problemName.equals("грусти") || problemName.equals("стыда") || problemName.equals("вины")) {
                chatMessage = "Это не очень хорошо, что сегодня вы испытали " + problemDegree + " " + problemName + "." + '\n';

                if (problemName.equals("усталости") || problemName.equals("стресса")) {
                    chatMessage += "Вам определенно нужно отдохнуть. Почитайте книгу или посмотрите фильм," +
                            " примите теплую ванну и поспите." + '\n';
                }
                if (problemName.equals("скуки")) {
                    chatMessage += "Почитайте книгу или посмотрите фильм," +
                            " начните учить новый язык или переберите все свои вещи." + '\n';
                }
                if (problemName.equals("злости")) {
                    chatMessage += "Я считаю, что вам нужно выместить свои эмоции. " +
                            "Уничтожте ненужную вещь или попробуйте метод пустого стула, послушайте музыку. " + '\n';
                }
                if (problemName.equals("раздраженности")) {
                    chatMessage += "Попробуйте выявить фактор, который вас раздражает " +
                            "и по возможности исключите его. Только не бросайтесь во все тяжкие. " + '\n';
                }
                if (problemName.equals("тревоги") || problemName.equals("небезопасности") || problemName.equals("страха")) {
                    chatMessage += "Включите успокаивающую музыку," +
                            " вспомните о позитивных моментах и попытайтесь избегать обстоятельств," +
                            " провоцирующих тревогу, — триггеров. " + '\n';
                }
                if (problemName.equals("грусти")) {
                    chatMessage += "Дышите глубже," +
                            " примите прохладный душ, направляя струю воды на затылок или переносицу, " +
                            "выйдите на пробежку или на прогулку. Я уверен, это поможет вам. " + '\n';
                }
                if (problemName.equals("стыда") || problemName.equals("вины")) {
                    chatMessage += "Начните снова сочувствовать себе и помните, что" +
                            " вроблема не в вас. " + '\n';
                }
                if (problemCauses.contains("тело")) {
                    chatMessage += "Любить себя очень важно. Не забывайте напоминать себе о том, что вы уникальны." + '\n';
                }
                if (problemCauses.contains("музыка")) {
                    chatMessage += "Музыка - неотъемлемая часть жизни каждого. " +
                            "Вам определенно стоит найти нового исполнителя или жанр, который придется вам по вкусу." + '\n';
                }
                if (problemCauses.contains("тренировка")) {
                    chatMessage += "Хорошая тренировка полезна и для тела, и для духа! " +
                            "Во время занятий спортом вырабатываются гормоны-нейромедиаторы," +
                            " которые поднимают настроение. Вам стоит найти новый любимый вид спорта." + '\n';
                }
                if (problemCauses.contains("еда")) {
                    chatMessage += "Еда, без нее человек не может жить. " +
                            "Она участвует во всех процессах тела. " +
                            "Пища - людское топпливо. Не забывайте об этом." + '\n';
                }
                if (problemCauses.contains("я")) {
                    chatMessage += "Любить себя очень важно. Не забывайте напоминать себе о том, что вы уникальны." + '\n';
                }
                if (problemCauses.contains("внешний мир")) {
                    chatMessage += "Внешний мир - это то, на что мы никак не можем повлиять, это всегда русская рулетка. " +
                            "Возможно, вам стоит отдохнуть от новостей, побыть какое-то время наедине с собой." + '\n';
                }
                if (problemCauses.contains("здоровье")) {
                    chatMessage += "Физическое здоровье - залог крепкого ментального. " +
                            "Чтобы оставаться здоровым, необходимо правильно питаться, достаточно спать и" +
                            " чаще проводить время на свежем воздухе." + '\n';
                }
                if (problemCauses.contains("партнер")) {
                    chatMessage += "Партнер - это человек, на которого всегда можно положиться. " +
                            "Ваш спутник должен приносить вам радость и любовь, а если это не так, то стоит поговорить с ним." + '\n';
                }
                if (problemCauses.contains("семья")) {
                    chatMessage += "Семья - важная часть жизни каждого. " +
                            "Общение с семьей может принести много радости и позитивных эмоций, " +
                            "но в ту же очередь и много негативных. Если у вас возникают какие-то проблемы с семьей," +
                            " вам нужно обсуить это." + '\n';
                }
                if (problemCauses.contains("школа")) {
                    chatMessage += "Процесс обучения не всегда очень весел, но я уверен, что он того стоит. " +
                            "Продолжайте в том же духе." + '\n';
                }
                if (problemCauses.contains("дружба")) {
                    chatMessage += "Дружба - важная часть жизни каждого. " +
                            "Общение с друзьями может принести много радости и позитивных эмоций, " +
                            "а если это не так, то стоит обсудить это ними." + '\n';
                }
                if (problemCauses.contains("сон")) {
                    chatMessage += "Здоровый и крепкий сон - залог успешного дня. " +
                            "Постарайтесь лечь сегодня пораньше." + '\n';
                }
                if (problemCauses.contains("дом")) {
                    chatMessage += "Домашняя обстановка может оказать значительное влияние на ваше общее самочувствие. " +
                            "Это чудесно чувствовать себя в безопасности дома." + '\n';
                }
                if (problemCauses.contains("социальные сети")) {
                    chatMessage += "Социальные сети - это то, на что мы никак не можем повлиять, это всегда русская рулетка. " +
                            "Возмоожно, вам стоит устроить интернет детокс." + '\n';
                }
                if (problemCauses.contains("деньги")) {
                    chatMessage += "Финансовые вопросы могут быть источником как стресса, так и успеха. " + '\n';
                }
                if (problemCauses.contains("работа")) {
                    chatMessage += "Уверенность может оказать значительное положительное" +
                            " влияние на вашу производительность труда и общее самочувствие. " + '\n';
                }
                //запоминаем время, когда было напечататно сообщение и весь Problem закодываем в базу данных
                Problem prevProblem = getIntent().getParcelableExtra("problem");
                calendar=Calendar.getInstance();
                simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Time = simpleDateFormat.format(calendar.getTime());
                prevProblem.setTime(Time);
                prevProblem.setText(chatMessage);
            }
            chatMessage += "Надеюсь, мои советы были полезны для вас." +
                    " Вам определенно стоит теперь посмотреть видео и послушать музыку с главной страницы приложения.";
            chatMessage += '\n';
        }
        handler.postDelayed(this::animateText, 500); //задержка
    }

    //метод, отвечающий за печать второго сообщения
    private void animateText() {
        messageContainer.setBackgroundResource(R.drawable.rounded_rectangle);
        //останавливаем обновление, если текст полностью отображен
        if (charIndex >= chatMessage.length()) {
            nextButton.setVisibility(View.VISIBLE);
            pushingProblem(prevProblem);
            return;
        }
        //добавляем следующий символ к SpannableStringBuilder
        SpannableString spannableString = new SpannableString(chatMessage.substring(0, charIndex));
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(android.R.color.white)),
                0, spannableString.length(), 0);
        spannableStringBuilder.clear();
        spannableStringBuilder.append(spannableString);
        //устанавливаем текст в TextView
        chatTextView.setText(spannableStringBuilder);
        //увеличиваем индекс символа
        charIndex++;
        //задержка перед добавлением следующего символа
        handler.postDelayed(this::animateText, delay);
    }
    //метод, отвечающий за отображение окна сообщения (прямоугольника, в котором оно печатается)
    private void setRectangleBorder(View view) {
        Drawable border = ContextCompat.getDrawable(this, R.drawable.rounded_rectangle);
        view.setBackground(border);
    }
    //добавляет проблему в базу
    void pushingProblem(Problem p) {
        DatabaseReference push = problem.push();
        String key = push.getKey();
        p.setKey(key);
        push.setValue(p);
       // finish();
    }
}