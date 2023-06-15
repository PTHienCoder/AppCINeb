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

import com.example.app_project.Detail_Post_Activity;
import com.example.app_project.Model.ModelPost;
import com.example.app_project.Model.Model_Cate_Post;
import com.example.app_project.R;
import com.example.app_project.Service.APIService;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_Item_Cate extends RecyclerView.Adapter<Adapter_Item_Cate.PlaceViewHolder> {

    //    public static int id ;
    private List<ModelPost> model_Posts;
    private Context context;


    public Adapter_Item_Cate(List<ModelPost> model_Posts, Context context) {
        this.model_Posts = model_Posts;
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
        ModelPost cateModel = model_Posts.get(position);
        if (cateModel == null){
            return;
        }
        int id = model_Posts.get(position).getId_post();
        holder.title.setText(model_Posts.get(position).getTitle_post().toString());
        try {
            Picasso.get().load(APIService.IPIMAGE_POST + model_Posts.get(position).getImage_post()).placeholder(R.drawable.ic_img).into(holder.image_cate);
        } catch (Exception e) {

        }
        holder.llll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detail_Post_Activity.class);
                intent.putExtra("id", String.valueOf(id));
                intent.putExtra("Image_post", cateModel.getImage_post());
                intent.putExtra("Title_post", cateModel.getTitle_post());
                intent.putExtra("Desc_post", cateModel.getDesc_post());
                intent.putExtra("Detail_post", cateModel.getDetail_post());
                intent.putExtra("Time_create", cateModel.getTime_create());
                intent.putExtra("Id_user_Post", String.valueOf(cateModel.getId_user()));
                intent.putExtra("name_user", cateModel.getName());
                intent.putExtra("image_user", cateModel.getImage_user());
                intent.putExtra("Qty_pro_post", cateModel.getQty_pro_post());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return model_Posts.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_cate;
        private TextView title;
        private LinearLayout llll;
        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            image_cate =  itemView.findViewById(R.id.cat_img);
            title =  itemView.findViewById(R.id.textView2);
            llll =  itemView.findViewById(R.id.llll);

        }
    }
}