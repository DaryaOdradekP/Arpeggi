package com.example.arpeggi11.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arpeggi11.Person;
import com.example.arpeggi11.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/*Активити, который отвечает за регистрацию пользователей.
Если такой пользователь существует/пароль при подтверждении был неверный/некорректный, то выводится соответсвенный toast.
Если пользователь сделал все правильно, его данные загружаются в базу.
 */
public class SignInActivity extends AppCompatActivity {
    EditText name, login, password1, password2;
    Button signIn;
    FirebaseDatabase db;
    DatabaseReference people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        name = findViewById(R.id.nameRegTxt);
        login = findViewById(R.id.loginRegTxt);
        password1 = findViewById(R.id.passwordRegTxt);
        password2 = findViewById(R.id.password2RegTxt);
        signIn = findViewById(R.id.signRegInBtn);
        db = FirebaseDatabase.getInstance("https://arpeggi-default-rtdb.europe-west1.firebasedatabase.app/");
        people = db.getReference().child("People");
        //получаем данные из EditText
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usersName = name.getText().toString();
                String usersLogin = login.getText().toString();
                String usersPassword = password1.getText().toString();
                String usersCheck = password2.getText().toString();

                final boolean[] loginExist = {false};
                //идем в базу по ветки логина
                Query query = people.orderByChild("login").equalTo(usersLogin);
                ValueEventListener listener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            //человек с таким логином уже существует - сообщаем об этом
                            loginExist[0] = true;
                            Toast.makeText(getApplicationContext(), "пользователь с таким логином уже существует.", Toast.LENGTH_LONG).show();
                        } else {
                            if (usersPassword.length() > 7) {
                                if (usersPassword.equals(usersCheck)) {
                                    //пароль длиннее 7 символов, логин новый - регистрируем юзера
                                    Person p = new Person(usersName, usersLogin, usersPassword);
                                    Toast.makeText(getApplicationContext(), "регистрация прошла успешно!", Toast.LENGTH_LONG).show();
                                    DatabaseReference push = people.push();
                                    String key = push.getKey();
                                    p.setKey(key);
                                    push.setValue(p);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "вы неверно подтвердили пароль", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "ваш пароль слишком короткий", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                };
                query.addListenerForSingleValueEvent(listener);
            }
        });
    }
}