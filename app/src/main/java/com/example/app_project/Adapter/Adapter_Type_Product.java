package com.example.app_project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_project.Model.ModelType_Pro;
import com.example.app_project.R;
import com.example.app_project.interface_app.ItemClicklistener_Type;

import java.util.List;

public class Adapter_Type_Product extends RecyclerView.Adapter<Adapter_Type_Product.PlaceViewHolder> {

//    public static int id ;
    private List<ModelType_Pro> modelType_pros;
    private Context context;
    ItemClicklistener_Type itemClicklistener;
    int selectPosition = -1;

    public Adapter_Type_Product(List<ModelType_Pro> modelType_pros, Context context, ItemClicklistener_Type itemClicklistener) {
        this.modelType_pros = modelType_pros;
        this.context = context;
        this.itemClicklistener = itemClicklistener;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_type_pro,viewGroup,false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        ModelType_Pro cateModel = modelType_pros.get(position);
        if (cateModel == null){
            return;
        }
         int id = modelType_pros.get(position).getId_type_pro();
//        String iamge = modelType_pros.get(position).getName_type_pro();
        String name = modelType_pros.get(position).getName_type_pro();

//
//        try {
//            Picasso.get().load(APIService.CARTEGORY + iamge).placeholder(R.drawable.ic_img).into(holder.image_cate);
//        } catch (Exception e) {
//
//        }
        holder.title.setText(name);
        holder.title.setChecked(position == selectPosition);
        holder.title.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    selectPosition = holder.getAdapterPosition();
                    itemClicklistener.Onclick_Type(id, name);

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
        return modelType_pros.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
//        private ImageView image_cate;
        private RadioButton title;
        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
//            image_cate =  itemView.findViewById(R.id.cat_img);
            title =  itemView.findViewById(R.id.radioButtontype);


        }
    }
}