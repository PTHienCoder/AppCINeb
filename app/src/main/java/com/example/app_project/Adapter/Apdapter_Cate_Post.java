package com.example.app_project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_project.Model.ModelPost;
import com.example.app_project.Model.ModelType_Pro;
import com.example.app_project.Model.Model_Cate_Post;
import com.example.app_project.R;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;
import com.example.app_project.interface_app.ItemClicklistener_Type;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Apdapter_Cate_Post extends RecyclerView.Adapter<Apdapter_Cate_Post.PlaceViewHolder> {

    //    public static int id ;
    private List<Model_Cate_Post> model_cate_posts;
    private Context context;
//

    Adapter_Item_Cate adapter_item_cate ;
    List<ModelPost> modelPosts;


    public Apdapter_Cate_Post(List<Model_Cate_Post> model_cate_posts, Context context) {
        this.model_cate_posts = model_cate_posts;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ecineb_tv,viewGroup,false);
        return new PlaceViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        Model_Cate_Post cateModel = model_cate_posts.get(position);
        if (cateModel == null){
            return;
        }

        int id = model_cate_posts.get(position).getId_field();
        holder.title.setText(model_cate_posts.get(position).getName_field());


        ////////////////// top posst ////////////////////////
        LinearLayoutManager ly = new LinearLayoutManager(context);
        ly.setOrientation(RecyclerView.HORIZONTAL);

        holder.rcv1.setLayoutManager(ly);

//        Toast.makeText(context, "tsd: " +id , Toast.LENGTH_SHORT).show();
        RetrofitInterface dataService = APIService.getService();
        Call<ModelPost> callback = dataService.Load_ItemPost_cate(id);
        callback.enqueue(new Callback<ModelPost>() {
            @Override
            public void onResponse(Call<ModelPost> call, Response<ModelPost> response) {
                if (response.isSuccessful()) {
                    List<ModelPost> Model_Cate_Post = response.body().getModelPost();

                    adapter_item_cate = new Adapter_Item_Cate(Model_Cate_Post, context);
                    holder.rcv1.setAdapter(adapter_item_cate);
                    adapter_item_cate.notifyDataSetChanged();

                } else {
                    Toast.makeText(context, "that ss" , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelPost> call, Throwable throwable) {
                Toast.makeText(context, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
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
        return model_cate_posts.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        //        private ImageView image_cate;
        private TextView title;
        private RecyclerView rcv1;
        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
//            image_cate =  itemView.findViewById(R.id.cat_img);
            title =  itemView.findViewById(R.id.tv1);
            rcv1 = itemView.findViewById(R.id.rcv1);


        }
    }
}