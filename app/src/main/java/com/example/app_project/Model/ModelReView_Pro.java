package com.example.app_project.Model;

import java.util.List;

public class ModelReView_Pro {
    int id_review, id_user,id_product;
    String content_review, rating_review,time_review, image_review, name, image_user;
    List<ModelReView_Pro> ModelReView_Pro;

    public ModelReView_Pro(int id_review, int id_user, int id_product, String content_review, String rating_review, String time_review, String image_review, String name, String image_user) {
        this.id_review = id_review;
        this.id_user = id_user;
        this.id_product = id_product;
        this.content_review = content_review;
        this.rating_review = rating_review;
        this.time_review = time_review;
        this.image_review = image_review;
        this.name = name;
        this.image_user = image_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_user() {
        return image_user;
    }

    public void setImage_user(String image_user) {
        this.image_user = image_user;
    }

    public int getId_review() {
        return id_review;
    }

    public void setId_review(int id_review) {
        this.id_review = id_review;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getContent_review() {
        return content_review;
    }

    public void setContent_review(String content_review) {
        this.content_review = content_review;
    }

    public String getRating_review() {
        return rating_review;
    }

    public void setRating_review(String rating_review) {
        this.rating_review = rating_review;
    }

    public String getTime_review() {
        return time_review;
    }

    public void setTime_review(String time_review) {
        this.time_review = time_review;
    }

    public String getImage_review() {
        return image_review;
    }

    public void setImage_review(String image_review) {
        this.image_review = image_review;
    }

    public List<com.example.app_project.Model.ModelReView_Pro> getModelReView_Pro() {
        return ModelReView_Pro;
    }

    public void setModelReView_Pro(List<com.example.app_project.Model.ModelReView_Pro> modelReView_Pro) {
        ModelReView_Pro = modelReView_Pro;
    }

    public ModelReView_Pro() {
    }
}
