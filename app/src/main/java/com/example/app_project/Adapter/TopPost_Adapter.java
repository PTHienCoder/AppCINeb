package com.example.app_project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_project.Model.ModelPost;
import com.example.app_project.Detail_Post_Activity;
import com.example.app_project.R;
import com.example.app_project.Service.APIService;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopPost_Adapter extends RecyclerView.Adapter<TopPost_Adapter.PlaceViewHolder> {

    private List<ModelPost> postModelList;
    private Context context;

    public TopPost_Adapter(List<ModelPost> postModelList, Context context) {
        this.postModelList = postModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cate_post,viewGroup,false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        ModelPost postModel = postModelList.get(position);
        if (postModel == null){
            return;
        }
        String name = postModelList.get(position).getTitle_post();
        int id = postModelList.get(position).getId_post();
        holder.textView.setText(name);
        try {
            Picasso.get().load(APIService.IPIMAGE_POST + postModel.getImage_post()).placeholder(R.drawable.ic_img).into(holder.banner_img);
        } catch (Exception e) {

        }

        holder.llll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detail_Post_Activity.class);
                intent.putExtra("id", String.valueOf(id));
                intent.putExtra("Image_post", postModel.getImage_post());
                intent.putExtra("Title_post", postModel.getTitle_post());
                intent.putExtra("Desc_post", postModel.getDesc_post());
                intent.putExtra("Detail_post", postModel.getDetail_post());
                intent.putExtra("Time_create", postModel.getTime_create());
                intent.putExtra("Id_user_Post", String.valueOf(postModel.getId_user()));
                intent.putExtra("name_user", postModel.getName());
                intent.putExtra("image_user", postModel.getImage_user());
                intent.putExtra("Qty_pro_post", postModel.getQty_pro_post());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return postModelList.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        private ImageView banner_img;
        private TextView textView;
        LinearLayout llll;
        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            banner_img =  itemView.findViewById(R.id.cat_img);
            textView =  itemView.findViewById(R.id.textView2);
            llll =  itemView.findViewById(R.id.llll);


        }
    }
}


