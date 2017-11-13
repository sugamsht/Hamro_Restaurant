package com.project.hamrorestaurant.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by sugam on 11/9/2017.
 */

public class RestaurantContract {

    public static final String CONTENT_AUTHORITY = "com.project.hamrorestaurant";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_RESTAURANTS = "restaurants";

    RestaurantContract() {
    }

    public static final class RestaurantEntry implements BaseColumns {

        /**
         * The content URI to access the restaurant data in the provider
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_RESTAURANTS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of restaurants.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANTS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single restaurant.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RESTAURANTS;

        public final static String TABLE_NAME = "restaurants";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_RESTAURANT_NAME = "name";

        public final static String COLUMN_RESTAURANT_LOCATION = "location";

        public final static String COLUMN_RESTAURANT_RATING = "rating";
    }
}
