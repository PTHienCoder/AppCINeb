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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_project.Model.Model_Comment;
import com.example.app_project.R;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Comment extends RecyclerView.Adapter<Adapter_Comment.Myholder>{
    Context context;
    List<Model_Comment> modelComments;

    String myUid;
    int countcmt = 0;

    public Adapter_Comment(Context context, List<Model_Comment> modelComments) {
        this.context = context;
        this.modelComments = modelComments;


    }

    @NonNull
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cmt, parent, false);
        return new Myholder(view);
    }



    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        String uname = modelComments.get(position).getUsername();
        String avta = modelComments.get(position).getAvatar();
        String pcmt = modelComments.get(position).getComment_content();
        String date = modelComments.get(position).getDate();
        String idcmnt = modelComments.get(position).getId();

//        Calendar calendar = Calendar.getInstance(Locale.getDefault());
//        calendar.setTimeInMillis(Long.parseLong(date));
//        String datetime = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();


        holder.tv_name.setText(uname);
        holder.tvtime.setText(date);
        holder.tvcmt.setText(pcmt);
        try {
            Picasso.get().load(APIService.IPIMAGE_USER+ modelComments.get(position).getId_of_user() +"/"+avta).placeholder(R.drawable.ic_face).into(holder.img_cmt);
        }catch (Exception e){

        }


    }





    public int getItemCount() {
        if (modelComments != null){
            return modelComments.size();
        }

        return 0;
    }

    class Myholder extends RecyclerView.ViewHolder{
        ImageView img_cmt;
        LinearLayout lu_cmt;
        TextView tv_name, tvcmt, tvtime,repcmnt;

        public Myholder(@NonNull View itemView) {
            super(itemView);
            img_cmt = itemView.findViewById(R.id.avatar_cmt);
            tv_name = itemView.findViewById(R.id.name_cmt);
            tvcmt = itemView.findViewById(R.id.cmt_tv);
            tvtime = itemView.findViewById(R.id.time_cmt);
            lu_cmt = itemView.findViewById(R.id.lu_cmt);
            repcmnt =itemView.findViewById(R.id.repcmt);
        }
    }
}
