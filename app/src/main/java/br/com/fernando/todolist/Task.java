package br.com.fernando.todolist;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by 19294 on 05/10/2018.
 */
@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private final Integer id;
    private final String title;
    private final boolean done;
    private final Date creationDate;

    @Ignore
    public Task(String title, boolean done) {
        this.id = null;
        this.title = title;
        this.done = done;
        this.creationDate = new Date();
    }

    public Task(Integer id, String title, boolean done, Date creationDate) {
        this.id = id;
        this.title = title;
        this.done = done;
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return done;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
