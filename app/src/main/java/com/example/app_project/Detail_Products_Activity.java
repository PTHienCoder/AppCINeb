package com.example.app_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_project.Adapter.Adapter_Size_Product;
import com.example.app_project.Adapter.Adapter_Type_Product;
import com.example.app_project.Adapter.Review_Product_Adapter;
import com.example.app_project.Adapter.TopPost_Adapter;
import com.example.app_project.Model.ModelPost;
import com.example.app_project.Model.ModelProduct;
import com.example.app_project.Model.ModelReView_Pro;
import com.example.app_project.Model.ModelSize_Pro;
import com.example.app_project.Model.ModelType_Pro;
import com.example.app_project.Model.Model_Load_PriceChoose;
import com.example.app_project.Model.Model_Load_Qty_Cart;
import com.example.app_project.Model.Model_Qty_rv;
import com.example.app_project.Model.Model_Store;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;
import com.example.app_project.interface_app.ItemClicklistener_Size;
import com.example.app_project.interface_app.ItemClicklistener_Type;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_Products_Activity extends AppCompatActivity {
    public String id_product, type_product,name_product,Image_product, price_pro, price_pro1, image_producsa, qty_kho, qty_kho_choose, title_type;
    private int id_tore;
    private TextView name_pro, tv_price_pro, tv_soSao,tv_daban,tv_nameshop, tv_address_shop,
            tv_xemShop, tv_xemchitiet, tv_xemmota,tv_xemRview;

    private ImageView image_pro, image_shop;

    private WebView Webview_Mota;
    private Button btn_add_toCart, btn_buy;
    private LinearLayout chat_shop;

    private String html_deatails_product;

    View view1, view2;
    NestedScrollView bottom2;
    LinearLayout bottom1;

    ItemClicklistener_Type ItemClicklistener_type;
    ItemClicklistener_Size ItemClicklistener_size;

    /////////////////// khai bao cho bottom add to cart
    private TextView price_product, nametype_chosse, qtykho, name_type, name_size,tv_qty;
    private ImageView image_pro_bt;
    private LinearLayout LN_type_product, LN_size_product;
    private RecyclerView rcv_type, rcv_size;
    private ImageButton btn_up, btn_dow;
    String nametype_choose = "", namsize_choose, price_pro_chooose;
    int id_type = 0, id_size = 0;
    ////////// rcv type and size product ////////////
    RecyclerView recyclerView_type;
    Adapter_Type_Product adapter_type_product ;
    List<ModelType_Pro> modelType_pros;

    RecyclerView recyclerView_Size;
    Adapter_Size_Product adapter_size_product ;
    List<ModelSize_Pro> modelSize_pros;


    ////////// rcv rview product ////////////

    RecyclerView recyclerView_Rview;
    Review_Product_Adapter review_product_adapter ;
    List<ModelReView_Pro> modelReView_pros;

    RetrofitInterface dataService = APIService.getService();

    @Override
    protected void onResume() {
        super.onResume();
        LoadQty_Cart();
        LoadReview_product(Integer.parseInt(id_product));
        Load_QtyReview_product(Integer.parseInt(id_product));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_products);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        Intent intent = getIntent();
        id_product  = ""+intent.getStringExtra("id_product");
        type_product = ""+intent.getStringExtra("type_product");
        name_product = ""+intent.getStringExtra("name_product");
        Image_product = ""+intent.getStringExtra("Image_product");


        name_pro = findViewById(R.id.name_pro);
        tv_price_pro = findViewById(R.id.tv_price_pro);
        tv_soSao = findViewById(R.id.tv_soSao);
        tv_daban = findViewById(R.id.tv_daban);

        tv_nameshop = findViewById(R.id.tv_nameshop);
        tv_address_shop = findViewById(R.id.tv_address_shop);
        tv_xemShop = findViewById(R.id.tv_xemShop);
        tv_xemchitiet = findViewById(R.id.tv_xemchitiet);
        tv_xemmota = findViewById(R.id.tv_xemmota);
        tv_xemRview = findViewById(R.id.tv_xemRview);

        image_pro = findViewById(R.id.image_pro);
        image_shop = findViewById(R.id.image_shop);

        btn_add_toCart = findViewById(R.id.btn_add_toCart);
        btn_buy = findViewById(R.id.btn_buy);

        Webview_Mota = findViewById(R.id.Webview_Mota);

        bottom1 = findViewById(R.id.bottom_sheet_details);
        bottom2 = findViewById(R.id.bottom_sheet_buycart);


        try {
            Picasso.get().load(APIService.IPIMAGE_PRODUCT + Image_product).placeholder(R.drawable.ic_img).into(image_pro);
        } catch (Exception e) {

        }


        ImageButton backc = findViewById(R.id.backc);
        backc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ImageButton btn_cart = findViewById(R.id.btn_cart);
        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detail_Products_Activity.this, Cart_Activity.class);
                startActivity(intent);
            }
        });



        getDetails_product(id_product);
        get_infor_store(id_product);
        LoadReview_product(Integer.parseInt(id_product));
        Load_QtyReview_product(Integer.parseInt(id_product));

        /////////////// click xem chi tiet///////////////

        tv_xemchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Detail_Products_Activity.this, R.style.BottomsheetTheme);
                view1 = LayoutInflater.from(Detail_Products_Activity.this).inflate(R.layout.bottom_details_product, bottom1);
                bottomSheetDialog.setContentView(view1);

                WebView WebView = view1.findViewById(R.id.WebviewChitiet);
                Button btn_xac_nhanchitet = view1.findViewById(R.id.btn_xac_nhanchitet);

                WebView.loadData(html_deatails_product, "text/html", "UTF-8");
                bottomSheetDialog.show();
                btn_xac_nhanchitet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        });
        /////////click xem shop ////////////////
        tv_xemShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detail_Products_Activity.this, Store_and_Product.class);
                intent.putExtra("id_tore", id_tore);
                startActivity(intent);
            }
        });

        /////////click xem danh gia san pham ////////////////
        tv_xemRview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detail_Products_Activity.this, Review_Product.class);
                intent.putExtra("id_product", id_product);
                startActivity(intent);
            }
        });


        /////////click xem  ////////////////
        btn_add_toCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickBuyOrAddtocart(1);
            }
        });

        /////////click xem  ////////////////
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 ClickBuyOrAddtocart(0);
            }
        });

        LoadQty_Cart();
    }

    private void ClickBuyOrAddtocart(int i) {
        id_type=0;
        id_size=0;
        getDetails_product(id_product);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Detail_Products_Activity.this, R.style.BottomsheetTheme);
        view2 = LayoutInflater.from(Detail_Products_Activity.this).inflate(R.layout.bottom_add_buy_product, bottom2);
        bottomSheetDialog.setContentView(view2);



         image_pro_bt = view2.findViewById(R.id.image_pro);
         price_product = view2.findViewById(R.id.price_product);
         nametype_chosse = view2.findViewById(R.id.nametype_chosse);
         qtykho = view2.findViewById(R.id.qtykho);

         LN_type_product = view2.findViewById(R.id.type_product);
         name_type = view2.findViewById(R.id.name_type);
         rcv_type = view2.findViewById(R.id.rcv_type);

         LN_size_product = view2.findViewById(R.id.size_product);
         name_size = view2.findViewById(R.id.name_size);
         rcv_size = view2.findViewById(R.id.rcv_size);

         btn_up = view2.findViewById(R.id.btn_up);
         tv_qty = view2.findViewById(R.id.tv_qty);
         btn_dow = view2.findViewById(R.id.btn_dow);
         tv_qty.setText("1");
          btn_dow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     int abc = Integer.parseInt(tv_qty.getText().toString()) - 1;
                    if(Integer.parseInt(tv_qty.getText().toString()) > 1){
                     tv_qty.setText(String.valueOf(abc));
                    }

                }
            });
            btn_up.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(Integer.parseInt(tv_qty.getText().toString()) < Integer.parseInt(qty_kho_choose)){
                        int abc = Integer.parseInt(tv_qty.getText().toString()) + 1;
                        tv_qty.setText(String.valueOf(abc));
                    }else{
                        Toast.makeText(Detail_Products_Activity.this, "out of stock" , Toast.LENGTH_SHORT).show();
                    }
                }
            });

         ////////////rcv //////////////
        nametype_chosse.setVisibility(View.GONE);
        Button btn_add_toCart = view2.findViewById(R.id.btn_add_toCart);

        btn_add_toCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(qty_kho_choose) == 0){
                    Toast.makeText(Detail_Products_Activity.this, "out of stock " , Toast.LENGTH_SHORT).show();
                }else{
                    if(Integer.parseInt(type_product) == 0){

                            if(i == 1){
                                saveadd_tocart(tv_qty.getText().toString(), id_type, id_size);
                                bottomSheetDialog.dismiss();
                            }else if(i==0){
                                Toast.makeText(Detail_Products_Activity.this, " Buy succes " , Toast.LENGTH_SHORT).show();
                                bottomSheetDialog.dismiss();
                                BuyProduct();

                            }

                    }else if(Integer.parseInt(type_product) == 1){
                        if( id_type == 0){
                            Toast.makeText(Detail_Products_Activity.this, "Please choose type product" +id_type , Toast.LENGTH_SHORT).show();
                        }else{
                                if(i == 1){
                                    saveadd_tocart(tv_qty.getText().toString(), id_type, id_size);
                                    bottomSheetDialog.dismiss();
                                }else if(i==0){
                                    Toast.makeText(Detail_Products_Activity.this, " Buy succes " , Toast.LENGTH_SHORT).show();
                                    bottomSheetDialog.dismiss();
                                    BuyProduct();

                                }

                        }
                    }else if(Integer.parseInt(type_product) == 2){

                        if( id_type == 0 || id_size == 0){
                            Toast.makeText(Detail_Products_Activity.this, "Please choose type and size product" , Toast.LENGTH_SHORT).show();
                        }else{
                                if(i == 1){
                                    saveadd_tocart(tv_qty.getText().toString(), id_type, id_size);
                                    bottomSheetDialog.dismiss();
                                }else if(i==0){
                                    Toast.makeText(Detail_Products_Activity.this, " Buy success " , Toast.LENGTH_SHORT).show();
                                    bottomSheetDialog.dismiss();
                                    BuyProduct();

                                }
                        }
                    }
                }

            }
        });

        ////////////////// type////////////////////////
        GridLayoutManager layoutManager1 = new GridLayoutManager(view2.getContext(), 4);
        recyclerView_type = view2.findViewById(R.id.rcv_type);
        recyclerView_type.setLayoutManager(layoutManager1);


        ////////////////// size ////////////////////////
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(view2.getContext());
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView_Size = view2.findViewById(R.id.rcv_size);
        recyclerView_Size.setLayoutManager(layoutManager2);

        /////////////////// /////////////////////////

        if(Integer.parseInt(type_product) == 0){
            LN_type_product.setVisibility(View.GONE);
            LN_size_product.setVisibility(View.GONE);
            name_type.setText("");
        }else if(Integer.parseInt(String.valueOf(type_product)) == 1){
            LN_type_product.setVisibility(View.VISIBLE);
            LN_size_product.setVisibility(View.GONE);
            load_type_product();
        }else if(Integer.parseInt(type_product) == 2){
            LN_type_product.setVisibility(View.VISIBLE);
            LN_size_product.setVisibility(View.VISIBLE);
            load_size_product(0);
            load_type_product();
        }

        getDetails_product_bottoms();
        bottomSheetDialog.show();

    }
    public void BuyProduct(){
        Intent intent = new Intent(Detail_Products_Activity.this, Checkout_Activity.class);
        intent.putExtra("checkout_type", "buy");
        intent.putExtra("id_product", id_product);
        intent.putExtra("type_pro", type_product);
        intent.putExtra("name_pro", name_product);
        intent.putExtra("image_pro", Image_product);
        intent.putExtra("id_type", String.valueOf(id_type));
        intent.putExtra("id_size", String.valueOf(id_size));
        intent.putExtra("name_type", nametype_choose);
        intent.putExtra("name_size", namsize_choose);
        intent.putExtra("qty_item", tv_qty.getText());
        if(Integer.parseInt(type_product) == 0){
         intent.putExtra("price_item", price_pro);
        }else{
        intent.putExtra("price_item", price_pro_chooose);
        }

        startActivity(intent);
    }

    public void saveadd_tocart(String qty, int id_type, int id_size){
        Call<Void> callback = dataService.AddProductCart(AppLayout.id_user,id_product, qty, id_type, id_size);
        callback.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    LoadQty_Cart();
                    Toast.makeText(Detail_Products_Activity.this, "Add to Cart Success", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Toast.makeText(Detail_Products_Activity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void LoadQty_Cart(){
        Call<Model_Load_Qty_Cart> callback = dataService.Get_Qty_Cart(AppLayout.id_user);
        callback.enqueue(new Callback<Model_Load_Qty_Cart>() {
            @Override
            public void onResponse(Call<Model_Load_Qty_Cart> call, Response<Model_Load_Qty_Cart> response) {
                if (response.isSuccessful()){
                    for(Model_Load_Qty_Cart item:response.body().getModel_Load_Qty_Cart()){
                      TextView tv_Qty_cart = findViewById(R.id.tv_Qty_cart);
                        tv_Qty_cart.setText(item.getQty_cart());
                    }
                }
            }

            @Override
            public void onFailure(Call<Model_Load_Qty_Cart> call, Throwable throwable) {
                Toast.makeText(Detail_Products_Activity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void load_type_product(){
        Call<ModelType_Pro> callback = dataService.LoadTypeProduct(id_product);
        callback.enqueue(new Callback<ModelType_Pro>() {
            @Override
            public void onResponse(Call<ModelType_Pro> call, Response<ModelType_Pro> response) {
                if (response.isSuccessful()){
                    List<ModelType_Pro> modelType = response.body().getModelType_Pro();
                    adapter_type_product = new Adapter_Type_Product(modelType, view2.getContext(), ItemClicklistener_type);
                    recyclerView_type.setAdapter(adapter_type_product);
                    adapter_type_product.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<ModelType_Pro> call, Throwable throwable) {
                Toast.makeText(Detail_Products_Activity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });


        ItemClicklistener_type = new ItemClicklistener_Type() {
            @Override
            public void Onclick_Type(int id,String name) {
                recyclerView_type.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter_type_product.notifyDataSetChanged();
                    }
                });
                if(Integer.parseInt(type_product) == 1){
                 getDetails_Affter_choose(id_product, id, 0);
                }else if(Integer.parseInt(type_product) == 2){
                 getDetails_product_bottoms();
                }
                id_type = id;
                load_size_product(id);

                nametype_chosse.setVisibility(View.VISIBLE);
                nametype_chosse.setText(name);
                nametype_choose = name;
//                Toast.makeText(view2.getContext(), "id_type" + id, Toast.LENGTH_SHORT).show();
            }

        };

    }
    public void load_size_product(int id_type){
        Call<ModelSize_Pro> callback = dataService.LoadSizeProduct(id_product, id_type);
        callback.enqueue(new Callback<ModelSize_Pro>() {
            @Override
            public void onResponse(Call<ModelSize_Pro> call, Response<ModelSize_Pro> response) {
                if (response.isSuccessful()){
                    List<ModelSize_Pro> modelSize = response.body().getModelSize_Pro();
                    adapter_size_product = new Adapter_Size_Product(modelSize, view2.getContext(), ItemClicklistener_size);
                    recyclerView_Size.setAdapter(adapter_size_product);
                    adapter_size_product.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ModelSize_Pro> call, Throwable throwable) {
                Toast.makeText(Detail_Products_Activity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });


            ItemClicklistener_size = new ItemClicklistener_Size() {
                @Override
                public void Onclick_Size(int id ,String name) {
                    if(id_type == 0){
                        Toast.makeText(view2.getContext(), "Please Choose Type Product", Toast.LENGTH_SHORT).show();
                    }else{

                    recyclerView_Size.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter_size_product.notifyDataSetChanged();
                        }
                    });
                    id_size =id;
                    getDetails_Affter_choose(id_product, id_type, id);
//                    Toast.makeText(view2.getContext(), "id_size" + id, Toast.LENGTH_SHORT).show();
                        nametype_chosse.setVisibility(View.VISIBLE);
                        nametype_chosse.setText(nametype_choose+","+ name);
                        namsize_choose = name;

                 }
                }
            };

    }

    private void getDetails_Affter_choose(String id_product, int id_type , int id_size) {
        RetrofitInterface dataService = APIService.getService();
        Call<Model_Load_PriceChoose> callback = dataService.getDetails_Affter_choose(id_type, id_size);
        callback.enqueue(new Callback<Model_Load_PriceChoose>() {
            @Override
            public void onResponse(Call<Model_Load_PriceChoose> call, Response<Model_Load_PriceChoose> response) {
                if (response.isSuccessful()){
                     for(Model_Load_PriceChoose item:response.body().getModel_Load_PriceChoose()){

                         price_product.setText(item.getPice_pro() +APIService.Donvi);
                         price_pro_chooose = item.getPice_pro();
                         qtykho.setText("Kho: "+item.getQty_kho());
                         qty_kho_choose = item.getQty_kho();
                         tv_qty.setText("1");

                    }
                }

            }

            @Override
            public void onFailure(Call<Model_Load_PriceChoose> call, Throwable throwable) {
                Toast.makeText(Detail_Products_Activity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDetails_product_bottoms() {

        try {
            Picasso.get().load(APIService.IPIMAGE_PRODUCT + Image_product).placeholder(R.drawable.ic_img).into(image_pro_bt);
        } catch (Exception e) {

        }

        if(Integer.parseInt(type_product) == 0){
            price_product.setText(price_pro+APIService.Donvi);
        }else if(Integer.parseInt(type_product) == 1){
            name_type.setText(title_type);
            price_product.setText(price_pro +APIService.Donvi +"-"+ price_pro1 +APIService.Donvi);
        }else if(Integer.parseInt(type_product) == 2){
            price_product.setText(price_pro +APIService.Donvi +"-"+ price_pro1 +APIService.Donvi);
            name_type.setText(title_type);
        }
        qtykho.setText("Kho: "+qty_kho);

    }

    
    private void getDetails_product(String id_product) {
        RetrofitInterface dataService = APIService.getService();
        Call<ModelProduct> callback = dataService.LoadDetailProduct(id_product);
        callback.enqueue(new Callback<ModelProduct>() {
            @Override
            public void onResponse(Call<ModelProduct> call, Response<ModelProduct> response) {
                if (response.isSuccessful()){
//                    List<ModelProduct> modelProducts = response.body().getModelProduct();
                    for(ModelProduct item:response.body().getModelProduct()){
                        name_pro.setText(item.getName_product());

                        price_pro = item.getPrice_product();
                        price_pro1 = item.getPrice_pro2();
                        image_producsa = item.getImage_product();
                        title_type= item.getTitle_type();


                        if(Integer.parseInt(type_product) == 0){

                            tv_price_pro.setText(price_pro+APIService.Donvi);
                            qty_kho = item.getQty_product();
                        }else if(Integer.parseInt(type_product) == 1){

                            tv_price_pro.setText(price_pro +APIService.Donvi +"-"+ price_pro1 +APIService.Donvi);
                            qty_kho = item.getQty_product();
                        }else if(Integer.parseInt(type_product) == 2){
                            tv_price_pro.setText(price_pro +APIService.Donvi +"-"+ price_pro1 +APIService.Donvi);
                            qty_kho = item.getQty_product();
                        }
                        qty_kho_choose = item.getQty_product();
                        tv_daban.setText("Đã bán: "+item.getQty_sale());

                        String html = "<html><body>"+item.getDesc_product()+"</body></html>";
                        Webview_Mota.loadData(html, "text/html", "UTF-8");

                        html_deatails_product = "<html><body>"+item.getDetails_product()+"</body></html>";

                    }

                }
                else {
                    Toast.makeText(Detail_Products_Activity.this, "getDetails_product Faill" , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelProduct> call, Throwable throwable) {
                Toast.makeText(Detail_Products_Activity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void get_infor_store(String id_product) {
        RetrofitInterface dataService = APIService.getService();
        Call<Model_Store> callback = dataService.LoadIforStore(id_product);
        callback.enqueue(new Callback<Model_Store>() {
            @Override
            public void onResponse(Call<Model_Store> call, Response<Model_Store> response) {
             if (response.isSuccessful()) {
//                    List<Model_Store> modelProducts = response.body().getModelProduct();
                    for(Model_Store item:response.body().getModel_Store()){
                        tv_nameshop.setText(item.getName_store());
                        id_tore = item.getId_store();
                        try {
                            Picasso.get().load(APIService.IMAGE_STORE + item.getAvt_store()).placeholder(R.drawable.ic_img).into(image_shop);
                        } catch (Exception e) {

                        }

                        tv_address_shop.setText("Địa chỉ: "+item.getAddress_store());

                    }

                }
                else {
                 Toast.makeText(Detail_Products_Activity.this, "get_infor_store Faill" , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model_Store> call, Throwable throwable) {
                Toast.makeText(Detail_Products_Activity.this, "failll" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void LoadReview_product(int id_product){


        ////////////////// rview ////////////////////////
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(Detail_Products_Activity.this);
        layoutManager3.setOrientation(RecyclerView.VERTICAL);
        recyclerView_Rview = findViewById(R.id.rcv_review_pro);
        recyclerView_Rview.setLayoutManager(layoutManager3);

        RetrofitInterface dataService = APIService.getService();
        Call<ModelReView_Pro> callback = dataService.LoadRview_Product(id_product, 1);
        callback.enqueue(new Callback<ModelReView_Pro>() {
            @Override
            public void onResponse(Call<ModelReView_Pro> call, Response<ModelReView_Pro> response) {
                if (response.isSuccessful()){
                    List<ModelReView_Pro> modelSize = response.body().getModelReView_Pro();
                    review_product_adapter = new Review_Product_Adapter(modelSize, Detail_Products_Activity.this);
                    recyclerView_Rview.setAdapter(review_product_adapter);
                    review_product_adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<ModelReView_Pro> call, Throwable throwable) {
                Toast.makeText(Detail_Products_Activity.this, "failll" + throwable, Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void Load_QtyReview_product(int id_product){
        RetrofitInterface dataService = APIService.getService();
        Call<Model_Qty_rv> callback = dataService.Load_Qty_Review(id_product);
        callback.enqueue(new Callback<Model_Qty_rv>() {
            @Override
            public void onResponse(Call<Model_Qty_rv> call, Response<Model_Qty_rv> response) {
                if (response.isSuccessful()){
                    for(Model_Qty_rv item:response.body().getModel_Qty_rv()){
                        tv_soSao.setText(item.getAvg_rv() + "/5");
                    }
                }

            }

            @Override
            public void onFailure(Call<Model_Qty_rv> call, Throwable throwable) {
                Toast.makeText(Detail_Products_Activity.this, "failll" + throwable, Toast.LENGTH_SHORT).show();
            }
        });

    }


}