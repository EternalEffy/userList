package com.example.userlist;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private Button add,remove,language;
    private TextView message;
    private ListView userList;
    private ArrayList<String> users = new ArrayList<>();
    private DBHelper dbHelper;
    private SQLiteDatabase usersDb;
    private Cursor cursor;
    private String user;
    private int idUser=0;
    private Languages lang;
    private int l = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRes();
        loadDb();
        lang.setLanguages();
        setLanguage(l);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent form = new Intent(MainActivity.this,Form.class);
                form.putExtra("language",l);
                startActivity(form);
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idUser>0) {
                    usersDb.delete(DBHelper.TABLE_USERS, DBHelper.KEY_ID + " = " + idUser, null);
                    if(l % 2 == 0)
                        Toast.makeText(MainActivity.this, "пользователь удалён", Toast.LENGTH_SHORT).show();
                        else Toast.makeText(MainActivity.this, "user was deleted", Toast.LENGTH_SHORT).show();
                    recreate();
                }else
                    if (l % 2 == 0)
                        Toast.makeText(MainActivity.this, "пожалуйста выберете пользователя для удаления", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "pls select user for delete", Toast.LENGTH_SHORT).show();
            }
        });

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                idUser= cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ID));
            }
        });

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l++;
                setLanguage(l);
            }
        });

        userList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,users));
    }

    private void initRes(){
        lang = new Languages();
        language = findViewById(R.id.lang);
        add = findViewById(R.id.add);
        remove = findViewById(R.id.remove);
        message = findViewById(R.id.message);
        userList = findViewById(R.id.userList);
        dbHelper = new DBHelper(this);
        usersDb = dbHelper.getWritableDatabase();
        cursor = usersDb.query(DBHelper.TABLE_USERS,null,null,null,null,null,null);
    }

    private void loadDb(){
        if(cursor.moveToFirst()){
            do {
                user = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ID)) + ". " + cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NAME)) + " " +
                        cursor.getString(cursor.getColumnIndex(DBHelper.KEY_SMILE)) + "\nexp: " + cursor.getString(cursor.getColumnIndex(DBHelper.KEY_EXP)) + "\nmessage count: " +
                        cursor.getString(cursor.getColumnIndex(DBHelper.KEY_COUNT)) + " ";
                users.add(user);
            }while (cursor.moveToNext());
        }else
            Log.d("@@@@","0 rows");
    }

    private void setLanguage(int l){
        switch (l%2){
            case 0:
                language.setText(lang.languages[0][0][3]);
                add.setText(lang.languages[0][0][1]);
                remove.setText(lang.languages[0][0][2]);
                if (users.size() > 0) {
                    message.setText(lang.languages[0][0][0]);
                } else {
                    message.setText(lang.languages[0][1][5]);
                }
                break;
            case 1:
                language.setText(lang.languages[1][0][3]);
                add.setText(lang.languages[1][0][1]);
                remove.setText(lang.languages[1][0][2]);
                if (users.size() > 0) {
                    message.setText(lang.languages[1][0][0]);
                } else {
                    message.setText(lang.languages[1][1][5]);
                }
        }
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

    @Override
    public void recreate() {
        super.recreate();
    }
}