package com.example.app_project.Model;

import java.util.List;

public class Model_My_Order_Product {
    int id_product;
    String name_product,image_product, type_pro,name_type,name_size,qty_product,price_product,
            price_items,order_code,id_order_user,id_store;
    List<Model_My_Order_Product> Model_My_Order_Product;

    public Model_My_Order_Product(int id_product, String name_product, String image_product, String type_pro, String name_type, String name_size, String qty_product, String price_product, String price_items, String order_code, String id_order_user, String id_store) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.image_product = image_product;
        this.type_pro = type_pro;
        this.name_type = name_type;
        this.name_size = name_size;
        this.qty_product = qty_product;
        this.price_product = price_product;
        this.price_items = price_items;
        this.order_code = order_code;
        this.id_order_user = id_order_user;
        this.id_store = id_store;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getImage_product() {
        return image_product;
    }

    public void setImage_product(String image_product) {
        this.image_product = image_product;
    }

    public String getType_pro() {
        return type_pro;
    }

    public void setType_pro(String type_pro) {
        this.type_pro = type_pro;
    }

    public String getName_type() {
        return name_type;
    }

    public void setName_type(String name_type) {
        this.name_type = name_type;
    }

    public String getName_size() {
        return name_size;
    }

    public void setName_size(String name_size) {
        this.name_size = name_size;
    }

    public String getQty_product() {
        return qty_product;
    }

    public void setQty_product(String qty_product) {
        this.qty_product = qty_product;
    }

    public String getPrice_product() {
        return price_product;
    }

    public void setPrice_product(String price_product) {
        this.price_product = price_product;
    }

    public String getPrice_items() {
        return price_items;
    }

    public void setPrice_items(String price_items) {
        this.price_items = price_items;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public String getId_order_user() {
        return id_order_user;
    }

    public void setId_order_user(String id_order_user) {
        this.id_order_user = id_order_user;
    }

    public String getId_store() {
        return id_store;
    }

    public void setId_store(String id_store) {
        this.id_store = id_store;
    }

    public List<com.example.app_project.Model.Model_My_Order_Product> getModel_My_Order_Product() {
        return Model_My_Order_Product;
    }

    public void setModel_My_Order_Product(List<com.example.app_project.Model.Model_My_Order_Product> model_My_Order_Product) {
        Model_My_Order_Product = model_My_Order_Product;
    }
}
