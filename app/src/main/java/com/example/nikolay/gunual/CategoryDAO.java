package com.example.nikolay.gunual;

import com.example.nikolay.gunual.models.WeaponCategory;

import java.util.ArrayList;

public interface CategoryDAO {
    ArrayList<WeaponCategory> loadCategoryList();
}
