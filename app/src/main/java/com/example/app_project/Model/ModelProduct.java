package com.example.app_project.Model;

import java.util.List;

public class ModelProduct {

    int id_product, id_cate_store,id_areas,id_store;
    String name_product,qty_product, price_product,discount_product, price_pro2,type_product,title_type,
            desc_product,details_product,image_product,hastag_product
            ,date_time,status, qty_sale;

    List<ModelProduct> ModelProduct;

    public ModelProduct(int id_product, int id_cate_store, int id_areas, int id_store, String name_product, String qty_product, String price_product, String discount_product, String price_pro2, String type_product, String title_type, String desc_product, String details_product, String image_product, String hastag_product, String date_time, String status, String qty_sale) {
        this.id_product = id_product;
        this.id_cate_store = id_cate_store;
        this.id_areas = id_areas;
        this.id_store = id_store;
        this.name_product = name_product;
        this.qty_product = qty_product;
        this.price_product = price_product;
        this.discount_product = discount_product;
        this.price_pro2 = price_pro2;
        this.type_product = type_product;
        this.title_type = title_type;
        this.desc_product = desc_product;
        this.details_product = details_product;
        this.image_product = image_product;
        this.hastag_product = hastag_product;
        this.date_time = date_time;
        this.status = status;
        this.qty_sale = qty_sale;
    }

    public String getDiscount_product() {
        return discount_product;
    }

    public void setDiscount_product(String discount_product) {
        this.discount_product = discount_product;
    }

    public String getQty_sale() {
        return qty_sale;
    }

    public void setQty_sale(String qty_sale) {
        this.qty_sale = qty_sale;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getId_cate_store() {
        return id_cate_store;
    }

    public void setId_cate_store(int id_cate_store) {
        this.id_cate_store = id_cate_store;
    }

    public int getId_areas() {
        return id_areas;
    }

    public void setId_areas(int id_areas) {
        this.id_areas = id_areas;
    }

    public int getId_store() {
        return id_store;
    }

    public void setId_store(int id_store) {
        this.id_store = id_store;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
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

    public String getPrice_pro2() {
        return price_pro2;
    }

    public void setPrice_pro2(String price_pro2) {
        this.price_pro2 = price_pro2;
    }

    public void setPrice_product(String price_product) {
        this.price_product = price_product;
    }

    public String getType_product() {
        return type_product;
    }

    public void setType_product(String type_product) {
        this.type_product = type_product;
    }

    public String getTitle_type() {
        return title_type;
    }

    public void setTitle_type(String title_type) {
        this.title_type = title_type;
    }

    public String getDesc_product() {
        return desc_product;
    }

    public void setDesc_product(String desc_product) {
        this.desc_product = desc_product;
    }

    public String getDetails_product() {
        return details_product;
    }

    public void setDetails_product(String details_product) {
        this.details_product = details_product;
    }

    public String getImage_product() {
        return image_product;
    }

    public void setImage_product(String image_product) {
        this.image_product = image_product;
    }

    public String getHastag_product() {
        return hastag_product;
    }

    public void setHastag_product(String hastag_product) {
        this.hastag_product = hastag_product;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<com.example.app_project.Model.ModelProduct> getModelProduct() {
        return ModelProduct;
    }

    public void setModelProduct(List<com.example.app_project.Model.ModelProduct> modelProduct) {
        ModelProduct = modelProduct;
    }

    public ModelProduct() {
    }
}
