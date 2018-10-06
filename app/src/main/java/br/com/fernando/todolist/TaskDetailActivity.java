package br.com.fernando.todolist;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TaskDetailActivity extends AppCompatActivity {

    private Task task;
    private TasksViewModel tasksViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        tasksViewModel = ViewModelProviders.of(this)
                .get(TasksViewModel.class);
        tasksViewModel.taskLiveData.observe(this, task -> {
            if(task != null){
                this.task = task;
                setTitle(task.getTitle());
            }
        });

        tasksViewModel.sucess.observe(this, success -> {
            if (Boolean.TRUE.equals(success)) {
                finish();
            }
        });

        int id = getIntent().getIntExtra("id", 0);
        tasksViewModel.findTaskById(id);

        Button buttonDelete = findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(view -> {
            tasksViewModel.delete(this.task);
        });

        Button buttonDone = findViewById(R.id.button_done);
        buttonDone.setOnClickListener(view -> {
            tasksViewModel.update(this.task);
        });
    }
}
