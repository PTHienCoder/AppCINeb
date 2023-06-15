package com.example.app_project.Model;

import java.util.List;

public class Model_Comment {
    String id,id_post, id_of_user, comment_content, description,date, avatar, username;
     List<Model_Comment> Model_Comment;

    public List<com.example.app_project.Model.Model_Comment> getModel_Comment() {
        return Model_Comment;
    }

    public void setModel_Comment(List<com.example.app_project.Model.Model_Comment> model_Comment) {
        Model_Comment = model_Comment;
    }

    public Model_Comment(String id, String id_post, String id_of_user, String comment_content, String description, String date, String avatar, String username) {
        this.id = id;
        this.id_post = id_post;
        this.id_of_user = id_of_user;
        this.comment_content = comment_content;
        this.description = description;
        this.date = date;
        this.avatar = avatar;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_post() {
        return id_post;
    }

    public void setId_post(String id_post) {
        this.id_post = id_post;
    }

    public String getId_of_user() {
        return id_of_user;
    }

    public void setId_of_user(String id_of_user) {
        this.id_of_user = id_of_user;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Model_Comment(){

    }
}
