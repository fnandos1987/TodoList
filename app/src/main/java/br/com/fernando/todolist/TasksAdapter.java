package br.com.fernando.todolist;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 19294 on 05/10/2018.
 */

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {

    private final onTaskClickListener listener;
    private List<Task> taskList = new ArrayList<>();

    public TasksAdapter(onTaskClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(
                android.R.layout.simple_list_item_2,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Task task = taskList.get(position);

        holder.title.setText(taskList.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(task);
            }
        });
        if (task.isDone()) {
            holder.title.setTextColor(Color.RED);
        } else {
            holder.title.setTextColor(Color.BLACK);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm");
        holder.date.setText(simpleDateFormat.format(task.getCreationDate()));
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void setup(List<Task> tasks) {
        this.taskList.clear();
        this.taskList.addAll(tasks);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(android.R.id.text1);
            date = itemView.findViewById(android.R.id.text2);
        }

    }

    interface onTaskClickListener {
        void onClick(Task task);
    }
}
