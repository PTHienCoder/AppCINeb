package com.example.app_project.Model;

import java.util.List;

public class Model_Qty_rv {
    String avg_rv, qty_rv, nam_sao, bon_sao, ba_sao, hai_sao,mot_sao;
    List<Model_Qty_rv> Model_Qty_rv;

    public Model_Qty_rv(String avg_rv, String qty_rv, String nam_sao, String bon_sao, String ba_sao, String hai_sao, String mot_sao) {
        this.avg_rv = avg_rv;
        this.qty_rv = qty_rv;
        this.nam_sao = nam_sao;
        this.bon_sao = bon_sao;
        this.ba_sao = ba_sao;
        this.hai_sao = hai_sao;
        this.mot_sao = mot_sao;
    }

    public String getAvg_rv() {
        return avg_rv;
    }

    public void setAvg_rv(String avg_rv) {
        this.avg_rv = avg_rv;
    }

    public String getQty_rv() {
        return qty_rv;
    }

    public void setQty_rv(String qty_rv) {
        this.qty_rv = qty_rv;
    }

    public String getNam_sao() {
        return nam_sao;
    }

    public void setNam_sao(String nam_sao) {
        this.nam_sao = nam_sao;
    }

    public String getBon_sao() {
        return bon_sao;
    }

    public void setBon_sao(String bon_sao) {
        this.bon_sao = bon_sao;
    }

    public String getBa_sao() {
        return ba_sao;
    }

    public void setBa_sao(String ba_sao) {
        this.ba_sao = ba_sao;
    }

    public String getHai_sao() {
        return hai_sao;
    }

    public void setHai_sao(String hai_sao) {
        this.hai_sao = hai_sao;
    }

    public String getMot_sao() {
        return mot_sao;
    }

    public void setMot_sao(String mot_sao) {
        this.mot_sao = mot_sao;
    }

    public List<com.example.app_project.Model.Model_Qty_rv> getModel_Qty_rv() {
        return Model_Qty_rv;
    }

    public void setModel_Qty_rv(List<com.example.app_project.Model.Model_Qty_rv> model_Qty_rv) {
        Model_Qty_rv = model_Qty_rv;
    }

    public Model_Qty_rv() {
    }
}
