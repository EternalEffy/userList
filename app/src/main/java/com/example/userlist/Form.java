package com.example.userlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Form extends Activity {

    private Button add,back;
    private EditText name,smile,exp,messageCount;
    private DBHelper dbHelper;
    private SQLiteDatabase usersDb;
    private ContentValues contentValues;
    private int l;
    private Bundle arguments;
    private Languages lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        arguments = getIntent().getExtras();
        l = (int) arguments.get("language");
        initRes();
        lang.setLanguages();
        setLanguage(l);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(name.getText().toString(),smile.getText().toString(),exp.getText().toString(), messageCount.getText().toString());
                contentValues.put(DBHelper.KEY_NAME,user.getNickName());
                contentValues.put(DBHelper.KEY_SMILE,user.getSmile());
                contentValues.put(DBHelper.KEY_EXP,user.getExp());
                contentValues.put(DBHelper.KEY_COUNT,user.getMessageCount());

                usersDb.insert(DBHelper.TABLE_USERS,null,contentValues);
                if ( l % 2 == 0)
                    Toast.makeText(Form.this,"пользователь добавлен",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Form.this,"user was added",Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back_to_main = new Intent(Form.this,MainActivity.class);
                startActivity(back_to_main);
            }
        });
    }

    private void setLanguage(int l) {
        switch (l%2){
            case 0:
                add.setText(lang.languages[0][0][1]);
                back.setText(lang.languages[0][1][4]);
                name.setHint(lang.languages[0][1][0]);
                smile.setHint(lang.languages[0][1][1]);
                exp.setHint(lang.languages[0][1][2]);
                messageCount.setHint(lang.languages[0][1][3]);
                break;
            case 1:
                add.setText(lang.languages[1][0][1]);
                back.setText(lang.languages[1][1][4]);
                name.setHint(lang.languages[1][1][0]);
                smile.setHint(lang.languages[1][1][1]);
                exp.setHint(lang.languages[1][1][2]);
                messageCount.setHint(lang.languages[1][1][3]);
        }
    }

    private void initRes(){
        lang = new Languages();
        add = findViewById(R.id.add_form);
        back = findViewById(R.id.back);
        name = findViewById(R.id.userName);
        smile = findViewById(R.id.smile);
        exp = findViewById(R.id.exp);
        messageCount = findViewById(R.id.messageCount);
        dbHelper = new DBHelper(this);
        usersDb = dbHelper.getWritableDatabase();
        contentValues = new ContentValues();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}