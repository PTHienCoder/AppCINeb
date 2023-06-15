package com.example.app_project.Model;

import java.util.List;

public class Model_My_Order_Store {
    int id_order_store, id_order_user, id_store;
    String order_code,total_order, time_order, order_status;
    List<Model_My_Order_Store> Model_My_Order_Store;

    public Model_My_Order_Store(int id_order_store, int id_order_user, int id_store, String order_code, String total_order, String time_order, String order_status) {
        this.id_order_store = id_order_store;
        this.id_order_user = id_order_user;
        this.id_store = id_store;
        this.order_code = order_code;
        this.total_order = total_order;
        this.time_order = time_order;
        this.order_status = order_status;
    }

    public int getId_order_store() {
        return id_order_store;
    }

    public void setId_order_store(int id_order_store) {
        this.id_order_store = id_order_store;
    }

    public int getId_order_user() {
        return id_order_user;
    }

    public void setId_order_user(int id_order_user) {
        this.id_order_user = id_order_user;
    }

    public int getId_store() {
        return id_store;
    }

    public void setId_store(int id_store) {
        this.id_store = id_store;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public String getTotal_order() {
        return total_order;
    }

    public void setTotal_order(String total_order) {
        this.total_order = total_order;
    }

    public String getTime_order() {
        return time_order;
    }

    public void setTime_order(String time_order) {
        this.time_order = time_order;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public List<com.example.app_project.Model.Model_My_Order_Store> getModel_My_Order_Store() {
        return Model_My_Order_Store;
    }

    public void setModel_My_Order_Store(List<com.example.app_project.Model.Model_My_Order_Store> model_My_Order_Store) {
        Model_My_Order_Store = model_My_Order_Store;
    }
}
