package com.example.app_project.Model;

import java.util.List;

public class Model_My_Order {
    int id_order_user, id_user;
    String order_code, method_payment, discount_order, total_order, time_order, Qty_pro;
     List<Model_My_Order> Model_My_Order;

    public Model_My_Order(int id_order_user, int id_user, String order_code, String method_payment, String discount_order, String total_order, String time_order, String qty_pro) {
        this.id_order_user = id_order_user;
        this.id_user = id_user;
        this.order_code = order_code;
        this.method_payment = method_payment;
        this.discount_order = discount_order;
        this.total_order = total_order;
        this.time_order = time_order;
        Qty_pro = qty_pro;
    }

    public int getId_order_user() {
        return id_order_user;
    }

    public void setId_order_user(int id_order_user) {
        this.id_order_user = id_order_user;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public String getMethod_payment() {
        return method_payment;
    }

    public void setMethod_payment(String method_payment) {
        this.method_payment = method_payment;
    }

    public String getDiscount_order() {
        return discount_order;
    }

    public void setDiscount_order(String discount_order) {
        this.discount_order = discount_order;
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

    public String getQty_pro() {
        return Qty_pro;
    }

    public void setQty_pro(String qty_pro) {
        Qty_pro = qty_pro;
    }

    public List<com.example.app_project.Model.Model_My_Order> getModel_My_Order() {
        return Model_My_Order;
    }

    public void setModel_My_Order(List<com.example.app_project.Model.Model_My_Order> model_My_Order) {
        Model_My_Order = model_My_Order;
    }
}
