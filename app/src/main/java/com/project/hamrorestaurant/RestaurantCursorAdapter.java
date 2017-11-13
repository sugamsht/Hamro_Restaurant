package com.project.hamrorestaurant;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.project.hamrorestaurant.data.RestaurantContract.RestaurantEntry;

/**
 * Created by sugam on 11/9/2017.
 */

public class RestaurantCursorAdapter extends CursorAdapter {

    public RestaurantCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.restaurant_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView nameTextView = view.findViewById(R.id.restaurantName);
        TextView locationTextView = view.findViewById(R.id.restaurantLocation);

        int nameColumnIndex = cursor.getColumnIndex(RestaurantEntry.COLUMN_RESTAURANT_NAME);
        int locationColumnIndex = cursor.getColumnIndex(RestaurantEntry.COLUMN_RESTAURANT_LOCATION);

        String restaurantName = cursor.getString(nameColumnIndex);
        String restaurantLocation = cursor.getString(locationColumnIndex);

        nameTextView.setText(restaurantName);
        locationTextView.setText(restaurantLocation);

    }
}
