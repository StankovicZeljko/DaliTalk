package com.zeljkostankovic.dalitalk.modules;


import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "item_table")
public class Item {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String description;
    public String task;
    public Bitmap image;

    public Item() {};

    public Item(String desc, String task, Bitmap img) {
        this.description = desc;
        this.task = task;
        this.image = img;
    }

}
