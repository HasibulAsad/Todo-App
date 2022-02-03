package com.example.newtodoapp;

import android.text.Layout;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    ArrayList<Task> tasks= new ArrayList<>();
    OnClickListener onClickListener;

    public Adapter(ArrayList<Task> tasks,OnClickListener onClickListener) {
        this.tasks = tasks;
        this.onClickListener= onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row_layout, parent, false);
        return new ViewHolder(view,onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvtasktext.setText(tasks.get(position).getText());
        holder.tvdate.setText(DateFormat.format("dd", tasks.get(position).getCreated()));
        holder.tvday.setText(DateFormat.format("E", tasks.get(position).getCreated()));
        holder.tvtime.setText(DateFormat.format("hh:mm A", tasks.get(position).getCreated()));
        holder.checkBox.setChecked(tasks.get(position).isCompleted);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvday, tvdate, tvtime, tvtasktext;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView,OnClickListener onClickListener) {
            super(itemView);
            tvday = itemView.findViewById(R.id.tvday);
            tvdate = itemView.findViewById(R.id.tvdate);
            tvtime = itemView.findViewById(R.id.tvTime);
            tvtasktext = itemView.findViewById(R.id.idtasktext);
            checkBox = itemView.findViewById(R.id.idcheckBox);

            itemView.setOnClickListener(v -> onClickListener.OnitemClick(getAdapterPosition()));
            checkBox.setOnCheckedChangeListener((v,checked)-> onClickListener.OnitemCheaked(getAdapterPosition(),checked));

        }
    }
}
