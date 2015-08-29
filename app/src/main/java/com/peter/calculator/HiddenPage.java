package com.peter.calculator;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.util.ArrayMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by � on 2015/6/13 0013.
 */
public class HiddenPage extends ActionBarActivity {
    private GridView gridView;
    private SearchView searchView;
    private Menu menu;

    // Data Preparation
    private List<Map<String, Object>> categories;
    private int[] categoryNames = { R.string.text, R.string.photo, R.string.note, R.string.diary };
    private int[] categoryIcons = { R.drawable.text, R.drawable.photo, R.drawable.note, R.drawable.diary };

    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name);

        setContentView(R.layout.hidden);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        init();
    }

    private void init() {
        gridView = (GridView) findViewById(R.id.grid);
        categories = new ArrayList<Map<String, Object>>();
    }

    private void getData(){
        // TODO: Method to get data for four categories
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hidden_page_action_bar, menu);
        this.menu = menu;
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                break;
            case R.id.action_settings:
                Intent goToSettingsPage = new Intent(this, Settings.class);
                startActivity(goToSettingsPage);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                if(searchView.isShown()){
//                    Intent intent = new Intent(HiddenPage.this, MainActivity.class);
//                    startActivity(intent);
                }
                else {
                    searchView.onActionViewCollapsed();
                }
                break;
        }

        return super.onKeyDown(keyCode, event);
    }
}
