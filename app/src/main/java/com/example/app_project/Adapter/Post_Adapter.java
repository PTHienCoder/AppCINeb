package com.example.app_project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_project.AppLayout;

import com.example.app_project.Detail_Post_Activity;
import com.example.app_project.Model.ModelPost;
import com.example.app_project.R;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Post_Adapter extends RecyclerView.Adapter<Post_Adapter.PlaceViewHolder> {

    private List<ModelPost> postModelList;
    private Context context;

    int countlikes = 0;
    String checksave ="";

    boolean mProcessLike = false;

    public Post_Adapter(List<ModelPost> postModelList, Context context) {
        this.postModelList = postModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_post,viewGroup,false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        ModelPost postModel = postModelList.get(position);
        if (postModel == null){
            return;
        }
        int id = postModelList.get(position).getId_post();
        int id_of_user = postModelList.get(position).getId_user();
        String title = postModelList.get(position).getTitle_post();
        String username = postModelList.get(position).getName();
        String timep = postModelList.get(position).getTime_create();
//        String level = postModelList.get(position).getLevel();
        holder.title.setText(title);
        holder.name.setText(username);
//        holder.level.setText("A");
        holder.time.setText(timep);
        if(id_of_user == AppLayout.id_user){
            holder.btn_save.setVisibility(View.GONE);
            holder.btn_save1.setVisibility(View.GONE);
        }



        RetrofitInterface dataService = APIService.getService();
        Call<Void> callbackd = dataService.getiduser_savepost(id, AppLayout.id_user);
        callbackd.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200 ){
                    holder.btn_save.setVisibility(View.GONE);
                    holder.btn_save1.setVisibility(View.VISIBLE);


                }else if(response.code() == 404 ){

                    holder.btn_save.setVisibility(View.VISIBLE);
                    holder.btn_save1.setVisibility(View.GONE);

                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
        holder.btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        RetrofitInterface dataService = APIService.getService();
                        Call<Void> callbackd = dataService.savepost(id, AppLayout.id_user);
                        callbackd.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.code() == 200 ){
                                    Toast.makeText(context, "save Success " +AppLayout.id_user, Toast.LENGTH_SHORT).show();
                                    holder.btn_save.setVisibility(View.GONE);
                                    holder.btn_save1.setVisibility(View.VISIBLE);

                                }

                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });

            }
        });

        holder.btn_save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RetrofitInterface dataService = APIService.getService();
                Call<Void> callbackd = dataService.deletesave(id, AppLayout.id_user);
                callbackd.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code() == 200 ){
                            holder.btn_save.setBackgroundResource(R.drawable.ic_nosavee);
                            Toast.makeText(context, "Nosave Success "+AppLayout.id_user, Toast.LENGTH_SHORT).show();
                            holder.btn_save1.setVisibility(View.GONE);
                            holder.btn_save.setVisibility(View.VISIBLE);
                        }


                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            }
        });


        try {
            Picasso.get().load(APIService.IPIMAGE_POST + postModel.getImage_post()).placeholder(R.drawable.ic_img).into(holder.post_img);
        } catch (Exception e) {

        }

        try {
            Picasso.get().load(APIService.IPIMAGE_USER + id_of_user+"/"+ postModel.getImage_user()).placeholder(R.drawable.ic_img).into(holder.avatar_post);
        } catch (Exception e) {

        }


        holder.title.setOnClickListener(new View.OnClickListener() {
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
        private ImageView post_img,avatar_post;
        private TextView title, name, time, level ;
        ImageButton btn_save, btn_save1;
        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar_post =  itemView.findViewById(R.id.avatar_post);
            post_img =  itemView.findViewById(R.id.cat_img);
            title =  itemView.findViewById(R.id.textView1);
            name =  itemView.findViewById(R.id.name_post);
            time =  itemView.findViewById(R.id.timepost);
            level =  itemView.findViewById(R.id.name_level);
            btn_save = itemView.findViewById(R.id.btn_save);
            btn_save1 = itemView.findViewById(R.id.btn_save1);

        }
    }
}