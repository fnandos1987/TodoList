package br.com.fernando.todolist;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;

/**
 * Created by 19294 on 06/10/2018.
 */

public class Migrations {

    public static final Migration FROM_1_TO_2 = new Migration(1, 2){
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE tasks "
                    + " ADD COLUMN creationDate INTEGER DEFAULT 0");
        }
    };
}
