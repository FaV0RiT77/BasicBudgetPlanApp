package com.example.basicbudgetplanapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.basicbudgetplanapp.model.Category;
import com.example.basicbudgetplanapp.model.Item;
import com.example.basicbudgetplanapp.model.ItemListRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    // Repository
    private ItemListRepository repository;
    // LiveData
    private LiveData<List<Category>> allCategories;
    private LiveData<List<Item>> itemsOfSelectedCategory;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        repository = new ItemListRepository(application);
    }

    public LiveData<List<Category>> getAllCategories() {
        allCategories = repository.getCategories();
        return allCategories;
    }

    public LiveData<List<Item>> getItemsOfSelectedCategory(int categoryId) {
        itemsOfSelectedCategory = repository.getItems(categoryId);
        return itemsOfSelectedCategory;
    }

    public void addNewItem(Item item) {
        repository.insertItem(item);
    }

    public void updateItem(Item item) {
        repository.updateItem(item);
    }

    public void deleteItem(Item item) {
        repository.deleteItem(item);
    }

}
