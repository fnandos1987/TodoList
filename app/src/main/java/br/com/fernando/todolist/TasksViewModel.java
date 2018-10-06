package br.com.fernando.todolist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by 19294 on 06/10/2018.
 */

public class TasksViewModel extends AndroidViewModel {

    public final MutableLiveData<List<Task>> tasks = new MutableLiveData<>();
    public final MutableLiveData<Task> taskLiveData = new MutableLiveData<>();
    public final MutableLiveData<Boolean> sucess = new MutableLiveData<>();

    public TasksViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchTasks() {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                tasks.postValue(TasksStore.getInstance(getApplication())
                        .getTasksDao()
                        .fetchTasks());
                return null;
            }
        }.execute();
    }

    public void findTaskById(int id) {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                Task task = TasksStore.getInstance(getApplication())
                        .getTasksDao()
                        .findById(id);
                taskLiveData.postValue(task);
                return null;
            }
        }.execute();
    }

    public void insert(final Task task) {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                TasksStore.getInstance(getApplication())
                        .getTasksDao()
                        .insert(task);
                sucess.postValue(true);
                return null;
            }
        }.execute();
    }

    public void update(final Task task) {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                TasksStore.getInstance(getApplication())
                        .getTasksDao()
                        .update(new Task(task.getId(), task.getTitle(), true, task.getCreationDate()));
                sucess.postValue(true);
                return null;
            }
        }.execute();
    }

    public void delete(final Task task) {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                TasksStore.getInstance(getApplication())
                        .getTasksDao()
                        .delete(task);
                sucess.postValue(true);
                return null;
            }
        }.execute();
    }
}
