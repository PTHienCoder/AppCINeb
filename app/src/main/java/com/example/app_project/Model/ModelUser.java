package com.example.app_project.Model;

import java.util.List;

public class ModelUser {
    int id;
    String  name ,email, password, image_user, phone_user,
            birthday, story,type_user;

    List<ModelUser> ModelUser;

    public ModelUser(int id, String name, String email, String password, String image_user, String phone_user, String birthday, String story, String type_user) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.image_user = image_user;
        this.phone_user = phone_user;
        this.birthday = birthday;
        this.story = story;
        this.type_user = type_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage_user() {
        return image_user;
    }

    public void setImage_user(String image_user) {
        this.image_user = image_user;
    }

    public String getPhone_user() {
        return phone_user;
    }

    public void setPhone_user(String phone_user) {
        this.phone_user = phone_user;
    }


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getType_user() {
        return type_user;
    }

    public void setType_user(String type_user) {
        this.type_user = type_user;
    }

    public List<com.example.app_project.Model.ModelUser> getModelUser() {
        return ModelUser;
    }

    public void setModelUser(List<com.example.app_project.Model.ModelUser> modelUser) {
        ModelUser = modelUser;
    }

    public ModelUser() {
    }
}
