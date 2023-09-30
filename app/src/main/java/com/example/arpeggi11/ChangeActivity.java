package com.example.arpeggi11;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
/*
Активити, служащий для того, чтобы менять пароль пользователя.
 */
public class ChangeActivity extends AppCompatActivity {
    EditText oldPasswordTxt, newPasswordTxt;
    Button changeOkBtn;
    FirebaseDatabase db;
    Person thatPerson;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        oldPasswordTxt = findViewById(R.id.oldPasswordTxt);
        newPasswordTxt = findViewById(R.id.newPasswordTxt);
        changeOkBtn = findViewById(R.id.changeOkBtn);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        changeOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE);
                String password = sharedPreferences.getString("PASSWORD", "");
                String userKey = sharedPreferences.getString("KEY", "");
                db = FirebaseDatabase.getInstance("https://arpeggi-default-rtdb.europe-west1.firebasedatabase.app/");
                databaseReference = db.getReference().child("People");
                String oldPass = oldPasswordTxt.getText().toString();
                String newPass = newPasswordTxt.getText().toString();
                if (oldPass.equals(password)) {
                    databaseReference.child(userKey).child("password").setValue(newPass);
                    Toast.makeText(getApplicationContext(), "Пароль был успешно изменен", Toast.LENGTH_LONG).show();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Неверный пароль!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
