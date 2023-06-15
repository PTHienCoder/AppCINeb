package com.example.app_project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_project.Model.Model_Notification;
import com.example.app_project.R;
import com.example.app_project.Service.APIService;
import com.example.app_project.Service.RetrofitInterface;
import com.example.app_project.interface_app.itemOnclickListenner_ItemCart;
import com.example.app_project.interface_app.itemOnclickListenner_ItemNoti;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_Notification extends RecyclerView.Adapter<Adapter_Notification.Myholder>{
    Context context;
    List<Model_Notification> modelNotifications;
    itemOnclickListenner_ItemNoti itemOnclickListenner_itemNoti;
    public Adapter_Notification(Context context, List<Model_Notification> modelNotifications, itemOnclickListenner_ItemNoti itemOnclickListenner_itemNoti) {
        this.context = context;
        this.modelNotifications = modelNotifications;
        this.itemOnclickListenner_itemNoti = itemOnclickListenner_itemNoti;

    }

    @NonNull
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent,false);
        return new Myholder(view);
    }

    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        //get data
        String hisUID = modelNotifications.get(position).getId();
        String content = modelNotifications.get(position).getContent();
        String idp = modelNotifications.get(position).getIdp();
        String img = modelNotifications.get(position).getAvatar();
        String time = modelNotifications.get(position).getTimeNoti();
        String name = modelNotifications.get(position).getUsername();
        holder.contenttv.setText(content);
        holder.nametv.setText(name);

        holder.timetv.setText(time);
        try {
            Picasso.get().load(APIService.IPIMAGE_USER +hisUID+"/" + img)
                    .placeholder(R.drawable.ic_face)
                    .into(holder.avatar);
        }catch (Exception e){

        }
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                delete(modelNotifications.get(position).getIdnoti());
            }
        });

    }

    private void delete(String time) {

        RetrofitInterface dataService = APIService.getService();
        Call<Void> callback = dataService.Clear_notification(time);
        callback.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    Toast.makeText(context, "Delete Item Success" , Toast.LENGTH_SHORT).show();
                    itemOnclickListenner_itemNoti.Onclickitem_delete();
                }

            }
            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
            }
        });

    }

    public int getItemCount() {
        return modelNotifications.size();
    }

    class Myholder extends RecyclerView.ViewHolder {
        ImageView avatar;
        ImageButton btn_delete;
        TextView nametv, contenttv, timetv;
        RelativeLayout ul;
        public Myholder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar_useruc);
            nametv = itemView.findViewById(R.id.name_noti);
            btn_delete = itemView.findViewById(R.id.delete);
            contenttv = itemView.findViewById(R.id.tvnoti);
            timetv = itemView.findViewById(R.id.timenoti);
            ul = itemView.findViewById(R.id.ul);


        }
    }
}
