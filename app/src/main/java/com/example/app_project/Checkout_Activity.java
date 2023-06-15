package com.example.app_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_project.Adapter.ItemCart_Adapter;
import com.example.app_project.Adapter.ItemCheckout_Adapter;
import com.example.app_project.Model.ModelCart;
import com.example.app_project.Model.Model_Load_Qty_Cart;
import com.example.app_project.Model.Model_Shipping;
import com.example.app_project.Model.Model_TotalPriceCart;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Checkout_Activity extends AppCompatActivity {
    String type_checkout, id_product, type_product, name_product, Image_product,id_type,id_size, name_type, name_size, qty_item, price_item;
    int id_ship_cf = 0;
    String method_pay ="";
    TextView tv_nameship , tv_address_ship,tv_phone_ship;
    RelativeLayout lu_ship;

    /////////// rcv_item
    RecyclerView rcv_item_checkout;
    ItemCheckout_Adapter itemCheckout_adapter;


    /////////// item_buy
    LinearLayout itemBuy;
    ImageView image_item;
    TextView name_pro_item, tv_typro, tv_price, tv_qty;

    /////////// tom tat yeu cau
    TextView tv_totalx, txxxx, txxff;

    /////////// tom tat mmethod
    RadioButton radioButton, radioButton1, radioButton2;
    RadioGroup radioGroup;

    /////////// boottom
    TextView tv_qty_item, tv_total, tv_discount;
    Button btn_checkout;
    Double toatl;
    ProgressDialog pd;

    RetrofitInterface dataService = APIService.getService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        pd = new ProgressDialog(this);

        Intent intent = getIntent();
        type_checkout  = ""+intent.getStringExtra("checkout_type");
        id_product  = ""+intent.getStringExtra("id_product");
        type_product = ""+intent.getStringExtra("type_pro");
        name_product = ""+intent.getStringExtra("name_pro");
        Image_product = ""+intent.getStringExtra("image_pro");

        id_type = ""+intent.getStringExtra("id_type");
        id_size = ""+intent.getStringExtra("id_size");
        name_type  = ""+intent.getStringExtra("name_type");
        name_size = ""+intent.getStringExtra("name_size");
        qty_item = ""+intent.getStringExtra("qty_item");
        price_item = ""+intent.getStringExtra("price_item");


        AnhXa();
        ImageButton backc = findViewById(R.id.backc);
        backc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImageView btn_change_ship = findViewById(R.id.tv_xemship);
        btn_change_ship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Checkout_Activity.this, Infor_Shipping.class);
                startActivity(intent3);
            }
        });
        Load_Info_Ship();
        if(type_checkout.equals("buy")){
            itemBuy.setVisibility(View.VISIBLE);
            rcv_item_checkout.setVisibility(View.GONE);
            LoadItemBUY();
            tv_totalx.setText(price_item +APIService.Donvi);

            float abc = (float) (Integer.parseInt(price_item)*0.3);
            Double discont = (double) Math.round(abc * 10) / 10;

            toatl = Integer.parseInt(price_item) - discont;

            txxxx.setText("-" + discont +APIService.Donvi);
            txxff.setText(toatl +APIService.Donvi);

            ////////bottom //////////
            tv_qty_item.setText("1 mặt hàng");
            tv_total.setText(price_item +APIService.Donvi);
            tv_discount.setText(toatl + "("+APIService.Donvi+")");

        }else if(type_checkout.equals("cart")){
            itemBuy.setVisibility(View.GONE);
            rcv_item_checkout.setVisibility(View.VISIBLE);
            LoadTotal_checkout();
            LoadItemCart_Checkout();
        }


        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);

            // Gắn sự kiện OnClickListener cho mỗi RadioButton

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int text = ((RadioButton)view).getId();
                    if (text ==2131362227){
                        method_pay = "Cash on Delivery";
                    }else if (text ==2131362228){
                        method_pay = "Momo";
                    }else if (text ==2131362229){
                        method_pay = "Credit / Debit Card";
                    }

