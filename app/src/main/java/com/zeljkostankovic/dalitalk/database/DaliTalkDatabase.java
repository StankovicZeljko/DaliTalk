package com.zeljkostankovic.dalitalk.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.zeljkostankovic.dalitalk.dao.ItemDao;
import com.zeljkostankovic.dalitalk.modules.Item;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {Item.class}, version = 1, exportSchema = false)
public abstract class DaliTalkDatabase extends RoomDatabase {

    public abstract ItemDao itemDao();

    private static volatile DaliTalkDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static public ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static DaliTalkDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DaliTalkDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DaliTalkDatabase.class, "dali_talk_database").build();
                }
            }
        }
        return INSTANCE;

    }
}
