package com.example.app_project.Model;

import java.util.List;

public class ModelSize_Pro {
    int id_size_pro, id_product;
    String id_type_pro, name_size,qty_size_product, price_size_product;
    List<ModelSize_Pro> ModelSize_Pro;

    public ModelSize_Pro(int id_size_pro, int id_product, String id_type_pro, String name_size, String qty_size_product, String price_size_product) {
        this.id_size_pro = id_size_pro;
        this.id_product = id_product;
        this.id_type_pro = id_type_pro;
        this.name_size = name_size;
        this.qty_size_product = qty_size_product;
        this.price_size_product = price_size_product;
    }

    public int getId_size_pro() {
        return id_size_pro;
    }

    public void setId_size_pro(int id_size_pro) {
        this.id_size_pro = id_size_pro;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getId_type_pro() {
        return id_type_pro;
    }

    public void setId_type_pro(String id_type_pro) {
        this.id_type_pro = id_type_pro;
    }

    public String getName_size() {
        return name_size;
    }

    public void setName_size(String name_size) {
        this.name_size = name_size;
    }

    public String getQty_size_product() {
        return qty_size_product;
    }

    public void setQty_size_product(String qty_size_product) {
        this.qty_size_product = qty_size_product;
    }

    public String getPrice_size_product() {
        return price_size_product;
    }

    public void setPrice_size_product(String price_size_product) {
        this.price_size_product = price_size_product;
    }

    public List<com.example.app_project.Model.ModelSize_Pro> getModelSize_Pro() {
        return ModelSize_Pro;
    }

    public void setModelSize_Pro(List<com.example.app_project.Model.ModelSize_Pro> modelSize_Pro) {
        ModelSize_Pro = modelSize_Pro;
    }

    public ModelSize_Pro() {
    }
}
