package com.example.basicbudgetplanapp.model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "item_table", foreignKeys = @ForeignKey(entity = Category.class,
parentColumns = "id", childColumns = "category_id", onDelete = CASCADE))
public class Item extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id")
    private int itemId;
    @ColumnInfo(name = "item_name")
    private String itemName;
    @ColumnInfo(name = "item_price")
    private double itemPrice;
    @ColumnInfo(name = "category_id")
    private int categoryId;

    @Ignore
    public Item() {
    }

    public Item(int itemId, String itemName, double itemPrice, int categoryId) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.categoryId = categoryId;
    }

    @Bindable
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
        notifyPropertyChanged(BR.itemId);
    }

    @Bindable
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
        notifyPropertyChanged(BR.itemName);

    }

    @Bindable
    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
        notifyPropertyChanged(BR.itemPrice);
    }

    @Bindable
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        notifyPropertyChanged(BR.categoryId);
    }
}
