package com.project.hamrorestaurant.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.project.hamrorestaurant.data.RestaurantContract.RestaurantEntry;

/**
 * Created by sugam on 11/9/2017.
 */

public class RestaurantDbHelper extends SQLiteOpenHelper {


    public static final String LOG_TAG = RestaurantDbHelper.class.getSimpleName();

    public static final String DATABASE_NAME = "restaurantdb.db";

    public static final int DATABASE_VERSION = 1;


    public RestaurantDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_RESTAURANTS_TABLE = "CREATE TABLE  "
                + RestaurantEntry.TABLE_NAME + " ("
                + RestaurantEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RestaurantEntry.COLUMN_RESTAURANT_NAME + " TEXT NOT NULL, "
                + RestaurantEntry.COLUMN_RESTAURANT_LOCATION + " TEXT, "
                + RestaurantEntry.COLUMN_RESTAURANT_RATING + " INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_RESTAURANTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.

    }
}
