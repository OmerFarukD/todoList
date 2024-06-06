package com.example.todolist.utils.pagination;

import java.util.Collections;
import java.util.List;

public class Paginate<T> {
    private int size;
    private int index;
    private int count;
    private int pages;
    private List<T> items;

    public Paginate() {
        this.items = Collections.emptyList();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        this.pages = (int) Math.ceil((double) count / size);
    }

    public int getPages() {
        return pages;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public boolean hasPrevious() {
        return index > 0;
    }

    public boolean hasNext() {
        return index + 1 < pages;
    }
}
