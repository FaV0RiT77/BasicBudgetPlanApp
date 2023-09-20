package com.example.basicbudgetplanapp.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Category.class, Item.class}, version = 1)
public abstract class ItemDatabase extends RoomDatabase {
    public abstract CategoryDAO categoryDAO();
    public abstract ItemDAO itemDAO();

    // Singleton Pattern
    private static ItemDatabase instance;
    public static synchronized ItemDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ItemDatabase.class, "items-database")
                    .fallbackToDestructiveMigration().addCallback(callback).build();
        }
        return instance;
    }

    //Callback
    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            initializeData();
            // Insert data when db is created
        }
    };

    private static void initializeData() {

        ItemDAO itemDAO = instance.itemDAO();
        CategoryDAO categoryDAO = instance.categoryDAO();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                // Categories
                Category category1 = new Category();
                category1.setCategoryName("Groceries");
                category1.setCategoryDescription("Stuff you buy in a supermarket");

                Category category2 = new Category();
                category2.setCategoryName("Subscriptions and bills");
                category2.setCategoryDescription("Stuff you pay monthly fees for");

                categoryDAO.insert(category1);
                categoryDAO.insert(category2);

                // Items

                Item item1 = new Item();
                item1.setItemName("Lemonade");
                item1.setItemPrice(2.6);
                item1.setCategoryId(1);

                Item item2 = new Item();
                item2.setItemName("Chocolate");
                item2.setItemPrice(2.2);
                item2.setCategoryId(1);

                Item item3 = new Item();
                item3.setItemName("Babynua Pilaki");
                item3.setItemPrice(6.7);
                item3.setCategoryId(1);

                Item item4 = new Item();
                item4.setItemName("Apache");
                item4.setItemPrice(11);
                item4.setCategoryId(1);

                itemDAO.insert(item1);
                itemDAO.insert(item2);
                itemDAO.insert(item3);
                itemDAO.insert(item4);
            }
        });
    }

}