//                    Toast.makeText(Checkout_Activity.this, ":" +text, Toast.LENGTH_SHORT).show();
//                    method_pay =  ((RadioButton)view).getText().toString();
                }
            });
        }

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(method_pay =="") {
                    Toast.makeText(Checkout_Activity.this, "PleaseChoose Method Payment", Toast.LENGTH_SHORT).show();
                }else if(id_ship_cf == 0){
                    Toast.makeText(Checkout_Activity.this, "PleaseChoose Shipping Payment", Toast.LENGTH_SHORT).show();

                }else{
                    if(type_checkout.equals("buy")){
                        CheckoutItemBuy();
                    }else if(type_checkout.equals("cart")){
                        ChevkoutItemCart();
                    }
                }

            }
        });


    }

    private void ChevkoutItemCart() {
        pd.show();
        pd.setMessage("Paying ...");
        Double Pt_dis = 0.33;
        RetrofitInterface dataService = APIService.getService();
        Call<Void> callback = dataService.ChekoutItemCart(AppLayout.id_user, id_ship_cf, method_pay, toatl, Pt_dis);
        callback.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() ==200){
                    Toast.makeText(Checkout_Activity.this, "Checkout Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Checkout_Activity.this, Checkout_Success.class);
                    startActivity(intent);
                    finish();
                    pd.dismiss();

                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Toast.makeText(Checkout_Activity.this, "failll" + throwable, Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }

    private void CheckoutItemBuy() {
        pd.show();
        pd.setMessage("Paying ...");
        Double Pt_dis = 0.33;
        RetrofitInterface dataService = APIService.getService();
        Call<Void> callback = dataService.CheckoutBuyItem(AppLayout.id_user, id_ship_cf, method_pay, id_product, toatl,
                Pt_dis,type_product,id_type, id_size, name_type, name_size, qty_item, price_item);
        callback.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() ==200){
                    Toast.makeText(Checkout_Activity.this, "Checkout Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Checkout_Activity.this, Checkout_Success.class);
                    startActivity(intent);
                    finish();
                    pd.dismiss();

                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Toast.makeText(Checkout_Activity.this, "failll" + throwable, Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }

    private void Load_Info_Ship() {
        Call<Model_Shipping> callback = dataService.GetInfo_Ship(AppLayout.id_user);
        callback.enqueue(new Callback<Model_Shipping>() {
            @Override
            public void onResponse(Call<Model_Shipping> call, Response<Model_Shipping> response) {
                if (response.isSuccessful()){
                    for(Model_Shipping item:response.body().getModel_Shipping()){
                        id_ship_cf = item.getId_shipping();
                        tv_nameship.setText(item.getName_shipping());
                        tv_phone_ship.setText("Phone: "+item.getPhone_shipping());
                        tv_address_ship.setText(item.getDesc_address()+ ", "+ item.getAddress_shipping());
                    }
                }
            }

            @Override
            public void onFailure(Call<Model_Shipping> call, Throwable throwable) {
                Toast.makeText(Checkout_Activity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void LoadItemCart_Checkout() {
        LinearLayoutManager layoutManager_top = new LinearLayoutManager(Checkout_Activity.this);
        layoutManager_top.setOrientation(RecyclerView.VERTICAL);
        rcv_item_checkout.setLayoutManager(layoutManager_top);
        Call<ModelCart> callback = dataService.LoadItem_CheckOut(AppLayout.id_user);
        callback.enqueue(new Callback<ModelCart>() {
            @Override
            public void onResponse(Call<ModelCart> call, Response<ModelCart> response) {
                if (response.code() == 200) {
                    List<ModelCart> cart = response.body().getModelCart();
                    itemCheckout_adapter = new ItemCheckout_Adapter(cart, Checkout_Activity.this);
                    rcv_item_checkout.setAdapter(itemCheckout_adapter);
                    itemCheckout_adapter.notifyDataSetChanged();
                    int i=0;
                    for(ModelCart item:response.body().getModelCart()){
                        i++;
                    }
                    tv_qty_item.setText(i+" mặt hàng");
                }
            }

            @Override
            public void onFailure(Call<ModelCart> call, Throwable throwable) {
                Toast.makeText(Checkout_Activity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void LoadItemBUY() {
        try {
            Picasso.get().load(APIService.IPIMAGE_PRODUCT + Image_product).placeholder(R.drawable.ic_img).into(image_item);
        } catch (Exception e) {

        }
        name_pro_item.setText(name_product);


        tv_price.setText(qty_item + "x"+price_item +APIService.Donvi);
        tv_qty.setText(Integer.parseInt(qty_item)*Integer.parseInt(price_item)  +APIService.Donvi);

        if(Integer.parseInt(type_product) == 0){
            tv_typro.setVisibility(View.GONE);
        }else if(Integer.parseInt(type_product) == 1){
            tv_typro.setVisibility(View.VISIBLE);
            tv_typro.setText(name_type);
        }else if(Integer.parseInt(type_product) == 2){
            tv_typro.setVisibility(View.VISIBLE);
            tv_typro.setText(name_size + ", "+name_type);
        }

    }


    private void LoadTotal_checkout() {
        RetrofitInterface dataService = APIService.getService();
        Call<Model_TotalPriceCart> callback = dataService.Get_Total_Cart(AppLayout.id_user);
        callback.enqueue(new Callback<Model_TotalPriceCart>() {
            @Override
            public void onResponse(Call<Model_TotalPriceCart> call, Response<Model_TotalPriceCart> response) {
                if (response.isSuccessful()) {
                    for(Model_TotalPriceCart item:response.body().getModel_TotalPriceCart()){

                        tv_totalx.setText(item.getTotal() +APIService.Donvi);

                        float abc = (float) (Integer.parseInt(item.getTotal())*0.3);
                        Double discont = (double) Math.round(abc * 10) / 10;

                        toatl = Integer.parseInt(item.getTotal()) - discont;

                        txxxx.setText("-" + discont +APIService.Donvi);
                        txxff.setText(toatl +APIService.Donvi);

                        ////////bottom //////////
                        tv_qty_item.setText("1 mặt hàng");
                        tv_total.setText(item.getTotal() +APIService.Donvi);
                        tv_discount.setText(toatl +"("+APIService.Donvi+")");

                    }

                }
            }

            @Override
            public void onFailure(Call<Model_TotalPriceCart> call, Throwable throwable) {
                Toast.makeText(Checkout_Activity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void AnhXa() {
        tv_nameship = findViewById(R.id.tv_nameship);
        tv_address_ship = findViewById(R.id.tv_address_ship);
        tv_phone_ship = findViewById(R.id.tv_phone_ship);
        lu_ship = findViewById(R.id.lu_ship);

        rcv_item_checkout = findViewById(R.id.rcv_item_checkout);

        itemBuy = findViewById(R.id.itemBuy);
        image_item = findViewById(R.id.image_item);
        name_pro_item = findViewById(R.id.name_pro_item);
        tv_typro = findViewById(R.id.tv_typro);
        tv_price = findViewById(R.id.tv_price);
        tv_qty = findViewById(R.id.tv_qty);


        tv_totalx = findViewById(R.id.tv_totalx);
        txxxx = findViewById(R.id.txxxx);
        txxff = findViewById(R.id.txxff);

        radioGroup = findViewById(R.id.radioGroup);
        radioButton = findViewById(R.id.radioButton);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);

        tv_qty_item = findViewById(R.id.tv_qty_item);
        tv_total = findViewById(R.id.tv_total);
        tv_discount = findViewById(R.id.tv);
        btn_checkout = findViewById(R.id.btn_checkout);


    }
}