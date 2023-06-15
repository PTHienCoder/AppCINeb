package com.example.app_project.Model;

import java.util.List;

public class ModelPost {
   int id_post, id_user;
   String title_post,field_post,image_post,desc_post,
           detail_post,hastag_post,time_create, name, image_user, Qty_pro_post;
   List<ModelPost> ModelPost;

   public ModelPost(int id_post, int id_user, String title_post, String field_post, String image_post, String desc_post, String detail_post, String hastag_post, String time_create, String name, String image_user, String qty_pro_post) {
      this.id_post = id_post;
      this.id_user = id_user;
      this.title_post = title_post;
      this.field_post = field_post;
      this.image_post = image_post;
      this.desc_post = desc_post;
      this.detail_post = detail_post;
      this.hastag_post = hastag_post;
      this.time_create = time_create;
      this.name = name;
      this.image_user = image_user;
      Qty_pro_post = qty_pro_post;
   }

   public String getQty_pro_post() {
      return Qty_pro_post;
   }

   public void setQty_pro_post(String qty_pro_post) {
      Qty_pro_post = qty_pro_post;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getImage_user() {
      return image_user;
   }

   public void setImage_user(String image_user) {
      this.image_user = image_user;
   }

   public int getId_post() {
      return id_post;
   }

   public void setId_post(int id_post) {
      this.id_post = id_post;
   }

   public int getId_user() {
      return id_user;
   }

   public void setId_user(int id_user) {
      this.id_user = id_user;
   }

   public String getTitle_post() {
      return title_post;
   }

   public void setTitle_post(String title_post) {
      this.title_post = title_post;
   }

   public String getField_post() {
      return field_post;
   }

   public void setField_post(String field_post) {
      this.field_post = field_post;
   }

   public String getImage_post() {
      return image_post;
   }

   public void setImage_post(String image_post) {
      this.image_post = image_post;
   }

   public String getDesc_post() {
      return desc_post;
   }

   public void setDesc_post(String desc_post) {
      this.desc_post = desc_post;
   }

   public String getDetail_post() {
      return detail_post;
   }

   public void setDetail_post(String detail_post) {
      this.detail_post = detail_post;
   }

   public String getHastag_post() {
      return hastag_post;
   }

   public void setHastag_post(String hastag_post) {
      this.hastag_post = hastag_post;
   }

   public String getTime_create() {
      return time_create;
   }

   public void setTime_create(String time_create) {
      this.time_create = time_create;
   }

   public List<com.example.app_project.Model.ModelPost> getModelPost() {
      return ModelPost;
   }

   public void setModelPost(List<com.example.app_project.Model.ModelPost> modelPost) {
      ModelPost = modelPost;
   }

   public ModelPost() {

   }
}
