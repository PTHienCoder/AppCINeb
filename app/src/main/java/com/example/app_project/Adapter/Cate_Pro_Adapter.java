package com.example.app_project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_project.Model.ModelCateProduct;
import com.example.app_project.Model.ModelPost;
import com.example.app_project.R;
import com.example.app_project.Search_Product;
import com.example.app_project.Service.APIService;
import com.example.app_project.Shopping_Activity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Cate_Pro_Adapter extends RecyclerView.Adapter<Cate_Pro_Adapter.PlaceViewHolder> {


    private List<ModelCateProduct> ModelCateProduct;
    private Context context;


    public Cate_Pro_Adapter(List<ModelCateProduct> ModelCateProduct, Context context) {
        this.ModelCateProduct = ModelCateProduct;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cate_product,viewGroup,false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        ModelCateProduct cateModel = ModelCateProduct.get(position);
        if (cateModel == null){
            return;
        }
        int id = ModelCateProduct.get(position).getId_areas();
        String iamge = ModelCateProduct.get(position).getImage_areas();
        String name = ModelCateProduct.get(position).getName_areas();

        holder.title.setText(name);

        try {
            Picasso.get().load(APIService.CARTEGORY + iamge).placeholder(R.drawable.ic_img).into(holder.image_cate);
        } catch (Exception e) {

        }
        holder.image_cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(context, Search_Product.class);
                intent2.putExtra("key", String.valueOf(id));
                context.startActivity(intent2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ModelCateProduct.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_cate;
        private TextView title;
        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            image_cate =  itemView.findViewById(R.id.cat_img);
            title =  itemView.findViewById(R.id.textView2);


        }
    }
}
