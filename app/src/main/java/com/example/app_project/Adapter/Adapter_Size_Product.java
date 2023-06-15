package com.example.app_project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_project.Model.ModelSize_Pro;
import com.example.app_project.R;
import com.example.app_project.interface_app.ItemClicklistener_Size;
import com.example.app_project.interface_app.ItemClicklistener_Type;

import java.util.List;

public class Adapter_Size_Product extends RecyclerView.Adapter<Adapter_Size_Product.PlaceViewHolder> {


    private List<ModelSize_Pro> modelSize_pros;
    private Context context;
    ItemClicklistener_Size itemClicklisten;
    int selectPosition = -1;

    public Adapter_Size_Product(List<ModelSize_Pro> modelSize_pros, Context context, ItemClicklistener_Size itemClicklisten) {
        this.modelSize_pros = modelSize_pros;
        this.context = context;
        this.itemClicklisten = itemClicklisten;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_size_pro, viewGroup, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        ModelSize_Pro cateModel = modelSize_pros.get(position);
        if (cateModel == null) {
            return;
        }
        int id = modelSize_pros.get(position).getId_size_pro();
        String name = modelSize_pros.get(position).getName_size();

        holder.title.setText(name);
        holder.title.setChecked(position == selectPosition);
        holder.title.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    selectPosition = holder.getAdapterPosition();
                    itemClicklisten.Onclick_Size(id, name);

                }
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
        return modelSize_pros.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        //        private ImageView image_cate;
        private RadioButton title;

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.radioButtonsize);


        }
    }
}