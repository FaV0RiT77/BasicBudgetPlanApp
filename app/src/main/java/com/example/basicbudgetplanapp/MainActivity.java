package com.example.basicbudgetplanapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.basicbudgetplanapp.databinding.ActivityMainBinding;
import com.example.basicbudgetplanapp.model.Category;
import com.example.basicbudgetplanapp.model.Item;
import com.example.basicbudgetplanapp.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ArrayList<Category> categoryList;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers handlers;
    private Category selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handlers = new MainActivityClickHandlers();
        activityMainBinding.setClickHandlers(handlers);

        mainActivityViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryList = (ArrayList<Category>) categories;

                for (Category c: categories) {
                    Log.i("TAG", c.getCategoryName());
                }

                showOnSpinner();
            }
        });

        mainActivityViewModel.getItemsOfSelectedCategory(1).observe(this, items -> {
            for (Item c : items) {
                Log.v("TAG", c.getItemName());
            }
        });
    }

    private void showOnSpinner() {
        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, categoryList);

        categoryArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(categoryArrayAdapter);
    }

    public class MainActivityClickHandlers {
        public void onFABClicked (View view) {
            Toast.makeText(getApplicationContext(), "FAB Clicked", Toast.LENGTH_SHORT).show();
        }

        public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {
            selectedCategory = (Category) parent.getItemAtPosition(pos);

            String message = "id is: " + selectedCategory.getId() + " \n name is " + selectedCategory.getCategoryName();
            Toast.makeText(parent.getContext(), "message", Toast.LENGTH_SHORT).show();
        }
    }
}