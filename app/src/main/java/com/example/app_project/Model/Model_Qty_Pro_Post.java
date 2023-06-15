package com.example.app_project.Model;

import java.util.List;

public class Model_Qty_Pro_Post {
    int Qty_pro;
    List<Model_Qty_Pro_Post> Model_Qty_Pro_Post;

    public Model_Qty_Pro_Post(int qty_pro) {
        Qty_pro = qty_pro;
    }

    public int getQty_pro() {
        return Qty_pro;
    }

    public void setQty_pro(int qty_pro) {
        Qty_pro = qty_pro;
    }

    public List<com.example.app_project.Model.Model_Qty_Pro_Post> getModel_Qty_Pro_Post() {
        return Model_Qty_Pro_Post;
    }

    public void setModel_Qty_Pro_Post(List<com.example.app_project.Model.Model_Qty_Pro_Post> model_Qty_Pro_Post) {
        Model_Qty_Pro_Post = model_Qty_Pro_Post;
    }
}
