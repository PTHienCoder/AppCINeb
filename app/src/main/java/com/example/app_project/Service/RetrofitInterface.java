package com.example.app_project.Service;


import com.example.app_project.Model.ModelCart;
import com.example.app_project.Model.ModelCateProduct;
import com.example.app_project.Model.ModelPost;
import com.example.app_project.Model.ModelProduct;
import com.example.app_project.Model.ModelReView_Pro;
import com.example.app_project.Model.ModelSize_Pro;
import com.example.app_project.Model.ModelType_Pro;
import com.example.app_project.Model.ModelUser;
import com.example.app_project.Model.Model_Cate_Post;
import com.example.app_project.Model.Model_Comment;
import com.example.app_project.Model.Model_GetQty_Flower;
import com.example.app_project.Model.Model_Load_PriceChoose;
import com.example.app_project.Model.Model_Load_Qty_Cart;
import com.example.app_project.Model.Model_My_Order;
import com.example.app_project.Model.Model_My_Order_Product;
import com.example.app_project.Model.Model_My_Order_Store;
import com.example.app_project.Model.Model_Notification;
import com.example.app_project.Model.Model_Qty_Pro_Post;
import com.example.app_project.Model.Model_Qty_rv;
import com.example.app_project.Model.Model_Shipping;
import com.example.app_project.Model.Model_Store;
import com.example.app_project.Model.Model_TotalPriceCart;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @POST("PostLoginUser")
    Call<ModelUser> LoginUser(@Query("email") String email, @Query("password") String password);

    @POST("PostSignupUser")
    Call<Void> PostSignupUser(@Query("emails") String emails,
                              @Query("usernames") String usernames,
                              @Query("password") String password);

    @GET("LoadPost")
    Call<ModelPost> LoadPost();


    @GET("GetIforUser")
    Call<ModelUser> GetIforUser(@Query("id_user") int id_user);

    @GET("Get_Qty_Follower")
    Call<Model_GetQty_Flower> Get_Qty_Follower(@Query("id_user") int id_user);

    @GET("LoadCategory")
    Call<ModelCateProduct> LoadCategory();

    @GET("LoadProduct_Sale")
    Call<ModelProduct> LoadProduct_Sale();

    @GET("LoadProduct_All")
    Call<ModelProduct> LoadProduct_All();

    @GET("LoadDetailProduct")
    Call<ModelProduct> LoadDetailProduct(@Query("id_product") String id_product);

    @GET("LoadIforStore")
    Call<Model_Store> LoadIforStore(@Query("id_product") String id_product);

    @GET("LoadRview_Product")
    Call<ModelReView_Pro> LoadRview_Product(@Query("id_product") int id_product, @Query("qtyrv") int qtyrv);

    @GET("LoadTypeProduct")
    Call<ModelType_Pro> LoadTypeProduct(@Query("id_product") String id_product);

    @GET("LoadSizeProduct")
    Call<ModelSize_Pro> LoadSizeProduct(@Query("id_product") String id_product, @Query("id_type") int id_type);

    @GET("getDetails_Affter_choose")
    Call<Model_Load_PriceChoose> getDetails_Affter_choose(@Query("id_type") int id_type,
                                                          @Query("id_size") int id_size);

    @POST("AddProductCart")
    Call<Void> AddProductCart(@Query("id_user") int id_user,
                              @Query("id_product") String id_product,
                              @Query("qty_pro") String qty_pro,
                              @Query("id_type_product") int id_type_product,
                              @Query("id_size_product") int id_size_product);

    @GET("Get_Qty_Cart")
    Call<Model_Load_Qty_Cart> Get_Qty_Cart(@Query("id_user") int id_user);
