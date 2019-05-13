package ru.webant.nikolay.gunual;

import ru.webant.nikolay.gunual.models.WeaponCategory;

import java.util.ArrayList;

public interface CategoryDAO {
    ArrayList<WeaponCategory> loadCategoryList();
}
