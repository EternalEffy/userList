package com.example.userlist;

public class Languages {
    public String [][][] languages = new String[2][3][8];

    public void setLanguages() {
        languages [0][0][0] = "Список пользователей";
        languages [0][0][1] = "добавить пользователя";
        languages [0][0][2] = "удалить пользователя";
        languages [0][0][3] = "язык";
        languages [1][0][0] = "Saved users";
        languages [1][0][1] = "add";
        languages [1][0][2] = "remove";
        languages [1][0][3] = "lang";
        languages [0][1][0] = "имя пользователя";
        languages [0][1][1] = "смайлик";
        languages [0][1][2] = "опыт";
        languages [0][1][3] = "кол-во сообщений";
        languages [0][1][4] = "назад";
        languages [0][1][5] = "Нет данных. Добавьте больше пользователей";
        languages [1][1][0] = "nickname";
        languages [1][1][1] = "smile";
        languages [1][1][2] = "user exp";
        languages [1][1][3] = "message count";
        languages [1][1][4] = "back";
        languages [1][1][5] = "Nothing to show. Please add some users";
    }
}
