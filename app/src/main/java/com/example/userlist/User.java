package com.example.userlist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

@Entity
public class User {

    public String nickName,smile,exp,messageCount;
    @PrimaryKey
    public long id;

    public User(String nickName, String smile, String exp, String messageCount){
        this.nickName = nickName;
        this.smile = smile;
        this.exp = exp;
        this.messageCount = messageCount;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSmile() {
        return smile;
    }

    public void setSmile(String smile) {
        this.smile = smile;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(String messageCount) {
        this.messageCount = messageCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User: " + nickName + " " + smile + ", exp: " + exp + ", messageCount:" + messageCount;
    }
}
