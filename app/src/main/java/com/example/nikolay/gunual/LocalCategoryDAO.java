package com.example.nikolay.gunual;

import com.example.nikolay.gunual.models.WeaponCategory;

import java.util.ArrayList;

public class LocalCategoryDAO implements CategoryDAO {

    private String[] mCategoryOfWeapons = {"Pistol", "Submachine gun", "Rifle", "Carbine", "Sniper rifle", "Machine gun", "Shotgun"};
    private Integer[] mDrawables = {R.drawable.pistol, R.drawable.submachine_gun, R.drawable.rifle,
            R.drawable.carbine, R.drawable.sniper_rifle, R.drawable.machine_gun, R.drawable.shotgun};

    @Override
    public ArrayList<WeaponCategory> loadCategoryList() {
        ArrayList<WeaponCategory> weaponCategories = new ArrayList<>();
        for (int i = 0; i < mCategoryOfWeapons.length; i++) {
            weaponCategories.add(new WeaponCategory(
                    mCategoryOfWeapons[i],
                    mDrawables[i]));
        }
        return weaponCategories;
    }
}
