package com.zeljkostankovic.dalitalk.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.zeljkostankovic.dalitalk.modules.Item;

import java.util.List;

@Dao
public interface ItemDao {


   public static final String TABLE = "item_table";

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Item item);

    @Query("SELECT * FROM " + TABLE)
    LiveData<List<Item>> getAll();

    @Query("SELECT * FROM " + TABLE + " WHERE id LIKE :id LIMIT 1")
    Item findById(int id);

    @Delete
    void delete(Item item);
}
