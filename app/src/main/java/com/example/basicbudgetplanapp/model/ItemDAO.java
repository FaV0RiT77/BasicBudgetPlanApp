package com.example.basicbudgetplanapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDAO {

    @Insert
    void insert(Item item);

    @Update
    void update(Item item);

    @Delete
    void delete(Item item);

    @Query("SELECT * FROM item_table")
    LiveData<List<Item>> getAllItems();

    @Query("SELECT * FROM item_table WHERE category_id==:categoryId")
    LiveData<List<Item>> getItems(int categoryId);
}
