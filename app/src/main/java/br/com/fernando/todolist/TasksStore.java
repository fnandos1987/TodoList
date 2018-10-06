package br.com.fernando.todolist;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

/**
 * Created by 19294 on 06/10/2018.
 */
@Database(entities = Task.class, version = 1)
@TypeConverters({ DateConverter.class })
public abstract class TasksStore extends RoomDatabase {

    private static TasksStore instance = null;

    public abstract TasksDao getTasksDao();

    public static TasksStore getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    TasksStore.class,
                    "tasks.db")
                    .addMigrations(Migrations.FROM_1_TO_2)
                    .build();
        }
        return instance;
    }
}
