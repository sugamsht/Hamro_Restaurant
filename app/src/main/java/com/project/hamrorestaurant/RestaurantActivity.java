package com.project.hamrorestaurant;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.project.hamrorestaurant.data.RestaurantContract.RestaurantEntry;


public final class RestaurantActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    /**
     * Identifier for the restaurant data loader
     */
    private static final int RESTAURANT_LOADER = 0;

    /**
     * Adapter for the ListView
     */
    RestaurantCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // Setup FAB to open EditorActivity
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(RestaurantActivity.this, DetailActivity.class);
//                startActivity(intent);
//            }
//        });

        // Find the ListView which will be populated with the restaurant data
        ListView restaurantListView = findViewById(R.id.list);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        View emptyView = findViewById(R.id.empty_view);
        restaurantListView.setEmptyView(emptyView);

        // Setup an Adapter to create a list item for each row of restaurant data in the Cursor.
        // There is no restaurant data yet (until the loader finishes) so pass in null for the Cursor.
        mCursorAdapter = new RestaurantCursorAdapter(this, null);
        restaurantListView.setAdapter(mCursorAdapter);

        // Setup the item click listener
        restaurantListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Create new intent to go to {@link EditorActivity}
                Intent intent = new Intent(RestaurantActivity.this, DetailActivity.class);

                startActivity(intent);
            }
        });

        // Kick off the loader
        getLoaderManager().initLoader(RESTAURANT_LOADER, null, this);
    }

    private void insertRestaurant() {
        ContentValues values = new ContentValues();
        values.put(RestaurantEntry.COLUMN_RESTAURANT_NAME, "Hosanna");
        values.put(RestaurantEntry.COLUMN_RESTAURANT_LOCATION, "Gatthaghar");
        values.put(RestaurantEntry.COLUMN_RESTAURANT_RATING, 5);

        Uri newUri = getContentResolver().insert(RestaurantEntry.CONTENT_URI, values);
    }

    private void deleteAllRestaurants() {
        int rowsDeleted = getContentResolver().delete(RestaurantEntry.CONTENT_URI, null, null);
        Log.v("RestaurantActivity", rowsDeleted + "rows deleted from restaurantdatabse");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_restaurant, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertRestaurant();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                deleteAllRestaurants();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                RestaurantEntry._ID,
                RestaurantEntry.COLUMN_RESTAURANT_NAME,
                RestaurantEntry.COLUMN_RESTAURANT_LOCATION};

        return new CursorLoader(this,
                RestaurantEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);

    }
}