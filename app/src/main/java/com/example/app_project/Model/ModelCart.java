package com.example.app_project.Model;

import java.util.List;

public class ModelCart {
    int id_cart, id_store, id_product, type_product,
            id_type_product, id_size_product;
    String qty_product,check_item,Name_item, image_item, price_item, qty_kho_item, type_item;
    List<ModelCart> ModelCart;

    public ModelCart(int id_cart, int id_store, int id_product, int type_product, int id_type_product, int id_size_product, String qty_product, String check_item, String name_item, String image_item, String price_item, String qty_kho_item, String type_item) {
        this.id_cart = id_cart;
        this.id_store = id_store;
        this.id_product = id_product;
        this.type_product = type_product;
        this.id_type_product = id_type_product;
        this.id_size_product = id_size_product;
        this.qty_product = qty_product;
        this.check_item = check_item;
        Name_item = name_item;
        this.image_item = image_item;
        this.price_item = price_item;
        this.qty_kho_item = qty_kho_item;
        this.type_item = type_item;
    }

    public int getId_cart() {
        return id_cart;
    }

    public void setId_cart(int id_cart) {
        this.id_cart = id_cart;
    }

    public int getId_store() {
        return id_store;
    }

    public void setId_store(int id_store) {
        this.id_store = id_store;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getType_product() {
        return type_product;
    }

    public void setType_product(int type_product) {
        this.type_product = type_product;
    }

    public int getId_type_product() {
        return id_type_product;
    }

    public void setId_type_product(int id_type_product) {
        this.id_type_product = id_type_product;
    }

    public int getId_size_product() {
        return id_size_product;
    }

    public void setId_size_product(int id_size_product) {
        this.id_size_product = id_size_product;
    }

    public String getQty_product() {
        return qty_product;
    }

    public void setQty_product(String qty_product) {
        this.qty_product = qty_product;
    }

    public String getCheck_item() {
        return check_item;
    }

    public void setCheck_item(String check_item) {
        this.check_item = check_item;
    }

    public String getName_item() {
        return Name_item;
    }

    public void setName_item(String name_item) {
        Name_item = name_item;
    }

    public String getImage_item() {
        return image_item;
    }

    public void setImage_item(String image_item) {
        this.image_item = image_item;
    }

    public String getPrice_item() {
        return price_item;
    }

    public void setPrice_item(String price_item) {
        this.price_item = price_item;
    }

    public String getQty_kho_item() {
        return qty_kho_item;
    }

    public void setQty_kho_item(String qty_kho_item) {
        this.qty_kho_item = qty_kho_item;
    }

    public String getType_item() {
        return type_item;
    }

    public void setType_item(String type_item) {
        this.type_item = type_item;
    }

    public List<com.example.app_project.Model.ModelCart> getModelCart() {
        return ModelCart;
    }

    public void setModelCart(List<com.example.app_project.Model.ModelCart> modelCart) {
        ModelCart = modelCart;
    }

    public ModelCart() {
    }
}