//
//
//

    @GET("LoadProductCart")
    Call<ModelCart> LoadProductCart(@Query("id_user") int id_user);

    @POST("Update_checkItemCart")
    Call<Void> Update_checkItemCart(@Query("id_cart") int id_cart, @Query("checkedd") int checkedd);


    @POST("Update_QtyItemCart")
    Call<Void> Update_QtyItemCart(@Query("id_cart") int id_cart, @Query("qty_item") int qty_item);

    @POST("Clear_IteamCart")
    Call<Void> Clear_IteamCart(@Query("id_cart") int id_cart);

    @GET("LoadPriceCheckout")
    Call<Model_TotalPriceCart> Get_Total_Cart(@Query("id_user") int id_user);


    @GET("GetInfo_Ship")
    Call<Model_Shipping> GetInfo_Ship(@Query("id_user") int id_user);

    @GET("LoadItem_CheckOut")
    Call<ModelCart> LoadItem_CheckOut(@Query("id_user") int id_user);


    @POST("PostCommentProduct")
    Call<Void> PostCommentProduct(@Query("id_user") int id_user,
                                  @Query("id_product") String id_product,
                                  @Query("sosao") String sosao,
                                  @Query("content") String content);



    @GET("Load_Qty_Review")
    Call<Model_Qty_rv> Load_Qty_Review(@Query("id_product") int id_product);


    @POST("comfirm_checkout_CartItem")
    Call<Void> ChekoutItemCart(@Query("id_user") int id_user,
                               @Query("id_ship_cf") int id_ship_cf,
                               @Query("method_pay") String method_pay,
                               @Query("totalx") Double totalx,
                               @Query("pt_dis") Double pt_dis);


    @POST("comfirm_checkout_BuyItem")
    Call<Void> CheckoutBuyItem(@Query("id_user") int id_user,
                               @Query("id_ship_cf") int id_ship_cf,
                               @Query("method_pay") String method_pay,
                               @Query("id_product") String id_product,
                               @Query("totalx") Double totalx,
                               @Query("pt_dis") Double pt_dis,
                               @Query("type_pro") String type_pro,
                               @Query("id_type") String id_type,
                               @Query("id_size") String id_size,
                               @Query("name_type") String name_type,
                               @Query("name_size") String name_size,
                               @Query("Qty_item") String Qty_item,
                               @Query("Price_item") String Price_item);


    @GET("Load_Cate_Post")
    Call<Model_Cate_Post> Load_Cate_Post();

    @GET("Load_ItemPost_cate")
    Call<ModelPost> Load_ItemPost_cate(@Query("id") int id);

    @GET("getnotification")
    Call<Model_Notification> getnotification(@Query("iduser") int iduser);
    @GET("Clear_notification")
    Call<Void> Clear_notification(@Query("idnoti") String idnoti);



    @GET("getCmt")
    Call<Model_Comment> getCmt(@Query("id_post") String id_post);

    @POST("postComment")
    Call<Void> postComment(@Query("content") String content,
                           @Query("id_post") String id_post,
                           @Query("id_of_user") int id_of_user);



    @GET("getiduserfollow")
    Call<Void> getiduser_follow(@Query("id_my_user") int id_my_user,
                                @Query("id_of_user") int id_of_user);

    @POST("deletefollow")
    Call<Void> deletefollow(@Query("id_my_user") int id_my_user,
                            @Query("id_of_user") int id_of_user);

    @POST("savefollow")
    Call<Void> savefollow(@Query("id_my_user") int id_my_user,
                          @Query("id_of_user") int id_of_user);



    @GET("getidusersavepost")
    Call<Void> getiduser_savepost(@Query("id_post") int id_post,
                                  @Query("id_user") int id_user);
    @POST("deletesave")
    Call<Void> deletesave(@Query("id_post") int id_post,
                          @Query("id_user") int id_user);

    @POST("savepost")
    Call<Void> savepost(@Query("id_post") int id_post,
                        @Query("id_user") int id_user);



    @GET("getMy_post")
    Call<ModelPost> getMy_post(@Query("id_user") int id_user);


    @GET("getsave_post")
    Call<ModelPost> getsave_post(@Query("id_user") int id_user);


    @GET("get_product_post")
    Call<ModelProduct> get_product_post(@Query("id_post") String id_post);



    @GET("Get_My_Order")
    Call<Model_My_Order> Get_My_Order(@Query("id_user") int id_user);

    @GET("Get_My_Store_Order")
    Call<Model_My_Order_Store> Get_My_Store_Order(@Query("id_my_order") int id_my_order);

    @GET("Get_My_Order_Product")
    Call<Model_My_Order_Product> Get_My_Order_Product(@Query("id_store") int id_store,
                                                      @Query("id_order_user") int id_order_user);


    @GET("Load_Shipping")
    Call<Model_Shipping> Load_Shipping(@Query("id_user") int id_user);
    @GET("Clear_Shipping")
    Call<Void> Clear_Shipping(@Query("id_ship") int id_ship);
    @POST("Update_Check_Shipping")
    Call<Void> Update_Check_Shipping(@Query("id_ship") int id_ship, @Query("check") int check);


    @POST("Search_Post")
    Call<ModelPost> Search_Post(@Query("key_search") String key_search);

    @POST("Search_Product")
    Call<ModelProduct> Search_Product(@Query("key_search") String key_search);

    @POST("Get_Product_category")
    Call<ModelProduct> Get_Product_category(@Query("id") String id);


}
