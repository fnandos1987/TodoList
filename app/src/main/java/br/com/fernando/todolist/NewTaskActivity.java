package br.com.fernando.todolist;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class NewTaskActivity extends AppCompatActivity {

    private TasksViewModel tasksViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        tasksViewModel = ViewModelProviders.of(this)
                .get(TasksViewModel.class);

        tasksViewModel.sucess.observe(this, success -> {
            if (Boolean.TRUE.equals(success)) {
                finish();
            }
        });

        Button buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(view -> {
            EditText inputNewTask = findViewById(R.id.input_new_task);
            String value = inputNewTask.getText().toString();
            if (!value.isEmpty()){
                tasksViewModel.insert(new Task(value, false));
            }
        });
    }
}
