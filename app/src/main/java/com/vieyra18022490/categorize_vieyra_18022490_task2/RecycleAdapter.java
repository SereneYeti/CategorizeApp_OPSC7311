package com.vieyra18022490.categorize_vieyra_18022490_task2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private ArrayList<Recycling> recyclingArrayList;
    public RecycleAdapter(ArrayList<Recycling> RecyclingArrayList)
    {
        this.recyclingArrayList = RecyclingArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_cell,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recycleImage.setImageBitmap(recyclingArrayList.get(position).getImage());
        holder.recycleText.setText(recyclingArrayList.get(position).getName());
        holder.recycleText2.setText(recyclingArrayList.get(position).getDateCreated());

    }



    @Override
    public int getItemCount() {
        return recyclingArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView recycleImage;
        private TextView recycleText;
        private TextView recycleText2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recycleImage = (ImageView) itemView.findViewById(R.id.recycleCellImage);
            recycleText = (TextView) itemView.findViewById(R.id.recycleCellText);
            recycleText2 = (TextView) itemView.findViewById(R.id.recycleCellText2);
        }
    }
}
