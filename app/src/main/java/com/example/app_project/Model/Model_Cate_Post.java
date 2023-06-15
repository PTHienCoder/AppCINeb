package com.example.app_project.Model;

import java.util.List;

public class Model_Cate_Post {
    int id_field;
    String image_field, name_field, desc_field;

    List<Model_Cate_Post> Model_Cate_Post;

    public Model_Cate_Post() {
    }

    public Model_Cate_Post(int id_field, String image_field, String name_field, String desc_field) {
        this.id_field = id_field;
        this.image_field = image_field;
        this.name_field = name_field;
        this.desc_field = desc_field;
    }

    public int getId_field() {
        return id_field;
    }

    public void setId_field(int id_field) {
        this.id_field = id_field;
    }

    public String getImage_field() {
        return image_field;
    }

    public void setImage_field(String image_field) {
        this.image_field = image_field;
    }

    public String getName_field() {
        return name_field;
    }

    public void setName_field(String name_field) {
        this.name_field = name_field;
    }

    public String getDesc_field() {
        return desc_field;
    }

    public void setDesc_field(String desc_field) {
        this.desc_field = desc_field;
    }

    public List<com.example.app_project.Model.Model_Cate_Post> getModel_Cate_Post() {
        return Model_Cate_Post;
    }

    public void setModel_Cate_Post(List<com.example.app_project.Model.Model_Cate_Post> model_Cate_Post) {
        Model_Cate_Post = model_Cate_Post;
    }
}
