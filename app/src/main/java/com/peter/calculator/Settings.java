package com.peter.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;

/**
 * Created by 韬 on 2015/8/29 0029.
 */
public class Settings extends ActionBarActivity{
    private Menu menu;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(R.string.action_settings);

        super.onCreate(savedInstanceState);
    }

    private void init(){
        // TODO:
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_page_action_bar, menu);
        this.menu = menu;
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        return super.onCreateOptionsMenu(menu);
    }
}
