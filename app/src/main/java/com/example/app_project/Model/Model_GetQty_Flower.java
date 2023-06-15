package com.example.app_project.Model;

import java.util.List;

public class Model_GetQty_Flower {
    int  Qty_Follow, Qty_Follower, Qty_Save;
    List<Model_GetQty_Flower> Model_GetQty_Flower;

    public Model_GetQty_Flower(int qty_Follow, int qty_Follower, int qty_Save) {
        Qty_Follow = qty_Follow;
        Qty_Follower = qty_Follower;
        Qty_Save = qty_Save;
    }

    public int getQty_Follow() {
        return Qty_Follow;
    }

    public void setQty_Follow(int qty_Follow) {
        Qty_Follow = qty_Follow;
    }

    public int getQty_Follower() {
        return Qty_Follower;
    }

    public void setQty_Follower(int qty_Follower) {
        Qty_Follower = qty_Follower;
    }

    public int getQty_Save() {
        return Qty_Save;
    }

    public void setQty_Save(int qty_Save) {
        Qty_Save = qty_Save;
    }

    public List<com.example.app_project.Model.Model_GetQty_Flower> getModel_GetQty_Flower() {
        return Model_GetQty_Flower;
    }

    public void setModel_GetQty_Flower(List<com.example.app_project.Model.Model_GetQty_Flower> model_GetQty_Flower) {
        Model_GetQty_Flower = model_GetQty_Flower;
    }
}
