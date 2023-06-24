package com.ortega.tshombo.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ortega.tshombo.R;
import com.ortega.tshombo.models.DataModel;
import java.util.ArrayList;
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
    Context context;
    ArrayList<DataModel> dataModels;

    public DataAdapter(Context context, ArrayList<DataModel> dataModels) {
        this.context = context;
        this.dataModels = dataModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_data, parent, false);
        return new DataAdapter.MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataModel dataModel = dataModels.get(position);

        holder.room.setText("Room: " + dataModel.getRoom());
        holder.subject.setText("Subject: " + dataModel.getSubject());
        holder.hoursMask.setText("hoursMask: " + dataModel.getHoursMask());
        holder.start.setText("start: " + dataModel.getStart());
        holder.end.setText("end: " + dataModel.getEnd());
        holder.classes.setText("classes: " + dataModel.getClasses());
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView room;
        TextView subject;
        TextView hoursMask;
        TextView start;
        TextView end;
        TextView classes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            room = itemView.findViewById(R.id.room);
            subject = itemView.findViewById(R.id.subject);
            hoursMask = itemView.findViewById(R.id.hoursMask);
            start = itemView.findViewById(R.id.start);
            end = itemView.findViewById(R.id.end);
            classes = itemView.findViewById(R.id.classes);
        }
    }

}
