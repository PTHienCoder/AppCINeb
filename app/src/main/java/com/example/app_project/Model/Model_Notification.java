package com.example.app_project.Model;

import java.util.List;

public class Model_Notification {
    String idnoti, idp, timeNoti, content, id, username, avatar;
    List<Model_Notification> Model_Notification;
    public Model_Notification(String idnoti, String idp, String timeNoti, String content, String id, String username, String avatar) {
        this.idnoti = idnoti;
        this.idp = idp;
        this.timeNoti = timeNoti;
        this.content = content;
        this.id = id;
        this.username = username;
        this.avatar = avatar;
    }

    public List<com.example.app_project.Model.Model_Notification> getModel_Notification() {
        return Model_Notification;
    }

    public void setModel_Notification(List<com.example.app_project.Model.Model_Notification> model_Notification) {
        Model_Notification = model_Notification;
    }

    public String getIdnoti() {
        return idnoti;
    }

    public void setIdnoti(String idnoti) {
        this.idnoti = idnoti;
    }

    public String getIdp() {
        return idp;
    }

    public void setIdp(String idp) {
        this.idp = idp;
    }

    public String getTimeNoti() {
        return timeNoti;
    }

    public void setTimeNoti(String timeNoti) {
        this.timeNoti = timeNoti;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Model_Notification(){

    }
}
