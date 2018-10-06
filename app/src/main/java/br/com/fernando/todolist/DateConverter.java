package br.com.fernando.todolist;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by 19294 on 06/10/2018.
 */

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timeInMillis) {
        if(timeInMillis == null){
            return null;
        }

        return new Date(timeInMillis);
    }

    @TypeConverter
    public static Long fromDate(Date date) {
        if(date == null){
            return null;
        }

        return date.getTime();
    }
}
