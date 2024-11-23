package com.pharmacy.pharmacymanagementsystem.doa;

import java.util.List;

import com.pharmacy.pharmacymanagementsystem.models.categories;

public interface CategoryRepo {
    List<categories> findAll();
    public void deleteCategory(int i);
    public void updateCategory(categories c);
    public void insertCategory(categories c);
}


