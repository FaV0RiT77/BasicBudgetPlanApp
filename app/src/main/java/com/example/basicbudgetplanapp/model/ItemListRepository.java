package com.example.basicbudgetplanapp.model;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ItemListRepository {
    private CategoryDAO categoryDAO;
    private ItemDAO itemDAO;

    private LiveData<List<Category>> categories;
    private LiveData<List<Item>> items;

    public ItemListRepository(Application application) {
        ItemDatabase itemDatabase = ItemDatabase.getInstance(application);
        categoryDAO = itemDatabase.categoryDAO();
        itemDAO = itemDatabase.itemDAO();
    }

    public LiveData<List<Category>> getCategories() {
        return categoryDAO.getAllCategories();
    }

    public LiveData<List<Item>> getItems(int categoryId) {
        return itemDAO.getItems(categoryId);
    }

    public void insertCategory(Category category) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            // Inserting Categories
            categoryDAO.insert(category);
        });
    }

    public void insertItem(Item item) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            // Inserting Categories
            itemDAO.insert(item);
        });
    }

    public void deleteCategory(Category category) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            // Inserting Categories
            categoryDAO.delete(category);
        });
    }

    public void deleteItem(Item item) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            // Inserting Categories
            itemDAO.delete(item);
        });
    }

    public void updateCategory(Category category) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            // Inserting Categories
            categoryDAO.update(category);
        });
    }

    public void updateItem(Item item) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            // Inserting Categories
            itemDAO.update(item);
        });
    }


}
