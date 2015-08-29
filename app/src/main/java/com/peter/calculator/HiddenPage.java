package com.peter.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.ArrayMap;
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
public class HiddenPage extends Activity {
    private GridView gridView;
    private List<Map<String, Object>> categories;
    private int[] categoryNames = { R.string.text, R.string.photo, R.string.note, R.string.diary };
    private int[] categoryIcons = { R.drawable.text, R.drawable.photo, R.drawable.note, R.drawable.diary };

    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name);
        setTheme(R.style.Theme_AppCompat_Light);

        setContentView(R.layout.hidden);

        init();
    }

    private void init() {
        gridView = (GridView) findViewById(R.id.grid);
        categories = new ArrayList<Map<String, Object>>();
    }

    private void getData(){

    }
}
