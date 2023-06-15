package com.example.app_project.Model;

import java.util.List;

public class Model_Store {
    int id_store, id_user;
    String name_store, address_store,cmnd_user,
            phone_store,avt_store,desc_store,
             Category_store,type_store,time_add;
    List<Model_Store> Model_Store;

    public Model_Store() {
    }

    public Model_Store(int id_store, int id_user, String name_store, String address_store, String cmnd_user, String phone_store, String avt_store, String desc_store, String category_store, String type_store, String time_add) {
        this.id_store = id_store;
        this.id_user = id_user;
        this.name_store = name_store;
        this.address_store = address_store;
        this.cmnd_user = cmnd_user;
        this.phone_store = phone_store;
        this.avt_store = avt_store;
        this.desc_store = desc_store;
        Category_store = category_store;
        this.type_store = type_store;
        this.time_add = time_add;
    }

    public int getId_store() {
        return id_store;
    }

    public void setId_store(int id_store) {
        this.id_store = id_store;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName_store() {
        return name_store;
    }

    public void setName_store(String name_store) {
        this.name_store = name_store;
    }

    public String getAddress_store() {
        return address_store;
    }

    public void setAddress_store(String address_store) {
        this.address_store = address_store;
    }

    public String getCmnd_user() {
        return cmnd_user;
    }

    public void setCmnd_user(String cmnd_user) {
        this.cmnd_user = cmnd_user;
    }

    public String getPhone_store() {
        return phone_store;
    }

    public void setPhone_store(String phone_store) {
        this.phone_store = phone_store;
    }

    public String getAvt_store() {
        return avt_store;
    }

    public void setAvt_store(String avt_store) {
        this.avt_store = avt_store;
    }

    public String getDesc_store() {
        return desc_store;
    }

    public void setDesc_store(String desc_store) {
        this.desc_store = desc_store;
    }

    public String getCategory_store() {
        return Category_store;
    }

    public void setCategory_store(String category_store) {
        Category_store = category_store;
    }

    public String getType_store() {
        return type_store;
    }

    public void setType_store(String type_store) {
        this.type_store = type_store;
    }

    public String getTime_add() {
        return time_add;
    }

    public void setTime_add(String time_add) {
        this.time_add = time_add;
    }

    public List<com.example.app_project.Model.Model_Store> getModel_Store() {
        return Model_Store;
    }

    public void setModel_Store(List<com.example.app_project.Model.Model_Store> model_Store) {
        Model_Store = model_Store;
    }
}
