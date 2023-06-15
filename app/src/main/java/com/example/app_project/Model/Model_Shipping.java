package com.example.app_project.Model;

import java.util.List;

public class Model_Shipping {
    int id_shipping, id_user;
    String name_shipping, phone_shipping,desc_address, address_shipping,type_shipping, check_shipping;
    List<Model_Shipping> Model_Shipping;

    public Model_Shipping(int id_shipping, int id_user, String name_shipping, String phone_shipping, String desc_address, String address_shipping, String type_shipping, String check_shipping) {
        this.id_shipping = id_shipping;
        this.id_user = id_user;
        this.name_shipping = name_shipping;
        this.phone_shipping = phone_shipping;
        this.desc_address = desc_address;
        this.address_shipping = address_shipping;
        this.type_shipping = type_shipping;
        this.check_shipping = check_shipping;
    }

    public int getId_shipping() {
        return id_shipping;
    }

    public void setId_shipping(int id_shipping) {
        this.id_shipping = id_shipping;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName_shipping() {
        return name_shipping;
    }

    public void setName_shipping(String name_shipping) {
        this.name_shipping = name_shipping;
    }

    public String getPhone_shipping() {
        return phone_shipping;
    }

    public void setPhone_shipping(String phone_shipping) {
        this.phone_shipping = phone_shipping;
    }

    public String getDesc_address() {
        return desc_address;
    }

    public void setDesc_address(String desc_address) {
        this.desc_address = desc_address;
    }

    public String getAddress_shipping() {
        return address_shipping;
    }

    public void setAddress_shipping(String address_shipping) {
        this.address_shipping = address_shipping;
    }

    public String getType_shipping() {
        return type_shipping;
    }

    public void setType_shipping(String type_shipping) {
        this.type_shipping = type_shipping;
    }

    public String getCheck_shipping() {
        return check_shipping;
    }

    public void setCheck_shipping(String check_shipping) {
        this.check_shipping = check_shipping;
    }

    public List<com.example.app_project.Model.Model_Shipping> getModel_Shipping() {
        return Model_Shipping;
    }

    public void setModel_Shipping(List<com.example.app_project.Model.Model_Shipping> model_Shipping) {
        Model_Shipping = model_Shipping;
    }
}
