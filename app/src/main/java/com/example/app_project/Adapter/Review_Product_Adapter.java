package com.example.app_project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_project.Model.ModelCateProduct;
import com.example.app_project.Model.ModelReView_Pro;
import com.example.app_project.R;
import com.example.app_project.Service.APIService;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Review_Product_Adapter extends RecyclerView.Adapter<Review_Product_Adapter.PlaceViewHolder> {


    private List<ModelReView_Pro> modelReView_pros;
    private Context context;


    public Review_Product_Adapter(List<ModelReView_Pro> modelReView_pros, Context context) {
        this.modelReView_pros = modelReView_pros;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_review_product,viewGroup,false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        ModelReView_Pro modelReView_pro = modelReView_pros.get(position);
        if (modelReView_pro == null){
            return;
        }
        int id = modelReView_pros.get(position).getId_review();
        int id_user = modelReView_pros.get(position).getId_user();
//        String iamge = modelReView_pros.get(position).getImage_review();
        String imga_user = modelReView_pros.get(position).getImage_user();
        String name = modelReView_pros.get(position).getName();
        String content = modelReView_pros.get(position).getContent_review();
        String time = modelReView_pros.get(position).getTime_review();

        String ratting = modelReView_pros.get(position).getRating_review();

        holder.tv_username.setText(name);
        holder.tv_content.setText(content);
        holder.tv_time.setText(time);

        if(ratting != null){
          holder.ratingBar.setRating(Float.parseFloat(ratting));
        }else{
            holder.ratingBar.setVisibility(View.GONE);
        }

        try {
            Picasso.get().load(APIService.IPIMAGE_USER +id_user +"/"+ imga_user).placeholder(R.drawable.ic_img).into(holder.image_user);
        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return modelReView_pros.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_user;
        private TextView tv_username, tv_content, tv_time;
        private RatingBar ratingBar;
        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            image_user =  itemView.findViewById(R.id.image_user);
            tv_username =  itemView.findViewById(R.id.tv_username);
            tv_content =  itemView.findViewById(R.id.tv_content);
            tv_time =  itemView.findViewById(R.id.tv_time);
            ratingBar =  itemView.findViewById(R.id.ratingBar);


        }
    }
}

