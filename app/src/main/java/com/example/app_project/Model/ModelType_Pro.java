package com.example.app_project.Model;

import java.util.List;

public class ModelType_Pro {
    int id_type_pro, id_product;
    String name_type_pro,image_type, qty_type_product,price_type_product;
    List<ModelType_Pro> ModelType_Pro;

    public ModelType_Pro(int id_type_pro, int id_product, String name_type_pro, String image_type, String qty_type_product, String price_type_product) {
        this.id_type_pro = id_type_pro;
        this.id_product = id_product;
        this.name_type_pro = name_type_pro;
        this.image_type = image_type;
        this.qty_type_product = qty_type_product;
        this.price_type_product = price_type_product;
    }

    public int getId_type_pro() {
        return id_type_pro;
    }

    public void setId_type_pro(int id_type_pro) {
        this.id_type_pro = id_type_pro;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName_type_pro() {
        return name_type_pro;
    }

    public void setName_type_pro(String name_type_pro) {
        this.name_type_pro = name_type_pro;
    }

    public String getImage_type() {
        return image_type;
    }

    public void setImage_type(String image_type) {
        this.image_type = image_type;
    }

    public String getQty_type_product() {
        return qty_type_product;
    }

    public void setQty_type_product(String qty_type_product) {
        this.qty_type_product = qty_type_product;
    }

    public String getPrice_type_product() {
        return price_type_product;
    }

    public void setPrice_type_product(String price_type_product) {
        this.price_type_product = price_type_product;
    }

    public List<com.example.app_project.Model.ModelType_Pro> getModelType_Pro() {
        return ModelType_Pro;
    }

    public void setModelType_Pro(List<com.example.app_project.Model.ModelType_Pro> modelType_Pro) {
        ModelType_Pro = modelType_Pro;
    }

    public ModelType_Pro() {
    }
}
