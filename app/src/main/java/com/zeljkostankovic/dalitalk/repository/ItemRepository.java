package com.zeljkostankovic.dalitalk.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.zeljkostankovic.dalitalk.dao.ItemDao;
import com.zeljkostankovic.dalitalk.database.DaliTalkDatabase;
import com.zeljkostankovic.dalitalk.modules.Item;

import java.util.List;

public class ItemRepository {

    private final ItemDao mItemDao;
    private final LiveData<List<Item>> mAllItems;

    public ItemRepository (Application application) {
        DaliTalkDatabase db = DaliTalkDatabase.getDatabase(application);
        mItemDao = db.itemDao();
        mAllItems = mItemDao.getAll();
    }

    public LiveData<List<Item>> getmAllItems() {
        return mAllItems;
    }

    public void insert(Item item) {
        DaliTalkDatabase.databaseWriteExecutor.execute( () -> {
            mItemDao.insert(item);
        });
    }

}
