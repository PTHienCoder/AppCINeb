package com.example.app_project.Model;

import java.util.List;

public class ModelCateProduct {
    int id_areas;
    String image_areas, name_areas,desc_areas;
    List<ModelCateProduct> ModelCateProduct;

    public ModelCateProduct(int id_areas, String image_areas, String name_areas, String desc_areas) {
        this.id_areas = id_areas;
        this.image_areas = image_areas;
        this.name_areas = name_areas;
        this.desc_areas = desc_areas;
    }

    public int getId_areas() {
        return id_areas;
    }

    public void setId_areas(int id_areas) {
        this.id_areas = id_areas;
    }

    public String getImage_areas() {
        return image_areas;
    }

    public void setImage_areas(String image_areas) {
        this.image_areas = image_areas;
    }

    public String getName_areas() {
        return name_areas;
    }

    public void setName_areas(String name_areas) {
        this.name_areas = name_areas;
    }

    public String getDesc_areas() {
        return desc_areas;
    }

    public void setDesc_areas(String desc_areas) {
        this.desc_areas = desc_areas;
    }

    public List<com.example.app_project.Model.ModelCateProduct> getModelCateProduct() {
        return ModelCateProduct;
    }

    public void setModelCateProduct(List<com.example.app_project.Model.ModelCateProduct> modelCateProduct) {
        ModelCateProduct = modelCateProduct;
    }
}
