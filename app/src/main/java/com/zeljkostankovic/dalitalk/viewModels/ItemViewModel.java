package com.zeljkostankovic.dalitalk.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.zeljkostankovic.dalitalk.modules.Item;
import com.zeljkostankovic.dalitalk.repository.ItemRepository;

import java.util.List;


public class ItemViewModel extends AndroidViewModel {

    private final ItemRepository mItemRepository;
    private final LiveData<List<Item>> mAllItems;

    public ItemViewModel(@NonNull Application application) {
        super(application);

        mItemRepository = new ItemRepository(application);
        mAllItems = mItemRepository.getmAllItems();

    }

    public LiveData<List<Item>> getmAllItems() {
        return mAllItems;
    }

    public void insert(Item item) {
        mItemRepository.insert(item);
    }
}
