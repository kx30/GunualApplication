package com.example.nikolay.gunual;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nikolay.gunual.models.Weapon;

public class LocalDatabaseFavoriteDAO extends SQLiteOpenHelper implements FavoriteDAO {

    private static final String DATABASE_NAME = "Favorites.db";
    private static final String TABLE_NAME = "favorites";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "TITLE";
    private static final String COL_3 = "COUNTRY";
    private static final String COL_4 = "YEAR_OF_PRODUCTION";
    private static final String COL_5 = "TYPE_OF_BULLET";
    private static final String COL_6 = "EFFECTIVE_RANGE";
    private static final String COL_7 = "MUZZLE_VELOCITY";
    private static final String COL_8 = "LENGTH";
    private static final String COL_9 = "BARREL_LENGTH";
    private static final String COL_10 = "WEIGHT";
    private static final String COL_11 = "FEED_SYSTEM";
    private static final String COL_12 = "DESCRIPTION";
    private static final String COL_13 = "IMAGE_URL";

    public LocalDatabaseFavoriteDAO(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME +
                " (ID integer primary key autoincrement, TITLE text, COUNTRY text, YEAR_OF_PRODUCTION text, TYPE_OF_BULLET text," +
                " EFFECTIVE_RANGE text, MUZZLE_VELOCITY text, LENGTH text, BARREL_LENGTH text, " +
                "WEIGHT text, FEED_SYSTEM text, DESCRIPTION text, IMAGE_URL text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void addToFavorite(Weapon weapon) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, weapon.getTitle());
        contentValues.put(COL_3, weapon.getCountry());
        contentValues.put(COL_4, weapon.getYearOfProduction());
        contentValues.put(COL_5, weapon.getTypeOfBullet());
        contentValues.put(COL_6, weapon.getEffectiveRange());
        contentValues.put(COL_7, weapon.getMuzzleVelocity());
        contentValues.put(COL_8, weapon.getLength());
        contentValues.put(COL_9, weapon.getBarrelLength());
        contentValues.put(COL_10, weapon.getWeight());
        contentValues.put(COL_11, weapon.getFeedSystem());
        contentValues.put(COL_12, weapon.getDescription());
        contentValues.put(COL_13, weapon.getImageUrl());
        database.insert(TABLE_NAME, null, contentValues);
    }

    @Override
    public void removeFromFavorite(String title) {
        SQLiteDatabase database = getReadableDatabase();
        String query = String.format("DELETE FROM " + TABLE_NAME + " WHERE TITLE LIKE " + "'" + title + "'");
        database.execSQL(query);
    }

    @Override
    public boolean isFavorite(String title) {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = String.format("SELECT * FROM " + TABLE_NAME + " WHERE TITLE='%s';", title);
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    @Override
    public Cursor getFavoriteWeaponList() {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = String.format("SELECT * FROM " + TABLE_NAME);
        Cursor res = database.rawQuery(query, null);
        return res;
    }
}
