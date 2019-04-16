package com.example.nikolay.gunual;

import android.database.Cursor;

import com.example.nikolay.gunual.models.Weapon;

public interface FavoriteDAO {
    void addToFavorite(Weapon weapon);
    void removeFromFavorite(String title);
    boolean isFavorite(String title);
    Cursor getFavoriteWeaponList();
}

